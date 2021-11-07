package controllers

import (
	"backend/database"
	"backend/model"
	"github.com/gofiber/fiber/v2"
)

func AllPermissions(c *fiber.Ctx) error {
	var permissions []model.Permission

	database.DB.Find(&permissions)
	return c.JSON(permissions)
}
