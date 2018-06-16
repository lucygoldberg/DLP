package com.proofpoint.dlp.controller;

import com.proofpoint.dlp.entity.DetectorType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Before
    public void setUp() {
        webTestClient = webTestClient
                .mutate()
                .responseTimeout(Duration.ofSeconds(60))
                .build();
    }

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
    @Test
    public void testFileRequest() {
        String path = "classpath:/static/file.txt";
        webTestClient.post().uri("/detect/file")
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just(path), String.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(DetectorType.CreditCard.toString());
    }

    @Test
    public void testDriveRequest() {
        String fileId = "1dJWb55zHCngbVDNcWrpcHFGG-fC3jHyVpW04ET65_U4";
        webTestClient.get().uri("/detect/drive?fileId=" + fileId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("SSN,IBAN,CreditCard");
    }

    @Test
    public void testDriveRequest_false() {
        String fileId = "1dJWb55zHCngbVDNcWrpcHFGG-fC3jHyVpW04ET65_U5";
        webTestClient.get().uri("/detect/drive?fileId=" + fileId)
                .exchange()
                .expectStatus().is4xxClientError();
    }
}
