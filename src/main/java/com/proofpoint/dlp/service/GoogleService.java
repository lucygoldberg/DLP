package com.proofpoint.dlp.service;

import com.proofpoint.dlp.entity.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * https://help.talend.com/reader/Ovc10QFckCdvYbzxTECexA/EoAKa_oFqZFXH0aE0wNbHQ
 *
 * https://www.googleapis.com/drive/v2/files/1XNTa0WaSkVj-OzI-dNb70jAj8aH0Wjg4?alt=media&access_token=ya29.GlvcBSgbo9-cKAQYuVS1_th7wx2afgWNk0W1Pkz1RBhNqcv2asFjH6SyrzmyXjqg4r8KilUv_AGT948Orlot8gxeD0fJk5mALR5Zt1LLMI_4Ca-JvscwNuaTByLK
 */
public class GoogleService {
    private static final Logger logger = LoggerFactory.getLogger(GoogleService.class);

    private WebClient webClient = WebClient.create();
    @Value("${google.body.accesstoken}")
    private String bodyForAccessToken;
    @Value("${google.token.url}")
    private String tokenUrl;
    @Value("${google.file.url}")
    private String fileUrl;
    private Mono<String> getAccessToken() {
       return webClient.post().uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromObject(bodyForAccessToken))
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .map(TokenResponse::getAccessToken)
                .onErrorResume(e -> {
                    logger.error(e.getMessage());
                    return Mono.empty();
                });
    }
    private String buildUrl(String token, String fileId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(fileUrl);
        stringBuilder.append(fileId);
        stringBuilder.append("?alt=media&access_token=");
        stringBuilder.append(token);
        return stringBuilder.toString();
    }
    public Mono<String> getFile(String fileId) {
        return getAccessToken().map(token -> buildUrl(token, fileId))
                .flatMap(url -> runUrl(url));
    }

    public Mono<String> runUrl(String url) {
        return webClient.get()
                .uri(url)
                .exchange()
                .flatMap(response -> {
                    if (response.statusCode().is3xxRedirection()) {
                        String redirectUrl = response.headers().header("Location").get(0);
                        return response.bodyToMono(Void.class).then(runUrl(redirectUrl));
                    } else if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(String.class);
                    } else {
                        return Mono.empty();
                    }
                }).onErrorResume(e -> {
                    logger.error(e.getMessage());
                    return Mono.empty();
                });
    }
}
