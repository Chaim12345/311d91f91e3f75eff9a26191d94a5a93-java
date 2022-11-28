package org.hamcrest.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import org.hamcrest.Condition;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
/* loaded from: classes4.dex */
public class HasPropertyWithValue<T> extends TypeSafeDiagnosingMatcher<T> {
    private static final Condition.Step<PropertyDescriptor, Method> WITH_READ_METHOD = withReadMethod();
    private final String messageFormat;
    private final String propertyName;
    private final Matcher<Object> valueMatcher;

    public HasPropertyWithValue(String str, Matcher<?> matcher) {
        this(str, matcher, " property '%s' ");
    }

    public HasPropertyWithValue(String str, Matcher<?> matcher, String str2) {
        this.propertyName = str;
        this.valueMatcher = nastyGenericsWorkaround(matcher);
        this.messageFormat = str2;
    }

    public static <T> Matcher<T> hasProperty(String str, Matcher<?> matcher) {
        return new HasPropertyWithValue(str, matcher);
    }

    public static <T> Matcher<T> hasPropertyAtPath(String str, Matcher<T> matcher) {
        List asList = Arrays.asList(str.split("\\."));
        ListIterator listIterator = asList.listIterator(asList.size());
        while (listIterator.hasPrevious()) {
            matcher = new HasPropertyWithValue((String) listIterator.previous(), matcher, "%s.");
        }
        return matcher;
    }

    private static Matcher<Object> nastyGenericsWorkaround(Matcher<?> matcher) {
        return matcher;
    }

    private Condition<PropertyDescriptor> propertyOn(T t2, Description description) {
        PropertyDescriptor propertyDescriptor = PropertyUtil.getPropertyDescriptor(this.propertyName, t2);
        if (propertyDescriptor == null) {
            description.appendText("No property \"" + this.propertyName + "\"");
            return Condition.notMatched();
        }
        return Condition.matched(propertyDescriptor, description);
    }

    private Condition.Step<Method, Object> withPropertyValue(final T t2) {
        return new Condition.Step<Method, Object>(this) { // from class: org.hamcrest.beans.HasPropertyWithValue.1
            @Override // org.hamcrest.Condition.Step
            public Condition<Object> apply(Method method, Description description) {
                try {
                    return Condition.matched(method.invoke(t2, PropertyUtil.NO_ARGUMENTS), description);
                } catch (InvocationTargetException e2) {
                    description.appendText("Calling '").appendText(method.toString()).appendText("': ").appendValue(e2.getTargetException().getMessage());
                    return Condition.notMatched();
                } catch (Exception e3) {
                    throw new IllegalStateException("Calling: '" + method + "' should not have thrown " + e3);
                }
            }
        };
    }

    private static Condition.Step<PropertyDescriptor, Method> withReadMethod() {
        return new Condition.Step<PropertyDescriptor, Method>() { // from class: org.hamcrest.beans.HasPropertyWithValue.2
            @Override // org.hamcrest.Condition.Step
            public Condition<Method> apply(PropertyDescriptor propertyDescriptor, Description description) {
                Method readMethod = propertyDescriptor.getReadMethod();
                if (readMethod == null) {
                    description.appendText("property \"" + propertyDescriptor.getName() + "\" is not readable");
                    return Condition.notMatched();
                }
                return Condition.matched(readMethod, description);
            }
        };
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("hasProperty(").appendValue(this.propertyName).appendText(", ").appendDescriptionOf(this.valueMatcher).appendText(")");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.hamcrest.TypeSafeDiagnosingMatcher
    public boolean matchesSafely(T t2, Description description) {
        return propertyOn(t2, description).and(WITH_READ_METHOD).and(withPropertyValue(t2)).matching(this.valueMatcher, String.format(this.messageFormat, this.propertyName));
    }
}
