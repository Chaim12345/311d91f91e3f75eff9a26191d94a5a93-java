package com.google.api.client.googleapis.util;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Beta;
@Beta
/* loaded from: classes2.dex */
public final class Utils {

    /* loaded from: classes2.dex */
    private static class JsonFactoryInstanceHolder {

        /* renamed from: a  reason: collision with root package name */
        static final JsonFactory f8036a = new JacksonFactory();

        private JsonFactoryInstanceHolder() {
        }
    }

    /* loaded from: classes2.dex */
    private static class TransportInstanceHolder {

        /* renamed from: a  reason: collision with root package name */
        static final HttpTransport f8037a = new NetHttpTransport();

        private TransportInstanceHolder() {
        }
    }

    private Utils() {
    }

    public static JsonFactory getDefaultJsonFactory() {
        return JsonFactoryInstanceHolder.f8036a;
    }

    public static HttpTransport getDefaultTransport() {
        return TransportInstanceHolder.f8037a;
    }
}
