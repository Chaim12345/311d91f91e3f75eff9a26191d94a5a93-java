package org.apache.http.conn;

import org.apache.http.HttpConnection;
import org.apache.http.config.ConnectionConfig;
/* loaded from: classes3.dex */
public interface HttpConnectionFactory<T, C extends HttpConnection> {
    C create(T t2, ConnectionConfig connectionConfig);
}
