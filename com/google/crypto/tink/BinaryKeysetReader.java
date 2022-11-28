package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.shaded.protobuf.ExtensionRegistryLite;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/* loaded from: classes2.dex */
public final class BinaryKeysetReader implements KeysetReader {
    private final boolean closeStreamAfterReading;
    private final InputStream inputStream;

    private BinaryKeysetReader(InputStream inputStream, boolean z) {
        this.inputStream = inputStream;
        this.closeStreamAfterReading = z;
    }

    public static KeysetReader withBytes(byte[] bArr) {
        return new BinaryKeysetReader(new ByteArrayInputStream(bArr), true);
    }

    public static KeysetReader withFile(File file) {
        return new BinaryKeysetReader(new FileInputStream(file), true);
    }

    public static KeysetReader withInputStream(InputStream inputStream) {
        return new BinaryKeysetReader(inputStream, false);
    }

    @Override // com.google.crypto.tink.KeysetReader
    public Keyset read() {
        try {
            return Keyset.parseFrom(this.inputStream, ExtensionRegistryLite.getEmptyRegistry());
        } finally {
            if (this.closeStreamAfterReading) {
                this.inputStream.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetReader
    public EncryptedKeyset readEncrypted() {
        try {
            return EncryptedKeyset.parseFrom(this.inputStream, ExtensionRegistryLite.getEmptyRegistry());
        } finally {
            if (this.closeStreamAfterReading) {
                this.inputStream.close();
            }
        }
    }
}
