package org.apache.http.conn.scheme;

import java.net.Socket;
@Deprecated
/* loaded from: classes3.dex */
public interface LayeredSchemeSocketFactory extends SchemeSocketFactory {
    Socket createLayeredSocket(Socket socket, String str, int i2, boolean z);
}
