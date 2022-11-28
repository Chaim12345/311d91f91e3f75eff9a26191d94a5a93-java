package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes3.dex */
public final class UByte implements Comparable<UByte> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @PublishedApi
    private /* synthetic */ UByte(byte b2) {
        this.data = b2;
    }

    @InlineOnly
    /* renamed from: and-7apg3OU  reason: not valid java name */
    private static final byte m198and7apg3OU(byte b2, byte b3) {
        return m205constructorimpl((byte) (b2 & b3));
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UByte m199boximpl(byte b2) {
        return new UByte(b2);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private int m200compareTo7apg3OU(byte b2) {
        return Intrinsics.compare(m254unboximpl() & 255, b2 & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static int m201compareTo7apg3OU(byte b2, byte b3) {
        return Intrinsics.compare(b2 & 255, b3 & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m202compareToVKZWuLQ(byte b2, long j2) {
        return UnsignedKt.ulongCompare(ULong.m359constructorimpl(b2 & 255), j2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m203compareToWZ4Q5Ns(byte b2, int i2) {
        return UnsignedKt.uintCompare(UInt.m281constructorimpl(b2 & 255), i2);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m204compareToxj2QHRw(byte b2, short s2) {
        return Intrinsics.compare(b2 & 255, s2 & UShort.MAX_VALUE);
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static byte m205constructorimpl(byte b2) {
        return b2;
    }

    @InlineOnly
    /* renamed from: dec-w2LRezQ  reason: not valid java name */
    private static final byte m206decw2LRezQ(byte b2) {
        return m205constructorimpl((byte) (b2 - 1));
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m207div7apg3OU(byte b2, byte b3) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(b3 & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m208divVKZWuLQ(byte b2, long j2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(ULong.m359constructorimpl(b2 & 255), j2);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m209divWZ4Q5Ns(byte b2, int i2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(b2 & 255), i2);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m210divxj2QHRw(byte b2, short s2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m211equalsimpl(byte b2, Object obj) {
        return (obj instanceof UByte) && b2 == ((UByte) obj).m254unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m212equalsimpl0(byte b2, byte b3) {
        return b2 == b3;
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m213floorDiv7apg3OU(byte b2, byte b3) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(b3 & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m214floorDivVKZWuLQ(byte b2, long j2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(ULong.m359constructorimpl(b2 & 255), j2);
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m215floorDivWZ4Q5Ns(byte b2, int i2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(b2 & 255), i2);
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m216floorDivxj2QHRw(byte b2, short s2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m217hashCodeimpl(byte b2) {
        return b2;
    }

    @InlineOnly
    /* renamed from: inc-w2LRezQ  reason: not valid java name */
    private static final byte m218incw2LRezQ(byte b2) {
        return m205constructorimpl((byte) (b2 + 1));
    }

    @InlineOnly
    /* renamed from: inv-w2LRezQ  reason: not valid java name */
    private static final byte m219invw2LRezQ(byte b2) {
        return m205constructorimpl((byte) (~b2));
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m220minus7apg3OU(byte b2, byte b3) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) - UInt.m281constructorimpl(b3 & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m221minusVKZWuLQ(byte b2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(b2 & 255) - j2);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m222minusWZ4Q5Ns(byte b2, int i2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) - i2);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m223minusxj2QHRw(byte b2, short s2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) - UInt.m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m224mod7apg3OU(byte b2, byte b3) {
        return m205constructorimpl((byte) UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(b3 & 255)));
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m225modVKZWuLQ(byte b2, long j2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(ULong.m359constructorimpl(b2 & 255), j2);
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m226modWZ4Q5Ns(byte b2, int i2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(b2 & 255), i2);
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m227modxj2QHRw(byte b2, short s2) {
        return UShort.m465constructorimpl((short) UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(s2 & UShort.MAX_VALUE)));
    }

    @InlineOnly
    /* renamed from: or-7apg3OU  reason: not valid java name */
    private static final byte m228or7apg3OU(byte b2, byte b3) {
        return m205constructorimpl((byte) (b2 | b3));
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m229plus7apg3OU(byte b2, byte b3) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) + UInt.m281constructorimpl(b3 & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m230plusVKZWuLQ(byte b2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(b2 & 255) + j2);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m231plusWZ4Q5Ns(byte b2, int i2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) + i2);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m232plusxj2QHRw(byte b2, short s2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) + UInt.m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-7apg3OU  reason: not valid java name */
    private static final UIntRange m233rangeTo7apg3OU(byte b2, byte b3) {
        return new UIntRange(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(b3 & 255), null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m234rem7apg3OU(byte b2, byte b3) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(b3 & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m235remVKZWuLQ(byte b2, long j2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(ULong.m359constructorimpl(b2 & 255), j2);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m236remWZ4Q5Ns(byte b2, int i2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(b2 & 255), i2);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m237remxj2QHRw(byte b2, short s2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(b2 & 255), UInt.m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m238times7apg3OU(byte b2, byte b3) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) * UInt.m281constructorimpl(b3 & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m239timesVKZWuLQ(byte b2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(b2 & 255) * j2);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m240timesWZ4Q5Ns(byte b2, int i2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) * i2);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m241timesxj2QHRw(byte b2, short s2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(b2 & 255) * UInt.m281constructorimpl(s2 & UShort.MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m242toByteimpl(byte b2) {
        return b2;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m243toDoubleimpl(byte b2) {
        return b2 & 255;
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m244toFloatimpl(byte b2) {
        return b2 & 255;
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m245toIntimpl(byte b2) {
        return b2 & 255;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m246toLongimpl(byte b2) {
        return b2 & 255;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m247toShortimpl(byte b2) {
        return (short) (b2 & 255);
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m248toStringimpl(byte b2) {
        return String.valueOf(b2 & 255);
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m249toUBytew2LRezQ(byte b2) {
        return b2;
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m250toUIntpVg5ArA(byte b2) {
        return UInt.m281constructorimpl(b2 & 255);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m251toULongsVKNKU(byte b2) {
        return ULong.m359constructorimpl(b2 & 255);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m252toUShortMh2AYeg(byte b2) {
        return UShort.m465constructorimpl((short) (b2 & 255));
    }

    @InlineOnly
    /* renamed from: xor-7apg3OU  reason: not valid java name */
    private static final byte m253xor7apg3OU(byte b2, byte b3) {
        return m205constructorimpl((byte) (b2 ^ b3));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UByte uByte) {
        return Intrinsics.compare(m254unboximpl() & 255, uByte.m254unboximpl() & 255);
    }

    public boolean equals(Object obj) {
        return m211equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m217hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m248toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ byte m254unboximpl() {
        return this.data;
    }
}
