package com.google.maps.internal;

import com.fasterxml.jackson.core.JsonPointer;
import java.nio.charset.StandardCharsets;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import okio.ByteString;
import org.apache.commons.codec.language.Soundex;
/* loaded from: classes2.dex */
public class UrlSigner {
    private static final String ALGORITHM_HMAC_SHA1 = "HmacSHA1";
    private final Mac mac;

    public UrlSigner(String str) {
        ByteString decodeBase64 = ByteString.decodeBase64(str.replace(Soundex.SILENT_MARKER, '+').replace('_', JsonPointer.SEPARATOR));
        if (decodeBase64 == null) {
            throw new IllegalArgumentException("Private key is invalid.");
        }
        Mac mac = Mac.getInstance(ALGORITHM_HMAC_SHA1);
        this.mac = mac;
        mac.init(new SecretKeySpec(decodeBase64.toByteArray(), ALGORITHM_HMAC_SHA1));
    }

    private Mac getMac() {
        try {
            return (Mac) this.mac.clone();
        } catch (CloneNotSupportedException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public String getSignature(String str) {
        return ByteString.of(getMac().doFinal(str.getBytes(StandardCharsets.UTF_8))).base64().replace('+', Soundex.SILENT_MARKER).replace(JsonPointer.SEPARATOR, '_');
    }
}
