package controllers

import (
	"../db"
	"../models"
	"github.com/gofiber/fiber"
	"math/rand"
	"net/smtp"
)

func Forgot(c *fiber.Ctx) error {
	var data map[string]string

	if err := c.BodyParser(&data); err != nil {
		return err
	}

	token := RandStringRunes(12)

	passwordReset := models.PasswordReset{
		Email: data["email"],
		Token: token,
	}

	db.DB.Create(&passwordReset)

	from := "admin@example.com"

	to := []string{
		data["email"],
	}

	url := "http://localhost:3000/reset/" + token

	message := []byte("Click <a href=\"" + url + "\"> here</a> to reset your password!")

	err := smtp.SendMail("0.0.0.0:1025", nil, from, to, message)

	if err != nil {
		return err
	}
	return c.JSON(fiber.Map{
		"message": "success",
	})
}

func Reset(c *fiber.Ctx) error {
	var data map[string]string
	if err := c.BodyParser(&data); err != nil {
		return err
	}

	if data["password"] != data["password_confirm"] {
		c.Status(400)
		return c.JSON(fiber.Map{
			"message": "Password do not match!",
		})
	}

	var passwordReset = models.PasswordReset{}

	if err := db.DB.Where("token=?", data["token"]).Last(&passwordReset); err.Error != nil {
		c.Status(400)
		return c.JSON(fiber.Map{
			"message": "Invalid token!",
		})
	}

	return c.JSON(fiber.Map{
		"message": "success",
	})
}

func RandStringRunes(n int) string {
	var letterRunes = []rune("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")

	b := make([]rune, n)
	for i := range b {
		b[i] = letterRunes[rand.Intn(len(letterRunes))]
	}
	return string(b)
}
