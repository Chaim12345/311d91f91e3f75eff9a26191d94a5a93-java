package org.hamcrest;

import org.hamcrest.Description;
import org.hamcrest.internal.ReflectiveTypeFinder;
/* loaded from: classes4.dex */
public abstract class TypeSafeDiagnosingMatcher<T> extends BaseMatcher<T> {
    private static final ReflectiveTypeFinder TYPE_FINDER = new ReflectiveTypeFinder("matchesSafely", 2, 0);
    private final Class<?> expectedType;

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSafeDiagnosingMatcher() {
        this(TYPE_FINDER);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSafeDiagnosingMatcher(Class cls) {
        this.expectedType = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSafeDiagnosingMatcher(ReflectiveTypeFinder reflectiveTypeFinder) {
        this.expectedType = reflectiveTypeFinder.findExpectedType(getClass());
    }

    @Override // org.hamcrest.BaseMatcher, org.hamcrest.Matcher
    public final void describeMismatch(Object obj, Description description) {
        if (obj == null) {
            description.appendText("was null");
        } else if (this.expectedType.isInstance(obj)) {
            matchesSafely(obj, description);
        } else {
            description.appendText("was ").appendText(obj.getClass().getSimpleName()).appendText(" ").appendValue(obj);
        }
    }

    @Override // org.hamcrest.Matcher
    public final boolean matches(Object obj) {
        return obj != null && this.expectedType.isInstance(obj) && matchesSafely(obj, new Description.NullDescription());
    }

    protected abstract boolean matchesSafely(Object obj, Description description);
}
