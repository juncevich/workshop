package main

import (
	"backend/internal/repository"
	dbrepo "backend/internal/repository/db_repo"
	"flag"
	"log"
	"net/http"
)

const port = "8080"

type application struct {
	DSN    string
	Domain string `json:"domain"`
	DB     repository.DatabaseRepo
}

func main() {
	//set application config
	var app application
	//read from commandline
	flag.StringVar(&app.DSN, "dsn", "host=localhost port=5432 user=postgres password=postgres dbname=movies sslmode=disable timezone=UTC connect_timeout=5", "Postgres connection string")
	flag.Parse()
	// connect to database

	conn, err := app.connectToDB()
	if err != nil {
		log.Fatal(err)
	}
	app.DB = &dbrepo.PostgresDBRepo{DB: conn}
	defer app.DB.Connection().Close()

	app.Domain = "example.com"

	log.Println("Starting application on port", port)

	//start a webserver
	err = http.ListenAndServe(":"+port, app.routes())

	if err != nil {
		log.Fatal(err)
	}
}
