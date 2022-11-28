package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import okhttp3.HttpUrl;
/* loaded from: classes2.dex */
public final class zzev {

    /* renamed from: a  reason: collision with root package name */
    protected static final AtomicReference f6718a = new AtomicReference();

    /* renamed from: b  reason: collision with root package name */
    protected static final AtomicReference f6719b = new AtomicReference();

    /* renamed from: c  reason: collision with root package name */
    protected static final AtomicReference f6720c = new AtomicReference();
    private final zzeu zzd;

    public zzev(zzeu zzeuVar) {
        this.zzd = zzeuVar;
    }

    private static final String zzg(String str, String[] strArr, String[] strArr2, AtomicReference atomicReference) {
        String str2;
        Preconditions.checkNotNull(strArr);
        Preconditions.checkNotNull(strArr2);
        Preconditions.checkNotNull(atomicReference);
        Preconditions.checkArgument(strArr.length == strArr2.length);
        for (int i2 = 0; i2 < strArr.length; i2++) {
            String str3 = strArr[i2];
            if (str == str3 || str.equals(str3)) {
                synchronized (atomicReference) {
                    String[] strArr3 = (String[]) atomicReference.get();
                    if (strArr3 == null) {
                        strArr3 = new String[strArr2.length];
                        atomicReference.set(strArr3);
                    }
                    str2 = strArr3[i2];
                    if (str2 == null) {
                        str2 = strArr2[i2] + "(" + strArr[i2] + ")";
                        strArr3[i2] = str2;
                    }
                }
                return str2;
            }
        }
        return str;
    }

    protected final String a(Object[] objArr) {
        if (objArr == null) {
            return HttpUrl.PATH_SEGMENT_ENCODE_SET_URI;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objArr) {
            String b2 = obj instanceof Bundle ? b((Bundle) obj) : String.valueOf(obj);
            if (b2 != null) {
                if (sb.length() != 1) {
                    sb.append(", ");
                }
                sb.append(b2);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String b(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (this.zzd.zza()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Bundle[{");
            for (String str : bundle.keySet()) {
                if (sb.length() != 8) {
                    sb.append(", ");
                }
                sb.append(e(str));
                sb.append("=");
                Object obj = bundle.get(str);
                sb.append(obj instanceof Bundle ? a(new Object[]{obj}) : obj instanceof Object[] ? a((Object[]) obj) : obj instanceof ArrayList ? a(((ArrayList) obj).toArray()) : String.valueOf(obj));
            }
            sb.append("}]");
            return sb.toString();
        }
        return bundle.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String c(zzaw zzawVar) {
        if (this.zzd.zza()) {
            StringBuilder sb = new StringBuilder();
            sb.append("origin=");
            sb.append(zzawVar.zzc);
            sb.append(",name=");
            sb.append(d(zzawVar.zza));
            sb.append(",params=");
            zzau zzauVar = zzawVar.zzb;
            sb.append(zzauVar == null ? null : !this.zzd.zza() ? zzauVar.toString() : b(zzauVar.zzc()));
            return sb.toString();
        }
        return zzawVar.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String d(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zzg(str, zzhh.zzc, zzhh.zza, f6718a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String e(String str) {
        if (str == null) {
            return null;
        }
        return !this.zzd.zza() ? str : zzg(str, zzhi.zzb, zzhi.zza, f6719b);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String f(String str) {
        if (str == null) {
            return null;
        }
        if (this.zzd.zza()) {
            if (str.startsWith("_exp_")) {
                return "experiment_id(" + str + ")";
            }
            return zzg(str, zzhj.zzb, zzhj.zza, f6720c);
        }
        return str;
    }
}
