package org.bouncycastle.cms.jcajce;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.PrivateKey;
import javax.crypto.Cipher;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.jcajce.io.CipherInputStream;
import org.bouncycastle.operator.InputAEADDecryptor;
/* loaded from: classes3.dex */
public class JceKeyTransAuthEnvelopedRecipient extends JceKeyTransRecipient {

    /* loaded from: classes3.dex */
    private static class AADStream extends OutputStream {
        private Cipher cipher;
        private byte[] oneByte = new byte[1];

        public AADStream(Cipher cipher) {
            this.cipher = cipher;
        }

        @Override // java.io.OutputStream
        public void write(int i2) {
            byte[] bArr = this.oneByte;
            bArr[0] = (byte) i2;
            this.cipher.updateAAD(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i2, int i3) {
            this.cipher.updateAAD(bArr, i2, i3);
        }
    }

    public JceKeyTransAuthEnvelopedRecipient(PrivateKey privateKey) {
        super(privateKey);
    }

    @Override // org.bouncycastle.cms.KeyTransRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) {
        final Cipher createContentCipher = this.f13220b.createContentCipher(a(algorithmIdentifier, algorithmIdentifier2, bArr), algorithmIdentifier2);
        return new RecipientOperator(new InputAEADDecryptor(this) { // from class: org.bouncycastle.cms.jcajce.JceKeyTransAuthEnvelopedRecipient.1
            @Override // org.bouncycastle.operator.AADProcessor
            public OutputStream getAADStream() {
                return new AADStream(createContentCipher);
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return new CipherInputStream(inputStream, createContentCipher);
            }

            @Override // org.bouncycastle.operator.AADProcessor
            public byte[] getMAC() {
                return new byte[0];
            }
        });
    }
}
