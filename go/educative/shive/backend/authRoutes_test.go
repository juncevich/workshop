package main

import (
	"bytes"
	"encoding/json"
	"github.com/stretchr/testify/assert"
	"net/http"
	"net/http/httptest"
	"testing"
)

func TestCreateRoute(t *testing.T) {
	_, router := setupRouter()

	w := httptest.NewRecorder()
	// JSON body
	bodyBytes := []byte(`{
 		"Name":"name",
 		"Username":"user_name",
 		"Email":"user_email@email.com",
 		"Password":"user_password",
 		"User_type":"ADMIN"
 	}`)
	req, _ := http.NewRequest("POST", "/users/signup", bytes.NewBuffer(bodyBytes))
	router.ServeHTTP(w, req)

	assert.Equal(t, 201, w.Code)

	response := &UserCreateResponse{}
	json.NewDecoder(w.Body).Decode(response)
	assert.Equal(t, 201, response.Status)
	assert.Equal(t, "User created successfully!", response.Message)
}

func TestLoginRoute(t *testing.T) {
	_, router := setupRouter()

	signupRecorder := httptest.NewRecorder()
	// JSON body
	signupBodyBytes := []byte(`{
 		"Name":"name",
 		"Username":"user_name",
 		"Email":"user_email@email.com",
 		"Password":"user_password",
 		"User_type":"ADMIN"
 	}`)
	signupReq, _ := http.NewRequest("POST", "/users/signup", bytes.NewBuffer(signupBodyBytes))
	router.ServeHTTP(signupRecorder, signupReq)

	loginRecorder := httptest.NewRecorder()
	loginBodyBytes := []byte(`{
 		"Email":"user_email@email.com",
 		"Password":"user_password"
 	}`)
	loginReq, _ := http.NewRequest("POST", "/users/login", bytes.NewBuffer(loginBodyBytes))
	router.ServeHTTP(loginRecorder, loginReq)

	assert.Equal(t, 201, loginRecorder.Code)

	assert.Equal(t, "User created successfully!", loginRecorder.Body.String())
}

type UserCreateResponse struct {
	Data    string `json:"data"`
	Message string `json:"Message"`
	Status  int    `json:"Status"`
}

type UserLoginResponse struct {
	Data    string `json:"data"`
	Message string `json:"Message"`
	Status  int    `json:"Status"`
}
