package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Landroid/view/View;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 2})
@DebugMetadata(c = "androidx.core.view.ViewGroupKt$descendants$1", f = "ViewGroup.kt", i = {0, 0, 0, 0, 1, 1, 1}, l = {97, 99}, m = "invokeSuspend", n = {"$this$sequence", "$this$forEach$iv", "child", "index$iv", "$this$sequence", "$this$forEach$iv", "index$iv"}, s = {"L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
public final class ViewGroupKt$descendants$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super View>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f2662a;

    /* renamed from: b  reason: collision with root package name */
    Object f2663b;

    /* renamed from: c  reason: collision with root package name */
    int f2664c;

    /* renamed from: d  reason: collision with root package name */
    int f2665d;

    /* renamed from: e  reason: collision with root package name */
    int f2666e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ ViewGroup f2667f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewGroupKt$descendants$1(ViewGroup viewGroup, Continuation continuation) {
        super(2, continuation);
        this.f2667f = viewGroup;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1 = new ViewGroupKt$descendants$1(this.f2667f, completion);
        viewGroupKt$descendants$1.L$0 = obj;
        return viewGroupKt$descendants$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super View> sequenceScope, Continuation<? super Unit> continuation) {
        return ((ViewGroupKt$descendants$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x008e -> B:22:0x0090). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0097 -> B:24:0x009b). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        SequenceScope sequenceScope;
        ViewGroup viewGroup;
        int i2;
        int childCount;
        ViewGroupKt$descendants$1 viewGroupKt$descendants$1;
        ViewGroupKt$descendants$1 viewGroupKt$descendants$12;
        SequenceScope sequenceScope2;
        ViewGroup viewGroup2;
        View view;
        int i3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.f2666e;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceScope = (SequenceScope) this.L$0;
            viewGroup = this.f2667f;
            i2 = 0;
            childCount = viewGroup.getChildCount();
            viewGroupKt$descendants$1 = this;
            if (i2 >= childCount) {
            }
        } else if (i4 == 1) {
            i3 = this.f2665d;
            i2 = this.f2664c;
            view = (View) this.f2663b;
            viewGroup2 = (ViewGroup) this.f2662a;
            sequenceScope2 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            viewGroupKt$descendants$12 = this;
            if (view instanceof ViewGroup) {
            }
        } else if (i4 != 2) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            i3 = this.f2665d;
            i2 = this.f2664c;
            ViewGroup viewGroup3 = (ViewGroup) this.f2662a;
            SequenceScope sequenceScope3 = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            viewGroupKt$descendants$12 = this;
            SequenceScope sequenceScope4 = sequenceScope3;
            viewGroupKt$descendants$1 = viewGroupKt$descendants$12;
            sequenceScope = sequenceScope4;
            ViewGroup viewGroup4 = viewGroup3;
            childCount = i3;
            viewGroup = viewGroup4;
            i2++;
            if (i2 >= childCount) {
                View childAt = viewGroup.getChildAt(i2);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(index)");
                viewGroupKt$descendants$1.L$0 = sequenceScope;
                viewGroupKt$descendants$1.f2662a = viewGroup;
                viewGroupKt$descendants$1.f2663b = childAt;
                viewGroupKt$descendants$1.f2664c = i2;
                viewGroupKt$descendants$1.f2665d = childCount;
                viewGroupKt$descendants$1.f2666e = 1;
                if (sequenceScope.yield(childAt, viewGroupKt$descendants$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                sequenceScope2 = sequenceScope;
                viewGroupKt$descendants$12 = viewGroupKt$descendants$1;
                viewGroup2 = viewGroup;
                i3 = childCount;
                view = childAt;
                if (view instanceof ViewGroup) {
                    childCount = i3;
                    viewGroup = viewGroup2;
                    viewGroupKt$descendants$1 = viewGroupKt$descendants$12;
                    sequenceScope = sequenceScope2;
                    i2++;
                    if (i2 >= childCount) {
                    }
                } else {
                    Sequence<View> descendants = ViewGroupKt.getDescendants((ViewGroup) view);
                    viewGroupKt$descendants$12.L$0 = sequenceScope2;
                    viewGroupKt$descendants$12.f2662a = viewGroup2;
                    viewGroupKt$descendants$12.f2663b = null;
                    viewGroupKt$descendants$12.f2664c = i2;
                    viewGroupKt$descendants$12.f2665d = i3;
                    viewGroupKt$descendants$12.f2666e = 2;
                    if (sequenceScope2.yieldAll(descendants, viewGroupKt$descendants$12) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    viewGroup3 = viewGroup2;
                    sequenceScope3 = sequenceScope2;
                    SequenceScope sequenceScope42 = sequenceScope3;
                    viewGroupKt$descendants$1 = viewGroupKt$descendants$12;
                    sequenceScope = sequenceScope42;
                    ViewGroup viewGroup42 = viewGroup3;
                    childCount = i3;
                    viewGroup = viewGroup42;
                    i2++;
                    if (i2 >= childCount) {
                        return Unit.INSTANCE;
                    }
                }
            }
        }
    }
}
