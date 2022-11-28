package com.psa.mym.mycitroenconnect.api.body.dashboard;

import com.google.gson.annotations.SerializedName;
import com.psa.mym.mycitroenconnect.model.dashboard.EmergencyDetailsItem;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class EmergencyContactBody {
    @SerializedName("countryCode")
    @NotNull
    private String countryCode;
    @SerializedName("emergencyDetails")
    @Nullable
    private ArrayList<EmergencyDetailsItem> emergencyDetails;
    @SerializedName("userName")
    @NotNull
    private String userName;

    public EmergencyContactBody() {
        this(null, null, null, 7, null);
    }

    public EmergencyContactBody(@NotNull String userName, @NotNull String countryCode, @Nullable ArrayList<EmergencyDetailsItem> arrayList) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        this.userName = userName;
        this.countryCode = countryCode;
        this.emergencyDetails = arrayList;
    }

    public /* synthetic */ EmergencyContactBody(String str, String str2, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? null : arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ EmergencyContactBody copy$default(EmergencyContactBody emergencyContactBody, String str, String str2, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = emergencyContactBody.userName;
        }
        if ((i2 & 2) != 0) {
            str2 = emergencyContactBody.countryCode;
        }
        if ((i2 & 4) != 0) {
            arrayList = emergencyContactBody.emergencyDetails;
        }
        return emergencyContactBody.copy(str, str2, arrayList);
    }

    @NotNull
    public final String component1() {
        return this.userName;
    }

    @NotNull
    public final String component2() {
        return this.countryCode;
    }

    @Nullable
    public final ArrayList<EmergencyDetailsItem> component3() {
        return this.emergencyDetails;
    }

    @NotNull
    public final EmergencyContactBody copy(@NotNull String userName, @NotNull String countryCode, @Nullable ArrayList<EmergencyDetailsItem> arrayList) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(countryCode, "countryCode");
        return new EmergencyContactBody(userName, countryCode, arrayList);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmergencyContactBody) {
            EmergencyContactBody emergencyContactBody = (EmergencyContactBody) obj;
            return Intrinsics.areEqual(this.userName, emergencyContactBody.userName) && Intrinsics.areEqual(this.countryCode, emergencyContactBody.countryCode) && Intrinsics.areEqual(this.emergencyDetails, emergencyContactBody.emergencyDetails);
        }
        return false;
    }

    @NotNull
    public final String getCountryCode() {
        return this.countryCode;
    }

    @Nullable
    public final ArrayList<EmergencyDetailsItem> getEmergencyDetails() {
        return this.emergencyDetails;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    public int hashCode() {
        int hashCode = ((this.userName.hashCode() * 31) + this.countryCode.hashCode()) * 31;
        ArrayList<EmergencyDetailsItem> arrayList = this.emergencyDetails;
        return hashCode + (arrayList == null ? 0 : arrayList.hashCode());
    }

    public final void setCountryCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.countryCode = str;
    }

    public final void setEmergencyDetails(@Nullable ArrayList<EmergencyDetailsItem> arrayList) {
        this.emergencyDetails = arrayList;
    }

    public final void setUserName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.userName = str;
    }

    @NotNull
    public String toString() {
        return "EmergencyContactBody(userName=" + this.userName + ", countryCode=" + this.countryCode + ", emergencyDetails=" + this.emergencyDetails + ')';
    }
}
