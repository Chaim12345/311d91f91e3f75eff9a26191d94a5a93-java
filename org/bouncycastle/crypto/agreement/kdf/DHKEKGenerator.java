package org.bouncycastle.crypto.agreement.kdf;

import java.io.IOException;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Encoding;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.util.Pack;
/* loaded from: classes3.dex */
public class DHKEKGenerator implements DerivationFunction {
    private ASN1ObjectIdentifier algorithm;
    private final Digest digest;
    private int keySize;
    private byte[] partyAInfo;
    private byte[] z;

    public DHKEKGenerator(Digest digest) {
        this.digest = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i2, int i3) {
        boolean z;
        int i4 = i3;
        int i5 = i2;
        if (bArr.length - i4 >= i5) {
            long j2 = i4;
            int digestSize = this.digest.getDigestSize();
            if (j2 <= 8589934591L) {
                long j3 = digestSize;
                int i6 = (int) (((j2 + j3) - 1) / j3);
                byte[] bArr2 = new byte[this.digest.getDigestSize()];
                int i7 = 0;
                int i8 = 0;
                int i9 = 1;
                while (i8 < i6) {
                    Digest digest = this.digest;
                    byte[] bArr3 = this.z;
                    digest.update(bArr3, i7, bArr3.length);
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                    aSN1EncodableVector2.add(this.algorithm);
                    aSN1EncodableVector2.add(new DEROctetString(Pack.intToBigEndian(i9)));
                    aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
                    if (this.partyAInfo != null) {
                        z = true;
                        aSN1EncodableVector.add(new DERTaggedObject(true, i7, (ASN1Encodable) new DEROctetString(this.partyAInfo)));
                    } else {
                        z = true;
                    }
                    aSN1EncodableVector.add(new DERTaggedObject(z, 2, new DEROctetString(Pack.intToBigEndian(this.keySize))));
                    try {
                        byte[] encoded = new DERSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER);
                        this.digest.update(encoded, 0, encoded.length);
                        this.digest.doFinal(bArr2, 0);
                        if (i4 > digestSize) {
                            System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                            i5 += digestSize;
                            i4 -= digestSize;
                        } else {
                            System.arraycopy(bArr2, 0, bArr, i5, i4);
                        }
                        i9++;
                        i8++;
                        i7 = 0;
                    } catch (IOException e2) {
                        throw new IllegalArgumentException("unable to encode parameter info: " + e2.getMessage());
                    }
                }
                this.digest.reset();
                return (int) j2;
            }
            throw new IllegalArgumentException("Output length too large");
        }
        throw new OutputLengthException("output buffer too small");
    }

    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        DHKDFParameters dHKDFParameters = (DHKDFParameters) derivationParameters;
        this.algorithm = dHKDFParameters.getAlgorithm();
        this.keySize = dHKDFParameters.getKeySize();
        this.z = dHKDFParameters.getZ();
        this.partyAInfo = dHKDFParameters.getExtraInfo();
    }
}
