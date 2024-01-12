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

	response := &UserLoginResponse{}
	json.NewDecoder(loginRecorder.Body).Decode(response)
	assert.Equal(t, 200, loginRecorder.Code)

	assert.NotEmpty(t, response.ID)
	assert.Equal(t, "name", response.Name)
	assert.Equal(t, "user_name", response.Username)
	assert.NotEmpty(t, response.Password)
	assert.Equal(t, "user_email@email.com", response.Email)
	assert.Equal(t, "ADMIN", response.UserType)
	assert.NotEmpty(t, response.RefreshToken)
	assert.NotEmpty(t, response.CreatedAt)
	assert.NotEmpty(t, response.UpdatedAt)
	assert.NotEmpty(t, response.UserId)
}

func TestGetUserRoute(t *testing.T) {
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
	userLoginResponse := &UserLoginResponse{}
	json.NewDecoder(loginRecorder.Body).Decode(userLoginResponse)

	getUserRecorder := httptest.NewRecorder()
	getUserReq, _ := http.NewRequest("GET", "/users/"+userLoginResponse.UserId, nil)
	getUserReq.Header.Add("token", userLoginResponse.Token)
	router.ServeHTTP(getUserRecorder, getUserReq)

	response := &UserLoginResponse{}
	json.NewDecoder(getUserRecorder.Body).Decode(response)
	assert.Equal(t, 200, loginRecorder.Code)

	//assert.Equal(t, "User created successfully!", response)
	assert.NotEmpty(t, response.ID)
	assert.Empty(t, response.Name)
	assert.Empty(t, response.Username)
	assert.Empty(t, response.Password)
	assert.Empty(t, response.Email)
	assert.Empty(t, response.UserType)
	assert.Empty(t, response.RefreshToken)
	assert.NotEmpty(t, response.CreatedAt)
	assert.NotEmpty(t, response.UpdatedAt)
	assert.Empty(t, response.UserId)
}

type UserCreateResponse struct {
	Data    string `json:"data"`
	Message string `json:"Message"`
	Status  int    `json:"Status"`
}

type UserLoginResponse struct {
	ID           string `json:"ID"`
	Name         string `json:"name"`
	Username     string `json:"username"`
	Password     string `json:"password"`
	Email        string `json:"email"`
	Token        string `json:"token"`
	UserType     string `json:"user_type"`
	RefreshToken string `json:"refresh_token"`
	CreatedAt    string `json:"created_at"`
	UpdatedAt    string `json:"updated_at"`
	UserId       string `json:"user_id"`
}
