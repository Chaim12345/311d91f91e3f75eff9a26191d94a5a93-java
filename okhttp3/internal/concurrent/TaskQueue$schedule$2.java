package okhttp3.internal.concurrent;

import kotlin.jvm.functions.Function0;
/* loaded from: classes3.dex */
public final class TaskQueue$schedule$2 extends Task {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function0 f12537a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TaskQueue$schedule$2(String str, Function0<Long> function0) {
        super(str, false, 2, null);
        this.f12537a = function0;
    }

    @Override // okhttp3.internal.concurrent.Task
    public long runOnce() {
        return ((Number) this.f12537a.invoke()).longValue();
    }
}
