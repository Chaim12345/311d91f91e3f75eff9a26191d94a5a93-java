package androidx.camera.core.impl.utils;

import android.os.Build;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureMetaData;
import androidx.core.util.Preconditions;
import androidx.exifinterface.media.ExifInterface;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.ws.WebSocketProtocol;
import org.apache.commons.cli.HelpFormatter;
/* loaded from: classes.dex */
public class ExifData {
    private static final boolean DEBUG = false;
    private static final ExifTag[] IFD_EXIF_TAGS;
    private static final ExifTag[] IFD_GPS_TAGS;
    private static final ExifTag[] IFD_INTEROPERABILITY_TAGS;
    private static final ExifTag[] IFD_TIFF_TAGS;
    private static final int MM_IN_MICRONS = 1000;
    private static final String TAG = "ExifData";

    /* renamed from: a  reason: collision with root package name */
    static final ExifTag[] f1186a;

    /* renamed from: b  reason: collision with root package name */
    static final ExifTag[][] f1187b;

    /* renamed from: c  reason: collision with root package name */
    static final HashSet f1188c;
    private final List<Map<String, ExifAttribute>> mAttributes;
    private final ByteOrder mByteOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: androidx.camera.core.impl.utils.ExifData$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f1189a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f1190b;

        static {
            int[] iArr = new int[WhiteBalanceMode.values().length];
            f1190b = iArr;
            try {
                iArr[WhiteBalanceMode.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1190b[WhiteBalanceMode.MANUAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[CameraCaptureMetaData.FlashState.values().length];
            f1189a = iArr2;
            try {
                iArr2[CameraCaptureMetaData.FlashState.READY.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1189a[CameraCaptureMetaData.FlashState.NONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1189a[CameraCaptureMetaData.FlashState.FIRED.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private static final int DATETIME_VALUE_STRING_LENGTH = 19;

        /* renamed from: a  reason: collision with root package name */
        final List f1192a = Collections.list(new Enumeration<Map<String, ExifAttribute>>(this) { // from class: androidx.camera.core.impl.utils.ExifData.Builder.2

            /* renamed from: a  reason: collision with root package name */
            int f1194a = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f1194a < ExifData.f1187b.length;
            }

            @Override // java.util.Enumeration
            public Map<String, ExifAttribute> nextElement() {
                this.f1194a++;
                return new HashMap();
            }
        });
        private final ByteOrder mByteOrder;
        private static final Pattern GPS_TIMESTAMP_PATTERN = Pattern.compile("^(\\d{2}):(\\d{2}):(\\d{2})$");
        private static final Pattern DATETIME_PRIMARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4}):(\\d{2}):(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");
        private static final Pattern DATETIME_SECONDARY_FORMAT_PATTERN = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})\\s(\\d{2}):(\\d{2}):(\\d{2})$");

        /* renamed from: b  reason: collision with root package name */
        static final List f1191b = Collections.list(new Enumeration<HashMap<String, ExifTag>>() { // from class: androidx.camera.core.impl.utils.ExifData.Builder.1

            /* renamed from: a  reason: collision with root package name */
            int f1193a = 0;

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.f1193a < ExifData.f1187b.length;
            }

            @Override // java.util.Enumeration
            public HashMap<String, ExifTag> nextElement() {
                ExifTag[] exifTagArr;
                HashMap<String, ExifTag> hashMap = new HashMap<>();
                for (ExifTag exifTag : ExifData.f1187b[this.f1193a]) {
                    hashMap.put(exifTag.name, exifTag);
                }
                this.f1193a++;
                return hashMap;
            }
        });

        Builder(@NonNull ByteOrder byteOrder) {
            this.mByteOrder = byteOrder;
        }

        private static Pair<Integer, Integer> guessDataFormat(String str) {
            if (str.contains(",")) {
                String[] split = str.split(",", -1);
                Pair<Integer, Integer> guessDataFormat = guessDataFormat(split[0]);
                if (((Integer) guessDataFormat.first).intValue() == 2) {
                    return guessDataFormat;
                }
                for (int i2 = 1; i2 < split.length; i2++) {
                    Pair<Integer, Integer> guessDataFormat2 = guessDataFormat(split[i2]);
                    int intValue = (((Integer) guessDataFormat2.first).equals(guessDataFormat.first) || ((Integer) guessDataFormat2.second).equals(guessDataFormat.first)) ? ((Integer) guessDataFormat.first).intValue() : -1;
                    int intValue2 = (((Integer) guessDataFormat.second).intValue() == -1 || !(((Integer) guessDataFormat2.first).equals(guessDataFormat.second) || ((Integer) guessDataFormat2.second).equals(guessDataFormat.second))) ? -1 : ((Integer) guessDataFormat.second).intValue();
                    if (intValue == -1 && intValue2 == -1) {
                        return new Pair<>(2, -1);
                    }
                    if (intValue == -1) {
                        guessDataFormat = new Pair<>(Integer.valueOf(intValue2), -1);
                    } else if (intValue2 == -1) {
                        guessDataFormat = new Pair<>(Integer.valueOf(intValue), -1);
                    }
                }
                return guessDataFormat;
            } else if (!str.contains("/")) {
                try {
                    try {
                        long parseLong = Long.parseLong(str);
                        int i3 = (parseLong > 0L ? 1 : (parseLong == 0L ? 0 : -1));
                        return (i3 < 0 || parseLong > WebSocketProtocol.PAYLOAD_SHORT_MAX) ? i3 < 0 ? new Pair<>(9, -1) : new Pair<>(4, -1) : new Pair<>(3, 4);
                    } catch (NumberFormatException unused) {
                        return new Pair<>(2, -1);
                    }
                } catch (NumberFormatException unused2) {
                    Double.parseDouble(str);
                    return new Pair<>(12, -1);
                }
            } else {
                String[] split2 = str.split("/", -1);
                if (split2.length == 2) {
                    try {
                        long parseDouble = (long) Double.parseDouble(split2[0]);
                        long parseDouble2 = (long) Double.parseDouble(split2[1]);
                        if (parseDouble >= 0 && parseDouble2 >= 0) {
                            if (parseDouble <= 2147483647L && parseDouble2 <= 2147483647L) {
                                return new Pair<>(10, 5);
                            }
                            return new Pair<>(5, -1);
                        }
                        return new Pair<>(10, -1);
                    } catch (NumberFormatException unused3) {
                    }
                }
                return new Pair<>(2, -1);
            }
        }

        private void setAttributeIfMissing(@NonNull String str, @NonNull String str2, @NonNull List<Map<String, ExifAttribute>> list) {
            for (Map<String, ExifAttribute> map : list) {
                if (map.containsKey(str)) {
                    return;
                }
            }
            setAttributeInternal(str, str2, list);
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x018a, code lost:
            if (r7 != r0) goto L97;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private void setAttributeInternal(@NonNull String str, @Nullable String str2, @NonNull List<Map<String, ExifAttribute>> list) {
            int i2;
            int i3;
            int i4;
            Map<String, ExifAttribute> map;
            ExifAttribute createByte;
            Map<String, ExifAttribute> map2;
            ExifAttribute createSLong;
            Matcher matcher;
            Builder builder = this;
            String str3 = str;
            String str4 = str2;
            if ((ExifInterface.TAG_DATETIME.equals(str3) || ExifInterface.TAG_DATETIME_ORIGINAL.equals(str3) || ExifInterface.TAG_DATETIME_DIGITIZED.equals(str3)) && str4 != null) {
                boolean find = DATETIME_PRIMARY_FORMAT_PATTERN.matcher(str4).find();
                boolean find2 = DATETIME_SECONDARY_FORMAT_PATTERN.matcher(str4).find();
                if (str2.length() != 19 || (!find && !find2)) {
                    Logger.w(ExifData.TAG, "Invalid value for " + str3 + " : " + str4);
                    return;
                } else if (find2) {
                    str4 = str4.replaceAll(HelpFormatter.DEFAULT_OPT_PREFIX, ":");
                }
            }
            if (ExifInterface.TAG_ISO_SPEED_RATINGS.equals(str3)) {
                str3 = ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY;
            }
            String str5 = str3;
            int i5 = 2;
            int i6 = 1;
            if (str4 != null && ExifData.f1188c.contains(str5)) {
                if (str5.equals(ExifInterface.TAG_GPS_TIMESTAMP)) {
                    if (!GPS_TIMESTAMP_PATTERN.matcher(str4).find()) {
                        Logger.w(ExifData.TAG, "Invalid value for " + str5 + " : " + str4);
                        return;
                    }
                    str4 = Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(1))) + "/1," + Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(2))) + "/1," + Integer.parseInt((String) Preconditions.checkNotNull(matcher.group(3))) + "/1";
                } else {
                    try {
                        str4 = new LongRational(Double.parseDouble(str4)).toString();
                    } catch (NumberFormatException e2) {
                        Logger.w(ExifData.TAG, "Invalid value for " + str5 + " : " + str4, e2);
                        return;
                    }
                }
            }
            int i7 = 0;
            int i8 = 0;
            while (i8 < ExifData.f1187b.length) {
                ExifTag exifTag = (ExifTag) ((HashMap) f1191b.get(i8)).get(str5);
                if (exifTag != null) {
                    if (str4 == null) {
                        list.get(i8).remove(str5);
                    } else {
                        Pair<Integer, Integer> guessDataFormat = guessDataFormat(str4);
                        if (exifTag.primaryFormat == ((Integer) guessDataFormat.first).intValue() || exifTag.primaryFormat == ((Integer) guessDataFormat.second).intValue()) {
                            i4 = exifTag.primaryFormat;
                        } else {
                            int i9 = exifTag.secondaryFormat;
                            if (i9 == -1 || !(i9 == ((Integer) guessDataFormat.first).intValue() || exifTag.secondaryFormat == ((Integer) guessDataFormat.second).intValue())) {
                                i4 = exifTag.primaryFormat;
                                if (i4 != i6) {
                                    if (i4 != 7) {
                                    }
                                }
                            } else {
                                i4 = exifTag.secondaryFormat;
                            }
                        }
                        String str6 = "/";
                        switch (i4) {
                            case 1:
                                i2 = i8;
                                i3 = i6;
                                map = list.get(i2);
                                createByte = ExifAttribute.createByte(str4);
                                map.put(str5, createByte);
                                break;
                            case 2:
                            case 7:
                                i2 = i8;
                                i3 = i6;
                                map = list.get(i2);
                                createByte = ExifAttribute.createString(str4);
                                map.put(str5, createByte);
                                break;
                            case 3:
                                i2 = i8;
                                i3 = i6;
                                String[] split = str4.split(",", -1);
                                int[] iArr = new int[split.length];
                                for (int i10 = 0; i10 < split.length; i10++) {
                                    iArr[i10] = Integer.parseInt(split[i10]);
                                }
                                map = list.get(i2);
                                createByte = ExifAttribute.createUShort(iArr, builder.mByteOrder);
                                map.put(str5, createByte);
                                break;
                            case 4:
                                i2 = i8;
                                i3 = i6;
                                String[] split2 = str4.split(",", -1);
                                long[] jArr = new long[split2.length];
                                for (int i11 = 0; i11 < split2.length; i11++) {
                                    jArr[i11] = Long.parseLong(split2[i11]);
                                }
                                map = list.get(i2);
                                createByte = ExifAttribute.createULong(jArr, builder.mByteOrder);
                                map.put(str5, createByte);
                                break;
                            case 5:
                                String str7 = "/";
                                String[] split3 = str4.split(",", -1);
                                LongRational[] longRationalArr = new LongRational[split3.length];
                                int i12 = i7;
                                while (i12 < split3.length) {
                                    String str8 = str7;
                                    String[] split4 = split3[i12].split(str8, -1);
                                    longRationalArr[i12] = new LongRational((long) Double.parseDouble(split4[i7]), (long) Double.parseDouble(split4[1]));
                                    i12++;
                                    str7 = str8;
                                    i8 = i8;
                                    i7 = 0;
                                }
                                i2 = i8;
                                i3 = 1;
                                map = list.get(i2);
                                createByte = ExifAttribute.createURational(longRationalArr, builder.mByteOrder);
                                map.put(str5, createByte);
                                break;
                            case 9:
                                String[] split5 = str4.split(",", -1);
                                int[] iArr2 = new int[split5.length];
                                for (int i13 = i7; i13 < split5.length; i13++) {
                                    iArr2[i13] = Integer.parseInt(split5[i13]);
                                }
                                map2 = list.get(i8);
                                createSLong = ExifAttribute.createSLong(iArr2, builder.mByteOrder);
                                map2.put(str5, createSLong);
                                i2 = i8;
                                i3 = 1;
                                break;
                            case 10:
                                String[] split6 = str4.split(",", -1);
                                LongRational[] longRationalArr2 = new LongRational[split6.length];
                                int i14 = i7;
                                while (i14 < split6.length) {
                                    String[] split7 = split6[i14].split(str6, -1);
                                    longRationalArr2[i14] = new LongRational((long) Double.parseDouble(split7[i7]), (long) Double.parseDouble(split7[i6]));
                                    i14++;
                                    str6 = str6;
                                    i6 = 1;
                                }
                                map2 = list.get(i8);
                                builder = this;
                                createSLong = ExifAttribute.createSRational(longRationalArr2, builder.mByteOrder);
                                map2.put(str5, createSLong);
                                i2 = i8;
                                i3 = 1;
                                break;
                            case 12:
                                String[] split8 = str4.split(",", -1);
                                double[] dArr = new double[split8.length];
                                for (int i15 = i7; i15 < split8.length; i15++) {
                                    dArr[i15] = Double.parseDouble(split8[i15]);
                                }
                                list.get(i8).put(str5, ExifAttribute.createDouble(dArr, builder.mByteOrder));
                                break;
                        }
                        i8 = i2 + 1;
                        i6 = i3;
                        i5 = 2;
                        i7 = 0;
                    }
                }
                i2 = i8;
                i3 = i6;
                i8 = i2 + 1;
                i6 = i3;
                i5 = 2;
                i7 = 0;
            }
        }

