package org.hamcrest.object;

import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class HasToString<T> extends FeatureMatcher<T, String> {
    public HasToString(Matcher<? super String> matcher) {
        super(matcher, "with toString()", "toString()");
    }

    public static <T> Matcher<T> hasToString(String str) {
        return new HasToString(IsEqual.equalTo(str));
    }

    public static <T> Matcher<T> hasToString(Matcher<? super String> matcher) {
        return new HasToString(matcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.FeatureMatcher
    /* renamed from: c */
    public String b(Object obj) {
        return String.valueOf(obj);
    }
}
