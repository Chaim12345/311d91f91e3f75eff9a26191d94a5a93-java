package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.internal.NullSafety;
/* loaded from: classes4.dex */
public class IsIterableContainingInOrder<E> extends TypeSafeDiagnosingMatcher<Iterable<? extends E>> {
    private final List<Matcher<? super E>> matchers;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class MatchSeries<F> {
        private final List<Matcher<? super F>> matchers;
        private final Description mismatchDescription;
        private int nextMatchIx = 0;

        public MatchSeries(List<Matcher<? super F>> list, Description description) {
            this.mismatchDescription = description;
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Should specify at least one expected element");
            }
            this.matchers = list;
        }

        private void describeMismatch(Matcher<? super F> matcher, F f2) {
            Description description = this.mismatchDescription;
            description.appendText("item " + this.nextMatchIx + ": ");
            matcher.describeMismatch(f2, this.mismatchDescription);
        }

        private boolean isMatched(F f2) {
            Matcher<? super F> matcher = this.matchers.get(this.nextMatchIx);
            if (matcher.matches(f2)) {
                this.nextMatchIx++;
                return true;
            }
            describeMismatch(matcher, f2);
            return false;
        }

        public boolean isFinished() {
            if (this.nextMatchIx < this.matchers.size()) {
                this.mismatchDescription.appendText("no item was ").appendDescriptionOf(this.matchers.get(this.nextMatchIx));
                return false;
            }
            return true;
        }

        public boolean matches(F f2) {
            if (this.matchers.size() <= this.nextMatchIx) {
                this.mismatchDescription.appendText("not matched: ").appendValue(f2);
                return false;
            }
            return isMatched(f2);
        }
    }

    public IsIterableContainingInOrder(List<Matcher<? super E>> list) {
        this.matchers = list;
    }

    public static <E> Matcher<Iterable<? extends E>> contains(List<Matcher<? super E>> list) {
        return new IsIterableContainingInOrder(list);
    }

    public static <E> Matcher<Iterable<? extends E>> contains(Matcher<? super E> matcher) {
        return contains(new ArrayList(Collections.singletonList(matcher)));
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> contains(E... eArr) {
        return contains(ArrayMatching.asEqualMatchers(eArr));
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> contains(Matcher<? super E>... matcherArr) {
        return contains(NullSafety.nullSafe(matcherArr));
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("iterable containing ").appendList("[", ", ", "]", this.matchers);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public boolean matchesSafely(Iterable iterable, Description description) {
        MatchSeries matchSeries = new MatchSeries(this.matchers, description);
        for (Object obj : iterable) {
            if (!matchSeries.matches(obj)) {
                return false;
            }
        }
        return matchSeries.isFinished();
    }
}
