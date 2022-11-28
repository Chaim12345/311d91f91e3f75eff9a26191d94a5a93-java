package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.concurrent.LazyInit;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class LinearTransformation {

    /* loaded from: classes2.dex */
    public static final class LinearTransformationBuilder {
        private final double x1;
        private final double y1;

        private LinearTransformationBuilder(double d2, double d3) {
            this.x1 = d2;
            this.y1 = d3;
        }

        public LinearTransformation and(double d2, double d3) {
            Preconditions.checkArgument(DoubleUtils.d(d2) && DoubleUtils.d(d3));
            double d4 = this.x1;
            if (d2 == d4) {
                Preconditions.checkArgument(d3 != this.y1);
                return new VerticalLinearTransformation(this.x1);
            }
            return withSlope((d3 - this.y1) / (d2 - d4));
        }

        public LinearTransformation withSlope(double d2) {
            Preconditions.checkArgument(!Double.isNaN(d2));
            return DoubleUtils.d(d2) ? new RegularLinearTransformation(d2, this.y1 - (this.x1 * d2)) : new VerticalLinearTransformation(this.x1);
        }
    }

    /* loaded from: classes2.dex */
    private static final class NaNLinearTransformation extends LinearTransformation {

        /* renamed from: a  reason: collision with root package name */
        static final NaNLinearTransformation f9326a = new NaNLinearTransformation();

        private NaNLinearTransformation() {
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            return this;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return Double.NaN;
        }

        public String toString() {
            return "NaN";
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d2) {
            return Double.NaN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class RegularLinearTransformation extends LinearTransformation {

        /* renamed from: a  reason: collision with root package name */
        final double f9327a;

        /* renamed from: b  reason: collision with root package name */
        final double f9328b;
        @LazyInit

        /* renamed from: c  reason: collision with root package name */
        LinearTransformation f9329c;

        RegularLinearTransformation(double d2, double d3) {
            this.f9327a = d2;
            this.f9328b = d3;
            this.f9329c = null;
        }

        RegularLinearTransformation(double d2, double d3, LinearTransformation linearTransformation) {
            this.f9327a = d2;
            this.f9328b = d3;
            this.f9329c = linearTransformation;
        }

        private LinearTransformation createInverse() {
            double d2 = this.f9327a;
            return d2 != 0.0d ? new RegularLinearTransformation(1.0d / d2, (this.f9328b * (-1.0d)) / d2, this) : new VerticalLinearTransformation(this.f9328b, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.f9329c;
            if (linearTransformation == null) {
                LinearTransformation createInverse = createInverse();
                this.f9329c = createInverse;
                return createInverse;
            }
            return linearTransformation;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return this.f9327a == 0.0d;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            return this.f9327a;
        }

        public String toString() {
            return String.format("y = %g * x + %g", Double.valueOf(this.f9327a), Double.valueOf(this.f9328b));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d2) {
            return (d2 * this.f9327a) + this.f9328b;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class VerticalLinearTransformation extends LinearTransformation {

        /* renamed from: a  reason: collision with root package name */
        final double f9330a;
        @LazyInit

        /* renamed from: b  reason: collision with root package name */
        LinearTransformation f9331b;

        VerticalLinearTransformation(double d2) {
            this.f9330a = d2;
            this.f9331b = null;
        }

        VerticalLinearTransformation(double d2, LinearTransformation linearTransformation) {
            this.f9330a = d2;
            this.f9331b = linearTransformation;
        }

        private LinearTransformation createInverse() {
            return new RegularLinearTransformation(0.0d, this.f9330a, this);
        }

        @Override // com.google.common.math.LinearTransformation
        public LinearTransformation inverse() {
            LinearTransformation linearTransformation = this.f9331b;
            if (linearTransformation == null) {
                LinearTransformation createInverse = createInverse();
                this.f9331b = createInverse;
                return createInverse;
            }
            return linearTransformation;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isHorizontal() {
            return false;
        }

        @Override // com.google.common.math.LinearTransformation
        public boolean isVertical() {
            return true;
        }

        @Override // com.google.common.math.LinearTransformation
        public double slope() {
            throw new IllegalStateException();
        }

        public String toString() {
            return String.format("x = %g", Double.valueOf(this.f9330a));
        }

        @Override // com.google.common.math.LinearTransformation
        public double transform(double d2) {
            throw new IllegalStateException();
        }
    }

    public static LinearTransformation forNaN() {
        return NaNLinearTransformation.f9326a;
    }

    public static LinearTransformation horizontal(double d2) {
        Preconditions.checkArgument(DoubleUtils.d(d2));
        return new RegularLinearTransformation(0.0d, d2);
    }

    public static LinearTransformationBuilder mapping(double d2, double d3) {
        Preconditions.checkArgument(DoubleUtils.d(d2) && DoubleUtils.d(d3));
        return new LinearTransformationBuilder(d2, d3);
    }

    public static LinearTransformation vertical(double d2) {
        Preconditions.checkArgument(DoubleUtils.d(d2));
        return new VerticalLinearTransformation(d2);
    }

    public abstract LinearTransformation inverse();

    public abstract boolean isHorizontal();

    public abstract boolean isVertical();

    public abstract double slope();

    public abstract double transform(double d2);
}
