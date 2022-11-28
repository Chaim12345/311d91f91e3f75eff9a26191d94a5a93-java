package com.facebook.stetho.websocket;

import com.facebook.stetho.common.Utf8Charset;
/* loaded from: classes.dex */
class FrameHelper {
    FrameHelper() {
    }

    public static Frame createBinaryFrame(byte[] bArr) {
        return createSimpleFrame((byte) 2, bArr);
    }

    public static Frame createCloseFrame(int i2, String str) {
        byte[] bArr;
        int i3;
        if (str != null) {
            bArr = Utf8Charset.encodeUTF8(str);
            i3 = bArr.length + 2;
        } else {
            bArr = null;
            i3 = 2;
        }
        byte[] bArr2 = new byte[i3];
        bArr2[0] = (byte) ((i2 >> 8) & 255);
        bArr2[1] = (byte) (i2 & 255);
        if (bArr != null) {
            System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        }
        return createSimpleFrame((byte) 8, bArr2);
    }

    public static Frame createPingFrame(byte[] bArr, int i2) {
        return createSimpleFrame((byte) 9, bArr, i2);
    }

    public static Frame createPongFrame(byte[] bArr, int i2) {
        return createSimpleFrame((byte) 10, bArr, i2);
    }

    private static Frame createSimpleFrame(byte b2, byte[] bArr) {
        return createSimpleFrame(b2, bArr, bArr.length);
    }

    private static Frame createSimpleFrame(byte b2, byte[] bArr, int i2) {
        Frame frame = new Frame();
        frame.fin = true;
        frame.hasMask = false;
        frame.opcode = b2;
        frame.payloadLen = i2;
        frame.payloadData = bArr;
        return frame;
    }

    public static Frame createTextFrame(String str) {
        return createSimpleFrame((byte) 1, Utf8Charset.encodeUTF8(str));
    }
}
