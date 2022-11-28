package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.state.Transition;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.HashMap;
import java.util.Set;
import org.apache.http.message.TokenParser;
/* loaded from: classes.dex */
public class WidgetFrame {
    private static final boolean OLD_SYSTEM = true;
    public static float phone_orientation = Float.NaN;
    public float alpha;
    public int bottom;
    public float interpolatedPos;
    public int left;
    public final HashMap<String, CustomVariable> mCustom;
    public String name;
    public float pivotX;
    public float pivotY;
    public int right;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float scaleX;
    public float scaleY;
    public int top;
    public float translationX;
    public float translationY;
    public float translationZ;
    public int visibility;
    public ConstraintWidget widget;

    public WidgetFrame() {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = Float.NaN;
        this.pivotY = Float.NaN;
        this.rotationX = Float.NaN;
        this.rotationY = Float.NaN;
        this.rotationZ = Float.NaN;
        this.translationX = Float.NaN;
        this.translationY = Float.NaN;
        this.translationZ = Float.NaN;
        this.scaleX = Float.NaN;
        this.scaleY = Float.NaN;
        this.alpha = Float.NaN;
        this.interpolatedPos = Float.NaN;
        this.visibility = 0;
        this.mCustom = new HashMap<>();
        this.name = null;
    }

    public WidgetFrame(WidgetFrame widgetFrame) {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = Float.NaN;
        this.pivotY = Float.NaN;
        this.rotationX = Float.NaN;
        this.rotationY = Float.NaN;
        this.rotationZ = Float.NaN;
        this.translationX = Float.NaN;
        this.translationY = Float.NaN;
        this.translationZ = Float.NaN;
        this.scaleX = Float.NaN;
        this.scaleY = Float.NaN;
        this.alpha = Float.NaN;
        this.interpolatedPos = Float.NaN;
        this.visibility = 0;
        this.mCustom = new HashMap<>();
        this.name = null;
        this.widget = widgetFrame.widget;
        this.left = widgetFrame.left;
        this.top = widgetFrame.top;
        this.right = widgetFrame.right;
        this.bottom = widgetFrame.bottom;
        updateAttributes(widgetFrame);
    }

    public WidgetFrame(ConstraintWidget constraintWidget) {
        this.widget = null;
        this.left = 0;
        this.top = 0;
        this.right = 0;
        this.bottom = 0;
        this.pivotX = Float.NaN;
        this.pivotY = Float.NaN;
        this.rotationX = Float.NaN;
        this.rotationY = Float.NaN;
        this.rotationZ = Float.NaN;
        this.translationX = Float.NaN;
        this.translationY = Float.NaN;
        this.translationZ = Float.NaN;
        this.scaleX = Float.NaN;
        this.scaleY = Float.NaN;
        this.alpha = Float.NaN;
        this.interpolatedPos = Float.NaN;
        this.visibility = 0;
        this.mCustom = new HashMap<>();
        this.name = null;
        this.widget = constraintWidget;
    }

    private static void add(StringBuilder sb, String str, float f2) {
        if (Float.isNaN(f2)) {
            return;
        }
        sb.append(str);
        sb.append(": ");
        sb.append(f2);
        sb.append(",\n");
    }

    private static void add(StringBuilder sb, String str, int i2) {
        sb.append(str);
        sb.append(": ");
        sb.append(i2);
        sb.append(",\n");
    }

    private static float interpolate(float f2, float f3, float f4, float f5) {
        boolean isNaN = Float.isNaN(f2);
        boolean isNaN2 = Float.isNaN(f3);
        if (isNaN && isNaN2) {
            return Float.NaN;
        }
        if (isNaN) {
            f2 = f4;
        }
        if (isNaN2) {
            f3 = f4;
        }
        return f2 + (f5 * (f3 - f2));
    }

