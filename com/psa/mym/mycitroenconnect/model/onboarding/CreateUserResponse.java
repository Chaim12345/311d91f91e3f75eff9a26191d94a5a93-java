package com.psa.mym.mycitroenconnect.model.onboarding;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class CreateUserResponse {
    @SerializedName("userName")
    @Nullable
    private final String userName;
    @SerializedName("uuid")
    @Nullable
    private final String uuid;

    public CreateUserResponse() {
        this(null, null, 3, null);
    }

    public CreateUserResponse(@Nullable String str, @Nullable String str2) {
        this.userName = str;
        this.uuid = str2;
    }

    public /* synthetic */ CreateUserResponse(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2);
    }

    public static /* synthetic */ CreateUserResponse copy$default(CreateUserResponse createUserResponse, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = createUserResponse.userName;
        }
        if ((i2 & 2) != 0) {
            str2 = createUserResponse.uuid;
        }
        return createUserResponse.copy(str, str2);
    }

    @Nullable
    public final String component1() {
        return this.userName;
    }

    @Nullable
    public final String component2() {
        return this.uuid;
    }

    @NotNull
    public final CreateUserResponse copy(@Nullable String str, @Nullable String str2) {
        return new CreateUserResponse(str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CreateUserResponse) {
            CreateUserResponse createUserResponse = (CreateUserResponse) obj;
            return Intrinsics.areEqual(this.userName, createUserResponse.userName) && Intrinsics.areEqual(this.uuid, createUserResponse.uuid);
        }
        return false;
    }

    @Nullable
    public final String getUserName() {
        return this.userName;
    }

    @Nullable
    public final String getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        String str = this.userName;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.uuid;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "CreateUserResponse(userName=" + this.userName + ", uuid=" + this.uuid + ')';
    }
}
