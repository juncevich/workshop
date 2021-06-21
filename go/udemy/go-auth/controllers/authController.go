package controllers

import (
	"../models"
	"github.com/gofiber/fiber"
)

func Register(c *fiber.Ctx) error {

	var user models.User

	user.FirstName = "John"
	user.LastName = "Doe"
	user.Email = "john@doe.com"

	return c.JSON(user)
}
