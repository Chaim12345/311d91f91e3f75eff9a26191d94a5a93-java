package org.bouncycastle.jcajce.provider.symmetric.util;

import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactorySpi;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
/* loaded from: classes3.dex */
public class BaseSecretKeyFactory extends SecretKeyFactorySpi implements PBE {

    /* renamed from: a  reason: collision with root package name */
    protected String f13785a;

    /* renamed from: b  reason: collision with root package name */
    protected ASN1ObjectIdentifier f13786b;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseSecretKeyFactory(String str, ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f13785a = str;
        this.f13786b = aSN1ObjectIdentifier;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // javax.crypto.SecretKeyFactorySpi
    public SecretKey engineGenerateSecret(KeySpec keySpec) {
        if (keySpec instanceof SecretKeySpec) {
            return new SecretKeySpec(((SecretKeySpec) keySpec).getEncoded(), this.f13785a);
        }
        throw new InvalidKeySpecException("Invalid KeySpec");
    }

    @Override // javax.crypto.SecretKeyFactorySpi
    protected KeySpec engineGetKeySpec(SecretKey secretKey, Class cls) {
        if (cls != null) {
            if (secretKey != null) {
                if (SecretKeySpec.class.isAssignableFrom(cls)) {
                    return new SecretKeySpec(secretKey.getEncoded(), this.f13785a);
                }
                try {
                    return (KeySpec) cls.getConstructor(byte[].class).newInstance(secretKey.getEncoded());
                } catch (Exception e2) {
                    throw new InvalidKeySpecException(e2.toString());
                }
            }
            throw new InvalidKeySpecException("key parameter is null");
        }
        throw new InvalidKeySpecException("keySpec parameter is null");
    }

    @Override // javax.crypto.SecretKeyFactorySpi
    protected SecretKey engineTranslateKey(SecretKey secretKey) {
        if (secretKey != null) {
            if (secretKey.getAlgorithm().equalsIgnoreCase(this.f13785a)) {
                return new SecretKeySpec(secretKey.getEncoded(), this.f13785a);
            }
            throw new InvalidKeyException("Key not of type " + this.f13785a + ".");
        }
        throw new InvalidKeyException("key parameter is null");
    }
}
