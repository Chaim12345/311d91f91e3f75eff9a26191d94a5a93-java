package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import java.util.Arrays;
/* loaded from: classes2.dex */
public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new Parcelable.Creator<CalendarConstraints>() { // from class: com.google.android.material.datepicker.CalendarConstraints.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NonNull
        public CalendarConstraints createFromParcel(@NonNull Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NonNull
        public CalendarConstraints[] newArray(int i2) {
            return new CalendarConstraints[i2];
        }
    };
    @NonNull
    private final Month end;
    private final int monthSpan;
    @Nullable
    private Month openAt;
    @NonNull
    private final Month start;
    @NonNull
    private final DateValidator validator;
    private final int yearSpan;

    /* loaded from: classes2.dex */
    public static final class Builder {
        private static final String DEEP_COPY_VALIDATOR_KEY = "DEEP_COPY_VALIDATOR_KEY";

        /* renamed from: a  reason: collision with root package name */
        static final long f7221a = UtcDates.a(Month.a(1900, 0).f7273e);

        /* renamed from: b  reason: collision with root package name */
        static final long f7222b = UtcDates.a(Month.a(2100, 11).f7273e);
        private long end;
        private Long openAt;
        private long start;
        private DateValidator validator;

        public Builder() {
            this.start = f7221a;
            this.end = f7222b;
            this.validator = DateValidatorPointForward.from(Long.MIN_VALUE);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(@NonNull CalendarConstraints calendarConstraints) {
            this.start = f7221a;
            this.end = f7222b;
            this.validator = DateValidatorPointForward.from(Long.MIN_VALUE);
            this.start = calendarConstraints.start.f7273e;
            this.end = calendarConstraints.end.f7273e;
            this.openAt = Long.valueOf(calendarConstraints.openAt.f7273e);
            this.validator = calendarConstraints.validator;
        }

        @NonNull
        public CalendarConstraints build() {
            Bundle bundle = new Bundle();
            bundle.putParcelable(DEEP_COPY_VALIDATOR_KEY, this.validator);
            Month b2 = Month.b(this.start);
            Month b3 = Month.b(this.end);
            DateValidator dateValidator = (DateValidator) bundle.getParcelable(DEEP_COPY_VALIDATOR_KEY);
            Long l2 = this.openAt;
            return new CalendarConstraints(b2, b3, dateValidator, l2 == null ? null : Month.b(l2.longValue()));
        }

        @NonNull
        public Builder setEnd(long j2) {
            this.end = j2;
            return this;
        }

        @NonNull
        public Builder setOpenAt(long j2) {
            this.openAt = Long.valueOf(j2);
            return this;
        }

        @NonNull
        public Builder setStart(long j2) {
            this.start = j2;
            return this;
        }

        @NonNull
        public Builder setValidator(@NonNull DateValidator dateValidator) {
            this.validator = dateValidator;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    public interface DateValidator extends Parcelable {
        boolean isValid(long j2);
    }

    private CalendarConstraints(@NonNull Month month, @NonNull Month month2, @NonNull DateValidator dateValidator, @Nullable Month month3) {
        this.start = month;
        this.end = month2;
        this.openAt = month3;
        this.validator = dateValidator;
        if (month3 != null && month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        }
        if (month3 != null && month3.compareTo(month2) > 0) {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
        this.monthSpan = month.j(month2) + 1;
        this.yearSpan = (month2.f7270b - month.f7270b) + 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Month e(Month month) {
        return month.compareTo(this.start) < 0 ? this.start : month.compareTo(this.end) > 0 ? this.end : month;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CalendarConstraints) {
            CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
            return this.start.equals(calendarConstraints.start) && this.end.equals(calendarConstraints.end) && ObjectsCompat.equals(this.openAt, calendarConstraints.openAt) && this.validator.equals(calendarConstraints.validator);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month f() {
        return this.end;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.monthSpan;
    }

    public DateValidator getDateValidator() {
        return this.validator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Month h() {
        return this.openAt;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.start, this.end, this.openAt, this.validator});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month i() {
        return this.start;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j() {
        return this.yearSpan;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean k(long j2) {
        if (this.start.e(1) <= j2) {
            Month month = this.end;
            if (j2 <= month.e(month.f7272d)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(@Nullable Month month) {
        this.openAt = month;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.start, 0);
        parcel.writeParcelable(this.end, 0);
        parcel.writeParcelable(this.openAt, 0);
        parcel.writeParcelable(this.validator, 0);
    }
}
