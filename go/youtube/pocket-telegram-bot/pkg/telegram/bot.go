package telegram

import (
	tgbotapi "github.com/go-telegram-bot-api/telegram-bot-api"
	"github.com/zhashkevych/go-pocket-sdk"
	"log"
	"pocket-telegram-bot/pkg/repository"
)

type Bot struct {
	bot             *tgbotapi.BotAPI
	pocketClient    *pocket.Client
	redirectUrl     string
	tokenRepository repository.TokenRepository
}

func NewBot(bot *tgbotapi.BotAPI, pocketClient *pocket.Client, tokenRepository repository.TokenRepository, redirectUrl string) *Bot {
	return &Bot{bot: bot, pocketClient: pocketClient, tokenRepository: tokenRepository, redirectUrl: redirectUrl}
}

func (b *Bot) Start() error {

	log.Printf("Authorized on account %s", b.bot.Self.UserName)
	updates, err := b.initUpdatesChannel()
	if err != nil {
		return err
	}
	b.handleUpdates(updates)
	return nil
}

func (b *Bot) initUpdatesChannel() (tgbotapi.UpdatesChannel, error) {
	u := tgbotapi.NewUpdate(0)
	u.Timeout = 60

	return b.bot.GetUpdatesChan(u)
}