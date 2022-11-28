package org.bouncycastle.operator.jcajce;

import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.Provider;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.DefaultJcaJceHelper;
import org.bouncycastle.jcajce.util.JcaJceHelper;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.jcajce.util.ProviderJcaJceHelper;
import org.bouncycastle.operator.DigestCalculator;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
/* loaded from: classes4.dex */
public class JcaDigestCalculatorProviderBuilder {
    private OperatorHelper helper = new OperatorHelper(new DefaultJcaJceHelper());

    /* loaded from: classes4.dex */
    private class DigestOutputStream extends OutputStream {
        private MessageDigest dig;

        DigestOutputStream(JcaDigestCalculatorProviderBuilder jcaDigestCalculatorProviderBuilder, MessageDigest messageDigest) {
            this.dig = messageDigest;
        }

        byte[] a() {
            return this.dig.digest();
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            this.dig.update((byte) i2);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) {
            this.dig.update(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            this.dig.update(bArr, i2, i3);
        }
    }

    public DigestCalculatorProvider build() {
        return new DigestCalculatorProvider() { // from class: org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder.1
            @Override // org.bouncycastle.operator.DigestCalculatorProvider
            public DigestCalculator get(final AlgorithmIdentifier algorithmIdentifier) {
                try {
                    final DigestOutputStream digestOutputStream = new DigestOutputStream(JcaDigestCalculatorProviderBuilder.this, JcaDigestCalculatorProviderBuilder.this.helper.d(algorithmIdentifier));
                    return new DigestCalculator(this) { // from class: org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder.1.1
                        @Override // org.bouncycastle.operator.DigestCalculator
                        public AlgorithmIdentifier getAlgorithmIdentifier() {
                            return algorithmIdentifier;
                        }

                        @Override // org.bouncycastle.operator.DigestCalculator
                        public byte[] getDigest() {
                            return digestOutputStream.a();
                        }

                        @Override // org.bouncycastle.operator.DigestCalculator
                        public OutputStream getOutputStream() {
                            return digestOutputStream;
                        }
                    };
                } catch (GeneralSecurityException e2) {
                    throw new OperatorCreationException("exception on setup: " + e2, e2);
                }
            }
        };
    }

    public JcaDigestCalculatorProviderBuilder setHelper(JcaJceHelper jcaJceHelper) {
        this.helper = new OperatorHelper(jcaJceHelper);
        return this;
    }

    public JcaDigestCalculatorProviderBuilder setProvider(String str) {
        this.helper = new OperatorHelper(new NamedJcaJceHelper(str));
        return this;
    }

    public JcaDigestCalculatorProviderBuilder setProvider(Provider provider) {
        this.helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }
}
