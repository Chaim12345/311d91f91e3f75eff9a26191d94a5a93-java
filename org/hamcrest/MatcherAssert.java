package org.hamcrest;
/* loaded from: classes4.dex */
public class MatcherAssert {
    public static <T> void assertThat(T t2, Matcher<? super T> matcher) {
        assertThat("", t2, matcher);
    }

    public static <T> void assertThat(String str, T t2, Matcher<? super T> matcher) {
        if (matcher.matches(t2)) {
            return;
        }
        StringDescription stringDescription = new StringDescription();
        stringDescription.appendText(str).appendText(System.lineSeparator()).appendText("Expected: ").appendDescriptionOf(matcher).appendText(System.lineSeparator()).appendText("     but: ");
        matcher.describeMismatch(t2, stringDescription);
        throw new AssertionError(stringDescription.toString());
    }

    public static void assertThat(String str, boolean z) {
        if (!z) {
            throw new AssertionError(str);
        }
    }
}
