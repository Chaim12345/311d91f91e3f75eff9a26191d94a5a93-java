package com.psa.mym.mycitroenconnect.model.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class AddSecondaryUserResponse {
    @SerializedName("userName")
    @NotNull
    private final String userName;
    @SerializedName("uuid")
    @NotNull
    private final String uuid;

    public AddSecondaryUserResponse(@NotNull String userName, @NotNull String uuid) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        this.userName = userName;
        this.uuid = uuid;
    }

    public static /* synthetic */ AddSecondaryUserResponse copy$default(AddSecondaryUserResponse addSecondaryUserResponse, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = addSecondaryUserResponse.userName;
        }
        if ((i2 & 2) != 0) {
            str2 = addSecondaryUserResponse.uuid;
        }
        return addSecondaryUserResponse.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.userName;
    }

    @NotNull
    public final String component2() {
        return this.uuid;
    }

    @NotNull
    public final AddSecondaryUserResponse copy(@NotNull String userName, @NotNull String uuid) {
        Intrinsics.checkNotNullParameter(userName, "userName");
        Intrinsics.checkNotNullParameter(uuid, "uuid");
        return new AddSecondaryUserResponse(userName, uuid);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AddSecondaryUserResponse) {
            AddSecondaryUserResponse addSecondaryUserResponse = (AddSecondaryUserResponse) obj;
            return Intrinsics.areEqual(this.userName, addSecondaryUserResponse.userName) && Intrinsics.areEqual(this.uuid, addSecondaryUserResponse.uuid);
        }
        return false;
    }

    @NotNull
    public final String getUserName() {
        return this.userName;
    }

    @NotNull
    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        return (this.userName.hashCode() * 31) + this.uuid.hashCode();
    }

    @NotNull
    public String toString() {
        return "AddSecondaryUserResponse(userName=" + this.userName + ", uuid=" + this.uuid + ')';
    }
}
