package com.google.android.gms.common.logging;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
@KeepForSdk
/* loaded from: classes.dex */
public class Logger {
    private final String zza;
    private final String zzb;
    private final GmsLogger zzc;
    private final int zzd;

    @KeepForSdk
    public Logger(@NonNull String str, @NonNull String... strArr) {
        String sb;
        if (strArr.length == 0) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(AbstractJsonLexerKt.BEGIN_LIST);
            for (String str2 : strArr) {
                if (sb2.length() > 1) {
                    sb2.append(",");
                }
                sb2.append(str2);
            }
            sb2.append("] ");
            sb = sb2.toString();
        }
        this.zzb = sb;
        this.zza = str;
        this.zzc = new GmsLogger(str);
        int i2 = 2;
        while (i2 <= 7 && !Log.isLoggable(this.zza, i2)) {
            i2++;
        }
        this.zzd = i2;
    }

    @NonNull
    @KeepForSdk
    protected String a(@NonNull String str, @Nullable Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzb.concat(str);
    }

    @KeepForSdk
    public void d(@NonNull String str, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            a(str, objArr);
        }
    }

    @KeepForSdk
    public void e(@NonNull String str, @NonNull Throwable th, @Nullable Object... objArr) {
        Log.e(this.zza, a(str, objArr), th);
    }

    @KeepForSdk
    public void e(@NonNull String str, @Nullable Object... objArr) {
        Log.e(this.zza, a(str, objArr));
    }

    @NonNull
    @KeepForSdk
    public String getTag() {
        return this.zza;
    }

    @KeepForSdk
    public void i(@NonNull String str, @Nullable Object... objArr) {
        a(str, objArr);
    }

    @KeepForSdk
    public boolean isLoggable(int i2) {
        return this.zzd <= i2;
    }

    @KeepForSdk
    public void v(@NonNull String str, @NonNull Throwable th, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            a(str, objArr);
        }
    }

    @KeepForSdk
    public void v(@NonNull String str, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            a(str, objArr);
        }
    }

    @KeepForSdk
    public void w(@NonNull String str, @Nullable Object... objArr) {
        a(str, objArr);
    }

    @KeepForSdk
    public void wtf(@NonNull String str, @NonNull Throwable th, @Nullable Object... objArr) {
        Log.wtf(this.zza, a(str, objArr), th);
    }

    @KeepForSdk
    public void wtf(@NonNull Throwable th) {
        Log.wtf(this.zza, th);
    }
}
