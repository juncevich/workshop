package main

import (
	"./db"
	"./routes"
	"github.com/gofiber/fiber"
)

func main() {

	db.Connect()

	app := fiber.New()

	routes.Setup(app)
	app.Listen(":3000")
}
