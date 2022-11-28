package com.appmattus.certificatetransparency.internal.utils;

import java.security.PublicKey;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.jcajce.provider.digest.SHA1;
import org.bouncycastle.jcajce.provider.digest.SHA256;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class PublicKeyExtKt {
    @NotNull
    public static final byte[] sha1Hash(@NotNull PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(publicKey, "<this>");
        byte[] digest = new SHA1.Digest().digest(publicKey.getEncoded());
        Intrinsics.checkNotNullExpressionValue(digest, "Digest().digest(encoded)");
        return digest;
    }

    @NotNull
    public static final byte[] sha256Hash(@NotNull PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(publicKey, "<this>");
        byte[] digest = new SHA256.Digest().digest(publicKey.getEncoded());
        Intrinsics.checkNotNullExpressionValue(digest, "Digest().digest(encoded)");
        return digest;
    }
}
