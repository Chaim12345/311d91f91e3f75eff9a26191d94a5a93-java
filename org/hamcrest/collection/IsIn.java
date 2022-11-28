package org.hamcrest.collection;

import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class IsIn<T> extends BaseMatcher<T> {
    private final Collection<T> collection;

    public IsIn(Collection<T> collection) {
        this.collection = collection;
    }

    public IsIn(T[] tArr) {
        this.collection = Arrays.asList(tArr);
    }

    public static <T> Matcher<T> in(Collection<T> collection) {
        return new IsIn(collection);
    }

    public static <T> Matcher<T> in(T[] tArr) {
        return new IsIn(tArr);
    }

    @Deprecated
    public static <T> Matcher<T> isIn(Collection<T> collection) {
        return in(collection);
    }

    @Deprecated
    public static <T> Matcher<T> isIn(T[] tArr) {
        return in(tArr);
    }

    @SafeVarargs
    @Deprecated
    public static <T> Matcher<T> isOneOf(T... tArr) {
        return oneOf(tArr);
    }

    @SafeVarargs
    public static <T> Matcher<T> oneOf(T... tArr) {
        return in(tArr);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("one of ");
        description.appendValueList("{", ", ", "}", this.collection);
    }

    @Override // org.hamcrest.Matcher
    public boolean matches(Object obj) {
        return this.collection.contains(obj);
    }
}
