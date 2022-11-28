package org.hamcrest.core;

import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class StringEndsWith extends SubstringMatcher {
    public StringEndsWith(String str) {
        this(false, str);
    }

    public StringEndsWith(boolean z, String str) {
        super("ending with", z, str);
    }

    public static Matcher<String> endsWith(String str) {
        return new StringEndsWith(false, str);
    }

    public static Matcher<String> endsWithIgnoringCase(String str) {
        return new StringEndsWith(true, str);
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected boolean c(String str) {
        return b(str).endsWith(b(this.f15219a));
    }
}
