package org.apache.http.io;

import org.apache.http.util.CharArrayBuffer;
/* loaded from: classes3.dex */
public interface SessionInputBuffer {
    HttpTransportMetrics getMetrics();

    @Deprecated
    boolean isDataAvailable(int i2);

    int read();

    int read(byte[] bArr);

    int read(byte[] bArr, int i2, int i3);

    int readLine(CharArrayBuffer charArrayBuffer);

    String readLine();
}
