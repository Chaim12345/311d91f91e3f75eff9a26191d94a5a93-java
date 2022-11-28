package org.bouncycastle.oer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Integer;
/* loaded from: classes4.dex */
public class OERDefinition {
    private static final BigInteger[] uIntMax = {new BigInteger("256"), new BigInteger("65536"), new BigInteger("4294967296"), new BigInteger("18446744073709551616")};
    private static final BigInteger[][] sIntRange = {new BigInteger[]{new BigInteger("-128"), new BigInteger("127")}, new BigInteger[]{new BigInteger("-32768"), new BigInteger("32767")}, new BigInteger[]{new BigInteger("-2147483648"), new BigInteger("2147483647")}, new BigInteger[]{new BigInteger("-9223372036854775808"), new BigInteger("9223372036854775807")}};

    /* loaded from: classes4.dex */
    public enum BaseType {
        SEQ,
        SEQ_OF,
        CHOICE,
        ENUM,
        INT,
        OCTET_STRING,
        UTF8_STRING,
        BIT_STRING,
        NULL,
        EXTENSION,
        ENUM_ITEM,
        BOOLEAN,
        IS0646String,
        PrintableString,
        NumericString,
        BMPString,
        UniversalString,
        IA5String,
        VisibleString
    }

    /* loaded from: classes4.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        protected final BaseType f14372a;

        /* renamed from: b  reason: collision with root package name */
        protected ArrayList f14373b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        protected boolean f14374c = false;

        /* renamed from: d  reason: collision with root package name */
        protected String f14375d;

        /* renamed from: e  reason: collision with root package name */
        protected BigInteger f14376e;

        /* renamed from: f  reason: collision with root package name */
        protected BigInteger f14377f;

        /* renamed from: g  reason: collision with root package name */
        protected BigInteger f14378g;

        /* renamed from: h  reason: collision with root package name */
        protected ASN1Encodable f14379h;

        public Builder(BaseType baseType) {
            this.f14372a = baseType;
        }

        private Builder wrap(boolean z, Object obj) {
            if (obj instanceof Builder) {
                return ((Builder) obj).explicit(z);
            }
            if (obj instanceof BaseType) {
                return new Builder((BaseType) obj).explicit(z);
            }
            throw new IllegalStateException("Unable to wrap item in builder");
        }

        public Element build() {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            if (this.f14372a == BaseType.ENUM) {
                HashSet hashSet = new HashSet();
                int i2 = 0;
                for (int i3 = 0; i3 < this.f14373b.size(); i3++) {
                    Builder builder = (Builder) this.f14373b.get(i3);
                    if (builder.f14378g == null) {
                        builder.f14378g = BigInteger.valueOf(i2);
                        i2++;
                    }
                    if (hashSet.contains(builder.f14378g)) {
                        throw new IllegalStateException("duplicate enum value at index " + i3);
                    }
                    hashSet.add(builder.f14378g);
                }
            }
            Iterator it = this.f14373b.iterator();
            boolean z2 = false;
            while (it.hasNext()) {
                Builder builder2 = (Builder) it.next();
                if (!z2 && builder2.f14372a == BaseType.EXTENSION) {
                    if (!builder2.f14373b.isEmpty() || this.f14372a == BaseType.CHOICE) {
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                }
                arrayList.add(builder2.build());
            }
            BaseType baseType = this.f14372a;
            ASN1Encodable aSN1Encodable = this.f14379h;
            if (aSN1Encodable == null && this.f14374c) {
                z = true;
            }
            return new Element(baseType, arrayList, z, this.f14375d, this.f14377f, this.f14376e, z2, this.f14378g, aSN1Encodable);
        }

        public Builder copy() {
            Builder builder = new Builder(this.f14372a);
            Iterator it = this.f14373b.iterator();
            while (it.hasNext()) {
                builder.f14373b.add(((Builder) it.next()).copy());
            }
            builder.f14374c = this.f14374c;
            builder.f14375d = this.f14375d;
            builder.f14376e = this.f14376e;
            builder.f14377f = this.f14377f;
            builder.f14379h = this.f14379h;
            builder.f14378g = this.f14378g;
            return builder;
        }

        public Builder defaultValue(ASN1Encodable aSN1Encodable) {
            Builder copy = copy();
            copy.f14379h = aSN1Encodable;
            return copy;
        }

        public Builder enumValue(BigInteger bigInteger) {
            Builder copy = copy();
            this.f14378g = bigInteger;
            return copy;
        }

        public Builder explicit(boolean z) {
            Builder copy = copy();
            copy.f14374c = z;
            return copy;
        }

        public Builder fixedSize(long j2) {
            Builder copy = copy();
            copy.f14376e = BigInteger.valueOf(j2);
            copy.f14377f = BigInteger.valueOf(j2);
            return copy;
        }

        public Builder items(Object... objArr) {
            Builder copy = copy();
            for (int i2 = 0; i2 != objArr.length; i2++) {
                Object obj = objArr[i2];
                if (obj instanceof OptionalList) {
                    for (Object obj2 : (List) obj) {
                        copy.f14373b.add(wrap(false, obj2));
                    }
                } else if (obj.getClass().isArray()) {
                    items((Object[]) obj);
                } else {
                    copy.f14373b.add(wrap(true, obj));
                }
            }
            return copy;
        }

        public Builder label(String str) {
            Builder copy = copy();
            if (str != null) {
                copy.f14375d = str;
            }
            copy.f14374c = this.f14374c;
            return copy;
        }

        public Builder labelPrefix(String str) {
            Builder copy = copy();
            copy.f14375d = str + " " + this.f14375d;
            return copy;
        }

        public Builder range(long j2, long j3, ASN1Encodable aSN1Encodable) {
            Builder copy = copy();
            copy.f14377f = BigInteger.valueOf(j2);
            copy.f14376e = BigInteger.valueOf(j3);
            copy.f14379h = aSN1Encodable;
            return copy;
        }

        public Builder range(BigInteger bigInteger, BigInteger bigInteger2) {
            Builder copy = copy();
            copy.f14377f = bigInteger;
            copy.f14376e = bigInteger2;
            return copy;
        }

        public Builder rangeToMAXFrom(long j2) {
            Builder copy = copy();
            copy.f14377f = BigInteger.valueOf(j2);
            copy.f14376e = null;
            return copy;
        }

        public Builder rangeZeroTo(long j2) {
            Builder copy = copy();
            copy.f14376e = BigInteger.valueOf(j2);
            copy.f14377f = BigInteger.ZERO;
            return copy;
        }

        public Builder unbounded() {
            Builder copy = copy();
            copy.f14377f = null;
            copy.f14376e = null;
            return copy;
        }
    }

