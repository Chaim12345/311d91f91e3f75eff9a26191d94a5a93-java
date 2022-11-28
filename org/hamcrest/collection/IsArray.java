package org.hamcrest.collection;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class IsArray<T> extends TypeSafeMatcher<T[]> {
    private final Matcher<? super T>[] elementMatchers;

    public IsArray(Matcher<? super T>[] matcherArr) {
        this.elementMatchers = (Matcher[]) matcherArr.clone();
    }

    public static <T> IsArray<T> array(Matcher<? super T>... matcherArr) {
        return new IsArray<>(matcherArr);
    }

    protected String b() {
        return "]";
    }

    protected String c() {
        return ", ";
    }

    protected String d() {
        return "[";
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(T[] tArr, Description description) {
        if (tArr.length != this.elementMatchers.length) {
            description.appendText("array length was ").appendValue(Integer.valueOf(tArr.length));
            return;
        }
        for (int i2 = 0; i2 < tArr.length; i2++) {
            if (!this.elementMatchers[i2].matches(tArr[i2])) {
                description.appendText("element ").appendValue(Integer.valueOf(i2)).appendText(" ");
                this.elementMatchers[i2].describeMismatch(tArr[i2], description);
                return;
            }
        }
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendList(d(), c(), b(), Arrays.asList(this.elementMatchers));
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(T[] tArr) {
        if (tArr.length != this.elementMatchers.length) {
            return false;
        }
        for (int i2 = 0; i2 < tArr.length; i2++) {
            if (!this.elementMatchers[i2].matches(tArr[i2])) {
                return false;
            }
        }
        return true;
    }
}
