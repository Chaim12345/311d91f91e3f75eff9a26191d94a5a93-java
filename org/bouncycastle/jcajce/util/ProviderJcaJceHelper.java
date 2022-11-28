package org.bouncycastle.jcajce.util;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathValidator;
import java.security.cert.CertStore;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertificateFactory;
import javax.crypto.Cipher;
import javax.crypto.ExemptionMechanism;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
/* loaded from: classes3.dex */
public class ProviderJcaJceHelper implements JcaJceHelper {

    /* renamed from: a  reason: collision with root package name */
    protected final Provider f13801a;

    public ProviderJcaJceHelper(Provider provider) {
        this.f13801a = provider;
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public AlgorithmParameterGenerator createAlgorithmParameterGenerator(String str) {
        return AlgorithmParameterGenerator.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public AlgorithmParameters createAlgorithmParameters(String str) {
        return AlgorithmParameters.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public CertPathBuilder createCertPathBuilder(String str) {
        return CertPathBuilder.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public CertPathValidator createCertPathValidator(String str) {
        return CertPathValidator.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public CertStore createCertStore(String str, CertStoreParameters certStoreParameters) {
        return CertStore.getInstance(str, certStoreParameters, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public CertificateFactory createCertificateFactory(String str) {
        return CertificateFactory.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public Cipher createCipher(String str) {
        return Cipher.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public MessageDigest createDigest(String str) {
        return MessageDigest.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public ExemptionMechanism createExemptionMechanism(String str) {
        return ExemptionMechanism.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public KeyAgreement createKeyAgreement(String str) {
        return KeyAgreement.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public KeyFactory createKeyFactory(String str) {
        return KeyFactory.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public KeyGenerator createKeyGenerator(String str) {
        return KeyGenerator.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public KeyPairGenerator createKeyPairGenerator(String str) {
        return KeyPairGenerator.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public KeyStore createKeyStore(String str) {
        return KeyStore.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public Mac createMac(String str) {
        return Mac.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public MessageDigest createMessageDigest(String str) {
        return MessageDigest.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public SecretKeyFactory createSecretKeyFactory(String str) {
        return SecretKeyFactory.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public SecureRandom createSecureRandom(String str) {
        return SecureRandom.getInstance(str, this.f13801a);
    }

    @Override // org.bouncycastle.jcajce.util.JcaJceHelper
    public Signature createSignature(String str) {
        return Signature.getInstance(str, this.f13801a);
    }
}
