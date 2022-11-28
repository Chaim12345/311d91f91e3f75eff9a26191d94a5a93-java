package androidx.camera.core.impl.utils;

import androidx.annotation.NonNull;
import androidx.camera.core.impl.utils.ExifData;
import androidx.core.util.Preconditions;
import java.io.BufferedOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import kotlin.UShort;
/* loaded from: classes.dex */
public final class ExifOutputStream extends FilterOutputStream {
    private static final short BYTE_ALIGN_II = 18761;
    private static final short BYTE_ALIGN_MM = 19789;
    private static final boolean DEBUG = false;
    private static final byte[] IDENTIFIER_EXIF_APP1 = "Exif\u0000\u0000".getBytes(ExifAttribute.f1182a);
    private static final int IFD_OFFSET = 8;
    private static final byte START_CODE = 42;
    private static final int STATE_FRAME_HEADER = 1;
    private static final int STATE_JPEG_DATA = 2;
    private static final int STATE_SOI = 0;
    private static final int STREAMBUFFER_SIZE = 65536;
    private static final String TAG = "ExifOutputStream";
    private final ByteBuffer mBuffer;
    private int mByteToCopy;
    private int mByteToSkip;
    private final ExifData mExifData;
    private final byte[] mSingleByteArray;
    private int mState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class JpegHeader {
        public static final short APP1 = -31;
        public static final short DAC = -52;
        public static final short DHT = -60;
        public static final short EOI = -39;
        public static final short JPG = -56;
        public static final short SOF0 = -64;
        public static final short SOF15 = -49;
        public static final short SOI = -40;

        private JpegHeader() {
        }

        public static boolean isSofMarker(short s2) {
            return (s2 < -64 || s2 > -49 || s2 == -60 || s2 == -56 || s2 == -52) ? false : true;
        }
    }

    public ExifOutputStream(@NonNull OutputStream outputStream, @NonNull ExifData exifData) {
        super(new BufferedOutputStream(outputStream, 65536));
        this.mSingleByteArray = new byte[1];
        this.mBuffer = ByteBuffer.allocate(4);
        this.mState = 0;
        this.mExifData = exifData;
    }

    private int requestByteToBuffer(int i2, byte[] bArr, int i3, int i4) {
        int min = Math.min(i4, i2 - this.mBuffer.position());
        this.mBuffer.put(bArr, i3, min);
        return min;
    }

