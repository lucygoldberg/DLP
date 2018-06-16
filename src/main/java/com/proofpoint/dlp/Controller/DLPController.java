package com.proofpoint.dlp.Controller;

import com.proofpoint.dlp.service.DetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.FileReader;
import java.io.IOException;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
public class DLPController {
    @Autowired
    private DetectorService detectorService;

    @Autowired
    private ResourceLoader resourceLoader;
    @GetMapping(value = "keepalive")
    public String keepalive() {
        return "OK!!!";
    }

    @PostMapping(value = "/detect/text", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<String> getSensitiveTypes(@RequestBody Mono<String> requestMono) {
        return requestMono.map(detectorService::getSensitiveTypes);
    }

    @PostMapping(value = "/detect/file", consumes = MediaType.TEXT_PLAIN_VALUE)
    public Mono<ResponseEntity<String>> getSensitiveTypesByPath(@RequestBody Mono<String> requestMono) {
        return requestMono.map(resourceLoader::getResource)
                .map(resource -> {
                            try {
                                String text = FileCopyUtils.copyToString(new FileReader(resource.getFile()));
                                return ok().body(detectorService.getSensitiveTypes(text));
                            } catch (IOException e) {
                                return badRequest().body("No file exists");
                            }
                        });
    }
}
