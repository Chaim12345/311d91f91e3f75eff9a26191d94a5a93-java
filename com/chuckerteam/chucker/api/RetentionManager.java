package com.chuckerteam.chucker.api;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.chuckerteam.chucker.internal.support.Logger;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001e\u001fB\u001b\b\u0007\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u000e¢\u0006\u0004\b\u001c\u0010\u001dJ\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0002H\u0002J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u0002H\u0002J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0002H\u0002J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\u000f\u0010\u0013\u001a\u00020\u0006H\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0016\u0010\u000f\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010\u0014R\u0016\u0010\u0015\u001a\u00020\u00028\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006 "}, d2 = {"Lcom/chuckerteam/chucker/api/RetentionManager;", "", "", "fallback", "getLastCleanup", AppConstants.GEO_FENCE_TIME, "", "updateLastCleanup", "threshold", "deleteSince", "now", "", "isCleanupDue", "getThreshold", "Lcom/chuckerteam/chucker/api/RetentionManager$Period;", TypedValues.Cycle.S_WAVE_PERIOD, "toMillis", "doMaintenance$com_github_ChuckerTeam_Chucker_library", "()V", "doMaintenance", "J", "cleanupFrequency", "Landroid/content/SharedPreferences;", "prefs", "Landroid/content/SharedPreferences;", "Landroid/content/Context;", "context", "retentionPeriod", "<init>", "(Landroid/content/Context;Lcom/chuckerteam/chucker/api/RetentionManager$Period;)V", "Companion", "Period", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class RetentionManager {
    private static final Companion Companion = new Companion(null);
    private static final String KEY_LAST_CLEANUP = "last_cleanup";
    private static final String PREFS_NAME = "chucker_preferences";
    private static long lastCleanup;
    private final long cleanupFrequency;
    private final long period;
    private final SharedPreferences prefs;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\t\u0010\nR\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0003\u0010\u0004R\u0016\u0010\u0005\u001a\u00020\u00028\u0002@\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0004R\u0016\u0010\u0007\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/chuckerteam/chucker/api/RetentionManager$Companion;", "", "", "KEY_LAST_CLEANUP", "Ljava/lang/String;", "PREFS_NAME", "", "lastCleanup", "J", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/chuckerteam/chucker/api/RetentionManager$Period;", "", "<init>", "(Ljava/lang/String;I)V", "ONE_HOUR", "ONE_DAY", "ONE_WEEK", "FOREVER", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public enum Period {
        ONE_HOUR,
        ONE_DAY,
        ONE_WEEK,
        FOREVER
    }

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Period.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[Period.ONE_HOUR.ordinal()] = 1;
            iArr[Period.ONE_DAY.ordinal()] = 2;
            iArr[Period.ONE_WEEK.ordinal()] = 3;
            iArr[Period.FOREVER.ordinal()] = 4;
        }
    }

    @JvmOverloads
    public RetentionManager(@NotNull Context context) {
        this(context, null, 2, null);
    }

    @JvmOverloads
    public RetentionManager(@NotNull Context context, @NotNull Period retentionPeriod) {
        TimeUnit timeUnit;
        long j2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(retentionPeriod, "retentionPeriod");
        this.period = toMillis(retentionPeriod);
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "context.getSharedPreferences(PREFS_NAME, 0)");
        this.prefs = sharedPreferences;
        if (retentionPeriod == Period.ONE_HOUR) {
            timeUnit = TimeUnit.MINUTES;
            j2 = 30;
        } else {
            timeUnit = TimeUnit.HOURS;
            j2 = 2;
        }
        this.cleanupFrequency = timeUnit.toMillis(j2);
    }

    public /* synthetic */ RetentionManager(Context context, Period period, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? Period.ONE_WEEK : period);
    }

    private final void deleteSince(long j2) {
        BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new RetentionManager$deleteSince$1(j2, null), 3, null);
    }

    private final long getLastCleanup(long j2) {
        if (lastCleanup == 0) {
            lastCleanup = this.prefs.getLong(KEY_LAST_CLEANUP, j2);
        }
        return lastCleanup;
    }

    private final long getThreshold(long j2) {
        long j3 = this.period;
        return j3 == 0 ? j2 : j2 - j3;
    }

    private final boolean isCleanupDue(long j2) {
        return j2 - getLastCleanup(j2) > this.cleanupFrequency;
    }

    private final long toMillis(Period period) {
        TimeUnit timeUnit;
        int i2 = WhenMappings.$EnumSwitchMapping$0[period.ordinal()];
        if (i2 == 1) {
            timeUnit = TimeUnit.HOURS;
        } else if (i2 != 2) {
            if (i2 != 3) {
                if (i2 == 4) {
                    return 0L;
                }
                throw new NoWhenBranchMatchedException();
            }
            return TimeUnit.DAYS.toMillis(7L);
        } else {
            timeUnit = TimeUnit.DAYS;
        }
        return timeUnit.toMillis(1L);
    }

    private final void updateLastCleanup(long j2) {
        lastCleanup = j2;
        this.prefs.edit().putLong(KEY_LAST_CLEANUP, j2).apply();
    }

    public final synchronized void doMaintenance$com_github_ChuckerTeam_Chucker_library() {
        if (this.period > 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (isCleanupDue(currentTimeMillis)) {
                Logger.INSTANCE.info("Performing data retention maintenance...");
                deleteSince(getThreshold(currentTimeMillis));
                updateLastCleanup(currentTimeMillis);
            }
        }
    }
}
