package controllers

import (
	"backend/database"
	"backend/midlewares"
	"backend/model"
	"github.com/gofiber/fiber/v2"
	"strconv"
)

func AllUsers(c *fiber.Ctx) error {
	if err := midlewares.IsAuthorized(c, "users"); err != nil {
		return err
	}
	page, _ := strconv.Atoi(c.Query("page", "1"))

	return c.JSON(model.Paginate(database.DB, &model.User{}, page))
}

func CreateUser(c *fiber.Ctx) error {
	if err := midlewares.IsAuthorized(c, "users"); err != nil {
		return err
	}
	var user model.User

	if err := c.BodyParser(&user); err != nil {
		return err
	}

	user.SetPassword("1234")

	database.DB.Create(&user)

	return c.JSON(user)
}

func GetUser(c *fiber.Ctx) error {
	if err := midlewares.IsAuthorized(c, "users"); err != nil {
		return err
	}
	id, _ := strconv.Atoi(c.Params("id"))

	user := model.User{
		Id: uint(id),
	}

	database.DB.Find(&user)
	return c.JSON(user)
}

func UpdateUser(c *fiber.Ctx) error {
	if err := midlewares.IsAuthorized(c, "users"); err != nil {
		return err
	}
	id, _ := strconv.Atoi(c.Params("id"))

	user := model.User{
		Id: uint(id),
	}

	if err := c.BodyParser(&user); err != nil {
		return err
	}

	database.DB.Model(&user).Updates(user)
	return c.JSON(user)
}

func DeleteUser(c *fiber.Ctx) error {
	if err := midlewares.IsAuthorized(c, "users"); err != nil {
		return err
	}
	id, _ := strconv.Atoi(c.Params("id"))

	user := model.User{
		Id: uint(id),
	}

	database.DB.Delete(user)
	return nil
}
