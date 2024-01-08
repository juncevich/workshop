package main

import (
	"backend/database"
	"backend/routes"
	"os"

	"github.com/gin-gonic/gin"
)

func main() {
	//run database
	database.StartDB()

	port, router := setupRouter()

	router.Run(":" + port)

}

func setupRouter() (string, *gin.Engine) {
	port := os.Getenv("PORT")
	if port == "" {
		port = "8000"
	}
	router := gin.Default()

	//Log events
	router.Use(gin.Logger())

	//Register app routes here
	routes.AuthRoutes(router)
	routes.UserRoutes(router)

	router.GET("/api", func(c *gin.Context) {
		c.JSON(200, gin.H{
			"success": "Welcome to shive api!"})
	})
	return port, router
}