        @NonNull
        public ExifData build() {
            ArrayList list = Collections.list(new Enumeration<Map<String, ExifAttribute>>() { // from class: androidx.camera.core.impl.utils.ExifData.Builder.3

                /* renamed from: a  reason: collision with root package name */
                final Enumeration f1195a;

                {
                    this.f1195a = Collections.enumeration(Builder.this.f1192a);
                }

                @Override // java.util.Enumeration
                public boolean hasMoreElements() {
                    return this.f1195a.hasMoreElements();
                }

                @Override // java.util.Enumeration
                public Map<String, ExifAttribute> nextElement() {
                    return new HashMap((Map) this.f1195a.nextElement());
                }
            });
            if (!list.get(1).isEmpty()) {
                setAttributeIfMissing(ExifInterface.TAG_EXPOSURE_PROGRAM, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_EXIF_VERSION, "0230", list);
                setAttributeIfMissing(ExifInterface.TAG_COMPONENTS_CONFIGURATION, "1,2,3,0", list);
                setAttributeIfMissing(ExifInterface.TAG_METERING_MODE, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_LIGHT_SOURCE, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_FLASHPIX_VERSION, "0100", list);
                setAttributeIfMissing(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, String.valueOf(2), list);
                setAttributeIfMissing(ExifInterface.TAG_FILE_SOURCE, String.valueOf(3), list);
                setAttributeIfMissing(ExifInterface.TAG_SCENE_TYPE, String.valueOf(1), list);
                setAttributeIfMissing(ExifInterface.TAG_CUSTOM_RENDERED, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_SCENE_CAPTURE_TYPE, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_CONTRAST, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_SATURATION, String.valueOf(0), list);
                setAttributeIfMissing(ExifInterface.TAG_SHARPNESS, String.valueOf(0), list);
            }
            if (!list.get(2).isEmpty()) {
                setAttributeIfMissing(ExifInterface.TAG_GPS_VERSION_ID, "2300", list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_SPEED_REF, "K", list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_TRACK_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_DEST_BEARING_REF, ExifInterface.GPS_DIRECTION_TRUE, list);
                setAttributeIfMissing(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, "K", list);
            }
            return new ExifData(this.mByteOrder, list);
        }

