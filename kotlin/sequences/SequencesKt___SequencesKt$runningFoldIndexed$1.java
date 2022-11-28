package kotlin.sequences;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlin.sequences.SequencesKt___SequencesKt$runningFoldIndexed$1", f = "_Sequences.kt", i = {0, 1, 1, 1}, l = {2143, 2148}, m = "invokeSuspend", n = {"$this$sequence", "$this$sequence", "accumulator", FirebaseAnalytics.Param.INDEX}, s = {"L$0", "L$0", "L$1", "I$0"})
/* loaded from: classes3.dex */
public final class SequencesKt___SequencesKt$runningFoldIndexed$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11201a;

    /* renamed from: b  reason: collision with root package name */
    Object f11202b;

    /* renamed from: c  reason: collision with root package name */
    int f11203c;

    /* renamed from: d  reason: collision with root package name */
    int f11204d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ Object f11205e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ Sequence f11206f;

    /* renamed from: g  reason: collision with root package name */
    final /* synthetic */ Function3 f11207g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$runningFoldIndexed$1(Object obj, Sequence sequence, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.f11205e = obj;
        this.f11206f = sequence;
        this.f11207g = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        SequencesKt___SequencesKt$runningFoldIndexed$1 sequencesKt___SequencesKt$runningFoldIndexed$1 = new SequencesKt___SequencesKt$runningFoldIndexed$1(this.f11205e, this.f11206f, this.f11207g, continuation);
        sequencesKt___SequencesKt$runningFoldIndexed$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningFoldIndexed$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull SequenceScope<? super R> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningFoldIndexed$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0058  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        SequenceScope sequenceScope;
        int i2;
        SequenceScope sequenceScope2;
        Object obj2;
        Iterator it;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f11204d;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            sequenceScope = (SequenceScope) this.L$0;
            Object obj3 = this.f11205e;
            this.L$0 = sequenceScope;
            this.f11204d = 1;
            if (sequenceScope.yield(obj3, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 != 1) {
            if (i3 == 2) {
                int i4 = this.f11203c;
                it = (Iterator) this.f11202b;
                Object obj4 = this.f11201a;
                sequenceScope2 = (SequenceScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                i2 = i4;
                obj2 = obj4;
                while (it.hasNext()) {
                    Object next = it.next();
                    Function3 function3 = this.f11207g;
                    int i5 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt__CollectionsKt.throwIndexOverflow();
                    }
                    Object invoke = function3.invoke(Boxing.boxInt(i2), obj2, next);
                    this.L$0 = sequenceScope2;
                    this.f11201a = invoke;
                    this.f11202b = it;
                    this.f11203c = i5;
                    this.f11204d = 2;
                    if (sequenceScope2.yield(invoke, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    obj2 = invoke;
                    i2 = i5;
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        i2 = 0;
        sequenceScope2 = sequenceScope;
        obj2 = this.f11205e;
        it = this.f11206f.iterator();
        while (it.hasNext()) {
        }
        return Unit.INSTANCE;
    }
}
