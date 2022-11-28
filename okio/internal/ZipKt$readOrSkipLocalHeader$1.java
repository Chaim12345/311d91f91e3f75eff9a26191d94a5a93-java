package okio.internal;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import okio.BufferedSource;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"", "headerId", "", "dataSize", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
/* loaded from: classes3.dex */
final class ZipKt$readOrSkipLocalHeader$1 extends Lambda implements Function2<Integer, Long, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BufferedSource f12649a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f12650b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f12651c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f12652d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipKt$readOrSkipLocalHeader$1(BufferedSource bufferedSource, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.ObjectRef objectRef3) {
        super(2);
        this.f12649a = bufferedSource;
        this.f12650b = objectRef;
        this.f12651c = objectRef2;
        this.f12652d = objectRef3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l2) {
        invoke(num.intValue(), l2.longValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [T, java.lang.Long] */
    /* JADX WARN: Type inference failed for: r10v12, types: [T, java.lang.Long] */
    /* JADX WARN: Type inference failed for: r11v3, types: [T, java.lang.Long] */
    public final void invoke(int i2, long j2) {
        if (i2 == 21589) {
            if (j2 < 1) {
                throw new IOException("bad zip: extended timestamp extra too short");
            }
            int readByte = this.f12649a.readByte() & 255;
            boolean z = (readByte & 1) == 1;
            boolean z2 = (readByte & 2) == 2;
            boolean z3 = (readByte & 4) == 4;
            BufferedSource bufferedSource = this.f12649a;
            long j3 = z ? 5L : 1L;
            if (z2) {
                j3 += 4;
            }
            if (z3) {
                j3 += 4;
            }
            if (j2 < j3) {
                throw new IOException("bad zip: extended timestamp extra too short");
            }
            if (z) {
                this.f12650b.element = Long.valueOf(bufferedSource.readIntLe() * 1000);
            }
            if (z2) {
                this.f12651c.element = Long.valueOf(this.f12649a.readIntLe() * 1000);
            }
            if (z3) {
                this.f12652d.element = Long.valueOf(this.f12649a.readIntLe() * 1000);
            }
        }
    }
}
