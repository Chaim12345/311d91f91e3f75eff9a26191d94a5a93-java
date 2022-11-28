package com.google.android.material.timepicker;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.IntRange;
import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TimeModel implements Parcelable {
    public static final Parcelable.Creator<TimeModel> CREATOR = new Parcelable.Creator<TimeModel>() { // from class: com.google.android.material.timepicker.TimeModel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeModel createFromParcel(Parcel parcel) {
            return new TimeModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeModel[] newArray(int i2) {
            return new TimeModel[i2];
        }
    };
    public static final String NUMBER_FORMAT = "%d";
    public static final String ZERO_LEADING_NUMBER_FORMAT = "%02d";

    /* renamed from: a  reason: collision with root package name */
    final int f7618a;

    /* renamed from: b  reason: collision with root package name */
    int f7619b;

    /* renamed from: c  reason: collision with root package name */
    int f7620c;

    /* renamed from: d  reason: collision with root package name */
    int f7621d;

    /* renamed from: e  reason: collision with root package name */
    int f7622e;
    private final MaxInputValidator hourInputValidator;
    private final MaxInputValidator minuteInputValidator;

    public TimeModel() {
        this(0);
    }

    public TimeModel(int i2) {
        this(0, 0, 10, i2);
    }

    public TimeModel(int i2, int i3, int i4, int i5) {
        this.f7619b = i2;
        this.f7620c = i3;
        this.f7621d = i4;
        this.f7618a = i5;
        this.f7622e = getPeriod(i2);
        this.minuteInputValidator = new MaxInputValidator(59);
        this.hourInputValidator = new MaxInputValidator(i5 == 1 ? 24 : 12);
    }

    protected TimeModel(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }

    public static String formatText(Resources resources, CharSequence charSequence) {
        return formatText(resources, charSequence, ZERO_LEADING_NUMBER_FORMAT);
    }

    public static String formatText(Resources resources, CharSequence charSequence, String str) {
        return String.format(resources.getConfiguration().locale, str, Integer.valueOf(Integer.parseInt(String.valueOf(charSequence))));
    }

    private static int getPeriod(int i2) {
        return i2 >= 12 ? 1 : 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TimeModel) {
            TimeModel timeModel = (TimeModel) obj;
            return this.f7619b == timeModel.f7619b && this.f7620c == timeModel.f7620c && this.f7618a == timeModel.f7618a && this.f7621d == timeModel.f7621d;
        }
        return false;
    }

    public int getHourForDisplay() {
        if (this.f7618a == 1) {
            return this.f7619b % 24;
        }
        int i2 = this.f7619b;
        if (i2 % 12 == 0) {
            return 12;
        }
        return this.f7622e == 1 ? i2 - 12 : i2;
    }

    public MaxInputValidator getHourInputValidator() {
        return this.hourInputValidator;
    }

    public MaxInputValidator getMinuteInputValidator() {
        return this.minuteInputValidator;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f7618a), Integer.valueOf(this.f7619b), Integer.valueOf(this.f7620c), Integer.valueOf(this.f7621d)});
    }

    public void setHour(int i2) {
        if (this.f7618a == 1) {
            this.f7619b = i2;
        } else {
            this.f7619b = (i2 % 12) + (this.f7622e != 1 ? 0 : 12);
        }
    }

    public void setHourOfDay(int i2) {
        this.f7622e = getPeriod(i2);
        this.f7619b = i2;
    }

    public void setMinute(@IntRange(from = 0, to = 60) int i2) {
        this.f7620c = i2 % 60;
    }

    public void setPeriod(int i2) {
        int i3;
        if (i2 != this.f7622e) {
            this.f7622e = i2;
            int i4 = this.f7619b;
            if (i4 < 12 && i2 == 1) {
                i3 = i4 + 12;
            } else if (i4 < 12 || i2 != 0) {
                return;
            } else {
                i3 = i4 - 12;
            }
            this.f7619b = i3;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f7619b);
        parcel.writeInt(this.f7620c);
        parcel.writeInt(this.f7621d);
        parcel.writeInt(this.f7618a);
    }
}
