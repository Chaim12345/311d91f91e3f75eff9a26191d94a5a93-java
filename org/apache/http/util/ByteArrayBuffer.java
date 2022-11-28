package org.apache.http.util;

import java.io.Serializable;
import okio.Utf8;
/* loaded from: classes3.dex */
public final class ByteArrayBuffer implements Serializable {
    private static final long serialVersionUID = 4359112959524048036L;
    private byte[] buffer;
    private int len;

    public ByteArrayBuffer(int i2) {
        Args.notNegative(i2, "Buffer capacity");
        this.buffer = new byte[i2];
    }

    private void expand(int i2) {
        byte[] bArr = new byte[Math.max(this.buffer.length << 1, i2)];
        System.arraycopy(this.buffer, 0, bArr, 0, this.len);
        this.buffer = bArr;
    }

    public void append(int i2) {
        int i3 = this.len + 1;
        if (i3 > this.buffer.length) {
            expand(i3);
        }
        this.buffer[this.len] = (byte) i2;
        this.len = i3;
    }

    public void append(CharArrayBuffer charArrayBuffer, int i2, int i3) {
        if (charArrayBuffer == null) {
            return;
        }
        append(charArrayBuffer.buffer(), i2, i3);
    }

    public void append(byte[] bArr, int i2, int i3) {
        int i4;
        if (bArr == null) {
            return;
        }
        if (i2 < 0 || i2 > bArr.length || i3 < 0 || (i4 = i2 + i3) < 0 || i4 > bArr.length) {
            throw new IndexOutOfBoundsException("off: " + i2 + " len: " + i3 + " b.length: " + bArr.length);
        } else if (i3 == 0) {
        } else {
            int i5 = this.len + i3;
            if (i5 > this.buffer.length) {
                expand(i5);
            }
            System.arraycopy(bArr, i2, this.buffer, this.len, i3);
            this.len = i5;
        }
    }

    public void append(char[] cArr, int i2, int i3) {
        int i4;
        if (cArr == null) {
            return;
        }
        if (i2 < 0 || i2 > cArr.length || i3 < 0 || (i4 = i2 + i3) < 0 || i4 > cArr.length) {
            throw new IndexOutOfBoundsException("off: " + i2 + " len: " + i3 + " b.length: " + cArr.length);
        } else if (i3 == 0) {
        } else {
            int i5 = this.len;
            int i6 = i3 + i5;
            if (i6 > this.buffer.length) {
                expand(i6);
            }
            while (i5 < i6) {
                char c2 = cArr[i2];
                if ((c2 < ' ' || c2 > '~') && ((c2 < 160 || c2 > 255) && c2 != '\t')) {
                    this.buffer[i5] = Utf8.REPLACEMENT_BYTE;
                } else {
                    this.buffer[i5] = (byte) c2;
                }
                i2++;
                i5++;
            }
            this.len = i6;
        }
    }

    public byte[] buffer() {
        return this.buffer;
    }

    public int byteAt(int i2) {
        return this.buffer[i2];
    }

    public int capacity() {
        return this.buffer.length;
    }

    public void clear() {
        this.len = 0;
    }

    public void ensureCapacity(int i2) {
        if (i2 <= 0) {
            return;
        }
        int length = this.buffer.length;
        int i3 = this.len;
        if (i2 > length - i3) {
            expand(i3 + i2);
        }
    }

    public int indexOf(byte b2) {
        return indexOf(b2, 0, this.len);
    }

    public int indexOf(byte b2, int i2, int i3) {
        if (i2 < 0) {
            i2 = 0;
        }
        int i4 = this.len;
        if (i3 > i4) {
            i3 = i4;
        }
        if (i2 > i3) {
            return -1;
        }
        while (i2 < i3) {
            if (this.buffer[i2] == b2) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public boolean isFull() {
        return this.len == this.buffer.length;
    }

    public int length() {
        return this.len;
    }

    public void setLength(int i2) {
        if (i2 >= 0 && i2 <= this.buffer.length) {
            this.len = i2;
            return;
        }
        throw new IndexOutOfBoundsException("len: " + i2 + " < 0 or > buffer len: " + this.buffer.length);
    }

    public byte[] toByteArray() {
        int i2 = this.len;
        byte[] bArr = new byte[i2];
        if (i2 > 0) {
            System.arraycopy(this.buffer, 0, bArr, 0, i2);
        }
        return bArr;
    }
}
