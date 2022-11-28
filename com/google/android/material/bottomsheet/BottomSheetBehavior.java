package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final int CORNER_ANIMATION_DURATION = 500;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_BottomSheet_Modal;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int NO_WIDTH = -1;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    private static final int SIGNIFICANT_VEL_THRESHOLD = 500;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "BottomSheetBehavior";

    /* renamed from: a  reason: collision with root package name */
    int f7166a;

    /* renamed from: b  reason: collision with root package name */
    int f7167b;

    /* renamed from: c  reason: collision with root package name */
    int f7168c;
    @NonNull
    private final ArrayList<BottomSheetCallback> callbacks;
    private int childHeight;

    /* renamed from: d  reason: collision with root package name */
    float f7169d;
    private final ViewDragHelper.Callback dragCallback;
    private boolean draggable;

    /* renamed from: e  reason: collision with root package name */
    int f7170e;
    private int expandHalfwayActionId;

    /* renamed from: f  reason: collision with root package name */
    float f7171f;
    private boolean fitToContents;

    /* renamed from: g  reason: collision with root package name */
    boolean f7172g;
    private int gestureInsetBottom;
    private boolean gestureInsetBottomIgnored;

    /* renamed from: h  reason: collision with root package name */
    int f7173h;
    @Nullable

    /* renamed from: i  reason: collision with root package name */
    ViewDragHelper f7174i;
    private boolean ignoreEvents;
    @Nullable
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int insetBottom;
    private int insetTop;
    @Nullable
    private ValueAnimator interpolatorAnimator;
    private boolean isShapeExpanded;

    /* renamed from: j  reason: collision with root package name */
    int f7175j;

    /* renamed from: k  reason: collision with root package name */
    int f7176k;
    @Nullable

    /* renamed from: l  reason: collision with root package name */
    WeakReference f7177l;
    private int lastNestedScrollDy;
    @Nullable

    /* renamed from: m  reason: collision with root package name */
    WeakReference f7178m;
    private MaterialShapeDrawable materialShapeDrawable;
    private int maxWidth;
    private float maximumVelocity;

    /* renamed from: n  reason: collision with root package name */
    int f7179n;
    private boolean nestedScrolled;

    /* renamed from: o  reason: collision with root package name */
    boolean f7180o;
    private boolean paddingBottomSystemWindowInsets;
    private boolean paddingLeftSystemWindowInsets;
    private boolean paddingRightSystemWindowInsets;
    private boolean paddingTopSystemWindowInsets;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightGestureInsetBuffer;
    private int peekHeightMin;
    private int saveFlags;
    private BottomSheetBehavior<V>.SettleRunnable settleRunnable;
    private ShapeAppearanceModel shapeAppearanceModelDefault;
    private boolean shapeThemingEnabled;
    private boolean skipCollapsed;
    private boolean updateImportantForAccessibilityOnSiblings;
    @Nullable
    private VelocityTracker velocityTracker;

    /* loaded from: classes2.dex */
    public static abstract class BottomSheetCallback {
        public abstract void onSlide(@NonNull View view, float f2);

        public abstract void onStateChanged(@NonNull View view, int i2);
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes2.dex */
    public @interface SaveFlags {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes2.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
            @Override // android.os.Parcelable.Creator
            @Nullable
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            @NonNull
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            @NonNull
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        final int f7192a;

        /* renamed from: b  reason: collision with root package name */
        int f7193b;

        /* renamed from: c  reason: collision with root package name */
        boolean f7194c;

        /* renamed from: d  reason: collision with root package name */
        boolean f7195d;

        /* renamed from: e  reason: collision with root package name */
        boolean f7196e;

        public SavedState(@NonNull Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public SavedState(@NonNull Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.f7192a = parcel.readInt();
            this.f7193b = parcel.readInt();
            this.f7194c = parcel.readInt() == 1;
            this.f7195d = parcel.readInt() == 1;
            this.f7196e = parcel.readInt() == 1;
        }

        @Deprecated
        public SavedState(Parcelable parcelable, int i2) {
            super(parcelable);
            this.f7192a = i2;
        }

        public SavedState(Parcelable parcelable, @NonNull BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.f7192a = bottomSheetBehavior.f7173h;
            this.f7193b = ((BottomSheetBehavior) bottomSheetBehavior).peekHeight;
            this.f7194c = ((BottomSheetBehavior) bottomSheetBehavior).fitToContents;
            this.f7195d = bottomSheetBehavior.f7172g;
            this.f7196e = ((BottomSheetBehavior) bottomSheetBehavior).skipCollapsed;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i2) {
            super.writeToParcel(parcel, i2);
            parcel.writeInt(this.f7192a);
            parcel.writeInt(this.f7193b);
            parcel.writeInt(this.f7194c ? 1 : 0);
            parcel.writeInt(this.f7195d ? 1 : 0);
            parcel.writeInt(this.f7196e ? 1 : 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SettleRunnable implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        int f7197a;
        private boolean isPosted;
        private final View view;

        SettleRunnable(View view, int i2) {
            this.view = view;
            this.f7197a = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper viewDragHelper = BottomSheetBehavior.this.f7174i;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.q(this.f7197a);
            } else {
                ViewCompat.postOnAnimation(this.view, this);
            }
            this.isPosted = false;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes2.dex */
    public @interface State {
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.settleRunnable = null;
        this.f7169d = 0.5f;
        this.f7171f = -1.0f;
        this.draggable = true;
        this.f7173h = 4;
        this.callbacks = new ArrayList<>();
        this.expandHalfwayActionId = -1;
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5
            private boolean releasedLow(@NonNull View view) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return top > (bottomSheetBehavior.f7176k + bottomSheetBehavior.getExpandedOffset()) / 2;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(@NonNull View view, int i2, int i3) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(@NonNull View view, int i2, int i3) {
                int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return MathUtils.clamp(i2, expandedOffset, bottomSheetBehavior.f7172g ? bottomSheetBehavior.f7176k : bottomSheetBehavior.f7170e);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(@NonNull View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return bottomSheetBehavior.f7172g ? bottomSheetBehavior.f7176k : bottomSheetBehavior.f7170e;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i2) {
                if (i2 == 1 && BottomSheetBehavior.this.draggable) {
                    BottomSheetBehavior.this.q(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(@NonNull View view, int i2, int i3, int i4, int i5) {
                BottomSheetBehavior.this.n(i3);
            }

            /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
                if (java.lang.Math.abs(r7.getTop() - r6.f7189a.getExpandedOffset()) < java.lang.Math.abs(r7.getTop() - r6.f7189a.f7168c)) goto L30;
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x007b, code lost:
                r8 = r6.f7189a.getExpandedOffset();
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x00b7, code lost:
                if (java.lang.Math.abs(r8 - r6.f7189a.f7168c) < java.lang.Math.abs(r8 - r6.f7189a.f7170e)) goto L31;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00de, code lost:
                if (java.lang.Math.abs(r8 - r6.f7189a.f7167b) < java.lang.Math.abs(r8 - r6.f7189a.f7170e)) goto L5;
             */
            /* JADX WARN: Code restructure failed: missing block: B:49:0x00f0, code lost:
                if (r8 < java.lang.Math.abs(r8 - r9.f7170e)) goto L30;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x0102, code lost:
                if (java.lang.Math.abs(r8 - r0) < java.lang.Math.abs(r8 - r6.f7189a.f7170e)) goto L31;
             */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onViewReleased(@NonNull View view, float f2, float f3) {
                int i2;
                int i3 = 4;
                if (f3 < 0.0f) {
                    if (!BottomSheetBehavior.this.fitToContents) {
                        int top = view.getTop();
                        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                        int i4 = bottomSheetBehavior.f7168c;
                        if (top > i4) {
                            i2 = i4;
                            i3 = 6;
                        } else {
                            i2 = bottomSheetBehavior.getExpandedOffset();
                            i3 = 3;
                        }
                    }
                    i2 = BottomSheetBehavior.this.f7167b;
                    i3 = 3;
                } else {
                    BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                    if (bottomSheetBehavior2.f7172g && bottomSheetBehavior2.s(view, f3)) {
                        if ((Math.abs(f2) >= Math.abs(f3) || f3 <= 500.0f) && !releasedLow(view)) {
                            if (!BottomSheetBehavior.this.fitToContents) {
                            }
                            i2 = BottomSheetBehavior.this.f7167b;
                            i3 = 3;
                        } else {
                            i2 = BottomSheetBehavior.this.f7176k;
                            i3 = 5;
                        }
                    } else if (f3 == 0.0f || Math.abs(f2) > Math.abs(f3)) {
                        int top2 = view.getTop();
                        if (!BottomSheetBehavior.this.fitToContents) {
                            BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                            int i5 = bottomSheetBehavior3.f7168c;
                            if (top2 < i5) {
                            }
                            i2 = BottomSheetBehavior.this.f7168c;
                            i3 = 6;
                        }
                        i2 = BottomSheetBehavior.this.f7170e;
                    } else {
                        if (!BottomSheetBehavior.this.fitToContents) {
                            int top3 = view.getTop();
                        }
                        i2 = BottomSheetBehavior.this.f7170e;
                    }
                }
                BottomSheetBehavior.this.t(view, i3, i2, true);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(@NonNull View view, int i2) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i3 = bottomSheetBehavior.f7173h;
                if (i3 == 1 || bottomSheetBehavior.f7180o) {
                    return false;
                }
                if (i3 == 3 && bottomSheetBehavior.f7179n == i2) {
                    WeakReference weakReference = bottomSheetBehavior.f7178m;
                    View view2 = weakReference != null ? (View) weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                WeakReference weakReference2 = BottomSheetBehavior.this.f7177l;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
    }

    public BottomSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        int i2;
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.settleRunnable = null;
        this.f7169d = 0.5f;
        this.f7171f = -1.0f;
        this.draggable = true;
        this.f7173h = 4;
        this.callbacks = new ArrayList<>();
        this.expandHalfwayActionId = -1;
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5
            private boolean releasedLow(@NonNull View view) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return top > (bottomSheetBehavior.f7176k + bottomSheetBehavior.getExpandedOffset()) / 2;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(@NonNull View view, int i22, int i3) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(@NonNull View view, int i22, int i3) {
                int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return MathUtils.clamp(i22, expandedOffset, bottomSheetBehavior.f7172g ? bottomSheetBehavior.f7176k : bottomSheetBehavior.f7170e);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(@NonNull View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return bottomSheetBehavior.f7172g ? bottomSheetBehavior.f7176k : bottomSheetBehavior.f7170e;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i22) {
                if (i22 == 1 && BottomSheetBehavior.this.draggable) {
                    BottomSheetBehavior.this.q(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(@NonNull View view, int i22, int i3, int i4, int i5) {
                BottomSheetBehavior.this.n(i3);
            }

            /* JADX WARN: Code restructure failed: missing block: B:27:0x0079, code lost:
                if (java.lang.Math.abs(r7.getTop() - r6.f7189a.getExpandedOffset()) < java.lang.Math.abs(r7.getTop() - r6.f7189a.f7168c)) goto L30;
             */
            /* JADX WARN: Code restructure failed: missing block: B:28:0x007b, code lost:
                r8 = r6.f7189a.getExpandedOffset();
             */
            /* JADX WARN: Code restructure failed: missing block: B:38:0x00b7, code lost:
                if (java.lang.Math.abs(r8 - r6.f7189a.f7168c) < java.lang.Math.abs(r8 - r6.f7189a.f7170e)) goto L31;
             */
            /* JADX WARN: Code restructure failed: missing block: B:44:0x00de, code lost:
                if (java.lang.Math.abs(r8 - r6.f7189a.f7167b) < java.lang.Math.abs(r8 - r6.f7189a.f7170e)) goto L5;
             */
            /* JADX WARN: Code restructure failed: missing block: B:49:0x00f0, code lost:
                if (r8 < java.lang.Math.abs(r8 - r9.f7170e)) goto L30;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x0102, code lost:
                if (java.lang.Math.abs(r8 - r0) < java.lang.Math.abs(r8 - r6.f7189a.f7170e)) goto L31;
             */
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onViewReleased(@NonNull View view, float f2, float f3) {
                int i22;
                int i3 = 4;
                if (f3 < 0.0f) {
                    if (!BottomSheetBehavior.this.fitToContents) {
                        int top = view.getTop();
                        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                        int i4 = bottomSheetBehavior.f7168c;
                        if (top > i4) {
                            i22 = i4;
                            i3 = 6;
                        } else {
                            i22 = bottomSheetBehavior.getExpandedOffset();
                            i3 = 3;
                        }
                    }
                    i22 = BottomSheetBehavior.this.f7167b;
                    i3 = 3;
                } else {
                    BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                    if (bottomSheetBehavior2.f7172g && bottomSheetBehavior2.s(view, f3)) {
                        if ((Math.abs(f2) >= Math.abs(f3) || f3 <= 500.0f) && !releasedLow(view)) {
                            if (!BottomSheetBehavior.this.fitToContents) {
                            }
                            i22 = BottomSheetBehavior.this.f7167b;
                            i3 = 3;
                        } else {
                            i22 = BottomSheetBehavior.this.f7176k;
                            i3 = 5;
                        }
                    } else if (f3 == 0.0f || Math.abs(f2) > Math.abs(f3)) {
                        int top2 = view.getTop();
                        if (!BottomSheetBehavior.this.fitToContents) {
                            BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                            int i5 = bottomSheetBehavior3.f7168c;
                            if (top2 < i5) {
                            }
                            i22 = BottomSheetBehavior.this.f7168c;
                            i3 = 6;
                        }
                        i22 = BottomSheetBehavior.this.f7170e;
                    } else {
                        if (!BottomSheetBehavior.this.fitToContents) {
                            int top3 = view.getTop();
                        }
                        i22 = BottomSheetBehavior.this.f7170e;
                    }
                }
                BottomSheetBehavior.this.t(view, i3, i22, true);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(@NonNull View view, int i22) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i3 = bottomSheetBehavior.f7173h;
                if (i3 == 1 || bottomSheetBehavior.f7180o) {
                    return false;
                }
                if (i3 == 3 && bottomSheetBehavior.f7179n == i22) {
                    WeakReference weakReference = bottomSheetBehavior.f7178m;
                    View view2 = weakReference != null ? (View) weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                WeakReference weakReference2 = BottomSheetBehavior.this.f7177l;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
        this.peekHeightGestureInsetBuffer = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        this.shapeThemingEnabled = obtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance);
        int i3 = R.styleable.BottomSheetBehavior_Layout_backgroundTint;
        boolean hasValue = obtainStyledAttributes.hasValue(i3);
        if (hasValue) {
            createMaterialShapeDrawable(context, attributeSet, hasValue, MaterialResources.getColorStateList(context, obtainStyledAttributes, i3));
        } else {
            createMaterialShapeDrawable(context, attributeSet, hasValue);
        }
        createShapeValueAnimator();
        if (Build.VERSION.SDK_INT >= 21) {
            this.f7171f = obtainStyledAttributes.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        int i4 = R.styleable.BottomSheetBehavior_Layout_android_maxWidth;
        if (obtainStyledAttributes.hasValue(i4)) {
            setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(i4, -1));
        }
        int i5 = R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue peekValue = obtainStyledAttributes.peekValue(i5);
        if (peekValue == null || (i2 = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(i5, -1));
        } else {
            setPeekHeight(i2);
        }
        setHideable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setGestureInsetBottomIgnored(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        setFitToContents(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        setDraggable(obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        setSaveFlags(obtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        setHalfExpandedRatio(obtainStyledAttributes.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i6 = R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(i6);
        setExpandedOffset((peekValue2 == null || peekValue2.type != 16) ? obtainStyledAttributes.getDimensionPixelOffset(i6, 0) : peekValue2.data);
        this.paddingBottomSystemWindowInsets = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.paddingTopSystemWindowInsets = obtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        obtainStyledAttributes.recycle();
        this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }

    private int addAccessibilityActionForState(V v, @StringRes int i2, int i3) {
        return ViewCompat.addAccessibilityAction(v, v.getResources().getString(i2), createAccessibilityViewCommandForState(i3));
    }

    private void calculateCollapsedOffset() {
        int calculatePeekHeight = calculatePeekHeight();
        if (this.fitToContents) {
            this.f7170e = Math.max(this.f7176k - calculatePeekHeight, this.f7167b);
        } else {
            this.f7170e = this.f7176k - calculatePeekHeight;
        }
    }

    private void calculateHalfExpandedOffset() {
        this.f7168c = (int) (this.f7176k * (1.0f - this.f7169d));
    }

    private int calculatePeekHeight() {
        int i2;
        return this.peekHeightAuto ? Math.min(Math.max(this.peekHeightMin, this.f7176k - ((this.f7175j * 9) / 16)), this.childHeight) + this.insetBottom : (this.gestureInsetBottomIgnored || this.paddingBottomSystemWindowInsets || (i2 = this.gestureInsetBottom) <= 0) ? this.peekHeight + this.insetBottom : Math.max(this.peekHeight, i2 + this.peekHeightGestureInsetBuffer);
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(final int i2) {
        return new AccessibilityViewCommand() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.6
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(@NonNull View view, @Nullable AccessibilityViewCommand.CommandArguments commandArguments) {
                BottomSheetBehavior.this.setState(i2);
                return true;
            }
        };
    }

    private void createMaterialShapeDrawable(@NonNull Context context, AttributeSet attributeSet, boolean z) {
        createMaterialShapeDrawable(context, attributeSet, z, null);
    }

    private void createMaterialShapeDrawable(@NonNull Context context, AttributeSet attributeSet, boolean z, @Nullable ColorStateList colorStateList) {
        if (this.shapeThemingEnabled) {
            this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(context, attributeSet, R.attr.bottomSheetStyle, DEF_STYLE_RES).build();
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
            this.materialShapeDrawable = materialShapeDrawable;
            materialShapeDrawable.initializeElevationOverlay(context);
            if (z && colorStateList != null) {
                this.materialShapeDrawable.setFillColor(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16842801, typedValue, true);
            this.materialShapeDrawable.setTint(typedValue.data);
        }
    }

    private void createShapeValueAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.interpolatorAnimator = ofFloat;
        ofFloat.setDuration(500L);
        this.interpolatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(@NonNull ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (BottomSheetBehavior.this.materialShapeDrawable != null) {
                    BottomSheetBehavior.this.materialShapeDrawable.setInterpolation(floatValue);
                }
            }
        });
    }

    @NonNull
    public static <V extends View> BottomSheetBehavior<V> from(@NonNull V v) {
        ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.f7179n);
    }

    private void replaceAccessibilityActionForState(V v, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i2) {
        ViewCompat.replaceAccessibilityAction(v, accessibilityActionCompat, null, createAccessibilityViewCommandForState(i2));
    }

    private void reset() {
        this.f7179n = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    private void restoreOptionalState(@NonNull SavedState savedState) {
        int i2 = this.saveFlags;
        if (i2 == 0) {
            return;
        }
        if (i2 == -1 || (i2 & 1) == 1) {
            this.peekHeight = savedState.f7193b;
        }
        if (i2 == -1 || (i2 & 2) == 2) {
            this.fitToContents = savedState.f7194c;
        }
        if (i2 == -1 || (i2 & 4) == 4) {
            this.f7172g = savedState.f7195d;
        }
        if (i2 == -1 || (i2 & 8) == 8) {
            this.skipCollapsed = savedState.f7196e;
        }
    }

    private void setWindowInsetsListener(@NonNull View view) {
        final boolean z = (Build.VERSION.SDK_INT < 29 || isGestureInsetBottomIgnored() || this.peekHeightAuto) ? false : true;
        if (this.paddingBottomSystemWindowInsets || this.paddingLeftSystemWindowInsets || this.paddingRightSystemWindowInsets || z) {
            ViewUtils.doOnApplyWindowInsets(view, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.4
                @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
                public WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat, ViewUtils.RelativePadding relativePadding) {
                    BottomSheetBehavior.this.insetTop = windowInsetsCompat.getSystemWindowInsetTop();
                    boolean isLayoutRtl = ViewUtils.isLayoutRtl(view2);
                    int paddingBottom = view2.getPaddingBottom();
                    int paddingLeft = view2.getPaddingLeft();
                    int paddingRight = view2.getPaddingRight();
                    if (BottomSheetBehavior.this.paddingBottomSystemWindowInsets) {
                        BottomSheetBehavior.this.insetBottom = windowInsetsCompat.getSystemWindowInsetBottom();
                        paddingBottom = relativePadding.bottom + BottomSheetBehavior.this.insetBottom;
                    }
                    if (BottomSheetBehavior.this.paddingLeftSystemWindowInsets) {
                        paddingLeft = (isLayoutRtl ? relativePadding.end : relativePadding.start) + windowInsetsCompat.getSystemWindowInsetLeft();
                    }
                    if (BottomSheetBehavior.this.paddingRightSystemWindowInsets) {
                        paddingRight = (isLayoutRtl ? relativePadding.start : relativePadding.end) + windowInsetsCompat.getSystemWindowInsetRight();
                    }
                    view2.setPadding(paddingLeft, view2.getPaddingTop(), paddingRight, paddingBottom);
                    if (z) {
                        BottomSheetBehavior.this.gestureInsetBottom = windowInsetsCompat.getMandatorySystemGestureInsets().bottom;
                    }
                    if (BottomSheetBehavior.this.paddingBottomSystemWindowInsets || z) {
                        BottomSheetBehavior.this.updatePeekHeight(false);
                    }
                    return windowInsetsCompat;
                }
            });
        }
    }

    private void settleToStatePendingLayout(final int i2) {
        final View view = (View) this.f7177l.get();
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(view)) {
            view.post(new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior.this.r(view, i2);
                }
            });
        } else {
            r(view, i2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateAccessibilityActions() {
        View view;
        int i2;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
        WeakReference weakReference = this.f7177l;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        ViewCompat.removeAccessibilityAction(view, 524288);
        ViewCompat.removeAccessibilityAction(view, 262144);
        ViewCompat.removeAccessibilityAction(view, 1048576);
        int i3 = this.expandHalfwayActionId;
        if (i3 != -1) {
            ViewCompat.removeAccessibilityAction(view, i3);
        }
        if (!this.fitToContents && this.f7173h != 6) {
            this.expandHalfwayActionId = addAccessibilityActionForState(view, R.string.bottomsheet_action_expand_halfway, 6);
        }
        if (this.f7172g && this.f7173h != 5) {
            replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
        }
        int i4 = this.f7173h;
        if (i4 == 3) {
            i2 = this.fitToContents ? 4 : 6;
            accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE;
        } else if (i4 != 4) {
            if (i4 != 6) {
                return;
            }
            replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
            replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            return;
        } else {
            i2 = this.fitToContents ? 3 : 6;
            accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND;
        }
        replaceAccessibilityActionForState(view, accessibilityActionCompat, i2);
    }

    private void updateDrawableForTargetState(int i2) {
        ValueAnimator valueAnimator;
        if (i2 == 2) {
            return;
        }
        boolean z = i2 == 3;
        if (this.isShapeExpanded != z) {
            this.isShapeExpanded = z;
            if (this.materialShapeDrawable == null || (valueAnimator = this.interpolatorAnimator) == null) {
                return;
            }
            if (valueAnimator.isRunning()) {
                this.interpolatorAnimator.reverse();
                return;
            }
            float f2 = z ? 0.0f : 1.0f;
            this.interpolatorAnimator.setFloatValues(1.0f - f2, f2);
            this.interpolatorAnimator.start();
        }
    }

    private void updateImportantForAccessibility(boolean z) {
        Map<View, Integer> map;
        int intValue;
        WeakReference weakReference = this.f7177l;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = ((View) weakReference.get()).getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (Build.VERSION.SDK_INT >= 16 && z) {
                if (this.importantForAccessibilityMap != null) {
                    return;
                }
                this.importantForAccessibilityMap = new HashMap(childCount);
            }
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = coordinatorLayout.getChildAt(i2);
                if (childAt != this.f7177l.get()) {
                    if (z) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        }
                        if (this.updateImportantForAccessibilityOnSiblings) {
                            intValue = 4;
                            ViewCompat.setImportantForAccessibility(childAt, intValue);
                        }
                    } else if (this.updateImportantForAccessibilityOnSiblings && (map = this.importantForAccessibilityMap) != null && map.containsKey(childAt)) {
                        intValue = this.importantForAccessibilityMap.get(childAt).intValue();
                        ViewCompat.setImportantForAccessibility(childAt, intValue);
                    }
                }
            }
            if (!z) {
                this.importantForAccessibilityMap = null;
            } else if (this.updateImportantForAccessibilityOnSiblings) {
                ((View) this.f7177l.get()).sendAccessibilityEvent(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePeekHeight(boolean z) {
        View view;
        if (this.f7177l != null) {
            calculateCollapsedOffset();
            if (this.f7173h != 4 || (view = (View) this.f7177l.get()) == null) {
                return;
            }
            if (z) {
                settleToStatePendingLayout(this.f7173h);
            } else {
                view.requestLayout();
            }
        }
    }

    public void addBottomSheetCallback(@NonNull BottomSheetCallback bottomSheetCallback) {
        if (this.callbacks.contains(bottomSheetCallback)) {
            return;
        }
        this.callbacks.add(bottomSheetCallback);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public void disableShapeAnimations() {
        this.interpolatorAnimator = null;
    }

    public int getExpandedOffset() {
        if (this.fitToContents) {
            return this.f7167b;
        }
        return Math.max(this.f7166a, this.paddingTopSystemWindowInsets ? 0 : this.insetTop);
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public float getHalfExpandedRatio() {
        return this.f7169d;
    }

    @Px
    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getPeekHeight() {
        if (this.peekHeightAuto) {
            return -1;
        }
        return this.peekHeight;
    }

    public int getSaveFlags() {
        return this.saveFlags;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public int getState() {
        return this.f7173h;
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isHideable() {
        return this.f7172g;
    }

    void n(int i2) {
        float f2;
        float f3;
        View view = (View) this.f7177l.get();
        if (view == null || this.callbacks.isEmpty()) {
            return;
        }
        int i3 = this.f7170e;
        if (i2 > i3 || i3 == getExpandedOffset()) {
            int i4 = this.f7170e;
            f2 = i4 - i2;
            f3 = this.f7176k - i4;
        } else {
            int i5 = this.f7170e;
            f2 = i5 - i2;
            f3 = i5 - getExpandedOffset();
        }
        float f4 = f2 / f3;
        for (int i6 = 0; i6 < this.callbacks.size(); i6++) {
            this.callbacks.get(i6).onSlide(view, f4);
        }
    }

    @Nullable
    @VisibleForTesting
    View o(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View o2 = o(viewGroup.getChildAt(i2));
                if (o2 != null) {
                    return o2;
                }
            }
            return null;
        }
        return null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.f7177l = null;
        this.f7174i = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.f7177l = null;
        this.f7174i = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (!v.isShown() || !this.draggable) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.initialY = (int) motionEvent.getY();
            if (this.f7173h != 2) {
                WeakReference weakReference = this.f7178m;
                View view = weakReference != null ? (View) weakReference.get() : null;
                if (view != null && coordinatorLayout.isPointInChildBounds(view, x, this.initialY)) {
                    this.f7179n = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.f7180o = true;
                }
            }
            this.ignoreEvents = this.f7179n == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.initialY);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.f7180o = false;
            this.f7179n = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (this.ignoreEvents || (viewDragHelper = this.f7174i) == null || !viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            WeakReference weakReference2 = this.f7178m;
            View view2 = weakReference2 != null ? (View) weakReference2.get() : null;
            return (actionMasked != 2 || view2 == null || this.ignoreEvents || this.f7173h == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.f7174i == null || Math.abs(((float) this.initialY) - motionEvent.getY()) <= ((float) this.f7174i.getTouchSlop())) ? false : true;
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull final V v, int i2) {
        int i3;
        MaterialShapeDrawable materialShapeDrawable;
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v)) {
            v.setFitsSystemWindows(true);
        }
        if (this.f7177l == null) {
            this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            setWindowInsetsListener(v);
            this.f7177l = new WeakReference(v);
            if (this.shapeThemingEnabled && (materialShapeDrawable = this.materialShapeDrawable) != null) {
                ViewCompat.setBackground(v, materialShapeDrawable);
            }
            MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
            if (materialShapeDrawable2 != null) {
                float f2 = this.f7171f;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.getElevation(v);
                }
                materialShapeDrawable2.setElevation(f2);
                boolean z = this.f7173h == 3;
                this.isShapeExpanded = z;
                this.materialShapeDrawable.setInterpolation(z ? 0.0f : 1.0f);
            }
            updateAccessibilityActions();
            if (ViewCompat.getImportantForAccessibility(v) == 0) {
                ViewCompat.setImportantForAccessibility(v, 1);
            }
            int measuredWidth = v.getMeasuredWidth();
            int i4 = this.maxWidth;
            if (measuredWidth > i4 && i4 != -1) {
                final ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.width = this.maxWidth;
                v.post(new Runnable(this) { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                    @Override // java.lang.Runnable
                    public void run() {
                        v.setLayoutParams(layoutParams);
                    }
                });
            }
        }
        if (this.f7174i == null) {
            this.f7174i = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i2);
        this.f7175j = coordinatorLayout.getWidth();
        this.f7176k = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.childHeight = height;
        int i5 = this.f7176k;
        int i6 = i5 - height;
        int i7 = this.insetTop;
        if (i6 < i7) {
            if (this.paddingTopSystemWindowInsets) {
                this.childHeight = i5;
            } else {
                this.childHeight = i5 - i7;
            }
        }
        this.f7167b = Math.max(0, i5 - this.childHeight);
        calculateHalfExpandedOffset();
        calculateCollapsedOffset();
        int i8 = this.f7173h;
        if (i8 == 3) {
            i3 = getExpandedOffset();
        } else if (i8 == 6) {
            i3 = this.f7168c;
        } else if (this.f7172g && i8 == 5) {
            i3 = this.f7176k;
        } else if (i8 != 4) {
            if (i8 == 1 || i8 == 2) {
                ViewCompat.offsetTopAndBottom(v, top - v.getTop());
            }
            this.f7178m = new WeakReference(o(v));
            return true;
        } else {
            i3 = this.f7170e;
        }
        ViewCompat.offsetTopAndBottom(v, i3);
        this.f7178m = new WeakReference(o(v));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, float f2, float f3) {
        WeakReference weakReference = this.f7178m;
        if (weakReference == null || view != weakReference.get()) {
            return false;
        }
        return this.f7173h != 3 || super.onNestedPreFling(coordinatorLayout, v, view, f2, f3);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i2, int i3, @NonNull int[] iArr, int i4) {
        int i5;
        if (i4 == 1) {
            return;
        }
        WeakReference weakReference = this.f7178m;
        if (view != (weakReference != null ? (View) weakReference.get() : null)) {
            return;
        }
        int top = v.getTop();
        int i6 = top - i3;
        if (i3 > 0) {
            if (i6 < getExpandedOffset()) {
                iArr[1] = top - getExpandedOffset();
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                i5 = 3;
                q(i5);
            } else if (!this.draggable) {
                return;
            } else {
                iArr[1] = i3;
                ViewCompat.offsetTopAndBottom(v, -i3);
                q(1);
            }
        } else if (i3 < 0 && !view.canScrollVertically(-1)) {
            int i7 = this.f7170e;
            if (i6 > i7 && !this.f7172g) {
                iArr[1] = top - i7;
                ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                i5 = 4;
                q(i5);
            } else if (!this.draggable) {
                return;
            } else {
                iArr[1] = i3;
                ViewCompat.offsetTopAndBottom(v, -i3);
                q(1);
            }
        }
        n(v.getTop());
        this.lastNestedScrollDy = i3;
        this.nestedScrolled = true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i2, int i3, int i4, int i5, int i6, @NonNull int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v, savedState.getSuperState());
        restoreOptionalState(savedState);
        int i2 = savedState.f7192a;
        this.f7173h = (i2 == 1 || i2 == 2) ? 4 : 4;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    @NonNull
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v), (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, @NonNull View view2, int i2, int i3) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (i2 & 2) != 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x006b, code lost:
        if (java.lang.Math.abs(r3 - r2.f7167b) < java.lang.Math.abs(r3 - r2.f7170e)) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x007a, code lost:
        if (r3 < java.lang.Math.abs(r3 - r2.f7170e)) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x008a, code lost:
        if (java.lang.Math.abs(r3 - r1) < java.lang.Math.abs(r3 - r2.f7170e)) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
        if (java.lang.Math.abs(r3 - r2.f7168c) < java.lang.Math.abs(r3 - r2.f7170e)) goto L40;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onStopNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull View view, int i2) {
        int i3;
        int i4 = 3;
        if (v.getTop() == getExpandedOffset()) {
            q(3);
            return;
        }
        WeakReference weakReference = this.f7178m;
        if (weakReference != null && view == weakReference.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                if (!this.fitToContents) {
                    int top = v.getTop();
                    int i5 = this.f7168c;
                    if (top > i5) {
                        i3 = i5;
                        i4 = 6;
                    }
                    i3 = getExpandedOffset();
                }
                i3 = this.f7167b;
            } else if (this.f7172g && s(v, getYVelocity())) {
                i3 = this.f7176k;
                i4 = 5;
            } else if (this.lastNestedScrollDy == 0) {
                int top2 = v.getTop();
                if (!this.fitToContents) {
                    int i6 = this.f7168c;
                    if (top2 < i6) {
                    }
                    i3 = this.f7168c;
                    i4 = 6;
                }
                i3 = this.f7170e;
                i4 = 4;
            } else {
                if (!this.fitToContents) {
                    int top3 = v.getTop();
                }
                i3 = this.f7170e;
                i4 = 4;
            }
            t(v, i4, i3, false);
            this.nestedScrolled = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v, @NonNull MotionEvent motionEvent) {
        if (v.isShown()) {
            int actionMasked = motionEvent.getActionMasked();
            if (this.f7173h == 1 && actionMasked == 0) {
                return true;
            }
            ViewDragHelper viewDragHelper = this.f7174i;
            if (viewDragHelper != null) {
                viewDragHelper.processTouchEvent(motionEvent);
            }
            if (actionMasked == 0) {
                reset();
            }
            if (this.velocityTracker == null) {
                this.velocityTracker = VelocityTracker.obtain();
            }
            this.velocityTracker.addMovement(motionEvent);
            if (this.f7174i != null && actionMasked == 2 && !this.ignoreEvents && Math.abs(this.initialY - motionEvent.getY()) > this.f7174i.getTouchSlop()) {
                this.f7174i.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
            return !this.ignoreEvents;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MaterialShapeDrawable p() {
        return this.materialShapeDrawable;
    }

    void q(int i2) {
        View view;
        if (this.f7173h == i2) {
            return;
        }
        this.f7173h = i2;
        WeakReference weakReference = this.f7177l;
        if (weakReference == null || (view = (View) weakReference.get()) == null) {
            return;
        }
        if (i2 == 3) {
            updateImportantForAccessibility(true);
        } else if (i2 == 6 || i2 == 5 || i2 == 4) {
            updateImportantForAccessibility(false);
        }
        updateDrawableForTargetState(i2);
        for (int i3 = 0; i3 < this.callbacks.size(); i3++) {
            this.callbacks.get(i3).onStateChanged(view, i2);
        }
        updateAccessibilityActions();
    }

    void r(@NonNull View view, int i2) {
        int i3;
        int i4;
        if (i2 == 4) {
            i3 = this.f7170e;
        } else if (i2 == 6) {
            int i5 = this.f7168c;
            if (!this.fitToContents || i5 > (i4 = this.f7167b)) {
                i3 = i5;
            } else {
                i2 = 3;
                i3 = i4;
            }
        } else if (i2 == 3) {
            i3 = getExpandedOffset();
        } else if (!this.f7172g || i2 != 5) {
            throw new IllegalArgumentException("Illegal state argument: " + i2);
        } else {
            i3 = this.f7176k;
        }
        t(view, i2, i3, false);
    }

    public void removeBottomSheetCallback(@NonNull BottomSheetCallback bottomSheetCallback) {
        this.callbacks.remove(bottomSheetCallback);
    }

    boolean s(@NonNull View view, float f2) {
        if (this.skipCollapsed) {
            return true;
        }
        if (view.getTop() < this.f7170e) {
            return false;
        }
        return Math.abs((((float) view.getTop()) + (f2 * 0.1f)) - ((float) this.f7170e)) / ((float) calculatePeekHeight()) > 0.5f;
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callbacks.clear();
        if (bottomSheetCallback != null) {
            this.callbacks.add(bottomSheetCallback);
        }
    }

    public void setDraggable(boolean z) {
        this.draggable = z;
    }

    public void setExpandedOffset(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.f7166a = i2;
    }

    public void setFitToContents(boolean z) {
        if (this.fitToContents == z) {
            return;
        }
        this.fitToContents = z;
        if (this.f7177l != null) {
            calculateCollapsedOffset();
        }
        q((this.fitToContents && this.f7173h == 6) ? 3 : this.f7173h);
        updateAccessibilityActions();
    }

    public void setGestureInsetBottomIgnored(boolean z) {
        this.gestureInsetBottomIgnored = z;
    }

    public void setHalfExpandedRatio(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.f7169d = f2;
        if (this.f7177l != null) {
            calculateHalfExpandedOffset();
        }
    }

    public void setHideable(boolean z) {
        if (this.f7172g != z) {
            this.f7172g = z;
            if (!z && this.f7173h == 5) {
                setState(4);
            }
            updateAccessibilityActions();
        }
    }

    public void setMaxWidth(@Px int i2) {
        this.maxWidth = i2;
    }

    public void setPeekHeight(int i2) {
        setPeekHeight(i2, false);
    }

    public final void setPeekHeight(int i2, boolean z) {
        boolean z2 = true;
        if (i2 == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
            }
            z2 = false;
        } else {
            if (this.peekHeightAuto || this.peekHeight != i2) {
                this.peekHeightAuto = false;
                this.peekHeight = Math.max(0, i2);
            }
            z2 = false;
        }
        if (z2) {
            updatePeekHeight(z);
        }
    }

    public void setSaveFlags(int i2) {
        this.saveFlags = i2;
    }

    public void setSkipCollapsed(boolean z) {
        this.skipCollapsed = z;
    }

    public void setState(int i2) {
        if (i2 == this.f7173h) {
            return;
        }
        if (this.f7177l != null) {
            settleToStatePendingLayout(i2);
        } else if (i2 == 4 || i2 == 3 || i2 == 6 || (this.f7172g && i2 == 5)) {
            this.f7173h = i2;
        }
    }

    public void setUpdateImportantForAccessibilityOnSiblings(boolean z) {
        this.updateImportantForAccessibilityOnSiblings = z;
    }

    void t(View view, int i2, int i3, boolean z) {
        ViewDragHelper viewDragHelper = this.f7174i;
        if (!(viewDragHelper != null && (!z ? !viewDragHelper.smoothSlideViewTo(view, view.getLeft(), i3) : !viewDragHelper.settleCapturedViewAt(view.getLeft(), i3)))) {
            q(i2);
            return;
        }
        q(2);
        updateDrawableForTargetState(i2);
        if (this.settleRunnable == null) {
            this.settleRunnable = new SettleRunnable(view, i2);
        }
        if (((SettleRunnable) this.settleRunnable).isPosted) {
            this.settleRunnable.f7197a = i2;
            return;
        }
        BottomSheetBehavior<V>.SettleRunnable settleRunnable = this.settleRunnable;
        settleRunnable.f7197a = i2;
        ViewCompat.postOnAnimation(view, settleRunnable);
        ((SettleRunnable) this.settleRunnable).isPosted = true;
    }
}
