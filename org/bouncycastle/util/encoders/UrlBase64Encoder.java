package org.bouncycastle.util.encoders;

import com.facebook.stetho.dumpapp.Framer;
/* loaded from: classes4.dex */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        byte[] bArr = this.f15102a;
        bArr[bArr.length - 2] = Framer.STDIN_FRAME_PREFIX;
        bArr[bArr.length - 1] = Framer.STDIN_REQUEST_FRAME_PREFIX;
        this.f15103b = (byte) 46;
        a();
    }
}
