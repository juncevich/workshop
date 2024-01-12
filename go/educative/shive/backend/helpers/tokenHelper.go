package helpers

import (
	"backend/database"
	"context"
	"fmt"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo/options"
	"log"
	"os"
	"time"

	//jwt "github.com/dgrijalva/jwt-go"
	jwt "github.com/golang-jwt/jwt/v5"
)

type JwtSignedDetails struct {
	Email    string
	Name     string
	Username string
	Uid      string
	UserType string
	jwt.MapClaims
}

var userCollection = database.OpenCollection(database.Client, "user")

var SecretKey = os.Getenv("SECRET_KEY")

func GenerateAllTokens(
	email string,
	name string,
	userName string,
	userType string,
	uid string) (
	signedToken string,
	signedRefreshToken string,
	err error) {
	claims := &JwtSignedDetails{
		Email:    email,
		Name:     name,
		Username: userName,
		Uid:      uid,
		UserType: userType,
		MapClaims: jwt.MapClaims{
			"exp": time.Now().Local().Add(time.Hour * time.Duration(12)).Unix(),
		},
	}

	refreshClaims := &JwtSignedDetails{
		MapClaims: jwt.MapClaims{
			"exp": time.Now().Local().Add(time.Hour * time.Duration(100)).Unix(),
		},
	}

	token, err := jwt.NewWithClaims(jwt.SigningMethodHS256, claims).SignedString([]byte(SecretKey))
	refreshToken, err := jwt.NewWithClaims(jwt.SigningMethodHS256, refreshClaims).SignedString([]byte(SecretKey))

	if err != nil {
		log.Panic(err)
		return
	}

	return token, refreshToken, err
}

func ValidateToken(signedToken string) (claims *JwtSignedDetails, msg string) {
	token, err := jwt.ParseWithClaims(
		signedToken,
		&JwtSignedDetails{},
		func(token *jwt.Token) (interface{}, error) {
			return []byte(SecretKey), nil
		},
	)

	if err != nil {
		msg = err.Error()
		return
	}

	claims, ok := token.Claims.(*JwtSignedDetails)
	if !ok {
		msg = fmt.Sprintf("This token is incorrect. Sorry!")
		msg = err.Error()
		return
	}

	exp, err := claims.GetExpirationTime()
	if err != nil {
		msg = fmt.Sprintf("Cannot fetch expiration time. Sorry!")
		msg = err.Error()
		return
	}

	if exp.Unix() < time.Now().Local().Unix() {
		msg = fmt.Sprintf("Ooops looks like your token has expired")
		msg = err.Error()
		return
	}
	return claims, msg
}

func UpdateTokens(signedToken string, signedRefreshToken string, userId string) {
	var ctx, cancel = context.WithTimeout(context.Background(), 100*time.Second)

	var updatableToken primitive.D

	updatableToken = append(updatableToken, bson.E{Key: "token", Value: signedToken})
	updatableToken = append(updatableToken, bson.E{Key: "refresh_token", Value: signedRefreshToken})

	UpdatedAt, _ := time.Parse(time.RFC3339, time.Now().Format(time.RFC3339))
	updatableToken = append(updatableToken, bson.E{Key: "updated_at", Value: UpdatedAt})

	upsert := true
	filter := bson.M{"user_id": userId}
	opt := options.UpdateOptions{
		Upsert: &upsert,
	}

	_, err := userCollection.UpdateOne(
		ctx,
		filter,
		bson.D{
			{Key: "$set", Value: updatableToken},
		},
		&opt,
	)

	defer cancel()

	if err != nil {
		log.Panic(err)
		return
	}
	return
}
