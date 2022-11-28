package com.facebook.stetho.server;

import android.net.LocalSocket;
import java.io.InputStream;
import java.io.OutputStream;
/* loaded from: classes.dex */
public class SocketLike {
    private final LeakyBufferedInputStream mLeakyInput;
    private final LocalSocket mSocket;

    public SocketLike(LocalSocket localSocket, LeakyBufferedInputStream leakyBufferedInputStream) {
        this.mSocket = localSocket;
        this.mLeakyInput = leakyBufferedInputStream;
    }

    public SocketLike(SocketLike socketLike, LeakyBufferedInputStream leakyBufferedInputStream) {
        this(socketLike.mSocket, leakyBufferedInputStream);
    }

    public InputStream getInput() {
        return this.mLeakyInput.leakBufferAndStream();
    }

    public OutputStream getOutput() {
        return this.mSocket.getOutputStream();
    }
}
