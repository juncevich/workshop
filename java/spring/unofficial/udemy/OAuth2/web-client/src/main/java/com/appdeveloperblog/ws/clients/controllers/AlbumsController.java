package com.appdeveloperblog.ws.clients.controllers;

import com.appdeveloperblog.ws.clients.response.AlbumRest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static java.util.Optional.ofNullable;

@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

        System.out.println("Principal = " + principal);
        ofNullable(principal.getIdToken())
                .map(AbstractOAuth2Token::getTokenValue)
                .ifPresent(tokenValue -> System.out.println("idTokenValue = " + tokenValue));
        AlbumRest album1 = new AlbumRest();
        album1.setAlbumId("albumIdHere");
        album1.setUserId("1");
        album1.setAlbumTitle("Album 1 title");
        album1.setAlbumDescription("Album 1 description");
        album1.setAlbumUrl("Album 1 URL");

        AlbumRest album2 = new AlbumRest();
        album2.setAlbumId("albumIdHere");
        album2.setUserId("2");
        album2.setAlbumTitle("Album 2 title");
        album2.setAlbumDescription("Album 2 description");
        album2.setAlbumUrl("Album 2 URL");

        model.addAttribute("albums", List.of(album1, album2));

        return "albums";
    }
}
