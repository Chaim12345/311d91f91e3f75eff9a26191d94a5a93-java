package org.hamcrest;
/* loaded from: classes4.dex */
public abstract class BaseMatcher<T> implements Matcher<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(Object obj, Description description) {
        if (obj == null) {
            description.appendText("was null");
            return false;
        }
        return true;
    }

    @Override // org.hamcrest.Matcher
    @Deprecated
    public final void _dont_implement_Matcher___instead_extend_BaseMatcher_() {
    }

    @Override // org.hamcrest.Matcher
    public void describeMismatch(Object obj, Description description) {
        description.appendText("was ").appendValue(obj);
    }

    public String toString() {
        return StringDescription.toString(this);
    }
}
