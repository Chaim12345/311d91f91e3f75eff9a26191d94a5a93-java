package com.google.api.client.http.javanet;

import java.net.HttpURLConnection;
import java.net.URL;
/* loaded from: classes2.dex */
public interface ConnectionFactory {
    HttpURLConnection openConnection(URL url);
}
