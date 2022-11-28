package org.hamcrest.text;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.IsNull;
/* loaded from: classes4.dex */
public final class IsEmptyString extends TypeSafeMatcher<String> {
    private static final IsEmptyString INSTANCE;
    private static final Matcher<String> NULL_OR_EMPTY_INSTANCE;

    static {
        IsEmptyString isEmptyString = new IsEmptyString();
        INSTANCE = isEmptyString;
        NULL_OR_EMPTY_INSTANCE = AnyOf.anyOf(IsNull.nullValue(), isEmptyString);
    }

    private IsEmptyString() {
    }

    public static Matcher<String> emptyOrNullString() {
        return NULL_OR_EMPTY_INSTANCE;
    }

    public static Matcher<String> emptyString() {
        return INSTANCE;
    }

    @Deprecated
    public static Matcher<String> isEmptyOrNullString() {
        return emptyOrNullString();
    }

    @Deprecated
    public static Matcher<String> isEmptyString() {
        return emptyString();
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("an empty string");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        return str.equals("");
    }
}
