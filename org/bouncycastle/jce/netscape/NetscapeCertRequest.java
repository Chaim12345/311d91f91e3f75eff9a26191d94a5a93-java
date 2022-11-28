package org.bouncycastle.jce.netscape;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1IA5String;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/* loaded from: classes3.dex */
public class NetscapeCertRequest extends ASN1Object {

    /* renamed from: a  reason: collision with root package name */
    AlgorithmIdentifier f13802a;

    /* renamed from: b  reason: collision with root package name */
    AlgorithmIdentifier f13803b;

    /* renamed from: c  reason: collision with root package name */
    byte[] f13804c;

    /* renamed from: d  reason: collision with root package name */
    String f13805d;

    /* renamed from: e  reason: collision with root package name */
    DERBitString f13806e;

    /* renamed from: f  reason: collision with root package name */
    PublicKey f13807f;

    public NetscapeCertRequest(String str, AlgorithmIdentifier algorithmIdentifier, PublicKey publicKey) {
        this.f13805d = str;
        this.f13802a = algorithmIdentifier;
        this.f13807f = publicKey;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(getKeySpec());
        aSN1EncodableVector.add(new DERIA5String(str));
        try {
            this.f13806e = new DERBitString(new DERSequence(aSN1EncodableVector));
        } catch (IOException e2) {
            throw new InvalidKeySpecException("exception encoding key: " + e2.toString());
        }
    }

    public NetscapeCertRequest(ASN1Sequence aSN1Sequence) {
        try {
            if (aSN1Sequence.size() != 3) {
                throw new IllegalArgumentException("invalid SPKAC (size):" + aSN1Sequence.size());
            }
            this.f13802a = AlgorithmIdentifier.getInstance(aSN1Sequence.getObjectAt(1));
            this.f13804c = ((DERBitString) aSN1Sequence.getObjectAt(2)).getOctets();
            ASN1Sequence aSN1Sequence2 = (ASN1Sequence) aSN1Sequence.getObjectAt(0);
            if (aSN1Sequence2.size() != 2) {
                throw new IllegalArgumentException("invalid PKAC (len): " + aSN1Sequence2.size());
            }
            this.f13805d = ((ASN1IA5String) aSN1Sequence2.getObjectAt(1)).getString();
            this.f13806e = new DERBitString(aSN1Sequence2);
            SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(aSN1Sequence2.getObjectAt(0));
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(new DERBitString(subjectPublicKeyInfo).getBytes());
            AlgorithmIdentifier algorithm = subjectPublicKeyInfo.getAlgorithm();
            this.f13803b = algorithm;
            this.f13807f = KeyFactory.getInstance(algorithm.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME).generatePublic(x509EncodedKeySpec);
        } catch (Exception e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    public NetscapeCertRequest(byte[] bArr) {
        this(getReq(bArr));
    }

    private ASN1Primitive getKeySpec() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(this.f13807f.getEncoded());
            byteArrayOutputStream.close();
            return new ASN1InputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (IOException e2) {
            throw new InvalidKeySpecException(e2.getMessage());
        }
    }

    private static ASN1Sequence getReq(byte[] bArr) {
        return ASN1Sequence.getInstance(new ASN1InputStream(new ByteArrayInputStream(bArr)).readObject());
    }

    public String getChallenge() {
        return this.f13805d;
    }

    public AlgorithmIdentifier getKeyAlgorithm() {
        return this.f13803b;
    }

    public PublicKey getPublicKey() {
        return this.f13807f;
    }

    public AlgorithmIdentifier getSigningAlgorithm() {
        return this.f13802a;
    }

    public void setChallenge(String str) {
        this.f13805d = str;
    }

    public void setKeyAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        this.f13803b = algorithmIdentifier;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.f13807f = publicKey;
    }

    public void setSigningAlgorithm(AlgorithmIdentifier algorithmIdentifier) {
        this.f13802a = algorithmIdentifier;
    }

    public void sign(PrivateKey privateKey) {
        sign(privateKey, null);
    }

    public void sign(PrivateKey privateKey, SecureRandom secureRandom) {
        Signature signature = Signature.getInstance(this.f13802a.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME);
        if (secureRandom != null) {
            signature.initSign(privateKey, secureRandom);
        } else {
            signature.initSign(privateKey);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(getKeySpec());
        aSN1EncodableVector.add(new DERIA5String(this.f13805d));
        try {
            signature.update(new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
            this.f13804c = signature.sign();
        } catch (IOException e2) {
            throw new SignatureException(e2.getMessage());
        }
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        try {
            aSN1EncodableVector2.add(getKeySpec());
        } catch (Exception unused) {
        }
        aSN1EncodableVector2.add(new DERIA5String(this.f13805d));
        aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        aSN1EncodableVector.add(this.f13802a);
        aSN1EncodableVector.add(new DERBitString(this.f13804c));
        return new DERSequence(aSN1EncodableVector);
    }

    public boolean verify(String str) {
        if (str.equals(this.f13805d)) {
            Signature signature = Signature.getInstance(this.f13802a.getAlgorithm().getId(), BouncyCastleProvider.PROVIDER_NAME);
            signature.initVerify(this.f13807f);
            signature.update(this.f13806e.getBytes());
            return signature.verify(this.f13804c);
        }
        return false;
    }
}
