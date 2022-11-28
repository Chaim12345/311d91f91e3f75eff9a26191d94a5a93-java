package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes3.dex */
class LoggingInputStream extends InputStream {
    private final InputStream in;
    private final Wire wire;

    public LoggingInputStream(InputStream inputStream, Wire wire) {
        this.in = inputStream;
        this.wire = wire;
    }

    @Override // java.io.InputStream
    public int available() {
        try {
            return this.in.available();
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.input("[available] I/O error : " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.in.close();
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.input("[close] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        super.mark(i2);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read() {
        try {
            int read = this.in.read();
            if (read == -1) {
                this.wire.input("end of stream");
            } else {
                this.wire.input(read);
            }
            return read;
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.input("[read] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        try {
            int read = this.in.read(bArr);
            if (read == -1) {
                this.wire.input("end of stream");
            } else if (read > 0) {
                this.wire.input(bArr, 0, read);
            }
            return read;
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.input("[read] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        try {
            int read = this.in.read(bArr, i2, i3);
            if (read == -1) {
                this.wire.input("end of stream");
            } else if (read > 0) {
                this.wire.input(bArr, i2, read);
            }
            return read;
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.input("[read] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.InputStream
    public void reset() {
        super.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        try {
            return super.skip(j2);
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.input("[skip] I/O error: " + e2.getMessage());
            throw e2;
        }
    }
}
