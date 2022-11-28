package org.bouncycastle.oer;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Enumerated;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.oer.OERDefinition;
import org.bouncycastle.util.BigIntegers;
import org.bouncycastle.util.Pack;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.io.Streams;
/* loaded from: classes4.dex */
public class OERInputStream extends FilterInputStream {
    private static final int[] bits = {1, 2, 4, 8, 16, 32, 64, 128};

    /* renamed from: a  reason: collision with root package name */
    protected PrintWriter f14380a;
    private int maxByteAllocation;

    /* renamed from: org.bouncycastle.oer.OERInputStream$1  reason: invalid class name */
    /* loaded from: classes4.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f14381a;

        static {
            int[] iArr = new int[OERDefinition.BaseType.values().length];
            f14381a = iArr;
            try {
                iArr[OERDefinition.BaseType.SEQ_OF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f14381a[OERDefinition.BaseType.SEQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f14381a[OERDefinition.BaseType.CHOICE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f14381a[OERDefinition.BaseType.ENUM.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f14381a[OERDefinition.BaseType.INT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f14381a[OERDefinition.BaseType.OCTET_STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f14381a[OERDefinition.BaseType.UTF8_STRING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f14381a[OERDefinition.BaseType.BIT_STRING.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f14381a[OERDefinition.BaseType.NULL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f14381a[OERDefinition.BaseType.EXTENSION.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    /* loaded from: classes4.dex */
    public static class Choice extends OERInputStream {

        /* renamed from: b  reason: collision with root package name */
        final int f14382b;

        /* renamed from: c  reason: collision with root package name */
        final int f14383c;

        /* renamed from: d  reason: collision with root package name */
        final int f14384d;

        public Choice(InputStream inputStream) {
            super(inputStream);
            int read;
            int read2 = read();
            this.f14382b = read2;
            if (read2 < 0) {
                throw new EOFException("expecting preamble byte of choice");
            }
            this.f14384d = read2 & 192;
            int i2 = read2 & 63;
            if (i2 < 63) {
                this.f14383c = i2;
            }
            i2 = 0;
            do {
                read = inputStream.read();
                if (read < 0) {
                    throw new EOFException("expecting further tag bytes");
                }
                i2 = (i2 << 7) | (read & 127);
            } while ((read & 128) != 0);
            this.f14383c = i2;
        }

        public int getTag() {
            return this.f14383c;
        }

        public int getTagClass() {
            return this.f14384d;
        }

        public boolean isApplicationTagClass() {
            return this.f14384d == 64;
        }

        public boolean isContextSpecific() {
            return this.f14384d == 128;
        }

        public boolean isPrivateTagClass() {
            return this.f14384d == 192;
        }

