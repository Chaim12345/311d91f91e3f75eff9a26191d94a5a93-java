package com.google.crypto.tink;
@Deprecated
/* loaded from: classes2.dex */
public interface Catalogue<P> {
    KeyManager<P> getKeyManager(String str, String str2, int i2);

    PrimitiveWrapper<?, P> getPrimitiveWrapper();
}
