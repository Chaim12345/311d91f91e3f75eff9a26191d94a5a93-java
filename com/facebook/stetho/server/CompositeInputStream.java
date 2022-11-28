package com.facebook.stetho.server;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
/* loaded from: classes.dex */
public class CompositeInputStream extends InputStream {
    private int mCurrentIndex;
    private final InputStream[] mStreams;

    public CompositeInputStream(InputStream[] inputStreamArr) {
        if (inputStreamArr == null || inputStreamArr.length < 2) {
            throw new IllegalArgumentException("Streams must be non-null and have more than 1 entry");
        }
        this.mStreams = inputStreamArr;
        this.mCurrentIndex = 0;
    }

    private void closeAll(int i2) {
        IOException iOException = null;
        int i3 = 0;
        while (true) {
            InputStream[] inputStreamArr = this.mStreams;
            if (i3 >= inputStreamArr.length) {
                return;
            }
            try {
                inputStreamArr[i3].close();
            } catch (IOException e2) {
                e = e2;
                if (i3 != i2 && iOException != null) {
                    e = iOException;
                }
                if (iOException != null && iOException != e) {
                    LogUtil.w(iOException, "Suppressing exception");
                }
                iOException = e;
            }
            i3++;
        }
    }

    private boolean tryMoveToNextStream() {
        int i2 = this.mCurrentIndex;
        if (i2 + 1 < this.mStreams.length) {
            this.mCurrentIndex = i2 + 1;
            return true;
        }
        return false;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.mStreams[this.mCurrentIndex].available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        closeAll(this.mCurrentIndex);
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.InputStream
    public int read() {
        int read;
        do {
            read = this.mStreams[this.mCurrentIndex].read();
            if (read != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return read;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read;
        do {
            read = this.mStreams[this.mCurrentIndex].read(bArr, i2, i3);
            if (read != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return read;
    }

    @Override // java.io.InputStream
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public long skip(long j2) {
        int read = read(new byte[(int) j2]);
        if (read >= 0) {
            return read;
        }
        return -1L;
    }
}
