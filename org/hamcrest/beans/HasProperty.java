package org.hamcrest.beans;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class HasProperty<T> extends TypeSafeMatcher<T> {
    private final String propertyName;

    public HasProperty(String str) {
        this.propertyName = str;
    }

    public static <T> Matcher<T> hasProperty(String str) {
        return new HasProperty(str);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(T t2, Description description) {
        description.appendText("no ").appendValue(this.propertyName).appendText(" in ").appendValue(t2);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("hasProperty(").appendValue(this.propertyName).appendText(")");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(T t2) {
        try {
            return PropertyUtil.getPropertyDescriptor(this.propertyName, t2) != null;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }
}
