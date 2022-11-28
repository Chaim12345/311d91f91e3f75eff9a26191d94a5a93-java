package org.bouncycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes3.dex */
public class ASN1InputStream extends FilterInputStream implements BERTags {
    private final boolean lazyEvaluate;
    private final int limit;
    private final byte[][] tmpBuffers;

    public ASN1InputStream(InputStream inputStream) {
        this(inputStream, StreamUtil.a(inputStream));
    }

    public ASN1InputStream(InputStream inputStream, int i2) {
        this(inputStream, i2, false);
    }

    public ASN1InputStream(InputStream inputStream, int i2, boolean z) {
        this(inputStream, i2, z, new byte[11]);
    }

    private ASN1InputStream(InputStream inputStream, int i2, boolean z, byte[][] bArr) {
        super(inputStream);
        this.limit = i2;
        this.lazyEvaluate = z;
        this.tmpBuffers = bArr;
    }

    public ASN1InputStream(InputStream inputStream, boolean z) {
        this(inputStream, StreamUtil.a(inputStream), z);
    }

    public ASN1InputStream(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    public ASN1InputStream(byte[] bArr, boolean z) {
        this(new ByteArrayInputStream(bArr), bArr.length, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ASN1Primitive d(int i2, DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) {
        switch (i2) {
            case 1:
                return ASN1Boolean.h(getBuffer(definiteLengthInputStream, bArr));
            case 2:
                return ASN1Integer.h(definiteLengthInputStream.e());
            case 3:
                return ASN1BitString.h(definiteLengthInputStream.e());
            case 4:
                return ASN1OctetString.h(definiteLengthInputStream.e());
            case 5:
                return ASN1Null.h(definiteLengthInputStream.e());
            case 6:
                return ASN1ObjectIdentifier.h(getBuffer(definiteLengthInputStream, bArr), true);
            case 7:
                return ASN1ObjectDescriptor.h(definiteLengthInputStream.e());
            case 8:
            case 9:
            case 11:
            case 14:
            case 15:
            case 16:
            case 17:
            case 29:
            default:
                throw new IOException("unknown tag " + i2 + " encountered");
            case 10:
                return ASN1Enumerated.h(getBuffer(definiteLengthInputStream, bArr), true);
            case 12:
                return ASN1UTF8String.h(definiteLengthInputStream.e());
            case 13:
                return ASN1RelativeOID.h(definiteLengthInputStream.e(), false);
            case 18:
                return ASN1NumericString.h(definiteLengthInputStream.e());
            case 19:
                return ASN1PrintableString.h(definiteLengthInputStream.e());
            case 20:
                return ASN1T61String.h(definiteLengthInputStream.e());
            case 21:
                return ASN1VideotexString.h(definiteLengthInputStream.e());
            case 22:
                return ASN1IA5String.h(definiteLengthInputStream.e());
            case 23:
                return ASN1UTCTime.h(definiteLengthInputStream.e());
            case 24:
                return ASN1GeneralizedTime.h(definiteLengthInputStream.e());
            case 25:
                return ASN1GraphicString.h(definiteLengthInputStream.e());
            case 26:
                return ASN1VisibleString.h(definiteLengthInputStream.e());
            case 27:
                return ASN1GeneralString.h(definiteLengthInputStream.e());
            case 28:
                return ASN1UniversalString.h(definiteLengthInputStream.e());
            case 30:
                return ASN1BMPString.i(getBMPCharBuffer(definiteLengthInputStream));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int g(InputStream inputStream, int i2, boolean z) {
        int read = inputStream.read();
        if ((read >>> 7) == 0) {
            return read;
        }
        if (128 == read) {
            return -1;
        }
        if (read >= 0) {
            if (255 != read) {
                int i3 = read & 127;
                int i4 = 0;
                int i5 = 0;
                do {
                    int read2 = inputStream.read();
                    if (read2 < 0) {
                        throw new EOFException("EOF found reading length");
                    }
                    if ((i4 >>> 23) != 0) {
                        throw new IOException("long form definite-length more than 31 bits");
                    }
                    i4 = (i4 << 8) + read2;
                    i5++;
                } while (i5 < i3);
                if (i4 < i2 || z) {
                    return i4;
                }
                throw new IOException("corrupted stream - out of bounds length found: " + i4 + " >= " + i2);
            }
            throw new IOException("invalid long form definite-length 0xFF");
        }
        throw new EOFException("EOF found when length expected");
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream definiteLengthInputStream) {
        int i2;
        int c2 = definiteLengthInputStream.c();
        if ((c2 & 1) == 0) {
            int i3 = c2 / 2;
            char[] cArr = new char[i3];
            byte[] bArr = new byte[8];
            int i4 = 0;
            int i5 = 0;
            while (c2 >= 8) {
                if (Streams.readFully(definiteLengthInputStream, bArr, 0, 8) != 8) {
                    throw new EOFException("EOF encountered in middle of BMPString");
                }
                cArr[i5] = (char) ((bArr[0] << 8) | (bArr[1] & 255));
                cArr[i5 + 1] = (char) ((bArr[2] << 8) | (bArr[3] & 255));
                cArr[i5 + 2] = (char) ((bArr[4] << 8) | (bArr[5] & 255));
                cArr[i5 + 3] = (char) ((bArr[6] << 8) | (bArr[7] & 255));
                i5 += 4;
                c2 -= 8;
            }
            if (c2 > 0) {
                if (Streams.readFully(definiteLengthInputStream, bArr, 0, c2) != c2) {
                    throw new EOFException("EOF encountered in middle of BMPString");
                }
                while (true) {
                    int i6 = i4 + 1;
                    int i7 = i6 + 1;
                    i2 = i5 + 1;
                    cArr[i5] = (char) ((bArr[i4] << 8) | (bArr[i6] & 255));
                    if (i7 >= c2) {
                        break;
                    }
                    i4 = i7;
                    i5 = i2;
                }
                i5 = i2;
            }
            if (definiteLengthInputStream.c() == 0 && i3 == i5) {
                return cArr;
            }
            throw new IllegalStateException();
        }
        throw new IOException("malformed BMPString encoding encountered");
    }

    private static byte[] getBuffer(DefiniteLengthInputStream definiteLengthInputStream, byte[][] bArr) {
        int c2 = definiteLengthInputStream.c();
        if (c2 >= bArr.length) {
            return definiteLengthInputStream.e();
        }
        byte[] bArr2 = bArr[c2];
        if (bArr2 == null) {
            bArr2 = new byte[c2];
            bArr[c2] = bArr2;
        }
        definiteLengthInputStream.d(bArr2);
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int h(InputStream inputStream, int i2) {
        int i3 = i2 & 31;
        if (i3 == 31) {
            int i4 = 0;
            int read = inputStream.read();
            if (read < 31) {
                if (read < 0) {
                    throw new EOFException("EOF found inside tag value.");
                }
                throw new IOException("corrupted stream - high tag number < 31 found");
            } else if ((read & 127) != 0) {
                while ((read & 128) != 0) {
                    if ((i4 >>> 24) != 0) {
                        throw new IOException("Tag number more than 31 bits");
                    }
                    i4 = (i4 | (read & 127)) << 7;
                    read = inputStream.read();
                    if (read < 0) {
                        throw new EOFException("EOF found inside tag value.");
                    }
                }
                return i4 | (read & 127);
            } else {
                throw new IOException("corrupted stream - invalid high tag number found");
            }
        }
        return i3;
    }

    ASN1BitString a(ASN1EncodableVector aSN1EncodableVector) {
        int size = aSN1EncodableVector.size();
        ASN1BitString[] aSN1BitStringArr = new ASN1BitString[size];
        for (int i2 = 0; i2 != size; i2++) {
            ASN1Encodable aSN1Encodable = aSN1EncodableVector.get(i2);
            if (!(aSN1Encodable instanceof ASN1BitString)) {
                throw new ASN1Exception("unknown object encountered in constructed BIT STRING: " + aSN1Encodable.getClass());
            }
            aSN1BitStringArr[i2] = (ASN1BitString) aSN1Encodable;
        }
        return new BERBitString(aSN1BitStringArr);
    }

    ASN1OctetString b(ASN1EncodableVector aSN1EncodableVector) {
        int size = aSN1EncodableVector.size();
        ASN1OctetString[] aSN1OctetStringArr = new ASN1OctetString[size];
        for (int i2 = 0; i2 != size; i2++) {
            ASN1Encodable aSN1Encodable = aSN1EncodableVector.get(i2);
            if (!(aSN1Encodable instanceof ASN1OctetString)) {
                throw new ASN1Exception("unknown object encountered in constructed OCTET STRING: " + aSN1Encodable.getClass());
            }
            aSN1OctetStringArr[i2] = (ASN1OctetString) aSN1Encodable;
        }
        return new BEROctetString(aSN1OctetStringArr);
    }

    protected ASN1Primitive c(int i2, int i3, int i4) {
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this, i4, this.limit);
        if ((i2 & BERTags.FLAGS) == 0) {
            return d(i3, definiteLengthInputStream, this.tmpBuffers);
        }
        int i5 = i2 & 192;
        if (i5 != 0) {
            return i(i5, i3, (i2 & 32) != 0, definiteLengthInputStream);
        } else if (i3 != 3) {
            if (i3 != 4) {
                if (i3 != 8) {
                    if (i3 == 16) {
                        return definiteLengthInputStream.c() < 1 ? DLFactory.f12746a : this.lazyEvaluate ? new LazyEncodedSequence(definiteLengthInputStream.e()) : DLFactory.a(k(definiteLengthInputStream));
                    } else if (i3 == 17) {
                        return DLFactory.b(k(definiteLengthInputStream));
                    } else {
                        throw new IOException("unknown tag " + i3 + " encountered");
                    }
                }
                return DLFactory.a(k(definiteLengthInputStream)).k();
            }
            return b(k(definiteLengthInputStream));
        } else {
            return a(k(definiteLengthInputStream));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int e() {
        return this.limit;
    }

    protected int f() {
        return g(this, this.limit, false);
    }

    ASN1Primitive i(int i2, int i3, boolean z, DefiniteLengthInputStream definiteLengthInputStream) {
        return !z ? ASN1TaggedObject.j(i2, i3, definiteLengthInputStream.e()) : ASN1TaggedObject.h(i2, i3, k(definiteLengthInputStream));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASN1EncodableVector j() {
        ASN1Primitive readObject = readObject();
        if (readObject == null) {
            return new ASN1EncodableVector(0);
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        do {
            aSN1EncodableVector.add(readObject);
            readObject = readObject();
        } while (readObject != null);
        return aSN1EncodableVector;
    }

    ASN1EncodableVector k(DefiniteLengthInputStream definiteLengthInputStream) {
        int c2 = definiteLengthInputStream.c();
        return c2 < 1 ? new ASN1EncodableVector(0) : new ASN1InputStream(definiteLengthInputStream, c2, this.lazyEvaluate, this.tmpBuffers).j();
    }

    public ASN1Primitive readObject() {
        int read = read();
        if (read <= 0) {
            if (read != 0) {
                return null;
            }
            throw new IOException("unexpected end-of-contents marker");
        }
        int h2 = h(this, read);
        int f2 = f();
        if (f2 >= 0) {
            try {
                return c(read, h2, f2);
            } catch (IllegalArgumentException e2) {
                throw new ASN1Exception("corrupted stream detected", e2);
            }
        } else if ((read & 32) != 0) {
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this, this.limit), this.limit, this.tmpBuffers);
            int i2 = read & 192;
            if (i2 != 0) {
                return aSN1StreamParser.c(i2, h2);
            }
            if (h2 != 3) {
                if (h2 != 4) {
                    if (h2 != 8) {
                        if (h2 != 16) {
                            if (h2 == 17) {
                                return BERSetParser.a(aSN1StreamParser);
                            }
                            throw new IOException("unknown BER object encountered");
                        }
                        return BERSequenceParser.a(aSN1StreamParser);
                    }
                    return DERExternalParser.a(aSN1StreamParser);
                }
                return BEROctetStringParser.a(aSN1StreamParser);
            }
            return BERBitStringParser.a(aSN1StreamParser);
        } else {
            throw new IOException("indefinite-length primitive encoding encountered");
        }
    }
}
