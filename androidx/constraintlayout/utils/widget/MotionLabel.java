package androidx.constraintlayout.utils.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.motion.widget.Debug;
import androidx.constraintlayout.motion.widget.FloatLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.view.GravityCompat;
import com.google.android.material.badge.BadgeDrawable;
/* loaded from: classes.dex */
public class MotionLabel extends View implements FloatLayout {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;

    /* renamed from: a  reason: collision with root package name */
    TextPaint f2214a;

    /* renamed from: b  reason: collision with root package name */
    Path f2215b;

    /* renamed from: c  reason: collision with root package name */
    ViewOutlineProvider f2216c;

    /* renamed from: d  reason: collision with root package name */
    RectF f2217d;

    /* renamed from: e  reason: collision with root package name */
    boolean f2218e;

    /* renamed from: f  reason: collision with root package name */
    Matrix f2219f;

    /* renamed from: g  reason: collision with root package name */
    Paint f2220g;

    /* renamed from: h  reason: collision with root package name */
    Rect f2221h;

    /* renamed from: i  reason: collision with root package name */
    Paint f2222i;

    /* renamed from: j  reason: collision with root package name */
    float f2223j;

    /* renamed from: k  reason: collision with root package name */
    float f2224k;

    /* renamed from: l  reason: collision with root package name */
    float f2225l;

    /* renamed from: m  reason: collision with root package name */
    float f2226m;
    private boolean mAutoSize;
    private int mAutoSizeTextType;
    private float mBaseTextSize;
    private float mDeltaLeft;
    private float mFloatHeight;
    private float mFloatWidth;
    private String mFontFamily;
    private int mGravity;
    private Layout mLayout;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private float mRound;
    private float mRoundPercent;
    private int mStyleIndex;
    private String mText;
    private Drawable mTextBackground;
    private Bitmap mTextBackgroundBitmap;
    private Rect mTextBounds;
    private int mTextFillColor;
    private int mTextOutlineColor;
    private float mTextOutlineThickness;
    private float mTextPanX;
    private float mTextPanY;
    private BitmapShader mTextShader;
    private Matrix mTextShaderMatrix;
    private float mTextSize;
    private int mTextureEffect;
    private float mTextureHeight;
    private float mTextureWidth;
    private CharSequence mTransformed;
    private int mTypefaceIndex;
    private boolean mUseOutline;

    /* renamed from: n  reason: collision with root package name */
    float f2227n;

    public MotionLabel(Context context) {
        super(context);
        this.f2214a = new TextPaint();
        this.f2215b = new Path();
        this.mTextFillColor = 65535;
        this.mTextOutlineColor = 65535;
        this.mUseOutline = false;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        this.mTextSize = 48.0f;
        this.mBaseTextSize = Float.NaN;
        this.mTextOutlineThickness = 0.0f;
        this.mText = "Hello World";
        this.f2218e = true;
        this.mTextBounds = new Rect();
        this.mPaddingLeft = 1;
        this.mPaddingRight = 1;
        this.mPaddingTop = 1;
        this.mPaddingBottom = 1;
        this.mGravity = BadgeDrawable.TOP_START;
        this.mAutoSizeTextType = 0;
        this.mAutoSize = false;
        this.mTextureHeight = Float.NaN;
        this.mTextureWidth = Float.NaN;
        this.mTextPanX = 0.0f;
        this.mTextPanY = 0.0f;
        this.f2220g = new Paint();
        this.mTextureEffect = 0;
        this.f2224k = Float.NaN;
        this.f2225l = Float.NaN;
        this.f2226m = Float.NaN;
        this.f2227n = Float.NaN;
        init(context, null);
    }