        public boolean isUniversalTagClass() {
            return this.f14384d == 0;
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("CHOICE(");
            int i2 = this.f14384d;
            if (i2 == 0) {
                str = "Universal ";
            } else if (i2 == 64) {
                str = "Application ";
            } else if (i2 != 128) {
                if (i2 == 192) {
                    str = "Private ";
                }
                sb.append("Tag = " + this.f14383c);
                sb.append(")");
                return sb.toString();
            } else {
                str = "ContextSpecific ";
            }
            sb.append(str);
            sb.append("Tag = " + this.f14383c);
            sb.append(")");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public final class LengthInfo {
        private final BigInteger length;
        private final boolean shortForm;

        public LengthInfo(OERInputStream oERInputStream, BigInteger bigInteger, boolean z) {
            this.length = bigInteger;
            this.shortForm = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int intLength() {
            return this.length.intValue();
        }
    }

    /* loaded from: classes4.dex */
    public static class Sequence extends OERInputStream {

        /* renamed from: b  reason: collision with root package name */
        final int f14385b;
        private final boolean extensionFlagSet;
        private final boolean[] optionalPresent;

        public Sequence(InputStream inputStream, int i2, boolean z, boolean z2) {
            super(inputStream);
            if (i2 == 0 && !z2 && !z) {
                this.f14385b = 0;
                this.optionalPresent = new boolean[0];
                this.extensionFlagSet = false;
                return;
            }
            int read = inputStream.read();
            this.f14385b = read;
            if (read < 0) {
                throw new EOFException("expecting preamble byte of sequence");
            }
            this.extensionFlagSet = z2 && (read & 128) == 128;
            int i3 = z2 ? 6 : 7;
            this.optionalPresent = new boolean[i2];
            for (int i4 = 0; i4 < this.optionalPresent.length; i4++) {
                if (i3 < 0) {
                    read = inputStream.read();
                    if (read < 0) {
                        throw new EOFException("expecting mask byte sequence");
                    }
                    i3 = 7;
                }
                this.optionalPresent[i4] = (OERInputStream.bits[i3] & read) > 0;
                i3--;
            }
        }

        public boolean hasExtension() {
            return this.extensionFlagSet;
        }

        public boolean hasOptional(int i2) {
            return this.optionalPresent[i2];
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("SEQ(");
            sb.append(hasExtension() ? "Ext " : "");
            int i2 = 0;
            while (true) {
                boolean[] zArr = this.optionalPresent;
                if (i2 >= zArr.length) {
                    sb.append(")");
                    return sb.toString();
                }
                sb.append(zArr[i2] ? "1" : "0");
                i2++;
            }
        }
    }

    public OERInputStream(InputStream inputStream) {
        super(inputStream);
        this.maxByteAllocation = 1048576;
        this.f14380a = null;
    }

    public OERInputStream(InputStream inputStream, int i2) {
        super(inputStream);
        this.maxByteAllocation = 1048576;
        this.f14380a = null;
        this.maxByteAllocation = i2;
    }

    private ASN1Encodable absent(OERDefinition.Element element) {
        b(element.appendLabel("Absent"));
        return OEROptional.ABSENT;
    }

    private byte[] allocateArray(int i2) {
        if (i2 <= this.maxByteAllocation) {
            return new byte[i2];
        }
        throw new IllegalArgumentException("required byte array size " + i2 + " was greater than " + this.maxByteAllocation);
    }

    private int countOptionalChildTypes(OERDefinition.Element element) {
        int i2 = 0;
        for (OERDefinition.Element element2 : element.children) {
            i2 += !element2.explicit ? 1 : 0;
        }
        return i2;
    }

    protected void b(String str) {
        if (this.f14380a == null) {
            return;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i2 = -1;
        for (int i3 = 0; i3 != stackTrace.length; i3++) {
            StackTraceElement stackTraceElement = stackTrace[i3];
            if (stackTraceElement.getMethodName().equals("debugPrint")) {
                i2 = 0;
            } else if (stackTraceElement.getClassName().contains("OERInput")) {
                i2++;
            }
        }
        while (true) {
            PrintWriter printWriter = this.f14380a;
            if (i2 <= 0) {
                printWriter.append((CharSequence) str).append((CharSequence) "\n");
                this.f14380a.flush();
                return;
            }
            printWriter.append((CharSequence) "    ");
            i2--;
        }
    }

    public Choice choice() {
        return new Choice(this);
    }

    public BigInteger enumeration() {
        int read = read();
        if (read != -1) {
            if ((read & 128) == 128) {
                int i2 = read & 127;
                if (i2 == 0) {
                    return BigInteger.ZERO;
                }
                byte[] bArr = new byte[i2];
                if (Streams.readFully(this, bArr) == i2) {
                    return new BigInteger(1, bArr);
                }
                throw new EOFException("unable to fully read integer component of enumeration");
            }
            return BigInteger.valueOf(read);
        }
        throw new EOFException("expecting prefix of enumeration");
    }

    public BigInteger int16() {
        return parseInt(false, 2);
    }

    public BigInteger int32() {
        return parseInt(false, 4);
    }

    public BigInteger int64() {
        return parseInt(false, 8);
    }

    public BigInteger int8() {
        return parseInt(false, 1);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public ASN1Object parse(OERDefinition.Element element) {
        ASN1Encodable absent;
        byte[] allocateArray;
        BigInteger bigInteger;
        int i2;
        long j2;
        switch (AnonymousClass1.f14381a[element.baseType.ordinal()]) {
            case 1:
                byte[] allocateArray2 = allocateArray(readLength().intLength());
                if (Streams.readFully(this, allocateArray2) == allocateArray2.length) {
                    int intValue = BigIntegers.fromUnsignedByteArray(allocateArray2).intValue();
                    b(element.appendLabel("(len = " + intValue + ")"));
                    ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
                    for (int i3 = 0; i3 < intValue; i3++) {
                        aSN1EncodableVector.add(parse(element.children.get(0)));
                    }
                    return new DERSequence(aSN1EncodableVector);
                }
                throw new IOException("could not read all of count of seq-of values");
            case 2:
                Sequence sequence = sequence(countOptionalChildTypes(element), element.hasDefaultChildren(), element.extensionsInDefinition);
                b(element.appendLabel(sequence.toString()));
                ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
                for (int i4 = 0; i4 < element.children.size(); i4++) {
                    OERDefinition.Element element2 = element.children.get(i4);
                    if (element2.explicit) {
                        absent = parse(element2);
                    } else if (sequence.hasOptional(element.optionalOrDefaultChildrenInOrder().indexOf(element2))) {
                        absent = OEROptional.getInstance(parse(element2));
                    } else if (element2.getDefaultValue() != null) {
                        aSN1EncodableVector2.add(element2.defaultValue);
                        b("Using default.");
                    } else {
                        absent = absent(element2);
                    }
                    aSN1EncodableVector2.add(absent);
                }
                return new DERSequence(aSN1EncodableVector2);
            case 3:
                Choice choice = choice();
                b(element.appendLabel(choice.toString()));
                if (choice.isContextSpecific()) {
                    element.children.get(choice.getTag());
                    return new DERTaggedObject(choice.f14383c, parse(element.children.get(choice.getTag())));
                } else if (choice.isApplicationTagClass()) {
                    throw new IllegalStateException("Unimplemented tag type");
                } else {
                    if (choice.isPrivateTagClass()) {
                        throw new IllegalStateException("Unimplemented tag type");
                    }
                    if (choice.isUniversalTagClass()) {
                        choice.getTag();
                        break;
                    } else {
                        throw new IllegalStateException("Unimplemented tag type");
                    }
                }
            case 4:
                break;
            case 5:
                int intBytesForRange = element.intBytesForRange();
                if (intBytesForRange != 0) {
                    allocateArray = allocateArray(Math.abs(intBytesForRange));
                    Streams.readFully(this, allocateArray);
                    int length = allocateArray.length;
                    if (length == 1) {
                        i2 = allocateArray[0];
                    } else if (length == 2) {
                        i2 = Pack.bigEndianToShort(allocateArray, 0);
                    } else if (length == 4) {
                        i2 = Pack.bigEndianToInt(allocateArray, 0);
                    } else if (length != 8) {
                        throw new IllegalStateException("Unknown size");
                    } else {
                        j2 = Pack.bigEndianToLong(allocateArray, 0);
                        bigInteger = BigInteger.valueOf(j2);
                    }
                    j2 = i2;
                    bigInteger = BigInteger.valueOf(j2);
                } else if (element.isLowerRangeZero()) {
                    allocateArray = allocateArray(readLength().intLength());
                    Streams.readFully(this, allocateArray);
                    if (allocateArray.length != 0) {
                        bigInteger = BigIntegers.fromUnsignedByteArray(allocateArray);
                    }
                    bigInteger = BigInteger.ZERO;
                } else {
                    allocateArray = allocateArray(readLength().intLength());
                    Streams.readFully(this, allocateArray);
                    if (allocateArray.length != 0) {
                        bigInteger = new BigInteger(allocateArray);
                    }
                    bigInteger = BigInteger.ZERO;
                }
                if (this.f14380a != null) {
                    b(element.appendLabel("INTEGER(" + allocateArray.length + " " + bigInteger.toString(16) + ")"));
                }
                return new ASN1Integer(bigInteger);
            case 6:
                BigInteger bigInteger2 = element.upperBound;
                int intLength = (bigInteger2 == null || !bigInteger2.equals(element.lowerBound)) ? readLength().intLength() : element.upperBound.intValue();
                byte[] allocateArray3 = allocateArray(intLength);
                if (Streams.readFully(this, allocateArray3) != intLength) {
                    throw new IOException("did not read all of " + element.label);
                }
                if (this.f14380a != null) {
                    b(element.appendLabel("OCTET STRING (" + allocateArray3.length + ") = " + Hex.toHexString(allocateArray3, 0, Math.min(allocateArray3.length, 32))));
                }
                return new DEROctetString(allocateArray3);
            case 7:
                byte[] allocateArray4 = allocateArray(readLength().intLength());
                if (Streams.readFully(this, allocateArray4) == allocateArray4.length) {
                    String fromUTF8ByteArray = Strings.fromUTF8ByteArray(allocateArray4);
                    if (this.f14380a != null) {
                        b(element.appendLabel("UTF8 String (" + allocateArray4.length + ") = " + fromUTF8ByteArray));
                    }
                    return new DERUTF8String(fromUTF8ByteArray);
                }
                throw new IOException("could not read all of utf 8 string");
            case 8:
                byte[] allocateArray5 = element.isFixedLength() ? new byte[element.lowerBound.intValue() / 8] : allocateArray((BigInteger.ZERO.compareTo(element.upperBound) > 0 ? element.upperBound.intValue() : readLength().intLength()) / 8);
                Streams.readFully(this, allocateArray5);
                if (this.f14380a != null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("BIT STRING(" + (allocateArray5.length * 8) + ") = ");
                    for (int i5 = 0; i5 != allocateArray5.length; i5++) {
                        byte b2 = allocateArray5[i5];
                        for (int i6 = 0; i6 < 8; i6++) {
                            stringBuffer.append((b2 & 128) > 0 ? "1" : "0");
                            b2 = (byte) (b2 << 1);
                        }
                    }
                    b(element.appendLabel(stringBuffer.toString()));
                }
                return new DERBitString(allocateArray5);
            case 9:
                b(element.appendLabel("NULL"));
                return DERNull.INSTANCE;
            case 10:
                LengthInfo readLength = readLength();
                byte[] bArr = new byte[readLength.intLength()];
                if (Streams.readFully(this, bArr) == readLength.intLength()) {
                    b("ext " + readLength.intLength() + " " + Hex.toHexString(bArr));
                    return new DEROctetString(bArr);
                }
                throw new IOException("could not read all of count of open value in choice (...) ");
            default:
                throw new IllegalStateException("Unhandled type " + element.baseType);
        }
        BigInteger enumeration = enumeration();
        b(element.appendLabel("ENUM(" + enumeration + ") = " + element.children.get(enumeration.intValue()).label));
        return new ASN1Enumerated(enumeration);
    }

    public BigInteger parseInt(boolean z, int i2) {
        byte[] bArr = new byte[i2];
        if (Streams.readFully(this, bArr) == i2) {
            return z ? new BigInteger(1, bArr) : new BigInteger(bArr);
        }
        throw new IllegalStateException("integer not fully read");
    }

    public LengthInfo readLength() {
        int read = read();
        if (read != -1) {
            if ((read & 128) == 0) {
                return new LengthInfo(this, BigInteger.valueOf(read & 127), true);
            }
            int i2 = read & 127;
            byte[] bArr = new byte[i2];
            if (Streams.readFully(this, bArr) == i2) {
                Hex.toHexString(bArr);
                return new LengthInfo(this, BigIntegers.fromUnsignedByteArray(bArr), false);
            }
            throw new EOFException("did not read all bytes of length definition");
        }
        throw new EOFException("expecting length");
    }

    public Sequence sequence(int i2, boolean z, boolean z2) {
        return new Sequence(this, i2, z, z2);
    }

    public BigInteger uint16() {
        return parseInt(true, 2);
    }

    public BigInteger uint32() {
        return parseInt(true, 4);
    }

    public BigInteger uint64() {
        return parseInt(false, 8);
    }

    public BigInteger uint8() {
        return parseInt(true, 1);
    }
}
