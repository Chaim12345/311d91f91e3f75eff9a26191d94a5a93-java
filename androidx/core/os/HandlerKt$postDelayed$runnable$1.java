package androidx.core.os;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"", "run", "()V", "<anonymous>"}, k = 3, mv = {1, 4, 2})
/* loaded from: classes.dex */
public final class HandlerKt$postDelayed$runnable$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function0 f2562a;

    public HandlerKt$postDelayed$runnable$1(Function0 function0) {
        this.f2562a = function0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f2562a.invoke();
    }
}
