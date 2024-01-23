package models

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
	"time"
)

type Genre struct {
	Id        primitive.ObjectID `bson:"_id"`
	Name      *string            `json:"name" validate:"required,min=4,max=100"`
	CreatedAt time.Time          `json:"created_at"`
	UpdatedAt time.Time          `json:"updated_at"`
	GenreId   int                `json:"genre_id" validate:"required"`
}
