package com.facebook.stetho.dumpapp;

import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.SocketLikeHandler;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class DumpappSocketLikeHandler implements SocketLikeHandler {
    public static final byte[] PROTOCOL_MAGIC = {68, 85, 77, 80};
    public static final int PROTOCOL_VERSION = 1;
    private final Dumper mDumper;

    public DumpappSocketLikeHandler(Dumper dumper) {
        this.mDumper = dumper;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void dump(Dumper dumper, Framer framer, String[] strArr) {
        try {
            framer.writeExitCode(dumper.dump(framer.getStdin(), framer.getStdout(), framer.getStderr(), strArr));
        } catch (DumpappOutputBrokenException unused) {
        }
    }

    private void establishConversation(DataInputStream dataInputStream) {
        byte[] bArr = new byte[4];
        dataInputStream.readFully(bArr);
        if (!Arrays.equals(PROTOCOL_MAGIC, bArr)) {
            throw logAndThrowProtocolException("Incompatible protocol, are you using an old dumpapp script?");
        }
        int readInt = dataInputStream.readInt();
        if (readInt == 1) {
            return;
        }
        throw logAndThrowProtocolException("Expected version=1; got=" + readInt);
    }

    private static IOException logAndThrowProtocolException(String str) {
        LogUtil.w(str);
        throw new IOException(str);
    }

    private String[] readArgs(Framer framer) {
        String[] strArr;
        synchronized (framer) {
            byte readFrameType = framer.readFrameType();
            if (readFrameType != 33) {
                throw new DumpappFramingException("Expected enter frame, got: " + ((int) readFrameType));
            }
            int readInt = framer.readInt();
            strArr = new String[readInt];
            for (int i2 = 0; i2 < readInt; i2++) {
                strArr[i2] = framer.readString();
            }
        }
        return strArr;
    }

    @Override // com.facebook.stetho.server.SocketLikeHandler
    public void onAccepted(SocketLike socketLike) {
        DataInputStream dataInputStream = new DataInputStream(socketLike.getInput());
        establishConversation(dataInputStream);
        Framer framer = new Framer(dataInputStream, socketLike.getOutput());
        dump(this.mDumper, framer, readArgs(framer));
    }
}
