package org.hamcrest.collection;

import java.util.Arrays;
import java.util.Collection;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class ArrayAsIterableMatcher<E> extends TypeSafeMatcher<E[]> {

    /* renamed from: a  reason: collision with root package name */
    protected final TypeSafeDiagnosingMatcher f15217a;

    /* renamed from: b  reason: collision with root package name */
    protected final Collection f15218b;
    private final String message;

    public ArrayAsIterableMatcher(TypeSafeDiagnosingMatcher<Iterable<? extends E>> typeSafeDiagnosingMatcher, Collection<Matcher<? super E>> collection, String str) {
        this.f15218b = collection;
        this.f15217a = typeSafeDiagnosingMatcher;
        this.message = str;
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(E[] eArr, Description description) {
        this.f15217a.describeMismatch(Arrays.asList(eArr), description);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendList("[", ", ", "]", this.f15218b).appendText(" ").appendText(this.message);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(E[] eArr) {
        return this.f15217a.matches(Arrays.asList(eArr));
    }
}
