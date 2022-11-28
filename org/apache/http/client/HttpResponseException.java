package org.apache.http.client;

import org.apache.http.util.TextUtils;
/* loaded from: classes3.dex */
public class HttpResponseException extends ClientProtocolException {
    private static final long serialVersionUID = -7186627969477257933L;
    private final String reasonPhrase;
    private final int statusCode;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public HttpResponseException(int i2, String str) {
        super(String.format(r0.toString(), Integer.valueOf(i2), str));
        StringBuilder sb = new StringBuilder();
        sb.append("status code: %d");
        sb.append(TextUtils.isBlank(str) ? "" : ", reason phrase: %s");
        this.statusCode = i2;
        this.reasonPhrase = str;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
