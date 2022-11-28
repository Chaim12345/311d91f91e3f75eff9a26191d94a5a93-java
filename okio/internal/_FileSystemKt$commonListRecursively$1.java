package okio.internal;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;
import okio.FileSystem;
import okio.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H\u008a@"}, d2 = {"Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "", "<anonymous>"}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "okio.internal._FileSystemKt$commonListRecursively$1", f = "-FileSystem.kt", i = {0, 0}, l = {93}, m = "invokeSuspend", n = {"$this$sequence", "stack"}, s = {"L$0", "L$1"})
/* loaded from: classes3.dex */
public final class _FileSystemKt$commonListRecursively$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;

    /* renamed from: a  reason: collision with root package name */
    Object f12665a;

    /* renamed from: b  reason: collision with root package name */
    Object f12666b;

    /* renamed from: c  reason: collision with root package name */
    int f12667c;

    /* renamed from: d  reason: collision with root package name */
    final /* synthetic */ Path f12668d;

    /* renamed from: e  reason: collision with root package name */
    final /* synthetic */ FileSystem f12669e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ boolean f12670f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public _FileSystemKt$commonListRecursively$1(Path path, FileSystem fileSystem, boolean z, Continuation continuation) {
        super(2, continuation);
        this.f12668d = path;
        this.f12669e = fileSystem;
        this.f12670f = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        _FileSystemKt$commonListRecursively$1 _filesystemkt_commonlistrecursively_1 = new _FileSystemKt$commonListRecursively$1(this.f12668d, this.f12669e, this.f12670f, continuation);
        _filesystemkt_commonlistrecursively_1.L$0 = obj;
        return _filesystemkt_commonlistrecursively_1;
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull SequenceScope<? super Path> sequenceScope, @Nullable Continuation<? super Unit> continuation) {
        return ((_FileSystemKt$commonListRecursively$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        _FileSystemKt$commonListRecursively$1 _filesystemkt_commonlistrecursively_1;
        SequenceScope sequenceScope;
        ArrayDeque arrayDeque;
        Iterator<Path> it;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.f12667c;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayDeque arrayDeque2 = new ArrayDeque();
            arrayDeque2.addLast(this.f12668d);
            _filesystemkt_commonlistrecursively_1 = this;
            sequenceScope = (SequenceScope) this.L$0;
            arrayDeque = arrayDeque2;
            it = this.f12669e.list(this.f12668d).iterator();
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            it = (Iterator) this.f12666b;
            ResultKt.throwOnFailure(obj);
            _filesystemkt_commonlistrecursively_1 = this;
            arrayDeque = (ArrayDeque) this.f12665a;
            sequenceScope = (SequenceScope) this.L$0;
        }
        while (it.hasNext()) {
            Path next = it.next();
            FileSystem fileSystem = _filesystemkt_commonlistrecursively_1.f12669e;
            boolean z = _filesystemkt_commonlistrecursively_1.f12670f;
            _filesystemkt_commonlistrecursively_1.L$0 = sequenceScope;
            _filesystemkt_commonlistrecursively_1.f12665a = arrayDeque;
            _filesystemkt_commonlistrecursively_1.f12666b = it;
            _filesystemkt_commonlistrecursively_1.f12667c = 1;
            if (_FileSystemKt.collectRecursively(sequenceScope, fileSystem, arrayDeque, next, z, false, _filesystemkt_commonlistrecursively_1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
