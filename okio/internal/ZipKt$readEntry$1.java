package okio.internal;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import okio.BufferedSource;
import org.bouncycastle.asn1.cmc.BodyPartID;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\n"}, d2 = {"", "headerId", "", "dataSize", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
/* loaded from: classes3.dex */
final class ZipKt$readEntry$1 extends Lambda implements Function2<Integer, Long, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.BooleanRef f12643a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ long f12644b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Ref.LongRef f12645c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ BufferedSource f12646d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Ref.LongRef f12647e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Ref.LongRef f12648f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZipKt$readEntry$1(Ref.BooleanRef booleanRef, long j2, Ref.LongRef longRef, BufferedSource bufferedSource, Ref.LongRef longRef2, Ref.LongRef longRef3) {
        super(2);
        this.f12643a = booleanRef;
        this.f12644b = j2;
        this.f12645c = longRef;
        this.f12646d = bufferedSource;
        this.f12647e = longRef2;
        this.f12648f = longRef3;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Integer num, Long l2) {
        invoke(num.intValue(), l2.longValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i2, long j2) {
        if (i2 == 1) {
            Ref.BooleanRef booleanRef = this.f12643a;
            if (booleanRef.element) {
                throw new IOException("bad zip: zip64 extra repeated");
            }
            booleanRef.element = true;
            if (j2 < this.f12644b) {
                throw new IOException("bad zip: zip64 extra too short");
            }
            Ref.LongRef longRef = this.f12645c;
            long j3 = longRef.element;
            if (j3 == BodyPartID.bodyIdMax) {
                j3 = this.f12646d.readLongLe();
            }
            longRef.element = j3;
            Ref.LongRef longRef2 = this.f12647e;
            longRef2.element = longRef2.element == BodyPartID.bodyIdMax ? this.f12646d.readLongLe() : 0L;
            Ref.LongRef longRef3 = this.f12648f;
            longRef3.element = longRef3.element == BodyPartID.bodyIdMax ? this.f12646d.readLongLe() : 0L;
        }
    }
}
