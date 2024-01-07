package database

import (
	"context"
	"fmt"
	mim "github.com/ONSdigital/dp-mongodb-in-memory"
	"log"
	"time"

	"github.com/joho/godotenv"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

func StartDB() *mongo.Client {
	err := godotenv.Load(".env")
	if err != nil {
		log.Fatal("Error loading  the .env file")
	}

	//MongoDb := os.Getenv("MONGOURI")

	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	server, err := mim.Start(ctx, "5.0.2")

	client, err := mongo.Connect(ctx, options.Client().ApplyURI(server.URI()))
	if err != nil {
		log.Fatal(err)
	}

	defer cancel()
	fmt.Println("Connected to MongoDB Successfully!")

	return client
}

var Client = StartDB()

func OpenCollection(client *mongo.Client, collectionName string) *mongo.Collection {
	var collection = client.Database("shive-api").Collection(collectionName)
	return collection
}
