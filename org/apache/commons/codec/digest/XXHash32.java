package org.apache.commons.codec.digest;

import java.util.zip.Checksum;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes3.dex */
public class XXHash32 implements Checksum {
    private static final int BUF_SIZE = 16;
    private static final int PRIME1 = -1640531535;
    private static final int PRIME2 = -2048144777;
    private static final int PRIME3 = -1028477379;
    private static final int PRIME4 = 668265263;
    private static final int PRIME5 = 374761393;
    private static final int ROTATE_BITS = 13;
    private final byte[] buffer;
    private final byte[] oneByte;
    private int pos;
    private final int seed;
    private final int[] state;
    private int totalLen;

    public XXHash32() {
        this(0);
    }

    public XXHash32(int i2) {
        this.oneByte = new byte[1];
        this.state = new int[4];
        this.buffer = new byte[16];
        this.seed = i2;
        initializeState();
    }

    private static long fromLittleEndian(byte[] bArr, int i2, int i3) {
        if (i3 <= 8) {
            long j2 = 0;
            for (int i4 = 0; i4 < i3; i4++) {
                j2 |= (bArr[i2 + i4] & 255) << (i4 * 8);
            }
            return j2;
        }
        throw new IllegalArgumentException("can't read more than eight bytes into a long value");
    }

    private static int getInt(byte[] bArr, int i2) {
        return (int) (fromLittleEndian(bArr, i2, 4) & BodyPartID.bodyIdMax);
    }

    private void initializeState() {
        int[] iArr = this.state;
        int i2 = this.seed;
        iArr[0] = i2 + PRIME1 + PRIME2;
        iArr[1] = PRIME2 + i2;
        iArr[2] = i2;
        iArr[3] = i2 - PRIME1;
    }

    private void process(byte[] bArr, int i2) {
        int[] iArr = this.state;
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        int i6 = iArr[3];
        int rotateLeft = Integer.rotateLeft(i3 + (getInt(bArr, i2) * PRIME2), 13) * PRIME1;
        int rotateLeft2 = Integer.rotateLeft(i4 + (getInt(bArr, i2 + 4) * PRIME2), 13) * PRIME1;
        int rotateLeft3 = Integer.rotateLeft(i5 + (getInt(bArr, i2 + 8) * PRIME2), 13) * PRIME1;
        int rotateLeft4 = Integer.rotateLeft(i6 + (getInt(bArr, i2 + 12) * PRIME2), 13) * PRIME1;
        int[] iArr2 = this.state;
        iArr2[0] = rotateLeft;
        iArr2[1] = rotateLeft2;
        iArr2[2] = rotateLeft3;
        iArr2[3] = rotateLeft4;
        this.pos = 0;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        int i2 = 0;
        int rotateLeft = (this.totalLen > 16 ? Integer.rotateLeft(this.state[0], 1) + Integer.rotateLeft(this.state[1], 7) + Integer.rotateLeft(this.state[2], 12) + Integer.rotateLeft(this.state[3], 18) : this.state[2] + PRIME5) + this.totalLen;
        int i3 = this.pos - 4;
        while (i2 <= i3) {
            rotateLeft = Integer.rotateLeft(rotateLeft + (getInt(this.buffer, i2) * PRIME3), 17) * PRIME4;
            i2 += 4;
        }
        while (i2 < this.pos) {
            rotateLeft = Integer.rotateLeft(rotateLeft + ((this.buffer[i2] & 255) * PRIME5), 11) * PRIME1;
            i2++;
        }
        int i4 = (rotateLeft ^ (rotateLeft >>> 15)) * PRIME2;
        int i5 = (i4 ^ (i4 >>> 13)) * PRIME3;
        return (i5 ^ (i5 >>> 16)) & BodyPartID.bodyIdMax;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        initializeState();
        this.totalLen = 0;
        this.pos = 0;
    }

    @Override // java.util.zip.Checksum
    public void update(int i2) {
        byte[] bArr = this.oneByte;
        bArr[0] = (byte) (i2 & 255);
        update(bArr, 0, 1);
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i2, int i3) {
        if (i3 <= 0) {
            return;
        }
        this.totalLen += i3;
        int i4 = i2 + i3;
        int i5 = this.pos;
        if (i5 + i3 < 16) {
            System.arraycopy(bArr, i2, this.buffer, i5, i3);
            this.pos += i3;
            return;
        }
        if (i5 > 0) {
            int i6 = 16 - i5;
            System.arraycopy(bArr, i2, this.buffer, i5, i6);
            process(this.buffer, 0);
            i2 += i6;
        }
        int i7 = i4 - 16;
        while (i2 <= i7) {
            process(bArr, i2);
            i2 += 16;
        }
        if (i2 < i4) {
            int i8 = i4 - i2;
            this.pos = i8;
            System.arraycopy(bArr, i2, this.buffer, 0, i8);
        }
    }
}