    public static void interpolate(int i2, int i3, WidgetFrame widgetFrame, WidgetFrame widgetFrame2, WidgetFrame widgetFrame3, Transition transition, float f2) {
        int i4;
        float f3;
        int i5;
        float f4;
        float f5;
        int i6;
        float f6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        float f7 = 100.0f * f2;
        int i12 = (int) f7;
        int i13 = widgetFrame2.left;
        int i14 = widgetFrame2.top;
        int i15 = widgetFrame3.left;
        int i16 = widgetFrame3.top;
        int i17 = widgetFrame2.right - i13;
        int i18 = widgetFrame2.bottom - i14;
        int i19 = widgetFrame3.right - i15;
        int i20 = widgetFrame3.bottom - i16;
        float f8 = widgetFrame2.alpha;
        float f9 = widgetFrame3.alpha;
        if (widgetFrame2.visibility == 8) {
            i13 = (int) (i13 - (i19 / 2.0f));
            i14 = (int) (i14 - (i20 / 2.0f));
            if (Float.isNaN(f8)) {
                i5 = i20;
                i4 = i19;
                f3 = 0.0f;
            } else {
                f3 = f8;
                i4 = i19;
                i5 = i20;
            }
        } else {
            i4 = i17;
            f3 = f8;
            i5 = i18;
        }
        if (widgetFrame3.visibility == 8) {
            i15 = (int) (i15 - (i4 / 2.0f));
            i16 = (int) (i16 - (i5 / 2.0f));
            i19 = i4;
            i20 = i5;
            if (Float.isNaN(f9)) {
                f9 = 0.0f;
            }
        }
        if (Float.isNaN(f3) && !Float.isNaN(f9)) {
            f3 = 1.0f;
        }
        if (!Float.isNaN(f3) && Float.isNaN(f9)) {
            f9 = 1.0f;
        }
        if (widgetFrame2.visibility == 4) {
            f5 = f9;
            f4 = 0.0f;
        } else {
            f4 = f3;
            f5 = f9;
        }
        float f10 = widgetFrame3.visibility == 4 ? 0.0f : f5;
        if (widgetFrame.widget == null || !transition.hasPositionKeyframes()) {
            i6 = i13;
            f6 = f2;
        } else {
            Transition.KeyPosition findPreviousPosition = transition.findPreviousPosition(widgetFrame.widget.stringId, i12);
            i6 = i13;
            Transition.KeyPosition findNextPosition = transition.findNextPosition(widgetFrame.widget.stringId, i12);
            if (findPreviousPosition == findNextPosition) {
                findNextPosition = null;
            }
            if (findPreviousPosition != null) {
                i6 = (int) (findPreviousPosition.f1907b * i2);
                i10 = i3;
                i14 = (int) (findPreviousPosition.f1908c * i10);
                i9 = findPreviousPosition.f1906a;
                i8 = i2;
            } else {
                i8 = i2;
                i9 = 0;
                i10 = i3;
            }
            if (findNextPosition != null) {
                int i21 = (int) (findNextPosition.f1907b * i8);
                int i22 = (int) (findNextPosition.f1908c * i10);
                i11 = findNextPosition.f1906a;
                i15 = i21;
                i16 = i22;
            } else {
                i11 = 100;
            }
            f6 = (f7 - i9) / (i11 - i9);
        }
        widgetFrame.widget = widgetFrame2.widget;
        int i23 = (int) (i6 + ((i15 - i7) * f6));
        widgetFrame.left = i23;
        int i24 = (int) (i14 + (f6 * (i16 - i14)));
        widgetFrame.top = i24;
        float f11 = 1.0f - f2;
        widgetFrame.right = i23 + ((int) ((i4 * f11) + (i19 * f2)));
        widgetFrame.bottom = i24 + ((int) ((f11 * i5) + (i20 * f2)));
        widgetFrame.pivotX = interpolate(widgetFrame2.pivotX, widgetFrame3.pivotX, 0.5f, f2);
        widgetFrame.pivotY = interpolate(widgetFrame2.pivotY, widgetFrame3.pivotY, 0.5f, f2);
        widgetFrame.rotationX = interpolate(widgetFrame2.rotationX, widgetFrame3.rotationX, 0.0f, f2);
        widgetFrame.rotationY = interpolate(widgetFrame2.rotationY, widgetFrame3.rotationY, 0.0f, f2);
        widgetFrame.rotationZ = interpolate(widgetFrame2.rotationZ, widgetFrame3.rotationZ, 0.0f, f2);
        widgetFrame.scaleX = interpolate(widgetFrame2.scaleX, widgetFrame3.scaleX, 1.0f, f2);
        widgetFrame.scaleY = interpolate(widgetFrame2.scaleY, widgetFrame3.scaleY, 1.0f, f2);
        widgetFrame.translationX = interpolate(widgetFrame2.translationX, widgetFrame3.translationX, 0.0f, f2);
        widgetFrame.translationY = interpolate(widgetFrame2.translationY, widgetFrame3.translationY, 0.0f, f2);
        widgetFrame.translationZ = interpolate(widgetFrame2.translationZ, widgetFrame3.translationZ, 0.0f, f2);
        widgetFrame.alpha = interpolate(f4, f10, 1.0f, f2);
    }

