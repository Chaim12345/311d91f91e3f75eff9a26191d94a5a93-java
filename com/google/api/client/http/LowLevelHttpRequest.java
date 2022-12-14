package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
/* loaded from: classes2.dex */
public abstract class LowLevelHttpRequest {
    private String contentEncoding;
    private long contentLength = -1;
    private String contentType;
    private StreamingContent streamingContent;

    public abstract void addHeader(String str, String str2);

    public abstract LowLevelHttpResponse execute();

    public final String getContentEncoding() {
        return this.contentEncoding;
    }

    public final long getContentLength() {
        return this.contentLength;
    }

    public final String getContentType() {
        return this.contentType;
    }

    public final StreamingContent getStreamingContent() {
        return this.streamingContent;
    }

    public final void setContentEncoding(String str) {
        this.contentEncoding = str;
    }

    public final void setContentLength(long j2) {
        this.contentLength = j2;
    }

    public final void setContentType(String str) {
        this.contentType = str;
    }

    public final void setStreamingContent(StreamingContent streamingContent) {
        this.streamingContent = streamingContent;
    }

    public void setTimeout(int i2, int i3) {
    }

    public void setWriteTimeout(int i2) {
    }
}
