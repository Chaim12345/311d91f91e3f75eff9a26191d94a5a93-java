package org.apache.http.conn;

import java.util.concurrent.TimeUnit;
@Deprecated
/* loaded from: classes3.dex */
public interface ClientConnectionRequest {
    void abortRequest();

    ManagedClientConnection getConnection(long j2, TimeUnit timeUnit);
}
