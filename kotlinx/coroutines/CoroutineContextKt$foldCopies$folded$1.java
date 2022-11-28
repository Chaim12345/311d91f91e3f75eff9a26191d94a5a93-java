package kotlinx.coroutines;

import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
final class CoroutineContextKt$foldCopies$folded$1 extends Lambda implements Function2<CoroutineContext, CoroutineContext.Element, CoroutineContext> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Ref.ObjectRef f11278a;

    /* renamed from: b  reason: collision with root package name */
    final /* synthetic */ boolean f11279b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CoroutineContextKt$foldCopies$folded$1(Ref.ObjectRef objectRef, boolean z) {
        super(2);
        this.f11278a = objectRef;
        this.f11279b = z;
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [T, kotlin.coroutines.CoroutineContext] */
    @Override // kotlin.jvm.functions.Function2
    @NotNull
    public final CoroutineContext invoke(@NotNull CoroutineContext coroutineContext, @NotNull CoroutineContext.Element element) {
        if (element instanceof CopyableThreadContextElement) {
            CoroutineContext.Element element2 = ((CoroutineContext) this.f11278a.element).get(element.getKey());
            if (element2 != null) {
                Ref.ObjectRef objectRef = this.f11278a;
                objectRef.element = ((CoroutineContext) objectRef.element).minusKey(element.getKey());
                return coroutineContext.plus(((CopyableThreadContextElement) element).mergeForChild(element2));
            }
            CopyableThreadContextElement copyableThreadContextElement = (CopyableThreadContextElement) element;
            if (this.f11279b) {
                copyableThreadContextElement = copyableThreadContextElement.copyForChild();
            }
            return coroutineContext.plus(copyableThreadContextElement);
        }
        return coroutineContext.plus(element);
    }
}
