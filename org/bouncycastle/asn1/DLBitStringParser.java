package org.bouncycastle.asn1;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes3.dex */
public class DLBitStringParser implements ASN1BitStringParser {
    private int padBits = 0;
    private final DefiniteLengthInputStream stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DLBitStringParser(DefiniteLengthInputStream definiteLengthInputStream) {
        this.stream = definiteLengthInputStream;
    }

    private InputStream getBitStream(boolean z) {
        int c2 = this.stream.c();
        if (c2 >= 1) {
            int read = this.stream.read();
            this.padBits = read;
            if (read > 0) {
                if (c2 < 2) {
                    throw new IllegalStateException("zero length data with non-zero pad bits");
                }
                if (read > 7) {
                    throw new IllegalStateException("pad bits cannot be greater than 7 or less than 0");
                }
                if (z) {
                    throw new IOException("expected octet-aligned bitstring, but found padBits: " + this.padBits);
                }
            }
            return this.stream;
        }
        throw new IllegalStateException("content octets cannot be empty");
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public InputStream getBitStream() {
        return getBitStream(false);
    }

    @Override // org.bouncycastle.asn1.InMemoryRepresentable
    public ASN1Primitive getLoadedObject() {
        return ASN1BitString.h(this.stream.e());
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public InputStream getOctetStream() {
        return getBitStream(true);
    }

    @Override // org.bouncycastle.asn1.ASN1BitStringParser
    public int getPadBits() {
        return this.padBits;
    }

    @Override // org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e2) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e2.getMessage(), e2);
        }
    }
}
