package org.bouncycastle.pqc.math.linearalgebra;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.common.base.Ascii;
import com.google.common.primitives.Shorts;
import java.math.BigInteger;
import java.util.Random;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.apache.commons.codec.language.bm.Rule;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.bouncycastle.asn1.cmp.PKIFailureInfo;
import org.bouncycastle.util.Arrays;
/* loaded from: classes4.dex */
public class GF2Polynomial {
    private int blocks;
    private int len;
    private int[] value;
    private static Random rand = new Random();
    private static final boolean[] parity = {false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    private static final short[] squaringTable = {0, 1, 4, 5, 16, 17, 20, 21, 64, 65, 68, 69, 80, 81, 84, 85, 256, 257, 260, 261, 272, 273, 276, 277, 320, 321, 324, 325, 336, 337, 340, 341, 1024, 1025, 1028, 1029, 1040, 1041, 1044, 1045, 1088, 1089, 1092, 1093, 1104, 1105, 1108, 1109, 1280, 1281, 1284, 1285, 1296, 1297, 1300, 1301, 1344, 1345, 1348, 1349, 1360, 1361, 1364, 1365, 4096, 4097, 4100, 4101, 4112, 4113, 4116, 4117, 4160, 4161, 4164, 4165, 4176, 4177, 4180, 4181, 4352, 4353, 4356, 4357, 4368, 4369, 4372, 4373, 4416, 4417, 4420, 4421, 4432, 4433, 4436, 4437, 5120, 5121, 5124, 5125, 5136, 5137, 5140, 5141, 5184, 5185, 5188, 5189, 5200, 5201, 5204, 5205, 5376, 5377, 5380, 5381, 5392, 5393, 5396, 5397, 5440, 5441, 5444, 5445, 5456, 5457, 5460, 5461, Shorts.MAX_POWER_OF_TWO, 16385, 16388, 16389, 16400, 16401, 16404, 16405, 16448, 16449, 16452, 16453, 16464, 16465, 16468, 16469, 16640, 16641, 16644, 16645, 16656, 16657, 16660, 16661, 16704, 16705, 16708, 16709, 16720, 16721, 16724, 16725, 17408, 17409, 17412, 17413, 17424, 17425, 17428, 17429, 17472, 17473, 17476, 17477, 17488, 17489, 17492, 17493, 17664, 17665, 17668, 17669, 17680, 17681, 17684, 17685, 17728, 17729, 17732, 17733, 17744, 17745, 17748, 17749, 20480, 20481, 20484, 20485, 20496, 20497, 20500, 20501, 20544, 20545, 20548, 20549, 20560, 20561, 20564, 20565, 20736, 20737, 20740, 20741, 20752, 20753, 20756, 20757, 20800, 20801, 20804, 20805, 20816, 20817, 20820, 20821, 21504, 21505, 21508, 21509, 21520, 21521, 21524, 21525, 21568, 21569, 21572, 21573, 21584, 21585, 21588, 21589, 21760, 21761, 21764, 21765, 21776, 21777, 21780, 21781, 21824, 21825, 21828, 21829, 21840, 21841, 21844, 21845};
    private static final int[] bitMask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, MediaHttpDownloader.MAXIMUM_CHUNK_SIZE, 67108864, 134217728, 268435456, PKIFailureInfo.duplicateCertReq, 1073741824, Integer.MIN_VALUE, 0};
    private static final int[] reverseRightMask = {0, 1, 3, 7, 15, 31, 63, 127, 255, FrameMetricsAggregator.EVERY_DURATION, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, LockFreeTaskQueueCore.MAX_CAPACITY_MASK, Integer.MAX_VALUE, -1};

    public GF2Polynomial(int i2) {
        i2 = i2 < 1 ? 1 : i2;
        int i3 = ((i2 - 1) >> 5) + 1;
        this.blocks = i3;
        this.value = new int[i3];
        this.len = i2;
    }

    public GF2Polynomial(int i2, String str) {
        i2 = i2 < 1 ? 1 : i2;
        int i3 = ((i2 - 1) >> 5) + 1;
        this.blocks = i3;
        this.value = new int[i3];
        this.len = i2;
        if (str.equalsIgnoreCase("ZERO")) {
            assignZero();
        } else if (str.equalsIgnoreCase("ONE")) {
            assignOne();
        } else if (str.equalsIgnoreCase("RANDOM")) {
            randomize();
        } else if (str.equalsIgnoreCase("X")) {
            assignX();
        } else if (str.equalsIgnoreCase(Rule.ALL)) {
            assignAll();
        } else {
            throw new IllegalArgumentException("Error: GF2Polynomial was called using " + str + " as value!");
        }
    }

