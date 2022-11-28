package org.hamcrest.collection;

import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class IsEmptyCollection<E> extends TypeSafeMatcher<Collection<? extends E>> {
    public static <E> Matcher<Collection<? extends E>> empty() {
        return new IsEmptyCollection();
    }

    public static <E> Matcher<Collection<E>> emptyCollectionOf(Class<E> cls) {
        return empty();
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public /* bridge */ /* synthetic */ void describeMismatchSafely(Object obj, Description description) {
        describeMismatchSafely((Collection) ((Collection) obj), description);
    }

    public void describeMismatchSafely(Collection<? extends E> collection, Description description) {
        description.appendValue(collection);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("an empty collection");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public /* bridge */ /* synthetic */ boolean matchesSafely(Object obj) {
        return matchesSafely((Collection) ((Collection) obj));
    }

    public boolean matchesSafely(Collection<? extends E> collection) {
        return collection.isEmpty();
    }
}
