package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import okhttp3.internal.ws.WebSocketProtocol;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes3.dex */
public final class UShort implements Comparable<UShort> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @PublishedApi
    private /* synthetic */ UShort(short s2) {
        this.data = s2;
    }

    @InlineOnly
    /* renamed from: and-xj2QHRw  reason: not valid java name */
    private static final short m458andxj2QHRw(short s2, short s3) {
        return m465constructorimpl((short) (s2 & s3));
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ UShort m459boximpl(short s2) {
        return new UShort(s2);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m460compareTo7apg3OU(short s2, byte b2) {
        return Intrinsics.compare(s2 & MAX_VALUE, b2 & 255);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static final int m461compareToVKZWuLQ(short s2, long j2) {
        return UnsignedKt.ulongCompare(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX), j2);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m462compareToWZ4Q5Ns(short s2, int i2) {
        return UnsignedKt.uintCompare(UInt.m281constructorimpl(s2 & MAX_VALUE), i2);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private int m463compareToxj2QHRw(short s2) {
        return Intrinsics.compare(m514unboximpl() & MAX_VALUE, s2 & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static int m464compareToxj2QHRw(short s2, short s3) {
        return Intrinsics.compare(s2 & MAX_VALUE, s3 & MAX_VALUE);
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static short m465constructorimpl(short s2) {
        return s2;
    }

    @InlineOnly
    /* renamed from: dec-Mh2AYeg  reason: not valid java name */
    private static final short m466decMh2AYeg(short s2) {
        return m465constructorimpl((short) (s2 - 1));
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final int m467div7apg3OU(short s2, byte b2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m468divVKZWuLQ(short s2, long j2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX), j2);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final int m469divWZ4Q5Ns(short s2, int i2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), i2);
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final int m470divxj2QHRw(short s2, short s3) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(s3 & MAX_VALUE));
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m471equalsimpl(short s2, Object obj) {
        return (obj instanceof UShort) && s2 == ((UShort) obj).m514unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m472equalsimpl0(short s2, short s3) {
        return s2 == s3;
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final int m473floorDiv7apg3OU(short s2, byte b2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m474floorDivVKZWuLQ(short s2, long j2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX), j2);
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final int m475floorDivWZ4Q5Ns(short s2, int i2) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), i2);
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final int m476floorDivxj2QHRw(short s2, short s3) {
        return UnsignedKt.m534uintDivideJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(s3 & MAX_VALUE));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m477hashCodeimpl(short s2) {
        return s2;
    }

    @InlineOnly
    /* renamed from: inc-Mh2AYeg  reason: not valid java name */
    private static final short m478incMh2AYeg(short s2) {
        return m465constructorimpl((short) (s2 + 1));
    }

    @InlineOnly
    /* renamed from: inv-Mh2AYeg  reason: not valid java name */
    private static final short m479invMh2AYeg(short s2) {
        return m465constructorimpl((short) (~s2));
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final int m480minus7apg3OU(short s2, byte b2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) - UInt.m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m481minusVKZWuLQ(short s2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) - j2);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final int m482minusWZ4Q5Ns(short s2, int i2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) - i2);
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final int m483minusxj2QHRw(short s2, short s3) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) - UInt.m281constructorimpl(s3 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m484mod7apg3OU(short s2, byte b2) {
        return UByte.m205constructorimpl((byte) UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(b2 & 255)));
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m485modVKZWuLQ(short s2, long j2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX), j2);
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m486modWZ4Q5Ns(short s2, int i2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), i2);
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m487modxj2QHRw(short s2, short s3) {
        return m465constructorimpl((short) UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(s3 & MAX_VALUE)));
    }

    @InlineOnly
    /* renamed from: or-xj2QHRw  reason: not valid java name */
    private static final short m488orxj2QHRw(short s2, short s3) {
        return m465constructorimpl((short) (s2 | s3));
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final int m489plus7apg3OU(short s2, byte b2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) + UInt.m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m490plusVKZWuLQ(short s2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) + j2);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final int m491plusWZ4Q5Ns(short s2, int i2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) + i2);
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final int m492plusxj2QHRw(short s2, short s3) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) + UInt.m281constructorimpl(s3 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: rangeTo-xj2QHRw  reason: not valid java name */
    private static final UIntRange m493rangeToxj2QHRw(short s2, short s3) {
        return new UIntRange(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(s3 & MAX_VALUE), null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final int m494rem7apg3OU(short s2, byte b2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m495remVKZWuLQ(short s2, long j2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX), j2);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final int m496remWZ4Q5Ns(short s2, int i2) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), i2);
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final int m497remxj2QHRw(short s2, short s3) {
        return UnsignedKt.m535uintRemainderJ1ME1BU(UInt.m281constructorimpl(s2 & MAX_VALUE), UInt.m281constructorimpl(s3 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final int m498times7apg3OU(short s2, byte b2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) * UInt.m281constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m499timesVKZWuLQ(short s2, long j2) {
        return ULong.m359constructorimpl(ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX) * j2);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final int m500timesWZ4Q5Ns(short s2, int i2) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) * i2);
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final int m501timesxj2QHRw(short s2, short s3) {
        return UInt.m281constructorimpl(UInt.m281constructorimpl(s2 & MAX_VALUE) * UInt.m281constructorimpl(s3 & MAX_VALUE));
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m502toByteimpl(short s2) {
        return (byte) s2;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m503toDoubleimpl(short s2) {
        return s2 & MAX_VALUE;
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m504toFloatimpl(short s2) {
        return s2 & MAX_VALUE;
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m505toIntimpl(short s2) {
        return s2 & MAX_VALUE;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m506toLongimpl(short s2) {
        return s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m507toShortimpl(short s2) {
        return s2;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m508toStringimpl(short s2) {
        return String.valueOf(s2 & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m509toUBytew2LRezQ(short s2) {
        return UByte.m205constructorimpl((byte) s2);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m510toUIntpVg5ArA(short s2) {
        return UInt.m281constructorimpl(s2 & MAX_VALUE);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m511toULongsVKNKU(short s2) {
        return ULong.m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX);
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m512toUShortMh2AYeg(short s2) {
        return s2;
    }

    @InlineOnly
    /* renamed from: xor-xj2QHRw  reason: not valid java name */
    private static final short m513xorxj2QHRw(short s2, short s3) {
        return m465constructorimpl((short) (s2 ^ s3));
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(UShort uShort) {
        return Intrinsics.compare(m514unboximpl() & MAX_VALUE, uShort.m514unboximpl() & MAX_VALUE);
    }

    public boolean equals(Object obj) {
        return m471equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m477hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m508toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ short m514unboximpl() {
        return this.data;
    }
}
