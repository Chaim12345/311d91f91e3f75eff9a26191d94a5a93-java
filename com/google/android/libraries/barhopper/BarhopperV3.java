package com.google.android.libraries.barhopper;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdi;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdn;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzen;
import com.google.barhopper.deeplearning.BarhopperV3Options;
import com.google.photos.vision.barhopper.BarhopperProto$BarhopperResponse;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
/* loaded from: classes2.dex */
public class BarhopperV3 implements Closeable {
    private static final long NULL_NATIVE_CONTEXT = 0;
    private static final String TAG = BarhopperV3.class.getSimpleName();
    private long nativeContext;

    public BarhopperV3() {
        System.loadLibrary("barhopper_v3");
    }

    private native void closeNative(long j2);

    private native long createNative();

    private native long createNativeWithClientOptions(byte[] bArr);

    private native byte[] recognizeBitmapNative(long j2, Bitmap bitmap, RecognitionOptions recognitionOptions);

    private native byte[] recognizeBufferNative(long j2, int i2, int i3, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeNative(long j2, int i2, int i3, byte[] bArr, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedBufferNative(long j2, int i2, int i3, int i4, ByteBuffer byteBuffer, RecognitionOptions recognitionOptions);

    private native byte[] recognizeStridedNative(long j2, int i2, int i3, int i4, byte[] bArr, RecognitionOptions recognitionOptions);

    private static BarhopperProto$BarhopperResponse toProto(byte[] bArr) {
        try {
            return BarhopperProto$BarhopperResponse.zzb(bArr, zzdn.zza());
        } catch (zzen e2) {
            throw new IllegalStateException("Received unexpected BarhopperResponse buffer: {0}".concat(e2.toString()));
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        long j2 = this.nativeContext;
        if (j2 != 0) {
            closeNative(j2);
            this.nativeContext = 0L;
        }
    }

    public void create() {
        if (this.nativeContext != 0) {
            return;
        }
        long createNative = createNative();
        this.nativeContext = createNative;
        if (createNative == 0) {
            throw new IllegalStateException("Failed to create native context.");
        }
    }

    public void create(@NonNull BarhopperV3Options barhopperV3Options) {
        if (this.nativeContext == 0) {
            try {
                byte[] bArr = new byte[barhopperV3Options.zzE()];
                zzdi zzF = zzdi.zzF(bArr);
                barhopperV3Options.zzW(zzF);
                zzF.zzG();
                long createNativeWithClientOptions = createNativeWithClientOptions(bArr);
                this.nativeContext = createNativeWithClientOptions;
                if (createNativeWithClientOptions == 0) {
                    throw new IllegalArgumentException("Failed to create native context with client options.");
                }
            } catch (IOException e2) {
                String name = barhopperV3Options.getClass().getName();
                StringBuilder sb = new StringBuilder(name.length() + 72);
                sb.append("Serializing ");
                sb.append(name);
                sb.append(" to a byte array threw an IOException (should never happen).");
                throw new RuntimeException(sb.toString(), e2);
            }
        }
    }

    protected void finalize() {
        try {
            close();
        } finally {
            super.finalize();
        }
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, int i4, @NonNull ByteBuffer byteBuffer, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativeContext;
        if (j2 != 0) {
            return toProto(recognizeStridedBufferNative(j2, i2, i3, i4, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, int i4, @NonNull byte[] bArr, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativeContext;
        if (j2 != 0) {
            return toProto(recognizeStridedNative(j2, i2, i3, i4, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, @NonNull ByteBuffer byteBuffer, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativeContext;
        if (j2 != 0) {
            return toProto(recognizeBufferNative(j2, i2, i3, byteBuffer, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(int i2, int i3, @NonNull byte[] bArr, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativeContext;
        if (j2 != 0) {
            return toProto(recognizeNative(j2, i2, i3, bArr, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }

    @NonNull
    public BarhopperProto$BarhopperResponse recognize(@NonNull Bitmap bitmap, @NonNull RecognitionOptions recognitionOptions) {
        long j2 = this.nativeContext;
        if (j2 != 0) {
            return toProto(recognizeBitmapNative(j2, bitmap, recognitionOptions));
        }
        throw new IllegalStateException("Native context does not exist.");
    }
}
