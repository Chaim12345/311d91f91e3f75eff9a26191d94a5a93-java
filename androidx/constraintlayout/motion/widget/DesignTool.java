package androidx.constraintlayout.motion.widget;

import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintSet;
import java.io.PrintStream;
import java.util.HashMap;
/* loaded from: classes.dex */
public class DesignTool implements ProxyInterface {
    private static final boolean DEBUG = false;
    private static final String TAG = "DesignTool";

    /* renamed from: a  reason: collision with root package name */
    static final HashMap f2039a;

    /* renamed from: b  reason: collision with root package name */
    static final HashMap f2040b;
    private final MotionLayout mMotionLayout;
    private MotionScene mSceneCache;
    private String mLastStartState = null;
    private String mLastEndState = null;
    private int mLastStartStateId = -1;
    private int mLastEndStateId = -1;

    static {
        HashMap hashMap = new HashMap();
        f2039a = hashMap;
        HashMap hashMap2 = new HashMap();
        f2040b = hashMap2;
        hashMap.put(Pair.create(4, 4), "layout_constraintBottom_toBottomOf");
        hashMap.put(Pair.create(4, 3), "layout_constraintBottom_toTopOf");
        hashMap.put(Pair.create(3, 4), "layout_constraintTop_toBottomOf");
        hashMap.put(Pair.create(3, 3), "layout_constraintTop_toTopOf");
        hashMap.put(Pair.create(6, 6), "layout_constraintStart_toStartOf");
        hashMap.put(Pair.create(6, 7), "layout_constraintStart_toEndOf");
        hashMap.put(Pair.create(7, 6), "layout_constraintEnd_toStartOf");
        hashMap.put(Pair.create(7, 7), "layout_constraintEnd_toEndOf");
        hashMap.put(Pair.create(1, 1), "layout_constraintLeft_toLeftOf");
        hashMap.put(Pair.create(1, 2), "layout_constraintLeft_toRightOf");
        hashMap.put(Pair.create(2, 2), "layout_constraintRight_toRightOf");
        hashMap.put(Pair.create(2, 1), "layout_constraintRight_toLeftOf");
        hashMap.put(Pair.create(5, 5), "layout_constraintBaseline_toBaselineOf");
        hashMap2.put("layout_constraintBottom_toBottomOf", "layout_marginBottom");
        hashMap2.put("layout_constraintBottom_toTopOf", "layout_marginBottom");
        hashMap2.put("layout_constraintTop_toBottomOf", "layout_marginTop");
        hashMap2.put("layout_constraintTop_toTopOf", "layout_marginTop");
        hashMap2.put("layout_constraintStart_toStartOf", "layout_marginStart");
        hashMap2.put("layout_constraintStart_toEndOf", "layout_marginStart");
        hashMap2.put("layout_constraintEnd_toStartOf", "layout_marginEnd");
        hashMap2.put("layout_constraintEnd_toEndOf", "layout_marginEnd");
        hashMap2.put("layout_constraintLeft_toLeftOf", "layout_marginLeft");
        hashMap2.put("layout_constraintLeft_toRightOf", "layout_marginLeft");
        hashMap2.put("layout_constraintRight_toRightOf", "layout_marginRight");
        hashMap2.put("layout_constraintRight_toLeftOf", "layout_marginRight");
    }

    public DesignTool(MotionLayout motionLayout) {
        this.mMotionLayout = motionLayout;
    }

    private static void Connect(int i2, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i3, int i4) {
        String str = (String) f2039a.get(Pair.create(Integer.valueOf(i3), Integer.valueOf(i4)));
        String str2 = hashMap.get(str);
        if (str2 != null) {
            String str3 = (String) f2040b.get(str);
            int GetPxFromDp = str3 != null ? GetPxFromDp(i2, hashMap.get(str3)) : 0;
            constraintSet.connect(view.getId(), i3, Integer.parseInt(str2), i4, GetPxFromDp);
        }
    }

    private static int GetPxFromDp(int i2, String str) {
        int indexOf;
        if (str == null || (indexOf = str.indexOf(100)) == -1) {
            return 0;
        }
        return (int) ((Integer.valueOf(str.substring(0, indexOf)).intValue() * i2) / 160.0f);
    }