        @NonNull
        public Builder removeAttribute(@NonNull String str) {
            setAttributeInternal(str, null, this.f1192a);
            return this;
        }

        @NonNull
        public Builder setAttribute(@NonNull String str, @NonNull String str2) {
            setAttributeInternal(str, str2, this.f1192a);
            return this;
        }

        @NonNull
        public Builder setExposureTimeNanos(long j2) {
            return setAttribute(ExifInterface.TAG_EXPOSURE_TIME, String.valueOf(j2 / TimeUnit.SECONDS.toNanos(1L)));
        }

        @NonNull
        public Builder setFlashState(@NonNull CameraCaptureMetaData.FlashState flashState) {
            int i2;
            if (flashState == CameraCaptureMetaData.FlashState.UNKNOWN) {
                return this;
            }
            int i3 = AnonymousClass1.f1189a[flashState.ordinal()];
            if (i3 == 1) {
                i2 = 0;
            } else if (i3 == 2) {
                i2 = 32;
            } else if (i3 != 3) {
                Logger.w(ExifData.TAG, "Unknown flash state: " + flashState);
                return this;
            } else {
                i2 = 1;
            }
            if ((i2 & 1) == 1) {
                setAttribute(ExifInterface.TAG_LIGHT_SOURCE, String.valueOf(4));
            }
            return setAttribute(ExifInterface.TAG_FLASH, String.valueOf(i2));
        }

