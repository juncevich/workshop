package db

import (
	"../models"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var DB *gorm.DB

func Connect() {
	dsn := "host=localhost user=gorm_user password=gorm_password dbname=go_auth port=5432 sslmode=disable"
	connection, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		panic("Could not connect with the database")
	}

	DB = connection

	connection.AutoMigrate(
		&models.User{},
		&models.PasswordReset{},
	)
}
