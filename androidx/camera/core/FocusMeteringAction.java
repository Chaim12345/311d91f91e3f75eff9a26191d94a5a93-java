package androidx.camera.core;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public final class FocusMeteringAction {
    public static final int FLAG_AE = 2;
    public static final int FLAG_AF = 1;
    public static final int FLAG_AWB = 4;
    private final long mAutoCancelDurationInMillis;
    private final List<MeteringPoint> mMeteringPointsAe;
    private final List<MeteringPoint> mMeteringPointsAf;
    private final List<MeteringPoint> mMeteringPointsAwb;

    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        final List f972a;

        /* renamed from: b  reason: collision with root package name */
        final List f973b;

        /* renamed from: c  reason: collision with root package name */
        final List f974c;

        /* renamed from: d  reason: collision with root package name */
        long f975d;

        public Builder(@NonNull MeteringPoint meteringPoint) {
            this(meteringPoint, 7);
        }

        public Builder(@NonNull MeteringPoint meteringPoint, int i2) {
            this.f972a = new ArrayList();
            this.f973b = new ArrayList();
            this.f974c = new ArrayList();
            this.f975d = 5000L;
            addPoint(meteringPoint, i2);
        }

        @NonNull
        public Builder addPoint(@NonNull MeteringPoint meteringPoint) {
            return addPoint(meteringPoint, 7);
        }

        @NonNull
        public Builder addPoint(@NonNull MeteringPoint meteringPoint, int i2) {
            boolean z = false;
            Preconditions.checkArgument(meteringPoint != null, "Point cannot be null.");
            if (i2 >= 1 && i2 <= 7) {
                z = true;
            }
            Preconditions.checkArgument(z, "Invalid metering mode " + i2);
            if ((i2 & 1) != 0) {
                this.f972a.add(meteringPoint);
            }
            if ((i2 & 2) != 0) {
                this.f973b.add(meteringPoint);
            }
            if ((i2 & 4) != 0) {
                this.f974c.add(meteringPoint);
            }
            return this;
        }

        @NonNull
        public FocusMeteringAction build() {
            return new FocusMeteringAction(this);
        }

        @NonNull
        public Builder disableAutoCancel() {
            this.f975d = 0L;
            return this;
        }

        @NonNull
        public Builder setAutoCancelDuration(@IntRange(from = 1) long j2, @NonNull TimeUnit timeUnit) {
            Preconditions.checkArgument(j2 >= 1, "autoCancelDuration must be at least 1");
            this.f975d = timeUnit.toMillis(j2);
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public @interface MeteringMode {
    }

    FocusMeteringAction(Builder builder) {
        this.mMeteringPointsAf = Collections.unmodifiableList(builder.f972a);
        this.mMeteringPointsAe = Collections.unmodifiableList(builder.f973b);
        this.mMeteringPointsAwb = Collections.unmodifiableList(builder.f974c);
        this.mAutoCancelDurationInMillis = builder.f975d;
    }

    public long getAutoCancelDurationInMillis() {
        return this.mAutoCancelDurationInMillis;
    }

    @NonNull
    public List<MeteringPoint> getMeteringPointsAe() {
        return this.mMeteringPointsAe;
    }

    @NonNull
    public List<MeteringPoint> getMeteringPointsAf() {
        return this.mMeteringPointsAf;
    }

    @NonNull
    public List<MeteringPoint> getMeteringPointsAwb() {
        return this.mMeteringPointsAwb;
    }

    public boolean isAutoCancelEnabled() {
        return this.mAutoCancelDurationInMillis > 0;
    }
}
