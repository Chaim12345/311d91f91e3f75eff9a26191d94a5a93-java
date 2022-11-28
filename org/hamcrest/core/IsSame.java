package org.hamcrest.core;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class IsSame<T> extends BaseMatcher<T> {
    private final T object;

    public IsSame(T t2) {
        this.object = t2;
    }

    public static <T> Matcher<T> sameInstance(T t2) {
        return new IsSame(t2);
    }

    public static <T> Matcher<T> theInstance(T t2) {
        return new IsSame(t2);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("sameInstance(").appendValue(this.object).appendText(")");
    }

    @Override // org.hamcrest.Matcher
    public boolean matches(Object obj) {
        return obj == this.object;
    }
}
