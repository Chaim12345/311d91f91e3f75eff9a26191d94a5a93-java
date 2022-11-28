package org.hamcrest.core;

import java.lang.reflect.Array;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class IsEqual<T> extends BaseMatcher<T> {
    private final Object expectedValue;

    public IsEqual(T t2) {
        this.expectedValue = t2;
    }

    private static boolean areArrayElementsEqual(Object obj, Object obj2) {
        for (int i2 = 0; i2 < Array.getLength(obj); i2++) {
            if (!areEqual(Array.get(obj, i2), Array.get(obj2, i2))) {
                return false;
            }
        }
        return true;
    }

    private static boolean areArrayLengthsEqual(Object obj, Object obj2) {
        return Array.getLength(obj) == Array.getLength(obj2);
    }

    private static boolean areArraysEqual(Object obj, Object obj2) {
        return areArrayLengthsEqual(obj, obj2) && areArrayElementsEqual(obj, obj2);
    }

    private static boolean areEqual(Object obj, Object obj2) {
        return obj == null ? obj2 == null : (obj2 == null || !isArray(obj)) ? obj.equals(obj2) : isArray(obj2) && areArraysEqual(obj, obj2);
    }

    public static <T> Matcher<T> equalTo(T t2) {
        return new IsEqual(t2);
    }

    public static Matcher<Object> equalToObject(Object obj) {
        return new IsEqual(obj);
    }

    private static boolean isArray(Object obj) {
        return obj.getClass().isArray();
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendValue(this.expectedValue);
    }

    @Override // org.hamcrest.Matcher
    public boolean matches(Object obj) {
        return areEqual(obj, this.expectedValue);
    }
}
