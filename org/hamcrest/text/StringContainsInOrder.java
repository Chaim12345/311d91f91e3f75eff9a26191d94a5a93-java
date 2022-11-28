package org.hamcrest.text;

import java.util.Arrays;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
/* loaded from: classes4.dex */
public class StringContainsInOrder extends TypeSafeMatcher<String> {
    private final Iterable<String> substrings;

    public StringContainsInOrder(Iterable<String> iterable) {
        this.substrings = iterable;
    }

    public static Matcher<String> stringContainsInOrder(Iterable<String> iterable) {
        return new StringContainsInOrder(iterable);
    }

    public static Matcher<String> stringContainsInOrder(String... strArr) {
        return new StringContainsInOrder(Arrays.asList(strArr));
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public void describeMismatchSafely(String str, Description description) {
        description.appendText("was \"").appendText(str).appendText("\"");
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string containing ").appendValueList("", ", ", "", this.substrings).appendText(" in order");
    }

    @Override // org.hamcrest.TypeSafeMatcher
    public boolean matchesSafely(String str) {
        int i2 = 0;
        for (String str2 : this.substrings) {
            int indexOf = str.indexOf(str2, i2);
            if (indexOf == -1) {
                return false;
            }
            i2 = indexOf + 1;
        }
        return true;
    }
}
