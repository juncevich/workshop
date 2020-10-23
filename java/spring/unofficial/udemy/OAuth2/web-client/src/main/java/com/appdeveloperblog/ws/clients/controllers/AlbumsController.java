package com.appdeveloperblog.ws.clients.controllers;

import com.appdeveloperblog.ws.clients.response.AlbumRest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumsController {

    final OAuth2AuthorizedClientService oAuth2ClientService;
    //    final RestTemplate                  restTemplate;
    final WebClient                     webClient;


    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

        String url = "http://localhost:8091/albums";
        final List<AlbumRest> responseEntity = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<AlbumRest>>() {
                })
                .block();

        model.addAttribute("albums", responseEntity);

        return "albums";
    }
}