    /* loaded from: classes4.dex */
    public static class Element {
        public final BaseType baseType;
        public final List<Element> children;
        public final ASN1Encodable defaultValue;
        public final BigInteger enumValue;
        public final boolean explicit;
        public final boolean extensionsInDefinition;
        public final String label;
        public final BigInteger lowerBound;
        private List<Element> optionalChildrenInOrder;
        public final BigInteger upperBound;

        public Element(BaseType baseType, List<Element> list, boolean z, String str, BigInteger bigInteger, BigInteger bigInteger2, boolean z2, BigInteger bigInteger3, ASN1Encodable aSN1Encodable) {
            this.baseType = baseType;
            this.children = list;
            this.explicit = z;
            this.label = str;
            this.lowerBound = bigInteger;
            this.upperBound = bigInteger2;
            this.extensionsInDefinition = z2;
            this.enumValue = bigInteger3;
            this.defaultValue = aSN1Encodable;
        }

        public String appendLabel(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            String str2 = this.label;
            if (str2 == null) {
                str2 = "";
            }
            sb.append(str2);
            sb.append(this.explicit ? " (E)" : "");
            sb.append("] ");
            sb.append(str);
            return sb.toString();
        }

        public boolean canBeNegative() {
            BigInteger bigInteger = this.lowerBound;
            return bigInteger != null && BigInteger.ZERO.compareTo(bigInteger) > 0;
        }

        public ASN1Encodable getDefaultValue() {
            return this.defaultValue;
        }

        public Element getFirstChid() {
            return this.children.get(0);
        }

        public boolean hasDefaultChildren() {
            for (Element element : this.children) {
                if (element.defaultValue != null) {
                    return true;
                }
            }
            return false;
        }

        public boolean hasPopulatedExtension() {
            for (Element element : this.children) {
                if (element.baseType == BaseType.EXTENSION) {
                    return true;
                }
            }
            return false;
        }

        public int intBytesForRange() {
            BigInteger bigInteger = this.lowerBound;
            if (bigInteger != null && this.upperBound != null) {
                int i2 = 1;
                if (BigInteger.ZERO.equals(bigInteger)) {
                    int i3 = 0;
                    while (i3 < OERDefinition.uIntMax.length) {
                        if (this.upperBound.compareTo(OERDefinition.uIntMax[i3]) < 0) {
                            return i2;
                        }
                        i3++;
                        i2 *= 2;
                    }
                } else {
                    int i4 = 0;
                    int i5 = 1;
                    while (i4 < OERDefinition.sIntRange.length) {
                        if (this.lowerBound.compareTo(OERDefinition.sIntRange[i4][0]) >= 0 && this.upperBound.compareTo(OERDefinition.sIntRange[i4][1]) < 0) {
                            return -i5;
                        }
                        i4++;
                        i5 *= 2;
                    }
                }
            }
            return 0;
        }

        public boolean isFixedLength() {
            BigInteger bigInteger = this.lowerBound;
            return bigInteger != null && bigInteger.equals(this.upperBound);
        }

