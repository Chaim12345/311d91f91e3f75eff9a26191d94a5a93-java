package com.google.android.libraries.places.internal;

import com.google.android.datatransport.Transformer;
import java.io.IOException;
/* loaded from: classes2.dex */
public final /* synthetic */ class zzew implements Transformer {
    public static final /* synthetic */ zzew zza = new zzew();

    private /* synthetic */ zzew() {
    }

    @Override // com.google.android.datatransport.Transformer
    public final Object apply(Object obj) {
        zzlg zzlgVar = (zzlg) obj;
        try {
            byte[] bArr = new byte[zzlgVar.zzv()];
            zzacx zzC = zzacx.zzC(bArr);
            zzlgVar.zzH(zzC);
            zzC.zzD();
            return bArr;
        } catch (IOException e2) {
            String name = zzlgVar.getClass().getName();
            StringBuilder sb = new StringBuilder(name.length() + 72);
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a byte array threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e2);
        }
    }
}
