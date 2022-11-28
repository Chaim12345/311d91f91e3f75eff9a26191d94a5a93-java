package kotlinx.serialization.json.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@DebugMetadata(c = "kotlinx.serialization.json.internal.JsonTreeReader", f = "JsonTreeReader.kt", i = {0, 0, 0, 0}, l = {23}, m = "readObject", n = {"$this$readObject", "this_$iv", "result$iv", "key$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes3.dex */
public final class JsonTreeReader$readObject$2 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12460a;

    /* renamed from: b  reason: collision with root package name */
    Object f12461b;

    /* renamed from: c  reason: collision with root package name */
    Object f12462c;

    /* renamed from: d  reason: collision with root package name */
    Object f12463d;

    /* renamed from: e  reason: collision with root package name */
    /* synthetic */ Object f12464e;

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ JsonTreeReader f12465f;

    /* renamed from: g  reason: collision with root package name */
    int f12466g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeReader$readObject$2(JsonTreeReader jsonTreeReader, Continuation continuation) {
        super(continuation);
        this.f12465f = jsonTreeReader;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object readObject;
        this.f12464e = obj;
        this.f12466g |= Integer.MIN_VALUE;
        readObject = this.f12465f.readObject(null, this);
        return readObject;
    }
}
