package com.google.android.material.datepicker;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new Parcelable.Creator<Month>() { // from class: com.google.android.material.datepicker.Month.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NonNull
        public Month createFromParcel(@NonNull Parcel parcel) {
            return Month.a(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NonNull
        public Month[] newArray(int i2) {
            return new Month[i2];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final int f7269a;

    /* renamed from: b  reason: collision with root package name */
    final int f7270b;

    /* renamed from: c  reason: collision with root package name */
    final int f7271c;

    /* renamed from: d  reason: collision with root package name */
    final int f7272d;

    /* renamed from: e  reason: collision with root package name */
    final long f7273e;
    @NonNull
    private final Calendar firstOfMonth;
    @Nullable
    private String longName;

    private Month(@NonNull Calendar calendar) {
        calendar.set(5, 1);
        Calendar d2 = UtcDates.d(calendar);
        this.firstOfMonth = d2;
        this.f7269a = d2.get(2);
        this.f7270b = d2.get(1);
        this.f7271c = d2.getMaximum(7);
        this.f7272d = d2.getActualMaximum(5);
        this.f7273e = d2.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Month a(int i2, int i3) {
        Calendar l2 = UtcDates.l();
        l2.set(1, i2);
        l2.set(2, i3);
        return new Month(l2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Month b(long j2) {
        Calendar l2 = UtcDates.l();
        l2.setTimeInMillis(j2);
        return new Month(l2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public static Month c() {
        return new Month(UtcDates.k());
    }

    @Override // java.lang.Comparable
    public int compareTo(@NonNull Month month) {
        return this.firstOfMonth.compareTo(month.firstOfMonth);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d() {
        int firstDayOfWeek = this.firstOfMonth.get(7) - this.firstOfMonth.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.f7271c : firstDayOfWeek;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long e(int i2) {
        Calendar d2 = UtcDates.d(this.firstOfMonth);
        d2.set(5, i2);
        return d2.getTimeInMillis();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Month) {
            Month month = (Month) obj;
            return this.f7269a == month.f7269a && this.f7270b == month.f7270b;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f(long j2) {
        Calendar d2 = UtcDates.d(this.firstOfMonth);
        d2.setTimeInMillis(j2);
        return d2.get(5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public String g(Context context) {
        if (this.longName == null) {
            this.longName = DateStrings.i(context, this.firstOfMonth.getTimeInMillis());
        }
        return this.longName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long h() {
        return this.firstOfMonth.getTimeInMillis();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f7269a), Integer.valueOf(this.f7270b)});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Month i(int i2) {
        Calendar d2 = UtcDates.d(this.firstOfMonth);
        d2.add(2, i2);
        return new Month(d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int j(@NonNull Month month) {
        if (this.firstOfMonth instanceof GregorianCalendar) {
            return ((month.f7270b - this.f7270b) * 12) + (month.f7269a - this.f7269a);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i2) {
        parcel.writeInt(this.f7270b);
        parcel.writeInt(this.f7269a);
    }
}
