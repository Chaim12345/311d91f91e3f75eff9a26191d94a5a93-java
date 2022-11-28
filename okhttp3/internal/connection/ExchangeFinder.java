package okhttp3.internal.connection;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ExchangeFinder {
    @NotNull
    private final Address address;
    @NotNull
    private final RealCall call;
    @NotNull
    private final RealConnectionPool connectionPool;
    private int connectionShutdownCount;
    @NotNull
    private final EventListener eventListener;
    @Nullable
    private Route nextRouteToTry;
    private int otherFailureCount;
    private int refusedStreamCount;
    @Nullable
    private RouteSelector.Selection routeSelection;
    @Nullable
    private RouteSelector routeSelector;

    public ExchangeFinder(@NotNull RealConnectionPool connectionPool, @NotNull Address address, @NotNull RealCall call, @NotNull EventListener eventListener) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(address, "address");
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        this.connectionPool = connectionPool;
        this.address = address;
        this.call = call;
        this.eventListener = eventListener;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x013b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final RealConnection findConnection(int i2, int i3, int i4, int i5, boolean z) {
        List<Route> routes;
        RealConnection connection;
        Socket releaseConnectionNoEvents$okhttp;
        if (this.call.isCanceled()) {
            throw new IOException("Canceled");
        }
        RealConnection connection2 = this.call.getConnection();
        if (connection2 != null) {
            synchronized (connection2) {
                if (!connection2.getNoNewExchanges() && sameHostAndPort(connection2.route().address().url())) {
                    releaseConnectionNoEvents$okhttp = null;
                    Unit unit = Unit.INSTANCE;
                }
                releaseConnectionNoEvents$okhttp = this.call.releaseConnectionNoEvents$okhttp();
                Unit unit2 = Unit.INSTANCE;
            }
            if (this.call.getConnection() != null) {
                if (releaseConnectionNoEvents$okhttp == null) {
                    return connection2;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
            if (releaseConnectionNoEvents$okhttp != null) {
                Util.closeQuietly(releaseConnectionNoEvents$okhttp);
            }
            this.eventListener.connectionReleased(this.call, connection2);
        }
        this.refusedStreamCount = 0;
        this.connectionShutdownCount = 0;
        this.otherFailureCount = 0;
        if (!this.connectionPool.callAcquirePooledConnection(this.address, this.call, null, false)) {
            Route route = this.nextRouteToTry;
            try {
                if (route != null) {
                    Intrinsics.checkNotNull(route);
                    this.nextRouteToTry = null;
                } else {
                    RouteSelector.Selection selection = this.routeSelection;
                    if (selection != null) {
                        Intrinsics.checkNotNull(selection);
                        if (selection.hasNext()) {
                            RouteSelector.Selection selection2 = this.routeSelection;
                            Intrinsics.checkNotNull(selection2);
                            route = selection2.next();
                        }
                    }
                    RouteSelector routeSelector = this.routeSelector;
                    if (routeSelector == null) {
                        routeSelector = new RouteSelector(this.address, this.call.getClient().getRouteDatabase(), this.call, this.eventListener);
                        this.routeSelector = routeSelector;
                    }
                    RouteSelector.Selection next = routeSelector.next();
                    this.routeSelection = next;
                    routes = next.getRoutes();
                    if (this.call.isCanceled()) {
                        throw new IOException("Canceled");
                    }
                    if (!this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, false)) {
                        route = next.next();
                        RealConnection realConnection = new RealConnection(this.connectionPool, route);
                        this.call.setConnectionToCancel(realConnection);
                        realConnection.connect(i2, i3, i4, i5, z, this.call, this.eventListener);
                        this.call.setConnectionToCancel(null);
                        this.call.getClient().getRouteDatabase().connected(realConnection.route());
                        if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
                            synchronized (realConnection) {
                                this.connectionPool.put(realConnection);
                                this.call.acquireConnectionNoEvents(realConnection);
                                Unit unit3 = Unit.INSTANCE;
                            }
                            this.eventListener.connectionAcquired(this.call, realConnection);
                            return realConnection;
                        }
                        connection = this.call.getConnection();
                        Intrinsics.checkNotNull(connection);
                        this.nextRouteToTry = route;
                        Util.closeQuietly(realConnection.socket());
                        this.eventListener.connectionAcquired(this.call, connection);
                        return connection;
                    }
                }
                realConnection.connect(i2, i3, i4, i5, z, this.call, this.eventListener);
                this.call.setConnectionToCancel(null);
                this.call.getClient().getRouteDatabase().connected(realConnection.route());
                if (this.connectionPool.callAcquirePooledConnection(this.address, this.call, routes, true)) {
                }
            } catch (Throwable th) {
                this.call.setConnectionToCancel(null);
                throw th;
            }
            routes = null;
            RealConnection realConnection2 = new RealConnection(this.connectionPool, route);
            this.call.setConnectionToCancel(realConnection2);
        }
        connection = this.call.getConnection();
        Intrinsics.checkNotNull(connection);
        this.eventListener.connectionAcquired(this.call, connection);
        return connection;
    }

    private final RealConnection findHealthyConnection(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
        while (true) {
            RealConnection findConnection = findConnection(i2, i3, i4, i5, z);
            if (findConnection.isHealthy(z2)) {
                return findConnection;
            }
            findConnection.noNewExchanges$okhttp();
            if (this.nextRouteToTry == null) {
                RouteSelector.Selection selection = this.routeSelection;
                if (selection == null ? true : selection.hasNext()) {
                    continue;
                } else {
                    RouteSelector routeSelector = this.routeSelector;
                    if (!(routeSelector != null ? routeSelector.hasNext() : true)) {
                        throw new IOException("exhausted all routes");
                    }
                }
            }
        }
    }

    private final Route retryRoute() {
        RealConnection connection;
        if (this.refusedStreamCount > 1 || this.connectionShutdownCount > 1 || this.otherFailureCount > 0 || (connection = this.call.getConnection()) == null) {
            return null;
        }
        synchronized (connection) {
            if (connection.getRouteFailureCount$okhttp() != 0) {
                return null;
            }
            if (Util.canReuseConnectionFor(connection.route().address().url(), getAddress$okhttp().url())) {
                return connection.route();
            }
            return null;
        }
    }

    @NotNull
    public final ExchangeCodec find(@NotNull OkHttpClient client, @NotNull RealInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        try {
            return findHealthyConnection(chain.getConnectTimeoutMillis$okhttp(), chain.getReadTimeoutMillis$okhttp(), chain.getWriteTimeoutMillis$okhttp(), client.pingIntervalMillis(), client.retryOnConnectionFailure(), !Intrinsics.areEqual(chain.getRequest$okhttp().method(), "GET")).newCodec$okhttp(client, chain);
        } catch (IOException e2) {
            trackFailure(e2);
            throw new RouteException(e2);
        } catch (RouteException e3) {
            trackFailure(e3.getLastConnectException());
            throw e3;
        }
    }

    @NotNull
    public final Address getAddress$okhttp() {
        return this.address;
    }

    public final boolean retryAfterFailure() {
        RouteSelector routeSelector;
        boolean z = false;
        if (this.refusedStreamCount == 0 && this.connectionShutdownCount == 0 && this.otherFailureCount == 0) {
            return false;
        }
        if (this.nextRouteToTry != null) {
            return true;
        }
        Route retryRoute = retryRoute();
        if (retryRoute != null) {
            this.nextRouteToTry = retryRoute;
            return true;
        }
        RouteSelector.Selection selection = this.routeSelection;
        if (selection != null && selection.hasNext()) {
            z = true;
        }
        if (z || (routeSelector = this.routeSelector) == null) {
            return true;
        }
        return routeSelector.hasNext();
    }

    public final boolean sameHostAndPort(@NotNull HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HttpUrl url2 = this.address.url();
        return url.port() == url2.port() && Intrinsics.areEqual(url.host(), url2.host());
    }

    public final void trackFailure(@NotNull IOException e2) {
        Intrinsics.checkNotNullParameter(e2, "e");
        this.nextRouteToTry = null;
        if ((e2 instanceof StreamResetException) && ((StreamResetException) e2).errorCode == ErrorCode.REFUSED_STREAM) {
            this.refusedStreamCount++;
        } else if (e2 instanceof ConnectionShutdownException) {
            this.connectionShutdownCount++;
        } else {
            this.otherFailureCount++;
        }
    }
}