    private static void SetAbsolutePositions(int i2, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap) {
        String str = hashMap.get("layout_editor_absoluteX");
        if (str != null) {
            constraintSet.setEditorAbsoluteX(view.getId(), GetPxFromDp(i2, str));
        }
        String str2 = hashMap.get("layout_editor_absoluteY");
        if (str2 != null) {
            constraintSet.setEditorAbsoluteY(view.getId(), GetPxFromDp(i2, str2));
        }
    }

    private static void SetBias(ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i2) {
        String str = hashMap.get(i2 == 1 ? "layout_constraintVertical_bias" : "layout_constraintHorizontal_bias");
        if (str != null) {
            if (i2 == 0) {
                constraintSet.setHorizontalBias(view.getId(), Float.parseFloat(str));
            } else if (i2 == 1) {
                constraintSet.setVerticalBias(view.getId(), Float.parseFloat(str));
            }
        }
    }

    private static void SetDimensions(int i2, ConstraintSet constraintSet, View view, HashMap<String, String> hashMap, int i3) {
        String str = hashMap.get(i3 == 1 ? "layout_height" : "layout_width");
        if (str != null) {
            int GetPxFromDp = str.equalsIgnoreCase("wrap_content") ? -2 : GetPxFromDp(i2, str);
            int id = view.getId();
            if (i3 == 0) {
                constraintSet.constrainWidth(id, GetPxFromDp);
            } else {
                constraintSet.constrainHeight(id, GetPxFromDp);
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public int designAccess(int i2, String str, Object obj, float[] fArr, int i3, float[] fArr2, int i4) {
        MotionController motionController;
        View view = (View) obj;
        if (i2 != 0) {
            MotionLayout motionLayout = this.mMotionLayout;
            if (motionLayout.f2078f == null || view == null || (motionController = (MotionController) motionLayout.f2083k.get(view)) == null) {
                return -1;
            }
        } else {
            motionController = null;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                int duration = this.mMotionLayout.f2078f.getDuration() / 16;
                motionController.c(fArr2, duration);
                return duration;
            } else if (i2 == 2) {
                int duration2 = this.mMotionLayout.f2078f.getDuration() / 16;
                motionController.b(fArr2, null);
                return duration2;
            } else if (i2 != 3) {
                return -1;
            } else {
                int duration3 = this.mMotionLayout.f2078f.getDuration() / 16;
                return motionController.g(str, fArr2, i4);
            }
        }
        return 1;
    }

    public void disableAutoTransition(boolean z) {
        this.mMotionLayout.I(z);
    }

    public void dumpConstraintSet(String str) {
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout.f2078f == null) {
            motionLayout.f2078f = this.mSceneCache;
        }
        int P = motionLayout.P(str);
        PrintStream printStream = System.out;
        printStream.println(" dumping  " + str + " (" + P + ")");
        try {
            this.mMotionLayout.f2078f.h(P).dump(this.mMotionLayout.f2078f, new int[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int getAnimationKeyFrames(Object obj, float[] fArr) {
        MotionScene motionScene = this.mMotionLayout.f2078f;
        if (motionScene == null) {
            return -1;
        }
        int duration = motionScene.getDuration() / 16;
        MotionController motionController = (MotionController) this.mMotionLayout.f2083k.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.b(fArr, null);
        return duration;
    }

    public int getAnimationPath(Object obj, float[] fArr, int i2) {
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout.f2078f == null) {
            return -1;
        }
        MotionController motionController = (MotionController) motionLayout.f2083k.get(obj);
        if (motionController == null) {
            return 0;
        }
        motionController.c(fArr, i2);
        return i2;
    }

    public void getAnimationRectangles(Object obj, float[] fArr) {
        MotionScene motionScene = this.mMotionLayout.f2078f;
        if (motionScene == null) {
            return;
        }
        int duration = motionScene.getDuration() / 16;
        MotionController motionController = (MotionController) this.mMotionLayout.f2083k.get(obj);
        if (motionController == null) {
            return;
        }
        motionController.e(fArr, duration);
    }

    public String getEndState() {
        int endState = this.mMotionLayout.getEndState();
        if (this.mLastEndStateId == endState) {
            return this.mLastEndState;
        }
        String N = this.mMotionLayout.N(endState);
        if (N != null) {
            this.mLastEndState = N;
            this.mLastEndStateId = endState;
        }
        return N;
    }

    public int getKeyFrameInfo(Object obj, int i2, int[] iArr) {
        MotionController motionController = (MotionController) this.mMotionLayout.f2083k.get((View) obj);
        if (motionController == null) {
            return 0;
        }
        return motionController.getKeyFrameInfo(i2, iArr);
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public float getKeyFramePosition(Object obj, int i2, float f2, float f3) {
        MotionController motionController;
        if ((obj instanceof View) && (motionController = (MotionController) this.mMotionLayout.f2083k.get((View) obj)) != null) {
            return motionController.j(i2, f2, f3);
        }
        return 0.0f;
    }

    public int getKeyFramePositions(Object obj, int[] iArr, float[] fArr) {
        MotionController motionController = (MotionController) this.mMotionLayout.f2083k.get((View) obj);
        if (motionController == null) {
            return 0;
        }
        return motionController.getKeyFramePositions(iArr, fArr);
    }

    public Object getKeyframe(int i2, int i3, int i4) {
        MotionLayout motionLayout = this.mMotionLayout;
        MotionScene motionScene = motionLayout.f2078f;
        if (motionScene == null) {
            return null;
        }
        return motionScene.k(motionLayout.getContext(), i2, i3, i4);
    }

    public Object getKeyframe(Object obj, int i2, int i3) {
        if (this.mMotionLayout.f2078f == null) {
            return null;
        }
        int id = ((View) obj).getId();
        MotionLayout motionLayout = this.mMotionLayout;
        return motionLayout.f2078f.k(motionLayout.getContext(), i2, id, i3);
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public Object getKeyframeAtLocation(Object obj, float f2, float f3) {
        MotionController motionController;
        View view = (View) obj;
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout.f2078f == null) {
            return -1;
        }
        if (view == null || (motionController = (MotionController) motionLayout.f2083k.get(view)) == null) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        return motionController.l(viewGroup.getWidth(), viewGroup.getHeight(), f2, f3);
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public Boolean getPositionKeyframe(Object obj, Object obj2, float f2, float f3, String[] strArr, float[] fArr) {
        if (obj instanceof KeyPositionBase) {
            View view = (View) obj2;
            ((MotionController) this.mMotionLayout.f2083k.get(view)).o(view, (KeyPositionBase) obj, f2, f3, strArr, fArr);
            this.mMotionLayout.rebuildScene();
            this.mMotionLayout.f2087o = true;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public float getProgress() {
        return this.mMotionLayout.getProgress();
    }

    public String getStartState() {
        int startState = this.mMotionLayout.getStartState();
        if (this.mLastStartStateId == startState) {
            return this.mLastStartState;
        }
        String N = this.mMotionLayout.N(startState);
        if (N != null) {
            this.mLastStartState = N;
            this.mLastStartStateId = startState;
        }
        return this.mMotionLayout.N(startState);
    }

    public String getState() {
        if (this.mLastStartState != null && this.mLastEndState != null) {
            float progress = getProgress();
            if (progress <= 0.01f) {
                return this.mLastStartState;
            }
            if (progress >= 0.99f) {
                return this.mLastEndState;
            }
        }
        return this.mLastStartState;
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public long getTransitionTimeMs() {
        return this.mMotionLayout.getTransitionTimeMs();
    }

    public boolean isInTransition() {
        return (this.mLastStartState == null || this.mLastEndState == null) ? false : true;
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public void setAttributes(int i2, String str, Object obj, Object obj2) {
        View view = (View) obj;
        HashMap hashMap = (HashMap) obj2;
        int P = this.mMotionLayout.P(str);
        ConstraintSet h2 = this.mMotionLayout.f2078f.h(P);
        if (h2 == null) {
            return;
        }
        h2.clear(view.getId());
        SetDimensions(i2, h2, view, hashMap, 0);
        SetDimensions(i2, h2, view, hashMap, 1);
        Connect(i2, h2, view, hashMap, 6, 6);
        Connect(i2, h2, view, hashMap, 6, 7);
        Connect(i2, h2, view, hashMap, 7, 7);
        Connect(i2, h2, view, hashMap, 7, 6);
        Connect(i2, h2, view, hashMap, 1, 1);
        Connect(i2, h2, view, hashMap, 1, 2);
        Connect(i2, h2, view, hashMap, 2, 2);
        Connect(i2, h2, view, hashMap, 2, 1);
        Connect(i2, h2, view, hashMap, 3, 3);
        Connect(i2, h2, view, hashMap, 3, 4);
        Connect(i2, h2, view, hashMap, 4, 3);
        Connect(i2, h2, view, hashMap, 4, 4);
        Connect(i2, h2, view, hashMap, 5, 5);
        SetBias(h2, view, hashMap, 0);
        SetBias(h2, view, hashMap, 1);
        SetAbsolutePositions(i2, h2, view, hashMap);
        this.mMotionLayout.updateState(P, h2);
        this.mMotionLayout.requestLayout();
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public void setKeyFrame(Object obj, int i2, String str, Object obj2) {
        MotionScene motionScene = this.mMotionLayout.f2078f;
        if (motionScene != null) {
            motionScene.setKeyframe((View) obj, i2, str, obj2);
            MotionLayout motionLayout = this.mMotionLayout;
            motionLayout.f2086n = i2 / 100.0f;
            motionLayout.f2085m = 0.0f;
            motionLayout.rebuildScene();
            this.mMotionLayout.K(true);
        }
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public boolean setKeyFramePosition(Object obj, int i2, int i3, float f2, float f3) {
        if (obj instanceof View) {
            MotionLayout motionLayout = this.mMotionLayout;
            if (motionLayout.f2078f != null) {
                MotionController motionController = (MotionController) motionLayout.f2083k.get(obj);
                MotionLayout motionLayout2 = this.mMotionLayout;
                int i4 = (int) (motionLayout2.f2084l * 100.0f);
                if (motionController != null) {
                    View view = (View) obj;
                    if (motionLayout2.f2078f.v(view, i4)) {
                        float j2 = motionController.j(2, f2, f3);
                        float j3 = motionController.j(5, f2, f3);
                        this.mMotionLayout.f2078f.setKeyframe(view, i4, "motion:percentX", Float.valueOf(j2));
                        this.mMotionLayout.f2078f.setKeyframe(view, i4, "motion:percentY", Float.valueOf(j3));
                        this.mMotionLayout.rebuildScene();
                        this.mMotionLayout.K(true);
                        this.mMotionLayout.invalidate();
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public void setKeyframe(Object obj, String str, Object obj2) {
        if (obj instanceof Key) {
            ((Key) obj).setValue(str, obj2);
            this.mMotionLayout.rebuildScene();
            this.mMotionLayout.f2087o = true;
        }
    }

    public void setState(String str) {
        if (str == null) {
            str = "motion_base";
        }
        if (this.mLastStartState == str) {
            return;
        }
        this.mLastStartState = str;
        this.mLastEndState = null;
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout.f2078f == null) {
            motionLayout.f2078f = this.mSceneCache;
        }
        int P = motionLayout.P(str);
        this.mLastStartStateId = P;
        if (P != 0) {
            if (P == this.mMotionLayout.getStartState()) {
                this.mMotionLayout.setProgress(0.0f);
            } else {
                if (P != this.mMotionLayout.getEndState()) {
                    this.mMotionLayout.transitionToState(P);
                }
                this.mMotionLayout.setProgress(1.0f);
            }
        }
        this.mMotionLayout.requestLayout();
    }

    @Override // androidx.constraintlayout.motion.widget.ProxyInterface
    public void setToolPosition(float f2) {
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout.f2078f == null) {
            motionLayout.f2078f = this.mSceneCache;
        }
        motionLayout.setProgress(f2);
        this.mMotionLayout.K(true);
        this.mMotionLayout.requestLayout();
        this.mMotionLayout.invalidate();
    }

    public void setTransition(String str, String str2) {
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout.f2078f == null) {
            motionLayout.f2078f = this.mSceneCache;
        }
        int P = motionLayout.P(str);
        int P2 = this.mMotionLayout.P(str2);
        this.mMotionLayout.setTransition(P, P2);
        this.mLastStartStateId = P;
        this.mLastEndStateId = P2;
        this.mLastStartState = str;
        this.mLastEndState = str2;
    }

    public void setViewDebug(Object obj, int i2) {
        MotionController motionController;
        if ((obj instanceof View) && (motionController = (MotionController) this.mMotionLayout.f2083k.get(obj)) != null) {
            motionController.setDrawPath(i2);
            this.mMotionLayout.invalidate();
        }
    }
}
