package com.google.android.play.core.assetpacks;

import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
final class zzbr {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(byte[] bArr, int i2) {
        return ((bArr[i2 + 1] & 255) << 8) | (bArr[i2] & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(byte[] bArr, int i2) {
        return (bArr[i2 + 3] & 255) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2 + 2] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long c(byte[] bArr, int i2) {
        return ((a(bArr, i2 + 2) << 16) | a(bArr, i2)) & BodyPartID.bodyIdMax;
    }
}
