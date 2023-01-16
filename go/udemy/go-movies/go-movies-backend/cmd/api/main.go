package main

import (
	"database/sql"
	"flag"
	"log"
	"net/http"
)

const port = "8080"

type application struct {
	DSN    string
	Domain string `json:"domain"`
	DB     *sql.DB
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
	app.DB = conn

	app.Domain = "example.com"

	log.Println("Starting application on port", port)

	//start a webserver
	err = http.ListenAndServe(":"+port, app.routes())

	if err != nil {
		log.Fatal(err)
	}
}
