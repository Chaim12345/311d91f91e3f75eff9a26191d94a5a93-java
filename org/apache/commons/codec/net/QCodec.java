package org.apache.commons.codec.net;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.BitSet;
import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringDecoder;
import org.apache.commons.codec.StringEncoder;
/* loaded from: classes3.dex */
public class QCodec extends RFC1522Codec implements StringEncoder, StringDecoder {
    private static final byte BLANK = 32;
    private static final BitSet PRINTABLE_CHARS;
    private static final byte UNDERSCORE = 95;
    private final Charset charset;
    private boolean encodeBlanks;

    static {
        BitSet bitSet = new BitSet(256);
        PRINTABLE_CHARS = bitSet;
        bitSet.set(32);
        bitSet.set(33);
        bitSet.set(34);
        bitSet.set(35);
        bitSet.set(36);
        bitSet.set(37);
        bitSet.set(38);
        bitSet.set(39);
        bitSet.set(40);
        bitSet.set(41);
        bitSet.set(42);
        bitSet.set(43);
        bitSet.set(44);
        bitSet.set(45);
        bitSet.set(46);
        bitSet.set(47);
        for (int i2 = 48; i2 <= 57; i2++) {
            PRINTABLE_CHARS.set(i2);
        }
        BitSet bitSet2 = PRINTABLE_CHARS;
        bitSet2.set(58);
        bitSet2.set(59);
        bitSet2.set(60);
        bitSet2.set(62);
        bitSet2.set(64);
        for (int i3 = 65; i3 <= 90; i3++) {
            PRINTABLE_CHARS.set(i3);
        }
        BitSet bitSet3 = PRINTABLE_CHARS;
        bitSet3.set(91);
        bitSet3.set(92);
        bitSet3.set(93);
        bitSet3.set(94);
        bitSet3.set(96);
        for (int i4 = 97; i4 <= 122; i4++) {
            PRINTABLE_CHARS.set(i4);
        }
        BitSet bitSet4 = PRINTABLE_CHARS;
        bitSet4.set(123);
        bitSet4.set(124);
        bitSet4.set(125);
        bitSet4.set(126);
    }

    public QCodec() {
        this(Charsets.UTF_8);
    }

    public QCodec(String str) {
        this(Charset.forName(str));
    }

    public QCodec(Charset charset) {
        this.encodeBlanks = false;
        this.charset = charset;
    }

    @Override // org.apache.commons.codec.Decoder
    public Object decode(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return decode((String) obj);
        }
        throw new DecoderException("Objects of type " + obj.getClass().getName() + " cannot be decoded using Q codec");
    }

    @Override // org.apache.commons.codec.StringDecoder
    public String decode(String str) {
        if (str == null) {
            return null;
        }
        try {
            return decodeText(str);
        } catch (UnsupportedEncodingException e2) {
            throw new DecoderException(e2.getMessage(), e2);
        }
    }

    @Override // org.apache.commons.codec.net.RFC1522Codec
    protected byte[] doDecoding(byte[] bArr) {
        boolean z;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            } else if (bArr[i2] == 95) {
                z = true;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            byte[] bArr2 = new byte[bArr.length];
            for (int i3 = 0; i3 < bArr.length; i3++) {
                byte b2 = bArr[i3];
                if (b2 != 95) {
                    bArr2[i3] = b2;
                } else {
                    bArr2[i3] = 32;
                }
            }
            return QuotedPrintableCodec.decodeQuotedPrintable(bArr2);
        }
        return QuotedPrintableCodec.decodeQuotedPrintable(bArr);
    }

    @Override // org.apache.commons.codec.net.RFC1522Codec
    protected byte[] doEncoding(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] encodeQuotedPrintable = QuotedPrintableCodec.encodeQuotedPrintable(PRINTABLE_CHARS, bArr);
        if (this.encodeBlanks) {
            for (int i2 = 0; i2 < encodeQuotedPrintable.length; i2++) {
                if (encodeQuotedPrintable[i2] == 32) {
                    encodeQuotedPrintable[i2] = 95;
                }
            }
        }
        return encodeQuotedPrintable;
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return encode((String) obj);
        }
        throw new EncoderException("Objects of type " + obj.getClass().getName() + " cannot be encoded using Q codec");
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String str) {
        if (str == null) {
            return null;
        }
        return encode(str, getCharset());
    }

    public String encode(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return encodeText(str, str2);
        } catch (UnsupportedEncodingException e2) {
            throw new EncoderException(e2.getMessage(), e2);
        }
    }

    public String encode(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return encodeText(str, charset);
    }

    public Charset getCharset() {
        return this.charset;
    }

    public String getDefaultCharset() {
        return this.charset.name();
    }

    @Override // org.apache.commons.codec.net.RFC1522Codec
    protected String getEncoding() {
        return "Q";
    }

    public boolean isEncodeBlanks() {
        return this.encodeBlanks;
    }

    public void setEncodeBlanks(boolean z) {
        this.encodeBlanks = z;
    }
}
