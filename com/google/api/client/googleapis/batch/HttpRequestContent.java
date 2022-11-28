package com.google.api.client.googleapis.batch;

import com.google.api.client.http.AbstractHttpContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
/* loaded from: classes2.dex */
class HttpRequestContent extends AbstractHttpContent {
    private static final String HTTP_VERSION = "HTTP/1.1";
    private final HttpRequest request;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpRequestContent(HttpRequest httpRequest) {
        super("application/http");
        this.request = httpRequest;
    }

    @Override // com.google.api.client.http.HttpContent, com.google.api.client.util.StreamingContent
    public void writeTo(OutputStream outputStream) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, b());
        outputStreamWriter.write(this.request.getRequestMethod());
        outputStreamWriter.write(" ");
        outputStreamWriter.write(this.request.getUrl().build());
        outputStreamWriter.write(" ");
        outputStreamWriter.write(HTTP_VERSION);
        outputStreamWriter.write("\r\n");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.fromHttpHeaders(this.request.getHeaders());
        httpHeaders.setAcceptEncoding(null).setUserAgent(null).setContentEncoding(null).setContentType(null).setContentLength(null);
        HttpContent content = this.request.getContent();
        if (content != null) {
            httpHeaders.setContentType(content.getType());
            long length = content.getLength();
            if (length != -1) {
                httpHeaders.setContentLength(Long.valueOf(length));
            }
        }
        HttpHeaders.serializeHeadersForMultipartRequests(httpHeaders, null, null, outputStreamWriter);
        outputStreamWriter.write("\r\n");
        outputStreamWriter.flush();
        if (content != null) {
            content.writeTo(outputStream);
        }
    }
}
