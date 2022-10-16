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
	msg := tgbotapi.NewMessage(chatId, b.messages.Default)
	switch err {
	case errInvalidUrl:
		msg.Text = b.messages.InvalidURL
		_, err = b.bot.Send(msg)
	case errUnAuthorized:
		msg.Text = b.messages.UnAuthorized
		_, err = b.bot.Send(msg)
	case errUnableToSave:
		msg.Text = b.messages.UnableToSave
		_, err = b.bot.Send(msg)
	default:
		_, err = b.bot.Send(msg)
	}
}