    private void writeExifSegment(@NonNull ByteOrderedDataOutputStream byteOrderedDataOutputStream) {
        ExifTag[] exifTagArr;
        ExifTag[][] exifTagArr2 = ExifData.f1187b;
        int[] iArr = new int[exifTagArr2.length];
        int[] iArr2 = new int[exifTagArr2.length];
        for (ExifTag exifTag : ExifData.f1186a) {
            for (int i2 = 0; i2 < ExifData.f1187b.length; i2++) {
                this.mExifData.a(i2).remove(exifTag.name);
            }
        }
        if (!this.mExifData.a(1).isEmpty()) {
            this.mExifData.a(0).put(ExifData.f1186a[1].name, ExifAttribute.createULong(0L, this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.a(2).isEmpty()) {
            this.mExifData.a(0).put(ExifData.f1186a[2].name, ExifAttribute.createULong(0L, this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.a(3).isEmpty()) {
            this.mExifData.a(1).put(ExifData.f1186a[3].name, ExifAttribute.createULong(0L, this.mExifData.getByteOrder()));
        }
        for (int i3 = 0; i3 < ExifData.f1187b.length; i3++) {
            int i4 = 0;
            for (Map.Entry entry : this.mExifData.a(i3).entrySet()) {
                int size = ((ExifAttribute) entry.getValue()).size();
                if (size > 4) {
                    i4 += size;
                }
            }
            iArr2[i3] = iArr2[i3] + i4;
        }
        int i5 = 8;
        for (int i6 = 0; i6 < ExifData.f1187b.length; i6++) {
            if (!this.mExifData.a(i6).isEmpty()) {
                iArr[i6] = i5;
                i5 += (this.mExifData.a(i6).size() * 12) + 2 + 4 + iArr2[i6];
            }
        }
        int i7 = i5 + 8;
        if (!this.mExifData.a(1).isEmpty()) {
            this.mExifData.a(0).put(ExifData.f1186a[1].name, ExifAttribute.createULong(iArr[1], this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.a(2).isEmpty()) {
            this.mExifData.a(0).put(ExifData.f1186a[2].name, ExifAttribute.createULong(iArr[2], this.mExifData.getByteOrder()));
        }
        if (!this.mExifData.a(3).isEmpty()) {
            this.mExifData.a(1).put(ExifData.f1186a[3].name, ExifAttribute.createULong(iArr[3], this.mExifData.getByteOrder()));
        }
        byteOrderedDataOutputStream.writeUnsignedShort(i7);
        byteOrderedDataOutputStream.write(IDENTIFIER_EXIF_APP1);
        byteOrderedDataOutputStream.writeShort(this.mExifData.getByteOrder() == ByteOrder.BIG_ENDIAN ? BYTE_ALIGN_MM : BYTE_ALIGN_II);
        byteOrderedDataOutputStream.setByteOrder(this.mExifData.getByteOrder());
        byteOrderedDataOutputStream.writeUnsignedShort(42);
        byteOrderedDataOutputStream.writeUnsignedInt(8L);
        for (int i8 = 0; i8 < ExifData.f1187b.length; i8++) {
            if (!this.mExifData.a(i8).isEmpty()) {
                byteOrderedDataOutputStream.writeUnsignedShort(this.mExifData.a(i8).size());
                int size2 = iArr[i8] + 2 + (this.mExifData.a(i8).size() * 12) + 4;
                for (Map.Entry entry2 : this.mExifData.a(i8).entrySet()) {
                    int i9 = ((ExifTag) Preconditions.checkNotNull((ExifTag) ((HashMap) ExifData.Builder.f1191b.get(i8)).get(entry2.getKey()), "Tag not supported: " + ((String) entry2.getKey()) + ". Tag needs to be ported from ExifInterface to ExifData.")).number;
                    ExifAttribute exifAttribute = (ExifAttribute) entry2.getValue();
                    int size3 = exifAttribute.size();
                    byteOrderedDataOutputStream.writeUnsignedShort(i9);
                    byteOrderedDataOutputStream.writeUnsignedShort(exifAttribute.format);
                    byteOrderedDataOutputStream.writeInt(exifAttribute.numberOfComponents);
                    if (size3 > 4) {
                        byteOrderedDataOutputStream.writeUnsignedInt(size2);
                        size2 += size3;
                    } else {
                        byteOrderedDataOutputStream.write(exifAttribute.bytes);
                        if (size3 < 4) {
                            while (size3 < 4) {
                                byteOrderedDataOutputStream.writeByte(0);
                                size3++;
                            }
                        }
                    }
                }
                byteOrderedDataOutputStream.writeUnsignedInt(0L);
                for (Map.Entry entry3 : this.mExifData.a(i8).entrySet()) {
                    byte[] bArr = ((ExifAttribute) entry3.getValue()).bytes;
                    if (bArr.length > 4) {
                        byteOrderedDataOutputStream.write(bArr, 0, bArr.length);
                    }
                }
            }
        }
        byteOrderedDataOutputStream.setByteOrder(ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(int i2) {
        byte[] bArr = this.mSingleByteArray;
        bArr[0] = (byte) (i2 & 255);
        write(bArr);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(@NonNull byte[] bArr) {
        write(bArr, 0, bArr.length);
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0105, code lost:
        if (r9 <= 0) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0107, code lost:
        ((java.io.FilterOutputStream) r6).out.write(r7, r8, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010c, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
        return;
     */
    @Override // java.io.FilterOutputStream, java.io.OutputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void write(@NonNull byte[] bArr, int i2, int i3) {
        while (true) {
            int i4 = this.mByteToSkip;
            if ((i4 > 0 || this.mByteToCopy > 0 || this.mState != 2) && i3 > 0) {
                if (i4 > 0) {
                    int min = Math.min(i3, i4);
                    i3 -= min;
                    this.mByteToSkip -= min;
                    i2 += min;
                }
                int i5 = this.mByteToCopy;
                if (i5 > 0) {
                    int min2 = Math.min(i3, i5);
                    ((FilterOutputStream) this).out.write(bArr, i2, min2);
                    i3 -= min2;
                    this.mByteToCopy -= min2;
                    i2 += min2;
                }
                if (i3 == 0) {
                    return;
                }
                int i6 = this.mState;
                if (i6 == 0) {
                    int requestByteToBuffer = requestByteToBuffer(2, bArr, i2, i3);
                    i2 += requestByteToBuffer;
                    i3 -= requestByteToBuffer;
                    if (this.mBuffer.position() < 2) {
                        return;
                    }
                    this.mBuffer.rewind();
                    if (this.mBuffer.getShort() != -40) {
                        throw new IOException("Not a valid jpeg image, cannot write exif");
                    }
                    ((FilterOutputStream) this).out.write(this.mBuffer.array(), 0, 2);
                    this.mState = 1;
                    this.mBuffer.rewind();
                    ByteOrderedDataOutputStream byteOrderedDataOutputStream = new ByteOrderedDataOutputStream(((FilterOutputStream) this).out, ByteOrder.BIG_ENDIAN);
                    byteOrderedDataOutputStream.writeShort((short) -31);
                    writeExifSegment(byteOrderedDataOutputStream);
                } else if (i6 != 1) {
                    continue;
                } else {
                    int requestByteToBuffer2 = requestByteToBuffer(4, bArr, i2, i3);
                    i2 += requestByteToBuffer2;
                    i3 -= requestByteToBuffer2;
                    if (this.mBuffer.position() == 2 && this.mBuffer.getShort() == -39) {
                        ((FilterOutputStream) this).out.write(this.mBuffer.array(), 0, 2);
                        this.mBuffer.rewind();
                    }
                    if (this.mBuffer.position() < 4) {
                        return;
                    }
                    this.mBuffer.rewind();
                    short s2 = this.mBuffer.getShort();
                    if (s2 == -31) {
                        this.mByteToSkip = (this.mBuffer.getShort() & UShort.MAX_VALUE) - 2;
                    } else if (JpegHeader.isSofMarker(s2)) {
                        ((FilterOutputStream) this).out.write(this.mBuffer.array(), 0, 4);
                    } else {
                        ((FilterOutputStream) this).out.write(this.mBuffer.array(), 0, 4);
                        this.mByteToCopy = (this.mBuffer.getShort() & UShort.MAX_VALUE) - 2;
                        this.mBuffer.rewind();
                    }
                    this.mState = 2;
                    this.mBuffer.rewind();
                }
            }
        }
    }
}
