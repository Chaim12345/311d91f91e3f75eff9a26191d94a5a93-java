package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.sync.MutexImpl;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class MutexImpl$LockSelect$completeResumeLockWaiter$1 extends Lambda implements Function1<Throwable, Unit> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ MutexImpl f12392a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ MutexImpl.LockSelect f12393b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutexImpl$LockSelect$completeResumeLockWaiter$1(MutexImpl mutexImpl, MutexImpl.LockSelect lockSelect) {
        super(1);
        this.f12392a = mutexImpl;
        this.f12393b = lockSelect;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Throwable th) {
        this.f12392a.unlock(this.f12393b.owner);
    }
}
