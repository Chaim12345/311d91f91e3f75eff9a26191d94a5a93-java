package okhttp3.internal.concurrent;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
/* loaded from: classes3.dex */
public final class TaskQueue$execute$1 extends Task {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function0 f12536a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskQueue$execute$1(String str, boolean z, Function0<Unit> function0) {
        super(str, z);
        this.f12536a = function0;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        this.f12536a.invoke();
        return -1L;
    }
}
