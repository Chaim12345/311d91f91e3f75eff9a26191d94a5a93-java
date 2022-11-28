package org.bouncycastle.mozilla;

import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentVerifier;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.util.Encodable;
/* loaded from: classes4.dex */
public class SignedPublicKeyAndChallenge implements Encodable {

    /* renamed from: a  reason: collision with root package name */
    protected final org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge f14368a;

    /* JADX INFO: Access modifiers changed from: protected */
    public SignedPublicKeyAndChallenge(org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge signedPublicKeyAndChallenge) {
        this.f14368a = signedPublicKeyAndChallenge;
    }

    public SignedPublicKeyAndChallenge(byte[] bArr) {
        this.f14368a = org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge.getInstance(bArr);
    }

    public String getChallenge() {
        return this.f14368a.getPublicKeyAndChallenge().getChallenge().getString();
    }

    @Override // org.bouncycastle.util.Encodable
    public byte[] getEncoded() {
        return toASN1Structure().getEncoded();
    }

    public PublicKey getPublicKey(String str) {
        SubjectPublicKeyInfo subjectPublicKeyInfo = this.f14368a.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
        try {
            return KeyFactory.getInstance(subjectPublicKeyInfo.getAlgorithm().getAlgorithm().getId(), str).generatePublic(new X509EncodedKeySpec(new DERBitString(subjectPublicKeyInfo).getOctets()));
        } catch (Exception unused) {
            throw new InvalidKeyException("error encoding public key");
        }
    }

    public PublicKeyAndChallenge getPublicKeyAndChallenge() {
        return this.f14368a.getPublicKeyAndChallenge();
    }

    public SubjectPublicKeyInfo getSubjectPublicKeyInfo() {
        return this.f14368a.getPublicKeyAndChallenge().getSubjectPublicKeyInfo();
    }

    public boolean isSignatureValid(ContentVerifierProvider contentVerifierProvider) {
        ContentVerifier contentVerifier = contentVerifierProvider.get(this.f14368a.getSignatureAlgorithm());
        OutputStream outputStream = contentVerifier.getOutputStream();
        this.f14368a.getPublicKeyAndChallenge().encodeTo(outputStream, ASN1Encoding.DER);
        outputStream.close();
        return contentVerifier.verify(this.f14368a.getSignature().getOctets());
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f14368a.toASN1Primitive();
    }

    public org.bouncycastle.asn1.mozilla.SignedPublicKeyAndChallenge toASN1Structure() {
        return this.f14368a;
    }

    public boolean verify() {
        return verify(null);
    }

    public boolean verify(String str) {
        String id = this.f14368a.getSignatureAlgorithm().getAlgorithm().getId();
        Signature signature = str == null ? Signature.getInstance(id) : Signature.getInstance(id, str);
        signature.initVerify(getPublicKey(str));
        try {
            signature.update(this.f14368a.getPublicKeyAndChallenge().getEncoded());
            return signature.verify(this.f14368a.getSignature().getBytes());
        } catch (Exception unused) {
            throw new InvalidKeyException("error encoding public key");
        }
    }
}
