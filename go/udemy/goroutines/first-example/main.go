package main

import (
	"fmt"
	"time"
)

func main() {
	go printSomething("First string")
	time.Sleep(1 * time.Second)
	printSomething("Second string")
}

func printSomething(s string) {
	fmt.Println(s)
}
