package org.bouncycastle.est;

import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes3.dex */
public interface Source<T> {
    void close();

    InputStream getInputStream();

    OutputStream getOutputStream();

    T getSession();
}
