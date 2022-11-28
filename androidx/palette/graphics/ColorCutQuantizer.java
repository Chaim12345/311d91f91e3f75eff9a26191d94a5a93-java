package androidx.palette.graphics;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import androidx.palette.graphics.Palette;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ColorCutQuantizer {
    private static final String LOG_TAG = "ColorCutQuantizer";
    private static final boolean LOG_TIMINGS = false;
    private static final int QUANTIZE_WORD_MASK = 31;
    private static final int QUANTIZE_WORD_WIDTH = 5;
    private static final Comparator<Vbox> VBOX_COMPARATOR_VOLUME = new Comparator<Vbox>() { // from class: androidx.palette.graphics.ColorCutQuantizer.1
        @Override // java.util.Comparator
        public int compare(Vbox vbox, Vbox vbox2) {
            return vbox2.g() - vbox.g();
        }
    };

    /* renamed from: a  reason: collision with root package name */
    final int[] f3430a;

    /* renamed from: b  reason: collision with root package name */
    final int[] f3431b;

    /* renamed from: c  reason: collision with root package name */
    final List f3432c;

    /* renamed from: d  reason: collision with root package name */
    final Palette.Filter[] f3433d;
    private final float[] mTempHsl = new float[3];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class Vbox {
        private int mLowerIndex;
        private int mMaxBlue;
        private int mMaxGreen;
        private int mMaxRed;
        private int mMinBlue;
        private int mMinGreen;
        private int mMinRed;
        private int mPopulation;
        private int mUpperIndex;

        Vbox(int i2, int i3) {
            this.mLowerIndex = i2;
            this.mUpperIndex = i3;
            c();
        }

        final boolean a() {
            return e() > 1;
        }

        final int b() {
            int f2 = f();
            ColorCutQuantizer colorCutQuantizer = ColorCutQuantizer.this;
            int[] iArr = colorCutQuantizer.f3430a;
            int[] iArr2 = colorCutQuantizer.f3431b;
            ColorCutQuantizer.c(iArr, f2, this.mLowerIndex, this.mUpperIndex);
            Arrays.sort(iArr, this.mLowerIndex, this.mUpperIndex + 1);
            ColorCutQuantizer.c(iArr, f2, this.mLowerIndex, this.mUpperIndex);
            int i2 = this.mPopulation / 2;
            int i3 = this.mLowerIndex;
            int i4 = 0;
            while (true) {
                int i5 = this.mUpperIndex;
                if (i3 > i5) {
                    return this.mLowerIndex;
                }
                i4 += iArr2[iArr[i3]];
                if (i4 >= i2) {
                    return Math.min(i5 - 1, i3);
                }
                i3++;
            }
        }

        final void c() {
            ColorCutQuantizer colorCutQuantizer = ColorCutQuantizer.this;
            int[] iArr = colorCutQuantizer.f3430a;
            int[] iArr2 = colorCutQuantizer.f3431b;
            int i2 = Integer.MAX_VALUE;
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MIN_VALUE;
            int i5 = Integer.MIN_VALUE;
            int i6 = 0;
            int i7 = Integer.MAX_VALUE;
            int i8 = Integer.MAX_VALUE;
            for (int i9 = this.mLowerIndex; i9 <= this.mUpperIndex; i9++) {
                int i10 = iArr[i9];
                i6 += iArr2[i10];
                int f2 = ColorCutQuantizer.f(i10);
                int e2 = ColorCutQuantizer.e(i10);
                int d2 = ColorCutQuantizer.d(i10);
                if (f2 > i3) {
                    i3 = f2;
                }
                if (f2 < i2) {
                    i2 = f2;
                }
                if (e2 > i4) {
                    i4 = e2;
                }
                if (e2 < i7) {
                    i7 = e2;
                }
                if (d2 > i5) {
                    i5 = d2;
                }
                if (d2 < i8) {
                    i8 = d2;
                }
            }
            this.mMinRed = i2;
            this.mMaxRed = i3;
            this.mMinGreen = i7;
            this.mMaxGreen = i4;
            this.mMinBlue = i8;
            this.mMaxBlue = i5;
            this.mPopulation = i6;
        }

        final Palette.Swatch d() {
            ColorCutQuantizer colorCutQuantizer = ColorCutQuantizer.this;
            int[] iArr = colorCutQuantizer.f3430a;
            int[] iArr2 = colorCutQuantizer.f3431b;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = this.mLowerIndex; i6 <= this.mUpperIndex; i6++) {
                int i7 = iArr[i6];
                int i8 = iArr2[i7];
                i3 += i8;
                i2 += ColorCutQuantizer.f(i7) * i8;
                i4 += ColorCutQuantizer.e(i7) * i8;
                i5 += i8 * ColorCutQuantizer.d(i7);
            }
            float f2 = i3;
            return new Palette.Swatch(ColorCutQuantizer.a(Math.round(i2 / f2), Math.round(i4 / f2), Math.round(i5 / f2)), i3);
        }

        final int e() {
            return (this.mUpperIndex + 1) - this.mLowerIndex;
        }

        final int f() {
            int i2 = this.mMaxRed - this.mMinRed;
            int i3 = this.mMaxGreen - this.mMinGreen;
            int i4 = this.mMaxBlue - this.mMinBlue;
            if (i2 < i3 || i2 < i4) {
                return (i3 < i2 || i3 < i4) ? -1 : -2;
            }
            return -3;
        }

        final int g() {
            return ((this.mMaxRed - this.mMinRed) + 1) * ((this.mMaxGreen - this.mMinGreen) + 1) * ((this.mMaxBlue - this.mMinBlue) + 1);
        }

        final Vbox h() {
            if (a()) {
                int b2 = b();
                Vbox vbox = new Vbox(b2 + 1, this.mUpperIndex);
                this.mUpperIndex = b2;
                c();
                return vbox;
            }
            throw new IllegalStateException("Can not split a box with only 1 color");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ColorCutQuantizer(int[] iArr, int i2, Palette.Filter[] filterArr) {
        this.f3433d = filterArr;
        int[] iArr2 = new int[32768];
        this.f3431b = iArr2;
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int quantizeFromRgb888 = quantizeFromRgb888(iArr[i3]);
            iArr[i3] = quantizeFromRgb888;
            iArr2[quantizeFromRgb888] = iArr2[quantizeFromRgb888] + 1;
        }
        int i4 = 0;
        for (int i5 = 0; i5 < 32768; i5++) {
            if (iArr2[i5] > 0 && shouldIgnoreColor(i5)) {
                iArr2[i5] = 0;
            }
            if (iArr2[i5] > 0) {
                i4++;
            }
        }
        int[] iArr3 = new int[i4];
        this.f3430a = iArr3;
        int i6 = 0;
        for (int i7 = 0; i7 < 32768; i7++) {
            if (iArr2[i7] > 0) {
                iArr3[i6] = i7;
                i6++;
            }
        }
        if (i4 > i2) {
            this.f3432c = quantizePixels(i2);
            return;
        }
        this.f3432c = new ArrayList();
        for (int i8 = 0; i8 < i4; i8++) {
            int i9 = iArr3[i8];
            this.f3432c.add(new Palette.Swatch(approximateToRgb888(i9), iArr2[i9]));
        }
    }

    static int a(int i2, int i3, int i4) {
        return Color.rgb(modifyWordWidth(i2, 5, 8), modifyWordWidth(i3, 5, 8), modifyWordWidth(i4, 5, 8));
    }

    private static int approximateToRgb888(int i2) {
        return a(f(i2), e(i2), d(i2));
    }

    static void c(int[] iArr, int i2, int i3, int i4) {
        if (i2 == -2) {
            while (i3 <= i4) {
                int i5 = iArr[i3];
                iArr[i3] = d(i5) | (e(i5) << 10) | (f(i5) << 5);
                i3++;
            }
        } else if (i2 != -1) {
        } else {
            while (i3 <= i4) {
                int i6 = iArr[i3];
                iArr[i3] = f(i6) | (d(i6) << 10) | (e(i6) << 5);
                i3++;
            }
        }
    }

    static int d(int i2) {
        return i2 & 31;
    }

    static int e(int i2) {
        return (i2 >> 5) & 31;
    }

    static int f(int i2) {
        return (i2 >> 10) & 31;
    }

    private List<Palette.Swatch> generateAverageColors(Collection<Vbox> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Vbox vbox : collection) {
            Palette.Swatch d2 = vbox.d();
            if (!shouldIgnoreColor(d2)) {
                arrayList.add(d2);
            }
        }
        return arrayList;
    }

    private static int modifyWordWidth(int i2, int i3, int i4) {
        return (i4 > i3 ? i2 << (i4 - i3) : i2 >> (i3 - i4)) & ((1 << i4) - 1);
    }

    private static int quantizeFromRgb888(int i2) {
        return modifyWordWidth(Color.blue(i2), 8, 5) | (modifyWordWidth(Color.red(i2), 8, 5) << 10) | (modifyWordWidth(Color.green(i2), 8, 5) << 5);
    }

    private List<Palette.Swatch> quantizePixels(int i2) {
        PriorityQueue<Vbox> priorityQueue = new PriorityQueue<>(i2, VBOX_COMPARATOR_VOLUME);
        priorityQueue.offer(new Vbox(0, this.f3430a.length - 1));
        splitBoxes(priorityQueue, i2);
        return generateAverageColors(priorityQueue);
    }

    private boolean shouldIgnoreColor(int i2) {
        int approximateToRgb888 = approximateToRgb888(i2);
        ColorUtils.colorToHSL(approximateToRgb888, this.mTempHsl);
        return shouldIgnoreColor(approximateToRgb888, this.mTempHsl);
    }

    private boolean shouldIgnoreColor(int i2, float[] fArr) {
        Palette.Filter[] filterArr = this.f3433d;
        if (filterArr != null && filterArr.length > 0) {
            int length = filterArr.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (!this.f3433d[i3].isAllowed(i2, fArr)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean shouldIgnoreColor(Palette.Swatch swatch) {
        return shouldIgnoreColor(swatch.getRgb(), swatch.getHsl());
    }

    private void splitBoxes(PriorityQueue<Vbox> priorityQueue, int i2) {
        Vbox poll;
        while (priorityQueue.size() < i2 && (poll = priorityQueue.poll()) != null && poll.a()) {
            priorityQueue.offer(poll.h());
            priorityQueue.offer(poll);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List b() {
        return this.f3432c;
    }
}
