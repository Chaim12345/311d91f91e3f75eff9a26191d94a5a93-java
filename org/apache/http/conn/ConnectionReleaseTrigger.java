package org.apache.http.conn;
/* loaded from: classes3.dex */
public interface ConnectionReleaseTrigger {
    void abortConnection();

    void releaseConnection();
}
