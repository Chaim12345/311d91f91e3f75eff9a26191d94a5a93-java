package kotlinx.coroutines.flow;

import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3 implements Flow<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Iterable f11600a;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3", f = "Builders.kt", i = {0}, l = {115}, m = "collect", n = {"$this$asFlow_u24lambda_u2d3"}, s = {"L$0"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f11601a;

        /* renamed from: b  reason: collision with root package name */
        int f11602b;

        /* renamed from: d  reason: collision with root package name */
        Object f11604d;

        /* renamed from: e  reason: collision with root package name */
        Object f11605e;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f11601a = obj;
            this.f11602b |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$3(Iterable iterable) {
        this.f11600a = iterable;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004c  */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        FlowCollector flowCollector2;
        Iterator it;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i3 = anonymousClass1.f11602b;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.f11602b = i3 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.f11601a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.f11602b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    flowCollector2 = flowCollector;
                    it = this.f11600a.iterator();
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    it = (Iterator) anonymousClass1.f11605e;
                    ResultKt.throwOnFailure(obj);
                    flowCollector2 = (FlowCollector) anonymousClass1.f11604d;
                }
                while (it.hasNext()) {
                    Object next = it.next();
                    anonymousClass1.f11604d = flowCollector2;
                    anonymousClass1.f11605e = it;
                    anonymousClass1.f11602b = 1;
                    if (flowCollector2.emit(next, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj2 = anonymousClass1.f11601a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f11602b;
        if (i2 != 0) {
        }
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }
}