    public MotionLabel(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2214a = new TextPaint();
        this.f2215b = new Path();
        this.mTextFillColor = 65535;
        this.mTextOutlineColor = 65535;
        this.mUseOutline = false;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        this.mTextSize = 48.0f;
        this.mBaseTextSize = Float.NaN;
        this.mTextOutlineThickness = 0.0f;
        this.mText = "Hello World";
        this.f2218e = true;
        this.mTextBounds = new Rect();
        this.mPaddingLeft = 1;
        this.mPaddingRight = 1;
        this.mPaddingTop = 1;
        this.mPaddingBottom = 1;
        this.mGravity = BadgeDrawable.TOP_START;
        this.mAutoSizeTextType = 0;
        this.mAutoSize = false;
        this.mTextureHeight = Float.NaN;
        this.mTextureWidth = Float.NaN;
        this.mTextPanX = 0.0f;
        this.mTextPanY = 0.0f;
        this.f2220g = new Paint();
        this.mTextureEffect = 0;
        this.f2224k = Float.NaN;
        this.f2225l = Float.NaN;
        this.f2226m = Float.NaN;
        this.f2227n = Float.NaN;
        init(context, attributeSet);
    }

    public MotionLabel(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.f2214a = new TextPaint();
        this.f2215b = new Path();
        this.mTextFillColor = 65535;
        this.mTextOutlineColor = 65535;
        this.mUseOutline = false;
        this.mRoundPercent = 0.0f;
        this.mRound = Float.NaN;
        this.mTextSize = 48.0f;
        this.mBaseTextSize = Float.NaN;
        this.mTextOutlineThickness = 0.0f;
        this.mText = "Hello World";
        this.f2218e = true;
        this.mTextBounds = new Rect();
        this.mPaddingLeft = 1;
        this.mPaddingRight = 1;
        this.mPaddingTop = 1;
        this.mPaddingBottom = 1;
        this.mGravity = BadgeDrawable.TOP_START;
        this.mAutoSizeTextType = 0;
        this.mAutoSize = false;
        this.mTextureHeight = Float.NaN;
        this.mTextureWidth = Float.NaN;
        this.mTextPanX = 0.0f;
        this.mTextPanY = 0.0f;
        this.f2220g = new Paint();
        this.mTextureEffect = 0;
        this.f2224k = Float.NaN;
        this.f2225l = Float.NaN;
        this.f2226m = Float.NaN;
        this.f2227n = Float.NaN;
        init(context, attributeSet);
    }

    private void adjustTexture(float f2, float f3, float f4, float f5) {
        if (this.mTextShaderMatrix == null) {
            return;
        }
        this.mFloatWidth = f4 - f2;
        this.mFloatHeight = f5 - f3;
        updateShaderMatrix();
    }

    private float getHorizontalOffset() {
        float f2 = Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize;
        TextPaint textPaint = this.f2214a;
        String str = this.mText;
        return (((((Float.isNaN(this.mFloatWidth) ? getMeasuredWidth() : this.mFloatWidth) - getPaddingLeft()) - getPaddingRight()) - (f2 * textPaint.measureText(str, 0, str.length()))) * (this.mTextPanX + 1.0f)) / 2.0f;
    }

    private float getVerticalOffset() {
        float f2 = Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize;
        Paint.FontMetrics fontMetrics = this.f2214a.getFontMetrics();
        float measuredHeight = ((Float.isNaN(this.mFloatHeight) ? getMeasuredHeight() : this.mFloatHeight) - getPaddingTop()) - getPaddingBottom();
        float f3 = fontMetrics.descent;
        float f4 = fontMetrics.ascent;
        return (((measuredHeight - ((f3 - f4) * f2)) * (1.0f - this.mTextPanY)) / 2.0f) - (f2 * f4);
    }

