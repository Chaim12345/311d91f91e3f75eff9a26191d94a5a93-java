package org.hamcrest.collection;

import java.util.Map;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsMapContaining<K, V> extends TypeSafeMatcher<Map<? extends K, ? extends V>> {
    private final Matcher<? super K> keyMatcher;
    private final Matcher<? super V> valueMatcher;

    public IsMapContaining(Matcher<? super K> matcher, Matcher<? super V> matcher2) {
        this.keyMatcher = matcher;
        this.valueMatcher = matcher2;
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> hasEntry(K k2, V v) {
        return new IsMapContaining(IsEqual.equalTo(k2), IsEqual.equalTo(v));
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> hasEntry(Matcher<? super K> matcher, Matcher<? super V> matcher2) {
        return new IsMapContaining(matcher, matcher2);
    }

    public static <K> Matcher<Map<? extends K, ?>> hasKey(K k2) {
        return new IsMapContaining(IsEqual.equalTo(k2), IsAnything.anything());
    }

    public static <K> Matcher<Map<? extends K, ?>> hasKey(Matcher<? super K> matcher) {
        return new IsMapContaining(matcher, IsAnything.anything());
    }

    public static <V> Matcher<Map<?, ? extends V>> hasValue(V v) {
        return new IsMapContaining(IsAnything.anything(), IsEqual.equalTo(v));
    }

    public static <V> Matcher<Map<?, ? extends V>> hasValue(Matcher<? super V> matcher) {
        return new IsMapContaining(IsAnything.anything(), matcher);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public /* bridge */ /* synthetic */ void describeMismatchSafely(Object obj, Description description) {
        describeMismatchSafely((Map) ((Map) obj), description);
    }

    public void describeMismatchSafely(Map<? extends K, ? extends V> map, Description description) {
        description.appendText("map was ").appendValueList("[", ", ", "]", map.entrySet());
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("map containing [").appendDescriptionOf(this.keyMatcher).appendText("->").appendDescriptionOf(this.valueMatcher).appendText("]");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public /* bridge */ /* synthetic */ boolean matchesSafely(Object obj) {
        return matchesSafely((Map) ((Map) obj));
    }

    public boolean matchesSafely(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            if (this.keyMatcher.matches(entry.getKey()) && this.valueMatcher.matches(entry.getValue())) {
                return true;
            }
        }
        return false;
    }
}
