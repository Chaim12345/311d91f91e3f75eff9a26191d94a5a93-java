package com.fasterxml.jackson.core;

import java.io.OutputStream;
import java.nio.ByteBuffer;
/* loaded from: classes.dex */
public interface SerializableString {
    int appendQuoted(char[] cArr, int i2);

    int appendQuotedUTF8(byte[] bArr, int i2);

    int appendUnquoted(char[] cArr, int i2);

    int appendUnquotedUTF8(byte[] bArr, int i2);

    char[] asQuotedChars();

    byte[] asQuotedUTF8();

    byte[] asUnquotedUTF8();

    int charLength();

    String getValue();

    int putQuotedUTF8(ByteBuffer byteBuffer);

    int putUnquotedUTF8(ByteBuffer byteBuffer);

    int writeQuotedUTF8(OutputStream outputStream);

    int writeUnquotedUTF8(OutputStream outputStream);
}
