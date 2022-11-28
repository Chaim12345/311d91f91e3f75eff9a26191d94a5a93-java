package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsIterableContainingInAnyOrder<T> extends TypeSafeDiagnosingMatcher<Iterable<? extends T>> {
    private final Collection<Matcher<? super T>> matchers;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Matching<S> {
        private final Collection<Matcher<? super S>> matchers;
        private final Description mismatchDescription;

        public Matching(Collection<Matcher<? super S>> collection, Description description) {
            this.matchers = new ArrayList(collection);
            this.mismatchDescription = description;
        }

        private boolean isMatched(S s2) {
            for (Matcher<? super S> matcher : this.matchers) {
                if (matcher.matches(s2)) {
                    this.matchers.remove(matcher);
                    return true;
                }
            }
            this.mismatchDescription.appendText("not matched: ").appendValue(s2);
            return false;
        }

        public boolean isFinished(Iterable<? extends S> iterable) {
            if (this.matchers.isEmpty()) {
                return true;
            }
            this.mismatchDescription.appendText("no item matches: ").appendList("", ", ", "", this.matchers).appendText(" in ").appendValueList("[", ", ", "]", iterable);
            return false;
        }

        public boolean matches(S s2) {
            if (this.matchers.isEmpty()) {
                this.mismatchDescription.appendText("no match for: ").appendValue(s2);
                return false;
            }
            return isMatched(s2);
        }
    }

    public IsIterableContainingInAnyOrder(Collection<Matcher<? super T>> collection) {
        this.matchers = collection;
    }

    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(Collection<Matcher<? super T>> collection) {
        return new IsIterableContainingInAnyOrder(collection);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(T... tArr) {
        ArrayList arrayList = new ArrayList();
        for (T t2 : tArr) {
            arrayList.add(IsEqual.equalTo(t2));
        }
        return new IsIterableContainingInAnyOrder(arrayList);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(Matcher<? super T>... matcherArr) {
        return containsInAnyOrder(Arrays.asList(matcherArr));
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("iterable with items ").appendList("[", ", ", "]", this.matchers).appendText(" in any order");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public boolean matchesSafely(Iterable iterable, Description description) {
        Matching matching = new Matching(this.matchers, description);
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            if (!matching.matches(it.next())) {
                return false;
            }
        }
        return matching.isFinished(iterable);
    }
}
