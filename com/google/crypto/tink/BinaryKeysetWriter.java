package com.google.crypto.tink;

import com.google.crypto.tink.proto.EncryptedKeyset;
import com.google.crypto.tink.proto.Keyset;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
/* loaded from: classes2.dex */
public final class BinaryKeysetWriter implements KeysetWriter {
    private final boolean closeStreamAfterReading;
    private final OutputStream outputStream;

    private BinaryKeysetWriter(OutputStream outputStream, boolean z) {
        this.outputStream = outputStream;
        this.closeStreamAfterReading = z;
    }

    public static KeysetWriter withFile(File file) {
        return new BinaryKeysetWriter(new FileOutputStream(file), true);
    }

    public static KeysetWriter withOutputStream(OutputStream outputStream) {
        return new BinaryKeysetWriter(outputStream, false);
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(EncryptedKeyset encryptedKeyset) {
        try {
            encryptedKeyset.writeTo(this.outputStream);
        } finally {
            if (this.closeStreamAfterReading) {
                this.outputStream.close();
            }
        }
    }

    @Override // com.google.crypto.tink.KeysetWriter
    public void write(Keyset keyset) {
        try {
            keyset.writeTo(this.outputStream);
        } finally {
            if (this.closeStreamAfterReading) {
                this.outputStream.close();
            }
        }
    }
}