    private void init(Context context, AttributeSet attributeSet) {
        setUpTheme(context, attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLabel);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionLabel_android_text) {
                    setText(obtainStyledAttributes.getText(index));
                } else if (index == R.styleable.MotionLabel_android_fontFamily) {
                    this.mFontFamily = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.MotionLabel_scaleFromTextSize) {
                    this.mBaseTextSize = obtainStyledAttributes.getDimensionPixelSize(index, (int) this.mBaseTextSize);
                } else if (index == R.styleable.MotionLabel_android_textSize) {
                    this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(index, (int) this.mTextSize);
                } else if (index == R.styleable.MotionLabel_android_textStyle) {
                    this.mStyleIndex = obtainStyledAttributes.getInt(index, this.mStyleIndex);
                } else if (index == R.styleable.MotionLabel_android_typeface) {
                    this.mTypefaceIndex = obtainStyledAttributes.getInt(index, this.mTypefaceIndex);
                } else if (index == R.styleable.MotionLabel_android_textColor) {
                    this.mTextFillColor = obtainStyledAttributes.getColor(index, this.mTextFillColor);
                } else if (index == R.styleable.MotionLabel_borderRound) {
                    float dimension = obtainStyledAttributes.getDimension(index, this.mRound);
                    this.mRound = dimension;
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRound(dimension);
                    }
                } else if (index == R.styleable.MotionLabel_borderRoundPercent) {
                    float f2 = obtainStyledAttributes.getFloat(index, this.mRoundPercent);
                    this.mRoundPercent = f2;
                    if (Build.VERSION.SDK_INT >= 21) {
                        setRoundPercent(f2);
                    }
                } else if (index == R.styleable.MotionLabel_android_gravity) {
                    setGravity(obtainStyledAttributes.getInt(index, -1));
                } else if (index == R.styleable.MotionLabel_android_autoSizeTextType) {
                    this.mAutoSizeTextType = obtainStyledAttributes.getInt(index, 0);
                } else {
                    if (index == R.styleable.MotionLabel_textOutlineColor) {
                        this.mTextOutlineColor = obtainStyledAttributes.getInt(index, this.mTextOutlineColor);
                    } else if (index == R.styleable.MotionLabel_textOutlineThickness) {
                        this.mTextOutlineThickness = obtainStyledAttributes.getDimension(index, this.mTextOutlineThickness);
                    } else if (index == R.styleable.MotionLabel_textBackground) {
                        this.mTextBackground = obtainStyledAttributes.getDrawable(index);
                    } else if (index == R.styleable.MotionLabel_textBackgroundPanX) {
                        this.f2224k = obtainStyledAttributes.getFloat(index, this.f2224k);
                    } else if (index == R.styleable.MotionLabel_textBackgroundPanY) {
                        this.f2225l = obtainStyledAttributes.getFloat(index, this.f2225l);
                    } else if (index == R.styleable.MotionLabel_textPanX) {
                        this.mTextPanX = obtainStyledAttributes.getFloat(index, this.mTextPanX);
                    } else if (index == R.styleable.MotionLabel_textPanY) {
                        this.mTextPanY = obtainStyledAttributes.getFloat(index, this.mTextPanY);
                    } else if (index == R.styleable.MotionLabel_textBackgroundRotate) {
                        this.f2227n = obtainStyledAttributes.getFloat(index, this.f2227n);
                    } else if (index == R.styleable.MotionLabel_textBackgroundZoom) {
                        this.f2226m = obtainStyledAttributes.getFloat(index, this.f2226m);
                    } else if (index == R.styleable.MotionLabel_textureHeight) {
                        this.mTextureHeight = obtainStyledAttributes.getDimension(index, this.mTextureHeight);
                    } else if (index == R.styleable.MotionLabel_textureWidth) {
                        this.mTextureWidth = obtainStyledAttributes.getDimension(index, this.mTextureWidth);
                    } else if (index == R.styleable.MotionLabel_textureEffect) {
                        this.mTextureEffect = obtainStyledAttributes.getInt(index, this.mTextureEffect);
                    }
                    this.mUseOutline = true;
                }
            }
            obtainStyledAttributes.recycle();
        }
        setupTexture();
        e();
    }

    private void setTypefaceFromAttrs(String str, int i2, int i3) {
        Typeface typeface;
        if (str != null) {
            typeface = Typeface.create(str, i3);
            if (typeface != null) {
                setTypeface(typeface);
                return;
            }
        } else {
            typeface = null;
        }
        if (i2 == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i2 == 2) {
            typeface = Typeface.SERIF;
        } else if (i2 == 3) {
            typeface = Typeface.MONOSPACE;
        }
        if (i3 <= 0) {
            this.f2214a.setFakeBoldText(false);
            this.f2214a.setTextSkewX(0.0f);
            setTypeface(typeface);
            return;
        }
        Typeface defaultFromStyle = typeface == null ? Typeface.defaultFromStyle(i3) : Typeface.create(typeface, i3);
        setTypeface(defaultFromStyle);
        int i4 = (~(defaultFromStyle != null ? defaultFromStyle.getStyle() : 0)) & i3;
        this.f2214a.setFakeBoldText((i4 & 1) != 0);
        this.f2214a.setTextSkewX((i4 & 2) != 0 ? -0.25f : 0.0f);
    }

    private void setUpTheme(Context context, @Nullable AttributeSet attributeSet) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true);
        TextPaint textPaint = this.f2214a;
        int i2 = typedValue.data;
        this.mTextFillColor = i2;
        textPaint.setColor(i2);
    }

    private void setupTexture() {
        if (this.mTextBackground != null) {
            this.mTextShaderMatrix = new Matrix();
            int intrinsicWidth = this.mTextBackground.getIntrinsicWidth();
            int intrinsicHeight = this.mTextBackground.getIntrinsicHeight();
            if (intrinsicWidth <= 0 && (intrinsicWidth = getWidth()) == 0) {
                intrinsicWidth = Float.isNaN(this.mTextureWidth) ? 128 : (int) this.mTextureWidth;
            }
            if (intrinsicHeight <= 0 && (intrinsicHeight = getHeight()) == 0) {
                intrinsicHeight = Float.isNaN(this.mTextureHeight) ? 128 : (int) this.mTextureHeight;
            }
            if (this.mTextureEffect != 0) {
                intrinsicWidth /= 2;
                intrinsicHeight /= 2;
            }
            this.mTextBackgroundBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(this.mTextBackgroundBitmap);
            this.mTextBackground.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            this.mTextBackground.setFilterBitmap(true);
            this.mTextBackground.draw(canvas);
            if (this.mTextureEffect != 0) {
                this.mTextBackgroundBitmap = c(this.mTextBackgroundBitmap, 4);
            }
            Bitmap bitmap = this.mTextBackgroundBitmap;
            Shader.TileMode tileMode = Shader.TileMode.REPEAT;
            this.mTextShader = new BitmapShader(bitmap, tileMode, tileMode);
        }
    }

    private void updateShaderMatrix() {
        float f2 = Float.isNaN(this.f2224k) ? 0.0f : this.f2224k;
        float f3 = Float.isNaN(this.f2225l) ? 0.0f : this.f2225l;
        float f4 = Float.isNaN(this.f2226m) ? 1.0f : this.f2226m;
        float f5 = Float.isNaN(this.f2227n) ? 0.0f : this.f2227n;
        this.mTextShaderMatrix.reset();
        float width = this.mTextBackgroundBitmap.getWidth();
        float height = this.mTextBackgroundBitmap.getHeight();
        float f6 = Float.isNaN(this.mTextureWidth) ? this.mFloatWidth : this.mTextureWidth;
        float f7 = Float.isNaN(this.mTextureHeight) ? this.mFloatHeight : this.mTextureHeight;
        float f8 = f4 * (width * f7 < height * f6 ? f6 / width : f7 / height);
        this.mTextShaderMatrix.postScale(f8, f8);
        float f9 = width * f8;
        float f10 = f6 - f9;
        float f11 = f8 * height;
        float f12 = f7 - f11;
        if (!Float.isNaN(this.mTextureHeight)) {
            f12 = this.mTextureHeight / 2.0f;
        }
        if (!Float.isNaN(this.mTextureWidth)) {
            f10 = this.mTextureWidth / 2.0f;
        }
        this.mTextShaderMatrix.postTranslate((((f2 * f10) + f6) - f9) * 0.5f, (((f3 * f12) + f7) - f11) * 0.5f);
        this.mTextShaderMatrix.postRotate(f5, f6 / 2.0f, f7 / 2.0f);
        this.mTextShader.setLocalMatrix(this.mTextShaderMatrix);
    }

    Bitmap c(Bitmap bitmap, int i2) {
        System.nanoTime();
        int width = bitmap.getWidth() / 2;
        int height = bitmap.getHeight() / 2;
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
        for (int i3 = 0; i3 < i2 && width >= 32 && height >= 32; i3++) {
            width /= 2;
            height /= 2;
            createScaledBitmap = Bitmap.createScaledBitmap(createScaledBitmap, width, height, true);
        }
        return createScaledBitmap;
    }

    void d(float f2) {
        if (this.mUseOutline || f2 != 1.0f) {
            this.f2215b.reset();
            String str = this.mText;
            int length = str.length();
            this.f2214a.getTextBounds(str, 0, length, this.mTextBounds);
            this.f2214a.getTextPath(str, 0, length, 0.0f, 0.0f, this.f2215b);
            if (f2 != 1.0f) {
                StringBuilder sb = new StringBuilder();
                sb.append(Debug.getLoc());
                sb.append(" scale ");
                sb.append(f2);
                Matrix matrix = new Matrix();
                matrix.postScale(f2, f2);
                this.f2215b.transform(matrix);
            }
            Rect rect = this.mTextBounds;
            rect.right--;
            rect.left++;
            rect.bottom++;
            rect.top--;
            RectF rectF = new RectF();
            rectF.bottom = getHeight();
            rectF.right = getWidth();
            this.f2218e = false;
        }
    }

    void e() {
        this.mPaddingLeft = getPaddingLeft();
        this.mPaddingRight = getPaddingRight();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        setTypefaceFromAttrs(this.mFontFamily, this.mTypefaceIndex, this.mStyleIndex);
        this.f2214a.setColor(this.mTextFillColor);
        this.f2214a.setStrokeWidth(this.mTextOutlineThickness);
        this.f2214a.setStyle(Paint.Style.FILL_AND_STROKE);
        this.f2214a.setFlags(128);
        setTextSize(this.mTextSize);
        this.f2214a.setAntiAlias(true);
    }

    public float getRound() {
        return this.mRound;
    }

    public float getRoundPercent() {
        return this.mRoundPercent;
    }

    public float getScaleFromTextSize() {
        return this.mBaseTextSize;
    }

    public float getTextBackgroundPanX() {
        return this.f2224k;
    }

    public float getTextBackgroundPanY() {
        return this.f2225l;
    }

    public float getTextBackgroundRotate() {
        return this.f2227n;
    }

    public float getTextBackgroundZoom() {
        return this.f2226m;
    }

    public int getTextOutlineColor() {
        return this.mTextOutlineColor;
    }

    public float getTextPanX() {
        return this.mTextPanX;
    }

    public float getTextPanY() {
        return this.mTextPanY;
    }

    public float getTextureHeight() {
        return this.mTextureHeight;
    }

    public float getTextureWidth() {
        return this.mTextureWidth;
    }

    public Typeface getTypeface() {
        return this.f2214a.getTypeface();
    }

    @Override // androidx.constraintlayout.motion.widget.FloatLayout
    public void layout(float f2, float f3, float f4, float f5) {
        int i2 = (int) (f2 + 0.5f);
        this.mDeltaLeft = f2 - i2;
        int i3 = (int) (f4 + 0.5f);
        int i4 = i3 - i2;
        int i5 = (int) (f5 + 0.5f);
        int i6 = (int) (0.5f + f3);
        int i7 = i5 - i6;
        float f6 = f4 - f2;
        this.mFloatWidth = f6;
        float f7 = f5 - f3;
        this.mFloatHeight = f7;
        adjustTexture(f2, f3, f4, f5);
        if (getMeasuredHeight() != i7 || getMeasuredWidth() != i4) {
            measure(View.MeasureSpec.makeMeasureSpec(i4, 1073741824), View.MeasureSpec.makeMeasureSpec(i7, 1073741824));
        }
        super.layout(i2, i6, i3, i5);
        if (this.mAutoSize) {
            if (this.f2221h == null) {
                this.f2222i = new Paint();
                this.f2221h = new Rect();
                this.f2222i.set(this.f2214a);
                this.f2223j = this.f2222i.getTextSize();
            }
            this.mFloatWidth = f6;
            this.mFloatHeight = f7;
            Paint paint = this.f2222i;
            String str = this.mText;
            paint.getTextBounds(str, 0, str.length(), this.f2221h);
            float height = this.f2221h.height() * 1.3f;
            float f8 = (f6 - this.mPaddingRight) - this.mPaddingLeft;
            float f9 = (f7 - this.mPaddingBottom) - this.mPaddingTop;
            float width = this.f2221h.width();
            if (width * f9 > height * f8) {
                this.f2214a.setTextSize((this.f2223j * f8) / width);
            } else {
                this.f2214a.setTextSize((this.f2223j * f9) / height);
            }
            if (this.mUseOutline || !Float.isNaN(this.mBaseTextSize)) {
                d(Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize);
            }
        }
    }

    @Override // android.view.View
    public void layout(int i2, int i3, int i4, int i5) {
        super.layout(i2, i3, i4, i5);
        boolean isNaN = Float.isNaN(this.mBaseTextSize);
        float f2 = isNaN ? 1.0f : this.mTextSize / this.mBaseTextSize;
        this.mFloatWidth = i4 - i2;
        this.mFloatHeight = i5 - i3;
        if (this.mAutoSize) {
            if (this.f2221h == null) {
                this.f2222i = new Paint();
                this.f2221h = new Rect();
                this.f2222i.set(this.f2214a);
                this.f2223j = this.f2222i.getTextSize();
            }
            Paint paint = this.f2222i;
            String str = this.mText;
            paint.getTextBounds(str, 0, str.length(), this.f2221h);
            int width = this.f2221h.width();
            int height = (int) (this.f2221h.height() * 1.3f);
            float f3 = (this.mFloatWidth - this.mPaddingRight) - this.mPaddingLeft;
            float f4 = (this.mFloatHeight - this.mPaddingBottom) - this.mPaddingTop;
            if (isNaN) {
                float f5 = width;
                float f6 = height;
                if (f5 * f4 > f6 * f3) {
                    this.f2214a.setTextSize((this.f2223j * f3) / f5);
                } else {
                    this.f2214a.setTextSize((this.f2223j * f4) / f6);
                }
            } else {
                float f7 = width;
                float f8 = height;
                f2 = f7 * f4 > f8 * f3 ? f3 / f7 : f4 / f8;
            }
        }
        if (this.mUseOutline || !isNaN) {
            adjustTexture(i2, i3, i4, i5);
            d(f2);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float f2 = Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize;
        super.onDraw(canvas);
        if (!this.mUseOutline && f2 == 1.0f) {
            canvas.drawText(this.mText, this.mDeltaLeft + this.mPaddingLeft + getHorizontalOffset(), this.mPaddingTop + getVerticalOffset(), this.f2214a);
            return;
        }
        if (this.f2218e) {
            d(f2);
        }
        if (this.f2219f == null) {
            this.f2219f = new Matrix();
        }
        if (!this.mUseOutline) {
            float horizontalOffset = this.mPaddingLeft + getHorizontalOffset();
            float verticalOffset = this.mPaddingTop + getVerticalOffset();
            this.f2219f.reset();
            this.f2219f.preTranslate(horizontalOffset, verticalOffset);
            this.f2215b.transform(this.f2219f);
            this.f2214a.setColor(this.mTextFillColor);
            this.f2214a.setStyle(Paint.Style.FILL_AND_STROKE);
            this.f2214a.setStrokeWidth(this.mTextOutlineThickness);
            canvas.drawPath(this.f2215b, this.f2214a);
            this.f2219f.reset();
            this.f2219f.preTranslate(-horizontalOffset, -verticalOffset);
            this.f2215b.transform(this.f2219f);
            return;
        }
        this.f2220g.set(this.f2214a);
        this.f2219f.reset();
        float horizontalOffset2 = this.mPaddingLeft + getHorizontalOffset();
        float verticalOffset2 = this.mPaddingTop + getVerticalOffset();
        this.f2219f.postTranslate(horizontalOffset2, verticalOffset2);
        this.f2219f.preScale(f2, f2);
        this.f2215b.transform(this.f2219f);
        if (this.mTextShader != null) {
            this.f2214a.setFilterBitmap(true);
            this.f2214a.setShader(this.mTextShader);
        } else {
            this.f2214a.setColor(this.mTextFillColor);
        }
        this.f2214a.setStyle(Paint.Style.FILL);
        this.f2214a.setStrokeWidth(this.mTextOutlineThickness);
        canvas.drawPath(this.f2215b, this.f2214a);
        if (this.mTextShader != null) {
            this.f2214a.setShader(null);
        }
        this.f2214a.setColor(this.mTextOutlineColor);
        this.f2214a.setStyle(Paint.Style.STROKE);
        this.f2214a.setStrokeWidth(this.mTextOutlineThickness);
        canvas.drawPath(this.f2215b, this.f2214a);
        this.f2219f.reset();
        this.f2219f.postTranslate(-horizontalOffset2, -verticalOffset2);
        this.f2215b.transform(this.f2219f);
        this.f2214a.set(this.f2220g);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int mode = View.MeasureSpec.getMode(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i3);
        this.mAutoSize = false;
        this.mPaddingLeft = getPaddingLeft();
        this.mPaddingRight = getPaddingRight();
        this.mPaddingTop = getPaddingTop();
        this.mPaddingBottom = getPaddingBottom();
        if (mode != 1073741824 || mode2 != 1073741824) {
            TextPaint textPaint = this.f2214a;
            String str = this.mText;
            textPaint.getTextBounds(str, 0, str.length(), this.mTextBounds);
            if (mode != 1073741824) {
                size = (int) (this.mTextBounds.width() + 0.99999f);
            }
            size += this.mPaddingLeft + this.mPaddingRight;
            if (mode2 != 1073741824) {
                int fontMetricsInt = (int) (this.f2214a.getFontMetricsInt(null) + 0.99999f);
                if (mode2 == Integer.MIN_VALUE) {
                    fontMetricsInt = Math.min(size2, fontMetricsInt);
                }
                size2 = this.mPaddingTop + this.mPaddingBottom + fontMetricsInt;
            }
        } else if (this.mAutoSizeTextType != 0) {
            this.mAutoSize = true;
        }
        setMeasuredDimension(size, size2);
    }

    @SuppressLint({"RtlHardcoded"})
    public void setGravity(int i2) {
        if ((i2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK) == 0) {
            i2 |= GravityCompat.START;
        }
        if ((i2 & 112) == 0) {
            i2 |= 48;
        }
        if (i2 != this.mGravity) {
            invalidate();
        }
        this.mGravity = i2;
        int i3 = i2 & 112;
        if (i3 == 48) {
            this.mTextPanY = -1.0f;
        } else if (i3 != 80) {
            this.mTextPanY = 0.0f;
        } else {
            this.mTextPanY = 1.0f;
        }
        int i4 = i2 & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i4 != 3) {
            if (i4 != 5) {
                if (i4 != 8388611) {
                    if (i4 != 8388613) {
                        this.mTextPanX = 0.0f;
                        return;
                    }
                }
            }
            this.mTextPanX = 1.0f;
            return;
        }
        this.mTextPanX = -1.0f;
    }

    @RequiresApi(21)
    public void setRound(float f2) {
        if (Float.isNaN(f2)) {
            this.mRound = f2;
            float f3 = this.mRoundPercent;
            this.mRoundPercent = -1.0f;
            setRoundPercent(f3);
            return;
        }
        boolean z = this.mRound != f2;
        this.mRound = f2;
        if (f2 != 0.0f) {
            if (this.f2215b == null) {
                this.f2215b = new Path();
            }
            if (this.f2217d == null) {
                this.f2217d = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f2216c == null) {
                    ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() { // from class: androidx.constraintlayout.utils.widget.MotionLabel.2
                        @Override // android.view.ViewOutlineProvider
                        public void getOutline(View view, Outline outline) {
                            outline.setRoundRect(0, 0, MotionLabel.this.getWidth(), MotionLabel.this.getHeight(), MotionLabel.this.mRound);
                        }
                    };
                    this.f2216c = viewOutlineProvider;
                    setOutlineProvider(viewOutlineProvider);
                }
                setClipToOutline(true);
            }
            this.f2217d.set(0.0f, 0.0f, getWidth(), getHeight());
            this.f2215b.reset();
            Path path = this.f2215b;
            RectF rectF = this.f2217d;
            float f4 = this.mRound;
            path.addRoundRect(rectF, f4, f4, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    @RequiresApi(21)
    public void setRoundPercent(float f2) {
        boolean z = this.mRoundPercent != f2;
        this.mRoundPercent = f2;
        if (f2 != 0.0f) {
            if (this.f2215b == null) {
                this.f2215b = new Path();
            }
            if (this.f2217d == null) {
                this.f2217d = new RectF();
            }
            if (Build.VERSION.SDK_INT >= 21) {
                if (this.f2216c == null) {
                    ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() { // from class: androidx.constraintlayout.utils.widget.MotionLabel.1
                        @Override // android.view.ViewOutlineProvider
                        public void getOutline(View view, Outline outline) {
                            int width = MotionLabel.this.getWidth();
                            int height = MotionLabel.this.getHeight();
                            outline.setRoundRect(0, 0, width, height, (Math.min(width, height) * MotionLabel.this.mRoundPercent) / 2.0f);
                        }
                    };
                    this.f2216c = viewOutlineProvider;
                    setOutlineProvider(viewOutlineProvider);
                }
                setClipToOutline(true);
            }
            int width = getWidth();
            int height = getHeight();
            float min = (Math.min(width, height) * this.mRoundPercent) / 2.0f;
            this.f2217d.set(0.0f, 0.0f, width, height);
            this.f2215b.reset();
            this.f2215b.addRoundRect(this.f2217d, min, min, Path.Direction.CW);
        } else if (Build.VERSION.SDK_INT >= 21) {
            setClipToOutline(false);
        }
        if (!z || Build.VERSION.SDK_INT < 21) {
            return;
        }
        invalidateOutline();
    }

    public void setScaleFromTextSize(float f2) {
        this.mBaseTextSize = f2;
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence.toString();
        invalidate();
    }

    public void setTextBackgroundPanX(float f2) {
        this.f2224k = f2;
        updateShaderMatrix();
        invalidate();
    }

    public void setTextBackgroundPanY(float f2) {
        this.f2225l = f2;
        updateShaderMatrix();
        invalidate();
    }

    public void setTextBackgroundRotate(float f2) {
        this.f2227n = f2;
        updateShaderMatrix();
        invalidate();
    }

    public void setTextBackgroundZoom(float f2) {
        this.f2226m = f2;
        updateShaderMatrix();
        invalidate();
    }

    public void setTextFillColor(int i2) {
        this.mTextFillColor = i2;
        invalidate();
    }

    public void setTextOutlineColor(int i2) {
        this.mTextOutlineColor = i2;
        this.mUseOutline = true;
        invalidate();
    }

    public void setTextOutlineThickness(float f2) {
        this.mTextOutlineThickness = f2;
        this.mUseOutline = true;
        if (Float.isNaN(f2)) {
            this.mTextOutlineThickness = 1.0f;
            this.mUseOutline = false;
        }
        invalidate();
    }

    public void setTextPanX(float f2) {
        this.mTextPanX = f2;
        invalidate();
    }

    public void setTextPanY(float f2) {
        this.mTextPanY = f2;
        invalidate();
    }

    public void setTextSize(float f2) {
        this.mTextSize = f2;
        StringBuilder sb = new StringBuilder();
        sb.append(Debug.getLoc());
        sb.append("  ");
        sb.append(f2);
        sb.append(" / ");
        sb.append(this.mBaseTextSize);
        TextPaint textPaint = this.f2214a;
        if (!Float.isNaN(this.mBaseTextSize)) {
            f2 = this.mBaseTextSize;
        }
        textPaint.setTextSize(f2);
        d(Float.isNaN(this.mBaseTextSize) ? 1.0f : this.mTextSize / this.mBaseTextSize);
        requestLayout();
        invalidate();
    }

    public void setTextureHeight(float f2) {
        this.mTextureHeight = f2;
        updateShaderMatrix();
        invalidate();
    }

    public void setTextureWidth(float f2) {
        this.mTextureWidth = f2;
        updateShaderMatrix();
        invalidate();
    }

    public void setTypeface(Typeface typeface) {
        if (this.f2214a.getTypeface() != typeface) {
            this.f2214a.setTypeface(typeface);
            if (this.mLayout != null) {
                this.mLayout = null;
                requestLayout();
                invalidate();
            }
        }
    }
}
