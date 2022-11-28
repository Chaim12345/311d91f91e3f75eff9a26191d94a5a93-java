package org.bouncycastle.cms.bc;

import java.io.InputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.RecipientOperator;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.StreamCipher;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.operator.InputDecryptor;
/* loaded from: classes3.dex */
public class BcPasswordEnvelopedRecipient extends BcPasswordRecipient {
    public BcPasswordEnvelopedRecipient(char[] cArr) {
        super(cArr);
    }

    @Override // org.bouncycastle.cms.PasswordRecipient
    public RecipientOperator getRecipientOperator(AlgorithmIdentifier algorithmIdentifier, final AlgorithmIdentifier algorithmIdentifier2, byte[] bArr, byte[] bArr2) {
        final Object a2 = EnvelopedDataHelper.a(false, a(algorithmIdentifier, algorithmIdentifier2, bArr, bArr2), algorithmIdentifier2);
        return new RecipientOperator(new InputDecryptor(this) { // from class: org.bouncycastle.cms.bc.BcPasswordEnvelopedRecipient.1
            @Override // org.bouncycastle.operator.InputDecryptor
            public AlgorithmIdentifier getAlgorithmIdentifier() {
                return algorithmIdentifier2;
            }

            @Override // org.bouncycastle.operator.InputDecryptor
            public InputStream getInputStream(InputStream inputStream) {
                return a2 instanceof BufferedBlockCipher ? new CipherInputStream(inputStream, (BufferedBlockCipher) a2) : new CipherInputStream(inputStream, (StreamCipher) a2);
            }
        });
    }
}
