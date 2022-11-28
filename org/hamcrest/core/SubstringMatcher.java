package org.hamcrest.core;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public abstract class SubstringMatcher extends TypeSafeMatcher<String> {

    /* renamed from: a  reason: collision with root package name */
    protected final String f15219a;
    private final boolean ignoringCase;
    private final String relationship;

    /* JADX INFO: Access modifiers changed from: protected */
    public SubstringMatcher(String str, boolean z, String str2) {
        this.relationship = str;
        this.ignoringCase = z;
        this.f15219a = str2;
        if (str2 == null) {
            throw new IllegalArgumentException("missing substring");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String b(String str) {
        return this.ignoringCase ? str.toLowerCase() : str;
    }

    protected abstract boolean c(String str);

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(String str, Description description) {
        description.appendText("was \"").appendText(str).appendText("\"");
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string ").appendText(this.relationship).appendText(" ").appendValue(this.f15219a);
        if (this.ignoringCase) {
            description.appendText(" ignoring case");
        }
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        if (this.ignoringCase) {
            str = str.toLowerCase();
        }
        return c(str);
    }
}
