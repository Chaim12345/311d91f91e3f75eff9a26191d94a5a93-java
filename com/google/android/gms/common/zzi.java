package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class zzi extends com.google.android.gms.common.internal.zzy {
    private final int zza;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzi(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 25);
        this.zza = Arrays.hashCode(bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static byte[] b(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e2) {
            throw new AssertionError(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract byte[] c();

    public final boolean equals(@Nullable Object obj) {
        IObjectWrapper zzd;
        if (obj != null && (obj instanceof com.google.android.gms.common.internal.zzz)) {
            try {
                com.google.android.gms.common.internal.zzz zzzVar = (com.google.android.gms.common.internal.zzz) obj;
                if (zzzVar.zzc() == this.zza && (zzd = zzzVar.zzd()) != null) {
                    return Arrays.equals(c(), (byte[]) ObjectWrapper.unwrap(zzd));
                }
                return false;
            } catch (RemoteException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
            }
        }
        return false;
    }

    public final int hashCode() {
        return this.zza;
    }

    @Override // com.google.android.gms.common.internal.zzz
    public final int zzc() {
        return this.zza;
    }

    @Override // com.google.android.gms.common.internal.zzz
    public final IObjectWrapper zzd() {
        return ObjectWrapper.wrap(c());
    }
}
