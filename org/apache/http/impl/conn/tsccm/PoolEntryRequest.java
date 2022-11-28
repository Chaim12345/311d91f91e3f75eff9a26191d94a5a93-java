package org.apache.http.impl.conn.tsccm;

import java.util.concurrent.TimeUnit;
@Deprecated
/* loaded from: classes3.dex */
public interface PoolEntryRequest {
    void abortRequest();

    BasicPoolEntry getPoolEntry(long j2, TimeUnit timeUnit);
}
