package com.facebook.stetho.server;

import android.content.Context;
import android.net.LocalSocket;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
/* loaded from: classes.dex */
public class ProtocolDetectingSocketHandler extends SecureSocketHandler {
    private static final int SENSING_BUFFER_SIZE = 256;
    private final ArrayList<HandlerInfo> mHandlers;

    /* loaded from: classes.dex */
    public static class AlwaysMatchMatcher implements MagicMatcher {
        @Override // com.facebook.stetho.server.ProtocolDetectingSocketHandler.MagicMatcher
        public boolean matches(InputStream inputStream) {
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class ExactMagicMatcher implements MagicMatcher {
        private final byte[] mMagic;

        public ExactMagicMatcher(byte[] bArr) {
            this.mMagic = bArr;
        }

        @Override // com.facebook.stetho.server.ProtocolDetectingSocketHandler.MagicMatcher
        public boolean matches(InputStream inputStream) {
            int length = this.mMagic.length;
            byte[] bArr = new byte[length];
            return inputStream.read(bArr) == length && Arrays.equals(bArr, this.mMagic);
        }
    }

    /* loaded from: classes.dex */
    private static class HandlerInfo {
        public final SocketLikeHandler handler;
        public final MagicMatcher magicMatcher;

        private HandlerInfo(MagicMatcher magicMatcher, SocketLikeHandler socketLikeHandler) {
            this.magicMatcher = magicMatcher;
            this.handler = socketLikeHandler;
        }
    }

    /* loaded from: classes.dex */
    public interface MagicMatcher {
        boolean matches(InputStream inputStream);
    }

    public ProtocolDetectingSocketHandler(Context context) {
        super(context);
        this.mHandlers = new ArrayList<>(2);
    }

    public void addHandler(MagicMatcher magicMatcher, SocketLikeHandler socketLikeHandler) {
        this.mHandlers.add(new HandlerInfo(magicMatcher, socketLikeHandler));
    }

    @Override // com.facebook.stetho.server.SecureSocketHandler
    protected void onSecured(LocalSocket localSocket) {
        LeakyBufferedInputStream leakyBufferedInputStream = new LeakyBufferedInputStream(localSocket.getInputStream(), 256);
        if (this.mHandlers.isEmpty()) {
            throw new IllegalStateException("No handlers added");
        }
        int size = this.mHandlers.size();
        for (int i2 = 0; i2 < size; i2++) {
            HandlerInfo handlerInfo = this.mHandlers.get(i2);
            leakyBufferedInputStream.mark(256);
            boolean matches = handlerInfo.magicMatcher.matches(leakyBufferedInputStream);
            leakyBufferedInputStream.reset();
            if (matches) {
                handlerInfo.handler.onAccepted(new SocketLike(localSocket, leakyBufferedInputStream));
                return;
            }
        }
        throw new IOException("No matching handler, firstByte=" + leakyBufferedInputStream.read());
    }
}
