package org.apache.http.conn.scheme;

import java.net.Socket;
@Deprecated
/* loaded from: classes3.dex */
public interface LayeredSocketFactory extends SocketFactory {
    Socket createSocket(Socket socket, String str, int i2, boolean z);
}
