package com.google.crypto.tink;

import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
/* loaded from: classes2.dex */
public final class CryptoFormat {
    public static final int LEGACY_PREFIX_SIZE = 5;
    public static final byte LEGACY_START_BYTE = 0;
    public static final int NON_RAW_PREFIX_SIZE = 5;
    public static final byte[] RAW_PREFIX = new byte[0];
    public static final int RAW_PREFIX_SIZE = 0;
    public static final int TINK_PREFIX_SIZE = 5;
    public static final byte TINK_START_BYTE = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.crypto.tink.CryptoFormat$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f9605a;

        static {
            int[] iArr = new int[OutputPrefixType.values().length];
            f9605a = iArr;
            try {
                iArr[OutputPrefixType.LEGACY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9605a[OutputPrefixType.CRUNCHY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9605a[OutputPrefixType.TINK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f9605a[OutputPrefixType.RAW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static byte[] getOutputPrefix(Keyset.Key key) {
        ByteBuffer put;
        int i2 = AnonymousClass1.f9605a[key.getOutputPrefixType().ordinal()];
        if (i2 == 1 || i2 == 2) {
            put = ByteBuffer.allocate(5).put((byte) 0);
        } else if (i2 != 3) {
            if (i2 == 4) {
                return RAW_PREFIX;
            }
            throw new GeneralSecurityException("unknown output prefix type");
        } else {
            put = ByteBuffer.allocate(5).put((byte) 1);
        }
        return put.putInt(key.getKeyId()).array();
    }
}
