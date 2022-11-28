package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.constraintlayout.motion.utils.ViewSpline;
import java.util.HashMap;
import java.util.HashSet;
/* loaded from: classes.dex */
public abstract class Key {
    public static final String ALPHA = "alpha";
    public static final String CURVEFIT = "curveFit";
    public static final String CUSTOM = "CUSTOM";
    public static final String ELEVATION = "elevation";
    public static final String MOTIONPROGRESS = "motionProgress";
    public static final String PIVOT_X = "transformPivotX";
    public static final String PIVOT_Y = "transformPivotY";
    public static final String PROGRESS = "progress";
    public static final String ROTATION = "rotation";
    public static final String ROTATION_X = "rotationX";
    public static final String ROTATION_Y = "rotationY";
    public static final String SCALE_X = "scaleX";
    public static final String SCALE_Y = "scaleY";
    public static final String TRANSITIONEASING = "transitionEasing";
    public static final String TRANSITION_PATH_ROTATE = "transitionPathRotate";
    public static final String TRANSLATION_X = "translationX";
    public static final String TRANSLATION_Y = "translationY";
    public static final String TRANSLATION_Z = "translationZ";
    public static int UNSET = -1;
    public static final String VISIBILITY = "visibility";
    public static final String WAVE_OFFSET = "waveOffset";
    public static final String WAVE_PERIOD = "wavePeriod";
    public static final String WAVE_PHASE = "wavePhase";
    public static final String WAVE_VARIES_BY = "waveVariesBy";

    /* renamed from: a  reason: collision with root package name */
    int f2041a;

    /* renamed from: b  reason: collision with root package name */
    int f2042b;

    /* renamed from: c  reason: collision with root package name */
    String f2043c;

    /* renamed from: d  reason: collision with root package name */
    protected int f2044d;

    /* renamed from: e  reason: collision with root package name */
    HashMap f2045e;

    public Key() {
        int i2 = UNSET;
        this.f2041a = i2;
        this.f2042b = i2;
        this.f2043c = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a(String str) {
        String str2 = this.f2043c;
        if (str2 == null || str == null) {
            return false;
        }
        return str.matches(str2);
    }

    public abstract void addValues(HashMap<String, ViewSpline> hashMap);

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b(Object obj) {
        return obj instanceof Boolean ? ((Boolean) obj).booleanValue() : Boolean.parseBoolean(obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float c(Object obj) {
        return obj instanceof Float ? ((Float) obj).floatValue() : Float.parseFloat(obj.toString());
    }

    /* renamed from: clone */
    public abstract Key m4clone();

    public Key copy(Key key) {
        this.f2041a = key.f2041a;
        this.f2042b = key.f2042b;
        this.f2043c = key.f2043c;
        this.f2044d = key.f2044d;
        this.f2045e = key.f2045e;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int d(Object obj) {
        return obj instanceof Integer ? ((Integer) obj).intValue() : Integer.parseInt(obj.toString());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void getAttributeNames(HashSet hashSet);

    public int getFramePosition() {
        return this.f2041a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void load(Context context, AttributeSet attributeSet);

    public void setFramePosition(int i2) {
        this.f2041a = i2;
    }

    public void setInterpolation(HashMap<String, Integer> hashMap) {
    }

    public abstract void setValue(String str, Object obj);

    public Key setViewId(int i2) {
        this.f2042b = i2;
        return this;
    }
}
