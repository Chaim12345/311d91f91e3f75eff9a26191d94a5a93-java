package com.google.crypto.tink;
/* loaded from: classes2.dex */
public interface KmsClient {
    boolean doesSupport(String str);

    Aead getAead(String str);

    KmsClient withCredentials(String str);

    KmsClient withDefaultCredentials();
}
