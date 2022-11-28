package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.TextBuffer;
/* loaded from: classes.dex */
public class IOContext {

    /* renamed from: a  reason: collision with root package name */
    protected final Object f5150a;

    /* renamed from: b  reason: collision with root package name */
    protected JsonEncoding f5151b;

    /* renamed from: c  reason: collision with root package name */
    protected final boolean f5152c;

    /* renamed from: d  reason: collision with root package name */
    protected final BufferRecycler f5153d;

    /* renamed from: e  reason: collision with root package name */
    protected byte[] f5154e;

    /* renamed from: f  reason: collision with root package name */
    protected byte[] f5155f;

    /* renamed from: g  reason: collision with root package name */
    protected byte[] f5156g;

    /* renamed from: h  reason: collision with root package name */
    protected char[] f5157h;

    /* renamed from: i  reason: collision with root package name */
    protected char[] f5158i;

    /* renamed from: j  reason: collision with root package name */
    protected char[] f5159j;

    public IOContext(BufferRecycler bufferRecycler, Object obj, boolean z) {
        this.f5153d = bufferRecycler;
        this.f5150a = obj;
        this.f5152c = z;
    }

    private IllegalArgumentException wrongBuf() {
        return new IllegalArgumentException("Trying to release buffer smaller than original");
    }

    protected final void a(Object obj) {
        if (obj != null) {
            throw new IllegalStateException("Trying to call same allocXxx() method second time");
        }
    }

    public byte[] allocBase64Buffer() {
        a(this.f5156g);
        byte[] allocByteBuffer = this.f5153d.allocByteBuffer(3);
        this.f5156g = allocByteBuffer;
        return allocByteBuffer;
    }

    public byte[] allocBase64Buffer(int i2) {
        a(this.f5156g);
        byte[] allocByteBuffer = this.f5153d.allocByteBuffer(3, i2);
        this.f5156g = allocByteBuffer;
        return allocByteBuffer;
    }

    public char[] allocConcatBuffer() {
        a(this.f5158i);
        char[] allocCharBuffer = this.f5153d.allocCharBuffer(1);
        this.f5158i = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocNameCopyBuffer(int i2) {
        a(this.f5159j);
        char[] allocCharBuffer = this.f5153d.allocCharBuffer(3, i2);
        this.f5159j = allocCharBuffer;
        return allocCharBuffer;
    }

    public byte[] allocReadIOBuffer() {
        a(this.f5154e);
        byte[] allocByteBuffer = this.f5153d.allocByteBuffer(0);
        this.f5154e = allocByteBuffer;
        return allocByteBuffer;
    }

    public byte[] allocReadIOBuffer(int i2) {
        a(this.f5154e);
        byte[] allocByteBuffer = this.f5153d.allocByteBuffer(0, i2);
        this.f5154e = allocByteBuffer;
        return allocByteBuffer;
    }

    public char[] allocTokenBuffer() {
        a(this.f5157h);
        char[] allocCharBuffer = this.f5153d.allocCharBuffer(0);
        this.f5157h = allocCharBuffer;
        return allocCharBuffer;
    }

    public char[] allocTokenBuffer(int i2) {
        a(this.f5157h);
        char[] allocCharBuffer = this.f5153d.allocCharBuffer(0, i2);
        this.f5157h = allocCharBuffer;
        return allocCharBuffer;
    }

    public byte[] allocWriteEncodingBuffer() {
        a(this.f5155f);
        byte[] allocByteBuffer = this.f5153d.allocByteBuffer(1);
        this.f5155f = allocByteBuffer;
        return allocByteBuffer;
    }

    public byte[] allocWriteEncodingBuffer(int i2) {
        a(this.f5155f);
        byte[] allocByteBuffer = this.f5153d.allocByteBuffer(1, i2);
        this.f5155f = allocByteBuffer;
        return allocByteBuffer;
    }

    protected final void b(byte[] bArr, byte[] bArr2) {
        if (bArr != bArr2 && bArr.length < bArr2.length) {
            throw wrongBuf();
        }
    }

    protected final void c(char[] cArr, char[] cArr2) {
        if (cArr != cArr2 && cArr.length < cArr2.length) {
            throw wrongBuf();
        }
    }

    public TextBuffer constructTextBuffer() {
        return new TextBuffer(this.f5153d);
    }

    public JsonEncoding getEncoding() {
        return this.f5151b;
    }

    public Object getSourceReference() {
        return this.f5150a;
    }

    public boolean isResourceManaged() {
        return this.f5152c;
    }

    public void releaseBase64Buffer(byte[] bArr) {
        if (bArr != null) {
            b(bArr, this.f5156g);
            this.f5156g = null;
            this.f5153d.releaseByteBuffer(3, bArr);
        }
    }

    public void releaseConcatBuffer(char[] cArr) {
        if (cArr != null) {
            c(cArr, this.f5158i);
            this.f5158i = null;
            this.f5153d.releaseCharBuffer(1, cArr);
        }
    }

    public void releaseNameCopyBuffer(char[] cArr) {
        if (cArr != null) {
            c(cArr, this.f5159j);
            this.f5159j = null;
            this.f5153d.releaseCharBuffer(3, cArr);
        }
    }

    public void releaseReadIOBuffer(byte[] bArr) {
        if (bArr != null) {
            b(bArr, this.f5154e);
            this.f5154e = null;
            this.f5153d.releaseByteBuffer(0, bArr);
        }
    }

    public void releaseTokenBuffer(char[] cArr) {
        if (cArr != null) {
            c(cArr, this.f5157h);
            this.f5157h = null;
            this.f5153d.releaseCharBuffer(0, cArr);
        }
    }

    public void releaseWriteEncodingBuffer(byte[] bArr) {
        if (bArr != null) {
            b(bArr, this.f5155f);
            this.f5155f = null;
            this.f5153d.releaseByteBuffer(1, bArr);
        }
    }

    public void setEncoding(JsonEncoding jsonEncoding) {
        this.f5151b = jsonEncoding;
    }

    public IOContext withEncoding(JsonEncoding jsonEncoding) {
        this.f5151b = jsonEncoding;
        return this;
    }
}
