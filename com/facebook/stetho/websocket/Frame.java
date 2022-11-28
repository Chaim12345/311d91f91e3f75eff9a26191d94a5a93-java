package com.facebook.stetho.websocket;

import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.internal.ws.WebSocketProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Frame {
    public static final byte OPCODE_BINARY_FRAME = 2;
    public static final byte OPCODE_CONNECTION_CLOSE = 8;
    public static final byte OPCODE_CONNECTION_PING = 9;
    public static final byte OPCODE_CONNECTION_PONG = 10;
    public static final byte OPCODE_TEXT_FRAME = 1;
    public boolean fin;
    public boolean hasMask;
    public byte[] maskingKey;
    public byte opcode;
    public byte[] payloadData;
    public long payloadLen;
    public boolean rsv1;
    public boolean rsv2;
    public boolean rsv3;

    private void decodeFirstByte(byte b2) {
        this.fin = (b2 & 128) != 0;
        this.rsv1 = (b2 & SignedBytes.MAX_POWER_OF_TWO) != 0;
        this.rsv2 = (b2 & 32) != 0;
        this.rsv3 = (b2 & 16) != 0;
        this.opcode = (byte) (b2 & Ascii.SI);
    }

    private long decodeLength(byte b2, InputStream inputStream) {
        if (b2 <= 125) {
            return b2;
        }
        if (b2 == 126) {
            return ((readByteOrThrow(inputStream) & 255) << 8) | (readByteOrThrow(inputStream) & 255);
        }
        if (b2 != Byte.MAX_VALUE) {
            throw new IOException("Unexpected length byte: " + ((int) b2));
        }
        long j2 = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j2 = (j2 | (readByteOrThrow(inputStream) & 255)) << 8;
        }
        return j2;
    }

    private static byte[] decodeMaskingKey(InputStream inputStream) {
        byte[] bArr = new byte[4];
        readBytesOrThrow(inputStream, bArr, 0, 4);
        return bArr;
    }

    private byte encodeFirstByte() {
        byte b2 = this.fin ? (byte) 128 : (byte) 0;
        if (this.rsv1) {
            b2 = (byte) (b2 | SignedBytes.MAX_POWER_OF_TWO);
        }
        if (this.rsv2) {
            b2 = (byte) (b2 | 32);
        }
        if (this.rsv3) {
            b2 = (byte) (b2 | 16);
        }
        return (byte) (b2 | (this.opcode & Ascii.SI));
    }

    private static byte[] encodeLength(long j2) {
        return j2 <= 125 ? new byte[]{(byte) j2} : j2 <= WebSocketProtocol.PAYLOAD_SHORT_MAX ? new byte[]{126, (byte) ((j2 >> 8) & 255), (byte) (j2 & 255)} : new byte[]{Byte.MAX_VALUE, (byte) ((j2 >> 56) & 255), (byte) ((j2 >> 48) & 255), (byte) ((j2 >> 40) & 255), (byte) ((j2 >> 32) & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 8) & 255), (byte) (j2 & 255)};
    }

    private static byte readByteOrThrow(InputStream inputStream) {
        int read = inputStream.read();
        if (read != -1) {
            return (byte) read;
        }
        throw new EOFException();
    }

    private static void readBytesOrThrow(InputStream inputStream, byte[] bArr, int i2, int i3) {
        while (i3 > 0) {
            int read = inputStream.read(bArr, i2, i3);
            if (read == -1) {
                throw new EOFException();
            }
            i3 -= read;
            i2 += read;
        }
    }

    public void readFrom(BufferedInputStream bufferedInputStream) {
        decodeFirstByte(readByteOrThrow(bufferedInputStream));
        byte readByteOrThrow = readByteOrThrow(bufferedInputStream);
        this.hasMask = (readByteOrThrow & 128) != 0;
        this.payloadLen = decodeLength((byte) (readByteOrThrow & (-129)), bufferedInputStream);
        this.maskingKey = this.hasMask ? decodeMaskingKey(bufferedInputStream) : null;
        long j2 = this.payloadLen;
        byte[] bArr = new byte[(int) j2];
        this.payloadData = bArr;
        readBytesOrThrow(bufferedInputStream, bArr, 0, (int) j2);
        MaskingHelper.unmask(this.maskingKey, this.payloadData, 0, (int) this.payloadLen);
    }

    public void writeTo(BufferedOutputStream bufferedOutputStream) {
        bufferedOutputStream.write(encodeFirstByte());
        byte[] encodeLength = encodeLength(this.payloadLen);
        if (this.hasMask) {
            encodeLength[0] = (byte) (encodeLength[0] | 128);
        }
        bufferedOutputStream.write(encodeLength, 0, encodeLength.length);
        if (this.hasMask) {
            throw new UnsupportedOperationException("Writing masked data not implemented");
        }
        bufferedOutputStream.write(this.payloadData, 0, (int) this.payloadLen);
    }
}
