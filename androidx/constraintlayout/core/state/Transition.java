package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.client.config.CookieSpecs;
/* loaded from: classes.dex */
public class Transition {
    public static final int END = 1;
    public static final int INTERPOLATED = 2;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    private static final int SPLINE_STRING = -1;
    public static final int START = 0;
    private HashMap<String, WidgetState> state = new HashMap<>();

    /* renamed from: a  reason: collision with root package name */
    HashMap f1905a = new HashMap();
    private int pathMotionArc = -1;
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private int mAutoTransition = 0;
    private int mDuration = 400;
    private float mStagger = 0.0f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class KeyPosition {

        /* renamed from: a  reason: collision with root package name */
        int f1906a;

        /* renamed from: b  reason: collision with root package name */
        float f1907b;

        /* renamed from: c  reason: collision with root package name */
        float f1908c;

        public KeyPosition(String str, int i2, int i3, float f2, float f3) {
            this.f1906a = i2;
            this.f1907b = f2;
            this.f1908c = f3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class WidgetState {

        /* renamed from: d  reason: collision with root package name */
        Motion f1912d;

        /* renamed from: h  reason: collision with root package name */
        KeyCache f1916h = new KeyCache();

        /* renamed from: a  reason: collision with root package name */
        WidgetFrame f1909a = new WidgetFrame();

        /* renamed from: b  reason: collision with root package name */
        WidgetFrame f1910b = new WidgetFrame();

        /* renamed from: c  reason: collision with root package name */
        WidgetFrame f1911c = new WidgetFrame();

        /* renamed from: e  reason: collision with root package name */
        MotionWidget f1913e = new MotionWidget(this.f1909a);

        /* renamed from: f  reason: collision with root package name */
        MotionWidget f1914f = new MotionWidget(this.f1910b);

        /* renamed from: g  reason: collision with root package name */
        MotionWidget f1915g = new MotionWidget(this.f1911c);

        public WidgetState() {
            Motion motion = new Motion(this.f1913e);
            this.f1912d = motion;
            motion.setStart(this.f1913e);
            this.f1912d.setEnd(this.f1914f);
        }

        public WidgetFrame getFrame(int i2) {
            return i2 == 0 ? this.f1909a : i2 == 1 ? this.f1910b : this.f1911c;
        }

        public void interpolate(int i2, int i3, float f2, Transition transition) {
            this.f1912d.setup(i2, i3, 1.0f, System.nanoTime());
            WidgetFrame.interpolate(i2, i3, this.f1911c, this.f1909a, this.f1910b, transition, f2);
            this.f1911c.interpolatedPos = f2;
            this.f1912d.interpolate(this.f1915g, f2, System.nanoTime(), this.f1916h);
        }

        public void setKeyAttribute(TypedBundle typedBundle) {
            MotionKeyAttributes motionKeyAttributes = new MotionKeyAttributes();
            typedBundle.applyDelta(motionKeyAttributes);
            this.f1912d.addKey(motionKeyAttributes);
        }

        public void setKeyCycle(TypedBundle typedBundle) {
            MotionKeyCycle motionKeyCycle = new MotionKeyCycle();
            typedBundle.applyDelta(motionKeyCycle);
            this.f1912d.addKey(motionKeyCycle);
        }

        public void setKeyPosition(TypedBundle typedBundle) {
            MotionKeyPosition motionKeyPosition = new MotionKeyPosition();
            typedBundle.applyDelta(motionKeyPosition);
            this.f1912d.addKey(motionKeyPosition);
        }

        public void update(ConstraintWidget constraintWidget, int i2) {
            if (i2 == 0) {
                this.f1909a.update(constraintWidget);
                this.f1912d.setStart(this.f1913e);
            } else if (i2 == 1) {
                this.f1910b.update(constraintWidget);
                this.f1912d.setEnd(this.f1914f);
            }
        }
    }

    public static Interpolator getInterpolator(int i2, final String str) {
        switch (i2) {
            case -1:
                return new Interpolator() { // from class: androidx.constraintlayout.core.state.a
                    @Override // androidx.constraintlayout.core.state.Interpolator
                    public final float getInterpolation(float f2) {
                        float lambda$getInterpolator$0;
                        lambda$getInterpolator$0 = Transition.lambda$getInterpolator$0(str, f2);
                        return lambda$getInterpolator$0;
                    }
                };
            case 0:
                return d.f1920a;
            case 1:
                return e.f1921a;
            case 2:
                return b.f1918a;
            case 3:
                return c.f1919a;
            case 4:
                return f.f1922a;
            case 5:
                return h.f1924a;
            case 6:
                return g.f1923a;
            default:
                return null;
        }
    }

    private WidgetState getWidgetState(String str) {
        return this.state.get(str);
    }

    private WidgetState getWidgetState(String str, ConstraintWidget constraintWidget, int i2) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            widgetState = new WidgetState();
            int i3 = this.pathMotionArc;
            if (i3 != -1) {
                widgetState.f1912d.setPathMotionArc(i3);
            }
            this.state.put(str, widgetState);
            if (constraintWidget != null) {
                widgetState.update(constraintWidget, i2);
            }
        }
        return widgetState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$0(String str, float f2) {
        return (float) Easing.getInterpolator(str).get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$1(float f2) {
        return (float) Easing.getInterpolator(CookieSpecs.STANDARD).get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$2(float f2) {
        return (float) Easing.getInterpolator("accelerate").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$3(float f2) {
        return (float) Easing.getInterpolator("decelerate").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$4(float f2) {
        return (float) Easing.getInterpolator("linear").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$5(float f2) {
        return (float) Easing.getInterpolator("anticipate").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$6(float f2) {
        return (float) Easing.getInterpolator("overshoot").get(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ float lambda$getInterpolator$7(float f2) {
        return (float) Easing.getInterpolator("spline(0.0, 0.2, 0.4, 0.6, 0.8 ,1.0, 0.8, 1.0, 0.9, 1.0)").get(f2);
    }

    public void addCustomColor(int i2, String str, String str2, int i3) {
        getWidgetState(str, null, i2).getFrame(i2).addCustomColor(str2, i3);
    }

    public void addCustomFloat(int i2, String str, String str2, float f2) {
        getWidgetState(str, null, i2).getFrame(i2).addCustomFloat(str2, f2);
    }

    public void addKeyAttribute(String str, TypedBundle typedBundle) {
        getWidgetState(str, null, 0).setKeyAttribute(typedBundle);
    }

    public void addKeyCycle(String str, TypedBundle typedBundle) {
        getWidgetState(str, null, 0).setKeyCycle(typedBundle);
    }

    public void addKeyPosition(String str, int i2, int i3, float f2, float f3) {
        TypedBundle typedBundle = new TypedBundle();
        typedBundle.add(TypedValues.Position.TYPE_POSITION_TYPE, 2);
        typedBundle.add(100, i2);
        typedBundle.add(TypedValues.Position.TYPE_PERCENT_X, f2);
        typedBundle.add(507, f3);
        getWidgetState(str, null, 0).setKeyPosition(typedBundle);
        KeyPosition keyPosition = new KeyPosition(str, i2, i3, f2, f3);
        HashMap hashMap = (HashMap) this.f1905a.get(Integer.valueOf(i2));
        if (hashMap == null) {
            hashMap = new HashMap();
            this.f1905a.put(Integer.valueOf(i2), hashMap);
        }
        hashMap.put(str, keyPosition);
    }

    public void addKeyPosition(String str, TypedBundle typedBundle) {
        getWidgetState(str, null, 0).setKeyPosition(typedBundle);
    }

    public void clear() {
        this.state.clear();
    }

    public boolean contains(String str) {
        return this.state.containsKey(str);
    }

    public void fillKeyPositions(WidgetFrame widgetFrame, float[] fArr, float[] fArr2, float[] fArr3) {
        KeyPosition keyPosition;
        int i2 = 0;
        for (int i3 = 0; i3 <= 100; i3++) {
            HashMap hashMap = (HashMap) this.f1905a.get(Integer.valueOf(i3));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(widgetFrame.widget.stringId)) != null) {
                fArr[i2] = keyPosition.f1907b;
                fArr2[i2] = keyPosition.f1908c;
                fArr3[i2] = keyPosition.f1906a;
                i2++;
            }
        }
    }

    public KeyPosition findNextPosition(String str, int i2) {
        KeyPosition keyPosition;
        while (i2 <= 100) {
            HashMap hashMap = (HashMap) this.f1905a.get(Integer.valueOf(i2));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(str)) != null) {
                return keyPosition;
            }
            i2++;
        }
        return null;
    }

    public KeyPosition findPreviousPosition(String str, int i2) {
        KeyPosition keyPosition;
        while (i2 >= 0) {
            HashMap hashMap = (HashMap) this.f1905a.get(Integer.valueOf(i2));
            if (hashMap != null && (keyPosition = (KeyPosition) hashMap.get(str)) != null) {
                return keyPosition;
            }
            i2--;
        }
        return null;
    }

    public int getAutoTransition() {
        return this.mAutoTransition;
    }

    public WidgetFrame getEnd(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, null, 1).f1910b;
    }

    public WidgetFrame getEnd(String str) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.f1910b;
    }

    public WidgetFrame getInterpolated(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, null, 2).f1911c;
    }

    public WidgetFrame getInterpolated(String str) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.f1911c;
    }

    public Interpolator getInterpolator() {
        return getInterpolator(this.mDefaultInterpolator, this.mDefaultInterpolatorString);
    }

    public int getKeyFrames(String str, float[] fArr, int[] iArr, int[] iArr2) {
        return this.state.get(str).f1912d.buildKeyFrames(fArr, iArr, iArr2);
    }

    public Motion getMotion(String str) {
        return getWidgetState(str, null, 0).f1912d;
    }

    public int getNumberKeyPositions(WidgetFrame widgetFrame) {
        int i2 = 0;
        for (int i3 = 0; i3 <= 100; i3++) {
            HashMap hashMap = (HashMap) this.f1905a.get(Integer.valueOf(i3));
            if (hashMap != null && ((KeyPosition) hashMap.get(widgetFrame.widget.stringId)) != null) {
                i2++;
            }
        }
        return i2;
    }

    public float[] getPath(String str) {
        float[] fArr = new float[124];
        this.state.get(str).f1912d.buildPath(fArr, 62);
        return fArr;
    }

    public WidgetFrame getStart(ConstraintWidget constraintWidget) {
        return getWidgetState(constraintWidget.stringId, null, 0).f1909a;
    }

    public WidgetFrame getStart(String str) {
        WidgetState widgetState = this.state.get(str);
        if (widgetState == null) {
            return null;
        }
        return widgetState.f1909a;
    }

    public boolean hasPositionKeyframes() {
        return this.f1905a.size() > 0;
    }

    public void interpolate(int i2, int i3, float f2) {
        for (String str : this.state.keySet()) {
            this.state.get(str).interpolate(i2, i3, f2, this);
        }
    }

    public boolean isEmpty() {
        return this.state.isEmpty();
    }

    public void setTransitionProperties(TypedBundle typedBundle) {
        this.pathMotionArc = typedBundle.getInteger(509);
        this.mAutoTransition = typedBundle.getInteger(TypedValues.Transition.TYPE_AUTO_TRANSITION);
    }

    public void updateFrom(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        ArrayList<ConstraintWidget> children = constraintWidgetContainer.getChildren();
        int size = children.size();
        for (int i3 = 0; i3 < size; i3++) {
            ConstraintWidget constraintWidget = children.get(i3);
            getWidgetState(constraintWidget.stringId, null, i2).update(constraintWidget, i2);
        }
    }
}
