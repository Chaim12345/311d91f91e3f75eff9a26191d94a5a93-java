package org.hamcrest.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsIterableContainingInRelativeOrder<E> extends TypeSafeDiagnosingMatcher<Iterable<? extends E>> {
    private final List<Matcher<? super E>> matchers;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class MatchSeriesInRelativeOrder<F> {
        public final List<Matcher<? super F>> matchers;
        private final Description mismatchDescription;
        private int nextMatchIx = 0;
        private F lastMatchedItem = null;

        public MatchSeriesInRelativeOrder(List<Matcher<? super F>> list, Description description) {
            this.mismatchDescription = description;
            if (list.isEmpty()) {
                throw new IllegalArgumentException("Should specify at least one expected element");
            }
            this.matchers = list;
        }

        public boolean isFinished() {
            if (this.nextMatchIx < this.matchers.size()) {
                this.mismatchDescription.appendDescriptionOf(this.matchers.get(this.nextMatchIx)).appendText(" was not found");
                if (this.lastMatchedItem != null) {
                    this.mismatchDescription.appendText(" after ").appendValue(this.lastMatchedItem);
                    return false;
                }
                return false;
            }
            return true;
        }

        public void processItems(Iterable<? extends F> iterable) {
            for (F f2 : iterable) {
                if (this.nextMatchIx < this.matchers.size() && this.matchers.get(this.nextMatchIx).matches(f2)) {
                    this.lastMatchedItem = f2;
                    this.nextMatchIx++;
                }
            }
        }
    }

    public IsIterableContainingInRelativeOrder(List<Matcher<? super E>> list) {
        this.matchers = list;
    }

    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(List<Matcher<? super E>> list) {
        return new IsIterableContainingInRelativeOrder(list);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(E... eArr) {
        ArrayList arrayList = new ArrayList();
        for (E e2 : eArr) {
            arrayList.add(IsEqual.equalTo(e2));
        }
        return containsInRelativeOrder(arrayList);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(Matcher<? super E>... matcherArr) {
        return containsInRelativeOrder(Arrays.asList(matcherArr));
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("iterable containing ").appendList("[", ", ", "]", this.matchers).appendText(" in relative order");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public boolean matchesSafely(Iterable iterable, Description description) {
        MatchSeriesInRelativeOrder matchSeriesInRelativeOrder = new MatchSeriesInRelativeOrder(this.matchers, description);
        matchSeriesInRelativeOrder.processItems(iterable);
        return matchSeriesInRelativeOrder.isFinished();
    }
}
