package org.apache.http.io;

import org.apache.http.util.CharArrayBuffer;
/* loaded from: classes3.dex */
public interface SessionOutputBuffer {
    void flush();

    HttpTransportMetrics getMetrics();

    void write(int i2);

    void write(byte[] bArr);

    void write(byte[] bArr, int i2, int i3);

    void writeLine(String str);

    void writeLine(CharArrayBuffer charArrayBuffer);
}
