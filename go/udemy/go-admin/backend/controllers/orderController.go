package controllers

import (
	"backend/database"
	"backend/model"
	"github.com/gofiber/fiber/v2"
	"strconv"
)

func AllOrders(c *fiber.Ctx) error {
	page, _ := strconv.Atoi(c.Query("page", "1"))

	return c.JSON(model.Paginate(database.DB, &model.Order{}, page))
}
