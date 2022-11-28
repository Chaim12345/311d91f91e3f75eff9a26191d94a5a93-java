package org.hamcrest.core;

import org.hamcrest.Matcher;
/* loaded from: classes4.dex */
public class StringStartsWith extends SubstringMatcher {
    public StringStartsWith(String str) {
        this(false, str);
    }

    public StringStartsWith(boolean z, String str) {
        super("starting with", z, str);
    }

    public static Matcher<String> startsWith(String str) {
        return new StringStartsWith(false, str);
    }

    public static Matcher<String> startsWithIgnoringCase(String str) {
        return new StringStartsWith(true, str);
    }

    @Override // org.hamcrest.core.SubstringMatcher
    protected boolean c(String str) {
        return b(str).startsWith(b(this.f15219a));
    }
}
