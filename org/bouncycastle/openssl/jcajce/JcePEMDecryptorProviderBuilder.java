package org.bouncycastle.openssl.jcajce;

import java.security.Provider;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.openssl.PEMDecryptor;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PasswordException;
/* loaded from: classes4.dex */
public class JcePEMDecryptorProviderBuilder {
    private JcaJceHelper helper = new DefaultJcaJceHelper();

    public PEMDecryptorProvider build(final char[] cArr) {
        return new PEMDecryptorProvider() { // from class: org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder.1
            @Override // org.bouncycastle.openssl.PEMDecryptorProvider
            public PEMDecryptor get(final String str) {
                return new PEMDecryptor() { // from class: org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder.1.1
                    @Override // org.bouncycastle.openssl.PEMDecryptor
                    public byte[] decrypt(byte[] bArr, byte[] bArr2) {
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                        if (cArr != null) {
                            return PEMUtilities.a(false, JcePEMDecryptorProviderBuilder.this.helper, bArr, cArr, str, bArr2);
                        }
                        throw new PasswordException("Password is null, but a password is required");
                    }
                };
            }
        };
    }

    public JcePEMDecryptorProviderBuilder setProvider(String str) {
        this.helper = new NamedJcaJceHelper(str);
        return this;
    }

    public JcePEMDecryptorProviderBuilder setProvider(Provider provider) {
        this.helper = new ProviderJcaJceHelper(provider);
        return this;
    }
}
