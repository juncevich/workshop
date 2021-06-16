package main

import (
	"fmt"
	"github.com/gofiber/fiber"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

func main() {

	dsn := "host=localhost user=gorm_user password=gorm_password dbname=go_auth port=5432 sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		panic("Could not connect with the database")
	}

	fmt.Print(db)
	app := fiber.New()

	app.Get("/", home)

	app.Listen(":3000")
}

func home(c *fiber.Ctx) error {
	return c.SendString("Hello, World 👋!")
}
