package com.google.android.gms.internal.location;

import androidx.annotation.GuardedBy;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.jvm.internal.LongCompanionObject;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes.dex */
public final class zzbo {
    private static final SimpleDateFormat zza;
    private static final SimpleDateFormat zzb;
    @GuardedBy("sharedStringBuilder")
    private static final StringBuilder zzc;

    static {
        Locale locale = Locale.ROOT;
        zza = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", locale);
        zzb = new SimpleDateFormat("MM-dd HH:mm:ss", locale);
        zzc = new StringBuilder(33);
    }

    public static void zza(long j2, StringBuilder sb) {
        String str;
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        if (i2 == 0) {
            str = "0s";
        } else {
            sb.ensureCapacity(sb.length() + 27);
            boolean z = false;
            if (i2 < 0) {
                sb.append(HelpFormatter.DEFAULT_OPT_PREFIX);
                if (j2 != Long.MIN_VALUE) {
                    j2 = -j2;
                } else {
                    j2 = LongCompanionObject.MAX_VALUE;
                    z = true;
                }
            }
            if (j2 >= 86400000) {
                sb.append(j2 / 86400000);
                sb.append("d");
                j2 %= 86400000;
            }
            if (true == z) {
                j2 = 25975808;
            }
            if (j2 >= 3600000) {
                sb.append(j2 / 3600000);
                sb.append("h");
                j2 %= 3600000;
            }
            if (j2 >= AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS) {
                sb.append(j2 / AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
                sb.append("m");
                j2 %= AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS;
            }
            if (j2 >= 1000) {
                sb.append(j2 / 1000);
                sb.append("s");
                j2 %= 1000;
            }
            if (j2 <= 0) {
                return;
            }
            sb.append(j2);
            str = "ms";
        }
        sb.append(str);
    }
}
