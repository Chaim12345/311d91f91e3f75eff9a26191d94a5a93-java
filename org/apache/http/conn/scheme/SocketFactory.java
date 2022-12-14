package org.apache.http.conn.scheme;

import java.net.InetAddress;
import java.net.Socket;
import org.apache.http.params.HttpParams;
@Deprecated
/* loaded from: classes3.dex */
public interface SocketFactory {
    Socket connectSocket(Socket socket, String str, int i2, InetAddress inetAddress, int i3, HttpParams httpParams);

    Socket createSocket();

    boolean isSecure(Socket socket);
}
