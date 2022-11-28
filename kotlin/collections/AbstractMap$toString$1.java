package kotlin.collections;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class AbstractMap$toString$1 extends Lambda implements Function1<Map.Entry<? extends K, ? extends V>, CharSequence> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ AbstractMap f11024a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractMap$toString$1(AbstractMap abstractMap) {
        super(1);
        this.f11024a = abstractMap;
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    public final CharSequence invoke(@NotNull Map.Entry<? extends K, ? extends V> it) {
        String abstractMap;
        Intrinsics.checkNotNullParameter(it, "it");
        abstractMap = this.f11024a.toString((Map.Entry) it);
        return abstractMap;
    }
}
