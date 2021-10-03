package database

import (
	"backend/model"
	"fmt"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

var DB *gorm.DB

func Connect() {
	dsn := "host=localhost user=gorm_user password=gorm_password dbname=gorm_db port=5432 sslmode=disable"
	database, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		panic("Could not connect to the database")
	}
	fmt.Println(database)

	DB = database
	database.AutoMigrate(&model.User{})
}
