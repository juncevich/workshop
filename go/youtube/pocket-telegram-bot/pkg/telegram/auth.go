package telegram

import (
	"context"
	"fmt"
)

func (b *Bot) generateAuthorizationLink(chatId int64) (string, error) {
	redirectUrl := b.generateRedirectUrl(chatId)
	requestToken, err := b.pocketClient.GetRequestToken(context.Background(), redirectUrl)

	if err != nil {
		return "", err
	} else {
		return b.pocketClient.GetAuthorizationURL(requestToken, redirectUrl)
	}

}

func (b *Bot) generateRedirectUrl(chatId int64) string {
	return fmt.Sprintf("%s?chat_id=%d", b.redirectUrl, chatId)
}
