package com.google.common.math;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Doubles;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class PairedStatsAccumulator {
    private final StatsAccumulator xStats = new StatsAccumulator();
    private final StatsAccumulator yStats = new StatsAccumulator();
    private double sumOfProductsOfDeltas = 0.0d;

    private static double ensureInUnitRange(double d2) {
        return Doubles.constrainToRange(d2, -1.0d, 1.0d);
    }

    private double ensurePositive(double d2) {
        if (d2 > 0.0d) {
            return d2;
        }
        return Double.MIN_VALUE;
    }

    public void add(double d2, double d3) {
        this.xStats.add(d2);
        if (!Doubles.isFinite(d2) || !Doubles.isFinite(d3)) {
            this.sumOfProductsOfDeltas = Double.NaN;
        } else if (this.xStats.count() > 1) {
            this.sumOfProductsOfDeltas += (d2 - this.xStats.mean()) * (d3 - this.yStats.mean());
        }
        this.yStats.add(d3);
    }

    public void addAll(PairedStats pairedStats) {
        if (pairedStats.count() == 0) {
            return;
        }
        this.xStats.addAll(pairedStats.xStats());
        this.sumOfProductsOfDeltas = this.yStats.count() == 0 ? pairedStats.a() : this.sumOfProductsOfDeltas + pairedStats.a() + ((pairedStats.xStats().mean() - this.xStats.mean()) * (pairedStats.yStats().mean() - this.yStats.mean()) * pairedStats.count());
        this.yStats.addAll(pairedStats.yStats());
    }

    public long count() {
        return this.xStats.count();
    }

    public final LinearTransformation leastSquaresFit() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return LinearTransformation.forNaN();
        }
        double b2 = this.xStats.b();
        if (b2 > 0.0d) {
            return this.yStats.b() > 0.0d ? LinearTransformation.mapping(this.xStats.mean(), this.yStats.mean()).withSlope(this.sumOfProductsOfDeltas / b2) : LinearTransformation.horizontal(this.yStats.mean());
        }
        Preconditions.checkState(this.yStats.b() > 0.0d);
        return LinearTransformation.vertical(this.xStats.mean());
    }

    public final double pearsonsCorrelationCoefficient() {
        Preconditions.checkState(count() > 1);
        if (Double.isNaN(this.sumOfProductsOfDeltas)) {
            return Double.NaN;
        }
        double b2 = this.xStats.b();
        double b3 = this.yStats.b();
        Preconditions.checkState(b2 > 0.0d);
        Preconditions.checkState(b3 > 0.0d);
        return ensureInUnitRange(this.sumOfProductsOfDeltas / Math.sqrt(ensurePositive(b2 * b3)));
    }

    public double populationCovariance() {
        Preconditions.checkState(count() != 0);
        return this.sumOfProductsOfDeltas / count();
    }

    public final double sampleCovariance() {
        Preconditions.checkState(count() > 1);
        return this.sumOfProductsOfDeltas / (count() - 1);
    }

    public PairedStats snapshot() {
        return new PairedStats(this.xStats.snapshot(), this.yStats.snapshot(), this.sumOfProductsOfDeltas);
    }

    public Stats xStats() {
        return this.xStats.snapshot();
    }

    public Stats yStats() {
        return this.yStats.snapshot();
    }
}
