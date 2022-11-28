package org.hamcrest.core;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class AnyOf<T> extends ShortcutCombination<T> {
    public AnyOf(Iterable<Matcher<? super T>> iterable) {
        super(iterable);
    }

    @SafeVarargs
    public AnyOf(Matcher<? super T>... matcherArr) {
        this(Arrays.asList(matcherArr));
    }

    public static <T> AnyOf<T> anyOf(Iterable<Matcher<? super T>> iterable) {
        return new AnyOf<>(iterable);
    }

    @SafeVarargs
    public static <T> AnyOf<T> anyOf(Matcher<? super T>... matcherArr) {
        return anyOf(Arrays.asList(matcherArr));
    }

    @Override // org.hamcrest.core.ShortcutCombination, org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        describeTo(description, "or");
    }

    @Override // org.hamcrest.core.ShortcutCombination
    public /* bridge */ /* synthetic */ void describeTo(Description description, String str) {
        super.describeTo(description, str);
    }

    @Override // org.hamcrest.core.ShortcutCombination, org.hamcrest.Matcher
    public boolean matches(Object obj) {
        return b(obj, true);
    }
}