    public GF2Polynomial(int i2, BigInteger bigInteger) {
        i2 = i2 < 1 ? 1 : i2;
        int i3 = ((i2 - 1) >> 5) + 1;
        this.blocks = i3;
        this.value = new int[i3];
        this.len = i2;
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            byteArray = bArr;
        }
        int length2 = byteArray.length & 3;
        int length3 = ((byteArray.length - 1) >> 2) + 1;
        for (int i4 = 0; i4 < length2; i4++) {
            int[] iArr = this.value;
            int i5 = length3 - 1;
            iArr[i5] = iArr[i5] | ((byteArray[i4] & 255) << (((length2 - 1) - i4) << 3));
        }
        for (int i6 = 0; i6 <= ((byteArray.length - 4) >> 2); i6++) {
            int length4 = (byteArray.length - 1) - (i6 << 2);
            int[] iArr2 = this.value;
            iArr2[i6] = byteArray[length4] & 255;
            iArr2[i6] = iArr2[i6] | ((byteArray[length4 - 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            iArr2[i6] = iArr2[i6] | ((byteArray[length4 - 2] << 16) & 16711680);
            iArr2[i6] = ((byteArray[length4 - 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | iArr2[i6];
        }
        int i7 = this.len;
        if ((i7 & 31) != 0) {
            int[] iArr3 = this.value;
            int i8 = this.blocks - 1;
            iArr3[i8] = reverseRightMask[i7 & 31] & iArr3[i8];
        }
        reduceN();
    }

    public GF2Polynomial(int i2, Random random) {
        i2 = i2 < 1 ? 1 : i2;
        int i3 = ((i2 - 1) >> 5) + 1;
        this.blocks = i3;
        this.value = new int[i3];
        this.len = i2;
        randomize(random);
    }

    public GF2Polynomial(int i2, byte[] bArr) {
        int i3;
        i2 = i2 < 1 ? 1 : i2;
        int i4 = ((i2 - 1) >> 5) + 1;
        this.blocks = i4;
        this.value = new int[i4];
        this.len = i2;
        int min = Math.min(((bArr.length - 1) >> 2) + 1, i4);
        int i5 = 0;
        while (true) {
            i3 = min - 1;
            if (i5 >= i3) {
                break;
            }
            int length = (bArr.length - (i5 << 2)) - 1;
            int[] iArr = this.value;
            iArr[i5] = bArr[length] & 255;
            iArr[i5] = (65280 & (bArr[length - 1] << 8)) | iArr[i5];
            iArr[i5] = (16711680 & (bArr[length - 2] << 16)) | iArr[i5];
            iArr[i5] = ((bArr[length - 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | iArr[i5];
            i5++;
        }
        int length2 = (bArr.length - (i3 << 2)) - 1;
        int[] iArr2 = this.value;
        iArr2[i3] = bArr[length2] & 255;
        if (length2 > 0) {
            iArr2[i3] = (65280 & (bArr[length2 - 1] << 8)) | iArr2[i3];
        }
        if (length2 > 1) {
            iArr2[i3] = iArr2[i3] | (16711680 & (bArr[length2 - 2] << 16));
        }
        if (length2 > 2) {
            iArr2[i3] = ((bArr[length2 - 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | iArr2[i3];
        }
        zeroUnusedBits();
        reduceN();
    }

    public GF2Polynomial(int i2, int[] iArr) {
        i2 = i2 < 1 ? 1 : i2;
        int i3 = ((i2 - 1) >> 5) + 1;
        this.blocks = i3;
        this.value = new int[i3];
        this.len = i2;
        System.arraycopy(iArr, 0, this.value, 0, Math.min(i3, iArr.length));
        zeroUnusedBits();
    }

    public GF2Polynomial(GF2Polynomial gF2Polynomial) {
        this.len = gF2Polynomial.len;
        this.blocks = gF2Polynomial.blocks;
        this.value = IntUtils.clone(gF2Polynomial.value);
    }

    private void doShiftBlocksLeft(int i2) {
        int i3 = this.blocks;
        int[] iArr = this.value;
        if (i3 > iArr.length) {
            int[] iArr2 = new int[i3];
            System.arraycopy(iArr, 0, iArr2, i2, i3 - i2);
            this.value = null;
            this.value = iArr2;
            return;
        }
        for (int i4 = i3 - 1; i4 >= i2; i4--) {
            int[] iArr3 = this.value;
            iArr3[i4] = iArr3[i4 - i2];
        }
        for (int i5 = 0; i5 < i2; i5++) {
            this.value[i5] = 0;
        }
    }

    private GF2Polynomial karaMult(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len << 1);
        int i2 = this.len;
        if (i2 <= 32) {
            gF2Polynomial2.value = mult32(this.value[0], gF2Polynomial.value[0]);
            return gF2Polynomial2;
        } else if (i2 <= 64) {
            gF2Polynomial2.value = mult64(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else if (i2 <= 128) {
            gF2Polynomial2.value = mult128(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else if (i2 <= 256) {
            gF2Polynomial2.value = mult256(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else if (i2 <= 512) {
            gF2Polynomial2.value = mult512(this.value, gF2Polynomial.value);
            return gF2Polynomial2;
        } else {
            int i3 = bitMask[IntegerFunctions.floorLog(i2 - 1)];
            int i4 = ((i3 - 1) >> 5) + 1;
            GF2Polynomial lower = lower(i4);
            GF2Polynomial upper = upper(i4);
            GF2Polynomial lower2 = gF2Polynomial.lower(i4);
            GF2Polynomial upper2 = gF2Polynomial.upper(i4);
            GF2Polynomial karaMult = upper.karaMult(upper2);
            GF2Polynomial karaMult2 = lower.karaMult(lower2);
            lower.addToThis(upper);
            lower2.addToThis(upper2);
            GF2Polynomial karaMult3 = lower.karaMult(lower2);
            gF2Polynomial2.shiftLeftAddThis(karaMult, i3 << 1);
            gF2Polynomial2.shiftLeftAddThis(karaMult, i3);
            gF2Polynomial2.shiftLeftAddThis(karaMult3, i3);
            gF2Polynomial2.shiftLeftAddThis(karaMult2, i3);
            gF2Polynomial2.addToThis(karaMult2);
            return gF2Polynomial2;
        }
    }

    private GF2Polynomial lower(int i2) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(i2 << 5);
        System.arraycopy(this.value, 0, gF2Polynomial.value, 0, Math.min(i2, this.blocks));
        return gF2Polynomial;
    }

    private static int[] mult128(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[8];
        int[] iArr4 = new int[2];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(2, iArr.length));
        int[] iArr5 = new int[2];
        if (iArr.length > 2) {
            System.arraycopy(iArr, 2, iArr5, 0, Math.min(2, iArr.length - 2));
        }
        int[] iArr6 = new int[2];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(2, iArr2.length));
        int[] iArr7 = new int[2];
        if (iArr2.length > 2) {
            System.arraycopy(iArr2, 2, iArr7, 0, Math.min(2, iArr2.length - 2));
        }
        if (iArr5[1] != 0 || iArr7[1] != 0) {
            int[] mult64 = mult64(iArr5, iArr7);
            iArr3[7] = iArr3[7] ^ mult64[3];
            iArr3[6] = iArr3[6] ^ mult64[2];
            iArr3[5] = iArr3[5] ^ (mult64[1] ^ mult64[3]);
            iArr3[4] = iArr3[4] ^ (mult64[0] ^ mult64[2]);
            iArr3[3] = iArr3[3] ^ mult64[1];
            iArr3[2] = mult64[0] ^ iArr3[2];
        } else if (iArr5[0] != 0 || iArr7[0] != 0) {
            int[] mult32 = mult32(iArr5[0], iArr7[0]);
            iArr3[5] = iArr3[5] ^ mult32[1];
            iArr3[4] = iArr3[4] ^ mult32[0];
            iArr3[3] = iArr3[3] ^ mult32[1];
            iArr3[2] = mult32[0] ^ iArr3[2];
        }
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        if (iArr5[1] == 0 && iArr7[1] == 0) {
            int[] mult322 = mult32(iArr5[0], iArr7[0]);
            iArr3[3] = iArr3[3] ^ mult322[1];
            iArr3[2] = mult322[0] ^ iArr3[2];
        } else {
            int[] mult642 = mult64(iArr5, iArr7);
            iArr3[5] = iArr3[5] ^ mult642[3];
            iArr3[4] = iArr3[4] ^ mult642[2];
            iArr3[3] = iArr3[3] ^ mult642[1];
            iArr3[2] = mult642[0] ^ iArr3[2];
        }
        if (iArr4[1] == 0 && iArr6[1] == 0) {
            int[] mult323 = mult32(iArr4[0], iArr6[0]);
            iArr3[3] = iArr3[3] ^ mult323[1];
            iArr3[2] = iArr3[2] ^ mult323[0];
            iArr3[1] = iArr3[1] ^ mult323[1];
            iArr3[0] = mult323[0] ^ iArr3[0];
        } else {
            int[] mult643 = mult64(iArr4, iArr6);
            iArr3[5] = iArr3[5] ^ mult643[3];
            iArr3[4] = iArr3[4] ^ mult643[2];
            iArr3[3] = iArr3[3] ^ (mult643[1] ^ mult643[3]);
            iArr3[2] = iArr3[2] ^ (mult643[0] ^ mult643[2]);
            iArr3[1] = iArr3[1] ^ mult643[1];
            iArr3[0] = mult643[0] ^ iArr3[0];
        }
        return iArr3;
    }

    private static int[] mult256(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[16];
        int[] iArr4 = new int[4];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(4, iArr.length));
        int[] iArr5 = new int[4];
        if (iArr.length > 4) {
            System.arraycopy(iArr, 4, iArr5, 0, Math.min(4, iArr.length - 4));
        }
        int[] iArr6 = new int[4];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(4, iArr2.length));
        int[] iArr7 = new int[4];
        if (iArr2.length > 4) {
            System.arraycopy(iArr2, 4, iArr7, 0, Math.min(4, iArr2.length - 4));
        }
        if (iArr5[3] != 0 || iArr5[2] != 0 || iArr7[3] != 0 || iArr7[2] != 0) {
            int[] mult128 = mult128(iArr5, iArr7);
            iArr3[15] = iArr3[15] ^ mult128[7];
            iArr3[14] = iArr3[14] ^ mult128[6];
            iArr3[13] = iArr3[13] ^ mult128[5];
            iArr3[12] = iArr3[12] ^ mult128[4];
            iArr3[11] = iArr3[11] ^ (mult128[3] ^ mult128[7]);
            iArr3[10] = iArr3[10] ^ (mult128[2] ^ mult128[6]);
            iArr3[9] = iArr3[9] ^ (mult128[1] ^ mult128[5]);
            iArr3[8] = iArr3[8] ^ (mult128[0] ^ mult128[4]);
            iArr3[7] = iArr3[7] ^ mult128[3];
            iArr3[6] = iArr3[6] ^ mult128[2];
            iArr3[5] = iArr3[5] ^ mult128[1];
            iArr3[4] = mult128[0] ^ iArr3[4];
        } else if (iArr5[1] != 0 || iArr7[1] != 0) {
            int[] mult64 = mult64(iArr5, iArr7);
            iArr3[11] = iArr3[11] ^ mult64[3];
            iArr3[10] = iArr3[10] ^ mult64[2];
            iArr3[9] = iArr3[9] ^ mult64[1];
            iArr3[8] = iArr3[8] ^ mult64[0];
            iArr3[7] = iArr3[7] ^ mult64[3];
            iArr3[6] = iArr3[6] ^ mult64[2];
            iArr3[5] = iArr3[5] ^ mult64[1];
            iArr3[4] = mult64[0] ^ iArr3[4];
        } else if (iArr5[0] != 0 || iArr7[0] != 0) {
            int[] mult32 = mult32(iArr5[0], iArr7[0]);
            iArr3[9] = iArr3[9] ^ mult32[1];
            iArr3[8] = iArr3[8] ^ mult32[0];
            iArr3[5] = iArr3[5] ^ mult32[1];
            iArr3[4] = mult32[0] ^ iArr3[4];
        }
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr5[2] = iArr5[2] ^ iArr4[2];
        iArr5[3] = iArr5[3] ^ iArr4[3];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        iArr7[2] = iArr7[2] ^ iArr6[2];
        iArr7[3] = iArr7[3] ^ iArr6[3];
        int[] mult1282 = mult128(iArr5, iArr7);
        iArr3[11] = iArr3[11] ^ mult1282[7];
        iArr3[10] = iArr3[10] ^ mult1282[6];
        iArr3[9] = iArr3[9] ^ mult1282[5];
        iArr3[8] = iArr3[8] ^ mult1282[4];
        iArr3[7] = iArr3[7] ^ mult1282[3];
        iArr3[6] = iArr3[6] ^ mult1282[2];
        iArr3[5] = iArr3[5] ^ mult1282[1];
        iArr3[4] = mult1282[0] ^ iArr3[4];
        int[] mult1283 = mult128(iArr4, iArr6);
        iArr3[11] = iArr3[11] ^ mult1283[7];
        iArr3[10] = iArr3[10] ^ mult1283[6];
        iArr3[9] = iArr3[9] ^ mult1283[5];
        iArr3[8] = iArr3[8] ^ mult1283[4];
        iArr3[7] = iArr3[7] ^ (mult1283[3] ^ mult1283[7]);
        iArr3[6] = iArr3[6] ^ (mult1283[2] ^ mult1283[6]);
        iArr3[5] = iArr3[5] ^ (mult1283[1] ^ mult1283[5]);
        iArr3[4] = iArr3[4] ^ (mult1283[0] ^ mult1283[4]);
        iArr3[3] = iArr3[3] ^ mult1283[3];
        iArr3[2] = iArr3[2] ^ mult1283[2];
        iArr3[1] = iArr3[1] ^ mult1283[1];
        iArr3[0] = mult1283[0] ^ iArr3[0];
        return iArr3;
    }

    private static int[] mult32(int i2, int i3) {
        int[] iArr = new int[2];
        if (i2 != 0 && i3 != 0) {
            long j2 = i3 & BodyPartID.bodyIdMax;
            long j3 = 0;
            for (int i4 = 1; i4 <= 32; i4++) {
                if ((bitMask[i4 - 1] & i2) != 0) {
                    j3 ^= j2;
                }
                j2 <<= 1;
            }
            iArr[1] = (int) (j3 >>> 32);
            iArr[0] = (int) (j3 & BodyPartID.bodyIdMax);
        }
        return iArr;
    }

    private static int[] mult512(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[32];
        int[] iArr4 = new int[8];
        System.arraycopy(iArr, 0, iArr4, 0, Math.min(8, iArr.length));
        int[] iArr5 = new int[8];
        if (iArr.length > 8) {
            System.arraycopy(iArr, 8, iArr5, 0, Math.min(8, iArr.length - 8));
        }
        int[] iArr6 = new int[8];
        System.arraycopy(iArr2, 0, iArr6, 0, Math.min(8, iArr2.length));
        int[] iArr7 = new int[8];
        if (iArr2.length > 8) {
            System.arraycopy(iArr2, 8, iArr7, 0, Math.min(8, iArr2.length - 8));
        }
        int[] mult256 = mult256(iArr5, iArr7);
        iArr3[31] = iArr3[31] ^ mult256[15];
        iArr3[30] = iArr3[30] ^ mult256[14];
        iArr3[29] = iArr3[29] ^ mult256[13];
        iArr3[28] = iArr3[28] ^ mult256[12];
        iArr3[27] = iArr3[27] ^ mult256[11];
        iArr3[26] = iArr3[26] ^ mult256[10];
        iArr3[25] = iArr3[25] ^ mult256[9];
        iArr3[24] = iArr3[24] ^ mult256[8];
        iArr3[23] = iArr3[23] ^ (mult256[7] ^ mult256[15]);
        iArr3[22] = iArr3[22] ^ (mult256[6] ^ mult256[14]);
        iArr3[21] = iArr3[21] ^ (mult256[5] ^ mult256[13]);
        iArr3[20] = iArr3[20] ^ (mult256[4] ^ mult256[12]);
        iArr3[19] = iArr3[19] ^ (mult256[3] ^ mult256[11]);
        iArr3[18] = iArr3[18] ^ (mult256[2] ^ mult256[10]);
        iArr3[17] = iArr3[17] ^ (mult256[1] ^ mult256[9]);
        iArr3[16] = iArr3[16] ^ (mult256[0] ^ mult256[8]);
        iArr3[15] = iArr3[15] ^ mult256[7];
        iArr3[14] = iArr3[14] ^ mult256[6];
        iArr3[13] = iArr3[13] ^ mult256[5];
        iArr3[12] = iArr3[12] ^ mult256[4];
        iArr3[11] = iArr3[11] ^ mult256[3];
        iArr3[10] = iArr3[10] ^ mult256[2];
        iArr3[9] = iArr3[9] ^ mult256[1];
        iArr3[8] = iArr3[8] ^ mult256[0];
        iArr5[0] = iArr5[0] ^ iArr4[0];
        iArr5[1] = iArr5[1] ^ iArr4[1];
        iArr5[2] = iArr5[2] ^ iArr4[2];
        iArr5[3] = iArr5[3] ^ iArr4[3];
        iArr5[4] = iArr5[4] ^ iArr4[4];
        iArr5[5] = iArr5[5] ^ iArr4[5];
        iArr5[6] = iArr5[6] ^ iArr4[6];
        iArr5[7] = iArr5[7] ^ iArr4[7];
        iArr7[0] = iArr7[0] ^ iArr6[0];
        iArr7[1] = iArr7[1] ^ iArr6[1];
        iArr7[2] = iArr7[2] ^ iArr6[2];
        iArr7[3] = iArr7[3] ^ iArr6[3];
        iArr7[4] = iArr7[4] ^ iArr6[4];
        iArr7[5] = iArr7[5] ^ iArr6[5];
        iArr7[6] = iArr7[6] ^ iArr6[6];
        iArr7[7] = iArr7[7] ^ iArr6[7];
        int[] mult2562 = mult256(iArr5, iArr7);
        iArr3[23] = iArr3[23] ^ mult2562[15];
        iArr3[22] = iArr3[22] ^ mult2562[14];
        iArr3[21] = iArr3[21] ^ mult2562[13];
        iArr3[20] = iArr3[20] ^ mult2562[12];
        iArr3[19] = iArr3[19] ^ mult2562[11];
        iArr3[18] = iArr3[18] ^ mult2562[10];
        iArr3[17] = iArr3[17] ^ mult2562[9];
        iArr3[16] = iArr3[16] ^ mult2562[8];
        iArr3[15] = iArr3[15] ^ mult2562[7];
        iArr3[14] = iArr3[14] ^ mult2562[6];
        iArr3[13] = iArr3[13] ^ mult2562[5];
        iArr3[12] = iArr3[12] ^ mult2562[4];
        iArr3[11] = iArr3[11] ^ mult2562[3];
        iArr3[10] = iArr3[10] ^ mult2562[2];
        iArr3[9] = iArr3[9] ^ mult2562[1];
        iArr3[8] = mult2562[0] ^ iArr3[8];
        int[] mult2563 = mult256(iArr4, iArr6);
        iArr3[23] = iArr3[23] ^ mult2563[15];
        iArr3[22] = iArr3[22] ^ mult2563[14];
        iArr3[21] = iArr3[21] ^ mult2563[13];
        iArr3[20] = iArr3[20] ^ mult2563[12];
        iArr3[19] = iArr3[19] ^ mult2563[11];
        iArr3[18] = iArr3[18] ^ mult2563[10];
        iArr3[17] = iArr3[17] ^ mult2563[9];
        iArr3[16] = iArr3[16] ^ mult2563[8];
        iArr3[15] = iArr3[15] ^ (mult2563[7] ^ mult2563[15]);
        iArr3[14] = iArr3[14] ^ (mult2563[6] ^ mult2563[14]);
        iArr3[13] = iArr3[13] ^ (mult2563[5] ^ mult2563[13]);
        iArr3[12] = iArr3[12] ^ (mult2563[4] ^ mult2563[12]);
        iArr3[11] = iArr3[11] ^ (mult2563[3] ^ mult2563[11]);
        iArr3[10] = iArr3[10] ^ (mult2563[2] ^ mult2563[10]);
        iArr3[9] = iArr3[9] ^ (mult2563[1] ^ mult2563[9]);
        iArr3[8] = iArr3[8] ^ (mult2563[0] ^ mult2563[8]);
        iArr3[7] = iArr3[7] ^ mult2563[7];
        iArr3[6] = iArr3[6] ^ mult2563[6];
        iArr3[5] = iArr3[5] ^ mult2563[5];
        iArr3[4] = iArr3[4] ^ mult2563[4];
        iArr3[3] = iArr3[3] ^ mult2563[3];
        iArr3[2] = iArr3[2] ^ mult2563[2];
        iArr3[1] = iArr3[1] ^ mult2563[1];
        iArr3[0] = mult2563[0] ^ iArr3[0];
        return iArr3;
    }

    private static int[] mult64(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[4];
        int i2 = iArr[0];
        int i3 = iArr.length > 1 ? iArr[1] : 0;
        int i4 = iArr2[0];
        int i5 = iArr2.length > 1 ? iArr2[1] : 0;
        if (i3 != 0 || i5 != 0) {
            int[] mult32 = mult32(i3, i5);
            iArr3[3] = iArr3[3] ^ mult32[1];
            iArr3[2] = iArr3[2] ^ (mult32[0] ^ mult32[1]);
            iArr3[1] = mult32[0] ^ iArr3[1];
        }
        int[] mult322 = mult32(i3 ^ i2, i5 ^ i4);
        iArr3[2] = iArr3[2] ^ mult322[1];
        iArr3[1] = mult322[0] ^ iArr3[1];
        int[] mult323 = mult32(i2, i4);
        iArr3[2] = iArr3[2] ^ mult323[1];
        iArr3[1] = iArr3[1] ^ (mult323[0] ^ mult323[1]);
        iArr3[0] = mult323[0] ^ iArr3[0];
        return iArr3;
    }

    private GF2Polynomial upper(int i2) {
        int min = Math.min(i2, this.blocks - i2);
        GF2Polynomial gF2Polynomial = new GF2Polynomial(min << 5);
        if (this.blocks >= i2) {
            System.arraycopy(this.value, i2, gF2Polynomial.value, 0, min);
        }
        return gF2Polynomial;
    }

    private void zeroUnusedBits() {
        int i2 = this.len;
        if ((i2 & 31) != 0) {
            int[] iArr = this.value;
            int i3 = this.blocks - 1;
            iArr[i3] = reverseRightMask[i2 & 31] & iArr[i3];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i2, int[] iArr) {
        GF2Polynomial gF2Polynomial = this;
        int i3 = i2 >>> 5;
        int i4 = i2 & 31;
        int i5 = 32 - i4;
        boolean z = false;
        int i6 = (i2 - iArr[0]) >>> 5;
        int i7 = 32 - ((i2 - iArr[0]) & 31);
        int i8 = (i2 - iArr[1]) >>> 5;
        int i9 = 32 - ((i2 - iArr[1]) & 31);
        int i10 = (i2 - iArr[2]) >>> 5;
        int i11 = 32 - ((i2 - iArr[2]) & 31);
        int i12 = ((i2 << 1) - 2) >>> 5;
        while (i12 > i3) {
            int[] iArr2 = gF2Polynomial.value;
            long j2 = iArr2[i12] & BodyPartID.bodyIdMax;
            int i13 = i12 - i3;
            int i14 = i13 - 1;
            int i15 = i3;
            int i16 = i4;
            iArr2[i14] = ((int) (j2 << i5)) ^ iArr2[i14];
            iArr2[i13] = (int) (iArr2[i13] ^ (j2 >>> (32 - i5)));
            int i17 = i12 - i6;
            int i18 = i17 - 1;
            iArr2[i18] = iArr2[i18] ^ ((int) (j2 << i7));
            iArr2[i17] = (int) (iArr2[i17] ^ (j2 >>> (32 - i7)));
            int i19 = i12 - i8;
            int i20 = i19 - 1;
            iArr2[i20] = iArr2[i20] ^ ((int) (j2 << i9));
            iArr2[i19] = (int) (iArr2[i19] ^ (j2 >>> (32 - i9)));
            int i21 = i12 - i10;
            int i22 = i21 - 1;
            iArr2[i22] = iArr2[i22] ^ ((int) (j2 << i11));
            iArr2[i21] = (int) ((j2 >>> (32 - i11)) ^ iArr2[i21]);
            iArr2[i12] = 0;
            i12--;
            z = false;
            i3 = i15;
            i4 = i16;
            gF2Polynomial = this;
        }
        int i23 = i3;
        int i24 = i4;
        int[] iArr3 = gF2Polynomial.value;
        long j3 = iArr3[i23] & BodyPartID.bodyIdMax & (BodyPartID.bodyIdMax << i24);
        iArr3[0] = (int) ((j3 >>> (32 - i5)) ^ iArr3[0]);
        int i25 = i23 - i6;
        int i26 = i25 - 1;
        if (i26 >= 0) {
            iArr3[i26] = iArr3[i26] ^ ((int) (j3 << i7));
        }
        iArr3[i25] = (int) (iArr3[i25] ^ (j3 >>> (32 - i7)));
        int i27 = i23 - i8;
        int i28 = i27 - 1;
        if (i28 >= 0) {
            iArr3[i28] = iArr3[i28] ^ ((int) (j3 << i9));
        }
        iArr3[i27] = (int) (iArr3[i27] ^ (j3 >>> (32 - i9)));
        int i29 = i23 - i10;
        int i30 = i29 - 1;
        if (i30 >= 0) {
            iArr3[i30] = iArr3[i30] ^ ((int) (j3 << i11));
        }
        iArr3[i29] = (int) ((j3 >>> (32 - i11)) ^ iArr3[i29]);
        iArr3[i23] = iArr3[i23] & reverseRightMask[i24];
        this.blocks = ((i2 - 1) >>> 5) + 1;
        this.len = i2;
    }

    public GF2Polynomial add(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void addToThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.len);
        xorThisBy(gF2Polynomial);
    }

    public void assignAll() {
        for (int i2 = 0; i2 < this.blocks; i2++) {
            this.value[i2] = -1;
        }
        zeroUnusedBits();
    }

    public void assignOne() {
        for (int i2 = 1; i2 < this.blocks; i2++) {
            this.value[i2] = 0;
        }
        this.value[0] = 1;
    }

    public void assignX() {
        for (int i2 = 1; i2 < this.blocks; i2++) {
            this.value[i2] = 0;
        }
        this.value[0] = 2;
    }

    public void assignZero() {
        for (int i2 = 0; i2 < this.blocks; i2++) {
            this.value[i2] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(int i2, int i3) {
        int i4 = i2 >>> 5;
        int i5 = i2 & 31;
        int i6 = 32 - i5;
        int i7 = i2 - i3;
        int i8 = i7 >>> 5;
        int i9 = 32 - (i7 & 31);
        int i10 = ((i2 << 1) - 2) >>> 5;
        while (i10 > i4) {
            int[] iArr = this.value;
            long j2 = BodyPartID.bodyIdMax & iArr[i10];
            int i11 = i10 - i4;
            int i12 = i11 - 1;
            int i13 = i4;
            iArr[i12] = ((int) (j2 << i6)) ^ iArr[i12];
            iArr[i11] = (int) (iArr[i11] ^ (j2 >>> (32 - i6)));
            int i14 = i10 - i8;
            int i15 = i14 - 1;
            iArr[i15] = iArr[i15] ^ ((int) (j2 << i9));
            iArr[i14] = (int) ((j2 >>> (32 - i9)) ^ iArr[i14]);
            iArr[i10] = 0;
            i10--;
            i4 = i13;
        }
        int i16 = i4;
        int[] iArr2 = this.value;
        long j3 = (BodyPartID.bodyIdMax << i5) & iArr2[i16] & BodyPartID.bodyIdMax;
        iArr2[0] = (int) (iArr2[0] ^ (j3 >>> (32 - i6)));
        int i17 = i16 - i8;
        int i18 = i17 - 1;
        if (i18 >= 0) {
            iArr2[i18] = iArr2[i18] ^ ((int) (j3 << i9));
        }
        iArr2[i17] = (int) ((j3 >>> (32 - i9)) ^ iArr2[i17]);
        iArr2[i16] = iArr2[i16] & reverseRightMask[i5];
        this.blocks = ((i2 - 1) >>> 5) + 1;
        this.len = i2;
    }

    void c() {
        int i2 = this.blocks + 1;
        this.blocks = i2;
        this.len += 32;
        int[] iArr = this.value;
        if (i2 > iArr.length) {
            int[] iArr2 = new int[i2];
            System.arraycopy(iArr, 0, iArr2, 1, i2 - 1);
            this.value = null;
            this.value = iArr2;
            return;
        }
        for (int i3 = i2 - 1; i3 >= 1; i3--) {
            int[] iArr3 = this.value;
            iArr3[i3] = iArr3[i3 - 1];
        }
        this.value[0] = 0;
    }

    public Object clone() {
        return new GF2Polynomial(this);
    }

    public GF2Polynomial[] divide(GF2Polynomial gF2Polynomial) {
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[2];
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int i2 = gF2Polynomial3.len;
        int i3 = gF2Polynomial4.len;
        if (i2 < i3) {
            gF2PolynomialArr[0] = new GF2Polynomial(0);
            gF2PolynomialArr[1] = gF2Polynomial3;
            return gF2PolynomialArr;
        }
        int i4 = i2 - i3;
        gF2Polynomial2.expandN(i4 + 1);
        while (i4 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(i4));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(i4);
            i4 = gF2Polynomial3.len - gF2Polynomial4.len;
        }
        gF2PolynomialArr[0] = gF2Polynomial2;
        gF2PolynomialArr[1] = gF2Polynomial3;
        return gF2PolynomialArr;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2Polynomial)) {
            return false;
        }
        GF2Polynomial gF2Polynomial = (GF2Polynomial) obj;
        if (this.len != gF2Polynomial.len) {
            return false;
        }
        for (int i2 = 0; i2 < this.blocks; i2++) {
            if (this.value[i2] != gF2Polynomial.value[i2]) {
                return false;
            }
        }
        return true;
    }

    public void expandN(int i2) {
        if (this.len >= i2) {
            return;
        }
        this.len = i2;
        int i3 = ((i2 - 1) >>> 5) + 1;
        int i4 = this.blocks;
        if (i4 >= i3) {
            return;
        }
        int[] iArr = this.value;
        if (iArr.length >= i3) {
            while (i4 < i3) {
                this.value[i4] = 0;
                i4++;
            }
            this.blocks = i3;
            return;
        }
        int[] iArr2 = new int[i3];
        System.arraycopy(iArr, 0, iArr2, 0, i4);
        this.blocks = i3;
        this.value = null;
        this.value = iArr2;
    }

    public GF2Polynomial gcd(GF2Polynomial gF2Polynomial) {
        if (isZero() && gF2Polynomial.isZero()) {
            throw new ArithmeticException("Both operands of gcd equal zero.");
        }
        if (isZero()) {
            return new GF2Polynomial(gF2Polynomial);
        }
        if (gF2Polynomial.isZero()) {
            return new GF2Polynomial(this);
        }
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        GF2Polynomial gF2Polynomial4 = gF2Polynomial2;
        GF2Polynomial gF2Polynomial5 = gF2Polynomial3;
        while (!gF2Polynomial5.isZero()) {
            GF2Polynomial gF2Polynomial6 = gF2Polynomial5;
            gF2Polynomial5 = gF2Polynomial4.remainder(gF2Polynomial5);
            gF2Polynomial4 = gF2Polynomial6;
        }
        return gF2Polynomial4;
    }

    public int getBit(int i2) {
        if (i2 >= 0) {
            if (i2 > this.len - 1) {
                return 0;
            }
            return (bitMask[i2 & 31] & this.value[i2 >>> 5]) != 0 ? 1 : 0;
        }
        throw new RuntimeException();
    }

    public int getLength() {
        return this.len;
    }

    public int hashCode() {
        return this.len + Arrays.hashCode(this.value);
    }

    public GF2Polynomial increase() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.increaseThis();
        return gF2Polynomial;
    }

    public void increaseThis() {
        xorBit(0);
    }

    public boolean isIrreducible() {
        if (isZero()) {
            return false;
        }
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this);
        gF2Polynomial.reduceN();
        int i2 = gF2Polynomial.len;
        int i3 = i2 - 1;
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(i2, "X");
        for (int i4 = 1; i4 <= (i3 >> 1); i4++) {
            gF2Polynomial2.squareThisPreCalc();
            gF2Polynomial2 = gF2Polynomial2.remainder(gF2Polynomial);
            GF2Polynomial add = gF2Polynomial2.add(new GF2Polynomial(32, "X"));
            if (add.isZero() || !gF2Polynomial.gcd(add).isOne()) {
                return false;
            }
        }
        return true;
    }

    public boolean isOne() {
        for (int i2 = 1; i2 < this.blocks; i2++) {
            if (this.value[i2] != 0) {
                return false;
            }
        }
        return this.value[0] == 1;
    }

    public boolean isZero() {
        if (this.len == 0) {
            return true;
        }
        for (int i2 = 0; i2 < this.blocks; i2++) {
            if (this.value[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    public GF2Polynomial multiply(GF2Polynomial gF2Polynomial) {
        int max = Math.max(this.len, gF2Polynomial.len);
        expandN(max);
        gF2Polynomial.expandN(max);
        return karaMult(gF2Polynomial);
    }

    public GF2Polynomial multiplyClassic(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(Math.max(this.len, gF2Polynomial.len) << 1);
        GF2Polynomial[] gF2PolynomialArr = new GF2Polynomial[32];
        gF2PolynomialArr[0] = new GF2Polynomial(this);
        for (int i2 = 1; i2 <= 31; i2++) {
            gF2PolynomialArr[i2] = gF2PolynomialArr[i2 - 1].shiftLeft();
        }
        for (int i3 = 0; i3 < gF2Polynomial.blocks; i3++) {
            for (int i4 = 0; i4 <= 31; i4++) {
                if ((gF2Polynomial.value[i3] & bitMask[i4]) != 0) {
                    gF2Polynomial2.xorThisBy(gF2PolynomialArr[i4]);
                }
            }
            for (int i5 = 0; i5 <= 31; i5++) {
                gF2PolynomialArr[i5].c();
            }
        }
        return gF2Polynomial2;
    }

    public GF2Polynomial quotient(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this.len);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial4 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial4.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial3.reduceN();
        gF2Polynomial4.reduceN();
        int i2 = gF2Polynomial3.len;
        int i3 = gF2Polynomial4.len;
        if (i2 < i3) {
            return new GF2Polynomial(0);
        }
        int i4 = i2 - i3;
        gF2Polynomial2.expandN(i4 + 1);
        while (i4 >= 0) {
            gF2Polynomial3.subtractFromThis(gF2Polynomial4.shiftLeft(i4));
            gF2Polynomial3.reduceN();
            gF2Polynomial2.xorBit(i4);
            i4 = gF2Polynomial3.len - gF2Polynomial4.len;
        }
        return gF2Polynomial2;
    }

    public void randomize() {
        for (int i2 = 0; i2 < this.blocks; i2++) {
            this.value[i2] = rand.nextInt();
        }
        zeroUnusedBits();
    }

    public void randomize(Random random) {
        for (int i2 = 0; i2 < this.blocks; i2++) {
            this.value[i2] = random.nextInt();
        }
        zeroUnusedBits();
    }

    public void reduceN() {
        int[] iArr;
        int i2 = this.blocks;
        while (true) {
            i2--;
            iArr = this.value;
            if (iArr[i2] != 0 || i2 <= 0) {
                break;
            }
        }
        int i3 = iArr[i2];
        int i4 = 0;
        while (i3 != 0) {
            i3 >>>= 1;
            i4++;
        }
        this.len = (i2 << 5) + i4;
        this.blocks = i2 + 1;
    }

    public GF2Polynomial remainder(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2 = new GF2Polynomial(this);
        GF2Polynomial gF2Polynomial3 = new GF2Polynomial(gF2Polynomial);
        if (gF2Polynomial3.isZero()) {
            throw new RuntimeException();
        }
        gF2Polynomial2.reduceN();
        gF2Polynomial3.reduceN();
        int i2 = gF2Polynomial2.len;
        int i3 = gF2Polynomial3.len;
        if (i2 < i3) {
            return gF2Polynomial2;
        }
        while (true) {
            int i4 = i2 - i3;
            if (i4 < 0) {
                return gF2Polynomial2;
            }
            gF2Polynomial2.subtractFromThis(gF2Polynomial3.shiftLeft(i4));
            gF2Polynomial2.reduceN();
            i2 = gF2Polynomial2.len;
            i3 = gF2Polynomial3.len;
        }
    }

    public void resetBit(int i2) {
        if (i2 < 0) {
            throw new RuntimeException();
        }
        if (i2 > this.len - 1) {
            return;
        }
        int[] iArr = this.value;
        int i3 = i2 >>> 5;
        iArr[i3] = (~bitMask[i2 & 31]) & iArr[i3];
    }

    public void setBit(int i2) {
        if (i2 < 0 || i2 > this.len - 1) {
            throw new RuntimeException();
        }
        int[] iArr = this.value;
        int i3 = i2 >>> 5;
        iArr[i3] = bitMask[i2 & 31] | iArr[i3];
    }

    public GF2Polynomial shiftLeft() {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len + 1, this.value);
        for (int i2 = gF2Polynomial.blocks - 1; i2 >= 1; i2--) {
            int[] iArr = gF2Polynomial.value;
            iArr[i2] = iArr[i2] << 1;
            iArr[i2] = iArr[i2] | (iArr[i2 - 1] >>> 31);
        }
        int[] iArr2 = gF2Polynomial.value;
        iArr2[0] = iArr2[0] << 1;
        return gF2Polynomial;
    }

    public GF2Polynomial shiftLeft(int i2) {
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len + i2, this.value);
        if (i2 >= 32) {
            gF2Polynomial.doShiftBlocksLeft(i2 >>> 5);
        }
        int i3 = i2 & 31;
        if (i3 != 0) {
            for (int i4 = gF2Polynomial.blocks - 1; i4 >= 1; i4--) {
                int[] iArr = gF2Polynomial.value;
                iArr[i4] = iArr[i4] << i3;
                iArr[i4] = iArr[i4] | (iArr[i4 - 1] >>> (32 - i3));
            }
            int[] iArr2 = gF2Polynomial.value;
            iArr2[0] = iArr2[0] << i3;
        }
        return gF2Polynomial;
    }

    public void shiftLeftAddThis(GF2Polynomial gF2Polynomial, int i2) {
        int i3;
        if (i2 == 0) {
            addToThis(gF2Polynomial);
            return;
        }
        expandN(gF2Polynomial.len + i2);
        int i4 = i2 >>> 5;
        for (int i5 = gF2Polynomial.blocks - 1; i5 >= 0; i5--) {
            int i6 = i5 + i4;
            int i7 = i6 + 1;
            if (i7 < this.blocks && (i3 = i2 & 31) != 0) {
                int[] iArr = this.value;
                iArr[i7] = (gF2Polynomial.value[i5] >>> (32 - i3)) ^ iArr[i7];
            }
            int[] iArr2 = this.value;
            iArr2[i6] = iArr2[i6] ^ (gF2Polynomial.value[i5] << (i2 & 31));
        }
    }

    public void shiftLeftThis() {
        int i2 = this.len;
        int i3 = i2 & 31;
        this.len = i2 + 1;
        int i4 = this.blocks;
        if (i3 != 0) {
            for (int i5 = i4 - 1; i5 >= 1; i5--) {
                int[] iArr = this.value;
                iArr[i5] = iArr[i5] << 1;
                iArr[i5] = iArr[i5] | (iArr[i5 - 1] >>> 31);
            }
            int[] iArr2 = this.value;
            iArr2[0] = iArr2[0] << 1;
            return;
        }
        int i6 = i4 + 1;
        this.blocks = i6;
        int[] iArr3 = this.value;
        if (i6 > iArr3.length) {
            int[] iArr4 = new int[i6];
            System.arraycopy(iArr3, 0, iArr4, 0, iArr3.length);
            this.value = null;
            this.value = iArr4;
        }
        for (int i7 = this.blocks - 1; i7 >= 1; i7--) {
            int[] iArr5 = this.value;
            int i8 = i7 - 1;
            iArr5[i7] = iArr5[i7] | (iArr5[i8] >>> 31);
            iArr5[i8] = iArr5[i8] << 1;
        }
    }

    public GF2Polynomial shiftRight() {
        int i2;
        GF2Polynomial gF2Polynomial = new GF2Polynomial(this.len - 1);
        int i3 = 0;
        System.arraycopy(this.value, 0, gF2Polynomial.value, 0, gF2Polynomial.blocks);
        while (true) {
            i2 = gF2Polynomial.blocks;
            if (i3 > i2 - 2) {
                break;
            }
            int[] iArr = gF2Polynomial.value;
            iArr[i3] = iArr[i3] >>> 1;
            int i4 = i3 + 1;
            iArr[i3] = iArr[i3] | (iArr[i4] << 31);
            i3 = i4;
        }
        int[] iArr2 = gF2Polynomial.value;
        int i5 = i2 - 1;
        iArr2[i5] = iArr2[i5] >>> 1;
        if (i2 < this.blocks) {
            int i6 = i2 - 1;
            iArr2[i6] = (this.value[i2] << 31) | iArr2[i6];
        }
        return gF2Polynomial;
    }

    public void shiftRightThis() {
        int i2;
        int i3 = this.len - 1;
        this.len = i3;
        this.blocks = ((i3 - 1) >>> 5) + 1;
        int i4 = 0;
        while (true) {
            i2 = this.blocks;
            if (i4 > i2 - 2) {
                break;
            }
            int[] iArr = this.value;
            iArr[i4] = iArr[i4] >>> 1;
            int i5 = i4 + 1;
            iArr[i4] = iArr[i4] | (iArr[i5] << 31);
            i4 = i5;
        }
        int[] iArr2 = this.value;
        int i6 = i2 - 1;
        iArr2[i6] = iArr2[i6] >>> 1;
        if ((this.len & 31) == 0) {
            int i7 = i2 - 1;
            iArr2[i7] = (iArr2[i2] << 31) | iArr2[i7];
        }
    }

    public void squareThisBitwise() {
        if (isZero()) {
            return;
        }
        int i2 = this.blocks;
        int i3 = i2 << 1;
        int[] iArr = new int[i3];
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            int i5 = this.value[i4];
            int i6 = 1;
            for (int i7 = 0; i7 < 16; i7++) {
                if ((i5 & 1) != 0) {
                    int i8 = i4 << 1;
                    iArr[i8] = iArr[i8] | i6;
                }
                if ((65536 & i5) != 0) {
                    int i9 = (i4 << 1) + 1;
                    iArr[i9] = iArr[i9] | i6;
                }
                i6 <<= 2;
                i5 >>>= 1;
            }
        }
        this.value = null;
        this.value = iArr;
        this.blocks = i3;
        this.len = (this.len << 1) - 1;
    }

    public void squareThisPreCalc() {
        int i2;
        int i3;
        if (isZero()) {
            return;
        }
        int length = this.value.length;
        int i4 = this.blocks;
        if (length >= (i4 << 1)) {
            for (int i5 = i4 - 1; i5 >= 0; i5--) {
                int[] iArr = this.value;
                int i6 = i5 << 1;
                short[] sArr = squaringTable;
                iArr[i6 + 1] = sArr[(iArr[i5] & 16711680) >>> 16] | (sArr[(iArr[i5] & ViewCompat.MEASURED_STATE_MASK) >>> 24] << 16);
                iArr[i6] = sArr[iArr[i5] & 255] | (sArr[(iArr[i5] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8] << 16);
            }
            i3 = this.blocks << 1;
        } else {
            int[] iArr2 = new int[i4 << 1];
            int i7 = 0;
            while (true) {
                i2 = this.blocks;
                if (i7 >= i2) {
                    break;
                }
                int i8 = i7 << 1;
                short[] sArr2 = squaringTable;
                int[] iArr3 = this.value;
                iArr2[i8] = sArr2[iArr3[i7] & 255] | (sArr2[(iArr3[i7] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8] << 16);
                iArr2[i8 + 1] = (sArr2[(iArr3[i7] & ViewCompat.MEASURED_STATE_MASK) >>> 24] << 16) | sArr2[(iArr3[i7] & 16711680) >>> 16];
                i7++;
            }
            this.value = null;
            this.value = iArr2;
            i3 = i2 << 1;
        }
        this.blocks = i3;
        this.len = (this.len << 1) - 1;
    }

    public GF2Polynomial subtract(GF2Polynomial gF2Polynomial) {
        return xor(gF2Polynomial);
    }

    public void subtractFromThis(GF2Polynomial gF2Polynomial) {
        expandN(gF2Polynomial.len);
        xorThisBy(gF2Polynomial);
    }

    public boolean testBit(int i2) {
        if (i2 >= 0) {
            if (i2 > this.len - 1) {
                return false;
            }
            return (bitMask[i2 & 31] & this.value[i2 >>> 5]) != 0;
        }
        throw new RuntimeException();
    }

    public byte[] toByteArray() {
        int i2 = ((this.len - 1) >> 3) + 1;
        int i3 = i2 & 3;
        byte[] bArr = new byte[i2];
        for (int i4 = 0; i4 < (i2 >> 2); i4++) {
            int i5 = (i2 - (i4 << 2)) - 1;
            int[] iArr = this.value;
            bArr[i5] = (byte) (255 & iArr[i4]);
            bArr[i5 - 1] = (byte) ((iArr[i4] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8);
            bArr[i5 - 2] = (byte) ((iArr[i4] & 16711680) >>> 16);
            bArr[i5 - 3] = (byte) ((iArr[i4] & ViewCompat.MEASURED_STATE_MASK) >>> 24);
        }
        for (int i6 = 0; i6 < i3; i6++) {
            int i7 = ((i3 - i6) - 1) << 3;
            bArr[i6] = (byte) ((this.value[this.blocks - 1] & (255 << i7)) >>> i7);
        }
        return bArr;
    }

    public BigInteger toFlexiBigInt() {
        return (this.len == 0 || isZero()) ? new BigInteger(0, new byte[0]) : new BigInteger(1, toByteArray());
    }

    public int[] toIntegerArray() {
        int i2 = this.blocks;
        int[] iArr = new int[i2];
        System.arraycopy(this.value, 0, iArr, 0, i2);
        return iArr;
    }

    public String toString(int i2) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        String[] strArr = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        String str = new String();
        if (i2 == 16) {
            for (int i3 = this.blocks - 1; i3 >= 0; i3--) {
                str = ((((((((str + cArr[(this.value[i3] >>> 28) & 15]) + cArr[(this.value[i3] >>> 24) & 15]) + cArr[(this.value[i3] >>> 20) & 15]) + cArr[(this.value[i3] >>> 16) & 15]) + cArr[(this.value[i3] >>> 12) & 15]) + cArr[(this.value[i3] >>> 8) & 15]) + cArr[(this.value[i3] >>> 4) & 15]) + cArr[this.value[i3] & 15]) + " ";
            }
        } else {
            for (int i4 = this.blocks - 1; i4 >= 0; i4--) {
                str = ((((((((str + strArr[(this.value[i4] >>> 28) & 15]) + strArr[(this.value[i4] >>> 24) & 15]) + strArr[(this.value[i4] >>> 20) & 15]) + strArr[(this.value[i4] >>> 16) & 15]) + strArr[(this.value[i4] >>> 12) & 15]) + strArr[(this.value[i4] >>> 8) & 15]) + strArr[(this.value[i4] >>> 4) & 15]) + strArr[this.value[i4] & 15]) + " ";
            }
        }
        return str;
    }

    public boolean vectorMult(GF2Polynomial gF2Polynomial) {
        if (this.len == gF2Polynomial.len) {
            boolean z = false;
            for (int i2 = 0; i2 < this.blocks; i2++) {
                int i3 = this.value[i2] & gF2Polynomial.value[i2];
                boolean[] zArr = parity;
                z = (((z ^ zArr[i3 & 255]) ^ zArr[(i3 >>> 8) & 255]) ^ zArr[(i3 >>> 16) & 255]) ^ zArr[(i3 >>> 24) & 255];
            }
            return z;
        }
        throw new RuntimeException();
    }

    public GF2Polynomial xor(GF2Polynomial gF2Polynomial) {
        GF2Polynomial gF2Polynomial2;
        int min = Math.min(this.blocks, gF2Polynomial.blocks);
        int i2 = 0;
        if (this.len >= gF2Polynomial.len) {
            gF2Polynomial2 = new GF2Polynomial(this);
            while (i2 < min) {
                int[] iArr = gF2Polynomial2.value;
                iArr[i2] = iArr[i2] ^ gF2Polynomial.value[i2];
                i2++;
            }
        } else {
            gF2Polynomial2 = new GF2Polynomial(gF2Polynomial);
            while (i2 < min) {
                int[] iArr2 = gF2Polynomial2.value;
                iArr2[i2] = iArr2[i2] ^ this.value[i2];
                i2++;
            }
        }
        gF2Polynomial2.zeroUnusedBits();
        return gF2Polynomial2;
    }

    public void xorBit(int i2) {
        if (i2 < 0 || i2 > this.len - 1) {
            throw new RuntimeException();
        }
        int[] iArr = this.value;
        int i3 = i2 >>> 5;
        iArr[i3] = bitMask[i2 & 31] ^ iArr[i3];
    }

    public void xorThisBy(GF2Polynomial gF2Polynomial) {
        for (int i2 = 0; i2 < Math.min(this.blocks, gF2Polynomial.blocks); i2++) {
            int[] iArr = this.value;
            iArr[i2] = iArr[i2] ^ gF2Polynomial.value[i2];
        }
        zeroUnusedBits();
    }
}
