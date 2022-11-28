package org.hamcrest.collection;

import java.util.Collection;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class IsCollectionWithSize<E> extends FeatureMatcher<Collection<? extends E>, Integer> {
    public IsCollectionWithSize(Matcher<? super Integer> matcher) {
        super(matcher, "a collection with size", "collection size");
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(int i2) {
        return hasSize(IsEqual.equalTo(Integer.valueOf(i2)));
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(Matcher<? super Integer> matcher) {
        return new IsCollectionWithSize(matcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.FeatureMatcher
    /* renamed from: c */
    public Integer b(Collection collection) {
        return Integer.valueOf(collection.size());
    }
}
