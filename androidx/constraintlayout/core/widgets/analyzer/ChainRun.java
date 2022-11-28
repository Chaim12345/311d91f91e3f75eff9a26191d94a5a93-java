package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {
    private int chainStyle;

    /* renamed from: g  reason: collision with root package name */
    ArrayList f1975g;

    public ChainRun(ConstraintWidget constraintWidget, int i2) {
        super(constraintWidget);
        this.f1975g = new ArrayList();
        this.orientation = i2;
        build();
    }

    private void build() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.f2003a;
        do {
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget2.getPreviousChainMember(this.orientation);
        } while (constraintWidget2 != null);
        this.f2003a = constraintWidget;
        this.f1975g.add(constraintWidget.getRun(this.orientation));
        ConstraintWidget nextChainMember = constraintWidget.getNextChainMember(this.orientation);
        while (nextChainMember != null) {
            this.f1975g.add(nextChainMember.getRun(this.orientation));
            nextChainMember = nextChainMember.getNextChainMember(this.orientation);
        }
        Iterator it = this.f1975g.iterator();
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            int i2 = this.orientation;
            if (i2 == 0) {
                widgetRun.f2003a.horizontalChainRun = this;
            } else if (i2 == 1) {
                widgetRun.f2003a.verticalChainRun = this;
            }
        }
        if ((this.orientation == 0 && ((ConstraintWidgetContainer) this.f2003a.getParent()).isRtl()) && this.f1975g.size() > 1) {
            ArrayList arrayList = this.f1975g;
            this.f2003a = ((WidgetRun) arrayList.get(arrayList.size() - 1)).f2003a;
        }
        this.chainStyle = this.orientation == 0 ? this.f2003a.getHorizontalChainStyle() : this.f2003a.getVerticalChainStyle();
    }

    private ConstraintWidget getFirstVisibleWidget() {
        for (int i2 = 0; i2 < this.f1975g.size(); i2++) {
            WidgetRun widgetRun = (WidgetRun) this.f1975g.get(i2);
            if (widgetRun.f2003a.getVisibility() != 8) {
                return widgetRun.f2003a;
            }
        }
        return null;
    }

    private ConstraintWidget getLastVisibleWidget() {
        for (int size = this.f1975g.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = (WidgetRun) this.f1975g.get(size);
            if (widgetRun.f2003a.getVisibility() != 8) {
                return widgetRun.f2003a;
            }
        }
        return null;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        for (int i2 = 0; i2 < this.f1975g.size(); i2++) {
            ((WidgetRun) this.f1975g.get(i2)).applyToWidget();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x006d, code lost:
        if (r1 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a3, code lost:
        if (r1 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a5, code lost:
        a(r5.end, r1, -r0);
     */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    void c() {
        DependencyNode g2;
        int margin;
        Iterator it = this.f1975g.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).c();
        }
        int size = this.f1975g.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = ((WidgetRun) this.f1975g.get(0)).f2003a;
        ConstraintWidget constraintWidget2 = ((WidgetRun) this.f1975g.get(size - 1)).f2003a;
        if (this.orientation == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.mRight;
            DependencyNode g3 = g(constraintAnchor, 0);
            int margin2 = constraintAnchor.getMargin();
            ConstraintWidget firstVisibleWidget = getFirstVisibleWidget();
            if (firstVisibleWidget != null) {
                margin2 = firstVisibleWidget.mLeft.getMargin();
            }
            if (g3 != null) {
                a(this.start, g3, margin2);
            }
            g2 = g(constraintAnchor2, 0);
            margin = constraintAnchor2.getMargin();
            ConstraintWidget lastVisibleWidget = getLastVisibleWidget();
            if (lastVisibleWidget != null) {
                margin = lastVisibleWidget.mRight.getMargin();
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.mBottom;
            DependencyNode g4 = g(constraintAnchor3, 1);
            int margin3 = constraintAnchor3.getMargin();
            ConstraintWidget firstVisibleWidget2 = getFirstVisibleWidget();
            if (firstVisibleWidget2 != null) {
                margin3 = firstVisibleWidget2.mTop.getMargin();
            }
            if (g4 != null) {
                a(this.start, g4, margin3);
            }
            g2 = g(constraintAnchor4, 1);
            margin = constraintAnchor4.getMargin();
            ConstraintWidget lastVisibleWidget2 = getLastVisibleWidget();
            if (lastVisibleWidget2 != null) {
                margin = lastVisibleWidget2.mBottom.getMargin();
            }
        }
        this.start.updateDelegate = this;
        this.end.updateDelegate = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        this.f2004b = null;
        Iterator it = this.f1975g.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).d();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long getWrapDimension() {
        int size = this.f1975g.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            WidgetRun widgetRun = (WidgetRun) this.f1975g.get(i2);
            j2 = j2 + widgetRun.start.f1979c + widgetRun.getWrapDimension() + widgetRun.end.f1979c;
        }
        return j2;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean h() {
        int size = this.f1975g.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((WidgetRun) this.f1975g.get(i2)).h()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.orientation == 0 ? "horizontal : " : "vertical : ");
        Iterator it = this.f1975g.iterator();
        while (it.hasNext()) {
            sb.append("<");
            sb.append((WidgetRun) it.next());
            sb.append("> ");
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:275:0x03d1, code lost:
        r7 = r7 - r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00e9  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void update(Dependency dependency) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        float f2;
        boolean z;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z2;
        int i11;
        int i12;
        float f3;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        if (this.start.resolved && this.end.resolved) {
            ConstraintWidget parent = this.f2003a.getParent();
            boolean isRtl = parent instanceof ConstraintWidgetContainer ? ((ConstraintWidgetContainer) parent).isRtl() : false;
            int i18 = this.end.value - this.start.value;
            int size = this.f1975g.size();
            int i19 = 0;
            while (true) {
                i2 = -1;
                i3 = 8;
                if (i19 >= size) {
                    i19 = -1;
                    break;
                } else if (((WidgetRun) this.f1975g.get(i19)).f2003a.getVisibility() != 8) {
                    break;
                } else {
                    i19++;
                }
            }
            int i20 = size - 1;
            int i21 = i20;
            while (true) {
                if (i21 < 0) {
                    break;
                }
                if (((WidgetRun) this.f1975g.get(i21)).f2003a.getVisibility() != 8) {
                    i2 = i21;
                    break;
                }
                i21--;
            }
            int i22 = 0;
            while (i22 < 2) {
                int i23 = 0;
                i5 = 0;
                i6 = 0;
                int i24 = 0;
                f2 = 0.0f;
                while (i23 < size) {
                    WidgetRun widgetRun = (WidgetRun) this.f1975g.get(i23);
                    if (widgetRun.f2003a.getVisibility() != i3) {
                        i24++;
                        if (i23 > 0 && i23 >= i19) {
                            i5 += widgetRun.start.f1979c;
                        }
                        DimensionDependency dimensionDependency = widgetRun.f2006d;
                        int i25 = dimensionDependency.value;
                        boolean z3 = widgetRun.f2005c != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT;
                        if (z3) {
                            int i26 = this.orientation;
                            if (i26 == 0 && !widgetRun.f2003a.horizontalRun.f2006d.resolved) {
                                return;
                            }
                            if (i26 == 1 && !widgetRun.f2003a.verticalRun.f2006d.resolved) {
                                return;
                            }
                            i16 = i25;
                        } else {
                            i16 = i25;
                            if (widgetRun.matchConstraintsType == 1 && i22 == 0) {
                                i17 = dimensionDependency.wrapValue;
                                i6++;
                            } else if (dimensionDependency.resolved) {
                                i17 = i16;
                            }
                            z3 = true;
                            if (z3) {
                                i6++;
                                float f4 = widgetRun.f2003a.mWeight[this.orientation];
                                if (f4 >= 0.0f) {
                                    f2 += f4;
                                }
                            } else {
                                i5 += i17;
                            }
                            if (i23 < i20 && i23 < i2) {
                                i5 += -widgetRun.end.f1979c;
                            }
                        }
                        i17 = i16;
                        if (z3) {
                        }
                        if (i23 < i20) {
                            i5 += -widgetRun.end.f1979c;
                        }
                    }
                    i23++;
                    i3 = 8;
                }
                if (i5 < i18 || i6 == 0) {
                    i4 = i24;
                    break;
                } else {
                    i22++;
                    i3 = 8;
                }
            }
            i4 = 0;
            i5 = 0;
            i6 = 0;
            f2 = 0.0f;
            int i27 = this.start.value;
            if (isRtl) {
                i27 = this.end.value;
            }
            if (i5 > i18) {
                int i28 = (int) (((i5 - i18) / 2.0f) + 0.5f);
                i27 = isRtl ? i27 + i28 : i27 - i28;
            }
            if (i6 > 0) {
                float f5 = i18 - i5;
                int i29 = (int) ((f5 / i6) + 0.5f);
                int i30 = 0;
                int i31 = 0;
                while (i30 < size) {
                    WidgetRun widgetRun2 = (WidgetRun) this.f1975g.get(i30);
                    int i32 = i29;
                    int i33 = i5;
                    if (widgetRun2.f2003a.getVisibility() != 8 && widgetRun2.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        DimensionDependency dimensionDependency2 = widgetRun2.f2006d;
                        if (!dimensionDependency2.resolved) {
                            if (f2 > 0.0f) {
                                i12 = i27;
                                i13 = (int) (((widgetRun2.f2003a.mWeight[this.orientation] * f5) / f2) + 0.5f);
                            } else {
                                i12 = i27;
                                i13 = i32;
                            }
                            if (this.orientation == 0) {
                                ConstraintWidget constraintWidget = widgetRun2.f2003a;
                                f3 = f5;
                                i15 = constraintWidget.mMatchConstraintMaxWidth;
                                i14 = constraintWidget.mMatchConstraintMinWidth;
                                z2 = isRtl;
                            } else {
                                f3 = f5;
                                ConstraintWidget constraintWidget2 = widgetRun2.f2003a;
                                int i34 = constraintWidget2.mMatchConstraintMaxHeight;
                                z2 = isRtl;
                                i14 = constraintWidget2.mMatchConstraintMinHeight;
                                i15 = i34;
                            }
                            i11 = i4;
                            int max = Math.max(i14, widgetRun2.matchConstraintsType == 1 ? Math.min(i13, dimensionDependency2.wrapValue) : i13);
                            if (i15 > 0) {
                                max = Math.min(i15, max);
                            }
                            if (max != i13) {
                                i31++;
                                i13 = max;
                            }
                            widgetRun2.f2006d.resolve(i13);
                            i30++;
                            i29 = i32;
                            i5 = i33;
                            i27 = i12;
                            f5 = f3;
                            isRtl = z2;
                            i4 = i11;
                        }
                    }
                    z2 = isRtl;
                    i11 = i4;
                    i12 = i27;
                    f3 = f5;
                    i30++;
                    i29 = i32;
                    i5 = i33;
                    i27 = i12;
                    f5 = f3;
                    isRtl = z2;
                    i4 = i11;
                }
                z = isRtl;
                i7 = i4;
                i8 = i27;
                int i35 = i5;
                if (i31 > 0) {
                    i6 -= i31;
                    int i36 = 0;
                    for (int i37 = 0; i37 < size; i37++) {
                        WidgetRun widgetRun3 = (WidgetRun) this.f1975g.get(i37);
                        if (widgetRun3.f2003a.getVisibility() != 8) {
                            if (i37 > 0 && i37 >= i19) {
                                i36 += widgetRun3.start.f1979c;
                            }
                            i36 += widgetRun3.f2006d.value;
                            if (i37 < i20 && i37 < i2) {
                                i36 += -widgetRun3.end.f1979c;
                            }
                        }
                    }
                    i5 = i36;
                } else {
                    i5 = i35;
                }
                i10 = 2;
                if (this.chainStyle == 2 && i31 == 0) {
                    i9 = 0;
                    this.chainStyle = 0;
                } else {
                    i9 = 0;
                }
            } else {
                z = isRtl;
                i7 = i4;
                i8 = i27;
                i9 = 0;
                i10 = 2;
            }
            if (i5 > i18) {
                this.chainStyle = i10;
            }
            if (i7 > 0 && i6 == 0 && i19 == i2) {
                this.chainStyle = i10;
            }
            int i38 = this.chainStyle;
            int i39 = i7;
            if (i38 == 1) {
                int i40 = i39 > 1 ? (i18 - i5) / (i39 - 1) : i39 == 1 ? (i18 - i5) / 2 : i9;
                if (i6 > 0) {
                    i40 = i9;
                }
                int i41 = i8;
                for (int i42 = i9; i42 < size; i42++) {
                    WidgetRun widgetRun4 = (WidgetRun) this.f1975g.get(z ? size - (i42 + 1) : i42);
                    if (widgetRun4.f2003a.getVisibility() == 8) {
                        widgetRun4.start.resolve(i41);
                        widgetRun4.end.resolve(i41);
                    } else {
                        if (i42 > 0) {
                            i41 = z ? i41 - i40 : i41 + i40;
                        }
                        if (i42 > 0 && i42 >= i19) {
                            int i43 = widgetRun4.start.f1979c;
                            i41 = z ? i41 - i43 : i41 + i43;
                        }
                        (z ? widgetRun4.end : widgetRun4.start).resolve(i41);
                        DimensionDependency dimensionDependency3 = widgetRun4.f2006d;
                        int i44 = dimensionDependency3.value;
                        if (widgetRun4.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun4.matchConstraintsType == 1) {
                            i44 = dimensionDependency3.wrapValue;
                        }
                        i41 = z ? i41 - i44 : i41 + i44;
                        (z ? widgetRun4.start : widgetRun4.end).resolve(i41);
                        widgetRun4.f2007e = true;
                        if (i42 < i20 && i42 < i2) {
                            int i45 = -widgetRun4.end.f1979c;
                            i41 = z ? i41 - i45 : i41 + i45;
                        }
                    }
                }
            } else if (i38 == 0) {
                int i46 = (i18 - i5) / (i39 + 1);
                if (i6 > 0) {
                    i46 = i9;
                }
                int i47 = i8;
                for (int i48 = i9; i48 < size; i48++) {
                    WidgetRun widgetRun5 = (WidgetRun) this.f1975g.get(z ? size - (i48 + 1) : i48);
                    if (widgetRun5.f2003a.getVisibility() == 8) {
                        widgetRun5.start.resolve(i47);
                        widgetRun5.end.resolve(i47);
                    } else {
                        int i49 = z ? i47 - i46 : i47 + i46;
                        if (i48 > 0 && i48 >= i19) {
                            int i50 = widgetRun5.start.f1979c;
                            i49 = z ? i49 - i50 : i49 + i50;
                        }
                        (z ? widgetRun5.end : widgetRun5.start).resolve(i49);
                        DimensionDependency dimensionDependency4 = widgetRun5.f2006d;
                        int i51 = dimensionDependency4.value;
                        if (widgetRun5.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun5.matchConstraintsType == 1) {
                            i51 = Math.min(i51, dimensionDependency4.wrapValue);
                        }
                        i47 = z ? i49 - i51 : i49 + i51;
                        (z ? widgetRun5.start : widgetRun5.end).resolve(i47);
                        if (i48 < i20 && i48 < i2) {
                            int i52 = -widgetRun5.end.f1979c;
                            i47 = z ? i47 - i52 : i47 + i52;
                        }
                    }
                }
            } else if (i38 == 2) {
                float horizontalBiasPercent = this.orientation == 0 ? this.f2003a.getHorizontalBiasPercent() : this.f2003a.getVerticalBiasPercent();
                if (z) {
                    horizontalBiasPercent = 1.0f - horizontalBiasPercent;
                }
                int i53 = (int) (((i18 - i5) * horizontalBiasPercent) + 0.5f);
                if (i53 < 0 || i6 > 0) {
                    i53 = i9;
                }
                int i54 = z ? i8 - i53 : i8 + i53;
                for (int i55 = i9; i55 < size; i55++) {
                    WidgetRun widgetRun6 = (WidgetRun) this.f1975g.get(z ? size - (i55 + 1) : i55);
                    if (widgetRun6.f2003a.getVisibility() == 8) {
                        widgetRun6.start.resolve(i54);
                        widgetRun6.end.resolve(i54);
                    } else {
                        if (i55 > 0 && i55 >= i19) {
                            int i56 = widgetRun6.start.f1979c;
                            i54 = z ? i54 - i56 : i54 + i56;
                        }
                        (z ? widgetRun6.end : widgetRun6.start).resolve(i54);
                        DimensionDependency dimensionDependency5 = widgetRun6.f2006d;
                        int i57 = dimensionDependency5.value;
                        if (widgetRun6.f2005c == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widgetRun6.matchConstraintsType == 1) {
                            i57 = dimensionDependency5.wrapValue;
                        }
                        i54 += i57;
                        (z ? widgetRun6.start : widgetRun6.end).resolve(i54);
                        if (i55 < i20 && i55 < i2) {
                            int i58 = -widgetRun6.end.f1979c;
                            i54 = z ? i54 - i58 : i54 + i58;
                        }
                    }
                }
            }
        }
    }
}
