package com.google.crypto.tink.proto;

import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.MessageLiteOrBuilder;
import java.util.List;
@Deprecated
/* loaded from: classes2.dex */
public interface RegistryConfigOrBuilder extends MessageLiteOrBuilder {
    String getConfigName();

    ByteString getConfigNameBytes();

    KeyTypeEntry getEntry(int i2);

    int getEntryCount();

    List<KeyTypeEntry> getEntryList();
}
