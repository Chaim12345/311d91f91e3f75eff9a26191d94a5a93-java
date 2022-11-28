package org.hamcrest.collection;

import java.util.Map;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public final class IsMapWithSize<K, V> extends FeatureMatcher<Map<? extends K, ? extends V>, Integer> {
    public IsMapWithSize(Matcher<? super Integer> matcher) {
        super(matcher, "a map with size", "map size");
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> aMapWithSize(int i2) {
        return aMapWithSize(IsEqual.equalTo(Integer.valueOf(i2)));
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> aMapWithSize(Matcher<? super Integer> matcher) {
        return new IsMapWithSize(matcher);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> anEmptyMap() {
        return aMapWithSize(IsEqual.equalTo(0));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.FeatureMatcher
    /* renamed from: c */
    public Integer b(Map map) {
        return Integer.valueOf(map.size());
    }
}
