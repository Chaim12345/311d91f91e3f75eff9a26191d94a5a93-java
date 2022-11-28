package com.github.mikephil.charting.components;

import android.graphics.DashPathEffect;
import android.graphics.Paint;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class Legend extends ComponentBase {
    private List<Boolean> mCalculatedLabelBreakPoints;
    private List<FSize> mCalculatedLabelSizes;
    private List<FSize> mCalculatedLineSizes;
    private LegendDirection mDirection;
    private boolean mDrawInside;
    private LegendEntry[] mEntries;
    private LegendEntry[] mExtraEntries;
    private DashPathEffect mFormLineDashEffect;
    private float mFormLineWidth;
    private float mFormSize;
    private float mFormToTextSpace;
    private LegendHorizontalAlignment mHorizontalAlignment;
    private boolean mIsLegendCustom;
    private float mMaxSizePercent;
    public float mNeededHeight;
    public float mNeededWidth;
    private LegendOrientation mOrientation;
    private LegendForm mShape;
    private float mStackSpace;
    public float mTextHeightMax;
    public float mTextWidthMax;
    private LegendVerticalAlignment mVerticalAlignment;
    private boolean mWordWrapEnabled;
    private float mXEntrySpace;
    private float mYEntrySpace;

    /* renamed from: com.github.mikephil.charting.components.Legend$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f5312a;

        /* renamed from: b  reason: collision with root package name */
        static final /* synthetic */ int[] f5313b;

        static {
            int[] iArr = new int[LegendOrientation.values().length];
            f5313b = iArr;
            try {
                iArr[LegendOrientation.VERTICAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5313b[LegendOrientation.HORIZONTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[LegendPosition.values().length];
            f5312a = iArr2;
            try {
                iArr2[LegendPosition.LEFT_OF_CHART.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5312a[LegendPosition.LEFT_OF_CHART_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5312a[LegendPosition.LEFT_OF_CHART_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5312a[LegendPosition.RIGHT_OF_CHART.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5312a[LegendPosition.RIGHT_OF_CHART_INSIDE.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5312a[LegendPosition.RIGHT_OF_CHART_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5312a[LegendPosition.ABOVE_CHART_LEFT.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5312a[LegendPosition.ABOVE_CHART_CENTER.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5312a[LegendPosition.ABOVE_CHART_RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f5312a[LegendPosition.BELOW_CHART_LEFT.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                f5312a[LegendPosition.BELOW_CHART_CENTER.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                f5312a[LegendPosition.BELOW_CHART_RIGHT.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                f5312a[LegendPosition.PIECHART_CENTER.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
        }
    }

    /* loaded from: classes.dex */
    public enum LegendDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    /* loaded from: classes.dex */
    public enum LegendForm {
        NONE,
        EMPTY,
        DEFAULT,
        SQUARE,
        CIRCLE,
        LINE
    }

    /* loaded from: classes.dex */
    public enum LegendHorizontalAlignment {
        LEFT,
        CENTER,
        RIGHT
    }

    /* loaded from: classes.dex */
    public enum LegendOrientation {
        HORIZONTAL,
        VERTICAL
    }

    @Deprecated
    /* loaded from: classes.dex */
    public enum LegendPosition {
        RIGHT_OF_CHART,
        RIGHT_OF_CHART_CENTER,
        RIGHT_OF_CHART_INSIDE,
        LEFT_OF_CHART,
        LEFT_OF_CHART_CENTER,
        LEFT_OF_CHART_INSIDE,
        BELOW_CHART_LEFT,
        BELOW_CHART_RIGHT,
        BELOW_CHART_CENTER,
        ABOVE_CHART_LEFT,
        ABOVE_CHART_RIGHT,
        ABOVE_CHART_CENTER,
        PIECHART_CENTER
    }

    /* loaded from: classes.dex */
    public enum LegendVerticalAlignment {
        TOP,
        CENTER,
        BOTTOM
    }

    public Legend() {
        this.mEntries = new LegendEntry[0];
        this.mIsLegendCustom = false;
        this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
        this.mVerticalAlignment = LegendVerticalAlignment.BOTTOM;
        this.mOrientation = LegendOrientation.HORIZONTAL;
        this.mDrawInside = false;
        this.mDirection = LegendDirection.LEFT_TO_RIGHT;
        this.mShape = LegendForm.SQUARE;
        this.mFormSize = 8.0f;
        this.mFormLineWidth = 3.0f;
        this.mFormLineDashEffect = null;
        this.mXEntrySpace = 6.0f;
        this.mYEntrySpace = 0.0f;
        this.mFormToTextSpace = 5.0f;
        this.mStackSpace = 3.0f;
        this.mMaxSizePercent = 0.95f;
        this.mNeededWidth = 0.0f;
        this.mNeededHeight = 0.0f;
        this.mTextHeightMax = 0.0f;
        this.mTextWidthMax = 0.0f;
        this.mWordWrapEnabled = false;
        this.mCalculatedLabelSizes = new ArrayList(16);
        this.mCalculatedLabelBreakPoints = new ArrayList(16);
        this.mCalculatedLineSizes = new ArrayList(16);
        this.f5310e = Utils.convertDpToPixel(10.0f);
        this.f5307b = Utils.convertDpToPixel(5.0f);
        this.f5308c = Utils.convertDpToPixel(3.0f);
    }

    @Deprecated
    public Legend(List<Integer> list, List<String> list2) {
        this(Utils.convertIntegers(list), Utils.convertStrings(list2));
    }

    @Deprecated
    public Legend(int[] iArr, String[] strArr) {
        this();
        LegendForm legendForm;
        if (iArr == null || strArr == null) {
            throw new IllegalArgumentException("colors array or labels array is NULL");
        }
        if (iArr.length != strArr.length) {
            throw new IllegalArgumentException("colors array and labels array need to be of same size");
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < Math.min(iArr.length, strArr.length); i2++) {
            LegendEntry legendEntry = new LegendEntry();
            int i3 = iArr[i2];
            legendEntry.formColor = i3;
            legendEntry.label = strArr[i2];
            if (i3 == 1122868) {
                legendForm = LegendForm.NONE;
            } else if (i3 == 1122867 || i3 == 0) {
                legendForm = LegendForm.EMPTY;
            } else {
                arrayList.add(legendEntry);
            }
            legendEntry.form = legendForm;
            arrayList.add(legendEntry);
        }
        this.mEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
    }

    public Legend(LegendEntry[] legendEntryArr) {
        this();
        if (legendEntryArr == null) {
            throw new IllegalArgumentException("entries array is NULL");
        }
        this.mEntries = legendEntryArr;
    }

    public void calculateDimensions(Paint paint, ViewPortHandler viewPortHandler) {
        float f2;
        float f3;
        float f4;
        float convertDpToPixel = Utils.convertDpToPixel(this.mFormSize);
        float convertDpToPixel2 = Utils.convertDpToPixel(this.mStackSpace);
        float convertDpToPixel3 = Utils.convertDpToPixel(this.mFormToTextSpace);
        float convertDpToPixel4 = Utils.convertDpToPixel(this.mXEntrySpace);
        float convertDpToPixel5 = Utils.convertDpToPixel(this.mYEntrySpace);
        boolean z = this.mWordWrapEnabled;
        LegendEntry[] legendEntryArr = this.mEntries;
        int length = legendEntryArr.length;
        this.mTextWidthMax = getMaximumEntryWidth(paint);
        this.mTextHeightMax = getMaximumEntryHeight(paint);
        int i2 = AnonymousClass1.f5313b[this.mOrientation.ordinal()];
        if (i2 == 1) {
            float lineHeight = Utils.getLineHeight(paint);
            float f5 = 0.0f;
            float f6 = 0.0f;
            float f7 = 0.0f;
            boolean z2 = false;
            for (int i3 = 0; i3 < length; i3++) {
                LegendEntry legendEntry = legendEntryArr[i3];
                boolean z3 = legendEntry.form != LegendForm.NONE;
                float convertDpToPixel6 = Float.isNaN(legendEntry.formSize) ? convertDpToPixel : Utils.convertDpToPixel(legendEntry.formSize);
                String str = legendEntry.label;
                if (!z2) {
                    f7 = 0.0f;
                }
                if (z3) {
                    if (z2) {
                        f7 += convertDpToPixel2;
                    }
                    f7 += convertDpToPixel6;
                }
                if (str != null) {
                    if (z3 && !z2) {
                        f7 += convertDpToPixel3;
                    } else if (z2) {
                        f5 = Math.max(f5, f7);
                        f6 += lineHeight + convertDpToPixel5;
                        f7 = 0.0f;
                        z2 = false;
                    }
                    f7 += Utils.calcTextWidth(paint, str);
                    if (i3 < length - 1) {
                        f6 += lineHeight + convertDpToPixel5;
                    }
                } else {
                    f7 += convertDpToPixel6;
                    if (i3 < length - 1) {
                        f7 += convertDpToPixel2;
                    }
                    z2 = true;
                }
                f5 = Math.max(f5, f7);
            }
            this.mNeededWidth = f5;
            this.mNeededHeight = f6;
        } else if (i2 == 2) {
            float lineHeight2 = Utils.getLineHeight(paint);
            float lineSpacing = Utils.getLineSpacing(paint) + convertDpToPixel5;
            float contentWidth = viewPortHandler.contentWidth() * this.mMaxSizePercent;
            this.mCalculatedLabelBreakPoints.clear();
            this.mCalculatedLabelSizes.clear();
            this.mCalculatedLineSizes.clear();
            int i4 = 0;
            float f8 = 0.0f;
            int i5 = -1;
            float f9 = 0.0f;
            float f10 = 0.0f;
            while (i4 < length) {
                LegendEntry legendEntry2 = legendEntryArr[i4];
                float f11 = convertDpToPixel;
                float f12 = convertDpToPixel4;
                boolean z4 = legendEntry2.form != LegendForm.NONE;
                float convertDpToPixel7 = Float.isNaN(legendEntry2.formSize) ? f11 : Utils.convertDpToPixel(legendEntry2.formSize);
                String str2 = legendEntry2.label;
                LegendEntry[] legendEntryArr2 = legendEntryArr;
                float f13 = lineSpacing;
                this.mCalculatedLabelBreakPoints.add(Boolean.FALSE);
                float f14 = i5 == -1 ? 0.0f : f9 + convertDpToPixel2;
                if (str2 != null) {
                    f2 = convertDpToPixel2;
                    this.mCalculatedLabelSizes.add(Utils.calcTextSize(paint, str2));
                    f3 = f14 + (z4 ? convertDpToPixel3 + convertDpToPixel7 : 0.0f) + this.mCalculatedLabelSizes.get(i4).width;
                } else {
                    f2 = convertDpToPixel2;
                    float f15 = convertDpToPixel7;
                    this.mCalculatedLabelSizes.add(FSize.getInstance(0.0f, 0.0f));
                    f3 = f14 + (z4 ? f15 : 0.0f);
                    if (i5 == -1) {
                        i5 = i4;
                    }
                }
                if (str2 != null || i4 == length - 1) {
                    float f16 = f10;
                    int i6 = (f16 > 0.0f ? 1 : (f16 == 0.0f ? 0 : -1));
                    float f17 = i6 == 0 ? 0.0f : f12;
                    if (!z || i6 == 0 || contentWidth - f16 >= f17 + f3) {
                        f4 = f16 + f17 + f3;
                    } else {
                        this.mCalculatedLineSizes.add(FSize.getInstance(f16, lineHeight2));
                        float max = Math.max(f8, f16);
                        this.mCalculatedLabelBreakPoints.set(i5 > -1 ? i5 : i4, Boolean.TRUE);
                        f8 = max;
                        f4 = f3;
                    }
                    if (i4 == length - 1) {
                        this.mCalculatedLineSizes.add(FSize.getInstance(f4, lineHeight2));
                        f8 = Math.max(f8, f4);
                    }
                    f10 = f4;
                }
                if (str2 != null) {
                    i5 = -1;
                }
                i4++;
                convertDpToPixel2 = f2;
                convertDpToPixel = f11;
                convertDpToPixel4 = f12;
                lineSpacing = f13;
                f9 = f3;
                legendEntryArr = legendEntryArr2;
            }
            float f18 = lineSpacing;
            this.mNeededWidth = f8;
            this.mNeededHeight = (lineHeight2 * this.mCalculatedLineSizes.size()) + (f18 * (this.mCalculatedLineSizes.size() == 0 ? 0 : this.mCalculatedLineSizes.size() - 1));
        }
        this.mNeededHeight += this.f5308c;
        this.mNeededWidth += this.f5307b;
    }

    public List<Boolean> getCalculatedLabelBreakPoints() {
        return this.mCalculatedLabelBreakPoints;
    }

    public List<FSize> getCalculatedLabelSizes() {
        return this.mCalculatedLabelSizes;
    }

    public List<FSize> getCalculatedLineSizes() {
        return this.mCalculatedLineSizes;
    }

    @Deprecated
    public int[] getColors() {
        int[] iArr = new int[this.mEntries.length];
        int i2 = 0;
        while (true) {
            LegendEntry[] legendEntryArr = this.mEntries;
            if (i2 >= legendEntryArr.length) {
                return iArr;
            }
            iArr[i2] = legendEntryArr[i2].form == LegendForm.NONE ? ColorTemplate.COLOR_SKIP : legendEntryArr[i2].form == LegendForm.EMPTY ? ColorTemplate.COLOR_NONE : legendEntryArr[i2].formColor;
            i2++;
        }
    }

    public LegendDirection getDirection() {
        return this.mDirection;
    }

    public LegendEntry[] getEntries() {
        return this.mEntries;
    }

    @Deprecated
    public int[] getExtraColors() {
        int[] iArr = new int[this.mExtraEntries.length];
        int i2 = 0;
        while (true) {
            LegendEntry[] legendEntryArr = this.mExtraEntries;
            if (i2 >= legendEntryArr.length) {
                return iArr;
            }
            iArr[i2] = legendEntryArr[i2].form == LegendForm.NONE ? ColorTemplate.COLOR_SKIP : legendEntryArr[i2].form == LegendForm.EMPTY ? ColorTemplate.COLOR_NONE : legendEntryArr[i2].formColor;
            i2++;
        }
    }

    public LegendEntry[] getExtraEntries() {
        return this.mExtraEntries;
    }

    @Deprecated
    public String[] getExtraLabels() {
        String[] strArr = new String[this.mExtraEntries.length];
        int i2 = 0;
        while (true) {
            LegendEntry[] legendEntryArr = this.mExtraEntries;
            if (i2 >= legendEntryArr.length) {
                return strArr;
            }
            strArr[i2] = legendEntryArr[i2].label;
            i2++;
        }
    }

    public LegendForm getForm() {
        return this.mShape;
    }

    public DashPathEffect getFormLineDashEffect() {
        return this.mFormLineDashEffect;
    }

    public float getFormLineWidth() {
        return this.mFormLineWidth;
    }

    public float getFormSize() {
        return this.mFormSize;
    }

    public float getFormToTextSpace() {
        return this.mFormToTextSpace;
    }

    public LegendHorizontalAlignment getHorizontalAlignment() {
        return this.mHorizontalAlignment;
    }

    @Deprecated
    public String[] getLabels() {
        String[] strArr = new String[this.mEntries.length];
        int i2 = 0;
        while (true) {
            LegendEntry[] legendEntryArr = this.mEntries;
            if (i2 >= legendEntryArr.length) {
                return strArr;
            }
            strArr[i2] = legendEntryArr[i2].label;
            i2++;
        }
    }

    public float getMaxSizePercent() {
        return this.mMaxSizePercent;
    }

    public float getMaximumEntryHeight(Paint paint) {
        float f2 = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            String str = legendEntry.label;
            if (str != null) {
                float calcTextHeight = Utils.calcTextHeight(paint, str);
                if (calcTextHeight > f2) {
                    f2 = calcTextHeight;
                }
            }
        }
        return f2;
    }

    public float getMaximumEntryWidth(Paint paint) {
        LegendEntry[] legendEntryArr;
        float convertDpToPixel = Utils.convertDpToPixel(this.mFormToTextSpace);
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (LegendEntry legendEntry : this.mEntries) {
            float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? this.mFormSize : legendEntry.formSize);
            if (convertDpToPixel2 > f3) {
                f3 = convertDpToPixel2;
            }
            String str = legendEntry.label;
            if (str != null) {
                float calcTextWidth = Utils.calcTextWidth(paint, str);
                if (calcTextWidth > f2) {
                    f2 = calcTextWidth;
                }
            }
        }
        return f2 + f3 + convertDpToPixel;
    }

    public LegendOrientation getOrientation() {
        return this.mOrientation;
    }

    @Deprecated
    public LegendPosition getPosition() {
        LegendOrientation legendOrientation = this.mOrientation;
        if (legendOrientation == LegendOrientation.VERTICAL && this.mHorizontalAlignment == LegendHorizontalAlignment.CENTER && this.mVerticalAlignment == LegendVerticalAlignment.CENTER) {
            return LegendPosition.PIECHART_CENTER;
        }
        if (legendOrientation == LegendOrientation.HORIZONTAL) {
            if (this.mVerticalAlignment == LegendVerticalAlignment.TOP) {
                LegendHorizontalAlignment legendHorizontalAlignment = this.mHorizontalAlignment;
                return legendHorizontalAlignment == LegendHorizontalAlignment.LEFT ? LegendPosition.ABOVE_CHART_LEFT : legendHorizontalAlignment == LegendHorizontalAlignment.RIGHT ? LegendPosition.ABOVE_CHART_RIGHT : LegendPosition.ABOVE_CHART_CENTER;
            }
            LegendHorizontalAlignment legendHorizontalAlignment2 = this.mHorizontalAlignment;
            return legendHorizontalAlignment2 == LegendHorizontalAlignment.LEFT ? LegendPosition.BELOW_CHART_LEFT : legendHorizontalAlignment2 == LegendHorizontalAlignment.RIGHT ? LegendPosition.BELOW_CHART_RIGHT : LegendPosition.BELOW_CHART_CENTER;
        } else if (this.mHorizontalAlignment == LegendHorizontalAlignment.LEFT) {
            LegendVerticalAlignment legendVerticalAlignment = this.mVerticalAlignment;
            return (legendVerticalAlignment == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.LEFT_OF_CHART_INSIDE : legendVerticalAlignment == LegendVerticalAlignment.CENTER ? LegendPosition.LEFT_OF_CHART_CENTER : LegendPosition.LEFT_OF_CHART;
        } else {
            LegendVerticalAlignment legendVerticalAlignment2 = this.mVerticalAlignment;
            return (legendVerticalAlignment2 == LegendVerticalAlignment.TOP && this.mDrawInside) ? LegendPosition.RIGHT_OF_CHART_INSIDE : legendVerticalAlignment2 == LegendVerticalAlignment.CENTER ? LegendPosition.RIGHT_OF_CHART_CENTER : LegendPosition.RIGHT_OF_CHART;
        }
    }

    public float getStackSpace() {
        return this.mStackSpace;
    }

    public LegendVerticalAlignment getVerticalAlignment() {
        return this.mVerticalAlignment;
    }

    public float getXEntrySpace() {
        return this.mXEntrySpace;
    }

    public float getYEntrySpace() {
        return this.mYEntrySpace;
    }

    public boolean isDrawInsideEnabled() {
        return this.mDrawInside;
    }

    public boolean isLegendCustom() {
        return this.mIsLegendCustom;
    }

    public boolean isWordWrapEnabled() {
        return this.mWordWrapEnabled;
    }

    public void resetCustom() {
        this.mIsLegendCustom = false;
    }

    public void setCustom(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
        this.mIsLegendCustom = true;
    }

    public void setCustom(LegendEntry[] legendEntryArr) {
        this.mEntries = legendEntryArr;
        this.mIsLegendCustom = true;
    }

    public void setDirection(LegendDirection legendDirection) {
        this.mDirection = legendDirection;
    }

    public void setDrawInside(boolean z) {
        this.mDrawInside = z;
    }

    public void setEntries(List<LegendEntry> list) {
        this.mEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    public void setExtra(List<LegendEntry> list) {
        this.mExtraEntries = (LegendEntry[]) list.toArray(new LegendEntry[list.size()]);
    }

    @Deprecated
    public void setExtra(List<Integer> list, List<String> list2) {
        setExtra(Utils.convertIntegers(list), Utils.convertStrings(list2));
    }

    public void setExtra(int[] iArr, String[] strArr) {
        LegendForm legendForm;
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < Math.min(iArr.length, strArr.length); i2++) {
            LegendEntry legendEntry = new LegendEntry();
            int i3 = iArr[i2];
            legendEntry.formColor = i3;
            legendEntry.label = strArr[i2];
            if (i3 == 1122868 || i3 == 0) {
                legendForm = LegendForm.NONE;
            } else if (i3 == 1122867) {
                legendForm = LegendForm.EMPTY;
            } else {
                arrayList.add(legendEntry);
            }
            legendEntry.form = legendForm;
            arrayList.add(legendEntry);
        }
        this.mExtraEntries = (LegendEntry[]) arrayList.toArray(new LegendEntry[arrayList.size()]);
    }

    public void setExtra(LegendEntry[] legendEntryArr) {
        if (legendEntryArr == null) {
            legendEntryArr = new LegendEntry[0];
        }
        this.mExtraEntries = legendEntryArr;
    }

    public void setForm(LegendForm legendForm) {
        this.mShape = legendForm;
    }

    public void setFormLineDashEffect(DashPathEffect dashPathEffect) {
        this.mFormLineDashEffect = dashPathEffect;
    }

    public void setFormLineWidth(float f2) {
        this.mFormLineWidth = f2;
    }

    public void setFormSize(float f2) {
        this.mFormSize = f2;
    }

    public void setFormToTextSpace(float f2) {
        this.mFormToTextSpace = f2;
    }

    public void setHorizontalAlignment(LegendHorizontalAlignment legendHorizontalAlignment) {
        this.mHorizontalAlignment = legendHorizontalAlignment;
    }

    public void setMaxSizePercent(float f2) {
        this.mMaxSizePercent = f2;
    }

    public void setOrientation(LegendOrientation legendOrientation) {
        this.mOrientation = legendOrientation;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x004d, code lost:
        if (r3 == com.github.mikephil.charting.components.Legend.LegendPosition.RIGHT_OF_CHART_CENTER) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0050, code lost:
        r0 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0059, code lost:
        if (r3 == com.github.mikephil.charting.components.Legend.LegendPosition.LEFT_OF_CHART_CENTER) goto L4;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0060  */
    @Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void setPosition(LegendPosition legendPosition) {
        LegendVerticalAlignment legendVerticalAlignment;
        LegendOrientation legendOrientation;
        switch (AnonymousClass1.f5312a[legendPosition.ordinal()]) {
            case 1:
            case 2:
            case 3:
                this.mHorizontalAlignment = LegendHorizontalAlignment.LEFT;
                break;
            case 4:
            case 5:
            case 6:
                this.mHorizontalAlignment = LegendHorizontalAlignment.RIGHT;
                break;
            case 7:
            case 8:
            case 9:
                this.mHorizontalAlignment = legendPosition == LegendPosition.ABOVE_CHART_LEFT ? LegendHorizontalAlignment.LEFT : legendPosition == LegendPosition.ABOVE_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                legendVerticalAlignment = LegendVerticalAlignment.TOP;
                this.mVerticalAlignment = legendVerticalAlignment;
                legendOrientation = LegendOrientation.HORIZONTAL;
                this.mOrientation = legendOrientation;
                this.mDrawInside = legendPosition != LegendPosition.LEFT_OF_CHART_INSIDE || legendPosition == LegendPosition.RIGHT_OF_CHART_INSIDE;
                return;
            case 10:
            case 11:
            case 12:
                this.mHorizontalAlignment = legendPosition == LegendPosition.BELOW_CHART_LEFT ? LegendHorizontalAlignment.LEFT : legendPosition == LegendPosition.BELOW_CHART_RIGHT ? LegendHorizontalAlignment.RIGHT : LegendHorizontalAlignment.CENTER;
                legendVerticalAlignment = LegendVerticalAlignment.BOTTOM;
                this.mVerticalAlignment = legendVerticalAlignment;
                legendOrientation = LegendOrientation.HORIZONTAL;
                this.mOrientation = legendOrientation;
                if (legendPosition != LegendPosition.LEFT_OF_CHART_INSIDE) {
                    break;
                }
                this.mDrawInside = legendPosition != LegendPosition.LEFT_OF_CHART_INSIDE || legendPosition == LegendPosition.RIGHT_OF_CHART_INSIDE;
                return;
            case 13:
                this.mHorizontalAlignment = LegendHorizontalAlignment.CENTER;
                LegendVerticalAlignment legendVerticalAlignment2 = LegendVerticalAlignment.CENTER;
                this.mVerticalAlignment = legendVerticalAlignment2;
                legendOrientation = LegendOrientation.VERTICAL;
                this.mOrientation = legendOrientation;
                this.mDrawInside = legendPosition != LegendPosition.LEFT_OF_CHART_INSIDE || legendPosition == LegendPosition.RIGHT_OF_CHART_INSIDE;
                return;
            default:
                this.mDrawInside = legendPosition != LegendPosition.LEFT_OF_CHART_INSIDE || legendPosition == LegendPosition.RIGHT_OF_CHART_INSIDE;
                return;
        }
    }

    public void setStackSpace(float f2) {
        this.mStackSpace = f2;
    }

    public void setVerticalAlignment(LegendVerticalAlignment legendVerticalAlignment) {
        this.mVerticalAlignment = legendVerticalAlignment;
    }

    public void setWordWrapEnabled(boolean z) {
        this.mWordWrapEnabled = z;
    }

    public void setXEntrySpace(float f2) {
        this.mXEntrySpace = f2;
    }

    public void setYEntrySpace(float f2) {
        this.mYEntrySpace = f2;
    }
}
