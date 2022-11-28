package com.facebook.stetho.server.http;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
/* loaded from: classes.dex */
public abstract class LightHttpBody {
    public static LightHttpBody create(String str, String str2) {
        try {
            return create(str.getBytes("UTF-8"), str2);
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static LightHttpBody create(final byte[] bArr, final String str) {
        return new LightHttpBody() { // from class: com.facebook.stetho.server.http.LightHttpBody.1
            @Override // com.facebook.stetho.server.http.LightHttpBody
            public int contentLength() {
                return bArr.length;
            }

            @Override // com.facebook.stetho.server.http.LightHttpBody
            public String contentType() {
                return str;
            }

            @Override // com.facebook.stetho.server.http.LightHttpBody
            public void writeTo(OutputStream outputStream) {
                outputStream.write(bArr);
            }
        };
    }

    public abstract int contentLength();

    public abstract String contentType();

    public abstract void writeTo(OutputStream outputStream);
}
