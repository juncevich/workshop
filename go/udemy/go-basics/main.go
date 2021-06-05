package main

import "gorm.io/gorm"
import "gorm.io/driver/postgres"

func main() {
	dsn := "host=localhost user=gorm_user password=gorm_password dbname=gorm_db port=5432 sslmode=disable"
	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})

	if err != nil {
		panic("Could not connect with the database")
	}

	db.AutoMigrate(&User{})
}

type User struct {
	Id        int
	FirstName string
	LastName  string
	Email     string
}
