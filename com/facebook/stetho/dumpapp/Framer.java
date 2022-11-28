package com.facebook.stetho.dumpapp;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.stetho.common.LogUtil;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Framer {
    public static final byte ENTER_FRAME_PREFIX = 33;
    public static final byte EXIT_FRAME_PREFIX = 120;
    public static final byte STDERR_FRAME_PREFIX = 50;
    public static final byte STDIN_FRAME_PREFIX = 45;
    public static final byte STDIN_REQUEST_FRAME_PREFIX = 95;
    public static final byte STDOUT_FRAME_PREFIX = 49;
    private static final String TAG = "FramingSocket";
    private final DataInputStream mInput;
    private final DataOutputStream mMultiplexedOutputStream;
    private final InputStream mStdin = new FramingInputStream();
    private final PrintStream mStdout = new PrintStream(new BufferedOutputStream(new FramingOutputStream(STDOUT_FRAME_PREFIX)));
    private final PrintStream mStderr = new PrintStream(new FramingOutputStream(STDERR_FRAME_PREFIX));

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ClosedHelper {
        private volatile boolean mClosed;

        private ClosedHelper() {
        }

        public void close() {
            this.mClosed = true;
        }

        public void throwIfClosed() {
            if (this.mClosed) {
                throw new IOException("Stream is closed");
            }
        }
    }

    /* loaded from: classes.dex */
    private class FramingInputStream extends InputStream {
        private final ClosedHelper mClosedHelper;

        private FramingInputStream() {
            this.mClosedHelper = new ClosedHelper();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mClosedHelper.close();
        }

        @Override // java.io.InputStream
        public int read() {
            byte[] bArr = new byte[1];
            if (read(bArr) == 0) {
                return -1;
            }
            return bArr[0];
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) {
            return read(bArr, 0, bArr.length);
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i2, int i3) {
            int readInt;
            this.mClosedHelper.throwIfClosed();
            synchronized (Framer.this) {
                Framer.this.writeIntFrame(Framer.STDIN_REQUEST_FRAME_PREFIX, i3);
                byte readFrameType = Framer.this.readFrameType();
                if (readFrameType != 45) {
                    throw new UnexpectedFrameException(Framer.STDIN_FRAME_PREFIX, readFrameType);
                }
                readInt = Framer.this.readInt();
                if (readInt > 0) {
                    if (readInt > i3) {
                        throw new DumpappFramingException("Expected at most " + i3 + " bytes, got: " + readInt);
                    }
                    Framer.this.mInput.readFully(bArr, i2, readInt);
                }
            }
            return readInt;
        }

        @Override // java.io.InputStream
        public long skip(long j2) {
            long j3;
            byte[] bArr = new byte[(int) Math.min(j2, (long) PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH)];
            synchronized (Framer.this) {
                j3 = 0;
                while (j3 < j2) {
                    int read = read(bArr);
                    if (read < 0) {
                        break;
                    }
                    j3 += read;
                }
            }
            return j3;
        }
    }

    /* loaded from: classes.dex */
    private class FramingOutputStream extends OutputStream {
        private final ClosedHelper mClosedHelper = new ClosedHelper();
        private final byte mPrefix;

        public FramingOutputStream(byte b2) {
            this.mPrefix = b2;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.mClosedHelper.close();
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            write(new byte[]{(byte) i2}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            write(bArr, 0, bArr.length);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            this.mClosedHelper.throwIfClosed();
            if (i3 > 0) {
                try {
                    synchronized (Framer.this) {
                        Framer.this.writeIntFrame(this.mPrefix, i3);
                        Framer.this.writeBlob(bArr, i2, i3);
                        Framer.this.mMultiplexedOutputStream.flush();
                    }
                } catch (IOException e2) {
                    throw new DumpappOutputBrokenException(e2);
                }
            }
        }
    }

    public Framer(InputStream inputStream, OutputStream outputStream) {
        this.mInput = new DataInputStream(inputStream);
        this.mMultiplexedOutputStream = new DataOutputStream(outputStream);
    }

    private static <T extends Throwable> T handleSuppression(@Nullable T t2, T t3) {
        if (t2 == null) {
            return t3;
        }
        LogUtil.i(TAG, t3, "Suppressed while handling " + t2);
        return t2;
    }

    public PrintStream getStderr() {
        return this.mStderr;
    }

    public InputStream getStdin() {
        return this.mStdin;
    }

    public PrintStream getStdout() {
        return this.mStdout;
    }

    public byte readFrameType() {
        return this.mInput.readByte();
    }

    public int readInt() {
        return this.mInput.readInt();
    }

    public String readString() {
        byte[] bArr = new byte[this.mInput.readUnsignedShort()];
        this.mInput.readFully(bArr);
        return new String(bArr, Charset.forName("UTF-8"));
    }

    public void writeBlob(byte[] bArr, int i2, int i3) {
        this.mMultiplexedOutputStream.write(bArr, i2, i3);
    }

    public void writeExitCode(int i2) {
        this.mStdout.flush();
        this.mStderr.flush();
        writeIntFrame(EXIT_FRAME_PREFIX, i2);
    }

    public void writeIntFrame(byte b2, int i2) {
        this.mMultiplexedOutputStream.write(b2);
        this.mMultiplexedOutputStream.writeInt(i2);
    }
}
