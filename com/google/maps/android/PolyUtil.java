package com.google.maps.android;

import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/* loaded from: classes2.dex */
public class PolyUtil {
    public static final double DEFAULT_TOLERANCE = 0.1d;

    private PolyUtil() {
    }

    public static boolean containsLocation(double d2, double d3, List<LatLng> list, boolean z) {
        int size = list.size();
        if (size == 0) {
            return false;
        }
        double radians = Math.toRadians(d2);
        double radians2 = Math.toRadians(d3);
        LatLng latLng = list.get(size - 1);
        double radians3 = Math.toRadians(latLng.latitude);
        double radians4 = Math.toRadians(latLng.longitude);
        int i2 = 0;
        double d4 = radians3;
        for (LatLng latLng2 : list) {
            double k2 = MathUtil.k(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
            if (radians == d4 && k2 == 0.0d) {
                return true;
            }
            double radians5 = Math.toRadians(latLng2.latitude);
            double radians6 = Math.toRadians(latLng2.longitude);
            if (intersects(d4, radians5, MathUtil.k(radians6 - radians4, -3.141592653589793d, 3.141592653589793d), radians, k2, z)) {
                i2++;
            }
            d4 = radians5;
            radians4 = radians6;
        }
        return (i2 & 1) != 0;
    }

    public static boolean containsLocation(LatLng latLng, List<LatLng> list, boolean z) {
        return containsLocation(latLng.latitude, latLng.longitude, list, z);
    }

    public static List<LatLng> decode(String str) {
        int i2;
        int i3;
        int length = str.length();
        ArrayList arrayList = new ArrayList();
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

    public static double distanceToLine(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng2.equals(latLng3)) {
            return SphericalUtil.computeDistanceBetween(latLng3, latLng);
        }
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        double radians5 = Math.toRadians(latLng3.latitude) - radians3;
        double radians6 = Math.toRadians(latLng3.longitude) - radians4;
        double d2 = (((radians - radians3) * radians5) + ((radians2 - radians4) * radians6)) / ((radians5 * radians5) + (radians6 * radians6));
        if (d2 <= 0.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng2);
        }
        if (d2 >= 1.0d) {
            return SphericalUtil.computeDistanceBetween(latLng, latLng3);
        }
        double d3 = latLng2.latitude;
        double d4 = d3 + ((latLng3.latitude - d3) * d2);
        double d5 = latLng2.longitude;
        return SphericalUtil.computeDistanceBetween(latLng, new LatLng(d4, d5 + (d2 * (latLng3.longitude - d5))));
    }

    public static String encode(List<LatLng> list) {
        StringBuffer stringBuffer = new StringBuffer();
        long j2 = 0;
        long j3 = 0;
        for (LatLng latLng : list) {
            long round = Math.round(latLng.latitude * 100000.0d);
            long round2 = Math.round(latLng.longitude * 100000.0d);
            encode(round - j2, stringBuffer);
            encode(round2 - j3, stringBuffer);
            j2 = round;
            j3 = round2;
        }
        return stringBuffer.toString();
    }

    private static void encode(long j2, StringBuffer stringBuffer) {
        int i2 = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
        long j3 = j2 << 1;
        if (i2 < 0) {
            j3 = ~j3;
        }
        while (j3 >= 32) {
            stringBuffer.append(Character.toChars((int) ((32 | (31 & j3)) + 63)));
            j3 >>= 5;
        }
        stringBuffer.append(Character.toChars((int) (j3 + 63)));
    }

