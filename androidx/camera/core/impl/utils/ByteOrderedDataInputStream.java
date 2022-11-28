package androidx.camera.core.impl.utils;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes.dex */
final class ByteOrderedDataInputStream extends InputStream implements DataInput {

    /* renamed from: a  reason: collision with root package name */
    final int f1178a;

    /* renamed from: b  reason: collision with root package name */
    int f1179b;
    private ByteOrder mByteOrder;
    private final DataInputStream mDataInputStream;
    private static final ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
    private static final ByteOrder BIG_ENDIAN = ByteOrder.BIG_ENDIAN;

    ByteOrderedDataInputStream(InputStream inputStream) {
        this(inputStream, ByteOrder.BIG_ENDIAN);
    }

    ByteOrderedDataInputStream(InputStream inputStream, ByteOrder byteOrder) {
        this.mByteOrder = ByteOrder.BIG_ENDIAN;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.mDataInputStream = dataInputStream;
        int available = dataInputStream.available();
        this.f1178a = available;
        this.f1179b = 0;
        dataInputStream.mark(available);
        this.mByteOrder = byteOrder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteOrderedDataInputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr));
    }

    @Override // java.io.InputStream
    public int available() {
        return this.mDataInputStream.available();
    }

    public int getLength() {
        return this.f1178a;
    }

    @Override // java.io.InputStream
    public void mark(int i2) {
        synchronized (this.mDataInputStream) {
            this.mDataInputStream.mark(i2);
        }
    }

    public int peek() {
        return this.f1179b;
    }

    @Override // java.io.InputStream
    public int read() {
        this.f1179b++;
        return this.mDataInputStream.read();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i2, int i3) {
        int read = this.mDataInputStream.read(bArr, i2, i3);
        this.f1179b += read;
        return read;
    }

    @Override // java.io.DataInput
    public boolean readBoolean() {
        this.f1179b++;
        return this.mDataInputStream.readBoolean();
    }

    @Override // java.io.DataInput
    public byte readByte() {
        int i2 = this.f1179b + 1;
        this.f1179b = i2;
        if (i2 <= this.f1178a) {
            int read = this.mDataInputStream.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public char readChar() {
        this.f1179b += 2;
        return this.mDataInputStream.readChar();
    }

    @Override // java.io.DataInput
    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) {
        int length = this.f1179b + bArr.length;
        this.f1179b = length;
        if (length > this.f1178a) {
            throw new EOFException();
        }
        if (this.mDataInputStream.read(bArr, 0, bArr.length) != bArr.length) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i2, int i3) {
        int i4 = this.f1179b + i3;
        this.f1179b = i4;
        if (i4 > this.f1178a) {
            throw new EOFException();
        }
        if (this.mDataInputStream.read(bArr, i2, i3) != i3) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    @Override // java.io.DataInput
    public int readInt() {
        int i2 = this.f1179b + 4;
        this.f1179b = i2;
        if (i2 <= this.f1178a) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == LITTLE_ENDIAN) {
                    return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == BIG_ENDIAN) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public String readLine() {
        throw new UnsupportedOperationException("readLine() not implemented.");
    }

    @Override // java.io.DataInput
    public long readLong() {
        int i2 = this.f1179b + 8;
        this.f1179b = i2;
        if (i2 <= this.f1178a) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            int read3 = this.mDataInputStream.read();
            int read4 = this.mDataInputStream.read();
            int read5 = this.mDataInputStream.read();
            int read6 = this.mDataInputStream.read();
            int read7 = this.mDataInputStream.read();
            int read8 = this.mDataInputStream.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == LITTLE_ENDIAN) {
                    return (read8 << 56) + (read7 << 48) + (read6 << 40) + (read5 << 32) + (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == BIG_ENDIAN) {
                    return (read << 56) + (read2 << 48) + (read3 << 40) + (read4 << 32) + (read5 << 24) + (read6 << 16) + (read7 << 8) + read8;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public short readShort() {
        int i2 = this.f1179b + 2;
        this.f1179b = i2;
        if (i2 <= this.f1178a) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == LITTLE_ENDIAN) {
                    return (short) ((read2 << 8) + read);
                }
                if (byteOrder == BIG_ENDIAN) {
                    return (short) ((read << 8) + read2);
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public String readUTF() {
        this.f1179b += 2;
        return this.mDataInputStream.readUTF();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() {
        this.f1179b++;
        return this.mDataInputStream.readUnsignedByte();
    }

    public long readUnsignedInt() {
        return readInt() & BodyPartID.bodyIdMax;
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() {
        int i2 = this.f1179b + 2;
        this.f1179b = i2;
        if (i2 <= this.f1178a) {
            int read = this.mDataInputStream.read();
            int read2 = this.mDataInputStream.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.mByteOrder;
                if (byteOrder == LITTLE_ENDIAN) {
                    return (read2 << 8) + read;
                }
                if (byteOrder == BIG_ENDIAN) {
                    return (read << 8) + read2;
                }
                throw new IOException("Invalid byte order: " + this.mByteOrder);
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    public void seek(long j2) {
        int i2 = this.f1179b;
        if (i2 > j2) {
            this.f1179b = 0;
            this.mDataInputStream.reset();
            this.mDataInputStream.mark(this.f1178a);
        } else {
            j2 -= i2;
        }
        int i3 = (int) j2;
        if (skipBytes(i3) != i3) {
            throw new IOException("Couldn't seek up to the byteCount");
        }
    }

    public void setByteOrder(ByteOrder byteOrder) {
        this.mByteOrder = byteOrder;
    }

    @Override // java.io.DataInput
    public int skipBytes(int i2) {
        int min = Math.min(i2, this.f1178a - this.f1179b);
        int i3 = 0;
        while (i3 < min) {
            i3 += this.mDataInputStream.skipBytes(min - i3);
        }
        this.f1179b += i3;
        return i3;
    }
}
