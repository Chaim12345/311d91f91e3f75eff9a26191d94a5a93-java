package com.google.maps.internal;

import com.google.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes2.dex */
public class PolylineEncoding {
    public static List<LatLng> decode(String str) {
        int i2;
        int i3;
        int length = str.length();
        ArrayList arrayList = new ArrayList(length / 2);
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            int i7 = 0;
            int i8 = 1;
            while (true) {
                i2 = i4 + 1;
                int charAt = (str.charAt(i4) - '?') - 1;
                i8 += charAt << i7;
                i7 += 5;
                if (charAt < 31) {
                    break;
                }
                i4 = i2;
            }
            int i9 = ((i8 & 1) != 0 ? ~(i8 >> 1) : i8 >> 1) + i5;
            int i10 = 0;
            int i11 = 1;
            while (true) {
                i3 = i2 + 1;
                int charAt2 = (str.charAt(i2) - '?') - 1;
                i11 += charAt2 << i10;
                i10 += 5;
                if (charAt2 < 31) {
                    break;
                }
                i2 = i3;
            }
            int i12 = i11 & 1;
            int i13 = i11 >> 1;
            if (i12 != 0) {
                i13 = ~i13;
            }
            i6 += i13;
            arrayList.add(new LatLng(i9 * 1.0E-5d, i6 * 1.0E-5d));
            i5 = i9;
            i4 = i3;
        }
        return arrayList;
    }

    public static String encode(List<LatLng> list) {
        StringBuilder sb = new StringBuilder();
        long j2 = 0;
        long j3 = 0;
        for (LatLng latLng : list) {
            long round = Math.round(latLng.lat * 100000.0d);
            long round2 = Math.round(latLng.lng * 100000.0d);
            encode(round - j2, sb);
            encode(round2 - j3, sb);
            j2 = round;
            j3 = round2;
        }
        return sb.toString();
    }

    public static String encode(LatLng[] latLngArr) {
        return encode(Arrays.asList(latLngArr));
    }

    private static void encode(long j2, StringBuilder sb) {
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        long j3 = j2 << 1;
        if (i2 < 0) {
            j3 = ~j3;
        }
        while (j3 >= 32) {
            sb.append(Character.toChars((int) ((32 | (31 & j3)) + 63)));
            j3 >>= 5;
        }
        sb.append(Character.toChars((int) (j3 + 63)));
    }
}
