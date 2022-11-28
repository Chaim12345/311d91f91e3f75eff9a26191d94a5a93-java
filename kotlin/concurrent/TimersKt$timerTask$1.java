package kotlin.concurrent;

import java.util.TimerTask;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* loaded from: classes3.dex */
public final class TimersKt$timerTask$1 extends TimerTask {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Function1 f11122a;

    public TimersKt$timerTask$1(Function1<? super TimerTask, Unit> function1) {
        this.f11122a = function1;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        this.f11122a.invoke(this);
    }
}
