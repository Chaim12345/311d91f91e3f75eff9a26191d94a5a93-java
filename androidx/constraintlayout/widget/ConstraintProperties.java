package androidx.constraintlayout.widget;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
/* loaded from: classes.dex */
public class ConstraintProperties {
    public static final int BASELINE = 5;
    public static final int BOTTOM = 4;
    public static final int END = 7;
    public static final int LEFT = 1;
    public static final int MATCH_CONSTRAINT = 0;
    public static final int MATCH_CONSTRAINT_SPREAD = 0;
    public static final int MATCH_CONSTRAINT_WRAP = 1;
    public static final int PARENT_ID = 0;
    public static final int RIGHT = 2;
    public static final int START = 6;
    public static final int TOP = 3;
    public static final int UNSET = -1;
    public static final int WRAP_CONTENT = -2;

    /* renamed from: a  reason: collision with root package name */
    ConstraintLayout.LayoutParams f2294a;

    /* renamed from: b  reason: collision with root package name */
    View f2295b;

    public ConstraintProperties(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ConstraintLayout.LayoutParams)) {
            throw new RuntimeException("Only children of ConstraintLayout.LayoutParams supported");
        }
        this.f2294a = (ConstraintLayout.LayoutParams) layoutParams;
        this.f2295b = view;
    }

    private String sideToString(int i2) {
        switch (i2) {
            case 1:
                return "left";
            case 2:
                return "right";
            case 3:
                return "top";
            case 4:
                return "bottom";
            case 5:
                return "baseline";
            case 6:
                return "start";
            case 7:
                return "end";
            default:
                return "undefined";
        }
    }

    public ConstraintProperties addToHorizontalChain(int i2, int i3) {
        connect(1, i2, i2 == 0 ? 1 : 2, 0);
        connect(2, i3, i3 == 0 ? 2 : 1, 0);
        if (i2 != 0) {
            new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i2)).connect(2, this.f2295b.getId(), 1, 0);
        }
        if (i3 != 0) {
            new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i3)).connect(1, this.f2295b.getId(), 2, 0);
        }
        return this;
    }

    public ConstraintProperties addToHorizontalChainRTL(int i2, int i3) {
        connect(6, i2, i2 == 0 ? 6 : 7, 0);
        connect(7, i3, i3 == 0 ? 7 : 6, 0);
        if (i2 != 0) {
            new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i2)).connect(7, this.f2295b.getId(), 6, 0);
        }
        if (i3 != 0) {
            new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i3)).connect(6, this.f2295b.getId(), 7, 0);
        }
        return this;
    }

    public ConstraintProperties addToVerticalChain(int i2, int i3) {
        connect(3, i2, i2 == 0 ? 3 : 4, 0);
        connect(4, i3, i3 == 0 ? 4 : 3, 0);
        if (i2 != 0) {
            new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i2)).connect(4, this.f2295b.getId(), 3, 0);
        }
        if (i3 != 0) {
            new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i3)).connect(3, this.f2295b.getId(), 4, 0);
        }
        return this;
    }

    public ConstraintProperties alpha(float f2) {
        this.f2295b.setAlpha(f2);
        return this;
    }

    public void apply() {
    }

    public ConstraintProperties center(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        if (i4 >= 0) {
            if (i7 >= 0) {
                if (f2 <= 0.0f || f2 > 1.0f) {
                    throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
                }
                int i8 = 2;
                int i9 = 1;
                if (i3 != 1 && i3 != 2) {
                    i8 = 7;
                    i9 = 6;
                    if (i3 != 6 && i3 != 7) {
                        connect(3, i2, i3, i4);
                        connect(4, i5, i6, i7);
                        this.f2294a.verticalBias = f2;
                        return this;
                    }
                }
                connect(i9, i2, i3, i4);
                connect(i8, i5, i6, i7);
                this.f2294a.horizontalBias = f2;
                return this;
            }
            throw new IllegalArgumentException("margin must be > 0");
        }
        throw new IllegalArgumentException("margin must be > 0");
    }

    public ConstraintProperties centerHorizontally(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        float f2;
        ConstraintProperties constraintProperties;
        int i7;
        int i8;
        if (i2 == 0) {
            i7 = 0;
            i3 = 1;
            i4 = 0;
            i8 = 0;
            i5 = 2;
            i6 = 0;
            f2 = 0.5f;
            constraintProperties = this;
        } else {
            i3 = 2;
            i4 = 0;
            i5 = 1;
            i6 = 0;
            f2 = 0.5f;
            constraintProperties = this;
            i7 = i2;
            i8 = i2;
        }
        constraintProperties.center(i7, i3, i4, i8, i5, i6, f2);
        return this;
    }

    public ConstraintProperties centerHorizontally(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(1, i2, i3, i4);
        connect(2, i5, i6, i7);
        this.f2294a.horizontalBias = f2;
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        float f2;
        ConstraintProperties constraintProperties;
        int i7;
        int i8;
        if (i2 == 0) {
            i7 = 0;
            i3 = 6;
            i4 = 0;
            i8 = 0;
            i5 = 7;
            i6 = 0;
            f2 = 0.5f;
            constraintProperties = this;
        } else {
            i3 = 7;
            i4 = 0;
            i5 = 6;
            i6 = 0;
            f2 = 0.5f;
            constraintProperties = this;
            i7 = i2;
            i8 = i2;
        }
        constraintProperties.center(i7, i3, i4, i8, i5, i6, f2);
        return this;
    }

    public ConstraintProperties centerHorizontallyRtl(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(6, i2, i3, i4);
        connect(7, i5, i6, i7);
        this.f2294a.horizontalBias = f2;
        return this;
    }

    public ConstraintProperties centerVertically(int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        float f2;
        ConstraintProperties constraintProperties;
        int i7;
        int i8;
        if (i2 == 0) {
            i7 = 0;
            i3 = 3;
            i4 = 0;
            i8 = 0;
            i5 = 4;
            i6 = 0;
            f2 = 0.5f;
            constraintProperties = this;
        } else {
            i3 = 4;
            i4 = 0;
            i5 = 3;
            i6 = 0;
            f2 = 0.5f;
            constraintProperties = this;
            i7 = i2;
            i8 = i2;
        }
        constraintProperties.center(i7, i3, i4, i8, i5, i6, f2);
        return this;
    }

    public ConstraintProperties centerVertically(int i2, int i3, int i4, int i5, int i6, int i7, float f2) {
        connect(3, i2, i3, i4);
        connect(4, i5, i6, i7);
        this.f2294a.verticalBias = f2;
        return this;
    }

    public ConstraintProperties connect(int i2, int i3, int i4, int i5) {
        ConstraintLayout.LayoutParams layoutParams;
        ConstraintLayout.LayoutParams layoutParams2;
        ConstraintLayout.LayoutParams layoutParams3;
        switch (i2) {
            case 1:
                if (i4 == 1) {
                    ConstraintLayout.LayoutParams layoutParams4 = this.f2294a;
                    layoutParams4.leftToLeft = i3;
                    layoutParams4.leftToRight = -1;
                } else if (i4 != 2) {
                    throw new IllegalArgumentException("Left to " + sideToString(i4) + " undefined");
                } else {
                    ConstraintLayout.LayoutParams layoutParams5 = this.f2294a;
                    layoutParams5.leftToRight = i3;
                    layoutParams5.leftToLeft = -1;
                }
                ((ViewGroup.MarginLayoutParams) this.f2294a).leftMargin = i5;
                break;
            case 2:
                if (i4 == 1) {
                    ConstraintLayout.LayoutParams layoutParams6 = this.f2294a;
                    layoutParams6.rightToLeft = i3;
                    layoutParams6.rightToRight = -1;
                } else if (i4 != 2) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                } else {
                    ConstraintLayout.LayoutParams layoutParams7 = this.f2294a;
                    layoutParams7.rightToRight = i3;
                    layoutParams7.rightToLeft = -1;
                }
                ((ViewGroup.MarginLayoutParams) this.f2294a).rightMargin = i5;
                break;
            case 3:
                if (i4 == 3) {
                    layoutParams = this.f2294a;
                    layoutParams.topToTop = i3;
                    layoutParams.topToBottom = -1;
                } else if (i4 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                } else {
                    layoutParams = this.f2294a;
                    layoutParams.topToBottom = i3;
                    layoutParams.topToTop = -1;
                }
                layoutParams.baselineToBaseline = -1;
                layoutParams.baselineToTop = -1;
                layoutParams.baselineToBottom = -1;
                ((ViewGroup.MarginLayoutParams) this.f2294a).topMargin = i5;
                break;
            case 4:
                if (i4 == 4) {
                    layoutParams2 = this.f2294a;
                    layoutParams2.bottomToBottom = i3;
                    layoutParams2.bottomToTop = -1;
                } else if (i4 != 3) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                } else {
                    layoutParams2 = this.f2294a;
                    layoutParams2.bottomToTop = i3;
                    layoutParams2.bottomToBottom = -1;
                }
                layoutParams2.baselineToBaseline = -1;
                layoutParams2.baselineToTop = -1;
                layoutParams2.baselineToBottom = -1;
                ((ViewGroup.MarginLayoutParams) this.f2294a).bottomMargin = i5;
                break;
            case 5:
                if (i4 == 5) {
                    ConstraintLayout.LayoutParams layoutParams8 = this.f2294a;
                    layoutParams8.baselineToBaseline = i3;
                    layoutParams8.bottomToBottom = -1;
                    layoutParams8.bottomToTop = -1;
                    layoutParams8.topToTop = -1;
                    layoutParams8.topToBottom = -1;
                }
                if (i4 == 3) {
                    layoutParams3 = this.f2294a;
                    layoutParams3.baselineToTop = i3;
                } else if (i4 != 4) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                } else {
                    layoutParams3 = this.f2294a;
                    layoutParams3.baselineToBottom = i3;
                }
                layoutParams3.bottomToBottom = -1;
                layoutParams3.bottomToTop = -1;
                layoutParams3.topToTop = -1;
                layoutParams3.topToBottom = -1;
                this.f2294a.baselineMargin = i5;
                break;
            case 6:
                if (i4 == 6) {
                    ConstraintLayout.LayoutParams layoutParams9 = this.f2294a;
                    layoutParams9.startToStart = i3;
                    layoutParams9.startToEnd = -1;
                } else if (i4 != 7) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                } else {
                    ConstraintLayout.LayoutParams layoutParams10 = this.f2294a;
                    layoutParams10.startToEnd = i3;
                    layoutParams10.startToStart = -1;
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    this.f2294a.setMarginStart(i5);
                    break;
                }
                break;
            case 7:
                if (i4 == 7) {
                    ConstraintLayout.LayoutParams layoutParams11 = this.f2294a;
                    layoutParams11.endToEnd = i3;
                    layoutParams11.endToStart = -1;
                } else if (i4 != 6) {
                    throw new IllegalArgumentException("right to " + sideToString(i4) + " undefined");
                } else {
                    ConstraintLayout.LayoutParams layoutParams12 = this.f2294a;
                    layoutParams12.endToStart = i3;
                    layoutParams12.endToEnd = -1;
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    this.f2294a.setMarginEnd(i5);
                    break;
                }
                break;
            default:
                throw new IllegalArgumentException(sideToString(i2) + " to " + sideToString(i4) + " unknown");
        }
        return this;
    }

    public ConstraintProperties constrainDefaultHeight(int i2) {
        this.f2294a.matchConstraintDefaultHeight = i2;
        return this;
    }

    public ConstraintProperties constrainDefaultWidth(int i2) {
        this.f2294a.matchConstraintDefaultWidth = i2;
        return this;
    }

    public ConstraintProperties constrainHeight(int i2) {
        ((ViewGroup.MarginLayoutParams) this.f2294a).height = i2;
        return this;
    }

    public ConstraintProperties constrainMaxHeight(int i2) {
        this.f2294a.matchConstraintMaxHeight = i2;
        return this;
    }

    public ConstraintProperties constrainMaxWidth(int i2) {
        this.f2294a.matchConstraintMaxWidth = i2;
        return this;
    }

    public ConstraintProperties constrainMinHeight(int i2) {
        this.f2294a.matchConstraintMinHeight = i2;
        return this;
    }

    public ConstraintProperties constrainMinWidth(int i2) {
        this.f2294a.matchConstraintMinWidth = i2;
        return this;
    }

    public ConstraintProperties constrainWidth(int i2) {
        ((ViewGroup.MarginLayoutParams) this.f2294a).width = i2;
        return this;
    }

    public ConstraintProperties dimensionRatio(String str) {
        this.f2294a.dimensionRatio = str;
        return this;
    }

    public ConstraintProperties elevation(float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2295b.setElevation(f2);
        }
        return this;
    }

    public ConstraintProperties goneMargin(int i2, int i3) {
        switch (i2) {
            case 1:
                this.f2294a.goneLeftMargin = i3;
                break;
            case 2:
                this.f2294a.goneRightMargin = i3;
                break;
            case 3:
                this.f2294a.goneTopMargin = i3;
                break;
            case 4:
                this.f2294a.goneBottomMargin = i3;
                break;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.f2294a.goneStartMargin = i3;
                break;
            case 7:
                this.f2294a.goneEndMargin = i3;
                break;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
        return this;
    }

    public ConstraintProperties horizontalBias(float f2) {
        this.f2294a.horizontalBias = f2;
        return this;
    }

    public ConstraintProperties horizontalChainStyle(int i2) {
        this.f2294a.horizontalChainStyle = i2;
        return this;
    }

    public ConstraintProperties horizontalWeight(float f2) {
        this.f2294a.horizontalWeight = f2;
        return this;
    }

    public ConstraintProperties margin(int i2, int i3) {
        switch (i2) {
            case 1:
                ((ViewGroup.MarginLayoutParams) this.f2294a).leftMargin = i3;
                break;
            case 2:
                ((ViewGroup.MarginLayoutParams) this.f2294a).rightMargin = i3;
                break;
            case 3:
                ((ViewGroup.MarginLayoutParams) this.f2294a).topMargin = i3;
                break;
            case 4:
                ((ViewGroup.MarginLayoutParams) this.f2294a).bottomMargin = i3;
                break;
            case 5:
                throw new IllegalArgumentException("baseline does not support margins");
            case 6:
                this.f2294a.setMarginStart(i3);
                break;
            case 7:
                this.f2294a.setMarginEnd(i3);
                break;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
        return this;
    }

    public ConstraintProperties removeConstraints(int i2) {
        switch (i2) {
            case 1:
                ConstraintLayout.LayoutParams layoutParams = this.f2294a;
                layoutParams.leftToRight = -1;
                layoutParams.leftToLeft = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = -1;
                layoutParams.goneLeftMargin = Integer.MIN_VALUE;
                break;
            case 2:
                ConstraintLayout.LayoutParams layoutParams2 = this.f2294a;
                layoutParams2.rightToRight = -1;
                layoutParams2.rightToLeft = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams2).rightMargin = -1;
                layoutParams2.goneRightMargin = Integer.MIN_VALUE;
                break;
            case 3:
                ConstraintLayout.LayoutParams layoutParams3 = this.f2294a;
                layoutParams3.topToBottom = -1;
                layoutParams3.topToTop = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin = -1;
                layoutParams3.goneTopMargin = Integer.MIN_VALUE;
                break;
            case 4:
                ConstraintLayout.LayoutParams layoutParams4 = this.f2294a;
                layoutParams4.bottomToTop = -1;
                layoutParams4.bottomToBottom = -1;
                ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin = -1;
                layoutParams4.goneBottomMargin = Integer.MIN_VALUE;
                break;
            case 5:
                this.f2294a.baselineToBaseline = -1;
                break;
            case 6:
                ConstraintLayout.LayoutParams layoutParams5 = this.f2294a;
                layoutParams5.startToEnd = -1;
                layoutParams5.startToStart = -1;
                layoutParams5.setMarginStart(-1);
                this.f2294a.goneStartMargin = Integer.MIN_VALUE;
                break;
            case 7:
                ConstraintLayout.LayoutParams layoutParams6 = this.f2294a;
                layoutParams6.endToStart = -1;
                layoutParams6.endToEnd = -1;
                layoutParams6.setMarginEnd(-1);
                this.f2294a.goneEndMargin = Integer.MIN_VALUE;
                break;
            default:
                throw new IllegalArgumentException("unknown constraint");
        }
        return this;
    }

    public ConstraintProperties removeFromHorizontalChain() {
        ConstraintLayout.LayoutParams layoutParams = this.f2294a;
        int i2 = layoutParams.leftToRight;
        int i3 = layoutParams.rightToLeft;
        if (i2 == -1 && i3 == -1) {
            int i4 = layoutParams.startToEnd;
            int i5 = layoutParams.endToStart;
            if (i4 != -1 || i5 != -1) {
                ConstraintProperties constraintProperties = new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i4));
                ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i5));
                ConstraintLayout.LayoutParams layoutParams2 = this.f2294a;
                if (i4 != -1 && i5 != -1) {
                    constraintProperties.connect(7, i5, 6, 0);
                    constraintProperties2.connect(6, i2, 7, 0);
                } else if (i2 != -1 || i5 != -1) {
                    int i6 = layoutParams2.rightToRight;
                    if (i6 != -1) {
                        constraintProperties.connect(7, i6, 7, 0);
                    } else {
                        int i7 = layoutParams2.leftToLeft;
                        if (i7 != -1) {
                            constraintProperties2.connect(6, i7, 6, 0);
                        }
                    }
                }
            }
            removeConstraints(6);
            removeConstraints(7);
        } else {
            ConstraintProperties constraintProperties3 = new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i2));
            ConstraintProperties constraintProperties4 = new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i3));
            ConstraintLayout.LayoutParams layoutParams3 = this.f2294a;
            if (i2 != -1 && i3 != -1) {
                constraintProperties3.connect(2, i3, 1, 0);
                constraintProperties4.connect(1, i2, 2, 0);
            } else if (i2 != -1 || i3 != -1) {
                int i8 = layoutParams3.rightToRight;
                if (i8 != -1) {
                    constraintProperties3.connect(2, i8, 2, 0);
                } else {
                    int i9 = layoutParams3.leftToLeft;
                    if (i9 != -1) {
                        constraintProperties4.connect(1, i9, 1, 0);
                    }
                }
            }
            removeConstraints(1);
            removeConstraints(2);
        }
        return this;
    }

    public ConstraintProperties removeFromVerticalChain() {
        ConstraintLayout.LayoutParams layoutParams = this.f2294a;
        int i2 = layoutParams.topToBottom;
        int i3 = layoutParams.bottomToTop;
        if (i2 != -1 || i3 != -1) {
            ConstraintProperties constraintProperties = new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i2));
            ConstraintProperties constraintProperties2 = new ConstraintProperties(((ViewGroup) this.f2295b.getParent()).findViewById(i3));
            ConstraintLayout.LayoutParams layoutParams2 = this.f2294a;
            if (i2 != -1 && i3 != -1) {
                constraintProperties.connect(4, i3, 3, 0);
                constraintProperties2.connect(3, i2, 4, 0);
            } else if (i2 != -1 || i3 != -1) {
                int i4 = layoutParams2.bottomToBottom;
                if (i4 != -1) {
                    constraintProperties.connect(4, i4, 4, 0);
                } else {
                    int i5 = layoutParams2.topToTop;
                    if (i5 != -1) {
                        constraintProperties2.connect(3, i5, 3, 0);
                    }
                }
            }
        }
        removeConstraints(3);
        removeConstraints(4);
        return this;
    }

    public ConstraintProperties rotation(float f2) {
        this.f2295b.setRotation(f2);
        return this;
    }

    public ConstraintProperties rotationX(float f2) {
        this.f2295b.setRotationX(f2);
        return this;
    }

    public ConstraintProperties rotationY(float f2) {
        this.f2295b.setRotationY(f2);
        return this;
    }

    public ConstraintProperties scaleX(float f2) {
        this.f2295b.setScaleY(f2);
        return this;
    }

    public ConstraintProperties scaleY(float f2) {
        return this;
    }

    public ConstraintProperties transformPivot(float f2, float f3) {
        this.f2295b.setPivotX(f2);
        this.f2295b.setPivotY(f3);
        return this;
    }

    public ConstraintProperties transformPivotX(float f2) {
        this.f2295b.setPivotX(f2);
        return this;
    }

    public ConstraintProperties transformPivotY(float f2) {
        this.f2295b.setPivotY(f2);
        return this;
    }

    public ConstraintProperties translation(float f2, float f3) {
        this.f2295b.setTranslationX(f2);
        this.f2295b.setTranslationY(f3);
        return this;
    }

    public ConstraintProperties translationX(float f2) {
        this.f2295b.setTranslationX(f2);
        return this;
    }

    public ConstraintProperties translationY(float f2) {
        this.f2295b.setTranslationY(f2);
        return this;
    }

    public ConstraintProperties translationZ(float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2295b.setTranslationZ(f2);
        }
        return this;
    }

    public ConstraintProperties verticalBias(float f2) {
        this.f2294a.verticalBias = f2;
        return this;
    }

    public ConstraintProperties verticalChainStyle(int i2) {
        this.f2294a.verticalChainStyle = i2;
        return this;
    }

    public ConstraintProperties verticalWeight(float f2) {
        this.f2294a.verticalWeight = f2;
        return this;
    }

    public ConstraintProperties visibility(int i2) {
        this.f2295b.setVisibility(i2);
        return this;
    }
}