    private void serializeAnchor(StringBuilder sb, ConstraintAnchor.Type type) {
        ConstraintAnchor anchor = this.widget.getAnchor(type);
        if (anchor == null || anchor.mTarget == null) {
            return;
        }
        sb.append("Anchor");
        sb.append(type.name());
        sb.append(": ['");
        String str = anchor.mTarget.getOwner().stringId;
        if (str == null) {
            str = "#PARENT";
        }
        sb.append(str);
        sb.append("', '");
        sb.append(anchor.mTarget.getType().name());
        sb.append("', '");
        sb.append(anchor.mMargin);
        sb.append("'],\n");
    }

    void a(CLElement cLElement) {
        CLObject cLObject = (CLObject) cLElement;
        int size = cLObject.size();
        for (int i2 = 0; i2 < size; i2++) {
            CLKey cLKey = (CLKey) cLObject.get(i2);
            cLKey.content();
            CLElement value = cLKey.getValue();
            String content = value.content();
            if (content.matches("#[0-9a-fA-F]+")) {
                setCustomAttribute(cLKey.content(), TypedValues.Custom.TYPE_COLOR, Integer.parseInt(content.substring(1), 16));
            } else {
                boolean z = value instanceof CLNumber;
                String content2 = cLKey.content();
                if (z) {
                    setCustomAttribute(content2, TypedValues.Custom.TYPE_FLOAT, value.getFloat());
                } else {
                    setCustomAttribute(content2, TypedValues.Custom.TYPE_STRING, content);
                }
            }
        }
    }

    public void addCustomColor(String str, int i2) {
        setCustomAttribute(str, TypedValues.Custom.TYPE_COLOR, i2);
    }

    public void addCustomFloat(String str, float f2) {
        setCustomAttribute(str, TypedValues.Custom.TYPE_FLOAT, f2);
    }

    public float centerX() {
        int i2 = this.left;
        return i2 + ((this.right - i2) / 2.0f);
    }

    public float centerY() {
        int i2 = this.top;
        return i2 + ((this.bottom - i2) / 2.0f);
    }

    public CustomVariable getCustomAttribute(String str) {
        return this.mCustom.get(str);
    }

    public Set<String> getCustomAttributeNames() {
        return this.mCustom.keySet();
    }

    public int getCustomColor(String str) {
        if (this.mCustom.containsKey(str)) {
            return this.mCustom.get(str).getColorValue();
        }
        return -21880;
    }

    public float getCustomFloat(String str) {
        if (this.mCustom.containsKey(str)) {
            return this.mCustom.get(str).getFloatValue();
        }
        return Float.NaN;
    }

    public int height() {
        return Math.max(0, this.bottom - this.top);
    }

    public boolean isDefaultTransform() {
        return Float.isNaN(this.rotationX) && Float.isNaN(this.rotationY) && Float.isNaN(this.rotationZ) && Float.isNaN(this.translationX) && Float.isNaN(this.translationY) && Float.isNaN(this.translationZ) && Float.isNaN(this.scaleX) && Float.isNaN(this.scaleY) && Float.isNaN(this.alpha);
    }

