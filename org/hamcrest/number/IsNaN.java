package org.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public final class IsNaN extends TypeSafeMatcher<Double> {
    private IsNaN() {
    }

    public static Matcher<Double> notANumber() {
        return new IsNaN();
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(Double d2, Description description) {
        description.appendText("was ").appendValue(d2);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a double value of NaN");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(Double d2) {
        return Double.isNaN(d2.doubleValue());
    }
}
