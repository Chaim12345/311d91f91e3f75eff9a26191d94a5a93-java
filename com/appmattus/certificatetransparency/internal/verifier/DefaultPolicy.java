package com.appmattus.certificatetransparency.internal.verifier;

import com.appmattus.certificatetransparency.CTPolicy;
import com.appmattus.certificatetransparency.SctVerificationResult;
import com.appmattus.certificatetransparency.VerificationResult;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class DefaultPolicy implements CTPolicy {

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class MonthDifference {
        private final boolean hasPartialMonth;
        private final int roundedMonthDifference;

        public MonthDifference(int i2, boolean z) {
            this.roundedMonthDifference = i2;
            this.hasPartialMonth = z;
        }

        public static /* synthetic */ MonthDifference copy$default(MonthDifference monthDifference, int i2, boolean z, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i2 = monthDifference.roundedMonthDifference;
            }
            if ((i3 & 2) != 0) {
                z = monthDifference.hasPartialMonth;
            }
            return monthDifference.copy(i2, z);
        }

        public final int component1() {
            return this.roundedMonthDifference;
        }

        public final boolean component2() {
            return this.hasPartialMonth;
        }

        @NotNull
        public final MonthDifference copy(int i2, boolean z) {
            return new MonthDifference(i2, z);
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof MonthDifference) {
                MonthDifference monthDifference = (MonthDifference) obj;
                return this.roundedMonthDifference == monthDifference.roundedMonthDifference && this.hasPartialMonth == monthDifference.hasPartialMonth;
            }
            return false;
        }

        public final boolean getHasPartialMonth() {
            return this.hasPartialMonth;
        }

        public final int getRoundedMonthDifference() {
            return this.roundedMonthDifference;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int hashCode = Integer.hashCode(this.roundedMonthDifference) * 31;
            boolean z = this.hasPartialMonth;
            int i2 = z;
            if (z != 0) {
                i2 = 1;
            }
            return hashCode + i2;
        }

        @NotNull
        public String toString() {
            return "MonthDifference(roundedMonthDifference=" + this.roundedMonthDifference + ", hasPartialMonth=" + this.hasPartialMonth + ')';
        }
    }

    private final int getDayOfMonth(Calendar calendar) {
        return calendar.get(5);
    }

    private final int getMonth(Calendar calendar) {
        return calendar.get(2);
    }

    private final int getYear(Calendar calendar) {
        return calendar.get(1);
    }

    private final MonthDifference roundedDownMonthDifference(Calendar calendar, Calendar calendar2) {
        if (calendar2.compareTo(calendar) < 0) {
            return new MonthDifference(0, false);
        }
        return new MonthDifference((((getYear(calendar2) - getYear(calendar)) * 12) + (getMonth(calendar2) - getMonth(calendar))) - (getDayOfMonth(calendar2) < getDayOfMonth(calendar) ? 1 : 0), getDayOfMonth(calendar2) != getDayOfMonth(calendar));
    }

    @Override // com.appmattus.certificatetransparency.CTPolicy
    @NotNull
    public VerificationResult policyVerificationResult(@NotNull X509Certificate leafCertificate, @NotNull Map<String, ? extends SctVerificationResult> sctResults) {
        Intrinsics.checkNotNullParameter(leafCertificate, "leafCertificate");
        Intrinsics.checkNotNullParameter(sctResults, "sctResults");
        Calendar before = Calendar.getInstance();
        before.setTime(leafCertificate.getNotBefore());
        Calendar after = Calendar.getInstance();
        after.setTime(leafCertificate.getNotAfter());
        Intrinsics.checkNotNullExpressionValue(before, "before");
        Intrinsics.checkNotNullExpressionValue(after, "after");
        MonthDifference roundedDownMonthDifference = roundedDownMonthDifference(before, after);
        int component1 = roundedDownMonthDifference.component1();
        boolean component2 = roundedDownMonthDifference.component2();
        int i2 = (component1 > 39 || (component1 == 39 && component2)) ? 5 : (component1 > 27 || (component1 == 27 && component2)) ? 4 : component1 >= 15 ? 3 : 2;
        int i3 = 0;
        if (!sctResults.isEmpty()) {
            for (Map.Entry<String, ? extends SctVerificationResult> entry : sctResults.entrySet()) {
                if (entry.getValue() instanceof SctVerificationResult.Valid) {
                    i3++;
                }
            }
        }
        return i3 < i2 ? new VerificationResult.Failure.TooFewSctsTrusted(sctResults, i2) : new VerificationResult.Success.Trusted(sctResults);
    }
}
