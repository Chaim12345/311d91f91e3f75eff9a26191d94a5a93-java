package com.google.maps.android.heatmaps;

import android.graphics.Bitmap;
import android.graphics.Color;
import androidx.collection.LongSparseArray;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Tile;
import com.google.android.gms.maps.model.TileProvider;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class HeatmapTileProvider implements TileProvider {
    public static final Gradient DEFAULT_GRADIENT;
    private static final int[] DEFAULT_GRADIENT_COLORS;
    private static final float[] DEFAULT_GRADIENT_START_POINTS;
    private static final int DEFAULT_MAX_ZOOM = 11;
    private static final int DEFAULT_MIN_ZOOM = 5;
    public static final double DEFAULT_OPACITY = 0.7d;
    public static final int DEFAULT_RADIUS = 20;
    private static final int MAX_RADIUS = 50;
    private static final int MAX_ZOOM_LEVEL = 22;
    private static final int MIN_RADIUS = 10;
    private static final int SCREEN_SIZE = 1280;
    private static final int TILE_DIM = 512;
    private Bounds mBounds;
    private int[] mColorMap;
    private double mCustomMaxIntensity;
    private Collection<WeightedLatLng> mData;
    private Gradient mGradient;
    private double[] mKernel;
    private double[] mMaxIntensity;
    private double mOpacity;
    private int mRadius;
    private PointQuadTree<WeightedLatLng> mTree;

    /* loaded from: classes2.dex */
    public static class Builder {
        private Collection<WeightedLatLng> data;
        private int radius = 20;
        private Gradient gradient = HeatmapTileProvider.DEFAULT_GRADIENT;
        private double opacity = 0.7d;
        private double intensity = 0.0d;

        public HeatmapTileProvider build() {
            if (this.data != null) {
                return new HeatmapTileProvider(this);
            }
            throw new IllegalStateException("No input data: you must use either .data or .weightedData before building");
        }

        public Builder data(Collection<LatLng> collection) {
            return weightedData(HeatmapTileProvider.wrapData(collection));
        }

        public Builder gradient(Gradient gradient) {
            this.gradient = gradient;
            return this;
        }

        public Builder maxIntensity(double d2) {
            this.intensity = d2;
            return this;
        }

        public Builder opacity(double d2) {
            this.opacity = d2;
            if (d2 < 0.0d || d2 > 1.0d) {
                throw new IllegalArgumentException("Opacity must be in range [0, 1]");
            }
            return this;
        }

        public Builder radius(int i2) {
            this.radius = i2;
            if (i2 < 10 || i2 > 50) {
                throw new IllegalArgumentException("Radius not within bounds.");
            }
            return this;
        }

        public Builder weightedData(Collection<WeightedLatLng> collection) {
            this.data = collection;
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("No input points.");
            }
            return this;
        }
    }

    static {
        int[] iArr = {Color.rgb(102, 225, 0), Color.rgb(255, 0, 0)};
        DEFAULT_GRADIENT_COLORS = iArr;
        float[] fArr = {0.2f, 1.0f};
        DEFAULT_GRADIENT_START_POINTS = fArr;
        DEFAULT_GRADIENT = new Gradient(iArr, fArr);
    }

    private HeatmapTileProvider(Builder builder) {
        this.mData = builder.data;
        this.mRadius = builder.radius;
        this.mGradient = builder.gradient;
        this.mOpacity = builder.opacity;
        this.mCustomMaxIntensity = builder.intensity;
        int i2 = this.mRadius;
        this.mKernel = d(i2, i2 / 3.0d);
        setGradient(this.mGradient);
        setWeightedData(this.mData);
    }

    static Bitmap b(double[][] dArr, int[] iArr, double d2) {
        int i2 = iArr[iArr.length - 1];
        double length = (iArr.length - 1) / d2;
        int length2 = dArr.length;
        int[] iArr2 = new int[length2 * length2];
        for (int i3 = 0; i3 < length2; i3++) {
            for (int i4 = 0; i4 < length2; i4++) {
                double d3 = dArr[i4][i3];
                int i5 = (i3 * length2) + i4;
                int i6 = (int) (d3 * length);
                if (d3 == 0.0d) {
                    iArr2[i5] = 0;
                } else if (i6 < iArr.length) {
                    iArr2[i5] = iArr[i6];
                } else {
                    iArr2[i5] = i2;
                }
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(length2, length2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr2, 0, length2, 0, 0, length2, length2);
        return createBitmap;
    }

    static double[][] c(double[][] dArr, double[] dArr2) {
        int floor = (int) Math.floor(dArr2.length / 2.0d);
        int length = dArr.length;
        int i2 = length - (floor * 2);
        int i3 = 1;
        int i4 = (floor + i2) - 1;
        double[][] dArr3 = (double[][]) Array.newInstance(double.class, length, length);
        int i5 = 0;
        while (true) {
            double d2 = 0.0d;
            if (i5 >= length) {
                break;
            }
            int i6 = 0;
            while (i6 < length) {
                double d3 = dArr[i5][i6];
                if (d3 != d2) {
                    int i7 = i5 + floor;
                    if (i4 < i7) {
                        i7 = i4;
                    }
                    int i8 = i7 + 1;
                    int i9 = i5 - floor;
                    for (int i10 = floor > i9 ? floor : i9; i10 < i8; i10++) {
                        double[] dArr4 = dArr3[i10];
                        dArr4[i6] = dArr4[i6] + (dArr2[i10 - i9] * d3);
                    }
                }
                i6++;
                d2 = 0.0d;
            }
            i5++;
        }
        double[][] dArr5 = (double[][]) Array.newInstance(double.class, i2, i2);
        int i11 = floor;
        while (i11 < i4 + 1) {
            int i12 = 0;
            while (i12 < length) {
                double d4 = dArr3[i11][i12];
                if (d4 != 0.0d) {
                    int i13 = i12 + floor;
                    if (i4 < i13) {
                        i13 = i4;
                    }
                    int i14 = i13 + i3;
                    int i15 = i12 - floor;
                    for (int i16 = floor > i15 ? floor : i15; i16 < i14; i16++) {
                        double[] dArr6 = dArr5[i11 - floor];
                        int i17 = i16 - floor;
                        dArr6[i17] = dArr6[i17] + (dArr2[i16 - i15] * d4);
                    }
                }
                i12++;
                i3 = 1;
            }
            i11++;
            i3 = 1;
        }
        return dArr5;
    }

    private static Tile convertBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return new Tile(512, 512, byteArrayOutputStream.toByteArray());
    }

    static double[] d(int i2, double d2) {
        double[] dArr = new double[(i2 * 2) + 1];
        for (int i3 = -i2; i3 <= i2; i3++) {
            dArr[i3 + i2] = Math.exp(((-i3) * i3) / ((2.0d * d2) * d2));
        }
        return dArr;
    }

    static Bounds e(Collection collection) {
        Iterator it = collection.iterator();
        WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
        double d2 = weightedLatLng.getPoint().x;
        double d3 = weightedLatLng.getPoint().x;
        double d4 = d2;
        double d5 = d3;
        double d6 = weightedLatLng.getPoint().y;
        double d7 = weightedLatLng.getPoint().y;
        while (it.hasNext()) {
            WeightedLatLng weightedLatLng2 = (WeightedLatLng) it.next();
            double d8 = weightedLatLng2.getPoint().x;
            double d9 = weightedLatLng2.getPoint().y;
            if (d8 < d4) {
                d4 = d8;
            }
            if (d8 > d5) {
                d5 = d8;
            }
            if (d9 < d6) {
                d6 = d9;
            }
            if (d9 > d7) {
                d7 = d9;
            }
        }
        return new Bounds(d4, d5, d6, d7);
    }

    static double f(Collection collection, Bounds bounds, int i2, int i3) {
        double d2 = bounds.minX;
        double d3 = bounds.maxX;
        double d4 = bounds.minY;
        double d5 = d3 - d2;
        double d6 = bounds.maxY - d4;
        if (d5 <= d6) {
            d5 = d6;
        }
        double d7 = ((int) ((i3 / (i2 * 2)) + 0.5d)) / d5;
        LongSparseArray longSparseArray = new LongSparseArray();
        Iterator it = collection.iterator();
        double d8 = 0.0d;
        while (it.hasNext()) {
            WeightedLatLng weightedLatLng = (WeightedLatLng) it.next();
            double d9 = weightedLatLng.getPoint().x;
            int i4 = (int) ((weightedLatLng.getPoint().y - d4) * d7);
            long j2 = (int) ((d9 - d2) * d7);
            LongSparseArray longSparseArray2 = (LongSparseArray) longSparseArray.get(j2);
            if (longSparseArray2 == null) {
                longSparseArray2 = new LongSparseArray();
                longSparseArray.put(j2, longSparseArray2);
            }
            long j3 = i4;
            Double d10 = (Double) longSparseArray2.get(j3);
            if (d10 == null) {
                d10 = Double.valueOf(0.0d);
            }
            Double valueOf = Double.valueOf(d10.doubleValue() + weightedLatLng.getIntensity());
            longSparseArray2.put(j3, valueOf);
            if (valueOf.doubleValue() > d8) {
                d8 = valueOf.doubleValue();
            }
        }
        return d8;
    }

    private double[] getMaxIntensities(int i2) {
        int i3;
        double[] dArr = new double[22];
        if (this.mCustomMaxIntensity != 0.0d) {
            for (int i4 = 0; i4 < 22; i4++) {
                dArr[i4] = this.mCustomMaxIntensity;
            }
            return dArr;
        }
        int i5 = 5;
        while (true) {
            if (i5 >= 11) {
                break;
            }
            dArr[i5] = f(this.mData, this.mBounds, i2, (int) (Math.pow(2.0d, i5 - 3) * 1280.0d));
            if (i5 == 5) {
                for (int i6 = 0; i6 < i5; i6++) {
                    dArr[i6] = dArr[i5];
                }
            }
            i5++;
        }
        for (i3 = 11; i3 < 22; i3++) {
            dArr[i3] = dArr[10];
        }
        return dArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Collection<WeightedLatLng> wrapData(Collection<LatLng> collection) {
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : collection) {
            arrayList.add(new WeightedLatLng(latLng));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00a1  */
    @Override // com.google.android.gms.maps.model.TileProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Tile getTile(int i2, int i3, int i4) {
        int i5;
        double d2;
        double d3;
        Bounds bounds;
        Bounds bounds2;
        Bounds bounds3;
        double pow = 1.0d / Math.pow(2.0d, i4);
        double d4 = (this.mRadius * pow) / 512.0d;
        double d5 = ((2.0d * d4) + pow) / ((i5 * 2) + 512);
        double d6 = (i2 * pow) - d4;
        double d7 = ((i2 + 1) * pow) + d4;
        double d8 = (i3 * pow) - d4;
        double d9 = ((i3 + 1) * pow) + d4;
        Collection<WeightedLatLng> arrayList = new ArrayList<>();
        if (d6 < 0.0d) {
            bounds = new Bounds(d6 + 1.0d, 1.0d, d8, d9);
            d2 = -1.0d;
        } else {
            d2 = 1.0d;
            if (d7 <= 1.0d) {
                d3 = 0.0d;
                bounds2 = new Bounds(d6, d7, d8, d9);
                bounds3 = this.mBounds;
                if (bounds2.intersects(new Bounds(bounds3.minX - d4, bounds3.maxX + d4, bounds3.minY - d4, bounds3.maxY + d4))) {
                    return TileProvider.NO_TILE;
                }
                Collection<WeightedLatLng> search = this.mTree.search(bounds2);
                if (search.isEmpty()) {
                    return TileProvider.NO_TILE;
                }
                int i6 = this.mRadius;
                double[][] dArr = (double[][]) Array.newInstance(double.class, (i6 * 2) + 512, (i6 * 2) + 512);
                for (WeightedLatLng weightedLatLng : search) {
                    Point point = weightedLatLng.getPoint();
                    int i7 = (int) ((point.y - d8) / d5);
                    double[] dArr2 = dArr[(int) ((point.x - d6) / d5)];
                    dArr2[i7] = dArr2[i7] + weightedLatLng.getIntensity();
                }
                for (WeightedLatLng weightedLatLng2 : arrayList) {
                    Point point2 = weightedLatLng2.getPoint();
                    int i8 = (int) ((point2.y - d8) / d5);
                    double[] dArr3 = dArr[(int) (((point2.x + d3) - d6) / d5)];
                    dArr3[i8] = dArr3[i8] + weightedLatLng2.getIntensity();
                }
                return convertBitmap(b(c(dArr, this.mKernel), this.mColorMap, this.mMaxIntensity[i4]));
            }
            bounds = new Bounds(0.0d, d7 - 1.0d, d8, d9);
        }
        arrayList = this.mTree.search(bounds);
        d3 = d2;
        bounds2 = new Bounds(d6, d7, d8, d9);
        bounds3 = this.mBounds;
        if (bounds2.intersects(new Bounds(bounds3.minX - d4, bounds3.maxX + d4, bounds3.minY - d4, bounds3.maxY + d4))) {
        }
    }

    public void setData(Collection<LatLng> collection) {
        setWeightedData(wrapData(collection));
    }

    public void setGradient(Gradient gradient) {
        this.mGradient = gradient;
        this.mColorMap = gradient.a(this.mOpacity);
    }

    public void setMaxIntensity(double d2) {
        this.mCustomMaxIntensity = d2;
        setWeightedData(this.mData);
    }

    public void setOpacity(double d2) {
        this.mOpacity = d2;
        setGradient(this.mGradient);
    }

    public void setRadius(int i2) {
        this.mRadius = i2;
        this.mKernel = d(i2, i2 / 3.0d);
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }

    public void setWeightedData(Collection<WeightedLatLng> collection) {
        this.mData = collection;
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("No input points.");
        }
        Bounds e2 = e(this.mData);
        this.mBounds = e2;
        this.mTree = new PointQuadTree<>(e2);
        for (WeightedLatLng weightedLatLng : this.mData) {
            this.mTree.add(weightedLatLng);
        }
        this.mMaxIntensity = getMaxIntensities(this.mRadius);
    }
}
