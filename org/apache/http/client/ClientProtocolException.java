package org.apache.http.client;

import java.io.IOException;
/* loaded from: classes3.dex */
public class ClientProtocolException extends IOException {
    private static final long serialVersionUID = -5596590843227115865L;

    public ClientProtocolException() {
    }

    public ClientProtocolException(String str) {
        super(str);
    }

    public ClientProtocolException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    public ClientProtocolException(Throwable th) {
        initCause(th);
    }
}
