package helpers

import (
	"backend/database"
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
	jwt.Claims
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
		Claims: jwt.MapClaims{
			"exp": time.Now().Local().Add(time.Hour * time.Duration(12)).Unix(),
		},
	}

	refreshClaims := &JwtSignedDetails{
		Claims: jwt.MapClaims{
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
