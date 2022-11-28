package com.psa.mym.mycitroenconnect.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Parcelize
/* loaded from: classes3.dex */
public final class Token implements Parcelable {
    @NotNull
    public static final Parcelable.Creator<Token> CREATOR = new Creator();
    @SerializedName("access_token")
    @Nullable
    private final String accessToken;
    @SerializedName("expires_in")
    @Nullable
    private final Integer expiresIn;
    @SerializedName("refresh_token")
    @Nullable
    private final String refreshToken;
    @SerializedName("token_type")
    @Nullable
    private final String tokenType;

    /* loaded from: classes3.dex */
    public static final class Creator implements Parcelable.Creator<Token> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Token createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new Token(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Token[] newArray(int i2) {
            return new Token[i2];
        }
    }

    public Token() {
        this(null, null, null, null, 15, null);
    }

    public Token(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num) {
        this.accessToken = str;
        this.refreshToken = str2;
        this.tokenType = str3;
        this.expiresIn = num;
    }

    public /* synthetic */ Token(String str, String str2, String str3, Integer num, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : num);
    }

    public static /* synthetic */ Token copy$default(Token token, String str, String str2, String str3, Integer num, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = token.accessToken;
        }
        if ((i2 & 2) != 0) {
            str2 = token.refreshToken;
        }
        if ((i2 & 4) != 0) {
            str3 = token.tokenType;
        }
        if ((i2 & 8) != 0) {
            num = token.expiresIn;
        }
        return token.copy(str, str2, str3, num);
    }

    @Nullable
    public final String component1() {
        return this.accessToken;
    }

    @Nullable
    public final String component2() {
        return this.refreshToken;
    }

    @Nullable
    public final String component3() {
        return this.tokenType;
    }

    @Nullable
    public final Integer component4() {
        return this.expiresIn;
    }

    @NotNull
    public final Token copy(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable Integer num) {
        return new Token(str, str2, str3, num);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Token) {
            Token token = (Token) obj;
            return Intrinsics.areEqual(this.accessToken, token.accessToken) && Intrinsics.areEqual(this.refreshToken, token.refreshToken) && Intrinsics.areEqual(this.tokenType, token.tokenType) && Intrinsics.areEqual(this.expiresIn, token.expiresIn);
        }
        return false;
    }

    @Nullable
    public final String getAccessToken() {
        return this.accessToken;
    }

    @Nullable
    public final Integer getExpiresIn() {
        return this.expiresIn;
    }

    @Nullable
    public final String getRefreshToken() {
        return this.refreshToken;
    }

    @Nullable
    public final String getTokenType() {
        return this.tokenType;
    }

    public int hashCode() {
        String str = this.accessToken;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.refreshToken;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.tokenType;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.expiresIn;
        return hashCode3 + (num != null ? num.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "Token(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", tokenType=" + this.tokenType + ", expiresIn=" + this.expiresIn + ')';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel out, int i2) {
        int intValue;
        Intrinsics.checkNotNullParameter(out, "out");
        out.writeString(this.accessToken);
        out.writeString(this.refreshToken);
        out.writeString(this.tokenType);
        Integer num = this.expiresIn;
        if (num == null) {
            intValue = 0;
        } else {
            out.writeInt(1);
            intValue = num.intValue();
        }
        out.writeInt(intValue);
    }
}
