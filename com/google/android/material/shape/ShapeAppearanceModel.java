package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import androidx.annotation.AttrRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import com.google.android.material.R;
/* loaded from: classes2.dex */
public class ShapeAppearanceModel {
    public static final CornerSize PILL = new RelativeCornerSize(0.5f);

    /* renamed from: a  reason: collision with root package name */
    CornerTreatment f7459a;

    /* renamed from: b  reason: collision with root package name */
    CornerTreatment f7460b;

    /* renamed from: c  reason: collision with root package name */
    CornerTreatment f7461c;

    /* renamed from: d  reason: collision with root package name */
    CornerTreatment f7462d;

    /* renamed from: e  reason: collision with root package name */
    CornerSize f7463e;

    /* renamed from: f  reason: collision with root package name */
    CornerSize f7464f;

    /* renamed from: g  reason: collision with root package name */
    CornerSize f7465g;

    /* renamed from: h  reason: collision with root package name */
    CornerSize f7466h;

    /* renamed from: i  reason: collision with root package name */
    EdgeTreatment f7467i;

    /* renamed from: j  reason: collision with root package name */
    EdgeTreatment f7468j;

    /* renamed from: k  reason: collision with root package name */
    EdgeTreatment f7469k;

    /* renamed from: l  reason: collision with root package name */
    EdgeTreatment f7470l;

    /* loaded from: classes2.dex */
    public static final class Builder {
        @NonNull
        private EdgeTreatment bottomEdge;
        @NonNull
        private CornerTreatment bottomLeftCorner;
        @NonNull
        private CornerSize bottomLeftCornerSize;
        @NonNull
        private CornerTreatment bottomRightCorner;
        @NonNull
        private CornerSize bottomRightCornerSize;
        @NonNull
        private EdgeTreatment leftEdge;
        @NonNull
        private EdgeTreatment rightEdge;
        @NonNull
        private EdgeTreatment topEdge;
        @NonNull
        private CornerTreatment topLeftCorner;
        @NonNull
        private CornerSize topLeftCornerSize;
        @NonNull
        private CornerTreatment topRightCorner;
        @NonNull
        private CornerSize topRightCornerSize;

        public Builder() {
            this.topLeftCorner = MaterialShapeUtils.b();
            this.topRightCorner = MaterialShapeUtils.b();
            this.bottomRightCorner = MaterialShapeUtils.b();
            this.bottomLeftCorner = MaterialShapeUtils.b();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = MaterialShapeUtils.c();
            this.rightEdge = MaterialShapeUtils.c();
            this.bottomEdge = MaterialShapeUtils.c();
            this.leftEdge = MaterialShapeUtils.c();
        }

        public Builder(@NonNull ShapeAppearanceModel shapeAppearanceModel) {
            this.topLeftCorner = MaterialShapeUtils.b();
            this.topRightCorner = MaterialShapeUtils.b();
            this.bottomRightCorner = MaterialShapeUtils.b();
            this.bottomLeftCorner = MaterialShapeUtils.b();
            this.topLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomRightCornerSize = new AbsoluteCornerSize(0.0f);
            this.bottomLeftCornerSize = new AbsoluteCornerSize(0.0f);
            this.topEdge = MaterialShapeUtils.c();
            this.rightEdge = MaterialShapeUtils.c();
            this.bottomEdge = MaterialShapeUtils.c();
            this.leftEdge = MaterialShapeUtils.c();
            this.topLeftCorner = shapeAppearanceModel.f7459a;
            this.topRightCorner = shapeAppearanceModel.f7460b;
            this.bottomRightCorner = shapeAppearanceModel.f7461c;
            this.bottomLeftCorner = shapeAppearanceModel.f7462d;
            this.topLeftCornerSize = shapeAppearanceModel.f7463e;
            this.topRightCornerSize = shapeAppearanceModel.f7464f;
            this.bottomRightCornerSize = shapeAppearanceModel.f7465g;
            this.bottomLeftCornerSize = shapeAppearanceModel.f7466h;
            this.topEdge = shapeAppearanceModel.f7467i;
            this.rightEdge = shapeAppearanceModel.f7468j;
            this.bottomEdge = shapeAppearanceModel.f7469k;
            this.leftEdge = shapeAppearanceModel.f7470l;
        }

        private static float compatCornerTreatmentSize(CornerTreatment cornerTreatment) {
            if (cornerTreatment instanceof RoundedCornerTreatment) {
                return ((RoundedCornerTreatment) cornerTreatment).f7458a;
            }
            if (cornerTreatment instanceof CutCornerTreatment) {
                return ((CutCornerTreatment) cornerTreatment).f7454a;
            }
            return -1.0f;
        }

