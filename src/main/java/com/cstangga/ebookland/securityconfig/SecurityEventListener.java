package com.cstangga.ebookland.securityconfig;


import org.springframework.context.event.EventListener;
import org.springframework.security.access.event.AuthorizationFailureEvent;
import org.springframework.stereotype.Component;


public class SecurityEventListener {

    @EventListener
    public void handleAuthFailure(AuthorizationFailureEvent event) {
        System.out.println("Authorization failure: " + event.getAccessDeniedException().getMessage());
        System.out.println("Request details: " + event.getSource());
    }
}