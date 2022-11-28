package com.appmattus.certificatetransparency.internal.utils;

import java.io.StringReader;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x9.X9ObjectIdentifiers;
import org.bouncycastle.util.io.pem.PemReader;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class PublicKeyFactory {
    @NotNull
    public static final PublicKeyFactory INSTANCE = new PublicKeyFactory();

    private PublicKeyFactory() {
    }

    private final String determineKeyAlgorithm(byte[] bArr) {
        Object nextElement = ASN1Sequence.getInstance(bArr).getObjects().nextElement();
        Objects.requireNonNull(nextElement, "null cannot be cast to non-null type org.bouncycastle.asn1.DLSequence");
        Object nextElement2 = ((DLSequence) nextElement).getObjects().nextElement();
        Objects.requireNonNull(nextElement2, "null cannot be cast to non-null type org.bouncycastle.asn1.ASN1ObjectIdentifier");
        ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) nextElement2;
        if (Intrinsics.areEqual(aSN1ObjectIdentifier, PKCSObjectIdentifiers.rsaEncryption)) {
            return "RSA";
        }
        if (Intrinsics.areEqual(aSN1ObjectIdentifier, X9ObjectIdentifiers.id_ecPublicKey)) {
            return "EC";
        }
        throw new IllegalArgumentException("Unsupported key type " + aSN1ObjectIdentifier);
    }

    @NotNull
    public final PublicKey fromByteArray(@NotNull byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        PublicKey generatePublic = KeyFactory.getInstance(determineKeyAlgorithm(bytes)).generatePublic(new X509EncodedKeySpec(bytes));
        Intrinsics.checkNotNullExpressionValue(generatePublic, "keyFactory.generatePubli…509EncodedKeySpec(bytes))");
        return generatePublic;
    }

    @NotNull
    public final PublicKey fromPemString(@NotNull String keyText) {
        Intrinsics.checkNotNullParameter(keyText, "keyText");
        byte[] pemContent = new PemReader(new StringReader(keyText)).readPemObject().getContent();
        Intrinsics.checkNotNullExpressionValue(pemContent, "pemContent");
        return fromByteArray(pemContent);
    }
}
