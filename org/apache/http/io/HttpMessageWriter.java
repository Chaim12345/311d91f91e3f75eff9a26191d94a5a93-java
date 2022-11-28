package org.apache.http.io;

import org.apache.http.HttpMessage;
/* loaded from: classes3.dex */
public interface HttpMessageWriter<T extends HttpMessage> {
    void write(T t2);
}
