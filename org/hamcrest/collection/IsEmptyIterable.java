package org.hamcrest.collection;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class IsEmptyIterable<E> extends TypeSafeMatcher<Iterable<? extends E>> {
    public static <E> Matcher<Iterable<? extends E>> emptyIterable() {
        return new IsEmptyIterable();
    }

    public static <E> Matcher<Iterable<E>> emptyIterableOf(Class<E> cls) {
        return emptyIterable();
    }

    public void describeMismatchSafely(Iterable<? extends E> iterable, Description description) {
        description.appendValueList("[", ",", "]", iterable);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public /* bridge */ /* synthetic */ void describeMismatchSafely(Object obj, Description description) {
        describeMismatchSafely((Iterable) ((Iterable) obj), description);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("an empty iterable");
    }

    public boolean matchesSafely(Iterable<? extends E> iterable) {
        return !iterable.iterator().hasNext();
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public /* bridge */ /* synthetic */ boolean matchesSafely(Object obj) {
        return matchesSafely((Iterable) ((Iterable) obj));
    }
}
