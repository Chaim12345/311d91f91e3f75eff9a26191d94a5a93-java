package org.hamcrest.object;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class HasEqualValues<T> extends TypeSafeDiagnosingMatcher<T> {
    private final T expectedObject;
    private final List<FieldMatcher> fieldMatchers;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class FieldMatcher extends DiagnosingMatcher<Object> {
        private final Field field;
        private final Matcher<Object> matcher;

        public FieldMatcher(Field field, Object obj) {
            this.field = field;
            this.matcher = IsEqual.equalTo(HasEqualValues.uncheckedGet(field, obj));
        }

        @Override // org.hamcrest.SelfDescribing
        public void describeTo(Description description) {
            description.appendText(this.field.getName()).appendText(": ").appendDescriptionOf(this.matcher);
        }

        @Override // org.hamcrest.DiagnosingMatcher
        protected boolean matches(Object obj, Description description) {
            Object uncheckedGet = HasEqualValues.uncheckedGet(this.field, obj);
            if (this.matcher.matches(uncheckedGet)) {
                return true;
            }
            description.appendText("'").appendText(this.field.getName()).appendText("' ");
            this.matcher.describeMismatch(uncheckedGet, description);
            return false;
        }
    }

    public HasEqualValues(T t2) {
        super(t2.getClass());
        this.expectedObject = t2;
        this.fieldMatchers = fieldMatchers(t2);
    }

    private static List<FieldMatcher> fieldMatchers(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Field field : obj.getClass().getFields()) {
            arrayList.add(new FieldMatcher(field, obj));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object uncheckedGet(Field field, Object obj) {
        try {
            return field.get(obj);
        } catch (Exception unused) {
            throw new AssertionError(String.format("IllegalAccess, reading field '%s' from %s", field.getName(), obj));
        }
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText(this.expectedObject.getClass().getSimpleName()).appendText(" has values ").appendList("[", ", ", "]", this.fieldMatchers);
    }

    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    protected boolean matchesSafely(Object obj, Description description) {
        for (FieldMatcher fieldMatcher : this.fieldMatchers) {
            if (!fieldMatcher.matches(obj, description)) {
                return false;
            }
        }
        return true;
    }
}
