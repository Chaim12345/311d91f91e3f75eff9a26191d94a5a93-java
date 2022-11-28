package org.hamcrest.text;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class IsEqualCompressingWhiteSpace extends TypeSafeMatcher<String> {
    private final String string;

    public IsEqualCompressingWhiteSpace(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Non-null value required");
        }
        this.string = str;
    }

    public static Matcher<String> equalToCompressingWhiteSpace(String str) {
        return new IsEqualCompressingWhiteSpace(str);
    }

    public static Matcher<String> equalToIgnoringWhiteSpace(String str) {
        return new IsEqualCompressingWhiteSpace(str);
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(String str, Description description) {
        description.appendText("was ").appendValue(str);
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string equal to ").appendValue(this.string).appendText(" compressing white space");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        return stripSpaces(this.string).equals(stripSpaces(str));
    }

    public String stripSpaces(String str) {
        return str.replaceAll("\\s+", " ").trim();
    }
}