        public boolean isLowerRangeZero() {
            return BigInteger.ZERO.equals(this.lowerBound);
        }

        public boolean isUnbounded() {
            return this.upperBound == null && this.lowerBound == null;
        }

        public boolean isUnsignedWithRange() {
            BigInteger bigInteger;
            return isLowerRangeZero() && (bigInteger = this.upperBound) != null && BigInteger.ZERO.compareTo(bigInteger) < 0;
        }

        public List<Element> optionalOrDefaultChildrenInOrder() {
            List<Element> list;
            synchronized (this) {
                if (this.optionalChildrenInOrder == null) {
                    ArrayList arrayList = new ArrayList();
                    for (Element element : this.children) {
                        if (!element.explicit || element.getDefaultValue() != null) {
                            arrayList.add(element);
                        }
                    }
                    this.optionalChildrenInOrder = Collections.unmodifiableList(arrayList);
                }
                list = this.optionalChildrenInOrder;
            }
            return list;
        }

        public String rangeExpression() {
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            BigInteger bigInteger = this.lowerBound;
            sb.append(bigInteger != null ? bigInteger.toString() : "MIN");
            sb.append(" ... ");
            BigInteger bigInteger2 = this.upperBound;
            sb.append(bigInteger2 != null ? bigInteger2.toString() : "MAX");
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes4.dex */
    public static class MutableBuilder extends Builder {
        private boolean frozen;

        public MutableBuilder(BaseType baseType) {
            super(baseType);
            this.frozen = false;
        }

        public void addItemsAndFreeze(Builder... builderArr) {
            if (this.frozen) {
                throw new IllegalStateException("build cannot be modified and must be copied only");
            }
            for (int i2 = 0; i2 != builderArr.length; i2++) {
                this.f14373b.add(builderArr[i2]);
            }
            this.frozen = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class OptionalList extends ArrayList<Object> {
        public OptionalList(List<Object> list) {
            addAll(list);
        }
    }

    public static Builder bitString(long j2) {
        return new Builder(BaseType.BIT_STRING).fixedSize(j2);
    }

    public static Builder choice(Object... objArr) {
        return new Builder(BaseType.CHOICE).items(objArr);
    }

    public static Builder enumItem(String str) {
        return new Builder(BaseType.ENUM_ITEM).label(str);
    }

    public static Builder enumItem(String str, BigInteger bigInteger) {
        return new Builder(BaseType.ENUM_ITEM).enumValue(bigInteger).label(str);
    }

    public static Builder enumeration(Object... objArr) {
        return new Builder(BaseType.ENUM).items(objArr);
    }

    public static Builder extension() {
        return new Builder(BaseType.EXTENSION).label("extension");
    }

    public static Builder integer() {
        return new Builder(BaseType.INT);
    }

    public static Builder integer(long j2) {
        return new Builder(BaseType.INT).defaultValue(new ASN1Integer(j2));
    }

    public static Builder integer(long j2, long j3) {
        return new Builder(BaseType.INT).range(BigInteger.valueOf(j2), BigInteger.valueOf(j3));
    }

    public static Builder integer(long j2, long j3, ASN1Encodable aSN1Encodable) {
        return new Builder(BaseType.INT).range(j2, j3, aSN1Encodable);
    }

    public static Builder integer(BigInteger bigInteger, BigInteger bigInteger2) {
        return new Builder(BaseType.INT).range(bigInteger, bigInteger2);
    }

    public static Builder nullValue() {
        return new Builder(BaseType.NULL);
    }

    public static Builder octets() {
        return new Builder(BaseType.OCTET_STRING).unbounded();
    }

    public static Builder octets(int i2) {
        return new Builder(BaseType.OCTET_STRING).fixedSize(i2);
    }

    public static Builder octets(int i2, int i3) {
        return new Builder(BaseType.OCTET_STRING).range(BigInteger.valueOf(i2), BigInteger.valueOf(i3));
    }

    public static Builder opaque() {
        return new Builder(BaseType.OCTET_STRING).unbounded();
    }

    public static List<Object> optional(Object... objArr) {
        return new OptionalList(Arrays.asList(objArr));
    }

    public static Builder placeholder() {
        return new Builder(null);
    }

    public static Builder seq() {
        return new Builder(BaseType.SEQ);
    }

    public static Builder seq(Object... objArr) {
        return new Builder(BaseType.SEQ).items(objArr);
    }

    public static Builder seqof(Object... objArr) {
        return new Builder(BaseType.SEQ_OF).items(objArr);
    }

    public static Builder utf8String() {
        return new Builder(BaseType.UTF8_STRING);
    }

    public static Builder utf8String(int i2) {
        return new Builder(BaseType.UTF8_STRING).rangeToMAXFrom(i2);
    }

    public static Builder utf8String(int i2, int i3) {
        return new Builder(BaseType.UTF8_STRING).range(BigInteger.valueOf(i2), BigInteger.valueOf(i3));
    }
}
