package com.wix.mediaplatform.http;


import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import static org.apache.http.HttpHeaders.ACCEPT;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class Constants {
    public static final Header ACCEPT_JSON = new BasicHeader(ACCEPT, APPLICATION_JSON.getMimeType());
}