        @NonNull
        public Builder setFocalLength(float f2) {
            return setAttribute(ExifInterface.TAG_FOCAL_LENGTH, new LongRational(f2 * 1000.0f, 1000L).toString());
        }

        @NonNull
        public Builder setImageHeight(int i2) {
            return setAttribute(ExifInterface.TAG_IMAGE_LENGTH, String.valueOf(i2));
        }

        @NonNull
        public Builder setImageWidth(int i2) {
            return setAttribute(ExifInterface.TAG_IMAGE_WIDTH, String.valueOf(i2));
        }

        @NonNull
        public Builder setIso(int i2) {
            return setAttribute(ExifInterface.TAG_SENSITIVITY_TYPE, String.valueOf(3)).setAttribute(ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, String.valueOf(Math.min(65535, i2)));
        }

        @NonNull
        public Builder setLensFNumber(float f2) {
            return setAttribute(ExifInterface.TAG_F_NUMBER, String.valueOf(f2));
        }

        @NonNull
        public Builder setOrientationDegrees(int i2) {
            int i3;
            if (i2 == 0) {
                i3 = 1;
            } else if (i2 == 90) {
                i3 = 6;
            } else if (i2 == 180) {
                i3 = 3;
            } else if (i2 != 270) {
                Logger.w(ExifData.TAG, "Unexpected orientation value: " + i2 + ". Must be one of 0, 90, 180, 270.");
                i3 = 0;
            } else {
                i3 = 8;
            }
            return setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(i3));
        }

        @NonNull
        public Builder setWhiteBalanceMode(@NonNull WhiteBalanceMode whiteBalanceMode) {
            int i2 = AnonymousClass1.f1190b[whiteBalanceMode.ordinal()];
            return setAttribute(ExifInterface.TAG_WHITE_BALANCE, i2 != 1 ? i2 != 2 ? null : String.valueOf(1) : String.valueOf(0));
        }
    }

    /* loaded from: classes.dex */
    public enum WhiteBalanceMode {
        AUTO,
        MANUAL
    }

    static {
        ExifTag[] exifTagArr = {new ExifTag(ExifInterface.TAG_IMAGE_WIDTH, 256, 3, 4), new ExifTag(ExifInterface.TAG_IMAGE_LENGTH, 257, 3, 4), new ExifTag(ExifInterface.TAG_MAKE, 271, 2), new ExifTag(ExifInterface.TAG_MODEL, 272, 2), new ExifTag(ExifInterface.TAG_ORIENTATION, 274, 3), new ExifTag(ExifInterface.TAG_X_RESOLUTION, 282, 5), new ExifTag(ExifInterface.TAG_Y_RESOLUTION, 283, 5), new ExifTag(ExifInterface.TAG_RESOLUTION_UNIT, 296, 3), new ExifTag(ExifInterface.TAG_SOFTWARE, 305, 2), new ExifTag(ExifInterface.TAG_DATETIME, 306, 2), new ExifTag(ExifInterface.TAG_Y_CB_CR_POSITIONING, 531, 3), new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4)};
        IFD_TIFF_TAGS = exifTagArr;
        ExifTag[] exifTagArr2 = {new ExifTag(ExifInterface.TAG_EXPOSURE_TIME, 33434, 5), new ExifTag(ExifInterface.TAG_F_NUMBER, 33437, 5), new ExifTag(ExifInterface.TAG_EXPOSURE_PROGRAM, 34850, 3), new ExifTag(ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY, 34855, 3), new ExifTag(ExifInterface.TAG_SENSITIVITY_TYPE, 34864, 3), new ExifTag(ExifInterface.TAG_EXIF_VERSION, 36864, 2), new ExifTag(ExifInterface.TAG_DATETIME_ORIGINAL, 36867, 2), new ExifTag(ExifInterface.TAG_DATETIME_DIGITIZED, 36868, 2), new ExifTag(ExifInterface.TAG_COMPONENTS_CONFIGURATION, 37121, 7), new ExifTag(ExifInterface.TAG_SHUTTER_SPEED_VALUE, 37377, 10), new ExifTag(ExifInterface.TAG_APERTURE_VALUE, 37378, 5), new ExifTag(ExifInterface.TAG_BRIGHTNESS_VALUE, 37379, 10), new ExifTag(ExifInterface.TAG_EXPOSURE_BIAS_VALUE, 37380, 10), new ExifTag(ExifInterface.TAG_MAX_APERTURE_VALUE, 37381, 5), new ExifTag(ExifInterface.TAG_METERING_MODE, 37383, 3), new ExifTag(ExifInterface.TAG_LIGHT_SOURCE, 37384, 3), new ExifTag(ExifInterface.TAG_FLASH, 37385, 3), new ExifTag(ExifInterface.TAG_FOCAL_LENGTH, 37386, 5), new ExifTag(ExifInterface.TAG_SUBSEC_TIME, 37520, 2), new ExifTag(ExifInterface.TAG_SUBSEC_TIME_ORIGINAL, 37521, 2), new ExifTag(ExifInterface.TAG_SUBSEC_TIME_DIGITIZED, 37522, 2), new ExifTag(ExifInterface.TAG_FLASHPIX_VERSION, 40960, 7), new ExifTag(ExifInterface.TAG_COLOR_SPACE, 40961, 3), new ExifTag(ExifInterface.TAG_PIXEL_X_DIMENSION, 40962, 3, 4), new ExifTag(ExifInterface.TAG_PIXEL_Y_DIMENSION, 40963, 3, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4), new ExifTag(ExifInterface.TAG_FOCAL_PLANE_RESOLUTION_UNIT, 41488, 3), new ExifTag(ExifInterface.TAG_SENSING_METHOD, 41495, 3), new ExifTag(ExifInterface.TAG_FILE_SOURCE, 41728, 7), new ExifTag(ExifInterface.TAG_SCENE_TYPE, 41729, 7), new ExifTag(ExifInterface.TAG_CUSTOM_RENDERED, 41985, 3), new ExifTag(ExifInterface.TAG_EXPOSURE_MODE, 41986, 3), new ExifTag(ExifInterface.TAG_WHITE_BALANCE, 41987, 3), new ExifTag(ExifInterface.TAG_SCENE_CAPTURE_TYPE, 41990, 3), new ExifTag(ExifInterface.TAG_CONTRAST, 41992, 3), new ExifTag(ExifInterface.TAG_SATURATION, 41993, 3), new ExifTag(ExifInterface.TAG_SHARPNESS, 41994, 3)};
        IFD_EXIF_TAGS = exifTagArr2;
        ExifTag[] exifTagArr3 = {new ExifTag(ExifInterface.TAG_GPS_VERSION_ID, 0, 1), new ExifTag(ExifInterface.TAG_GPS_LATITUDE_REF, 1, 2), new ExifTag(ExifInterface.TAG_GPS_LATITUDE, 2, 5, 10), new ExifTag(ExifInterface.TAG_GPS_LONGITUDE_REF, 3, 2), new ExifTag(ExifInterface.TAG_GPS_LONGITUDE, 4, 5, 10), new ExifTag(ExifInterface.TAG_GPS_ALTITUDE_REF, 5, 1), new ExifTag(ExifInterface.TAG_GPS_ALTITUDE, 6, 5), new ExifTag(ExifInterface.TAG_GPS_TIMESTAMP, 7, 5), new ExifTag(ExifInterface.TAG_GPS_SPEED_REF, 12, 2), new ExifTag(ExifInterface.TAG_GPS_TRACK_REF, 14, 2), new ExifTag(ExifInterface.TAG_GPS_IMG_DIRECTION_REF, 16, 2), new ExifTag(ExifInterface.TAG_GPS_DEST_BEARING_REF, 23, 2), new ExifTag(ExifInterface.TAG_GPS_DEST_DISTANCE_REF, 25, 2)};
        IFD_GPS_TAGS = exifTagArr3;
        f1186a = new ExifTag[]{new ExifTag("SubIFDPointer", 330, 4), new ExifTag("ExifIFDPointer", 34665, 4), new ExifTag("GPSInfoIFDPointer", 34853, 4), new ExifTag("InteroperabilityIFDPointer", 40965, 4)};
        ExifTag[] exifTagArr4 = {new ExifTag(ExifInterface.TAG_INTEROPERABILITY_INDEX, 1, 2)};
        IFD_INTEROPERABILITY_TAGS = exifTagArr4;
        f1187b = new ExifTag[][]{exifTagArr, exifTagArr2, exifTagArr3, exifTagArr4};
        f1188c = new HashSet(Arrays.asList(ExifInterface.TAG_F_NUMBER, ExifInterface.TAG_EXPOSURE_TIME, ExifInterface.TAG_GPS_TIMESTAMP));
    }

    ExifData(ByteOrder byteOrder, List list) {
        Preconditions.checkState(list.size() == f1187b.length, "Malformed attributes list. Number of IFDs mismatch.");
        this.mByteOrder = byteOrder;
        this.mAttributes = list;
    }

    @NonNull
    public static Builder builderForDevice() {
        return new Builder(ByteOrder.BIG_ENDIAN).setAttribute(ExifInterface.TAG_ORIENTATION, String.valueOf(1)).setAttribute(ExifInterface.TAG_X_RESOLUTION, "72/1").setAttribute(ExifInterface.TAG_Y_RESOLUTION, "72/1").setAttribute(ExifInterface.TAG_RESOLUTION_UNIT, String.valueOf(2)).setAttribute(ExifInterface.TAG_Y_CB_CR_POSITIONING, String.valueOf(1)).setAttribute(ExifInterface.TAG_MAKE, Build.MANUFACTURER).setAttribute(ExifInterface.TAG_MODEL, Build.MODEL);
    }

    @Nullable
    private ExifAttribute getExifAttribute(@NonNull String str) {
        if (ExifInterface.TAG_ISO_SPEED_RATINGS.equals(str)) {
            str = ExifInterface.TAG_PHOTOGRAPHIC_SENSITIVITY;
        }
        for (int i2 = 0; i2 < f1187b.length; i2++) {
            ExifAttribute exifAttribute = this.mAttributes.get(i2).get(str);
            if (exifAttribute != null) {
                return exifAttribute;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public Map a(int i2) {
        int length = f1187b.length;
        Preconditions.checkArgumentInRange(i2, 0, length, "Invalid IFD index: " + i2 + ". Index should be between [0, EXIF_TAGS.length] ");
        return this.mAttributes.get(i2);
    }

    @Nullable
    public String getAttribute(@NonNull String str) {
        String str2;
        ExifAttribute exifAttribute = getExifAttribute(str);
        if (exifAttribute != null) {
            if (!f1188c.contains(str)) {
                return exifAttribute.getStringValue(this.mByteOrder);
            }
            if (str.equals(ExifInterface.TAG_GPS_TIMESTAMP)) {
                int i2 = exifAttribute.format;
                if (i2 == 5 || i2 == 10) {
                    LongRational[] longRationalArr = (LongRational[]) exifAttribute.a(this.mByteOrder);
                    if (longRationalArr != null && longRationalArr.length == 3) {
                        return String.format(Locale.US, "%02d:%02d:%02d", Integer.valueOf((int) (((float) longRationalArr[0].b()) / ((float) longRationalArr[0].a()))), Integer.valueOf((int) (((float) longRationalArr[1].b()) / ((float) longRationalArr[1].a()))), Integer.valueOf((int) (((float) longRationalArr[2].b()) / ((float) longRationalArr[2].a()))));
                    }
                    str2 = "Invalid GPS Timestamp array. array=" + Arrays.toString(longRationalArr);
                } else {
                    str2 = "GPS Timestamp format is not rational. format=" + exifAttribute.format;
                }
                Logger.w(TAG, str2);
                return null;
            }
            try {
                return Double.toString(exifAttribute.getDoubleValue(this.mByteOrder));
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    @NonNull
    public ByteOrder getByteOrder() {
        return this.mByteOrder;
    }
}
