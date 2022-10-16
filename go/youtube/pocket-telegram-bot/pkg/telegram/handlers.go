package telegram

import (
	"context"
	tgbotapi "github.com/go-telegram-bot-api/telegram-bot-api"
	"github.com/zhashkevych/go-pocket-sdk"
	"net/url"
)

const (
	commandStart           = "start"
	replyStart             = "Какой-то текст в ответ на команду старт."
	replyAlreadyAuthorized = "Ты уже авторизирован. Присылай ссылку, а я ее сохраню."
)

func (b *Bot) handleUpdates(updates tgbotapi.UpdatesChannel) {
	for update := range updates {
		if update.Message == nil {
			continue
		} else if update.Message.IsCommand() {
			if err := b.handleCommand(update.Message); err != nil {
				b.handleError(update.Message.Chat.ID, err)
			}
		} else {
			if err := b.handleMessage(update.Message); err != nil {
				b.handleError(update.Message.Chat.ID, err)
			}
		}

	}
}

func (b *Bot) handleMessage(message *tgbotapi.Message) error {
	_, err := url.ParseRequestURI(message.Text)

	if err != nil {
		return errInvalidUrl
		//msg.Text = "Это невалидная ссылка"
		//_, err = b.bot.Send(msg)
		//return err
	}
	accessToken, err := b.getAccessToken(message.Chat.ID)
	if err != nil {
		return errUnAuthorized
		//msg.Text = "Ты не авторизирован! Используй команду старт!"
		//_, err = b.bot.Send(msg)
		//return err
	}

	if err := b.pocketClient.Add(context.Background(), pocket.AddInput{
		AccessToken: accessToken,
		URL:         message.Text,
	}); err != nil {
		return errUnableToSave
		//msg.Text = "Увы, не удалось сохранить ссылку.Попробуй позже."
		//_, err = b.bot.Send(msg)
		//return err
	}
	msg := tgbotapi.NewMessage(message.Chat.ID, "Ссылка успешно сохранена")
	_, err = b.bot.Send(msg)
	return err
}

func (b *Bot) handleCommand(message *tgbotapi.Message) error {

	switch message.Command() {
	case commandStart:
		return b.handleCommandStart(message)
	default:
		return b.handleUnknownCommand(message)
	}
}

func (b *Bot) handleCommandStart(message *tgbotapi.Message) error {
	_, err := b.getAccessToken(message.Chat.ID)
	if err != nil {
		return b.initAuthorizationMessage(message)
	}
	msg := tgbotapi.NewMessage(message.Chat.ID, replyAlreadyAuthorized)
	_, err = b.bot.Send(msg)
	return err
}

func (b *Bot) handleUnknownCommand(message *tgbotapi.Message) error {
	msg := tgbotapi.NewMessage(message.Chat.ID, "Я не знаю такой команды (")
	_, err := b.bot.Send(msg)
	return err
}
