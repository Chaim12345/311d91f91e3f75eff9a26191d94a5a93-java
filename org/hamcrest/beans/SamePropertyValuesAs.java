package org.hamcrest.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsEqual;
/* loaded from: classes4.dex */
public class SamePropertyValuesAs<T> extends DiagnosingMatcher<T> {
    private final T expectedBean;
    private final List<String> ignoredFields;
    private final List<PropertyMatcher> propertyMatchers;
    private final Set<String> propertyNames;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class PropertyMatcher extends DiagnosingMatcher<Object> {
        private final Matcher<Object> matcher;
        private final String propertyName;
        private final Method readMethod;

        public PropertyMatcher(PropertyDescriptor propertyDescriptor, Object obj) {
            this.propertyName = propertyDescriptor.getDisplayName();
            Method readMethod = propertyDescriptor.getReadMethod();
            this.readMethod = readMethod;
            this.matcher = IsEqual.equalTo(SamePropertyValuesAs.readProperty(readMethod, obj));
        }

        @Override // org.hamcrest.SelfDescribing
        public void describeTo(Description description) {
            description.appendText(this.propertyName + ": ").appendDescriptionOf(this.matcher);
        }

        @Override // org.hamcrest.DiagnosingMatcher
        public boolean matches(Object obj, Description description) {
            Object readProperty = SamePropertyValuesAs.readProperty(this.readMethod, obj);
            if (this.matcher.matches(readProperty)) {
                return true;
            }
            description.appendText(this.propertyName + " ");
            this.matcher.describeMismatch(readProperty, description);
            return false;
        }
    }

    public SamePropertyValuesAs(T t2, List<String> list) {
        PropertyDescriptor[] propertyDescriptorsFor = PropertyUtil.propertyDescriptorsFor(t2, Object.class);
        this.expectedBean = t2;
        this.ignoredFields = list;
        this.propertyNames = propertyNamesFrom(propertyDescriptorsFor, list);
        this.propertyMatchers = propertyMatchersFor(t2, propertyDescriptorsFor, list);
    }

    private boolean hasMatchingValues(Object obj, Description description) {
        for (PropertyMatcher propertyMatcher : this.propertyMatchers) {
            if (!propertyMatcher.matches(obj)) {
                propertyMatcher.describeMismatch(obj, description);
                return false;
            }
        }
        return true;
    }

    private boolean hasNoExtraProperties(Object obj, Description description) {
        Set<String> propertyNamesFrom = propertyNamesFrom(PropertyUtil.propertyDescriptorsFor(obj, Object.class), this.ignoredFields);
        propertyNamesFrom.removeAll(this.propertyNames);
        if (propertyNamesFrom.isEmpty()) {
            return true;
        }
        description.appendText("has extra properties called " + propertyNamesFrom);
        return false;
    }

    private boolean isCompatibleType(Object obj, Description description) {
        if (this.expectedBean.getClass().isAssignableFrom(obj.getClass())) {
            return true;
        }
        description.appendText("is incompatible type: " + obj.getClass().getSimpleName());
        return false;
    }

    private static boolean isIgnored(List<String> list, PropertyDescriptor propertyDescriptor) {
        return !list.contains(propertyDescriptor.getDisplayName());
    }

    private static <T> List<PropertyMatcher> propertyMatchersFor(T t2, PropertyDescriptor[] propertyDescriptorArr, List<String> list) {
        ArrayList arrayList = new ArrayList(propertyDescriptorArr.length);
        for (PropertyDescriptor propertyDescriptor : propertyDescriptorArr) {
            if (isIgnored(list, propertyDescriptor)) {
                arrayList.add(new PropertyMatcher(propertyDescriptor, t2));
            }
        }
        return arrayList;
    }

    private static Set<String> propertyNamesFrom(PropertyDescriptor[] propertyDescriptorArr, List<String> list) {
        HashSet hashSet = new HashSet();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptorArr) {
            if (isIgnored(list, propertyDescriptor)) {
                hashSet.add(propertyDescriptor.getDisplayName());
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object readProperty(Method method, Object obj) {
        try {
            return method.invoke(obj, PropertyUtil.NO_ARGUMENTS);
        } catch (Exception e2) {
            throw new IllegalArgumentException("Could not invoke " + method + " on " + obj, e2);
        }
    }

    public static <B> Matcher<B> samePropertyValuesAs(B b2, String... strArr) {
        return new SamePropertyValuesAs(b2, Arrays.asList(strArr));
    }

    @Override // org.hamcrest.SelfDescribing
    public void describeTo(Description description) {
        description.appendText("same property values as " + this.expectedBean.getClass().getSimpleName()).appendList(" [", ", ", "]", this.propertyMatchers);
        if (this.ignoredFields.isEmpty()) {
            return;
        }
        description.appendText(" ignoring ").appendValueList("[", ", ", "]", this.ignoredFields);
    }

    @Override // org.hamcrest.DiagnosingMatcher
    protected boolean matches(Object obj, Description description) {
        return BaseMatcher.a(obj, description) && isCompatibleType(obj, description) && hasNoExtraProperties(obj, description) && hasMatchingValues(obj, description);
    }
}
