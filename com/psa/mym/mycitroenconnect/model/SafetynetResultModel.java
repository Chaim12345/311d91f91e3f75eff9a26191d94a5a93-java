package com.psa.mym.mycitroenconnect.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class SafetynetResultModel implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<SafetynetResultModel> CREATOR = new Creator();
    @NotNull
    private final String basicIntegrity;
    @NotNull
    private final String evaluationType;
    @NotNull
    private final String profileMatch;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<SafetynetResultModel> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SafetynetResultModel createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SafetynetResultModel(parcel.readString(), parcel.readString(), parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final SafetynetResultModel[] newArray(int i2) {
            return new SafetynetResultModel[i2];
        }
    }

    public SafetynetResultModel(@NotNull String basicIntegrity, @NotNull String evaluationType, @NotNull String profileMatch) {
        Intrinsics.checkNotNullParameter(basicIntegrity, "basicIntegrity");
        Intrinsics.checkNotNullParameter(evaluationType, "evaluationType");
        Intrinsics.checkNotNullParameter(profileMatch, "profileMatch");
        this.basicIntegrity = basicIntegrity;
        this.evaluationType = evaluationType;
        this.profileMatch = profileMatch;
    }

    public static /* synthetic */ SafetynetResultModel copy$default(SafetynetResultModel safetynetResultModel, String str, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = safetynetResultModel.basicIntegrity;
        }
        if ((i2 & 2) != 0) {
            str2 = safetynetResultModel.evaluationType;
        }
        if ((i2 & 4) != 0) {
            str3 = safetynetResultModel.profileMatch;
        }
        return safetynetResultModel.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.basicIntegrity;
    }

    @NotNull
    public final String component2() {
        return this.evaluationType;
    }

    @NotNull
    public final String component3() {
        return this.profileMatch;
    }

    @NotNull
    public final SafetynetResultModel copy(@NotNull String basicIntegrity, @NotNull String evaluationType, @NotNull String profileMatch) {
        Intrinsics.checkNotNullParameter(basicIntegrity, "basicIntegrity");
        Intrinsics.checkNotNullParameter(evaluationType, "evaluationType");
        Intrinsics.checkNotNullParameter(profileMatch, "profileMatch");
        return new SafetynetResultModel(basicIntegrity, evaluationType, profileMatch);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SafetynetResultModel) {
            SafetynetResultModel safetynetResultModel = (SafetynetResultModel) obj;
            return Intrinsics.areEqual(this.basicIntegrity, safetynetResultModel.basicIntegrity) && Intrinsics.areEqual(this.evaluationType, safetynetResultModel.evaluationType) && Intrinsics.areEqual(this.profileMatch, safetynetResultModel.profileMatch);
        }
        return false;
    }

    @NotNull
    public final String getBasicIntegrity() {
        return this.basicIntegrity;
    }

    @NotNull
    public final String getEvaluationType() {
        return this.evaluationType;
    }

    @NotNull
    public final String getProfileMatch() {
        return this.profileMatch;
    }

    public int hashCode() {
        return (((this.basicIntegrity.hashCode() * 31) + this.evaluationType.hashCode()) * 31) + this.profileMatch.hashCode();
    }

    @NotNull
    public String toString() {
        return "SafetynetResultModel(basicIntegrity=" + this.basicIntegrity + ", evaluationType=" + this.evaluationType + ", profileMatch=" + this.profileMatch + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.basicIntegrity);
        out.writeString(this.evaluationType);
        out.writeString(this.profileMatch);
    }
}
