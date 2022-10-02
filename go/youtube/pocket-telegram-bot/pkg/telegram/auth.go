package telegram

import (
	"context"
	"fmt"
	"pocket-telegram-bot/pkg/repository"
)

func (b *Bot) generateAuthorizationLink(chatId int64) (string, error) {
	redirectUrl := b.generateRedirectUrl(chatId)
	requestToken, err := b.pocketClient.GetRequestToken(context.Background(), redirectUrl)

	if err != nil {
		return "", err
	} else {
		if err := b.tokenRepository.Save(chatId, requestToken, repository.RequestTokens); err != nil {
			return "", err
		}
		return b.pocketClient.GetAuthorizationURL(requestToken, redirectUrl)
	}

}

func (b *Bot) generateRedirectUrl(chatId int64) string {
	return fmt.Sprintf("%s?chat_id=%d", b.redirectUrl, chatId)
}
