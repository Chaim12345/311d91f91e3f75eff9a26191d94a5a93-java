package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
/* loaded from: classes4.dex */
public class Every<T> extends TypeSafeDiagnosingMatcher<Iterable<? extends T>> {
    private final Matcher<? super T> matcher;

    public Every(Matcher<? super T> matcher) {
        this.matcher = matcher;
    }

    public static <U> Matcher<Iterable<? extends U>> everyItem(Matcher<U> matcher) {
        return new Every(matcher);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("every item is ").appendDescriptionOf(this.matcher);
    }

    public boolean matchesSafely(Iterable<? extends T> iterable, Description description) {
        for (T t2 : iterable) {
            if (!this.matcher.matches(t2)) {
                description.appendText("an item ");
                this.matcher.describeMismatch(t2, description);
                return false;
            }
        }
        return true;
    }

    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public /* bridge */ /* synthetic */ boolean matchesSafely(Object obj, Description description) {
        return matchesSafely((Iterable) ((Iterable) obj), description);
    }
}
