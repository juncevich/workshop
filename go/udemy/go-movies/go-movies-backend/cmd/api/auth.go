package main

import "time"

type Auth struct {
	Issuer        string        `json:"issuer"`
	Audience      string        `json:"audience"`
	Secret        string        `json:"secret"`
	TokenExpiry   time.Duration `json:"token_expiry"`
	RefreshExpiry time.Duration `json:"refresh_expiry"`
	CookieDomain  string        `json:"cookie_domain"`
	CookiePath    string        `json:"cookie_path"`
	CookieName    string        `json:"cookie_name"`
}

type JwtUser struct {
	ID        int    `json:"id"`
	Firstname string `json:"firstname"`
	Lastname  string `json:"lastname"`
}

type TokenPairs struct {
	Token        string `json:"token"`
	RefreshToken string `json:"refresh_token"`
}

type Claims struct {
	jwt.RegisteredClaims
}
