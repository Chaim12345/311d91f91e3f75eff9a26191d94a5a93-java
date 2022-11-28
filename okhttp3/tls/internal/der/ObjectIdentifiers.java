package okhttp3.tls.internal.der;

import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class ObjectIdentifiers {
    @NotNull
    public static final ObjectIdentifiers INSTANCE = new ObjectIdentifiers();
    @NotNull
    public static final String basicConstraints = "2.5.29.19";
    @NotNull
    public static final String commonName = "2.5.4.3";
    @NotNull
    public static final String ecPublicKey = "1.2.840.10045.2.1";
    @NotNull
    public static final String organizationalUnitName = "2.5.4.11";
    @NotNull
    public static final String rsaEncryption = "1.2.840.113549.1.1.1";
    @NotNull
    public static final String sha256WithRSAEncryption = "1.2.840.113549.1.1.11";
    @NotNull
    public static final String sha256withEcdsa = "1.2.840.10045.4.3.2";
    @NotNull
    public static final String subjectAlternativeName = "2.5.29.17";

    private ObjectIdentifiers() {
    }
}
