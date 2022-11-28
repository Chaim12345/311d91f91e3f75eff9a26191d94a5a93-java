package org.hamcrest.core;

import java.util.ArrayList;
import java.util.Iterator;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
/* loaded from: classes4.dex */
public class IsIterableContaining<T> extends TypeSafeDiagnosingMatcher<Iterable<? super T>> {
    private final Matcher<? super T> elementMatcher;

    public IsIterableContaining(Matcher<? super T> matcher) {
        this.elementMatcher = matcher;
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(T t2) {
        return new IsIterableContaining(IsEqual.equalTo(t2));
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> matcher) {
        return new IsIterableContaining(matcher);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(T... tArr) {
        ArrayList arrayList = new ArrayList(tArr.length);
        for (T t2 : tArr) {
            arrayList.add(hasItem(t2));
        }
        return AllOf.allOf(arrayList);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... matcherArr) {
        ArrayList arrayList = new ArrayList(matcherArr.length);
        for (Matcher<? super T> matcher : matcherArr) {
            arrayList.add(new IsIterableContaining(matcher));
        }
        return AllOf.allOf(arrayList);
    }

    private boolean isEmpty(Iterable<? super T> iterable) {
        return !iterable.iterator().hasNext();
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a collection containing ").appendDescriptionOf(this.elementMatcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public boolean matchesSafely(Iterable iterable, Description description) {
        String str;
        if (isEmpty(iterable)) {
            str = "was empty";
        } else {
            Iterator<T> it = iterable.iterator();
            while (it.hasNext()) {
                if (this.elementMatcher.matches(it.next())) {
                    return true;
                }
            }
            description.appendText("mismatches were: [");
            boolean z = false;
            for (T t2 : iterable) {
                if (z) {
                    description.appendText(", ");
                }
                this.elementMatcher.describeMismatch(t2, description);
                z = true;
            }
            str = "]";
        }
        description.appendText(str);
        return false;
    }
}
