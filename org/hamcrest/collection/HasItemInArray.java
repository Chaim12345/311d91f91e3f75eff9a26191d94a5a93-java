package org.hamcrest.collection;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsIterableContaining;
/* loaded from: classes4.dex */
public class HasItemInArray<T> extends TypeSafeMatcher<T[]> {
    private final TypeSafeDiagnosingMatcher<Iterable<? super T>> collectionMatcher;
    private final Matcher<? super T> elementMatcher;

    public HasItemInArray(Matcher<? super T> matcher) {
        this.elementMatcher = matcher;
        this.collectionMatcher = new IsIterableContaining(matcher);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(T[] tArr, Description description) {
        this.collectionMatcher.describeMismatch(Arrays.asList(tArr), description);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("an array containing ").appendDescriptionOf(this.elementMatcher);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(T[] tArr) {
        return this.collectionMatcher.matches(Arrays.asList(tArr));
    }
}
