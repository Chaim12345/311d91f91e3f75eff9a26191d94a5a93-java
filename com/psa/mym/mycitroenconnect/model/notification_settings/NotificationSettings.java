package com.psa.mym.mycitroenconnect.model.notification_settings;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NotificationSettings {
    @SerializedName("id")
    @NotNull
    private String id;
    @SerializedName("settings")
    @NotNull
    private ArrayList<Settings> settings;
    @SerializedName("vinNo")
    @NotNull
    private String vinNo;

    public NotificationSettings(@NotNull String id, @NotNull String vinNo, @NotNull ArrayList<Settings> settings) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(vinNo, "vinNo");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.id = id;
        this.vinNo = vinNo;
        this.settings = settings;
    }

    public /* synthetic */ NotificationSettings(String str, String str2, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i2 & 4) != 0 ? new ArrayList() : arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NotificationSettings copy$default(NotificationSettings notificationSettings, String str, String str2, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = notificationSettings.id;
        }
        if ((i2 & 2) != 0) {
            str2 = notificationSettings.vinNo;
        }
        if ((i2 & 4) != 0) {
            arrayList = notificationSettings.settings;
        }
        return notificationSettings.copy(str, str2, arrayList);
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final String component2() {
        return this.vinNo;
    }

    @NotNull
    public final ArrayList<Settings> component3() {
        return this.settings;
    }

    @NotNull
    public final NotificationSettings copy(@NotNull String id, @NotNull String vinNo, @NotNull ArrayList<Settings> settings) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(vinNo, "vinNo");
        Intrinsics.checkNotNullParameter(settings, "settings");
        return new NotificationSettings(id, vinNo, settings);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NotificationSettings) {
            NotificationSettings notificationSettings = (NotificationSettings) obj;
            return Intrinsics.areEqual(this.id, notificationSettings.id) && Intrinsics.areEqual(this.vinNo, notificationSettings.vinNo) && Intrinsics.areEqual(this.settings, notificationSettings.settings);
        }
        return false;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final ArrayList<Settings> getSettings() {
        return this.settings;
    }

    @NotNull
    public final String getVinNo() {
        return this.vinNo;
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.vinNo.hashCode()) * 31) + this.settings.hashCode();
    }

    public final void setId(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.id = str;
    }

    public final void setSettings(@NotNull ArrayList<Settings> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.settings = arrayList;
    }

    public final void setVinNo(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.vinNo = str;
    }

    @NotNull
    public String toString() {
        return "NotificationSettings(id=" + this.id + ", vinNo=" + this.vinNo + ", settings=" + this.settings + ')';
    }
}
