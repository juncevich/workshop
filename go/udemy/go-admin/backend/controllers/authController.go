package controllers

import (
	"backend/model"
	"github.com/gofiber/fiber/v2"
)

func Register(c *fiber.Ctx) error {
	user := model.User{
		FirstName: "John",
		LastName:  "Doe",
	}
	return c.JSON(user)
}
