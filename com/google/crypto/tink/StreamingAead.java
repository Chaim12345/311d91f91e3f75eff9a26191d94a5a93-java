package com.google.crypto.tink;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
/* loaded from: classes2.dex */
public interface StreamingAead {
    ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel, byte[] bArr);

    InputStream newDecryptingStream(InputStream inputStream, byte[] bArr);

    WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel, byte[] bArr);

    OutputStream newEncryptingStream(OutputStream outputStream, byte[] bArr);

    SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel, byte[] bArr);
}
