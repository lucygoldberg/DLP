package com.proofpoint.dlp.controller;

import com.proofpoint.dlp.entity.DetectorType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Test
    public void testTextRequest() {
        String text = "My credit card number is 4012 8888 8888 1881";
        webTestClient.post().uri("/detect/text")
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just(text), String.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(DetectorType.CreditCard.toString());
    }
}
