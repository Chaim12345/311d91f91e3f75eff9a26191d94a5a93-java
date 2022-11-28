package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.AnimatorUtils;
import androidx.transition.Transition;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    private int mMode;
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String[] sTransitionProperties = {"android:visibility:visibility", PROPNAME_PARENT};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener, AnimatorUtils.AnimatorPauseListenerCompat {

        /* renamed from: a  reason: collision with root package name */
        boolean f4157a = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        DisappearListener(View view, int i2, boolean z) {
            this.mView = view;
            this.mFinalVisibility = i2;
            this.mParent = (ViewGroup) view.getParent();
            this.mSuppressLayout = z;
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (!this.f4157a) {
                ViewUtils.i(this.mView, this.mFinalVisibility);
                ViewGroup viewGroup = this.mParent;
                if (viewGroup != null) {
                    viewGroup.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean z) {
            ViewGroup viewGroup;
            if (!this.mSuppressLayout || this.mLayoutSuppressed == z || (viewGroup = this.mParent) == null) {
                return;
            }
            this.mLayoutSuppressed = z;
            ViewGroupUtils.c(viewGroup, z);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f4157a = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            hideViewWhenNotCanceled();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, androidx.transition.AnimatorUtils.AnimatorPauseListenerCompat
        public void onAnimationPause(Animator animator) {
            if (this.f4157a) {
                return;
            }
            ViewUtils.i(this.mView, this.mFinalVisibility);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener, androidx.transition.AnimatorUtils.AnimatorPauseListenerCompat
        public void onAnimationResume(Animator animator) {
            if (this.f4157a) {
                return;
            }
            ViewUtils.i(this.mView, 0);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(@NonNull Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(@NonNull Transition transition) {
            hideViewWhenNotCanceled();
            transition.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(@NonNull Transition transition) {
            suppressLayout(false);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(@NonNull Transition transition) {
            suppressLayout(true);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(@NonNull Transition transition) {
        }
    }

    @SuppressLint({"UniqueConstants"})
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Mode {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class VisibilityInfo {

        /* renamed from: a  reason: collision with root package name */
        boolean f4158a;

        /* renamed from: b  reason: collision with root package name */
        boolean f4159b;

        /* renamed from: c  reason: collision with root package name */
        int f4160c;

        /* renamed from: d  reason: collision with root package name */
        int f4161d;

        /* renamed from: e  reason: collision with root package name */
        ViewGroup f4162e;

        /* renamed from: f  reason: collision with root package name */
        ViewGroup f4163f;

        VisibilityInfo() {
        }
    }

    public Visibility() {
        this.mMode = 3;
    }

    @SuppressLint({"RestrictedApi"})
    public Visibility(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 3;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, Styleable.f4107e);
        int namedInt = TypedArrayUtils.getNamedInt(obtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        obtainStyledAttributes.recycle();
        if (namedInt != 0) {
            setMode(namedInt);
        }
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put(PROPNAME_PARENT, transitionValues.view.getParent());
        int[] iArr = new int[2];
        transitionValues.view.getLocationOnScreen(iArr);
        transitionValues.values.put(PROPNAME_SCREEN_LOCATION, iArr);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0075, code lost:
        if (r9 == 0) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007f, code lost:
        if (r0.f4162e == null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0091, code lost:
        if (r0.f4160c == 0) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private VisibilityInfo getVisibilityChangeInfo(TransitionValues transitionValues, TransitionValues transitionValues2) {
        VisibilityInfo visibilityInfo = new VisibilityInfo();
        visibilityInfo.f4158a = false;
        visibilityInfo.f4159b = false;
        if (transitionValues == null || !transitionValues.values.containsKey("android:visibility:visibility")) {
            visibilityInfo.f4160c = -1;
            visibilityInfo.f4162e = null;
        } else {
            visibilityInfo.f4160c = ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue();
            visibilityInfo.f4162e = (ViewGroup) transitionValues.values.get(PROPNAME_PARENT);
        }
        if (transitionValues2 == null || !transitionValues2.values.containsKey("android:visibility:visibility")) {
            visibilityInfo.f4161d = -1;
            visibilityInfo.f4163f = null;
        } else {
            visibilityInfo.f4161d = ((Integer) transitionValues2.values.get("android:visibility:visibility")).intValue();
            visibilityInfo.f4163f = (ViewGroup) transitionValues2.values.get(PROPNAME_PARENT);
        }
        if (transitionValues == null || transitionValues2 == null) {
            if (transitionValues != null || visibilityInfo.f4161d != 0) {
                if (transitionValues2 == null) {
                }
                return visibilityInfo;
            }
            visibilityInfo.f4159b = true;
        } else {
            int i2 = visibilityInfo.f4160c;
            int i3 = visibilityInfo.f4161d;
            if (i2 == i3 && visibilityInfo.f4162e == visibilityInfo.f4163f) {
                return visibilityInfo;
            }
            if (i2 != i3) {
                if (i2 != 0) {
                }
                visibilityInfo.f4159b = false;
            } else {
                if (visibilityInfo.f4163f != null) {
                }
                visibilityInfo.f4159b = false;
            }
        }
        visibilityInfo.f4158a = true;
        return visibilityInfo;
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    @Nullable
    public Animator createAnimator(@NonNull ViewGroup viewGroup, @Nullable TransitionValues transitionValues, @Nullable TransitionValues transitionValues2) {
        VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
        if (visibilityChangeInfo.f4158a) {
            if (visibilityChangeInfo.f4162e == null && visibilityChangeInfo.f4163f == null) {
                return null;
            }
            return visibilityChangeInfo.f4159b ? onAppear(viewGroup, transitionValues, visibilityChangeInfo.f4160c, transitionValues2, visibilityChangeInfo.f4161d) : onDisappear(viewGroup, transitionValues, visibilityChangeInfo.f4160c, transitionValues2, visibilityChangeInfo.f4161d);
        }
        return null;
    }

    public int getMode() {
        return this.mMode;
    }

    @Override // androidx.transition.Transition
    @Nullable
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // androidx.transition.Transition
    public boolean isTransitionRequired(TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null && transitionValues2 == null) {
            return false;
        }
        if (transitionValues == null || transitionValues2 == null || transitionValues2.values.containsKey("android:visibility:visibility") == transitionValues.values.containsKey("android:visibility:visibility")) {
            VisibilityInfo visibilityChangeInfo = getVisibilityChangeInfo(transitionValues, transitionValues2);
            if (visibilityChangeInfo.f4158a) {
                return visibilityChangeInfo.f4160c == 0 || visibilityChangeInfo.f4161d == 0;
            }
            return false;
        }
        return false;
    }

    public boolean isVisible(TransitionValues transitionValues) {
        if (transitionValues == null) {
            return false;
        }
        return ((Integer) transitionValues.values.get("android:visibility:visibility")).intValue() == 0 && ((View) transitionValues.values.get(PROPNAME_PARENT)) != null;
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    public Animator onAppear(ViewGroup viewGroup, TransitionValues transitionValues, int i2, TransitionValues transitionValues2, int i3) {
        if ((this.mMode & 1) != 1 || transitionValues2 == null) {
            return null;
        }
        if (transitionValues == null) {
            View view = (View) transitionValues2.view.getParent();
            if (getVisibilityChangeInfo(h(view, false), getTransitionValues(view, false)).f4158a) {
                return null;
            }
        }
        return onAppear(viewGroup, transitionValues2.view, transitionValues, transitionValues2);
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0089, code lost:
        if (r17.f4118e != false) goto L52;
     */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Animator onDisappear(final ViewGroup viewGroup, TransitionValues transitionValues, int i2, TransitionValues transitionValues2, int i3) {
        View view;
        boolean z;
        boolean z2;
        View view2;
        if ((this.mMode & 2) == 2 && transitionValues != null) {
            final View view3 = transitionValues.view;
            View view4 = transitionValues2 != null ? transitionValues2.view : null;
            int i4 = R.id.save_overlay_view;
            final View view5 = (View) view3.getTag(i4);
            if (view5 != null) {
                view2 = null;
                z2 = true;
            } else if (view4 == null || view4.getParent() == null) {
                if (view4 != null) {
                    view = null;
                    z = false;
                    if (z) {
                        if (view3.getParent() != null) {
                            if (view3.getParent() instanceof View) {
                                View view6 = (View) view3.getParent();
                                if (getVisibilityChangeInfo(getTransitionValues(view6, true), h(view6, true)).f4158a) {
                                    int id = view6.getId();
                                    if (view6.getParent() == null) {
                                        if (id != -1) {
                                            if (viewGroup.findViewById(id) != null) {
                                            }
                                        }
                                    }
                                } else {
                                    view4 = TransitionUtils.a(viewGroup, view3, view6);
                                }
                            }
                        }
                        view2 = view;
                        z2 = false;
                        view5 = view3;
                    }
                    z2 = false;
                    View view7 = view;
                    view5 = view4;
                    view2 = view7;
                }
                view4 = null;
                view = null;
                z = true;
                if (z) {
                }
                z2 = false;
                View view72 = view;
                view5 = view4;
                view2 = view72;
            } else {
                if (i3 == 4 || view3 == view4) {
                    view = view4;
                    z = false;
                    view4 = null;
                    if (z) {
                    }
                    z2 = false;
                    View view722 = view;
                    view5 = view4;
                    view2 = view722;
                }
                view4 = null;
                view = null;
                z = true;
                if (z) {
                }
                z2 = false;
                View view7222 = view;
                view5 = view4;
                view2 = view7222;
            }
            if (view5 == null) {
                if (view2 != null) {
                    int visibility = view2.getVisibility();
                    ViewUtils.i(view2, 0);
                    Animator onDisappear = onDisappear(viewGroup, view2, transitionValues, transitionValues2);
                    if (onDisappear != null) {
                        DisappearListener disappearListener = new DisappearListener(view2, i3, true);
                        onDisappear.addListener(disappearListener);
                        AnimatorUtils.a(onDisappear, disappearListener);
                        addListener(disappearListener);
                    } else {
                        ViewUtils.i(view2, visibility);
                    }
                    return onDisappear;
                }
                return null;
            }
            if (!z2) {
                int[] iArr = (int[]) transitionValues.values.get(PROPNAME_SCREEN_LOCATION);
                int i5 = iArr[0];
                int i6 = iArr[1];
                int[] iArr2 = new int[2];
                viewGroup.getLocationOnScreen(iArr2);
                view5.offsetLeftAndRight((i5 - iArr2[0]) - view5.getLeft());
                view5.offsetTopAndBottom((i6 - iArr2[1]) - view5.getTop());
                ViewGroupUtils.b(viewGroup).add(view5);
            }
            Animator onDisappear2 = onDisappear(viewGroup, view5, transitionValues, transitionValues2);
            if (!z2) {
                if (onDisappear2 == null) {
                    ViewGroupUtils.b(viewGroup).remove(view5);
                } else {
                    view3.setTag(i4, view5);
                    addListener(new TransitionListenerAdapter() { // from class: androidx.transition.Visibility.1
                        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                        public void onTransitionEnd(@NonNull Transition transition) {
                            view3.setTag(R.id.save_overlay_view, null);
                            ViewGroupUtils.b(viewGroup).remove(view5);
                            transition.removeListener(this);
                        }

                        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                        public void onTransitionPause(@NonNull Transition transition) {
                            ViewGroupUtils.b(viewGroup).remove(view5);
                        }

                        @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                        public void onTransitionResume(@NonNull Transition transition) {
                            if (view5.getParent() == null) {
                                ViewGroupUtils.b(viewGroup).add(view5);
                            } else {
                                Visibility.this.cancel();
                            }
                        }
                    });
                }
            }
            return onDisappear2;
        }
        return null;
    }

    public void setMode(int i2) {
        if ((i2 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.mMode = i2;
    }
}
