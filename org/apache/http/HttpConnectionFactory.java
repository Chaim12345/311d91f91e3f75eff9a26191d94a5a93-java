package org.apache.http;

import java.net.Socket;
import org.apache.http.HttpConnection;
/* loaded from: classes3.dex */
public interface HttpConnectionFactory<T extends HttpConnection> {
    T createConnection(Socket socket);
}