        @NonNull
        public ShapeAppearanceModel build() {
            return new ShapeAppearanceModel(this);
        }

        @NonNull
        public Builder setAllCornerSizes(@Dimension float f2) {
            return setTopLeftCornerSize(f2).setTopRightCornerSize(f2).setBottomRightCornerSize(f2).setBottomLeftCornerSize(f2);
        }

        @NonNull
        public Builder setAllCornerSizes(@NonNull CornerSize cornerSize) {
            return setTopLeftCornerSize(cornerSize).setTopRightCornerSize(cornerSize).setBottomRightCornerSize(cornerSize).setBottomLeftCornerSize(cornerSize);
        }

        @NonNull
        public Builder setAllCorners(int i2, @Dimension float f2) {
            return setAllCorners(MaterialShapeUtils.a(i2)).setAllCornerSizes(f2);
        }

        @NonNull
        public Builder setAllCorners(@NonNull CornerTreatment cornerTreatment) {
            return setTopLeftCorner(cornerTreatment).setTopRightCorner(cornerTreatment).setBottomRightCorner(cornerTreatment).setBottomLeftCorner(cornerTreatment);
        }

        @NonNull
        public Builder setAllEdges(@NonNull EdgeTreatment edgeTreatment) {
            return setLeftEdge(edgeTreatment).setTopEdge(edgeTreatment).setRightEdge(edgeTreatment).setBottomEdge(edgeTreatment);
        }

        @NonNull
        public Builder setBottomEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.bottomEdge = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setBottomLeftCorner(int i2, @Dimension float f2) {
            return setBottomLeftCorner(MaterialShapeUtils.a(i2)).setBottomLeftCornerSize(f2);
        }

        @NonNull
        public Builder setBottomLeftCorner(int i2, @NonNull CornerSize cornerSize) {
            return setBottomLeftCorner(MaterialShapeUtils.a(i2)).setBottomLeftCornerSize(cornerSize);
        }

        @NonNull
        public Builder setBottomLeftCorner(@NonNull CornerTreatment cornerTreatment) {
            this.bottomLeftCorner = cornerTreatment;
            float compatCornerTreatmentSize = compatCornerTreatmentSize(cornerTreatment);
            if (compatCornerTreatmentSize != -1.0f) {
                setBottomLeftCornerSize(compatCornerTreatmentSize);
            }
            return this;
        }

        @NonNull
        public Builder setBottomLeftCornerSize(@Dimension float f2) {
            this.bottomLeftCornerSize = new AbsoluteCornerSize(f2);
            return this;
        }

        @NonNull
        public Builder setBottomLeftCornerSize(@NonNull CornerSize cornerSize) {
            this.bottomLeftCornerSize = cornerSize;
            return this;
        }

        @NonNull
        public Builder setBottomRightCorner(int i2, @Dimension float f2) {
            return setBottomRightCorner(MaterialShapeUtils.a(i2)).setBottomRightCornerSize(f2);
        }

        @NonNull
        public Builder setBottomRightCorner(int i2, @NonNull CornerSize cornerSize) {
            return setBottomRightCorner(MaterialShapeUtils.a(i2)).setBottomRightCornerSize(cornerSize);
        }

        @NonNull
        public Builder setBottomRightCorner(@NonNull CornerTreatment cornerTreatment) {
            this.bottomRightCorner = cornerTreatment;
            float compatCornerTreatmentSize = compatCornerTreatmentSize(cornerTreatment);
            if (compatCornerTreatmentSize != -1.0f) {
                setBottomRightCornerSize(compatCornerTreatmentSize);
            }
            return this;
        }

        @NonNull
        public Builder setBottomRightCornerSize(@Dimension float f2) {
            this.bottomRightCornerSize = new AbsoluteCornerSize(f2);
            return this;
        }

        @NonNull
        public Builder setBottomRightCornerSize(@NonNull CornerSize cornerSize) {
            this.bottomRightCornerSize = cornerSize;
            return this;
        }

        @NonNull
        public Builder setLeftEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.leftEdge = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setRightEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.rightEdge = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setTopEdge(@NonNull EdgeTreatment edgeTreatment) {
            this.topEdge = edgeTreatment;
            return this;
        }

        @NonNull
        public Builder setTopLeftCorner(int i2, @Dimension float f2) {
            return setTopLeftCorner(MaterialShapeUtils.a(i2)).setTopLeftCornerSize(f2);
        }

        @NonNull
        public Builder setTopLeftCorner(int i2, @NonNull CornerSize cornerSize) {
            return setTopLeftCorner(MaterialShapeUtils.a(i2)).setTopLeftCornerSize(cornerSize);
        }

