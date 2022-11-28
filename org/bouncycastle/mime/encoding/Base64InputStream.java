package org.bouncycastle.mime.encoding;

import java.io.EOFException;
import java.io.InputStream;
import okio.Utf8;
/* loaded from: classes4.dex */
public class Base64InputStream extends InputStream {
    private static final byte[] decodingTable = new byte[128];

    /* renamed from: a  reason: collision with root package name */
    InputStream f14360a;

    /* renamed from: b  reason: collision with root package name */
    int[] f14361b = new int[3];

    /* renamed from: c  reason: collision with root package name */
    int f14362c = 3;

    static {
        for (int i2 = 65; i2 <= 90; i2++) {
            decodingTable[i2] = (byte) (i2 - 65);
        }
        for (int i3 = 97; i3 <= 122; i3++) {
            decodingTable[i3] = (byte) ((i3 - 97) + 26);
        }
        for (int i4 = 48; i4 <= 57; i4++) {
            decodingTable[i4] = (byte) ((i4 - 48) + 52);
        }
        byte[] bArr = decodingTable;
        bArr[43] = 62;
        bArr[47] = Utf8.REPLACEMENT_BYTE;
    }

    public Base64InputStream(InputStream inputStream) {
        this.f14360a = inputStream;
    }

    private int decode(int i2, int i3, int i4, int i5, int[] iArr) {
        if (i5 >= 0) {
            if (i4 == 61) {
                byte[] bArr = decodingTable;
                iArr[2] = (((bArr[i2] & 255) << 2) | ((bArr[i3] & 255) >> 4)) & 255;
                return 2;
            } else if (i5 == 61) {
                byte[] bArr2 = decodingTable;
                byte b2 = bArr2[i2];
                byte b3 = bArr2[i3];
                byte b4 = bArr2[i4];
                iArr[1] = ((b2 << 2) | (b3 >> 4)) & 255;
                iArr[2] = ((b3 << 4) | (b4 >> 2)) & 255;
                return 1;
            } else {
                byte[] bArr3 = decodingTable;
                byte b5 = bArr3[i2];
                byte b6 = bArr3[i3];
                byte b7 = bArr3[i4];
                byte b8 = bArr3[i5];
                iArr[0] = ((b5 << 2) | (b6 >> 4)) & 255;
                iArr[1] = ((b6 << 4) | (b7 >> 2)) & 255;
                iArr[2] = ((b7 << 6) | b8) & 255;
                return 0;
            }
        }
        throw new EOFException("unexpected end of file in armored stream.");
    }

    private int readIgnoreSpace() {
        while (true) {
            int read = this.f14360a.read();
            if (read != 9 && read != 32) {
                return read;
            }
        }
    }

    private int readIgnoreSpaceFirst() {
        while (true) {
            int read = this.f14360a.read();
            if (read != 9 && read != 10 && read != 13 && read != 32) {
                return read;
            }
        }
    }

    @Override // java.io.InputStream
    public int available() {
        return 0;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f14360a.close();
    }

    @Override // java.io.InputStream
    public int read() {
        if (this.f14362c > 2) {
            int readIgnoreSpaceFirst = readIgnoreSpaceFirst();
            if (readIgnoreSpaceFirst < 0) {
                return -1;
            }
            this.f14362c = decode(readIgnoreSpaceFirst, readIgnoreSpace(), readIgnoreSpace(), readIgnoreSpace(), this.f14361b);
        }
        int[] iArr = this.f14361b;
        int i2 = this.f14362c;
        this.f14362c = i2 + 1;
        return iArr[i2];
    }
}
