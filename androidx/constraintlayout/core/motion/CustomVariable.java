package androidx.constraintlayout.core.motion;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.ViewCompat;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
/* loaded from: classes.dex */
public class CustomVariable {
    private static final String TAG = "TransitionLayout";

    /* renamed from: a  reason: collision with root package name */
    String f1687a;

    /* renamed from: b  reason: collision with root package name */
    boolean f1688b;
    private float mFloatValue;
    private int mIntegerValue;
    private String mStringValue;
    private int mType;

    public CustomVariable(CustomVariable customVariable) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = customVariable.f1687a;
        this.mType = customVariable.mType;
        this.mIntegerValue = customVariable.mIntegerValue;
        this.mFloatValue = customVariable.mFloatValue;
        this.mStringValue = customVariable.mStringValue;
        this.f1688b = customVariable.f1688b;
    }

    public CustomVariable(CustomVariable customVariable, Object obj) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = customVariable.f1687a;
        this.mType = customVariable.mType;
        setValue(obj);
    }

    public CustomVariable(String str, int i2) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = str;
        this.mType = i2;
    }

    public CustomVariable(String str, int i2, float f2) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = str;
        this.mType = i2;
        this.mFloatValue = f2;
    }

    public CustomVariable(String str, int i2, int i3) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = str;
        this.mType = i2;
        if (i2 == 901) {
            this.mFloatValue = i3;
        } else {
            this.mIntegerValue = i3;
        }
    }

    public CustomVariable(String str, int i2, Object obj) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = str;
        this.mType = i2;
        setValue(obj);
    }

    public CustomVariable(String str, int i2, String str2) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = str;
        this.mType = i2;
        this.mStringValue = str2;
    }

    public CustomVariable(String str, int i2, boolean z) {
        this.mIntegerValue = Integer.MIN_VALUE;
        this.mFloatValue = Float.NaN;
        this.mStringValue = null;
        this.f1687a = str;
        this.mType = i2;
        this.f1688b = z;
    }

    private static int clamp(int i2) {
        int i3 = (i2 & (~(i2 >> 31))) - 255;
        return (i3 & (i3 >> 31)) + 255;
    }

    public static String colorString(int i2) {
        String str = "00000000" + Integer.toHexString(i2);
        return "#" + str.substring(str.length() - 8);
    }

    public static int hsvToRgb(float f2, float f3, float f4) {
        float f5 = f2 * 6.0f;
        int i2 = (int) f5;
        float f6 = f5 - i2;
        float f7 = f4 * 255.0f;
        int i3 = (int) (((1.0f - f3) * f7) + 0.5f);
        int i4 = (int) (((1.0f - (f6 * f3)) * f7) + 0.5f);
        int i5 = (int) (((1.0f - ((1.0f - f6) * f3)) * f7) + 0.5f);
        int i6 = (int) (f7 + 0.5f);
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                return 0;
                            }
                            return ((i6 << 16) + (i3 << 8) + i4) | ViewCompat.MEASURED_STATE_MASK;
                        }
                        return ((i5 << 16) + (i3 << 8) + i6) | ViewCompat.MEASURED_STATE_MASK;
                    }
                    return ((i3 << 16) + (i4 << 8) + i6) | ViewCompat.MEASURED_STATE_MASK;
                }
                return ((i3 << 16) + (i6 << 8) + i5) | ViewCompat.MEASURED_STATE_MASK;
            }
            return ((i4 << 16) + (i6 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
        }
        return ((i6 << 16) + (i5 << 8) + i3) | ViewCompat.MEASURED_STATE_MASK;
    }

    public static int rgbaTocColor(float f2, float f3, float f4, float f5) {
        int clamp = clamp((int) (f2 * 255.0f));
        int clamp2 = clamp((int) (f3 * 255.0f));
        return (clamp << 16) | (clamp((int) (f5 * 255.0f)) << 24) | (clamp2 << 8) | clamp((int) (f4 * 255.0f));
    }

    public void applyToWidget(MotionWidget motionWidget) {
        int i2 = this.mType;
        switch (i2) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, this.mIntegerValue);
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, this.mFloatValue);
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, this.mStringValue);
                return;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, this.f1688b);
                return;
            default:
                return;
        }
    }

    public CustomVariable copy() {
        return new CustomVariable(this);
    }

    public boolean diff(CustomVariable customVariable) {
        int i2;
        if (customVariable == null || (i2 = this.mType) != customVariable.mType) {
            return false;
        }
        switch (i2) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                return this.mIntegerValue == customVariable.mIntegerValue;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                return this.mFloatValue == customVariable.mFloatValue;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                return this.mIntegerValue == customVariable.mIntegerValue;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                return this.mIntegerValue == customVariable.mIntegerValue;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                return this.f1688b == customVariable.f1688b;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                return this.mFloatValue == customVariable.mFloatValue;
            default:
                return false;
        }
    }

    public boolean getBooleanValue() {
        return this.f1688b;
    }

    public int getColorValue() {
        return this.mIntegerValue;
    }

    public float getFloatValue() {
        return this.mFloatValue;
    }

    public int getIntegerValue() {
        return this.mIntegerValue;
    }

    public int getInterpolatedColor(float[] fArr) {
        int clamp = clamp((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f));
        int clamp2 = clamp((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f));
        return (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp << 16) | (clamp2 << 8) | clamp((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f));
    }

    public String getName() {
        return this.f1687a;
    }

    public String getStringValue() {
        return this.mStringValue;
    }

    public int getType() {
        return this.mType;
    }

    public float getValueToInterpolate() {
        switch (this.mType) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                return this.mIntegerValue;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                return this.mFloatValue;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                throw new RuntimeException("Cannot interpolate String");
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                return this.f1688b ? 1.0f : 0.0f;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                return this.mFloatValue;
            default:
                return Float.NaN;
        }
    }

    public void getValuesToInterpolate(float[] fArr) {
        switch (this.mType) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                fArr[0] = this.mIntegerValue;
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                fArr[0] = this.mFloatValue;
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                int i2 = this.mIntegerValue;
                float pow = (float) Math.pow(((i2 >> 16) & 255) / 255.0f, 2.2d);
                float pow2 = (float) Math.pow(((i2 >> 8) & 255) / 255.0f, 2.2d);
                fArr[0] = pow;
                fArr[1] = pow2;
                fArr[2] = (float) Math.pow((i2 & 255) / 255.0f, 2.2d);
                fArr[3] = ((i2 >> 24) & 255) / 255.0f;
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                fArr[0] = this.f1688b ? 1.0f : 0.0f;
                return;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                fArr[0] = this.mFloatValue;
                return;
            default:
                return;
        }
    }

    public boolean isContinuous() {
        int i2 = this.mType;
        return (i2 == 903 || i2 == 904 || i2 == 906) ? false : true;
    }

    public int numberOfInterpolatedValues() {
        return this.mType != 902 ? 1 : 4;
    }

    public void setBooleanValue(boolean z) {
        this.f1688b = z;
    }

    public void setFloatValue(float f2) {
        this.mFloatValue = f2;
    }

    public void setIntValue(int i2) {
        this.mIntegerValue = i2;
    }

    public void setInterpolatedValue(MotionWidget motionWidget, float[] fArr) {
        int i2 = this.mType;
        switch (i2) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, (int) fArr[0]);
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, fArr[0]);
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                int clamp = clamp((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f));
                int clamp2 = clamp((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f));
                motionWidget.setCustomAttribute(this.f1687a, this.mType, (clamp((int) (fArr[3] * 255.0f)) << 24) | (clamp << 16) | (clamp2 << 8) | clamp((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f)));
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                throw new RuntimeException("unable to interpolate " + this.f1687a);
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                motionWidget.setCustomAttribute(this.f1687a, i2, fArr[0] > 0.5f);
                return;
            default:
                return;
        }
    }

    public void setStringValue(String str) {
        this.mStringValue = str;
    }

    public void setValue(Object obj) {
        switch (this.mType) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                this.mIntegerValue = ((Integer) obj).intValue();
                return;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                this.mFloatValue = ((Float) obj).floatValue();
                return;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                this.mStringValue = (String) obj;
                return;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                this.f1688b = ((Boolean) obj).booleanValue();
                return;
            default:
                return;
        }
    }

    public void setValue(float[] fArr) {
        int i2;
        switch (this.mType) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
            case TypedValues.Custom.TYPE_REFERENCE /* 906 */:
                i2 = (int) fArr[0];
                break;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                this.mFloatValue = fArr[0];
                return;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                int hsvToRgb = hsvToRgb(fArr[0], fArr[1], fArr[2]);
                this.mIntegerValue = hsvToRgb;
                i2 = (clamp((int) (fArr[3] * 255.0f)) << 24) | (hsvToRgb & 16777215);
                break;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                this.f1688b = ((double) fArr[0]) > 0.5d;
                return;
            default:
                return;
        }
        this.mIntegerValue = i2;
    }

    public String toString() {
        StringBuilder sb;
        String colorString;
        String str = this.f1687a + AbstractJsonLexerKt.COLON;
        switch (this.mType) {
            case TypedValues.Custom.TYPE_INT /* 900 */:
                sb = new StringBuilder();
                sb.append(str);
                sb.append(this.mIntegerValue);
                break;
            case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                sb = new StringBuilder();
                sb.append(str);
                sb.append(this.mFloatValue);
                break;
            case TypedValues.Custom.TYPE_COLOR /* 902 */:
                sb = new StringBuilder();
                sb.append(str);
                colorString = colorString(this.mIntegerValue);
                sb.append(colorString);
                break;
            case TypedValues.Custom.TYPE_STRING /* 903 */:
                sb = new StringBuilder();
                sb.append(str);
                colorString = this.mStringValue;
                sb.append(colorString);
                break;
            case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                sb = new StringBuilder();
                sb.append(str);
                sb.append(Boolean.valueOf(this.f1688b));
                break;
            case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                sb = new StringBuilder();
                sb.append(str);
                sb.append(this.mFloatValue);
                break;
            default:
                sb = new StringBuilder();
                sb.append(str);
                colorString = "????";
                sb.append(colorString);
                break;
        }
        return sb.toString();
    }
}
