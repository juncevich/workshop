package models

import (
	"time"

	"go.mongodb.org/mongo-driver/bson/primitive"
)

type User struct {
	ID           primitive.ObjectID `bson:"_id"`
	Name         *string            `json:"name" validate:"required,min=4,max=100"`
	Username     *string            `json:"username" validate:"required,min=4,max=100"`
	Password     *string            `json:"password" validate:"required,min=8"`
	Email        *string            `json:"email" validate:"email,required"`
	Token        *string            `json:"token"`
	UserType     *string            `json:"user_type" validate:"required,eq=ADMIN|eq=USER"`
	RefreshToken *string            `json:"refresh_token"`
	CreatedAt    time.Time          `json:"created_at"`
	UpdatedAt    time.Time          `json:"updated_at"`
	UserId       string             `json:"user_id"`
}
