package org.bouncycastle.cert.ocsp;

import java.io.OutputStream;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ocsp.ResponderID;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes3.dex */
public class RespID {
    public static final AlgorithmIdentifier HASH_SHA1 = new AlgorithmIdentifier(OIWObjectIdentifiers.idSHA1, DERNull.INSTANCE);

    /* renamed from: a  reason: collision with root package name */
    ResponderID f13096a;

    public RespID(ResponderID responderID) {
        this.f13096a = responderID;
    }

    public RespID(X500Name x500Name) {
        this.f13096a = new ResponderID(x500Name);
    }

    public RespID(SubjectPublicKeyInfo subjectPublicKeyInfo, DigestCalculator digestCalculator) {
        try {
            if (!digestCalculator.getAlgorithmIdentifier().equals(HASH_SHA1)) {
                throw new IllegalArgumentException("only SHA-1 can be used with RespID - found: " + digestCalculator.getAlgorithmIdentifier().getAlgorithm());
            }
            OutputStream outputStream = digestCalculator.getOutputStream();
            outputStream.write(subjectPublicKeyInfo.getPublicKeyData().getBytes());
            outputStream.close();
            this.f13096a = new ResponderID(new DEROctetString(digestCalculator.getDigest()));
        } catch (Exception e2) {
            throw new OCSPException("problem creating ID: " + e2, e2);
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof RespID) {
            return this.f13096a.equals(((RespID) obj).f13096a);
        }
        return false;
    }

    public int hashCode() {
        return this.f13096a.hashCode();
    }

    public ResponderID toASN1Primitive() {
        return this.f13096a;
    }
}
