package org.hamcrest.text;

import org.hamcrest.CoreMatchers;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class CharSequenceLength extends FeatureMatcher<CharSequence, Integer> {
    public CharSequenceLength(Matcher<? super Integer> matcher) {
        super(matcher, "a CharSequence with length", "length");
    }

    public static Matcher<CharSequence> hasLength(int i2) {
        return new CharSequenceLength(CoreMatchers.equalTo(Integer.valueOf(i2)));
    }

    public static Matcher<CharSequence> hasLength(Matcher<? super Integer> matcher) {
        return new CharSequenceLength(matcher);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.FeatureMatcher
    /* renamed from: c */
    public Integer b(CharSequence charSequence) {
        return Integer.valueOf(charSequence.length());
    }
}
