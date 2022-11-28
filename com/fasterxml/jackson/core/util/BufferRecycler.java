package com.fasterxml.jackson.core.util;

import java.util.concurrent.atomic.AtomicReferenceArray;
/* loaded from: classes.dex */
public class BufferRecycler {
    public static final int BYTE_BASE64_CODEC_BUFFER = 3;
    public static final int BYTE_READ_IO_BUFFER = 0;
    public static final int BYTE_WRITE_CONCAT_BUFFER = 2;
    public static final int BYTE_WRITE_ENCODING_BUFFER = 1;
    public static final int CHAR_CONCAT_BUFFER = 1;
    public static final int CHAR_NAME_COPY_BUFFER = 3;
    public static final int CHAR_TEXT_BUFFER = 2;
    public static final int CHAR_TOKEN_BUFFER = 0;

    /* renamed from: a  reason: collision with root package name */
    protected final AtomicReferenceArray f5225a;

    /* renamed from: b  reason: collision with root package name */
    protected final AtomicReferenceArray f5226b;
    private static final int[] BYTE_BUFFER_LENGTHS = {8000, 8000, 2000, 2000};
    private static final int[] CHAR_BUFFER_LENGTHS = {4000, 4000, 200, 200};

    public BufferRecycler() {
        this(4, 4);
    }

    protected BufferRecycler(int i2, int i3) {
        this.f5225a = new AtomicReferenceArray(i2);
        this.f5226b = new AtomicReferenceArray(i3);
    }

    protected byte[] a(int i2) {
        return new byte[i2];
    }

    public final byte[] allocByteBuffer(int i2) {
        return allocByteBuffer(i2, 0);
    }

    public byte[] allocByteBuffer(int i2, int i3) {
        int b2 = b(i2);
        if (i3 < b2) {
            i3 = b2;
        }
        byte[] bArr = (byte[]) this.f5225a.getAndSet(i2, null);
        return (bArr == null || bArr.length < i3) ? a(i3) : bArr;
    }

    public final char[] allocCharBuffer(int i2) {
        return allocCharBuffer(i2, 0);
    }

    public char[] allocCharBuffer(int i2, int i3) {
        int d2 = d(i2);
        if (i3 < d2) {
            i3 = d2;
        }
        char[] cArr = (char[]) this.f5226b.getAndSet(i2, null);
        return (cArr == null || cArr.length < i3) ? c(i3) : cArr;
    }

    protected int b(int i2) {
        return BYTE_BUFFER_LENGTHS[i2];
    }

    protected char[] c(int i2) {
        return new char[i2];
    }

    protected int d(int i2) {
        return CHAR_BUFFER_LENGTHS[i2];
    }

    public void releaseByteBuffer(int i2, byte[] bArr) {
        this.f5225a.set(i2, bArr);
    }

    public void releaseCharBuffer(int i2, char[] cArr) {
        this.f5226b.set(i2, cArr);
    }
}
