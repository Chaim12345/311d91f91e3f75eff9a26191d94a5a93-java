package okhttp3.internal.http2;

import java.util.List;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface PushObserver {
    @NotNull
    public static final Companion Companion = Companion.f12595a;
    @JvmField
    @NotNull
    public static final PushObserver CANCEL = new Companion.PushObserverCancel();

    /* loaded from: classes3.dex */
    public static final class Companion {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ Companion f12595a = new Companion();

        /* loaded from: classes3.dex */
        private static final class PushObserverCancel implements PushObserver {
            @Override // okhttp3.internal.http2.PushObserver
            public boolean onData(int i2, @NotNull BufferedSource source, int i3, boolean z) {
                Intrinsics.checkNotNullParameter(source, "source");
                source.skip(i3);
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public boolean onHeaders(int i2, @NotNull List<Header> responseHeaders, boolean z) {
                Intrinsics.checkNotNullParameter(responseHeaders, "responseHeaders");
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public boolean onRequest(int i2, @NotNull List<Header> requestHeaders) {
                Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
                return true;
            }

            @Override // okhttp3.internal.http2.PushObserver
            public void onReset(int i2, @NotNull ErrorCode errorCode) {
                Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            }
        }

        private Companion() {
        }
    }

    boolean onData(int i2, @NotNull BufferedSource bufferedSource, int i3, boolean z);

    boolean onHeaders(int i2, @NotNull List<Header> list, boolean z);

    boolean onRequest(int i2, @NotNull List<Header> list);

    void onReset(int i2, @NotNull ErrorCode errorCode);
}
