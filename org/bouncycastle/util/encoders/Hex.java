package org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.bouncycastle.util.Strings;
/* loaded from: classes4.dex */
public class Hex {
    private static final HexEncoder encoder = new HexEncoder();

    public static int decode(String str, OutputStream outputStream) {
        return encoder.decode(str, outputStream);
    }

    public static byte[] decode(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new DecoderException("exception decoding Hex string: " + e2.getMessage(), e2);
        }
    }

    public static byte[] decode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.decode(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new DecoderException("exception decoding Hex data: " + e2.getMessage(), e2);
        }
    }

    public static byte[] decodeStrict(String str) {
        try {
            return encoder.a(str, 0, str.length());
        } catch (Exception e2) {
            throw new DecoderException("exception decoding Hex string: " + e2.getMessage(), e2);
        }
    }

    public static byte[] decodeStrict(String str, int i2, int i3) {
        try {
            return encoder.a(str, i2, i3);
        } catch (Exception e2) {
            throw new DecoderException("exception decoding Hex string: " + e2.getMessage(), e2);
        }
    }

    public static int encode(byte[] bArr, int i2, int i3, OutputStream outputStream) {
        return encoder.encode(bArr, i2, i3, outputStream);
    }

    public static int encode(byte[] bArr, OutputStream outputStream) {
        return encoder.encode(bArr, 0, bArr.length, outputStream);
    }

    public static byte[] encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static byte[] encode(byte[] bArr, int i2, int i3) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encoder.encode(bArr, i2, i3, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new EncoderException("exception encoding Hex string: " + e2.getMessage(), e2);
        }
    }

    public static String toHexString(byte[] bArr) {
        return toHexString(bArr, 0, bArr.length);
    }

    public static String toHexString(byte[] bArr, int i2, int i3) {
        return Strings.fromByteArray(encode(bArr, i2, i3));
    }
}
