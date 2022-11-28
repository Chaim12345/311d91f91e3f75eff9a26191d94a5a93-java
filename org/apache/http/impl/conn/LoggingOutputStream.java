package org.apache.http.impl.conn;

import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes3.dex */
class LoggingOutputStream extends OutputStream {
    private final OutputStream out;
    private final Wire wire;

    public LoggingOutputStream(OutputStream outputStream, Wire wire) {
        this.out = outputStream;
        this.wire = wire;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        try {
            this.out.close();
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.output("[close] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        try {
            this.out.flush();
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.output("[flush] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.OutputStream
    public void write(int i2) {
        try {
            this.wire.output(i2);
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.output("[write] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        try {
            this.wire.output(bArr);
            this.out.write(bArr);
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.output("[write] I/O error: " + e2.getMessage());
            throw e2;
        }
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i2, int i3) {
        try {
            this.wire.output(bArr, i2, i3);
            this.out.write(bArr, i2, i3);
        } catch (IOException e2) {
            Wire wire = this.wire;
            wire.output("[write] I/O error: " + e2.getMessage());
            throw e2;
        }
    }
}
