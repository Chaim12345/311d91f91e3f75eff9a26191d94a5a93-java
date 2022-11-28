package org.apache.http.impl.io;

import java.net.Socket;
import org.apache.http.params.HttpParams;
import org.apache.http.util.Args;
@Deprecated
/* loaded from: classes3.dex */
public class SocketOutputBuffer extends AbstractSessionOutputBuffer {
    public SocketOutputBuffer(Socket socket, int i2, HttpParams httpParams) {
        Args.notNull(socket, "Socket");
        i2 = i2 < 0 ? socket.getSendBufferSize() : i2;
        init(socket.getOutputStream(), i2 < 1024 ? 1024 : i2, httpParams);
    }
}
