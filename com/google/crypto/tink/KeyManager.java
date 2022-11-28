package com.google.crypto.tink;

import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
/* loaded from: classes2.dex */
public interface KeyManager<P> {
    boolean doesSupport(String str);

    String getKeyType();

    P getPrimitive(ByteString byteString);

    P getPrimitive(MessageLite messageLite);

    Class<P> getPrimitiveClass();

    int getVersion();

    MessageLite newKey(ByteString byteString);

    MessageLite newKey(MessageLite messageLite);

    KeyData newKeyData(ByteString byteString);
}
