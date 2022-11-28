package kotlin.text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlin.text.Regex$splitToSequence$1", f = "Regex.kt", i = {1, 1, 1}, l = {276, 284, 288}, m = "invokeSuspend", n = {"$this$sequence", "matcher", "splitCount"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes3.dex */
public final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f11243a;

    /* renamed from: b  reason: collision with root package name */
    int f11244b;

    /* renamed from: c  reason: collision with root package name */
    int f11245c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Regex f11246d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ CharSequence f11247e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ int f11248f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i2, Continuation continuation) {
        super(2, continuation);
        this.f11246d = regex;
        this.f11247e = charSequence;
        this.f11248f = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.f11246d, this.f11247e, this.f11248f, continuation);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull SequenceScope<? super String> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0072 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009e A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0070 -> B:21:0x0073). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Pattern pattern;
        Matcher matcher;
        Regex$splitToSequence$1 regex$splitToSequence$1;
        SequenceScope sequenceScope;
        int i2;
        String obj2;
        String obj3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = this.f11245c;
        int i4 = 0;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            SequenceScope sequenceScope2 = (SequenceScope) this.L$0;
            pattern = this.f11246d.nativePattern;
            matcher = pattern.matcher(this.f11247e);
            if (this.f11248f != 1 && matcher.find()) {
                regex$splitToSequence$1 = this;
                sequenceScope = sequenceScope2;
                i2 = 0;
                obj2 = regex$splitToSequence$1.f11247e.subSequence(i4, matcher.start()).toString();
                regex$splitToSequence$1.L$0 = sequenceScope;
                regex$splitToSequence$1.f11243a = matcher;
                regex$splitToSequence$1.f11244b = i2;
                regex$splitToSequence$1.f11245c = 2;
                if (sequenceScope.yield(obj2, regex$splitToSequence$1) == coroutine_suspended) {
                }
                i4 = matcher.end();
                i2++;
                if (i2 != regex$splitToSequence$1.f11248f - 1) {
                }
                CharSequence charSequence = regex$splitToSequence$1.f11247e;
                obj3 = charSequence.subSequence(i4, charSequence.length()).toString();
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.f11243a = null;
                regex$splitToSequence$1.f11245c = 3;
                if (sequenceScope.yield(obj3, regex$splitToSequence$1) == coroutine_suspended) {
                }
                return Unit.INSTANCE;
            }
            String obj4 = this.f11247e.toString();
            this.f11245c = 1;
            if (sequenceScope2.yield(obj4, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i3 != 1) {
            if (i3 != 2) {
                if (i3 == 3) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i5 = this.f11244b;
            sequenceScope = (SequenceScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            regex$splitToSequence$1 = this;
            i2 = i5;
            matcher = (Matcher) this.f11243a;
            i4 = matcher.end();
            i2++;
            if (i2 != regex$splitToSequence$1.f11248f - 1 || !matcher.find()) {
                CharSequence charSequence2 = regex$splitToSequence$1.f11247e;
                obj3 = charSequence2.subSequence(i4, charSequence2.length()).toString();
                regex$splitToSequence$1.L$0 = null;
                regex$splitToSequence$1.f11243a = null;
                regex$splitToSequence$1.f11245c = 3;
                if (sequenceScope.yield(obj3, regex$splitToSequence$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            obj2 = regex$splitToSequence$1.f11247e.subSequence(i4, matcher.start()).toString();
            regex$splitToSequence$1.L$0 = sequenceScope;
            regex$splitToSequence$1.f11243a = matcher;
            regex$splitToSequence$1.f11244b = i2;
            regex$splitToSequence$1.f11245c = 2;
            if (sequenceScope.yield(obj2, regex$splitToSequence$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i4 = matcher.end();
            i2++;
            if (i2 != regex$splitToSequence$1.f11248f - 1) {
            }
            CharSequence charSequence22 = regex$splitToSequence$1.f11247e;
            obj3 = charSequence22.subSequence(i4, charSequence22.length()).toString();
            regex$splitToSequence$1.L$0 = null;
            regex$splitToSequence$1.f11243a = null;
            regex$splitToSequence$1.f11245c = 3;
            if (sequenceScope.yield(obj3, regex$splitToSequence$1) == coroutine_suspended) {
            }
            return Unit.INSTANCE;
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
