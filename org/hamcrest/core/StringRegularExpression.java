package org.hamcrest.core;

import java.util.regex.Pattern;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
/* loaded from: classes4.dex */
public class StringRegularExpression extends TypeSafeDiagnosingMatcher<String> {
    private Pattern pattern;

    protected StringRegularExpression(Pattern pattern) {
        this.pattern = pattern;
    }

    public static Matcher<String> matchesRegex(String str) {
        return matchesRegex(Pattern.compile(str));
    }

    public static Matcher<String> matchesRegex(Pattern pattern) {
        return new StringRegularExpression(pattern);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    /* renamed from: b */
    public boolean matchesSafely(String str, Description description) {
        if (this.pattern.matcher(str).matches()) {
            return true;
        }
        description.appendText("the string was ").appendValue(str);
        return false;
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("a string matching the pattern ").appendValue(this.pattern);
    }
}
