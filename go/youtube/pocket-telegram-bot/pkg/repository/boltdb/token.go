package boltdb

import (
	"errors"
	"github.com/boltdb/bolt"
	"pocket-telegram-bot/pkg/repository"
	"strconv"
)

type TokenRepository struct {
	db *bolt.DB
}

func NewTokenRepository(db *bolt.DB) *TokenRepository {
	return &TokenRepository{db: db}
}

func (r *TokenRepository) Save(chatId int64, token string, bucket repository.Bucket) error {
	return r.db.Update(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(bucket))

		return b.Put(intToBytes(chatId), []byte(token))
	})
}
func (r *TokenRepository) Get(chatId int64, bucket repository.Bucket) (string, error) {
	var token string

	err := r.db.View(func(tx *bolt.Tx) error {
		b := tx.Bucket([]byte(bucket))
		data := b.Get(intToBytes(chatId))
		token = string(data)
		return nil
	})

	if err != nil {
		return "", err
	}

	if token == "" {
		return "", errors.New("token not found")
	}

	return token, nil

}

func intToBytes(v int64) []byte {
	return []byte(strconv.FormatInt(v, 10))
}
