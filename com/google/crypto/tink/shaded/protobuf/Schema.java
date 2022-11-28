package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.ArrayDecoders;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public interface Schema<T> {
    boolean equals(T t2, T t3);

    int getSerializedSize(T t2);

    int hashCode(T t2);

    boolean isInitialized(T t2);

    void makeImmutable(T t2);

    void mergeFrom(T t2, Reader reader, ExtensionRegistryLite extensionRegistryLite);

    void mergeFrom(T t2, T t3);

    void mergeFrom(T t2, byte[] bArr, int i2, int i3, ArrayDecoders.Registers registers);

    T newInstance();

    void writeTo(T t2, Writer writer);
}
