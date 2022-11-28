package org.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class IsCloseTo extends TypeSafeMatcher<Double> {
    private final double delta;
    private final double value;

    public IsCloseTo(double d2, double d3) {
        this.delta = d3;
        this.value = d2;
    }

    private double actualDelta(Double d2) {
        return Math.abs(d2.doubleValue() - this.value) - this.delta;
    }

    public static Matcher<Double> closeTo(double d2, double d3) {
        return new IsCloseTo(d2, d3);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(Double d2, Description description) {
        description.appendValue(d2).appendText(" differed by ").appendValue(Double.valueOf(actualDelta(d2))).appendText(" more than delta ").appendValue(Double.valueOf(this.delta));
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a numeric value within ").appendValue(Double.valueOf(this.delta)).appendText(" of ").appendValue(Double.valueOf(this.value));
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(Double d2) {
        return actualDelta(d2) <= 0.0d;
    }
}
