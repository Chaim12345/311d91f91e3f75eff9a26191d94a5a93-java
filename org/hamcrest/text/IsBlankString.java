package org.hamcrest.text;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsNull;
/* loaded from: classes4.dex */
public final class IsBlankString extends TypeSafeMatcher<String> {
    private static final IsBlankString BLANK_INSTANCE;
    private static final Matcher<String> NULL_OR_BLANK_INSTANCE;
    private static final Pattern REGEX_WHITESPACE;

    static {
        IsBlankString isBlankString = new IsBlankString();
        BLANK_INSTANCE = isBlankString;
        NULL_OR_BLANK_INSTANCE = AnyOf.anyOf(IsNull.nullValue(), isBlankString);
        REGEX_WHITESPACE = Pattern.compile("\\s*");
    }

    private IsBlankString() {
    }

    public static Matcher<String> blankOrNullString() {
        return NULL_OR_BLANK_INSTANCE;
    }

    public static Matcher<String> blankString() {
        return BLANK_INSTANCE;
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a blank string");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        return REGEX_WHITESPACE.matcher(str).matches();
    }
}
