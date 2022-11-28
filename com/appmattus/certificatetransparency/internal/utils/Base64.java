package com.appmattus.certificatetransparency.internal.utils;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class Base64 {
    @NotNull
    public static final Base64 INSTANCE = new Base64();

    private Base64() {
    }

    @NotNull
    public final byte[] decode(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] decode = org.bouncycastle.util.encoders.Base64.decode(data);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(data)");
        return decode;
    }

    @NotNull
    public final String toBase64String(@Nullable byte[] bArr) {
        String base64String = org.bouncycastle.util.encoders.Base64.toBase64String(bArr);
        Intrinsics.checkNotNullExpressionValue(base64String, "toBase64String(data)");
        return base64String;
    }
}
