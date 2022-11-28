package com.appmattus.certificatetransparency.internal.utils;

import com.appmattus.certificatetransparency.internal.serialization.CTConstants;
import com.appmattus.certificatetransparency.internal.serialization.Deserializer;
import com.appmattus.certificatetransparency.internal.verifier.model.SignedCertificateTimestamp;
import java.io.ByteArrayInputStream;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.tls.TlsUtils;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public final class X509CertificateExtKt {
    private static final List<SignedCertificateTimestamp> parseSctsFromCertExtension(byte[] bArr) {
        List<SignedCertificateTimestamp> list;
        ArrayList arrayList = new ArrayList();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        TlsUtils.readUint16(byteArrayInputStream);
        while (byteArrayInputStream.available() > 2) {
            byte[] sctBytes = TlsUtils.readOpaque16(byteArrayInputStream);
            Deserializer deserializer = Deserializer.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(sctBytes, "sctBytes");
            arrayList.add(deserializer.parseSctFromBinary(new ByteArrayInputStream(sctBytes)));
        }
        list = CollectionsKt___CollectionsKt.toList(arrayList);
        return list;
    }

    @NotNull
    public static final List<SignedCertificateTimestamp> signedCertificateTimestamps(@NotNull X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(x509Certificate, "<this>");
        ASN1Primitive fromByteArray = ASN1Primitive.fromByteArray(ASN1OctetString.getInstance(x509Certificate.getExtensionValue(CTConstants.SCT_CERTIFICATE_OID)).getOctets());
        Objects.requireNonNull(fromByteArray, "null cannot be cast to non-null type org.bouncycastle.asn1.DEROctetString");
        byte[] octets = ((DEROctetString) fromByteArray).getOctets();
        Intrinsics.checkNotNullExpressionValue(octets, "p.octets");
        return parseSctsFromCertExtension(octets);
    }
}
