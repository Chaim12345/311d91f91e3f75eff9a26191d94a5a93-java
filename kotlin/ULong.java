package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import okhttp3.internal.ws.WebSocketProtocol;
import org.bouncycastle.asn1.cmc.BodyPartID;
import org.jetbrains.annotations.NotNull;
@SinceKotlin(version = "1.5")
@JvmInline
@WasExperimental(markerClass = {ExperimentalUnsignedTypes.class})
/* loaded from: classes3.dex */
public final class ULong implements Comparable<ULong> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final long MAX_VALUE = -1;
    public static final long MIN_VALUE = 0;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;
    private final long data;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @PublishedApi
    private /* synthetic */ ULong(long j2) {
        this.data = j2;
    }

    @InlineOnly
    /* renamed from: and-VKZWuLQ  reason: not valid java name */
    private static final long m352andVKZWuLQ(long j2, long j3) {
        return m359constructorimpl(j2 & j3);
    }

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ULong m353boximpl(long j2) {
        return new ULong(j2);
    }

    @InlineOnly
    /* renamed from: compareTo-7apg3OU  reason: not valid java name */
    private static final int m354compareTo7apg3OU(long j2, byte b2) {
        return UnsignedKt.ulongCompare(j2, m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private int m355compareToVKZWuLQ(long j2) {
        return UnsignedKt.ulongCompare(m410unboximpl(), j2);
    }

    @InlineOnly
    /* renamed from: compareTo-VKZWuLQ  reason: not valid java name */
    private static int m356compareToVKZWuLQ(long j2, long j3) {
        return UnsignedKt.ulongCompare(j2, j3);
    }

    @InlineOnly
    /* renamed from: compareTo-WZ4Q5Ns  reason: not valid java name */
    private static final int m357compareToWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.ulongCompare(j2, m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: compareTo-xj2QHRw  reason: not valid java name */
    private static final int m358compareToxj2QHRw(long j2, short s2) {
        return UnsignedKt.ulongCompare(j2, m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @PublishedApi
    /* renamed from: constructor-impl  reason: not valid java name */
    public static long m359constructorimpl(long j2) {
        return j2;
    }

    @InlineOnly
    /* renamed from: dec-s-VKNKU  reason: not valid java name */
    private static final long m360decsVKNKU(long j2) {
        return m359constructorimpl(j2 - 1);
    }

    @InlineOnly
    /* renamed from: div-7apg3OU  reason: not valid java name */
    private static final long m361div7apg3OU(long j2, byte b2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: div-VKZWuLQ  reason: not valid java name */
    private static final long m362divVKZWuLQ(long j2, long j3) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, j3);
    }

    @InlineOnly
    /* renamed from: div-WZ4Q5Ns  reason: not valid java name */
    private static final long m363divWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: div-xj2QHRw  reason: not valid java name */
    private static final long m364divxj2QHRw(long j2, short s2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m365equalsimpl(long j2, Object obj) {
        return (obj instanceof ULong) && j2 == ((ULong) obj).m410unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m366equalsimpl0(long j2, long j3) {
        return j2 == j3;
    }

    @InlineOnly
    /* renamed from: floorDiv-7apg3OU  reason: not valid java name */
    private static final long m367floorDiv7apg3OU(long j2, byte b2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: floorDiv-VKZWuLQ  reason: not valid java name */
    private static final long m368floorDivVKZWuLQ(long j2, long j3) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, j3);
    }

    @InlineOnly
    /* renamed from: floorDiv-WZ4Q5Ns  reason: not valid java name */
    private static final long m369floorDivWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: floorDiv-xj2QHRw  reason: not valid java name */
    private static final long m370floorDivxj2QHRw(long j2, short s2) {
        return UnsignedKt.m536ulongDivideeb3DHEI(j2, m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @PublishedApi
    public static /* synthetic */ void getData$annotations() {
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m371hashCodeimpl(long j2) {
        return (int) (j2 ^ (j2 >>> 32));
    }

    @InlineOnly
    /* renamed from: inc-s-VKNKU  reason: not valid java name */
    private static final long m372incsVKNKU(long j2) {
        return m359constructorimpl(j2 + 1);
    }

    @InlineOnly
    /* renamed from: inv-s-VKNKU  reason: not valid java name */
    private static final long m373invsVKNKU(long j2) {
        return m359constructorimpl(~j2);
    }

    @InlineOnly
    /* renamed from: minus-7apg3OU  reason: not valid java name */
    private static final long m374minus7apg3OU(long j2, byte b2) {
        return m359constructorimpl(j2 - m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: minus-VKZWuLQ  reason: not valid java name */
    private static final long m375minusVKZWuLQ(long j2, long j3) {
        return m359constructorimpl(j2 - j3);
    }

    @InlineOnly
    /* renamed from: minus-WZ4Q5Ns  reason: not valid java name */
    private static final long m376minusWZ4Q5Ns(long j2, int i2) {
        return m359constructorimpl(j2 - m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: minus-xj2QHRw  reason: not valid java name */
    private static final long m377minusxj2QHRw(long j2, short s2) {
        return m359constructorimpl(j2 - m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: mod-7apg3OU  reason: not valid java name */
    private static final byte m378mod7apg3OU(long j2, byte b2) {
        return UByte.m205constructorimpl((byte) UnsignedKt.m537ulongRemaindereb3DHEI(j2, m359constructorimpl(b2 & 255)));
    }

    @InlineOnly
    /* renamed from: mod-VKZWuLQ  reason: not valid java name */
    private static final long m379modVKZWuLQ(long j2, long j3) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(j2, j3);
    }

    @InlineOnly
    /* renamed from: mod-WZ4Q5Ns  reason: not valid java name */
    private static final int m380modWZ4Q5Ns(long j2, int i2) {
        return UInt.m281constructorimpl((int) UnsignedKt.m537ulongRemaindereb3DHEI(j2, m359constructorimpl(i2 & BodyPartID.bodyIdMax)));
    }

    @InlineOnly
    /* renamed from: mod-xj2QHRw  reason: not valid java name */
    private static final short m381modxj2QHRw(long j2, short s2) {
        return UShort.m465constructorimpl((short) UnsignedKt.m537ulongRemaindereb3DHEI(j2, m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX)));
    }

    @InlineOnly
    /* renamed from: or-VKZWuLQ  reason: not valid java name */
    private static final long m382orVKZWuLQ(long j2, long j3) {
        return m359constructorimpl(j2 | j3);
    }

    @InlineOnly
    /* renamed from: plus-7apg3OU  reason: not valid java name */
    private static final long m383plus7apg3OU(long j2, byte b2) {
        return m359constructorimpl(j2 + m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: plus-VKZWuLQ  reason: not valid java name */
    private static final long m384plusVKZWuLQ(long j2, long j3) {
        return m359constructorimpl(j2 + j3);
    }

    @InlineOnly
    /* renamed from: plus-WZ4Q5Ns  reason: not valid java name */
    private static final long m385plusWZ4Q5Ns(long j2, int i2) {
        return m359constructorimpl(j2 + m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: plus-xj2QHRw  reason: not valid java name */
    private static final long m386plusxj2QHRw(long j2, short s2) {
        return m359constructorimpl(j2 + m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: rangeTo-VKZWuLQ  reason: not valid java name */
    private static final ULongRange m387rangeToVKZWuLQ(long j2, long j3) {
        return new ULongRange(j2, j3, null);
    }

    @InlineOnly
    /* renamed from: rem-7apg3OU  reason: not valid java name */
    private static final long m388rem7apg3OU(long j2, byte b2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(j2, m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: rem-VKZWuLQ  reason: not valid java name */
    private static final long m389remVKZWuLQ(long j2, long j3) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(j2, j3);
    }

    @InlineOnly
    /* renamed from: rem-WZ4Q5Ns  reason: not valid java name */
    private static final long m390remWZ4Q5Ns(long j2, int i2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(j2, m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: rem-xj2QHRw  reason: not valid java name */
    private static final long m391remxj2QHRw(long j2, short s2) {
        return UnsignedKt.m537ulongRemaindereb3DHEI(j2, m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: shl-s-VKNKU  reason: not valid java name */
    private static final long m392shlsVKNKU(long j2, int i2) {
        return m359constructorimpl(j2 << i2);
    }

    @InlineOnly
    /* renamed from: shr-s-VKNKU  reason: not valid java name */
    private static final long m393shrsVKNKU(long j2, int i2) {
        return m359constructorimpl(j2 >>> i2);
    }

    @InlineOnly
    /* renamed from: times-7apg3OU  reason: not valid java name */
    private static final long m394times7apg3OU(long j2, byte b2) {
        return m359constructorimpl(j2 * m359constructorimpl(b2 & 255));
    }

    @InlineOnly
    /* renamed from: times-VKZWuLQ  reason: not valid java name */
    private static final long m395timesVKZWuLQ(long j2, long j3) {
        return m359constructorimpl(j2 * j3);
    }

    @InlineOnly
    /* renamed from: times-WZ4Q5Ns  reason: not valid java name */
    private static final long m396timesWZ4Q5Ns(long j2, int i2) {
        return m359constructorimpl(j2 * m359constructorimpl(i2 & BodyPartID.bodyIdMax));
    }

    @InlineOnly
    /* renamed from: times-xj2QHRw  reason: not valid java name */
    private static final long m397timesxj2QHRw(long j2, short s2) {
        return m359constructorimpl(j2 * m359constructorimpl(s2 & WebSocketProtocol.PAYLOAD_SHORT_MAX));
    }

    @InlineOnly
    /* renamed from: toByte-impl  reason: not valid java name */
    private static final byte m398toByteimpl(long j2) {
        return (byte) j2;
    }

    @InlineOnly
    /* renamed from: toDouble-impl  reason: not valid java name */
    private static final double m399toDoubleimpl(long j2) {
        return UnsignedKt.ulongToDouble(j2);
    }

    @InlineOnly
    /* renamed from: toFloat-impl  reason: not valid java name */
    private static final float m400toFloatimpl(long j2) {
        return (float) UnsignedKt.ulongToDouble(j2);
    }

    @InlineOnly
    /* renamed from: toInt-impl  reason: not valid java name */
    private static final int m401toIntimpl(long j2) {
        return (int) j2;
    }

    @InlineOnly
    /* renamed from: toLong-impl  reason: not valid java name */
    private static final long m402toLongimpl(long j2) {
        return j2;
    }

    @InlineOnly
    /* renamed from: toShort-impl  reason: not valid java name */
    private static final short m403toShortimpl(long j2) {
        return (short) j2;
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m404toStringimpl(long j2) {
        return UnsignedKt.ulongToString(j2);
    }

    @InlineOnly
    /* renamed from: toUByte-w2LRezQ  reason: not valid java name */
    private static final byte m405toUBytew2LRezQ(long j2) {
        return UByte.m205constructorimpl((byte) j2);
    }

    @InlineOnly
    /* renamed from: toUInt-pVg5ArA  reason: not valid java name */
    private static final int m406toUIntpVg5ArA(long j2) {
        return UInt.m281constructorimpl((int) j2);
    }

    @InlineOnly
    /* renamed from: toULong-s-VKNKU  reason: not valid java name */
    private static final long m407toULongsVKNKU(long j2) {
        return j2;
    }

    @InlineOnly
    /* renamed from: toUShort-Mh2AYeg  reason: not valid java name */
    private static final short m408toUShortMh2AYeg(long j2) {
        return UShort.m465constructorimpl((short) j2);
    }

    @InlineOnly
    /* renamed from: xor-VKZWuLQ  reason: not valid java name */
    private static final long m409xorVKZWuLQ(long j2, long j3) {
        return m359constructorimpl(j2 ^ j3);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ULong uLong) {
        return UnsignedKt.ulongCompare(m410unboximpl(), uLong.m410unboximpl());
    }

    public boolean equals(Object obj) {
        return m365equalsimpl(this.data, obj);
    }

    public int hashCode() {
        return m371hashCodeimpl(this.data);
    }

    @NotNull
    public String toString() {
        return m404toStringimpl(this.data);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long m410unboximpl() {
        return this.data;
    }
}
