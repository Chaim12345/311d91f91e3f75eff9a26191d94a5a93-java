package com.psa.mym.mycitroenconnect.model.secondary_user;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class Errors {
    @SerializedName("code")
    @NotNull
    private String code;
    @SerializedName("description")
    @NotNull
    private String description;

    public Errors() {
        this(null, null, 3, null);
    }

    public Errors(@NotNull String code, @NotNull String description) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(description, "description");
        this.code = code;
        this.description = description;
    }

    public /* synthetic */ Errors(String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2);
    }

    public static /* synthetic */ Errors copy$default(Errors errors, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = errors.code;
        }
        if ((i2 & 2) != 0) {
            str2 = errors.description;
        }
        return errors.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.code;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    @NotNull
    public final Errors copy(@NotNull String code, @NotNull String description) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(description, "description");
        return new Errors(code, description);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Errors) {
            Errors errors = (Errors) obj;
            return Intrinsics.areEqual(this.code, errors.code) && Intrinsics.areEqual(this.description, errors.description);
        }
        return false;
    }

    @NotNull
    public final String getCode() {
        return this.code;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public int hashCode() {
        return (this.code.hashCode() * 31) + this.description.hashCode();
    }

    public final void setCode(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.code = str;
    }

    public final void setDescription(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.description = str;
    }

    @NotNull
    public String toString() {
        return "Errors(code=" + this.code + ", description=" + this.description + ')';
    }
}
