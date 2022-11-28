package kotlinx.coroutines.internal;

import java.lang.reflect.Constructor;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$1 extends Lambda implements Function1<Throwable, Throwable> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Constructor f12351a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExceptionsConstructorKt$createSafeConstructor$$inlined$safeCtor$1(Constructor constructor) {
        super(1);
        this.f12351a = constructor;
    }

    @Override // kotlin.jvm.functions.Function1
    @Nullable
    public final Throwable invoke(@NotNull Throwable th) {
        Object m187constructorimpl;
        Object newInstance;
        try {
            Result.Companion companion = Result.Companion;
            newInstance = this.f12351a.newInstance(th.getMessage(), th);
        } catch (Throwable th2) {
            Result.Companion companion2 = Result.Companion;
            m187constructorimpl = Result.m187constructorimpl(ResultKt.createFailure(th2));
        }
        if (newInstance != null) {
            m187constructorimpl = Result.m187constructorimpl((Throwable) newInstance);
            if (Result.m193isFailureimpl(m187constructorimpl)) {
                m187constructorimpl = null;
            }
            return (Throwable) m187constructorimpl;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
    }
}
