package kotlinx.coroutines.internal;

import java.util.Objects;
import kotlin.jvm.functions.Function1;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@IgnoreJRERequirement
/* loaded from: classes3.dex */
final class ClassValueCtorCache extends CtorCache {
    @NotNull
    public static final ClassValueCtorCache INSTANCE = new ClassValueCtorCache();
    @NotNull
    private static final ClassValueCtorCache$cache$1 cache = new ClassValue<Function1<? super Throwable, ? extends Throwable>>() { // from class: kotlinx.coroutines.internal.ClassValueCtorCache$cache$1
        /* JADX INFO: Access modifiers changed from: protected */
        @NotNull
        /* renamed from: a */
        public Function1<Throwable, Throwable> computeValue(@Nullable Class<?> cls) {
            Objects.requireNonNull(cls, "null cannot be cast to non-null type java.lang.Class<out kotlin.Throwable>");
            return ExceptionsConstructorKt.access$createConstructor(cls);
        }
    };

    private ClassValueCtorCache() {
    }

    @Override // kotlinx.coroutines.internal.CtorCache
    @NotNull
    public Function1<Throwable, Throwable> get(@NotNull Class<? extends Throwable> cls) {
        return (Function1) cache.get(cls);
    }
}
