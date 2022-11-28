package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7 implements Flow<Integer> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int[] f11626a;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7", f = "Builders.kt", i = {0, 0}, l = {115}, m = "collect", n = {"$this$asFlow_u24lambda_u2d13", "$this$forEach$iv"}, s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f11627a;

        /* renamed from: b  reason: collision with root package name */
        int f11628b;

        /* renamed from: d  reason: collision with root package name */
        Object f11630d;

        /* renamed from: e  reason: collision with root package name */
        Object f11631e;

        /* renamed from: f  reason: collision with root package name */
        int f11632f;

        /* renamed from: g  reason: collision with root package name */
        int f11633g;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f11627a = obj;
            this.f11628b |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$7(int[] iArr) {
        this.f11626a = iArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0064  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x005f -> B:19:0x0062). Please submit an issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super Integer> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        int i3;
        FlowCollector<? super Integer> flowCollector2;
        int i4;
        int[] iArr;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i5 = anonymousClass1.f11628b;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.f11628b = i5 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.f11627a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.f11628b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    int[] iArr2 = this.f11626a;
                    i3 = 0;
                    int length = iArr2.length;
                    flowCollector2 = flowCollector;
                    i4 = length;
                    iArr = iArr2;
                    if (i3 < i4) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    i4 = anonymousClass1.f11633g;
                    i3 = anonymousClass1.f11632f;
                    iArr = (int[]) anonymousClass1.f11631e;
                    ResultKt.throwOnFailure(obj);
                    FlowCollector<? super Integer> flowCollector3 = (FlowCollector) anonymousClass1.f11630d;
                    i3++;
                    flowCollector2 = flowCollector3;
                    if (i3 < i4) {
                        Integer boxInt = Boxing.boxInt(iArr[i3]);
                        anonymousClass1.f11630d = flowCollector2;
                        anonymousClass1.f11631e = iArr;
                        anonymousClass1.f11632f = i3;
                        anonymousClass1.f11633g = i4;
                        anonymousClass1.f11628b = 1;
                        Object emit = flowCollector2.emit(boxInt, anonymousClass1);
                        flowCollector3 = flowCollector2;
                        if (emit == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i3++;
                        flowCollector2 = flowCollector3;
                        if (i3 < i4) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj2 = anonymousClass1.f11627a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f11628b;
        if (i2 != 0) {
        }
    }
}