    public StringBuilder serialize(StringBuilder sb) {
        return serialize(sb, false);
    }

    public StringBuilder serialize(StringBuilder sb, boolean z) {
        String colorString;
        sb.append("{\n");
        add(sb, "left", this.left);
        add(sb, "top", this.top);
        add(sb, "right", this.right);
        add(sb, "bottom", this.bottom);
        add(sb, "pivotX", this.pivotX);
        add(sb, "pivotY", this.pivotY);
        add(sb, "rotationX", this.rotationX);
        add(sb, "rotationY", this.rotationY);
        add(sb, "rotationZ", this.rotationZ);
        add(sb, "translationX", this.translationX);
        add(sb, "translationY", this.translationY);
        add(sb, "translationZ", this.translationZ);
        add(sb, "scaleX", this.scaleX);
        add(sb, "scaleY", this.scaleY);
        add(sb, "alpha", this.alpha);
        add(sb, "visibility", this.left);
        add(sb, "interpolatedPos", this.interpolatedPos);
        if (this.widget != null) {
            for (ConstraintAnchor.Type type : ConstraintAnchor.Type.values()) {
                serializeAnchor(sb, type);
            }
        }
        if (z) {
            add(sb, "phone_orientation", phone_orientation);
        }
        if (z) {
            add(sb, "phone_orientation", phone_orientation);
        }
        if (this.mCustom.size() != 0) {
            sb.append("custom : {\n");
            for (String str : this.mCustom.keySet()) {
                CustomVariable customVariable = this.mCustom.get(str);
                sb.append(str);
                sb.append(": ");
                switch (customVariable.getType()) {
                    case TypedValues.Custom.TYPE_INT /* 900 */:
                        sb.append(customVariable.getIntegerValue());
                        sb.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_FLOAT /* 901 */:
                    case TypedValues.Custom.TYPE_DIMENSION /* 905 */:
                        sb.append(customVariable.getFloatValue());
                        sb.append(",\n");
                        break;
                    case TypedValues.Custom.TYPE_COLOR /* 902 */:
                        sb.append("'");
                        colorString = CustomVariable.colorString(customVariable.getIntegerValue());
                        sb.append(colorString);
                        sb.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_STRING /* 903 */:
                        sb.append("'");
                        colorString = customVariable.getStringValue();
                        sb.append(colorString);
                        sb.append("',\n");
                        break;
                    case TypedValues.Custom.TYPE_BOOLEAN /* 904 */:
                        sb.append("'");
                        sb.append(customVariable.getBooleanValue());
                        sb.append("',\n");
                        break;
                }
            }
            sb.append("}\n");
        }
        sb.append("}\n");
        return sb;
    }

