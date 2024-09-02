package com.cainant.testebackendtgid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookExecutionService {

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(
            value = {HttpServerErrorException.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )

    public void executeWebhook(String webhookURL, String payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(payload, headers);

        restTemplate.postForObject(webhookURL, request, String.class);
    }

}

