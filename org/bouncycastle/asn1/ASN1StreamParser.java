package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes3.dex */
public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;
    private final byte[][] tmpBuffers;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.a(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i2) {
        this(inputStream, i2, new byte[11]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1StreamParser(InputStream inputStream, int i2, byte[][] bArr) {
        this._in = inputStream;
        this._limit = i2;
        this.tmpBuffers = bArr;
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    private void set00Check(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).c(z);
        }
    }

    ASN1Encodable a(int i2) {
        set00Check(false);
        int h2 = ASN1InputStream.h(this._in, i2);
        int g2 = ASN1InputStream.g(this._in, this._limit, h2 == 3 || h2 == 4 || h2 == 16 || h2 == 17 || h2 == 8);
        if (g2 < 0) {
            if ((i2 & 32) != 0) {
                ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this._in, this._limit), this._limit, this.tmpBuffers);
                int i3 = i2 & 192;
                return i3 != 0 ? 64 == i3 ? new BERApplicationSpecificParser(h2, aSN1StreamParser) : new BERTaggedObjectParser(i3, h2, aSN1StreamParser) : aSN1StreamParser.e(h2);
            }
            throw new IOException("indefinite-length primitive encoding encountered");
        }
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, g2, this._limit);
        if ((i2 & BERTags.FLAGS) == 0) {
            return g(h2, definiteLengthInputStream);
        }
        ASN1StreamParser aSN1StreamParser2 = new ASN1StreamParser(definiteLengthInputStream, definiteLengthInputStream.a(), this.tmpBuffers);
        int i4 = i2 & 192;
        if (i4 != 0) {
            boolean z = (i2 & 32) != 0;
            return 64 == i4 ? (DLApplicationSpecific) aSN1StreamParser2.b(i4, h2, z) : new DLTaggedObjectParser(i4, h2, z, aSN1StreamParser2);
        }
        return aSN1StreamParser2.d(h2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive b(int i2, int i3, boolean z) {
        return !z ? ASN1TaggedObject.j(i2, i3, ((DefiniteLengthInputStream) this._in).e()) : ASN1TaggedObject.h(i2, i3, j());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Primitive c(int i2, int i3) {
        return ASN1TaggedObject.i(i2, i3, j());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable d(int i2) {
        if (i2 != 3) {
            if (i2 != 4) {
                if (i2 != 8) {
                    if (i2 != 16) {
                        if (i2 == 17) {
                            return new DLSetParser(this);
                        }
                        throw new ASN1Exception("unknown DL object encountered: 0x" + Integer.toHexString(i2));
                    }
                    return new DLSequenceParser(this);
                }
                return new DERExternalParser(this);
            }
            return new BEROctetStringParser(this);
        }
        return new BERBitStringParser(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable e(int i2) {
        if (i2 != 3) {
            if (i2 != 4) {
                if (i2 != 8) {
                    if (i2 != 16) {
                        if (i2 == 17) {
                            return new BERSetParser(this);
                        }
                        throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i2));
                    }
                    return new BERSequenceParser(this);
                }
                return new DERExternalParser(this);
            }
            return new BEROctetStringParser(this);
        }
        return new BERBitStringParser(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable f(int i2) {
        return g(i2, (DefiniteLengthInputStream) this._in);
    }

    ASN1Encodable g(int i2, DefiniteLengthInputStream definiteLengthInputStream) {
        if (i2 != 3) {
            if (i2 != 4) {
                if (i2 != 8) {
                    if (i2 != 16) {
                        if (i2 != 17) {
                            try {
                                return ASN1InputStream.d(i2, definiteLengthInputStream, this.tmpBuffers);
                            } catch (IllegalArgumentException e2) {
                                throw new ASN1Exception("corrupted stream detected", e2);
                            }
                        }
                        throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
                    }
                    throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
                }
                throw new ASN1Exception("externals must use constructed encoding (see X.690 8.18)");
            }
            return new DEROctetStringParser(definiteLengthInputStream);
        }
        return new DLBitStringParser(definiteLengthInputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1Encodable h(int i2) {
        if (i2 < 0 || i2 > 30) {
            throw new IllegalArgumentException("invalid universal tag number: " + i2);
        }
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        if ((read & (-33)) == i2) {
            return a(read);
        }
        throw new IOException("unexpected identifier encountered: " + read);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1TaggedObjectParser i() {
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        if ((read & 192) != 0) {
            return (ASN1TaggedObjectParser) a(read);
        }
        throw new ASN1Exception("no tagged object found");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1EncodableVector j() {
        int read = this._in.read();
        if (read < 0) {
            return new ASN1EncodableVector(0);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        do {
            ASN1Encodable a2 = a(read);
            aSN1EncodableVector.add(a2 instanceof InMemoryRepresentable ? ((InMemoryRepresentable) a2).getLoadedObject() : a2.toASN1Primitive());
            read = this._in.read();
        } while (read >= 0);
        return aSN1EncodableVector;
    }

    public ASN1Encodable readObject() {
        int read = this._in.read();
        if (read < 0) {
            return null;
        }
        return a(read);
    }
}
