package org.apache.http.conn.scheme;

import java.net.Socket;
import org.apache.http.params.HttpParams;
@Deprecated
/* loaded from: classes3.dex */
public interface SchemeLayeredSocketFactory extends SchemeSocketFactory {
    Socket createLayeredSocket(Socket socket, String str, int i2, HttpParams httpParams);
}
