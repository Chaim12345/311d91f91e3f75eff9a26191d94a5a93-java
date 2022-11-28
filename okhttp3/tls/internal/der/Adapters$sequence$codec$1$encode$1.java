package okhttp3.tls.internal.der;

import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class Adapters$sequence$codec$1$encode$1 extends Lambda implements Function0<Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ List f12617a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DerAdapter[] f12618b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ DerWriter f12619c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Adapters$sequence$codec$1$encode$1(List list, DerAdapter[] derAdapterArr, DerWriter derWriter) {
        super(0);
        this.f12617a = list;
        this.f12618b = derAdapterArr;
        this.f12619c = derWriter;
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2() {
        int size = this.f12617a.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.f12618b[i2].toDer(this.f12619c, this.f12617a.get(i2));
        }
    }
}
