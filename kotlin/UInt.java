package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes3.dex */
public final class UInt implements Comparable<UInt> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 32;
    public static final int SIZE_BYTES = 4;
    private final int data;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @PublishedApi
    private /* synthetic */ UInt(int i2) {
        this.data = i2;
    }

    @InlineOnly
    /* renamed from: and-WZ4Q5Ns  reason: not valid java name */
    private static final int m274andWZ4Q5Ns(int i2, int i3) {
        return m281constructorimpl(i2 & i3);
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UInt m275boximpl(int i2) {
        return new UInt(i2);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m276compareTo7apg3OU(int i2, byte b2) {
        return UnsignedKt.uintCompare(i2, m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m277compareToVKZWuLQ(int i2, long j2) {
        return UnsignedKt.ulongCompare(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax), j2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private int m278compareToWZ4Q5Ns(int i2) {
        return UnsignedKt.uintCompare(m332unboximpl(), i2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static int m279compareToWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.uintCompare(i2, i3);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m280compareToxj2QHRw(int i2, short s2) {
        return UnsignedKt.uintCompare(i2, m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m281constructorimpl(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: dec-pVg5ArA  reason: not valid java name */
    private static final int m282decpVg5ArA(int i2) {
        return m281constructorimpl(i2 - 1);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m283div7apg3OU(int i2, byte b2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(i2, m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m284divVKZWuLQ(int i2, long j2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax), j2);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m285divWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m534uintDivideJ1ME1BU(i2, i3);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m286divxj2QHRw(int i2, short s2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(i2, m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m287equalsimpl(int i2, Object obj) {
        return (obj instanceof UInt) && i2 == ((UInt) obj).m332unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m288equalsimpl0(int i2, int i3) {
        return i2 == i3;
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m289floorDiv7apg3OU(int i2, byte b2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(i2, m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m290floorDivVKZWuLQ(int i2, long j2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax), j2);
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m291floorDivWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m534uintDivideJ1ME1BU(i2, i3);
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m292floorDivxj2QHRw(int i2, short s2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(i2, m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m293hashCodeimpl(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: inc-pVg5ArA  reason: not valid java name */
    private static final int m294incpVg5ArA(int i2) {
        return m281constructorimpl(i2 + 1);
    }

    @InlineOnly
    /* renamed from: inv-pVg5ArA  reason: not valid java name */
    private static final int m295invpVg5ArA(int i2) {
        return m281constructorimpl(~i2);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m296minus7apg3OU(int i2, byte b2) {
        return m281constructorimpl(i2 - m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m297minusVKZWuLQ(int i2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax) - j2);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m298minusWZ4Q5Ns(int i2, int i3) {
        return m281constructorimpl(i2 - i3);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m299minusxj2QHRw(int i2, short s2) {
        return m281constructorimpl(i2 - m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m300mod7apg3OU(int i2, byte b2) {
        return UByte.m205constructorimpl((byte) UnsignedKt.m535uintRemainderJ1ME1BU(i2, m281constructorimpl(b2 & 255)));
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m301modVKZWuLQ(int i2, long j2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax), j2);
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m302modWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(i2, i3);
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m303modxj2QHRw(int i2, short s2) {
        return UShort.m465constructorimpl((short) UnsignedKt.m535uintRemainderJ1ME1BU(i2, m281constructorimpl(s2 & UShort.MAX_VALUE)));
    }

    @InlineOnly
    /* renamed from: or-WZ4Q5Ns  reason: not valid java name */
    private static final int m304orWZ4Q5Ns(int i2, int i3) {
        return m281constructorimpl(i2 | i3);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m305plus7apg3OU(int i2, byte b2) {
        return m281constructorimpl(i2 + m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m306plusVKZWuLQ(int i2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax) + j2);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m307plusWZ4Q5Ns(int i2, int i3) {
        return m281constructorimpl(i2 + i3);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m308plusxj2QHRw(int i2, short s2) {
        return m281constructorimpl(i2 + m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-WZ4Q5Ns  reason: not valid java name */
    private static final UIntRange m309rangeToWZ4Q5Ns(int i2, int i3) {
        return new UIntRange(i2, i3, null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m310rem7apg3OU(int i2, byte b2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(i2, m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m311remVKZWuLQ(int i2, long j2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax), j2);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m312remWZ4Q5Ns(int i2, int i3) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(i2, i3);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m313remxj2QHRw(int i2, short s2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(i2, m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: shl-pVg5ArA  reason: not valid java name */
    private static final int m314shlpVg5ArA(int i2, int i3) {
        return m281constructorimpl(i2 << i3);
    }

    @InlineOnly
    /* renamed from: shr-pVg5ArA  reason: not valid java name */
    private static final int m315shrpVg5ArA(int i2, int i3) {
        return m281constructorimpl(i2 >>> i3);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m316times7apg3OU(int i2, byte b2) {
        return m281constructorimpl(i2 * m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m317timesVKZWuLQ(int i2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax) * j2);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m318timesWZ4Q5Ns(int i2, int i3) {
        return m281constructorimpl(i2 * i3);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m319timesxj2QHRw(int i2, short s2) {
        return m281constructorimpl(i2 * m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m320toByteimpl(int i2) {
        return (byte) i2;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m321toDoubleimpl(int i2) {
        return UnsignedKt.uintToDouble(i2);
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m322toFloatimpl(int i2) {
        return (float) UnsignedKt.uintToDouble(i2);
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m323toIntimpl(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m324toLongimpl(int i2) {
        return i2 & BodyPartID.bodyIdMax;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m325toShortimpl(int i2) {
        return (short) i2;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m326toStringimpl(int i2) {
        return String.valueOf(i2 & BodyPartID.bodyIdMax);
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m327toUBytew2LRezQ(int i2) {
        return UByte.m205constructorimpl((byte) i2);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m328toUIntpVg5ArA(int i2) {
        return i2;
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m329toULongsVKNKU(int i2) {
        return ULong.m359constructorimpl(i2 & BodyPartID.bodyIdMax);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m330toUShortMh2AYeg(int i2) {
        return UShort.m465constructorimpl((short) i2);
    }

    @InlineOnly
    /* renamed from: xor-WZ4Q5Ns  reason: not valid java name */
    private static final int m331xorWZ4Q5Ns(int i2, int i3) {
        return m281constructorimpl(i2 ^ i3);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UInt uInt) {
        return UnsignedKt.uintCompare(m332unboximpl(), uInt.m332unboximpl());
    }

    public boolean equals(Object obj) {
        return m287equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m293hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m326toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m332unboximpl() {
        return this.data;
    }
}
