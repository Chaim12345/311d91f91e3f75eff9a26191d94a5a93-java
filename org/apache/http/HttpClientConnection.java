package org.apache.http;
/* loaded from: classes3.dex */
public interface HttpClientConnection extends HttpConnection {
    void flush();

    boolean isResponseAvailable(int i2);

    void receiveResponseEntity(HttpResponse httpResponse);

    HttpResponse receiveResponseHeader();

    void sendRequestEntity(HttpEntityEnclosingRequest httpEntityEnclosingRequest);

    void sendRequestHeader(HttpRequest httpRequest);
}
