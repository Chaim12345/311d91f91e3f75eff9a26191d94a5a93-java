package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class TextBuffer {

    /* renamed from: a  reason: collision with root package name */
    static final char[] f5247a = new char[0];
    private final BufferRecycler _allocator;
    private char[] _currentSegment;
    private int _currentSize;
    private boolean _hasSegments;
    private char[] _inputBuffer;
    private int _inputLen;
    private int _inputStart;
    private char[] _resultArray;
    private String _resultString;
    private int _segmentSize;
    private ArrayList<char[]> _segments;

    public TextBuffer(BufferRecycler bufferRecycler) {
        this._allocator = bufferRecycler;
    }

    protected TextBuffer(BufferRecycler bufferRecycler, char[] cArr) {
        this._allocator = bufferRecycler;
        this._currentSegment = cArr;
        this._currentSize = cArr.length;
        this._inputStart = -1;
    }

    private char[] buf(int i2) {
        BufferRecycler bufferRecycler = this._allocator;
        return bufferRecycler != null ? bufferRecycler.allocCharBuffer(2, i2) : new char[Math.max(i2, 500)];
    }

    private char[] carr(int i2) {
        return new char[i2];
    }

    private void clearSegments() {
        this._hasSegments = false;
        this._segments.clear();
        this._segmentSize = 0;
        this._currentSize = 0;
    }

    private void expand(int i2) {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        char[] cArr = this._currentSegment;
        this._hasSegments = true;
        this._segments.add(cArr);
        this._segmentSize += cArr.length;
        this._currentSize = 0;
        int length = cArr.length;
        int i3 = length + (length >> 1);
        if (i3 < 500) {
            i3 = 500;
        } else if (i3 > 65536) {
            i3 = 65536;
        }
        this._currentSegment = carr(i3);
    }

    public static TextBuffer fromInitial(char[] cArr) {
        return new TextBuffer(null, cArr);
    }

    private char[] resultArray() {
        int i2;
        String str = this._resultString;
        if (str != null) {
            return str.toCharArray();
        }
        int i3 = this._inputStart;
        if (i3 >= 0) {
            int i4 = this._inputLen;
            return i4 < 1 ? f5247a : i3 == 0 ? Arrays.copyOf(this._inputBuffer, i4) : Arrays.copyOfRange(this._inputBuffer, i3, i4 + i3);
        }
        int size = size();
        if (size < 1) {
            return f5247a;
        }
        char[] carr = carr(size);
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size2 = arrayList.size();
            i2 = 0;
            for (int i5 = 0; i5 < size2; i5++) {
                char[] cArr = this._segments.get(i5);
                int length = cArr.length;
                System.arraycopy(cArr, 0, carr, i2, length);
                i2 += length;
            }
        } else {
            i2 = 0;
        }
        System.arraycopy(this._currentSegment, 0, carr, i2, this._currentSize);
        return carr;
    }

    private void unshare(int i2) {
        int i3 = this._inputLen;
        this._inputLen = 0;
        char[] cArr = this._inputBuffer;
        this._inputBuffer = null;
        int i4 = this._inputStart;
        this._inputStart = -1;
        int i5 = i2 + i3;
        char[] cArr2 = this._currentSegment;
        if (cArr2 == null || i5 > cArr2.length) {
            this._currentSegment = buf(i5);
        }
        if (i3 > 0) {
            System.arraycopy(cArr, i4, this._currentSegment, 0, i3);
        }
        this._segmentSize = 0;
        this._currentSize = i3;
    }

    public void append(char c2) {
        if (this._inputStart >= 0) {
            unshare(16);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        if (this._currentSize >= cArr.length) {
            expand(1);
            cArr = this._currentSegment;
        }
        int i2 = this._currentSize;
        this._currentSize = i2 + 1;
        cArr[i2] = c2;
    }

    public void append(String str, int i2, int i3) {
        if (this._inputStart >= 0) {
            unshare(i3);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int i4 = this._currentSize;
        int i5 = length - i4;
        if (i5 >= i3) {
            str.getChars(i2, i2 + i3, cArr, i4);
            this._currentSize += i3;
            return;
        }
        if (i5 > 0) {
            int i6 = i2 + i5;
            str.getChars(i2, i6, cArr, i4);
            i3 -= i5;
            i2 = i6;
        }
        while (true) {
            expand(i3);
            int min = Math.min(this._currentSegment.length, i3);
            int i7 = i2 + min;
            str.getChars(i2, i7, this._currentSegment, 0);
            this._currentSize += min;
            i3 -= min;
            if (i3 <= 0) {
                return;
            }
            i2 = i7;
        }
    }

    public void append(char[] cArr, int i2, int i3) {
        if (this._inputStart >= 0) {
            unshare(i3);
        }
        this._resultString = null;
        this._resultArray = null;
        char[] cArr2 = this._currentSegment;
        int length = cArr2.length;
        int i4 = this._currentSize;
        int i5 = length - i4;
        if (i5 >= i3) {
            System.arraycopy(cArr, i2, cArr2, i4, i3);
            this._currentSize += i3;
            return;
        }
        if (i5 > 0) {
            System.arraycopy(cArr, i2, cArr2, i4, i5);
            i2 += i5;
            i3 -= i5;
        }
        do {
            expand(i3);
            int min = Math.min(this._currentSegment.length, i3);
            System.arraycopy(cArr, i2, this._currentSegment, 0, min);
            this._currentSize += min;
            i2 += min;
            i3 -= min;
        } while (i3 > 0);
    }

    public char[] contentsAsArray() {
        char[] cArr = this._resultArray;
        if (cArr == null) {
            char[] resultArray = resultArray();
            this._resultArray = resultArray;
            return resultArray;
        }
        return cArr;
    }

    public BigDecimal contentsAsDecimal() {
        char[] cArr;
        char[] cArr2;
        char[] cArr3 = this._resultArray;
        if (cArr3 != null) {
            return NumberInput.parseBigDecimal(cArr3);
        }
        int i2 = this._inputStart;
        return (i2 < 0 || (cArr2 = this._inputBuffer) == null) ? (this._segmentSize != 0 || (cArr = this._currentSegment) == null) ? NumberInput.parseBigDecimal(contentsAsArray()) : NumberInput.parseBigDecimal(cArr, 0, this._currentSize) : NumberInput.parseBigDecimal(cArr2, i2, this._inputLen);
    }

    public double contentsAsDouble() {
        return NumberInput.parseDouble(contentsAsString());
    }

    public int contentsAsInt(boolean z) {
        char[] cArr;
        int i2 = this._inputStart;
        return (i2 < 0 || (cArr = this._inputBuffer) == null) ? z ? -NumberInput.parseInt(this._currentSegment, 1, this._currentSize - 1) : NumberInput.parseInt(this._currentSegment, 0, this._currentSize) : z ? -NumberInput.parseInt(cArr, i2 + 1, this._inputLen - 1) : NumberInput.parseInt(cArr, i2, this._inputLen);
    }

    public long contentsAsLong(boolean z) {
        char[] cArr;
        int i2 = this._inputStart;
        return (i2 < 0 || (cArr = this._inputBuffer) == null) ? z ? -NumberInput.parseLong(this._currentSegment, 1, this._currentSize - 1) : NumberInput.parseLong(this._currentSegment, 0, this._currentSize) : z ? -NumberInput.parseLong(cArr, i2 + 1, this._inputLen - 1) : NumberInput.parseLong(cArr, i2, this._inputLen);
    }

    public String contentsAsString() {
        String str;
        if (this._resultString == null) {
            char[] cArr = this._resultArray;
            if (cArr != null) {
                str = new String(cArr);
            } else {
                int i2 = this._inputStart;
                str = "";
                if (i2 >= 0) {
                    int i3 = this._inputLen;
                    if (i3 < 1) {
                        this._resultString = "";
                        return "";
                    }
                    str = new String(this._inputBuffer, i2, i3);
                } else {
                    int i4 = this._segmentSize;
                    int i5 = this._currentSize;
                    if (i4 != 0) {
                        StringBuilder sb = new StringBuilder(i4 + i5);
                        ArrayList<char[]> arrayList = this._segments;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            for (int i6 = 0; i6 < size; i6++) {
                                char[] cArr2 = this._segments.get(i6);
                                sb.append(cArr2, 0, cArr2.length);
                            }
                        }
                        sb.append(this._currentSegment, 0, this._currentSize);
                        this._resultString = sb.toString();
                    } else if (i5 != 0) {
                        str = new String(this._currentSegment, 0, i5);
                    }
                }
            }
            this._resultString = str;
        }
        return this._resultString;
    }

    public int contentsToWriter(Writer writer) {
        int i2;
        char[] cArr = this._resultArray;
        if (cArr != null) {
            writer.write(cArr);
            return this._resultArray.length;
        }
        String str = this._resultString;
        if (str != null) {
            writer.write(str);
            return this._resultString.length();
        }
        int i3 = this._inputStart;
        if (i3 >= 0) {
            int i4 = this._inputLen;
            if (i4 > 0) {
                writer.write(this._inputBuffer, i3, i4);
            }
            return i4;
        }
        ArrayList<char[]> arrayList = this._segments;
        if (arrayList != null) {
            int size = arrayList.size();
            i2 = 0;
            for (int i5 = 0; i5 < size; i5++) {
                char[] cArr2 = this._segments.get(i5);
                int length = cArr2.length;
                writer.write(cArr2, 0, length);
                i2 += length;
            }
        } else {
            i2 = 0;
        }
        int i6 = this._currentSize;
        if (i6 > 0) {
            writer.write(this._currentSegment, 0, i6);
            return i2 + i6;
        }
        return i2;
    }

    public char[] emptyAndGetCurrentSegment() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        char[] cArr = this._currentSegment;
        if (cArr == null) {
            char[] buf = buf(0);
            this._currentSegment = buf;
            return buf;
        }
        return cArr;
    }

    public void ensureNotShared() {
        if (this._inputStart >= 0) {
            unshare(16);
        }
    }

    public char[] expandCurrentSegment() {
        char[] cArr = this._currentSegment;
        int length = cArr.length;
        int i2 = (length >> 1) + length;
        if (i2 > 65536) {
            i2 = (length >> 2) + length;
        }
        char[] copyOf = Arrays.copyOf(cArr, i2);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public char[] expandCurrentSegment(int i2) {
        char[] cArr = this._currentSegment;
        if (cArr.length >= i2) {
            return cArr;
        }
        char[] copyOf = Arrays.copyOf(cArr, i2);
        this._currentSegment = copyOf;
        return copyOf;
    }

    public char[] finishCurrentSegment() {
        if (this._segments == null) {
            this._segments = new ArrayList<>();
        }
        this._hasSegments = true;
        this._segments.add(this._currentSegment);
        int length = this._currentSegment.length;
        this._segmentSize += length;
        this._currentSize = 0;
        int i2 = length + (length >> 1);
        if (i2 < 500) {
            i2 = 500;
        } else if (i2 > 65536) {
            i2 = 65536;
        }
        char[] carr = carr(i2);
        this._currentSegment = carr;
        return carr;
    }

    public char[] getBufferWithoutReset() {
        return this._currentSegment;
    }

    public char[] getCurrentSegment() {
        if (this._inputStart >= 0) {
            unshare(1);
        } else {
            char[] cArr = this._currentSegment;
            if (cArr == null) {
                this._currentSegment = buf(0);
            } else if (this._currentSize >= cArr.length) {
                expand(1);
            }
        }
        return this._currentSegment;
    }

    public int getCurrentSegmentSize() {
        return this._currentSize;
    }

    public char[] getTextBuffer() {
        if (this._inputStart >= 0) {
            return this._inputBuffer;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr;
        }
        String str = this._resultString;
        if (str != null) {
            char[] charArray = str.toCharArray();
            this._resultArray = charArray;
            return charArray;
        } else if (this._hasSegments) {
            return contentsAsArray();
        } else {
            char[] cArr2 = this._currentSegment;
            return cArr2 == null ? f5247a : cArr2;
        }
    }

    public int getTextOffset() {
        int i2 = this._inputStart;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    public boolean hasTextAsCharacters() {
        return this._inputStart >= 0 || this._resultArray != null || this._resultString == null;
    }

    public void releaseBuffers() {
        char[] cArr;
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        BufferRecycler bufferRecycler = this._allocator;
        if (bufferRecycler == null || (cArr = this._currentSegment) == null) {
            return;
        }
        this._currentSegment = null;
        bufferRecycler.releaseCharBuffer(2, cArr);
    }

    public void resetWith(char c2) {
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(1);
        }
        this._currentSegment[0] = c2;
        this._segmentSize = 1;
        this._currentSize = 1;
    }

    public void resetWithCopy(String str, int i2, int i3) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(i3);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(str, i2, i3);
    }

    public void resetWithCopy(char[] cArr, int i2, int i3) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        } else if (this._currentSegment == null) {
            this._currentSegment = buf(i3);
        }
        this._segmentSize = 0;
        this._currentSize = 0;
        append(cArr, i2, i3);
    }

    public void resetWithEmpty() {
        this._inputStart = -1;
        this._currentSize = 0;
        this._inputLen = 0;
        this._inputBuffer = null;
        this._resultString = null;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithShared(char[] cArr, int i2, int i3) {
        this._resultString = null;
        this._resultArray = null;
        this._inputBuffer = cArr;
        this._inputStart = i2;
        this._inputLen = i3;
        if (this._hasSegments) {
            clearSegments();
        }
    }

    public void resetWithString(String str) {
        this._inputBuffer = null;
        this._inputStart = -1;
        this._inputLen = 0;
        this._resultString = str;
        this._resultArray = null;
        if (this._hasSegments) {
            clearSegments();
        }
        this._currentSize = 0;
    }

    public String setCurrentAndReturn(int i2) {
        this._currentSize = i2;
        if (this._segmentSize > 0) {
            return contentsAsString();
        }
        String str = i2 == 0 ? "" : new String(this._currentSegment, 0, i2);
        this._resultString = str;
        return str;
    }

    public void setCurrentLength(int i2) {
        this._currentSize = i2;
    }

    public int size() {
        if (this._inputStart >= 0) {
            return this._inputLen;
        }
        char[] cArr = this._resultArray;
        if (cArr != null) {
            return cArr.length;
        }
        String str = this._resultString;
        return str != null ? str.length() : this._segmentSize + this._currentSize;
    }

    public String toString() {
        return contentsAsString();
    }
}
