package com.dropboxBusiness.dropbox_business_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DropboxClient {

    @Value("${dropbox.access.token}")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

}
