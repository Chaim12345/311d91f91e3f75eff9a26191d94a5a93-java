package com.yalantis.ucrop.util;

import android.media.ExifInterface;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
/* loaded from: classes3.dex */
public class ImageHeaderParser {
    private static final int EXIF_MAGIC_NUMBER = 65496;
    private static final int EXIF_SEGMENT_TYPE = 225;
    private static final int INTEL_TIFF_MAGIC_NUMBER = 18761;
    private static final int MARKER_EOI = 217;
    private static final int MOTOROLA_TIFF_MAGIC_NUMBER = 19789;
    private static final int ORIENTATION_TAG_TYPE = 274;
    private static final int SEGMENT_SOS = 218;
    private static final int SEGMENT_START_ID = 255;
    private static final String TAG = "ImageHeaderParser";
    public static final int UNKNOWN_ORIENTATION = -1;
    private final Reader reader;
    private static final String JPEG_EXIF_SEGMENT_PREAMBLE = "Exif\u0000\u0000";
    private static final byte[] JPEG_EXIF_SEGMENT_PREAMBLE_BYTES = JPEG_EXIF_SEGMENT_PREAMBLE.getBytes(Charset.forName("UTF-8"));
    private static final int[] BYTES_PER_FORMAT = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class RandomAccessReader {
        private final ByteBuffer data;

        public RandomAccessReader(byte[] bArr, int i2) {
            this.data = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i2);
        }

        public short getInt16(int i2) {
            return this.data.getShort(i2);
        }

        public int getInt32(int i2) {
            return this.data.getInt(i2);
        }

        public int length() {
            return this.data.remaining();
        }