        @NonNull
        public Builder setTopLeftCorner(@NonNull CornerTreatment cornerTreatment) {
            this.topLeftCorner = cornerTreatment;
            float compatCornerTreatmentSize = compatCornerTreatmentSize(cornerTreatment);
            if (compatCornerTreatmentSize != -1.0f) {
                setTopLeftCornerSize(compatCornerTreatmentSize);
            }
            return this;
        }

        @NonNull
        public Builder setTopLeftCornerSize(@Dimension float f2) {
            this.topLeftCornerSize = new AbsoluteCornerSize(f2);
            return this;
        }

        @NonNull
        public Builder setTopLeftCornerSize(@NonNull CornerSize cornerSize) {
            this.topLeftCornerSize = cornerSize;
            return this;
        }

        @NonNull
        public Builder setTopRightCorner(int i2, @Dimension float f2) {
            return setTopRightCorner(MaterialShapeUtils.a(i2)).setTopRightCornerSize(f2);
        }

        @NonNull
        public Builder setTopRightCorner(int i2, @NonNull CornerSize cornerSize) {
            return setTopRightCorner(MaterialShapeUtils.a(i2)).setTopRightCornerSize(cornerSize);
        }

        @NonNull
        public Builder setTopRightCorner(@NonNull CornerTreatment cornerTreatment) {
            this.topRightCorner = cornerTreatment;
            float compatCornerTreatmentSize = compatCornerTreatmentSize(cornerTreatment);
            if (compatCornerTreatmentSize != -1.0f) {
                setTopRightCornerSize(compatCornerTreatmentSize);
            }
            return this;
        }

        @NonNull
        public Builder setTopRightCornerSize(@Dimension float f2) {
            this.topRightCornerSize = new AbsoluteCornerSize(f2);
            return this;
        }

