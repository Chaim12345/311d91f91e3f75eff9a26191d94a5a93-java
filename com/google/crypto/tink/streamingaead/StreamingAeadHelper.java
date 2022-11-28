package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
final class StreamingAeadHelper implements StreamingAead {

    /* renamed from: a  reason: collision with root package name */
    PrimitiveSet f9828a;

    public StreamingAeadHelper(PrimitiveSet<StreamingAead> primitiveSet) {
        if (primitiveSet.getPrimary() == null) {
            throw new GeneralSecurityException("Missing primary primitive.");
        }
        this.f9828a = primitiveSet;
    }

    @Override // com.google.crypto.tink.StreamingAead
    public ReadableByteChannel newDecryptingChannel(ReadableByteChannel readableByteChannel, byte[] bArr) {
        return new ReadableByteChannelDecrypter(this.f9828a, readableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public InputStream newDecryptingStream(InputStream inputStream, byte[] bArr) {
        return new InputStreamDecrypter(this.f9828a, inputStream, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public WritableByteChannel newEncryptingChannel(WritableByteChannel writableByteChannel, byte[] bArr) {
        return ((StreamingAead) this.f9828a.getPrimary().getPrimitive()).newEncryptingChannel(writableByteChannel, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public OutputStream newEncryptingStream(OutputStream outputStream, byte[] bArr) {
        return ((StreamingAead) this.f9828a.getPrimary().getPrimitive()).newEncryptingStream(outputStream, bArr);
    }

    @Override // com.google.crypto.tink.StreamingAead
    public SeekableByteChannel newSeekableDecryptingChannel(SeekableByteChannel seekableByteChannel, byte[] bArr) {
        return new SeekableByteChannelDecrypter(this.f9828a, seekableByteChannel, bArr);
    }
}
