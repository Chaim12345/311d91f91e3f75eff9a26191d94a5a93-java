package org.bouncycastle.pqc.crypto.sphincs;

import org.bouncycastle.crypto.engines.ChaChaEngine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.pqc.crypto.sphincs.Tree;
import org.bouncycastle.util.Pack;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class Seed {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(HashFunctions hashFunctions, byte[] bArr, int i2, byte[] bArr2, Tree.leafaddr leafaddrVar) {
        byte[] bArr3 = new byte[40];
        for (int i3 = 0; i3 < 32; i3++) {
            bArr3[i3] = bArr2[i3];
        }
        Pack.longToLittleEndian((leafaddrVar.f14570c << 59) | leafaddrVar.f14568a | (leafaddrVar.f14569b << 4), bArr3, 32);
        hashFunctions.f(bArr, i2, bArr3, 40);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(byte[] bArr, int i2, long j2, byte[] bArr2, int i3) {
        ChaChaEngine chaChaEngine = new ChaChaEngine(12);
        chaChaEngine.init(true, new ParametersWithIV(new KeyParameter(bArr2, i3, 32), new byte[8]));
        chaChaEngine.processBytes(bArr, i2, (int) j2, bArr, i2);
    }
}
