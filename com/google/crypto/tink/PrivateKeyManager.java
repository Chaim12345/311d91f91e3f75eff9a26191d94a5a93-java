package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
/* loaded from: classes2.dex */
public interface PrivateKeyManager<P> extends KeyManager<P> {
    KeyData getPublicKeyData(ByteString byteString);
}
