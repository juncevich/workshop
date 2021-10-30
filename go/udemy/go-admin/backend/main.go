package main

import (
	"backend/database"
	"backend/midlewares"
	"backend/routes"
	"github.com/gofiber/fiber/v2"
	"github.com/gofiber/fiber/v2/middleware/cors"
)

func main() {

	database.Connect()

	app := fiber.New()
	app.Use(cors.New(cors.Config{AllowCredentials: true}))
	app.Use(midlewares.IsAuthenticated)
	routes.Setup(app)
	app.Listen(":8000")
}