        public void order(ByteOrder byteOrder) {
            this.data.order(byteOrder);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public interface Reader {
        int getUInt16();

        short getUInt8();

        int read(byte[] bArr, int i2);

        long skip(long j2);
    }

    /* loaded from: classes3.dex */
    private static class StreamReader implements Reader {
        private final InputStream is;

        public StreamReader(InputStream inputStream) {
            this.is = inputStream;
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public int getUInt16() {
            return ((this.is.read() << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | (this.is.read() & 255);
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public short getUInt8() {
            return (short) (this.is.read() & 255);
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public int read(byte[] bArr, int i2) {
            int i3 = i2;
            while (i3 > 0) {
                int read = this.is.read(bArr, i2 - i3, i3);
                if (read == -1) {
                    break;
                }
                i3 -= read;
            }
            return i2 - i3;
        }

        @Override // com.yalantis.ucrop.util.ImageHeaderParser.Reader
        public long skip(long j2) {
            if (j2 < 0) {
                return 0L;
            }
            long j3 = j2;
            while (j3 > 0) {
                long skip = this.is.skip(j3);
                if (skip <= 0) {
                    if (this.is.read() == -1) {
                        break;
                    }
                    skip = 1;
                }
                j3 -= skip;
            }
            return j2 - j3;
        }
    }

    public ImageHeaderParser(InputStream inputStream) {
        this.reader = new StreamReader(inputStream);
    }

    private static int calcTagOffset(int i2, int i3) {
        return i2 + 2 + (i3 * 12);
    }

    public static void copyExif(ExifInterface exifInterface, int i2, int i3, String str) {
        String[] strArr = {androidx.exifinterface.media.ExifInterface.TAG_F_NUMBER, androidx.exifinterface.media.ExifInterface.TAG_DATETIME, androidx.exifinterface.media.ExifInterface.TAG_DATETIME_DIGITIZED, androidx.exifinterface.media.ExifInterface.TAG_EXPOSURE_TIME, androidx.exifinterface.media.ExifInterface.TAG_FLASH, androidx.exifinterface.media.ExifInterface.TAG_FOCAL_LENGTH, androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE, androidx.exifinterface.media.ExifInterface.TAG_GPS_ALTITUDE_REF, androidx.exifinterface.media.ExifInterface.TAG_GPS_DATESTAMP, androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE, androidx.exifinterface.media.ExifInterface.TAG_GPS_LATITUDE_REF, androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE, androidx.exifinterface.media.ExifInterface.TAG_GPS_LONGITUDE_REF, androidx.exifinterface.media.ExifInterface.TAG_GPS_PROCESSING_METHOD, androidx.exifinterface.media.ExifInterface.TAG_GPS_TIMESTAMP, androidx.exifinterface.media.ExifInterface.TAG_ISO_SPEED_RATINGS, androidx.exifinterface.media.ExifInterface.TAG_MAKE, androidx.exifinterface.media.ExifInterface.TAG_MODEL, androidx.exifinterface.media.ExifInterface.TAG_SUBSEC_TIME, androidx.exifinterface.media.ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, androidx.exifinterface.media.ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, androidx.exifinterface.media.ExifInterface.TAG_WHITE_BALANCE};
        try {
            ExifInterface exifInterface2 = new ExifInterface(str);
            for (int i4 = 0; i4 < 22; i4++) {
                String str2 = strArr[i4];
                String attribute = exifInterface.getAttribute(str2);
                if (!TextUtils.isEmpty(attribute)) {
                    exifInterface2.setAttribute(str2, attribute);
                }
            }
            exifInterface2.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(i2));
            exifInterface2.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(i3));
            exifInterface2.setAttribute(androidx.exifinterface.media.ExifInterface.TAG_ORIENTATION, "0");
            exifInterface2.saveAttributes();
        } catch (IOException e2) {
            e2.getMessage();
        }
    }

    private static boolean handles(int i2) {
        return (i2 & EXIF_MAGIC_NUMBER) == EXIF_MAGIC_NUMBER || i2 == MOTOROLA_TIFF_MAGIC_NUMBER || i2 == INTEL_TIFF_MAGIC_NUMBER;
    }

    private boolean hasJpegExifPreamble(byte[] bArr, int i2) {
        boolean z = bArr != null && i2 > JPEG_EXIF_SEGMENT_PREAMBLE_BYTES.length;
        if (z) {
            int i3 = 0;
            while (true) {
                byte[] bArr2 = JPEG_EXIF_SEGMENT_PREAMBLE_BYTES;
                if (i3 >= bArr2.length) {
                    break;
                } else if (bArr[i3] != bArr2[i3]) {
                    return false;
                } else {
                    i3++;
                }
            }
        }
        return z;
    }

    private int moveToExifSegmentAndGetLength() {
        short uInt8;
        int uInt16;
        long j2;
        long skip;
        do {
            short uInt82 = this.reader.getUInt8();
            if (uInt82 != 255) {
                if (Log.isLoggable(TAG, 3)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unknown segmentId=");
                    sb.append((int) uInt82);
                }
                return -1;
            }
            uInt8 = this.reader.getUInt8();
            if (uInt8 == SEGMENT_SOS) {
                return -1;
            }
            if (uInt8 == MARKER_EOI) {
                Log.isLoggable(TAG, 3);
                return -1;
            }
            uInt16 = this.reader.getUInt16() - 2;
            if (uInt8 == EXIF_SEGMENT_TYPE) {
                return uInt16;
            }
            j2 = uInt16;
            skip = this.reader.skip(j2);
        } while (skip == j2);
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to skip enough data, type: ");
            sb2.append((int) uInt8);
            sb2.append(", wanted to skip: ");
            sb2.append(uInt16);
            sb2.append(", but actually skipped: ");
            sb2.append(skip);
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static int parseExifSegment(RandomAccessReader randomAccessReader) {
        ByteOrder byteOrder;
        short int16;
        int i2;
        StringBuilder sb;
        String str;
        short int162 = randomAccessReader.getInt16(6);
        if (int162 != MOTOROLA_TIFF_MAGIC_NUMBER) {
            if (int162 == INTEL_TIFF_MAGIC_NUMBER) {
                byteOrder = ByteOrder.LITTLE_ENDIAN;
                randomAccessReader.order(byteOrder);
                int int32 = randomAccessReader.getInt32(10) + 6;
                int16 = randomAccessReader.getInt16(int32);
                for (i2 = 0; i2 < int16; i2++) {
                    int calcTagOffset = calcTagOffset(int32, i2);
                    short int163 = randomAccessReader.getInt16(calcTagOffset);
                    if (int163 == ORIENTATION_TAG_TYPE) {
                        short int164 = randomAccessReader.getInt16(calcTagOffset + 2);
                        if (int164 >= 1 && int164 <= 12) {
                            int int322 = randomAccessReader.getInt32(calcTagOffset + 4);
                            if (int322 < 0) {
                                Log.isLoggable(TAG, 3);
                            } else {
                                if (Log.isLoggable(TAG, 3)) {
                                    StringBuilder sb2 = new StringBuilder();
                                    sb2.append("Got tagIndex=");
                                    sb2.append(i2);
                                    sb2.append(" tagType=");
                                    sb2.append((int) int163);
                                    sb2.append(" formatCode=");
                                    sb2.append((int) int164);
                                    sb2.append(" componentCount=");
                                    sb2.append(int322);
                                }
                                int i3 = int322 + BYTES_PER_FORMAT[int164];
                                if (i3 <= 4) {
                                    int i4 = calcTagOffset + 8;
                                    if (i4 < 0 || i4 > randomAccessReader.length()) {
                                        if (Log.isLoggable(TAG, 3)) {
                                            StringBuilder sb3 = new StringBuilder();
                                            sb3.append("Illegal tagValueOffset=");
                                            sb3.append(i4);
                                            sb3.append(" tagType=");
                                            sb3.append((int) int163);
                                        }
                                    } else if (i3 >= 0 && i3 + i4 <= randomAccessReader.length()) {
                                        return randomAccessReader.getInt16(i4);
                                    } else {
                                        if (Log.isLoggable(TAG, 3)) {
                                            StringBuilder sb4 = new StringBuilder();
                                            sb4.append("Illegal number of bytes for TI tag data tagType=");
                                            sb4.append((int) int163);
                                        }
                                    }
                                } else if (Log.isLoggable(TAG, 3)) {
                                    sb = new StringBuilder();
                                    str = "Got byte count > 4, not orientation, continuing, formatCode=";
                                    sb.append(str);
                                    sb.append((int) int164);
                                }
                            }
                        } else if (Log.isLoggable(TAG, 3)) {
                            sb = new StringBuilder();
                            str = "Got invalid format code = ";
                            sb.append(str);
                            sb.append((int) int164);
                        }
                    }
                }
                return -1;
            } else if (Log.isLoggable(TAG, 3)) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Unknown endianness = ");
                sb5.append((int) int162);
            }
        }
        byteOrder = ByteOrder.BIG_ENDIAN;
        randomAccessReader.order(byteOrder);
        int int323 = randomAccessReader.getInt32(10) + 6;
        int16 = randomAccessReader.getInt16(int323);
        while (i2 < int16) {
        }
        return -1;
    }

    private int parseExifSegment(byte[] bArr, int i2) {
        int read = this.reader.read(bArr, i2);
        if (read == i2) {
            if (hasJpegExifPreamble(bArr, i2)) {
                return parseExifSegment(new RandomAccessReader(bArr, i2));
            }
            Log.isLoggable(TAG, 3);
            return -1;
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unable to read exif segment data, length: ");
            sb.append(i2);
            sb.append(", actually read: ");
            sb.append(read);
        }
        return -1;
    }

    public int getOrientation() {
        int uInt16 = this.reader.getUInt16();
        if (handles(uInt16)) {
            int moveToExifSegmentAndGetLength = moveToExifSegmentAndGetLength();
            if (moveToExifSegmentAndGetLength == -1) {
                Log.isLoggable(TAG, 3);
                return -1;
            }
            return parseExifSegment(new byte[moveToExifSegmentAndGetLength], moveToExifSegmentAndGetLength);
        }
        if (Log.isLoggable(TAG, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Parser doesn't handle magic number: ");
            sb.append(uInt16);
        }
        return -1;
    }
}
