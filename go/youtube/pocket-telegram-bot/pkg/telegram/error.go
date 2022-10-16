package telegram

import (
	"errors"
	tgbotapi "github.com/go-telegram-bot-api/telegram-bot-api"
)

var (
	errInvalidUrl   = errors.New("url is invalid")
	errUnAuthorized = errors.New("user is not authorized")
	errUnableToSave = errors.New("unable to save")
)

func (b *Bot) handleError(chatId int64, err error) {
	msg := tgbotapi.NewMessage(chatId, "Произошла неизвестная ошибка")
	switch err {
	case errInvalidUrl:
		msg.Text = "Ты не авторизирован! Используй команду старт!"
		_, err = b.bot.Send(msg)
	case errUnAuthorized:
		msg.Text = "Ты не авторизирован! Используй команду старт!"
		_, err = b.bot.Send(msg)
	case errUnableToSave:
		msg.Text = "Увы, не удалось сохранить ссылку.Попробуй позже."
		_, err = b.bot.Send(msg)
	default:
		_, err = b.bot.Send(msg)
	}
}
