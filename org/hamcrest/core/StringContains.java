package org.hamcrest.core;

import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class StringContains extends SubstringMatcher {
    public StringContains(String str) {
        this(false, str);
    }

    public StringContains(boolean z, String str) {
        super("containing", z, str);
    }

    public static Matcher<String> containsString(String str) {
        return new StringContains(false, str);
    }

    public static Matcher<String> containsStringIgnoringCase(String str) {
        return new StringContains(true, str);
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected boolean c(String str) {
        return b(str).contains(b(this.f15219a));
    }
}
