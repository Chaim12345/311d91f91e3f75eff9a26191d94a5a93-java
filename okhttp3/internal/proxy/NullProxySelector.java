package okhttp3.internal.proxy;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class NullProxySelector extends ProxySelector {
    @NotNull
    public static final NullProxySelector INSTANCE = new NullProxySelector();

    private NullProxySelector() {
    }

    @Override // java.net.ProxySelector
    public void connectFailed(@Nullable URI uri, @Nullable SocketAddress socketAddress, @Nullable IOException iOException) {
    }

    @Override // java.net.ProxySelector
    @NotNull
    public List<Proxy> select(@Nullable URI uri) {
        List<Proxy> listOf;
        if (uri != null) {
            listOf = CollectionsKt__CollectionsJVMKt.listOf(Proxy.NO_PROXY);
            return listOf;
        }
        throw new IllegalArgumentException("uri must not be null".toString());
    }
}