        @NonNull
        public Builder setTopRightCornerSize(@NonNull CornerSize cornerSize) {
            this.topRightCornerSize = cornerSize;
            return this;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes2.dex */
    public interface CornerSizeUnaryOperator {
        @NonNull
        CornerSize apply(@NonNull CornerSize cornerSize);
    }

    public ShapeAppearanceModel() {
        this.f7459a = MaterialShapeUtils.b();
        this.f7460b = MaterialShapeUtils.b();
        this.f7461c = MaterialShapeUtils.b();
        this.f7462d = MaterialShapeUtils.b();
        this.f7463e = new AbsoluteCornerSize(0.0f);
        this.f7464f = new AbsoluteCornerSize(0.0f);
        this.f7465g = new AbsoluteCornerSize(0.0f);
        this.f7466h = new AbsoluteCornerSize(0.0f);
        this.f7467i = MaterialShapeUtils.c();
        this.f7468j = MaterialShapeUtils.c();
        this.f7469k = MaterialShapeUtils.c();
        this.f7470l = MaterialShapeUtils.c();
    }

    private ShapeAppearanceModel(@NonNull Builder builder) {
        this.f7459a = builder.topLeftCorner;
        this.f7460b = builder.topRightCorner;
        this.f7461c = builder.bottomRightCorner;
        this.f7462d = builder.bottomLeftCorner;
        this.f7463e = builder.topLeftCornerSize;
        this.f7464f = builder.topRightCornerSize;
        this.f7465g = builder.bottomRightCornerSize;
        this.f7466h = builder.bottomLeftCornerSize;
        this.f7467i = builder.topEdge;
        this.f7468j = builder.rightEdge;
        this.f7469k = builder.bottomEdge;
        this.f7470l = builder.leftEdge;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public static Builder builder(Context context, @StyleRes int i2, @StyleRes int i3) {
        return builder(context, i2, i3, 0);
    }

    @NonNull
    private static Builder builder(Context context, @StyleRes int i2, @StyleRes int i3, int i4) {
        return builder(context, i2, i3, new AbsoluteCornerSize(i4));
    }

    @NonNull
    private static Builder builder(Context context, @StyleRes int i2, @StyleRes int i3, @NonNull CornerSize cornerSize) {
        if (i3 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i2);
            i2 = i3;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i2, R.styleable.ShapeAppearance);
        try {
            int i4 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamily, 0);
            int i5 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopLeft, i4);
            int i6 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopRight, i4);
            int i7 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomRight, i4);
            int i8 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomLeft, i4);
            CornerSize cornerSize2 = getCornerSize(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSize, cornerSize);
            CornerSize cornerSize3 = getCornerSize(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeTopLeft, cornerSize2);
            CornerSize cornerSize4 = getCornerSize(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeTopRight, cornerSize2);
            CornerSize cornerSize5 = getCornerSize(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeBottomRight, cornerSize2);
            return new Builder().setTopLeftCorner(i5, cornerSize3).setTopRightCorner(i6, cornerSize4).setBottomRightCorner(i7, cornerSize5).setBottomLeftCorner(i8, getCornerSize(obtainStyledAttributes, R.styleable.ShapeAppearance_cornerSizeBottomLeft, cornerSize2));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @NonNull
    public static Builder builder(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3) {
        return builder(context, attributeSet, i2, i3, 0);
    }

    @NonNull
    public static Builder builder(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3, int i4) {
        return builder(context, attributeSet, i2, i3, new AbsoluteCornerSize(i4));
    }

    @NonNull
    public static Builder builder(@NonNull Context context, AttributeSet attributeSet, @AttrRes int i2, @StyleRes int i3, @NonNull CornerSize cornerSize) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialShape, i2, i3);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        return builder(context, resourceId, resourceId2, cornerSize);
    }

    @NonNull
    private static CornerSize getCornerSize(TypedArray typedArray, int i2, @NonNull CornerSize cornerSize) {
        TypedValue peekValue = typedArray.peekValue(i2);
        if (peekValue == null) {
            return cornerSize;
        }
        int i3 = peekValue.type;
        return i3 == 5 ? new AbsoluteCornerSize(TypedValue.complexToDimensionPixelSize(peekValue.data, typedArray.getResources().getDisplayMetrics())) : i3 == 6 ? new RelativeCornerSize(peekValue.getFraction(1.0f, 1.0f)) : cornerSize;
    }

    @NonNull
    public EdgeTreatment getBottomEdge() {
        return this.f7469k;
    }

    @NonNull
    public CornerTreatment getBottomLeftCorner() {
        return this.f7462d;
    }

    @NonNull
    public CornerSize getBottomLeftCornerSize() {
        return this.f7466h;
    }

    @NonNull
    public CornerTreatment getBottomRightCorner() {
        return this.f7461c;
    }

    @NonNull
    public CornerSize getBottomRightCornerSize() {
        return this.f7465g;
    }

    @NonNull
    public EdgeTreatment getLeftEdge() {
        return this.f7470l;
    }

    @NonNull
    public EdgeTreatment getRightEdge() {
        return this.f7468j;
    }

    @NonNull
    public EdgeTreatment getTopEdge() {
        return this.f7467i;
    }

    @NonNull
    public CornerTreatment getTopLeftCorner() {
        return this.f7459a;
    }

    @NonNull
    public CornerSize getTopLeftCornerSize() {
        return this.f7463e;
    }

    @NonNull
    public CornerTreatment getTopRightCorner() {
        return this.f7460b;
    }

    @NonNull
    public CornerSize getTopRightCornerSize() {
        return this.f7464f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean isRoundRect(@NonNull RectF rectF) {
        boolean z = this.f7470l.getClass().equals(EdgeTreatment.class) && this.f7468j.getClass().equals(EdgeTreatment.class) && this.f7467i.getClass().equals(EdgeTreatment.class) && this.f7469k.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.f7463e.getCornerSize(rectF);
        return z && ((this.f7464f.getCornerSize(rectF) > cornerSize ? 1 : (this.f7464f.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.f7466h.getCornerSize(rectF) > cornerSize ? 1 : (this.f7466h.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0 && (this.f7465g.getCornerSize(rectF) > cornerSize ? 1 : (this.f7465g.getCornerSize(rectF) == cornerSize ? 0 : -1)) == 0) && ((this.f7460b instanceof RoundedCornerTreatment) && (this.f7459a instanceof RoundedCornerTreatment) && (this.f7461c instanceof RoundedCornerTreatment) && (this.f7462d instanceof RoundedCornerTreatment));
    }

    @NonNull
    public Builder toBuilder() {
        return new Builder(this);
    }

    @NonNull
    public ShapeAppearanceModel withCornerSize(float f2) {
        return toBuilder().setAllCornerSizes(f2).build();
    }

    @NonNull
    public ShapeAppearanceModel withCornerSize(@NonNull CornerSize cornerSize) {
        return toBuilder().setAllCornerSizes(cornerSize).build();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ShapeAppearanceModel withTransformedCornerSizes(@NonNull CornerSizeUnaryOperator cornerSizeUnaryOperator) {
        return toBuilder().setTopLeftCornerSize(cornerSizeUnaryOperator.apply(getTopLeftCornerSize())).setTopRightCornerSize(cornerSizeUnaryOperator.apply(getTopRightCornerSize())).setBottomLeftCornerSize(cornerSizeUnaryOperator.apply(getBottomLeftCornerSize())).setBottomRightCornerSize(cornerSizeUnaryOperator.apply(getBottomRightCornerSize())).build();
    }
}
