package com.learning.sergio.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequest;
import com.learning.sergio.urlshortener.domain.request.ShortenUrlRequestMother;
import com.learning.sergio.urlshortener.domain.response.*;
import com.learning.sergio.urlshortener.service.UrlService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UrlControllerUnitTest {

    private static final String FIND_FULL_URL_PATH = "/{shortenedUrl}";
    private static final String GET_URL_DETAIL_PATH = "/detail/{shortenedUrl}";

    private final UrlService urlService = mock(UrlService.class);
    private final UrlController urlController = new UrlController(urlService);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(urlController).build();

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(urlService);
    }

    @Test
    void givenValidRequestWhenShortenUrlThenReturnShortenedUrl() throws Exception {
        ShortenUrlRequest shortenUrlRequest = ShortenUrlRequestMother.createShortenUrlRequest();
        ShortenUrlResponse shortenUrlResponse = ShortenUrlResponseMother.createShortenUrlResponse();

        given(urlService.shortenUrl(any(ShortenUrlRequest.class)))
                .willReturn(shortenUrlResponse);

        Gson gson = new Gson();
//        String jsonBody = gson.toJson(shortenUrlRequest);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(shortenUrlRequest);

        mockMvc.perform(
                post("/")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody)
        ).andExpect(status().isCreated())
                .andExpect(jsonPath("$.shortenedUrl")
                        .value(shortenUrlResponse.shortenedUrl()));

        ArgumentCaptor<ShortenUrlRequest> argumentCaptor = ArgumentCaptor.forClass(ShortenUrlRequest.class);
        verify(urlService).shortenUrl(argumentCaptor.capture());

        then(argumentCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(shortenUrlRequest);
    }

    @Test
    void givenValidRequestWhenFindFullUrlThenReturnSuccess() throws Exception {
        String shortenedUrl = "shortenedUrl";
        FindFullUrlResponse findFullUrlResponse = FindFullUrlResponseMother.createFindFullUrlResponse();

        given(urlService.findFullUrl(shortenedUrl))
                .willReturn(findFullUrlResponse);

        mockMvc.perform(
                get(FIND_FULL_URL_PATH, shortenedUrl)
        ).andExpect(status().isAccepted())
                .andExpect(jsonPath("$.fullUrl")
                        .value(findFullUrlResponse.fullUrl()));

        verify(urlService)
                .findFullUrl(shortenedUrl);
    }

    @Test
    void givenValidRequestWhenGetUrlDetailThenReturnSuccess() throws Exception {
        String shortenedUrl = "shortenedUrl";
        UrlDetailResponse urlDetailResponse = UrlDetailResponseMother.createUrlDetailResponse();

        given(urlService.getUrlDetail(shortenedUrl))
                .willReturn(urlDetailResponse);

        mockMvc.perform(
                get(GET_URL_DETAIL_PATH, shortenedUrl)
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id")
                        .value(urlDetailResponse.id()))
                .andExpect(jsonPath("$.shortenedUrl")
                        .value(urlDetailResponse.shortenedUrl()))
                .andExpect(jsonPath("$.fullUrl")
                        .value(urlDetailResponse.fullUrl()))
                .andExpect(jsonPath("$.clickCount")
                        .value(urlDetailResponse.clickCount()));

        verify(urlService)
                .getUrlDetail(shortenedUrl);
    }
}
