package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6 implements Flow<T> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Object[] f11618a;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6", f = "Builders.kt", i = {0, 0}, l = {115}, m = "collect", n = {"$this$asFlow_u24lambda_u2d11", "$this$forEach$iv"}, s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f11619a;

        /* renamed from: b  reason: collision with root package name */
        int f11620b;

        /* renamed from: d  reason: collision with root package name */
        Object f11622d;

        /* renamed from: e  reason: collision with root package name */
        Object f11623e;

        /* renamed from: f  reason: collision with root package name */
        int f11624f;

        /* renamed from: g  reason: collision with root package name */
        int f11625g;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f11619a = obj;
            this.f11620b |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$6(Object[] objArr) {
        this.f11618a = objArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0060  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005b -> B:19:0x005e). Please submit an issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        int i3;
        FlowCollector flowCollector2;
        int i4;
        Object[] objArr;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i5 = anonymousClass1.f11620b;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.f11620b = i5 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.f11619a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.f11620b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    Object[] objArr2 = this.f11618a;
                    i3 = 0;
                    int length = objArr2.length;
                    flowCollector2 = flowCollector;
                    i4 = length;
                    objArr = objArr2;
                    if (i3 < i4) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    i4 = anonymousClass1.f11625g;
                    i3 = anonymousClass1.f11624f;
                    objArr = (Object[]) anonymousClass1.f11623e;
                    ResultKt.throwOnFailure(obj);
                    flowCollector2 = (FlowCollector) anonymousClass1.f11622d;
                    i3++;
                    if (i3 < i4) {
                        Object obj2 = objArr[i3];
                        anonymousClass1.f11622d = flowCollector2;
                        anonymousClass1.f11623e = objArr;
                        anonymousClass1.f11624f = i3;
                        anonymousClass1.f11625g = i4;
                        anonymousClass1.f11620b = 1;
                        if (flowCollector2.emit(obj2, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i3++;
                        if (i3 < i4) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj3 = anonymousClass1.f11619a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f11620b;
        if (i2 != 0) {
        }
    }
}
