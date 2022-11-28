package org.hamcrest.text;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class IsEqualIgnoringCase extends TypeSafeMatcher<String> {
    private final String string;

    public IsEqualIgnoringCase(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Non-null value required");
        }
        this.string = str;
    }

    public static Matcher<String> equalToIgnoringCase(String str) {
        return new IsEqualIgnoringCase(str);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(String str, Description description) {
        description.appendText("was ").appendValue(str);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string equal to ").appendValue(this.string).appendText(" ignoring case");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        return this.string.equalsIgnoreCase(str);
    }
}
