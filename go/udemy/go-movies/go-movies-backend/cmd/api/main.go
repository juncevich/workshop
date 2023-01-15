package main

import (
	"log"
	"net/http"
)

const port = "8080"

type application struct {
	Domain string `json:"domain"`
}

func main() {
	//set application config
	var app application
	//read from commandline

	// connect to database

	app.Domain = "example.com"

	log.Println("Starting application on port", port)
	http.HandleFunc("/", Hello)
	//start a webserver
	err := http.ListenAndServe(":"+port, app.routes())

	if err != nil {
		log.Fatal(err)
	}
}
