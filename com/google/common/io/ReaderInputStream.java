package com.google.common.io;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedBytes;
import java.io.InputStream;
import java.io.Reader;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
@GwtIncompatible
/* loaded from: classes2.dex */
final class ReaderInputStream extends InputStream {
    private ByteBuffer byteBuffer;
    private CharBuffer charBuffer;
    private boolean doneFlushing;
    private boolean draining;
    private final CharsetEncoder encoder;
    private boolean endOfInput;
    private final Reader reader;
    private final byte[] singleByte;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReaderInputStream(Reader reader, Charset charset, int i2) {
        this(reader, charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE), i2);
    }

    ReaderInputStream(Reader reader, CharsetEncoder charsetEncoder, int i2) {
        this.singleByte = new byte[1];
        this.reader = (Reader) Preconditions.checkNotNull(reader);
        this.encoder = (CharsetEncoder) Preconditions.checkNotNull(charsetEncoder);
        Preconditions.checkArgument(i2 > 0, "bufferSize must be positive: %s", i2);
        charsetEncoder.reset();
        CharBuffer allocate = CharBuffer.allocate(i2);
        this.charBuffer = allocate;
        allocate.flip();
        this.byteBuffer = ByteBuffer.allocate(i2);
    }

    private static int availableCapacity(Buffer buffer) {
        return buffer.capacity() - buffer.limit();
    }

    private int drain(byte[] bArr, int i2, int i3) {
        int min = Math.min(i3, this.byteBuffer.remaining());
        this.byteBuffer.get(bArr, i2, min);
        return min;
    }

    private static CharBuffer grow(CharBuffer charBuffer) {
        CharBuffer wrap = CharBuffer.wrap(Arrays.copyOf(charBuffer.array(), charBuffer.capacity() * 2));
        wrap.position(charBuffer.position());
        wrap.limit(charBuffer.limit());
        return wrap;
    }

    private void readMoreChars() {
        if (availableCapacity(this.charBuffer) == 0) {
            if (this.charBuffer.position() > 0) {
                this.charBuffer.compact().flip();
            } else {
                this.charBuffer = grow(this.charBuffer);
            }
        }
        int limit = this.charBuffer.limit();
        int read = this.reader.read(this.charBuffer.array(), limit, availableCapacity(this.charBuffer));
        if (read == -1) {
            this.endOfInput = true;
        } else {
            this.charBuffer.limit(limit + read);
        }
    }

    private void startDraining(boolean z) {
        this.byteBuffer.flip();
        if (z && this.byteBuffer.remaining() == 0) {
            this.byteBuffer = ByteBuffer.allocate(this.byteBuffer.capacity() * 2);
        } else {
            this.draining = true;
        }
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.reader.close();
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.singleByte) == 1) {
            return UnsignedBytes.toInt(this.singleByte[0]);
        }
        return -1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
        if (r2 <= 0) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:?, code lost:
        return r2;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int read(byte[] bArr, int i2, int i3) {
        CoderResult flush;
        Preconditions.checkPositionIndexes(i2, i2 + i3, bArr.length);
        if (i3 == 0) {
            return 0;
        }
        boolean z = this.endOfInput;
        int i4 = 0;
        while (true) {
            if (this.draining) {
                i4 += drain(bArr, i2 + i4, i3 - i4);
                if (i4 == i3 || this.doneFlushing) {
                    break;
                }
                this.draining = false;
                this.byteBuffer.clear();
            }
            while (true) {
                if (this.doneFlushing) {
                    flush = CoderResult.UNDERFLOW;
                } else {
                    CharsetEncoder charsetEncoder = this.encoder;
                    flush = z ? charsetEncoder.flush(this.byteBuffer) : charsetEncoder.encode(this.charBuffer, this.byteBuffer, this.endOfInput);
                }
                if (flush.isOverflow()) {
                    startDraining(true);
                    break;
                } else if (flush.isUnderflow()) {
                    if (z) {
                        this.doneFlushing = true;
                        startDraining(false);
                        break;
                    } else if (this.endOfInput) {
                        z = true;
                    } else {
                        readMoreChars();
                    }
                } else if (flush.isError()) {
                    flush.throwException();
                    return 0;
                }
            }
        }
    }
}
