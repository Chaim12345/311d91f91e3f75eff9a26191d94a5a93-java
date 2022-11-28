package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
/* loaded from: classes2.dex */
public interface KeysetReader {
    Keyset read();

    EncryptedKeyset readEncrypted();
}