    private static boolean intersects(double d2, double d3, double d4, double d5, double d6, boolean z) {
        if ((d6 < 0.0d || d6 < d4) && ((d6 >= 0.0d || d6 >= d4) && d5 > -1.5707963267948966d && d2 > -1.5707963267948966d && d3 > -1.5707963267948966d && d2 < 1.5707963267948966d && d3 < 1.5707963267948966d && d4 > -3.141592653589793d)) {
            double d7 = (((d4 - d6) * d2) + (d3 * d6)) / d4;
            if (d2 < 0.0d || d3 < 0.0d || d5 >= d7) {
                if ((d2 > 0.0d || d3 > 0.0d || d5 < d7) && d5 < 1.5707963267948966d) {
                    if (z) {
                        if (Math.tan(d5) < tanLatGC(d2, d3, d4, d6)) {
                            return false;
                        }
                    } else if (MathUtil.g(d5) < mercatorLatRhumb(d2, d3, d4, d6)) {
                        return false;
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public static boolean isClosedPolygon(List<LatLng> list) {
        return list.get(0).equals(list.get(list.size() - 1));
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnEdge(latLng, list, z, 0.1d);
    }

    public static boolean isLocationOnEdge(LatLng latLng, List<LatLng> list, boolean z, double d2) {
        return isLocationOnEdgeOrPath(latLng, list, true, z, d2);
    }

    private static boolean isLocationOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z, boolean z2, double d2) {
        return locationIndexOnEdgeOrPath(latLng, list, z, z2, d2) >= 0;
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z) {
        return isLocationOnPath(latLng, list, z, 0.1d);
    }

    public static boolean isLocationOnPath(LatLng latLng, List<LatLng> list, boolean z, double d2) {
        return isLocationOnEdgeOrPath(latLng, list, false, z, d2);
    }

    private static boolean isOnSegmentGC(double d2, double d3, double d4, double d5, double d6, double d7, double d8) {
        double d9 = MathUtil.d(d2, d6, d3 - d7);
        if (d9 <= d8) {
            return true;
        }
        double d10 = MathUtil.d(d4, d6, d5 - d7);
        if (d10 <= d8) {
            return true;
        }
        double e2 = MathUtil.e(MathUtil.i(d9) * sinDeltaBearing(d2, d3, d4, d5, d6, d7));
        if (e2 > d8) {
            return false;
        }
        double d11 = MathUtil.d(d2, d4, d3 - d5);
        double d12 = ((1.0d - (d11 * 2.0d)) * e2) + d11;
        if (d9 > d12 || d10 > d12) {
            return false;
        }
        if (d11 < 0.74d) {
            return true;
        }
        double d13 = 1.0d - (2.0d * e2);
        return MathUtil.j((d9 - e2) / d13, (d10 - e2) / d13) > 0.0d;
    }

    public static int locationIndexOnEdgeOrPath(LatLng latLng, List<LatLng> list, boolean z, boolean z2, double d2) {
        List<LatLng> list2;
        int i2;
        char c2;
        int size = list.size();
        if (size == 0) {
            return -1;
        }
        double d3 = d2 / 6371009.0d;
        double c3 = MathUtil.c(d3);
        double radians = Math.toRadians(latLng.latitude);
        double radians2 = Math.toRadians(latLng.longitude);
        if (z) {
            i2 = size - 1;
            list2 = list;
        } else {
            list2 = list;
            i2 = 0;
        }
        LatLng latLng2 = list2.get(i2);
        double radians3 = Math.toRadians(latLng2.latitude);
        double radians4 = Math.toRadians(latLng2.longitude);
        if (z2) {
            int i3 = 0;
            double d4 = radians3;
            double d5 = radians4;
            for (LatLng latLng3 : list) {
                double radians5 = Math.toRadians(latLng3.latitude);
                double radians6 = Math.toRadians(latLng3.longitude);
                if (isOnSegmentGC(d4, d5, radians5, radians6, radians, radians2, c3)) {
                    return Math.max(0, i3 - 1);
                }
                i3++;
                d4 = radians5;
                d5 = radians6;
            }
            return -1;
        }
        double d6 = radians - d3;
        double d7 = radians + d3;
        double g2 = MathUtil.g(radians3);
        double g3 = MathUtil.g(radians);
        double[] dArr = new double[3];
        int i4 = 0;
        for (LatLng latLng4 : list) {
            double d8 = g3;
            double radians7 = Math.toRadians(latLng4.latitude);
            double g4 = MathUtil.g(radians7);
            double radians8 = Math.toRadians(latLng4.longitude);
            if (Math.max(radians3, radians7) < d6 || Math.min(radians3, radians7) > d7) {
                c2 = 3;
            } else {
                double k2 = MathUtil.k(radians8 - radians4, -3.141592653589793d, 3.141592653589793d);
                double k3 = MathUtil.k(radians2 - radians4, -3.141592653589793d, 3.141592653589793d);
                dArr[0] = k3;
                dArr[1] = k3 + 6.283185307179586d;
                dArr[2] = k3 - 6.283185307179586d;
                c2 = 3;
                for (int i5 = 0; i5 < 3; i5++) {
                    double d9 = dArr[i5];
                    double d10 = g4 - g2;
                    double d11 = (k2 * k2) + (d10 * d10);
                    double b2 = d11 > 0.0d ? MathUtil.b(((d9 * k2) + ((d8 - g2) * d10)) / d11, 0.0d, 1.0d) : 0.0d;
                    if (MathUtil.d(radians, MathUtil.f(g2 + (b2 * d10)), d9 - (b2 * k2)) < c3) {
                        return Math.max(0, i4 - 1);
                    }
                }
                continue;
            }
            i4++;
            radians3 = radians7;
            g3 = d8;
            g2 = g4;
            radians4 = radians8;
        }
        return -1;
    }

    public static int locationIndexOnPath(LatLng latLng, List<LatLng> list, boolean z) {
        return locationIndexOnPath(latLng, list, z, 0.1d);
    }

    public static int locationIndexOnPath(LatLng latLng, List<LatLng> list, boolean z, double d2) {
        return locationIndexOnEdgeOrPath(latLng, list, false, z, d2);
    }

    private static double mercatorLatRhumb(double d2, double d3, double d4, double d5) {
        return ((MathUtil.g(d2) * (d4 - d5)) + (MathUtil.g(d3) * d5)) / d4;
    }

    public static List<LatLng> simplify(List<LatLng> list, double d2) {
        int size = list.size();
        if (size >= 1) {
            double d3 = 0.0d;
            if (d2 > 0.0d) {
                boolean isClosedPolygon = isClosedPolygon(list);
                LatLng latLng = null;
                if (isClosedPolygon) {
                    latLng = list.get(list.size() - 1);
                    list.remove(list.size() - 1);
                    list.add(new LatLng(latLng.latitude + 1.0E-11d, latLng.longitude + 1.0E-11d));
                }
                Stack stack = new Stack();
                double[] dArr = new double[size];
                int i2 = 0;
                dArr[0] = 1.0d;
                int i3 = size - 1;
                dArr[i3] = 1.0d;
                if (size > 2) {
                    stack.push(new int[]{0, i3});
                    int i4 = 0;
                    while (stack.size() > 0) {
                        int[] iArr = (int[]) stack.pop();
                        double d4 = d3;
                        for (int i5 = iArr[0] + 1; i5 < iArr[1]; i5++) {
                            double distanceToLine = distanceToLine(list.get(i5), list.get(iArr[0]), list.get(iArr[1]));
                            if (distanceToLine > d4) {
                                d4 = distanceToLine;
                                i4 = i5;
                            }
                        }
                        if (d4 > d2) {
                            dArr[i4] = d4;
                            stack.push(new int[]{iArr[0], i4});
                            stack.push(new int[]{i4, iArr[1]});
                        }
                        d3 = 0.0d;
                    }
                }
                if (isClosedPolygon) {
                    list.remove(list.size() - 1);
                    list.add(latLng);
                }
                ArrayList arrayList = new ArrayList();
                for (LatLng latLng2 : list) {
                    if (dArr[i2] != 0.0d) {
                        arrayList.add(latLng2);
                    }
                    i2++;
                }
                return arrayList;
            }
            throw new IllegalArgumentException("Tolerance must be greater than zero");
        }
        throw new IllegalArgumentException("Polyline must have at least 1 point");
    }

    private static double sinDeltaBearing(double d2, double d3, double d4, double d5, double d6, double d7) {
        double sin = Math.sin(d2);
        double cos = Math.cos(d4);
        double cos2 = Math.cos(d6);
        double d8 = d7 - d3;
        double d9 = d5 - d3;
        double sin2 = Math.sin(d8) * cos2;
        double sin3 = Math.sin(d9) * cos;
        double d10 = sin * 2.0d;
        double sin4 = Math.sin(d6 - d2) + (cos2 * d10 * MathUtil.c(d8));
        double sin5 = Math.sin(d4 - d2) + (d10 * cos * MathUtil.c(d9));
        double d11 = ((sin2 * sin2) + (sin4 * sin4)) * ((sin3 * sin3) + (sin5 * sin5));
        if (d11 <= 0.0d) {
            return 1.0d;
        }
        return ((sin2 * sin5) - (sin4 * sin3)) / Math.sqrt(d11);
    }

    private static double tanLatGC(double d2, double d3, double d4, double d5) {
        return ((Math.tan(d2) * Math.sin(d4 - d5)) + (Math.tan(d3) * Math.sin(d5))) / Math.sin(d4);
    }
}
