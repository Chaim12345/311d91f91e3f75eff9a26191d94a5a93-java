package okhttp3.tls.internal.der;

import java.net.ProtocolException;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* loaded from: classes3.dex */
final class Adapters$sequence$codec$1$decode$1 extends Lambda implements Function0<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DerAdapter[] f12614a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ DerReader f12615b;

    /* renamed from: c  reason: collision with root package name */
    final /* synthetic */ Function1 f12616c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Adapters$sequence$codec$1$decode$1(DerAdapter[] derAdapterArr, DerReader derReader, Function1 function1) {
        super(0);
        this.f12614a = derAdapterArr;
        this.f12615b = derReader;
        this.f12616c = function1;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    public final T invoke() {
        ArrayList arrayList = new ArrayList();
        while (true) {
            int size = arrayList.size();
            DerAdapter[] derAdapterArr = this.f12614a;
            if (size >= derAdapterArr.length) {
                break;
            }
            arrayList.add(derAdapterArr[arrayList.size()].fromDer(this.f12615b));
        }
        if (this.f12615b.hasNext()) {
            throw new ProtocolException("unexpected " + this.f12615b.peekHeader() + " at " + this.f12615b);
        }
        return this.f12616c.invoke(arrayList);
    }
}