    public void setCustomAttribute(String str, int i2, float f2) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setFloatValue(f2);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i2, f2));
        }
    }

    public void setCustomAttribute(String str, int i2, int i3) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setIntValue(i3);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i2, i3));
        }
    }

    public void setCustomAttribute(String str, int i2, String str2) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setStringValue(str2);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i2, str2));
        }
    }

    public void setCustomAttribute(String str, int i2, boolean z) {
        if (this.mCustom.containsKey(str)) {
            this.mCustom.get(str).setBooleanValue(z);
        } else {
            this.mCustom.put(str, new CustomVariable(str, i2, z));
        }
    }

    public boolean setValue(String str, CLElement cLElement) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1881940865:
                if (str.equals("phone_orientation")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1383228885:
                if (str.equals("bottom")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1349088399:
                if (str.equals(AppConstants.GEO_FENCE_MODE_CUSTOM)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1249320806:
                if (str.equals("rotationX")) {
                    c2 = 3;
                    break;
                }
                break;
            case -1249320805:
                if (str.equals("rotationY")) {
                    c2 = 4;
                    break;
                }
                break;
            case -1249320804:
                if (str.equals("rotationZ")) {
                    c2 = 5;
                    break;
                }
                break;
            case -1225497657:
                if (str.equals("translationX")) {
                    c2 = 6;
                    break;
                }
                break;
            case -1225497656:
                if (str.equals("translationY")) {
                    c2 = 7;
                    break;
                }
                break;
            case -1225497655:
                if (str.equals("translationZ")) {
                    c2 = '\b';
                    break;
                }
                break;
            case -987906986:
                if (str.equals("pivotX")) {
                    c2 = '\t';
                    break;
                }
                break;
            case -987906985:
                if (str.equals("pivotY")) {
                    c2 = '\n';
                    break;
                }
                break;
            case -908189618:
                if (str.equals("scaleX")) {
                    c2 = 11;
                    break;
                }
                break;
            case -908189617:
                if (str.equals("scaleY")) {
                    c2 = '\f';
                    break;
                }
                break;
            case 115029:
                if (str.equals("top")) {
                    c2 = TokenParser.CR;
                    break;
                }
                break;
            case 3317767:
                if (str.equals("left")) {
                    c2 = 14;
                    break;
                }
                break;
            case 92909918:
                if (str.equals("alpha")) {
                    c2 = 15;
                    break;
                }
                break;
            case 108511772:
                if (str.equals("right")) {
                    c2 = 16;
                    break;
                }
                break;
            case 642850769:
                if (str.equals("interpolatedPos")) {
                    c2 = 17;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                phone_orientation = cLElement.getFloat();
                break;
            case 1:
                this.bottom = cLElement.getInt();
                break;
            case 2:
                a(cLElement);
                break;
            case 3:
                this.rotationX = cLElement.getFloat();
                break;
            case 4:
                this.rotationY = cLElement.getFloat();
                break;
            case 5:
                this.rotationZ = cLElement.getFloat();
                break;
            case 6:
                this.translationX = cLElement.getFloat();
                break;
            case 7:
                this.translationY = cLElement.getFloat();
                break;
            case '\b':
                this.translationZ = cLElement.getFloat();
                break;
            case '\t':
                this.pivotX = cLElement.getFloat();
                break;
            case '\n':
                this.pivotY = cLElement.getFloat();
                break;
            case 11:
                this.scaleX = cLElement.getFloat();
                break;
            case '\f':
                this.scaleY = cLElement.getFloat();
                break;
            case '\r':
                this.top = cLElement.getInt();
                break;
            case 14:
                this.left = cLElement.getInt();
                break;
            case 15:
                this.alpha = cLElement.getFloat();
                break;
            case 16:
                this.right = cLElement.getInt();
                break;
            case 17:
                this.interpolatedPos = cLElement.getFloat();
                break;
            default:
                return false;
        }
        return true;
    }

    public WidgetFrame update() {
        ConstraintWidget constraintWidget = this.widget;
        if (constraintWidget != null) {
            this.left = constraintWidget.getLeft();
            this.top = this.widget.getTop();
            this.right = this.widget.getRight();
            this.bottom = this.widget.getBottom();
            updateAttributes(this.widget.frame);
        }
        return this;
    }

    public WidgetFrame update(ConstraintWidget constraintWidget) {
        if (constraintWidget == null) {
            return this;
        }
        this.widget = constraintWidget;
        update();
        return this;
    }

    public void updateAttributes(WidgetFrame widgetFrame) {
        this.pivotX = widgetFrame.pivotX;
        this.pivotY = widgetFrame.pivotY;
        this.rotationX = widgetFrame.rotationX;
        this.rotationY = widgetFrame.rotationY;
        this.rotationZ = widgetFrame.rotationZ;
        this.translationX = widgetFrame.translationX;
        this.translationY = widgetFrame.translationY;
        this.translationZ = widgetFrame.translationZ;
        this.scaleX = widgetFrame.scaleX;
        this.scaleY = widgetFrame.scaleY;
        this.alpha = widgetFrame.alpha;
        this.visibility = widgetFrame.visibility;
        this.mCustom.clear();
        for (CustomVariable customVariable : widgetFrame.mCustom.values()) {
            this.mCustom.put(customVariable.getName(), customVariable.copy());
        }
    }

    public int width() {
        return Math.max(0, this.right - this.left);
    }
}
