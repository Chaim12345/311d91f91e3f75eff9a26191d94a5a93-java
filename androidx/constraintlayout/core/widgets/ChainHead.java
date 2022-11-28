package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class ChainHead {

    /* renamed from: a  reason: collision with root package name */
    protected ConstraintWidget f1929a;

    /* renamed from: b  reason: collision with root package name */
    protected ConstraintWidget f1930b;

    /* renamed from: c  reason: collision with root package name */
    protected ConstraintWidget f1931c;

    /* renamed from: d  reason: collision with root package name */
    protected ConstraintWidget f1932d;

    /* renamed from: e  reason: collision with root package name */
    protected ConstraintWidget f1933e;

    /* renamed from: f  reason: collision with root package name */
    protected ConstraintWidget f1934f;

    /* renamed from: g  reason: collision with root package name */
    protected ConstraintWidget f1935g;

    /* renamed from: h  reason: collision with root package name */
    protected ArrayList f1936h;

    /* renamed from: i  reason: collision with root package name */
    protected int f1937i;

    /* renamed from: j  reason: collision with root package name */
    protected int f1938j;

    /* renamed from: k  reason: collision with root package name */
    protected float f1939k = 0.0f;

    /* renamed from: l  reason: collision with root package name */
    int f1940l;

    /* renamed from: m  reason: collision with root package name */
    int f1941m;
    private boolean mDefined;
    private boolean mIsRtl;
    private int mOrientation;

    /* renamed from: n  reason: collision with root package name */
    int f1942n;

    /* renamed from: o  reason: collision with root package name */
    protected boolean f1943o;

    /* renamed from: p  reason: collision with root package name */
    protected boolean f1944p;

    /* renamed from: q  reason: collision with root package name */
    protected boolean f1945q;

    public ChainHead(ConstraintWidget constraintWidget, int i2, boolean z) {
        this.mIsRtl = false;
        this.f1929a = constraintWidget;
        this.mOrientation = i2;
        this.mIsRtl = z;
    }

    private void defineChainProperties() {
        int i2 = this.mOrientation * 2;
        ConstraintWidget constraintWidget = this.f1929a;
        boolean z = false;
        ConstraintWidget constraintWidget2 = constraintWidget;
        boolean z2 = false;
        while (!z2) {
            this.f1937i++;
            ConstraintWidget[] constraintWidgetArr = constraintWidget.I;
            int i3 = this.mOrientation;
            ConstraintWidget constraintWidget3 = null;
            constraintWidgetArr[i3] = null;
            constraintWidget.H[i3] = null;
            if (constraintWidget.getVisibility() != 8) {
                this.f1940l++;
                ConstraintWidget.DimensionBehaviour dimensionBehaviour = constraintWidget.getDimensionBehaviour(this.mOrientation);
                ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                if (dimensionBehaviour != dimensionBehaviour2) {
                    this.f1941m += constraintWidget.getLength(this.mOrientation);
                }
                int margin = this.f1941m + constraintWidget.mListAnchors[i2].getMargin();
                this.f1941m = margin;
                int i4 = i2 + 1;
                this.f1941m = margin + constraintWidget.mListAnchors[i4].getMargin();
                int margin2 = this.f1942n + constraintWidget.mListAnchors[i2].getMargin();
                this.f1942n = margin2;
                this.f1942n = margin2 + constraintWidget.mListAnchors[i4].getMargin();
                if (this.f1930b == null) {
                    this.f1930b = constraintWidget;
                }
                this.f1932d = constraintWidget;
                ConstraintWidget.DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
                int i5 = this.mOrientation;
                if (dimensionBehaviourArr[i5] == dimensionBehaviour2) {
                    int[] iArr = constraintWidget.mResolvedMatchConstraintDefault;
                    if (iArr[i5] == 0 || iArr[i5] == 3 || iArr[i5] == 2) {
                        this.f1938j++;
                        float[] fArr = constraintWidget.mWeight;
                        float f2 = fArr[i5];
                        if (f2 > 0.0f) {
                            this.f1939k += fArr[i5];
                        }
                        if (isMatchConstraintEqualityCandidate(constraintWidget, i5)) {
                            if (f2 < 0.0f) {
                                this.f1943o = true;
                            } else {
                                this.f1944p = true;
                            }
                            if (this.f1936h == null) {
                                this.f1936h = new ArrayList();
                            }
                            this.f1936h.add(constraintWidget);
                        }
                        if (this.f1934f == null) {
                            this.f1934f = constraintWidget;
                        }
                        ConstraintWidget constraintWidget4 = this.f1935g;
                        if (constraintWidget4 != null) {
                            constraintWidget4.H[this.mOrientation] = constraintWidget;
                        }
                        this.f1935g = constraintWidget;
                    }
                    if (this.mOrientation == 0) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mMatchConstraintMinWidth == 0) {
                            int i6 = constraintWidget.mMatchConstraintMaxWidth;
                        }
                    } else if (constraintWidget.mMatchConstraintDefaultHeight == 0 && constraintWidget.mMatchConstraintMinHeight == 0) {
                        int i7 = constraintWidget.mMatchConstraintMaxHeight;
                    }
                }
            }
            if (constraintWidget2 != constraintWidget) {
                constraintWidget2.I[this.mOrientation] = constraintWidget;
            }
            ConstraintAnchor constraintAnchor = constraintWidget.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor != null) {
                ConstraintWidget constraintWidget5 = constraintAnchor.mOwner;
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.mListAnchors;
                if (constraintAnchorArr[i2].mTarget != null && constraintAnchorArr[i2].mTarget.mOwner == constraintWidget) {
                    constraintWidget3 = constraintWidget5;
                }
            }
            if (constraintWidget3 == null) {
                constraintWidget3 = constraintWidget;
                z2 = true;
            }
            constraintWidget2 = constraintWidget;
            constraintWidget = constraintWidget3;
        }
        ConstraintWidget constraintWidget6 = this.f1930b;
        if (constraintWidget6 != null) {
            this.f1941m -= constraintWidget6.mListAnchors[i2].getMargin();
        }
        ConstraintWidget constraintWidget7 = this.f1932d;
        if (constraintWidget7 != null) {
            this.f1941m -= constraintWidget7.mListAnchors[i2 + 1].getMargin();
        }
        this.f1931c = constraintWidget;
        if (this.mOrientation == 0 && this.mIsRtl) {
            this.f1933e = constraintWidget;
        } else {
            this.f1933e = this.f1929a;
        }
        if (this.f1944p && this.f1943o) {
            z = true;
        }
        this.f1945q = z;
    }

    private static boolean isMatchConstraintEqualityCandidate(ConstraintWidget constraintWidget, int i2) {
        if (constraintWidget.getVisibility() != 8 && constraintWidget.mListDimensionBehaviors[i2] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
            int[] iArr = constraintWidget.mResolvedMatchConstraintDefault;
            if (iArr[i2] == 0 || iArr[i2] == 3) {
                return true;
            }
        }
        return false;
    }

    public void define() {
        if (!this.mDefined) {
            defineChainProperties();
        }
        this.mDefined = true;
    }

    public ConstraintWidget getFirst() {
        return this.f1929a;
    }

    public ConstraintWidget getFirstMatchConstraintWidget() {
        return this.f1934f;
    }

    public ConstraintWidget getFirstVisibleWidget() {
        return this.f1930b;
    }

    public ConstraintWidget getHead() {
        return this.f1933e;
    }

    public ConstraintWidget getLast() {
        return this.f1931c;
    }

    public ConstraintWidget getLastMatchConstraintWidget() {
        return this.f1935g;
    }

    public ConstraintWidget getLastVisibleWidget() {
        return this.f1932d;
    }

    public float getTotalWeight() {
        return this.f1939k;
    }
}
