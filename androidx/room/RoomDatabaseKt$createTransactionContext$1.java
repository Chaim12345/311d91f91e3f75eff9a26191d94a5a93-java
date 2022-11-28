package androidx.room;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0005\u001a\u0004\u0018\u00010\u0004*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0082@"}, d2 = {"Landroidx/room/RoomDatabase;", "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/CoroutineContext;", "continuation", "", "createTransactionContext"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "androidx.room.RoomDatabaseKt", f = "RoomDatabase.kt", i = {0, 0}, l = {99}, m = "createTransactionContext", n = {"$this$createTransactionContext", "controlJob"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class RoomDatabaseKt$createTransactionContext$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    /* synthetic */ Object f3920a;

    /* renamed from: b  reason: collision with root package name */
    int f3921b;

    /* renamed from: c  reason: collision with root package name */
    Object f3922c;

    /* renamed from: d  reason: collision with root package name */
    Object f3923d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RoomDatabaseKt$createTransactionContext$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f3920a = obj;
        this.f3921b |= Integer.MIN_VALUE;
        return RoomDatabaseKt.b(null, this);
    }
}
