package com.appmattus.certificatetransparency.internal.loglist.parser;

import com.appmattus.certificatetransparency.internal.loglist.GooglePublicKeyKt;
import com.appmattus.certificatetransparency.internal.loglist.LogServerSignatureResult;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class LogListVerifier {
    @NotNull
    private final PublicKey publicKey;

    public LogListVerifier() {
        this(null, 1, null);
    }

    public LogListVerifier(@NotNull PublicKey publicKey) {
        Intrinsics.checkNotNullParameter(publicKey, "publicKey");
        this.publicKey = publicKey;
    }

    public /* synthetic */ LogListVerifier(PublicKey publicKey, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? GooglePublicKeyKt.getGoogleLogListPublicKey() : publicKey);
    }

    @NotNull
    public final LogServerSignatureResult verify(@NotNull byte[] message, @NotNull byte[] signature) {
        LogServerSignatureResult signatureNotValid;
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(signature, "signature");
        try {
            Signature signature2 = Signature.getInstance("SHA256withRSA");
            signature2.initVerify(this.publicKey);
            signature2.update(message);
            return signature2.verify(signature) ? LogServerSignatureResult.Valid.INSTANCE : LogServerSignatureResult.Invalid.SignatureFailed.INSTANCE;
        } catch (InvalidKeyException e2) {
            signatureNotValid = new LogServerSignatureResult.Invalid.PublicKeyNotValid(e2);
            return signatureNotValid;
        } catch (NoSuchAlgorithmException e3) {
            signatureNotValid = new LogServerSignatureResult.Invalid.NoSuchAlgorithm(e3);
            return signatureNotValid;
        } catch (SignatureException e4) {
            signatureNotValid = new LogServerSignatureResult.Invalid.SignatureNotValid(e4);
            return signatureNotValid;
        }
    }
}
