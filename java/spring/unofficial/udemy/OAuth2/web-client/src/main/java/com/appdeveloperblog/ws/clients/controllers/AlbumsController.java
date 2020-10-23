package com.appdeveloperblog.ws.clients.controllers;

import com.appdeveloperblog.ws.clients.response.AlbumRest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.util.Optional.ofNullable;

@Controller
@RequiredArgsConstructor
public class AlbumsController {

    final OAuth2AuthorizedClientService oAuth2ClientService;
    final RestTemplate                  restTemplate;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {
        final Authentication      authentication      = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;

        final OAuth2AuthorizedClient oAuth2AuthorizedClient =
                oAuth2ClientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(),
                        authenticationToken.getName());

        final String accessTokenValue = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        System.out.println("jwtAccessToken = " + accessTokenValue);

        System.out.println("Principal = " + principal);
        final OidcIdToken oauthToken = principal.getIdToken();
        ofNullable(oauthToken)
                .map(AbstractOAuth2Token::getTokenValue)
                .ifPresent(tokenValue -> System.out.println("idTokenValue = " + tokenValue));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessTokenValue);

        HttpEntity<List<AlbumRest>> httpEntity = new HttpEntity<>(headers);
        String url = "http://localhost:8082/albums";
        final ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<AlbumRest>>() {
        });
//        AlbumRest album1 = new AlbumRest();
//        album1.setAlbumId("albumIdHere");
//        album1.setUserId("1");
//        album1.setAlbumTitle("Album 1 title");
//        album1.setAlbumDescription("Album 1 description");
//        album1.setAlbumUrl("Album 1 URL");
//
//        AlbumRest album2 = new AlbumRest();
//        album2.setAlbumId("albumIdHere");
//        album2.setUserId("2");
//        album2.setAlbumTitle("Album 2 title");
//        album2.setAlbumDescription("Album 2 description");
//        album2.setAlbumUrl("Album 2 URL");

        model.addAttribute("albums", responseEntity.getBody());

        return "albums";
    }
}
