package com.psa.mym.mycitroenconnect.views.custom_seek_bar;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.psa.mym.mycitroenconnect.utils.AppUtil;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uat.psa.mym.mycitroenconnect.R;
/* loaded from: classes3.dex */
public final class CustomSeekBar extends LinearLayout {
    @NotNull
    public static final Companion Companion = new Companion(null);
    private static final boolean DEFAULT_ENABLE_ROUND = false;
    private static final boolean DEFAULT_ENABLE_STEP = true;
    private static final double DEFAULT_MAX = 100.0d;
    private static final double DEFAULT_MIN = 0.0d;
    private static final double DEFAULT_PROGRESS = 0.0d;
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    private boolean enableRound;
    private boolean enableStep;
    @Nullable
    private CustomSeekBarListener listener;
    @NotNull
    private Locale locale;
    private double mMax;
    private double mMin;
    private double mProgress;
    @NotNull
    private final SeekBar mSeekBar;
    @NotNull
    private final TextView maxLabel;
    @NotNull
    private final TextView minLabel;
    @NotNull
    private NumberFormat numberFormat;
    @NotNull
    private Function0<Integer> step;

    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomSeekBar(@NotNull Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomSeekBar(@NotNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public CustomSeekBar(@NotNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        Double d2;
        Unit unit;
        Double d3;
        Unit unit2;
        Double d4;
        Intrinsics.checkNotNullParameter(context, "context");
        this._$_findViewCache = new LinkedHashMap();
        Locale defaultLocale = AppUtil.Companion.getDefaultLocale();
        this.locale = defaultLocale;
        NumberFormat numberFormat = NumberFormat.getInstance(defaultLocale);
        Intrinsics.checkNotNullExpressionValue(numberFormat, "getInstance(locale)");
        this.numberFormat = numberFormat;
        this.mMax = DEFAULT_MAX;
        this.enableStep = true;
        this.step = new CustomSeekBar$step$1(this);
        setOrientation(1);
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
        LayoutInflater.from(context).inflate(R.layout.layout_custom_seek_bar, (ViewGroup) this, true);
        View findViewById = findViewById(R.id.csb_seek_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.csb_seek_bar)");
        this.mSeekBar = (SeekBar) findViewById;
        View findViewById2 = findViewById(R.id.text_view_1);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.text_view_1)");
        this.minLabel = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.text_view_2);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(R.id.text_view_2)");
        this.maxLabel = (TextView) findViewById3;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.psa.mym.mycitroenconnect.R.styleable.CustomSeekBar, 0, 0);
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr…able.CustomSeekBar, 0, 0)");
            try {
                String string = obtainStyledAttributes.getString(3);
                Unit unit3 = null;
                if (string != null) {
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.styleable.CustomSeekBar_cs_max)");
                    d2 = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(string);
                } else {
                    d2 = null;
                }
                if (d2 != null) {
                    setMax(Double.valueOf(d2.doubleValue()));
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    setMax(Double.valueOf((double) DEFAULT_MAX));
                }
                try {
                    String string2 = obtainStyledAttributes.getString(4);
                    if (string2 != null) {
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.styleable.CustomSeekBar_cs_min)");
                        d3 = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(string2);
                    } else {
                        d3 = null;
                    }
                    if (d3 != null) {
                        setMin(Double.valueOf(d3.doubleValue()));
                        unit2 = Unit.INSTANCE;
                    } else {
                        unit2 = null;
                    }
                    if (unit2 == null) {
                        setMin(Double.valueOf(0.0d));
                    }
                    try {
                        String string3 = obtainStyledAttributes.getString(5);
                        if (string3 != null) {
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.styleable.CustomSeekBar_cs_progress)");
                            d4 = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(string3);
                        } else {
                            d4 = null;
                        }
                        if (d4 != null) {
                            setProgress(Double.valueOf(d4.doubleValue()));
                            unit3 = Unit.INSTANCE;
                        }
                        if (unit3 == null) {
                            setProgress(Double.valueOf(0.0d));
                        }
                        showMinLabel(obtainStyledAttributes.getBoolean(8, true));
                        showMaxLabel(obtainStyledAttributes.getBoolean(7, true));
                        setEnableStep(obtainStyledAttributes.getBoolean(0, true));
                        obtainStyledAttributes.recycle();
                        setupSeekBar();
                    } catch (Exception unused) {
                        throw new IllegalArgumentException("progress value is invalid");
                    }
                } catch (Exception unused2) {
                    throw new IllegalArgumentException("min value is invalid ");
                }
            } catch (Exception unused3) {
                throw new IllegalArgumentException("max value is invalid ");
            }
        }
        refreshSeekProgress(false);
    }

    public /* synthetic */ CustomSeekBar(Context context, AttributeSet attributeSet, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? null : attributeSet, (i3 & 4) != 0 ? 0 : i2);
    }

    static /* synthetic */ void a(CustomSeekBar customSeekBar, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            z = false;
        }
        customSeekBar.refreshSeekProgress(z);
    }

    private final Pair<Integer, Integer> findStepPointsBetweenProgress(int i2, int i3, int i4) {
        Integer valueOf;
        Integer valueOf2;
        if (i3 <= 0) {
            throw new IllegalArgumentException("Step must be positive, was: " + i3 + '.');
        }
        int i5 = 0;
        int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(0, i4, i3);
        if (progressionLastElement >= 0) {
            while (i5 < i2) {
                if (i5 != progressionLastElement) {
                    i5 += i3;
                }
            }
            valueOf = Integer.valueOf(i5 - i3);
            valueOf2 = Integer.valueOf(i5);
            return TuplesKt.to(valueOf, valueOf2);
        }
        valueOf = Integer.valueOf(i4);
        valueOf2 = Integer.valueOf(i4);
        return TuplesKt.to(valueOf, valueOf2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatCustom(double d2) {
        String format = this.numberFormat.format(d2);
        Intrinsics.checkNotNullExpressionValue(format, "numberFormat.format(this)");
        return format;
    }

    private final void handleStepProgress() {
        int i2 = (int) this.mProgress;
        Pair<Integer, Integer> findStepPointsBetweenProgress = findStepPointsBetweenProgress(i2, this.step.invoke().intValue(), (int) this.mMax);
        int intValue = findStepPointsBetweenProgress.component1().intValue();
        int intValue2 = findStepPointsBetweenProgress.component2().intValue();
        int i3 = i2 - intValue;
        int i4 = intValue2 - i2;
        this.mProgress = Math.min(i3, i4) == i4 ? intValue2 : intValue;
    }

    private final void refreshMaxTextView() {
        this.maxLabel.setText(this.numberFormat.format(this.mMax));
    }

    private final void refreshMinTextView() {
        this.minLabel.setText(this.numberFormat.format(this.mMin));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshSeekProgress(boolean z) {
        int roundToInt;
        int roundToInt2;
        int roundToInt3;
        int roundToInt4;
        if (z) {
            handleStepProgress();
        }
        SeekBar seekBar = this.mSeekBar;
        roundToInt = MathKt__MathJVMKt.roundToInt(this.mMax);
        roundToInt2 = MathKt__MathJVMKt.roundToInt(this.mMin);
        seekBar.setMax(roundToInt - roundToInt2);
        roundToInt3 = MathKt__MathJVMKt.roundToInt(this.mProgress);
        roundToInt4 = MathKt__MathJVMKt.roundToInt(this.mMin);
        seekBar.setProgress(roundToInt3 - roundToInt4);
    }

    private final double roundToDouble(Number number) {
        int roundToInt;
        roundToInt = MathKt__MathJVMKt.roundToInt(number.doubleValue());
        return roundToInt;
    }

    private final void setupSeekBar() {
        ExtensionKt.splitTrack(this.mSeekBar, false);
        this.mSeekBar.setOnSeekBarChangeListener(new SimpleSeekBarListener() { // from class: com.psa.mym.mycitroenconnect.views.custom_seek_bar.CustomSeekBar$setupSeekBar$1
            /* JADX WARN: Removed duplicated region for block: B:14:0x004b  */
            /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
            @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.SimpleSeekBarListener, android.widget.SeekBar.OnSeekBarChangeListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onProgressChanged(@Nullable SeekBar seekBar, int i2, boolean z) {
                SeekBar seekBar2;
                SeekBar seekBar3;
                boolean z2;
                double d2;
                int roundToInt;
                CustomSeekBar customSeekBar;
                double d3;
                CustomSeekBarListener customSeekBarListener;
                SeekBar seekBar4;
                double d4;
                double d5;
                String formatCustom;
                seekBar2 = CustomSeekBar.this.mSeekBar;
                if (ExtensionKt.isMinProgress(seekBar2)) {
                    customSeekBar = CustomSeekBar.this;
                    d3 = customSeekBar.mMin;
                } else {
                    seekBar3 = CustomSeekBar.this.mSeekBar;
                    if (!ExtensionKt.isMaxProgress(seekBar3)) {
                        if (z) {
                            CustomSeekBar customSeekBar2 = CustomSeekBar.this;
                            d2 = customSeekBar2.mMin;
                            roundToInt = MathKt__MathJVMKt.roundToInt(d2);
                            customSeekBar2.mProgress = i2 + roundToInt;
                        }
                        CustomSeekBar customSeekBar3 = CustomSeekBar.this;
                        z2 = customSeekBar3.enableStep;
                        customSeekBar3.refreshSeekProgress(z2);
                        customSeekBarListener = CustomSeekBar.this.listener;
                        if (customSeekBarListener == null) {
                            seekBar4 = CustomSeekBar.this.mSeekBar;
                            d4 = CustomSeekBar.this.mProgress;
                            CustomSeekBar customSeekBar4 = CustomSeekBar.this;
                            d5 = customSeekBar4.mProgress;
                            formatCustom = customSeekBar4.formatCustom(d5);
                            customSeekBarListener.onProgressChanged(seekBar4, d4, formatCustom, z);
                            return;
                        }
                        return;
                    }
                    customSeekBar = CustomSeekBar.this;
                    d3 = customSeekBar.mMax;
                }
                customSeekBar.mProgress = d3;
                customSeekBarListener = CustomSeekBar.this.listener;
                if (customSeekBarListener == null) {
                }
            }

            @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.SimpleSeekBarListener, android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(@Nullable SeekBar seekBar) {
                CustomSeekBarListener customSeekBarListener;
                SeekBar seekBar2;
                double d2;
                double d3;
                String formatCustom;
                customSeekBarListener = CustomSeekBar.this.listener;
                if (customSeekBarListener != null) {
                    seekBar2 = CustomSeekBar.this.mSeekBar;
                    d2 = CustomSeekBar.this.mProgress;
                    CustomSeekBar customSeekBar = CustomSeekBar.this;
                    d3 = customSeekBar.mProgress;
                    formatCustom = customSeekBar.formatCustom(d3);
                    customSeekBarListener.onStartTrackingTouch(seekBar2, d2, formatCustom);
                }
            }

            @Override // com.psa.mym.mycitroenconnect.views.custom_seek_bar.SimpleSeekBarListener, android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(@Nullable SeekBar seekBar) {
                CustomSeekBarListener customSeekBarListener;
                SeekBar seekBar2;
                double d2;
                double d3;
                String formatCustom;
                customSeekBarListener = CustomSeekBar.this.listener;
                if (customSeekBarListener != null) {
                    seekBar2 = CustomSeekBar.this.mSeekBar;
                    d2 = CustomSeekBar.this.mProgress;
                    CustomSeekBar customSeekBar = CustomSeekBar.this;
                    d3 = customSeekBar.mProgress;
                    formatCustom = customSeekBar.formatCustom(d3);
                    customSeekBarListener.onStopTrackingTouch(seekBar2, d2, formatCustom);
                }
            }
        });
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i2) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i2));
        if (view == null) {
            View findViewById = findViewById(i2);
            if (findViewById != null) {
                map.put(Integer.valueOf(i2), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final int getLeftBound() {
        return this.mSeekBar.getThumb().getBounds().left;
    }

    public final double getProgress() {
        return this.mProgress;
    }

    public final int getSeekBarLeftBound() {
        return this.mSeekBar.getLeft();
    }

    public final boolean maxLabelIsShow() {
        return ExtensionKt.isShow(this.maxLabel);
    }

    public final boolean minLabelIsShow() {
        return ExtensionKt.isShow(this.minLabel);
    }

    public final void setEnableRound(boolean z) {
        this.enableRound = z;
    }

    public final void setEnableStep(boolean z) {
        this.enableStep = z;
    }

    public final void setLocale(@NotNull Locale locale) {
        Intrinsics.checkNotNullParameter(locale, "locale");
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        Intrinsics.checkNotNullExpressionValue(numberFormat, "getInstance(locale)");
        this.numberFormat = numberFormat;
        refreshMinTextView();
        refreshMaxTextView();
    }

    public final void setMax(@NotNull Number max) {
        Intrinsics.checkNotNullParameter(max, "max");
        if (max.doubleValue() <= this.mMin) {
            throw new IllegalArgumentException("the `Max` value must be greater than the min value");
        }
        this.mMax = max.doubleValue();
        refreshMaxTextView();
        a(this, false, 1, null);
    }

    public final void setMin(@NotNull Number min) {
        Intrinsics.checkNotNullParameter(min, "min");
        if (min.doubleValue() >= this.mMax) {
            throw new IllegalArgumentException("The `Min` value must be less than the max value");
        }
        this.mMin = min.doubleValue();
        refreshMinTextView();
        a(this, false, 1, null);
    }

    public final void setOnSeekBarChangeListener(@Nullable CustomSeekBarListener customSeekBarListener) {
        this.listener = customSeekBarListener;
        if (customSeekBarListener != null) {
            SeekBar seekBar = this.mSeekBar;
            double d2 = this.mProgress;
            customSeekBarListener.onProgressChanged(seekBar, d2, formatCustom(d2), false);
        }
    }

    public final void setProgress(@NotNull Number progress) {
        Intrinsics.checkNotNullParameter(progress, "progress");
        double doubleValue = progress.doubleValue();
        double d2 = this.mMin;
        if (doubleValue >= d2) {
            double doubleValue2 = progress.doubleValue();
            d2 = this.mMax;
            if (doubleValue2 <= d2) {
                d2 = this.enableRound ? roundToDouble(progress) : progress.doubleValue();
            }
        }
        this.mProgress = d2;
        a(this, false, 1, null);
    }

    public final void showMaxLabel(boolean z) {
        ExtensionKt.visibleFromBoolean(this.maxLabel, z);
    }

    public final void showMinLabel(boolean z) {
        ExtensionKt.visibleFromBoolean(this.minLabel, z);
    }
}
