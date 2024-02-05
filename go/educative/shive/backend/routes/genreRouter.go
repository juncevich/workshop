package routes

import (
	controllers "backend/controllers"
	"backend/middleware"

	"github.com/gin-gonic/gin"
)

func GenreRoutes(router *gin.Engine) {
	router.Use(middleware.AuthenticateUser())
	router.POST("/genres/creategenre", controllers.CreateGenre())
	router.GET("/genres/:genre_id", controllers.GetGenre())
}
