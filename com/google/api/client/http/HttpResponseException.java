package com.google.api.client.http;

import com.google.api.client.util.Preconditions;
import com.google.api.client.util.StringUtils;
import java.io.IOException;
import org.apache.http.message.TokenParser;
/* loaded from: classes2.dex */
public class HttpResponseException extends IOException {
    private static final long serialVersionUID = -1875819453475890043L;
    private final String content;
    private final transient HttpHeaders headers;
    private final int statusCode;
    private final String statusMessage;

    /* loaded from: classes2.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        int f8045a;

        /* renamed from: b  reason: collision with root package name */
        String f8046b;

        /* renamed from: c  reason: collision with root package name */
        HttpHeaders f8047c;

        /* renamed from: d  reason: collision with root package name */
        String f8048d;

        /* renamed from: e  reason: collision with root package name */
        String f8049e;

        public Builder(int i2, String str, HttpHeaders httpHeaders) {
            setStatusCode(i2);
            setStatusMessage(str);
            setHeaders(httpHeaders);
        }

        public Builder(HttpResponse httpResponse) {
            this(httpResponse.getStatusCode(), httpResponse.getStatusMessage(), httpResponse.getHeaders());
            try {
                String parseAsString = httpResponse.parseAsString();
                this.f8048d = parseAsString;
                if (parseAsString.length() == 0) {
                    this.f8048d = null;
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (IllegalArgumentException e3) {
                e3.printStackTrace();
            }
            StringBuilder computeMessageBuffer = HttpResponseException.computeMessageBuffer(httpResponse);
            if (this.f8048d != null) {
                computeMessageBuffer.append(StringUtils.LINE_SEPARATOR);
                computeMessageBuffer.append(this.f8048d);
            }
            this.f8049e = computeMessageBuffer.toString();
        }

        public HttpResponseException build() {
            return new HttpResponseException(this);
        }

        public final String getContent() {
            return this.f8048d;
        }

        public HttpHeaders getHeaders() {
            return this.f8047c;
        }

        public final String getMessage() {
            return this.f8049e;
        }

        public final int getStatusCode() {
            return this.f8045a;
        }

        public final String getStatusMessage() {
            return this.f8046b;
        }

        public Builder setContent(String str) {
            this.f8048d = str;
            return this;
        }

        public Builder setHeaders(HttpHeaders httpHeaders) {
            this.f8047c = (HttpHeaders) Preconditions.checkNotNull(httpHeaders);
            return this;
        }

        public Builder setMessage(String str) {
            this.f8049e = str;
            return this;
        }

        public Builder setStatusCode(int i2) {
            Preconditions.checkArgument(i2 >= 0);
            this.f8045a = i2;
            return this;
        }

        public Builder setStatusMessage(String str) {
            this.f8046b = str;
            return this;
        }
    }

    public HttpResponseException(HttpResponse httpResponse) {
        this(new Builder(httpResponse));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpResponseException(Builder builder) {
        super(builder.f8049e);
        this.statusCode = builder.f8045a;
        this.statusMessage = builder.f8046b;
        this.headers = builder.f8047c;
        this.content = builder.f8048d;
    }

    public static StringBuilder computeMessageBuffer(HttpResponse httpResponse) {
        StringBuilder sb = new StringBuilder();
        int statusCode = httpResponse.getStatusCode();
        if (statusCode != 0) {
            sb.append(statusCode);
        }
        String statusMessage = httpResponse.getStatusMessage();
        if (statusMessage != null) {
            if (statusCode != 0) {
                sb.append(TokenParser.SP);
            }
            sb.append(statusMessage);
        }
        HttpRequest request = httpResponse.getRequest();
        if (request != null) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            String requestMethod = request.getRequestMethod();
            if (requestMethod != null) {
                sb.append(requestMethod);
                sb.append(TokenParser.SP);
            }
            sb.append(request.getUrl());
        }
        return sb;
    }

    public final String getContent() {
        return this.content;
    }

    public HttpHeaders getHeaders() {
        return this.headers;
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    public final String getStatusMessage() {
        return this.statusMessage;
    }

    public final boolean isSuccessStatusCode() {
        return HttpStatusCodes.isSuccess(this.statusCode);
    }
}
