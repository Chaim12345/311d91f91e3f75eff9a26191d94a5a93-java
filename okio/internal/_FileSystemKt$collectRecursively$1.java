package okio.internal;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.apache.http.cookie.ClientCookie;
import org.bouncycastle.tls.CipherSuite;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 5, 1})
@DebugMetadata(c = "okio.internal._FileSystemKt", f = "-FileSystem.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {113, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, CipherSuite.TLS_DHE_PSK_WITH_RC4_128_SHA}, m = "collectRecursively", n = {"$this$collectRecursively", "fileSystem", "stack", ClientCookie.PATH_ATTR, "followSymlinks", "postorder", "$this$collectRecursively", "fileSystem", "stack", ClientCookie.PATH_ATTR, "followSymlinks", "postorder"}, s = {"L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "Z$1"})
/* loaded from: classes3.dex */
public final class _FileSystemKt$collectRecursively$1 extends ContinuationImpl {

    /* renamed from: a  reason: collision with root package name */
    Object f12653a;

    /* renamed from: b  reason: collision with root package name */
    Object f12654b;

    /* renamed from: c  reason: collision with root package name */
    Object f12655c;

    /* renamed from: d  reason: collision with root package name */
    Object f12656d;

    /* renamed from: e  reason: collision with root package name */
    Object f12657e;

    /* renamed from: f  reason: collision with root package name */
    boolean f12658f;

    /* renamed from: g  reason: collision with root package name */
    boolean f12659g;

    /* renamed from: h  reason: collision with root package name */
    /* synthetic */ Object f12660h;

    /* renamed from: i  reason: collision with root package name */
    int f12661i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public _FileSystemKt$collectRecursively$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.f12660h = obj;
        this.f12661i |= Integer.MIN_VALUE;
        return _FileSystemKt.collectRecursively(null, null, null, null, false, false, this);
    }
}
