package org.apache.http.util;

import java.io.Serializable;
import java.nio.CharBuffer;
import org.apache.http.protocol.HTTP;
/* loaded from: classes3.dex */
public final class CharArrayBuffer implements CharSequence, Serializable {
    private static final long serialVersionUID = -6208952725094867135L;
    private char[] buffer;
    private int len;

    public CharArrayBuffer(int i2) {
        Args.notNegative(i2, "Buffer capacity");
        this.buffer = new char[i2];
    }

    private void expand(int i2) {
        char[] cArr = new char[Math.max(this.buffer.length << 1, i2)];
        System.arraycopy(this.buffer, 0, cArr, 0, this.len);
        this.buffer = cArr;
    }

    public void append(char c2) {
        int i2 = this.len + 1;
        if (i2 > this.buffer.length) {
            expand(i2);
        }
        this.buffer[this.len] = c2;
        this.len = i2;
    }

    public void append(Object obj) {
        append(String.valueOf(obj));
    }

    public void append(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i2 = this.len + length;
        if (i2 > this.buffer.length) {
            expand(i2);
        }
        str.getChars(0, length, this.buffer, this.len);
        this.len = i2;
    }

    public void append(ByteArrayBuffer byteArrayBuffer, int i2, int i3) {
        if (byteArrayBuffer == null) {
            return;
        }
        append(byteArrayBuffer.buffer(), i2, i3);
    }

    public void append(CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer == null) {
            return;
        }
        append(charArrayBuffer.buffer, 0, charArrayBuffer.len);
    }

    public void append(CharArrayBuffer charArrayBuffer, int i2, int i3) {
        if (charArrayBuffer == null) {
            return;
        }
        append(charArrayBuffer.buffer, i2, i3);
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
            int i5 = this.len;
            int i6 = i3 + i5;
            if (i6 > this.buffer.length) {
                expand(i6);
            }
            while (i5 < i6) {
                this.buffer[i5] = (char) (bArr[i2] & 255);
                i2++;
                i5++;
            }
            this.len = i6;
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
            int i5 = this.len + i3;
            if (i5 > this.buffer.length) {
                expand(i5);
            }
            System.arraycopy(cArr, i2, this.buffer, this.len, i3);
            this.len = i5;
        }
    }

    public char[] buffer() {
        return this.buffer;
    }

    public int capacity() {
        return this.buffer.length;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i2) {
        return this.buffer[i2];
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

    public int indexOf(int i2) {
        return indexOf(i2, 0, this.len);
    }

    public int indexOf(int i2, int i3, int i4) {
        if (i3 < 0) {
            i3 = 0;
        }
        int i5 = this.len;
        if (i4 > i5) {
            i4 = i5;
        }
        if (i3 > i4) {
            return -1;
        }
        while (i3 < i4) {
            if (this.buffer[i3] == i2) {
                return i3;
            }
            i3++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.len == 0;
    }

    public boolean isFull() {
        return this.len == this.buffer.length;
    }

    @Override // java.lang.CharSequence
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

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i2);
        } else if (i3 > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + i3 + " > length: " + this.len);
        } else if (i2 <= i3) {
            return CharBuffer.wrap(this.buffer, i2, i3);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i2 + " > endIndex: " + i3);
        }
    }

    public String substring(int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i2);
        } else if (i3 > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + i3 + " > length: " + this.len);
        } else if (i2 <= i3) {
            return new String(this.buffer, i2, i3 - i2);
        } else {
            throw new IndexOutOfBoundsException("beginIndex: " + i2 + " > endIndex: " + i3);
        }
    }

    public String substringTrimmed(int i2, int i3) {
        if (i2 < 0) {
            throw new IndexOutOfBoundsException("Negative beginIndex: " + i2);
        } else if (i3 > this.len) {
            throw new IndexOutOfBoundsException("endIndex: " + i3 + " > length: " + this.len);
        } else if (i2 > i3) {
            throw new IndexOutOfBoundsException("beginIndex: " + i2 + " > endIndex: " + i3);
        } else {
            while (i2 < i3 && HTTP.isWhitespace(this.buffer[i2])) {
                i2++;
            }
            while (i3 > i2 && HTTP.isWhitespace(this.buffer[i3 - 1])) {
                i3--;
            }
            return new String(this.buffer, i2, i3 - i2);
        }
    }

    public char[] toCharArray() {
        int i2 = this.len;
        char[] cArr = new char[i2];
        if (i2 > 0) {
            System.arraycopy(this.buffer, 0, cArr, 0, i2);
        }
        return cArr;
    }

    @Override // java.lang.CharSequence
    public String toString() {
        return new String(this.buffer, 0, this.len);
    }
}
