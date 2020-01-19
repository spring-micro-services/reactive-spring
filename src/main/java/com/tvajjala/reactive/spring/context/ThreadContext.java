package com.tvajjala.reactive.spring.context;

import org.springframework.http.HttpHeaders;

import java.io.Serializable;

/**
 * @author ThirupathiReddy Vajjala
 */
public final class ThreadContext implements Serializable{

    private HttpHeaders httpHeaders;
    // add any request specific context that you want to pass on to child threads.

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(HttpHeaders httpHeaders) {
        this.httpHeaders = httpHeaders;
    }
}
