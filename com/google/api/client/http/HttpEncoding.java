package com.google.api.client.http;

import com.google.api.client.util.StreamingContent;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public interface HttpEncoding {
    void encode(StreamingContent streamingContent, OutputStream outputStream);

    String getName();
}
