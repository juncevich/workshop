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

	testCtx := context.Background()
	server, err := mim.Start(testCtx, "5.0.2")

	client, err := mongo.NewClient(options.Client().ApplyURI(server.URI()))
	if err != nil {
		log.Fatal(err)
	}

	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	err = client.Connect(ctx)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Connected to MongoDB Successfully!")

	return client
}

var Client *mongo.Client = StartDB()

func OpenCollection(client *mongo.Client, collectionName string) *mongo.Collection {
	var collection *mongo.Collection = client.Database("shive-api").Collection(collectionName)
	return collection
}
