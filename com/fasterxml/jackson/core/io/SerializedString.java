package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.SerializableString;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public class SerializedString implements SerializableString, Serializable {
    private static final JsonStringEncoder JSON_ENCODER = JsonStringEncoder.getInstance();
    private static final long serialVersionUID = 1;

    /* renamed from: a  reason: collision with root package name */
    protected final String f5165a;

    /* renamed from: b  reason: collision with root package name */
    protected byte[] f5166b;

    /* renamed from: c  reason: collision with root package name */
    protected byte[] f5167c;

    /* renamed from: d  reason: collision with root package name */
    protected char[] f5168d;

    public SerializedString(String str) {
        if (str == null) {
            throw new IllegalStateException("Null String illegal for SerializedString");
        }
        this.f5165a = str;
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.readUTF();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.writeUTF(this.f5165a);
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int appendQuoted(char[] cArr, int i2) {
        char[] cArr2 = this.f5168d;
        if (cArr2 == null) {
            cArr2 = JSON_ENCODER.quoteAsString(this.f5165a);
            this.f5168d = cArr2;
        }
        int length = cArr2.length;
        if (i2 + length > cArr.length) {
            return -1;
        }
        System.arraycopy(cArr2, 0, cArr, i2, length);
        return length;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int appendQuotedUTF8(byte[] bArr, int i2) {
        byte[] bArr2 = this.f5166b;
        if (bArr2 == null) {
            bArr2 = JSON_ENCODER.quoteAsUTF8(this.f5165a);
            this.f5166b = bArr2;
        }
        int length = bArr2.length;
        if (i2 + length > bArr.length) {
            return -1;
        }
        System.arraycopy(bArr2, 0, bArr, i2, length);
        return length;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int appendUnquoted(char[] cArr, int i2) {
        String str = this.f5165a;
        int length = str.length();
        if (i2 + length > cArr.length) {
            return -1;
        }
        str.getChars(0, length, cArr, i2);
        return length;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int appendUnquotedUTF8(byte[] bArr, int i2) {
        byte[] bArr2 = this.f5167c;
        if (bArr2 == null) {
            bArr2 = JSON_ENCODER.encodeAsUTF8(this.f5165a);
            this.f5167c = bArr2;
        }
        int length = bArr2.length;
        if (i2 + length > bArr.length) {
            return -1;
        }
        System.arraycopy(bArr2, 0, bArr, i2, length);
        return length;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public final char[] asQuotedChars() {
        char[] cArr = this.f5168d;
        if (cArr == null) {
            char[] quoteAsString = JSON_ENCODER.quoteAsString(this.f5165a);
            this.f5168d = quoteAsString;
            return quoteAsString;
        }
        return cArr;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public final byte[] asQuotedUTF8() {
        byte[] bArr = this.f5166b;
        if (bArr == null) {
            byte[] quoteAsUTF8 = JSON_ENCODER.quoteAsUTF8(this.f5165a);
            this.f5166b = quoteAsUTF8;
            return quoteAsUTF8;
        }
        return bArr;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public final byte[] asUnquotedUTF8() {
        byte[] bArr = this.f5167c;
        if (bArr == null) {
            byte[] encodeAsUTF8 = JSON_ENCODER.encodeAsUTF8(this.f5165a);
            this.f5167c = encodeAsUTF8;
            return encodeAsUTF8;
        }
        return bArr;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public final int charLength() {
        return this.f5165a.length();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.f5165a.equals(((SerializedString) obj).f5165a);
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public final String getValue() {
        return this.f5165a;
    }

    public final int hashCode() {
        return this.f5165a.hashCode();
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int putQuotedUTF8(ByteBuffer byteBuffer) {
        byte[] bArr = this.f5166b;
        if (bArr == null) {
            bArr = JSON_ENCODER.quoteAsUTF8(this.f5165a);
            this.f5166b = bArr;
        }
        int length = bArr.length;
        if (length > byteBuffer.remaining()) {
            return -1;
        }
        byteBuffer.put(bArr, 0, length);
        return length;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int putUnquotedUTF8(ByteBuffer byteBuffer) {
        byte[] bArr = this.f5167c;
        if (bArr == null) {
            bArr = JSON_ENCODER.encodeAsUTF8(this.f5165a);
            this.f5167c = bArr;
        }
        int length = bArr.length;
        if (length > byteBuffer.remaining()) {
            return -1;
        }
        byteBuffer.put(bArr, 0, length);
        return length;
    }

    public final String toString() {
        return this.f5165a;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int writeQuotedUTF8(OutputStream outputStream) {
        byte[] bArr = this.f5166b;
        if (bArr == null) {
            bArr = JSON_ENCODER.quoteAsUTF8(this.f5165a);
            this.f5166b = bArr;
        }
        int length = bArr.length;
        outputStream.write(bArr, 0, length);
        return length;
    }

    @Override // com.fasterxml.jackson.core.SerializableString
    public int writeUnquotedUTF8(OutputStream outputStream) {
        byte[] bArr = this.f5167c;
        if (bArr == null) {
            bArr = JSON_ENCODER.encodeAsUTF8(this.f5165a);
            this.f5167c = bArr;
        }
        int length = bArr.length;
        outputStream.write(bArr, 0, length);
        return length;
    }
}
