package org.bouncycastle.crypto.util;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Strings;
/* loaded from: classes3.dex */
public final class DERMacData {
    private final byte[] macData;

    /* renamed from: org.bouncycastle.crypto.util.DERMacData$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f13522a;

        static {
            int[] iArr = new int[Type.values().length];
            f13522a = iArr;
            try {
                iArr[Type.UNILATERALU.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13522a[Type.BILATERALU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13522a[Type.UNILATERALV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f13522a[Type.BILATERALV.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes3.dex */
    public static final class Builder {
        private ASN1OctetString ephemDataU;
        private ASN1OctetString ephemDataV;
        private ASN1OctetString idU;
        private ASN1OctetString idV;
        private byte[] text;
        private final Type type;

        public Builder(Type type, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
            this.type = type;
            this.idU = DerUtil.a(bArr);
            this.idV = DerUtil.a(bArr2);
            this.ephemDataU = DerUtil.a(bArr3);
            this.ephemDataV = DerUtil.a(bArr4);
        }

        private byte[] concatenate(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            return Arrays.concatenate(Arrays.concatenate(bArr, bArr2, bArr3), Arrays.concatenate(bArr4, bArr5, bArr6));
        }

        public DERMacData build() {
            int i2 = AnonymousClass1.f13522a[this.type.ordinal()];
            if (i2 == 1 || i2 == 2) {
                return new DERMacData(concatenate(this.type.getHeader(), DerUtil.b(this.idU), DerUtil.b(this.idV), DerUtil.b(this.ephemDataU), DerUtil.b(this.ephemDataV), this.text), null);
            }
            if (i2 == 3 || i2 == 4) {
                return new DERMacData(concatenate(this.type.getHeader(), DerUtil.b(this.idV), DerUtil.b(this.idU), DerUtil.b(this.ephemDataV), DerUtil.b(this.ephemDataU), this.text), null);
            }
            throw new IllegalStateException("Unknown type encountered in build");
        }

        public Builder withText(byte[] bArr) {
            this.text = DerUtil.b(new DERTaggedObject(false, 0, (ASN1Encodable) DerUtil.a(bArr)));
            return this;
        }
    }

    /* loaded from: classes3.dex */
    public enum Type {
        UNILATERALU("KC_1_U"),
        UNILATERALV("KC_1_V"),
        BILATERALU("KC_2_U"),
        BILATERALV("KC_2_V");
        
        private final String enc;

        Type(String str) {
            this.enc = str;
        }

        public byte[] getHeader() {
            return Strings.toByteArray(this.enc);
        }
    }

    private DERMacData(byte[] bArr) {
        this.macData = bArr;
    }

    /* synthetic */ DERMacData(byte[] bArr, AnonymousClass1 anonymousClass1) {
        this(bArr);
    }

    public byte[] getMacData() {
        return Arrays.clone(this.macData);
    }
}
