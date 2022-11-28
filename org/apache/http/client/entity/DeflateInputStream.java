package org.apache.http.client.entity;

import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipException;
/* loaded from: classes3.dex */
public class DeflateInputStream extends InputStream {
    private final InputStream sourceStream;

    /* loaded from: classes3.dex */
    static class DeflateStream extends InflaterInputStream {
        private boolean closed;

        public DeflateStream(InputStream inputStream, Inflater inflater) {
            super(inputStream, inflater);
            this.closed = false;
        }

        @Override // java.util.zip.InflaterInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.closed) {
                return;
            }
            this.closed = true;
            ((InflaterInputStream) this).inf.end();
            super.close();
        }
    }

    public DeflateInputStream(InputStream inputStream) {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 2);
        int read = pushbackInputStream.read();
        int read2 = pushbackInputStream.read();
        if (read == -1 || read2 == -1) {
            throw new ZipException("Unexpected end of stream");
        }
        pushbackInputStream.unread(read2);
        pushbackInputStream.unread(read);
        boolean z = true;
        int i2 = read & 255;
        int i3 = (i2 >> 4) & 15;
        int i4 = read2 & 255;
        if ((i2 & 15) == 8 && i3 <= 7 && ((i2 << 8) | i4) % 31 == 0) {
            z = false;
        }
        this.sourceStream = new DeflateStream(pushbackInputStream, new Inflater(z));
    }

    @Override // java.io.InputStream
    public int available() {
        return this.sourceStream.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.sourceStream.close();
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        this.sourceStream.mark(i2);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.sourceStream.markSupported();
    }

    @Override // java.io.InputStream
    public int read() {
        return this.sourceStream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return this.sourceStream.read(bArr);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        return this.sourceStream.read(bArr, i2, i3);
    }

    @Override // java.io.InputStream
    public void reset() {
        this.sourceStream.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        return this.sourceStream.skip(j2);
    }
}
