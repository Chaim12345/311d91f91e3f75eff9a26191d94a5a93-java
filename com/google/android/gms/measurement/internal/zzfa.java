package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import org.apache.commons.cli.HelpFormatter;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
/* loaded from: classes2.dex */
public final class zzfa extends zzhe {
    private char zza;
    private long zzb;
    @GuardedBy("this")
    private String zzc;
    private final zzey zzd;
    private final zzey zze;
    private final zzey zzf;
    private final zzey zzg;
    private final zzey zzh;
    private final zzey zzi;
    private final zzey zzj;
    private final zzey zzk;
    private final zzey zzl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfa(zzgk zzgkVar) {
        super(zzgkVar);
        this.zza = (char) 0;
        this.zzb = -1L;
        this.zzd = new zzey(this, 6, false, false);
        this.zze = new zzey(this, 6, true, false);
        this.zzf = new zzey(this, 6, false, true);
        this.zzg = new zzey(this, 5, false, false);
        this.zzh = new zzey(this, 5, true, false);
        this.zzi = new zzey(this, 5, false, true);
        this.zzj = new zzey(this, 4, false, false);
        this.zzk = new zzey(this, 3, false, false);
        this.zzl = new zzey(this, 2, false, false);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object g(String str) {
        if (str == null) {
            return null;
        }
        return new zzez(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h(boolean z, String str, Object obj, Object obj2, Object obj3) {
        String str2 = "";
        if (str == null) {
            str = "";
        }
        String i2 = i(z, obj);
        String i3 = i(z, obj2);
        String i4 = i(z, obj3);
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        String str3 = ", ";
        if (!TextUtils.isEmpty(i2)) {
            sb.append(str2);
            sb.append(i2);
            str2 = ", ";
        }
        if (TextUtils.isEmpty(i3)) {
            str3 = str2;
        } else {
            sb.append(str2);
            sb.append(i3);
        }
        if (!TextUtils.isEmpty(i4)) {
            sb.append(str3);
            sb.append(i4);
        }
        return sb.toString();
    }

    @VisibleForTesting
    static String i(boolean z, Object obj) {
        String str;
        String className;
        if (obj == null) {
            return "";
        }
        if (obj instanceof Integer) {
            obj = Long.valueOf(((Integer) obj).intValue());
        }
        int i2 = 0;
        if (obj instanceof Long) {
            if (z) {
                Long l2 = (Long) obj;
                if (Math.abs(l2.longValue()) < 100) {
                    return obj.toString();
                }
                String str2 = obj.toString().charAt(0) == '-' ? HelpFormatter.DEFAULT_OPT_PREFIX : "";
                String valueOf = String.valueOf(Math.abs(l2.longValue()));
                long round = Math.round(Math.pow(10.0d, valueOf.length() - 1));
                long round2 = Math.round(Math.pow(10.0d, valueOf.length()) - 1.0d);
                return str2 + round + "..." + str2 + round2;
            }
            return obj.toString();
        } else if (obj instanceof Boolean) {
            return obj.toString();
        } else {
            if (!(obj instanceof Throwable)) {
                if (!(obj instanceof zzez)) {
                    return z ? HelpFormatter.DEFAULT_OPT_PREFIX : obj.toString();
                }
                str = ((zzez) obj).zza;
                return str;
            }
            Throwable th = (Throwable) obj;
            StringBuilder sb = new StringBuilder(z ? th.getClass().getName() : th.toString());
            String zzy = zzy(zzgk.class.getCanonicalName());
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                StackTraceElement stackTraceElement = stackTrace[i2];
                if (!stackTraceElement.isNativeMethod() && (className = stackTraceElement.getClassName()) != null && zzy(className).equals(zzy)) {
                    sb.append(": ");
                    sb.append(stackTraceElement);
                    break;
                }
                i2++;
            }
            return sb.toString();
        }
    }

    private static String zzy(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
    }

    @Override // com.google.android.gms.measurement.internal.zzhe
    protected final boolean b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void l(int i2, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && Log.isLoggable(zzq(), i2)) {
            Log.println(i2, zzq(), h(false, str, obj, obj2, obj3));
        }
        if (z2 || i2 < 5) {
            return;
        }
        Preconditions.checkNotNull(str);
        zzgh i3 = this.f6809a.i();
        if (i3 == null) {
            Log.println(6, zzq(), "Scheduler not set. Not logging error/warn");
        } else if (!i3.d()) {
            Log.println(6, zzq(), "Scheduler not initialized. Not logging error/warn");
        } else {
            if (i2 >= 9) {
                i2 = 8;
            }
            i3.zzp(new zzex(this, i2, str, obj, obj2, obj3));
        }
    }

    public final zzey zzc() {
        return this.zzk;
    }

    public final zzey zzd() {
        return this.zzd;
    }

    public final zzey zze() {
        return this.zzf;
    }

    public final zzey zzh() {
        return this.zze;
    }

    public final zzey zzi() {
        return this.zzj;
    }

    public final zzey zzj() {
        return this.zzl;
    }

    public final zzey zzk() {
        return this.zzg;
    }

    public final zzey zzl() {
        return this.zzi;
    }

    public final zzey zzm() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @VisibleForTesting
    @EnsuresNonNull({"logTagDoNotUseDirectly"})
    public final String zzq() {
        String str;
        synchronized (this) {
            if (this.zzc == null) {
                this.zzc = this.f6809a.zzy() != null ? this.f6809a.zzy() : this.f6809a.zzf().d();
            }
            Preconditions.checkNotNull(this.zzc);
            str = this.zzc;
        }
        return str;
    }
}
