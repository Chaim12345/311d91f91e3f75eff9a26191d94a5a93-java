package com.facebook.stetho.server;

import android.net.LocalSocket;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes.dex */
public class LazySocketHandler implements SocketHandler {
    @Nullable
    private SocketHandler mSocketHandler;
    private final SocketHandlerFactory mSocketHandlerFactory;

    public LazySocketHandler(SocketHandlerFactory socketHandlerFactory) {
        this.mSocketHandlerFactory = socketHandlerFactory;
    }

    @Nonnull
    private synchronized SocketHandler getSocketHandler() {
        if (this.mSocketHandler == null) {
            this.mSocketHandler = this.mSocketHandlerFactory.create();
        }
        return this.mSocketHandler;
    }

    @Override // com.facebook.stetho.server.SocketHandler
    public void onAccepted(LocalSocket localSocket) {
        getSocketHandler().onAccepted(localSocket);
    }
}
