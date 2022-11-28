package kotlin.time;

import kotlin.SinceKotlin;
import kotlin.jvm.internal.LongCompanionObject;
@SinceKotlin(version = "1.3")
@ExperimentalTime
/* loaded from: classes3.dex */
public final class TestTimeSource extends AbstractLongTimeSource {
    private long reading;

    public TestTimeSource() {
        super(DurationUnit.NANOSECONDS);
    }

    /* renamed from: overflow-LRDsOJo  reason: not valid java name */
    private final void m1605overflowLRDsOJo(long j2) {
        throw new IllegalStateException("TestTimeSource will overflow if its reading " + this.reading + "ns is advanced by " + ((Object) Duration.m1527toStringimpl(j2)) + '.');
    }

    @Override // kotlin.time.AbstractLongTimeSource
    protected long b() {
        return this.reading;
    }

    /* renamed from: plusAssign-LRDsOJo  reason: not valid java name */
    public final void m1606plusAssignLRDsOJo(long j2) {
        long j3;
        long m1524toLongimpl = Duration.m1524toLongimpl(j2, a());
        if (m1524toLongimpl == Long.MIN_VALUE || m1524toLongimpl == LongCompanionObject.MAX_VALUE) {
            double m1521toDoubleimpl = this.reading + Duration.m1521toDoubleimpl(j2, a());
            if (m1521toDoubleimpl > 9.223372036854776E18d || m1521toDoubleimpl < -9.223372036854776E18d) {
                m1605overflowLRDsOJo(j2);
            }
            j3 = (long) m1521toDoubleimpl;
        } else {
            long j4 = this.reading;
            j3 = j4 + m1524toLongimpl;
            if ((m1524toLongimpl ^ j4) >= 0 && (j4 ^ j3) < 0) {
                m1605overflowLRDsOJo(j2);
            }
        }
        this.reading = j3;
    }
}
