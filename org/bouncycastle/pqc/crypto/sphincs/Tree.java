package org.bouncycastle.pqc.crypto.sphincs;
/* loaded from: classes4.dex */
class Tree {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class leafaddr {

        /* renamed from: a  reason: collision with root package name */
        int f14568a;

        /* renamed from: b  reason: collision with root package name */
        long f14569b;

        /* renamed from: c  reason: collision with root package name */
        long f14570c;

        public leafaddr() {
        }

        public leafaddr(leafaddr leafaddrVar) {
            this.f14568a = leafaddrVar.f14568a;
            this.f14569b = leafaddrVar.f14569b;
            this.f14570c = leafaddrVar.f14570c;
        }
    }

    static void a(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, leafaddr leafaddrVar) {
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[2144];
        Wots wots = new Wots();
        Seed.a(hashFunctions, bArr4, 0, bArr3, leafaddrVar);
        wots.c(hashFunctions, bArr5, 0, bArr4, 0, bArr2, i3);
        b(hashFunctions, bArr, i2, bArr5, 0, bArr2, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, int i3, byte[] bArr3, int i4) {
        int i5;
        int i6 = 67;
        for (int i7 = 0; i7 < 7; i7++) {
            int i8 = 0;
            while (true) {
                i5 = i6 >>> 1;
                if (i8 >= i5) {
                    break;
                }
                hashFunctions.c(bArr2, i3 + (i8 * 32), bArr2, i3 + (i8 * 2 * 32), bArr3, i4 + (i7 * 2 * 32));
                i8++;
            }
            if ((i6 & 1) != 0) {
                System.arraycopy(bArr2, i3 + ((i6 - 1) * 32), bArr2, (i5 * 32) + i3, 32);
                i5++;
            }
            i6 = i5;
        }
        System.arraycopy(bArr2, i3, bArr, i2, 32);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(HashFunctions hashFunctions, byte[] bArr, int i2, int i3, byte[] bArr2, leafaddr leafaddrVar, byte[] bArr3, int i4) {
        leafaddr leafaddrVar2 = new leafaddr(leafaddrVar);
        int i5 = i3 + 1;
        byte[] bArr4 = new byte[i5 * 32];
        int[] iArr = new int[i5];
        int i6 = 1;
        int i7 = (int) (leafaddrVar2.f14570c + (1 << i3));
        int i8 = 0;
        while (true) {
            int i9 = 32;
            if (leafaddrVar2.f14570c >= i7) {
                break;
            }
            a(hashFunctions, bArr4, i8 * 32, bArr3, i4, bArr2, leafaddrVar2);
            iArr[i8] = 0;
            int i10 = i8 + i6;
            while (i10 > i6) {
                int i11 = i10 - 1;
                int i12 = i10 - 2;
                if (iArr[i11] == iArr[i12]) {
                    int i13 = i12 * 32;
                    int i14 = i6;
                    int[] iArr2 = iArr;
                    hashFunctions.c(bArr4, i13, bArr4, i13, bArr3, i4 + ((iArr[i11] + 7) * 2 * i9));
                    iArr2[i12] = iArr2[i12] + i14;
                    i10--;
                    i6 = i14;
                    i9 = i9;
                    i7 = i7;
                    iArr = iArr2;
                }
            }
            leafaddrVar2.f14570c++;
            i8 = i10;
            i6 = i6;
            i7 = i7;
            iArr = iArr;
        }
        for (int i15 = 0; i15 < 32; i15++) {
            bArr[i2 + i15] = bArr4[i15];
        }
    }
}
