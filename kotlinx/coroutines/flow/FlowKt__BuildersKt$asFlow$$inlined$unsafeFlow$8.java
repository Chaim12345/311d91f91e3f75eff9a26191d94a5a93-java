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
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 implements Flow<Long> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ long[] f11634a;

    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8", f = "Builders.kt", i = {0, 0}, l = {115}, m = "collect", n = {"$this$asFlow_u24lambda_u2d15", "$this$forEach$iv"}, s = {"L$0", "L$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {

        /* renamed from: a  reason: collision with root package name */
        /* synthetic */ Object f11635a;

        /* renamed from: b  reason: collision with root package name */
        int f11636b;

        /* renamed from: d  reason: collision with root package name */
        Object f11638d;

        /* renamed from: e  reason: collision with root package name */
        Object f11639e;

        /* renamed from: f  reason: collision with root package name */
        int f11640f;

        /* renamed from: g  reason: collision with root package name */
        int f11641g;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.f11635a = obj;
            this.f11636b |= Integer.MIN_VALUE;
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(long[] jArr) {
        this.f11634a = jArr;
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
    public Object collect(@NotNull FlowCollector<? super Long> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        int i3;
        FlowCollector<? super Long> flowCollector2;
        int i4;
        long[] jArr;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i5 = anonymousClass1.f11636b;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.f11636b = i5 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.f11635a;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.f11636b;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    long[] jArr2 = this.f11634a;
                    i3 = 0;
                    int length = jArr2.length;
                    flowCollector2 = flowCollector;
                    i4 = length;
                    jArr = jArr2;
                    if (i3 < i4) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    i4 = anonymousClass1.f11641g;
                    i3 = anonymousClass1.f11640f;
                    jArr = (long[]) anonymousClass1.f11639e;
                    ResultKt.throwOnFailure(obj);
                    FlowCollector<? super Long> flowCollector3 = (FlowCollector) anonymousClass1.f11638d;
                    i3++;
                    flowCollector2 = flowCollector3;
                    if (i3 < i4) {
                        Long boxLong = Boxing.boxLong(jArr[i3]);
                        anonymousClass1.f11638d = flowCollector2;
                        anonymousClass1.f11639e = jArr;
                        anonymousClass1.f11640f = i3;
                        anonymousClass1.f11641g = i4;
                        anonymousClass1.f11636b = 1;
                        Object emit = flowCollector2.emit(boxLong, anonymousClass1);
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
        Object obj2 = anonymousClass1.f11635a;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.f11636b;
        if (i2 != 0) {
        }
    }
}
