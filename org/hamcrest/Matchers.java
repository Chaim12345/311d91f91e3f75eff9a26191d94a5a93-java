package org.hamcrest;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.EventObject;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.xml.namespace.NamespaceContext;
import org.hamcrest.beans.HasProperty;
import org.hamcrest.beans.HasPropertyWithValue;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.hamcrest.collection.ArrayMatching;
import org.hamcrest.collection.IsArray;
import org.hamcrest.collection.IsArrayWithSize;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.collection.IsEmptyIterable;
import org.hamcrest.collection.IsIn;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.collection.IsIterableContainingInRelativeOrder;
import org.hamcrest.collection.IsIterableWithSize;
import org.hamcrest.collection.IsMapContaining;
import org.hamcrest.collection.IsMapWithSize;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.CombinableMatcher;
import org.hamcrest.core.DescribedAs;
import org.hamcrest.core.Every;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsAnything;
import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsIterableContaining;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.hamcrest.core.IsSame;
import org.hamcrest.core.StringContains;
import org.hamcrest.core.StringEndsWith;
import org.hamcrest.core.StringRegularExpression;
import org.hamcrest.core.StringStartsWith;
import org.hamcrest.number.BigDecimalCloseTo;
import org.hamcrest.number.IsCloseTo;
import org.hamcrest.number.IsNaN;
import org.hamcrest.number.OrderingComparison;
import org.hamcrest.object.HasToString;
import org.hamcrest.object.IsCompatibleType;
import org.hamcrest.object.IsEventFrom;
import org.hamcrest.text.CharSequenceLength;
import org.hamcrest.text.IsBlankString;
import org.hamcrest.text.IsEmptyString;
import org.hamcrest.text.IsEqualCompressingWhiteSpace;
import org.hamcrest.text.IsEqualIgnoringCase;
import org.hamcrest.text.MatchesPattern;
import org.hamcrest.text.StringContainsInOrder;
import org.hamcrest.xml.HasXPath;
import org.w3c.dom.Node;
/* loaded from: classes4.dex */
public class Matchers {
    public static <K, V> Matcher<Map<? extends K, ? extends V>> aMapWithSize(int i2) {
        return IsMapWithSize.aMapWithSize(i2);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> aMapWithSize(Matcher<? super Integer> matcher) {
        return IsMapWithSize.aMapWithSize(matcher);
    }

    public static <T> Matcher<T> allOf(Iterable<Matcher<? super T>> iterable) {
        return AllOf.allOf(iterable);
    }

    public static <T> Matcher<T> allOf(Matcher<? super T> matcher, Matcher<? super T> matcher2) {
        return AllOf.allOf(matcher, matcher2);
    }

    public static <T> Matcher<T> allOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3) {
        return AllOf.allOf(matcher, matcher2, matcher3);
    }

    public static <T> Matcher<T> allOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3, Matcher<? super T> matcher4) {
        return AllOf.allOf(matcher, matcher2, matcher3, matcher4);
    }

    public static <T> Matcher<T> allOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3, Matcher<? super T> matcher4, Matcher<? super T> matcher5) {
        return AllOf.allOf(matcher, matcher2, matcher3, matcher4, matcher5);
    }

    public static <T> Matcher<T> allOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3, Matcher<? super T> matcher4, Matcher<? super T> matcher5, Matcher<? super T> matcher6) {
        return AllOf.allOf(matcher, matcher2, matcher3, matcher4, matcher5, matcher6);
    }

    @SafeVarargs
    public static <T> Matcher<T> allOf(Matcher<? super T>... matcherArr) {
        return AllOf.allOf(matcherArr);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> anEmptyMap() {
        return IsMapWithSize.anEmptyMap();
    }

    public static <T> Matcher<T> any(Class<T> cls) {
        return IsInstanceOf.any(cls);
    }

    public static <T> AnyOf<T> anyOf(Iterable<Matcher<? super T>> iterable) {
        return AnyOf.anyOf(iterable);
    }

    public static <T> AnyOf<T> anyOf(Matcher<? super T> matcher, Matcher<? super T> matcher2) {
        return AnyOf.anyOf(matcher, matcher2);
    }

    public static <T> AnyOf<T> anyOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3) {
        return AnyOf.anyOf(matcher, matcher2, matcher3);
    }

    public static <T> AnyOf<T> anyOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3, Matcher<? super T> matcher4) {
        return AnyOf.anyOf(matcher, matcher2, matcher3, matcher4);
    }

    public static <T> AnyOf<T> anyOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3, Matcher<? super T> matcher4, Matcher<? super T> matcher5) {
        return AnyOf.anyOf(matcher, matcher2, matcher3, matcher4, matcher5);
    }

    public static <T> AnyOf<T> anyOf(Matcher<? super T> matcher, Matcher<? super T> matcher2, Matcher<? super T> matcher3, Matcher<? super T> matcher4, Matcher<? super T> matcher5, Matcher<? super T> matcher6) {
        return AnyOf.anyOf(matcher, matcher2, matcher3, matcher4, matcher5, matcher6);
    }

    @SafeVarargs
    public static <T> AnyOf<T> anyOf(Matcher<? super T>... matcherArr) {
        return AnyOf.anyOf(matcherArr);
    }

    public static Matcher<Object> anything() {
        return IsAnything.anything();
    }

    public static Matcher<Object> anything(String str) {
        return IsAnything.anything(str);
    }

    @SafeVarargs
    public static <T> IsArray<T> array(Matcher<? super T>... matcherArr) {
        return IsArray.array(matcherArr);
    }

    public static <E> Matcher<E[]> arrayContaining(List<Matcher<? super E>> list) {
        return ArrayMatching.arrayContaining(list);
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContaining(E... eArr) {
        return ArrayMatching.arrayContaining(eArr);
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContaining(Matcher<? super E>... matcherArr) {
        return ArrayMatching.arrayContaining((Matcher[]) matcherArr);
    }

    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Collection<Matcher<? super E>> collection) {
        return ArrayMatching.arrayContainingInAnyOrder(collection);
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContainingInAnyOrder(E... eArr) {
        return ArrayMatching.arrayContainingInAnyOrder(eArr);
    }

    @SafeVarargs
    public static <E> Matcher<E[]> arrayContainingInAnyOrder(Matcher<? super E>... matcherArr) {
        return ArrayMatching.arrayContainingInAnyOrder((Matcher[]) matcherArr);
    }

    public static <E> Matcher<E[]> arrayWithSize(int i2) {
        return IsArrayWithSize.arrayWithSize(i2);
    }

    public static <E> Matcher<E[]> arrayWithSize(Matcher<? super Integer> matcher) {
        return IsArrayWithSize.arrayWithSize(matcher);
    }

    public static Matcher<String> blankOrNullString() {
        return IsBlankString.blankOrNullString();
    }

    public static Matcher<String> blankString() {
        return IsBlankString.blankString();
    }

    public static <LHS> CombinableMatcher.CombinableBothMatcher<LHS> both(Matcher<? super LHS> matcher) {
        return CombinableMatcher.both(matcher);
    }

    public static Matcher<Double> closeTo(double d2, double d3) {
        return IsCloseTo.closeTo(d2, d3);
    }

    public static Matcher<BigDecimal> closeTo(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        return BigDecimalCloseTo.closeTo(bigDecimal, bigDecimal2);
    }

    public static <T extends Comparable<T>> Matcher<T> comparesEqualTo(T t2) {
        return OrderingComparison.comparesEqualTo(t2);
    }

    public static <E> Matcher<Iterable<? extends E>> contains(List<Matcher<? super E>> list) {
        return IsIterableContainingInOrder.contains(list);
    }

    public static <E> Matcher<Iterable<? extends E>> contains(Matcher<? super E> matcher) {
        return IsIterableContainingInOrder.contains(matcher);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> contains(E... eArr) {
        return IsIterableContainingInOrder.contains(eArr);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> contains(Matcher<? super E>... matcherArr) {
        return IsIterableContainingInOrder.contains((Matcher[]) matcherArr);
    }

    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(Collection<Matcher<? super T>> collection) {
        return IsIterableContainingInAnyOrder.containsInAnyOrder(collection);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(T... tArr) {
        return IsIterableContainingInAnyOrder.containsInAnyOrder(tArr);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<? extends T>> containsInAnyOrder(Matcher<? super T>... matcherArr) {
        return IsIterableContainingInAnyOrder.containsInAnyOrder((Matcher[]) matcherArr);
    }

    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(List<Matcher<? super E>> list) {
        return IsIterableContainingInRelativeOrder.containsInRelativeOrder(list);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(E... eArr) {
        return IsIterableContainingInRelativeOrder.containsInRelativeOrder(eArr);
    }

    @SafeVarargs
    public static <E> Matcher<Iterable<? extends E>> containsInRelativeOrder(Matcher<? super E>... matcherArr) {
        return IsIterableContainingInRelativeOrder.containsInRelativeOrder((Matcher[]) matcherArr);
    }

    public static Matcher<String> containsString(String str) {
        return StringContains.containsString(str);
    }

    public static Matcher<String> containsStringIgnoringCase(String str) {
        return StringContains.containsStringIgnoringCase(str);
    }

    public static <T> Matcher<T> describedAs(String str, Matcher<T> matcher, Object... objArr) {
        return DescribedAs.describedAs(str, matcher, objArr);
    }

    public static <LHS> CombinableMatcher.CombinableEitherMatcher<LHS> either(Matcher<? super LHS> matcher) {
        return CombinableMatcher.either(matcher);
    }

    public static <E> Matcher<Collection<? extends E>> empty() {
        return IsEmptyCollection.empty();
    }

    public static <E> Matcher<E[]> emptyArray() {
        return IsArrayWithSize.emptyArray();
    }

    public static <E> Matcher<Collection<E>> emptyCollectionOf(Class<E> cls) {
        return IsEmptyCollection.emptyCollectionOf(cls);
    }

    public static <E> Matcher<Iterable<? extends E>> emptyIterable() {
        return IsEmptyIterable.emptyIterable();
    }

    public static <E> Matcher<Iterable<E>> emptyIterableOf(Class<E> cls) {
        return IsEmptyIterable.emptyIterableOf(cls);
    }

    public static Matcher<String> emptyOrNullString() {
        return IsEmptyString.emptyOrNullString();
    }

    public static Matcher<String> emptyString() {
        return IsEmptyString.emptyString();
    }

    public static Matcher<String> endsWith(String str) {
        return StringEndsWith.endsWith(str);
    }

    public static Matcher<String> endsWithIgnoringCase(String str) {
        return StringEndsWith.endsWithIgnoringCase(str);
    }

    public static <T> Matcher<T> equalTo(T t2) {
        return IsEqual.equalTo(t2);
    }

    public static Matcher<String> equalToCompressingWhiteSpace(String str) {
        return IsEqualCompressingWhiteSpace.equalToCompressingWhiteSpace(str);
    }

    public static Matcher<String> equalToIgnoringCase(String str) {
        return IsEqualIgnoringCase.equalToIgnoringCase(str);
    }

    public static Matcher<String> equalToIgnoringWhiteSpace(String str) {
        return equalToCompressingWhiteSpace(str);
    }

    public static Matcher<Object> equalToObject(Object obj) {
        return IsEqual.equalToObject(obj);
    }

    public static Matcher<EventObject> eventFrom(Class<? extends EventObject> cls, Object obj) {
        return IsEventFrom.eventFrom(cls, obj);
    }

    public static Matcher<EventObject> eventFrom(Object obj) {
        return IsEventFrom.eventFrom(obj);
    }

    public static <U> Matcher<Iterable<? extends U>> everyItem(Matcher<U> matcher) {
        return Every.everyItem(matcher);
    }

    public static <T extends Comparable<T>> Matcher<T> greaterThan(T t2) {
        return OrderingComparison.greaterThan(t2);
    }

    public static <T extends Comparable<T>> Matcher<T> greaterThanOrEqualTo(T t2) {
        return OrderingComparison.greaterThanOrEqualTo(t2);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> hasEntry(K k2, V v) {
        return IsMapContaining.hasEntry(k2, v);
    }

    public static <K, V> Matcher<Map<? extends K, ? extends V>> hasEntry(Matcher<? super K> matcher, Matcher<? super V> matcher2) {
        return IsMapContaining.hasEntry((Matcher) matcher, (Matcher) matcher2);
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(T t2) {
        return IsIterableContaining.hasItem(t2);
    }

    public static <T> Matcher<Iterable<? super T>> hasItem(Matcher<? super T> matcher) {
        return IsIterableContaining.hasItem((Matcher) matcher);
    }

    public static <T> Matcher<T[]> hasItemInArray(T t2) {
        return ArrayMatching.hasItemInArray(t2);
    }

    public static <T> Matcher<T[]> hasItemInArray(Matcher<? super T> matcher) {
        return ArrayMatching.hasItemInArray((Matcher) matcher);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(T... tArr) {
        return IsIterableContaining.hasItems(tArr);
    }

    @SafeVarargs
    public static <T> Matcher<Iterable<T>> hasItems(Matcher<? super T>... matcherArr) {
        return IsIterableContaining.hasItems((Matcher[]) matcherArr);
    }

    public static <K> Matcher<Map<? extends K, ?>> hasKey(K k2) {
        return IsMapContaining.hasKey(k2);
    }

    public static <K> Matcher<Map<? extends K, ?>> hasKey(Matcher<? super K> matcher) {
        return IsMapContaining.hasKey((Matcher) matcher);
    }

    public static Matcher<CharSequence> hasLength(int i2) {
        return CharSequenceLength.hasLength(i2);
    }

    public static <T> Matcher<T> hasProperty(String str) {
        return HasProperty.hasProperty(str);
    }

    public static <T> Matcher<T> hasProperty(String str, Matcher<?> matcher) {
        return HasPropertyWithValue.hasProperty(str, matcher);
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(int i2) {
        return IsCollectionWithSize.hasSize(i2);
    }

    public static <E> Matcher<Collection<? extends E>> hasSize(Matcher<? super Integer> matcher) {
        return IsCollectionWithSize.hasSize(matcher);
    }

    public static <T> Matcher<T> hasToString(String str) {
        return HasToString.hasToString(str);
    }

    public static <T> Matcher<T> hasToString(Matcher<? super String> matcher) {
        return HasToString.hasToString(matcher);
    }

    public static <V> Matcher<Map<?, ? extends V>> hasValue(V v) {
        return IsMapContaining.hasValue(v);
    }

    public static <V> Matcher<Map<?, ? extends V>> hasValue(Matcher<? super V> matcher) {
        return IsMapContaining.hasValue((Matcher) matcher);
    }

    public static Matcher<Node> hasXPath(String str) {
        return HasXPath.hasXPath(str);
    }

    public static Matcher<Node> hasXPath(String str, NamespaceContext namespaceContext) {
        return HasXPath.hasXPath(str, namespaceContext);
    }

    public static Matcher<Node> hasXPath(String str, NamespaceContext namespaceContext, Matcher<String> matcher) {
        return HasXPath.hasXPath(str, namespaceContext, matcher);
    }

    public static Matcher<Node> hasXPath(String str, Matcher<String> matcher) {
        return HasXPath.hasXPath(str, matcher);
    }

    public static <T> Matcher<T> in(Collection<T> collection) {
        return IsIn.in(collection);
    }

    public static <T> Matcher<T> in(T[] tArr) {
        return IsIn.in(tArr);
    }

    public static <T> Matcher<T> instanceOf(Class<?> cls) {
        return IsInstanceOf.instanceOf(cls);
    }

    public static <T> Matcher<T> is(T t2) {
        return Is.is(t2);
    }

    public static <T> Matcher<T> is(Matcher<T> matcher) {
        return Is.is((Matcher) matcher);
    }

    public static <T> Matcher<T> isA(Class<?> cls) {
        return Is.isA(cls);
    }

    public static Matcher<String> isEmptyOrNullString() {
        return IsEmptyString.isEmptyOrNullString();
    }

    public static Matcher<String> isEmptyString() {
        return IsEmptyString.isEmptyString();
    }

    public static <T> Matcher<T> isIn(Collection<T> collection) {
        return IsIn.isIn(collection);
    }

    public static <T> Matcher<T> isIn(T[] tArr) {
        return IsIn.isIn(tArr);
    }

    @SafeVarargs
    public static <T> Matcher<T> isOneOf(T... tArr) {
        return IsIn.isOneOf(tArr);
    }

    public static <E> Matcher<Iterable<E>> iterableWithSize(int i2) {
        return IsIterableWithSize.iterableWithSize(i2);
    }

    public static <E> Matcher<Iterable<E>> iterableWithSize(Matcher<? super Integer> matcher) {
        return IsIterableWithSize.iterableWithSize(matcher);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThan(T t2) {
        return OrderingComparison.lessThan(t2);
    }

    public static <T extends Comparable<T>> Matcher<T> lessThanOrEqualTo(T t2) {
        return OrderingComparison.lessThanOrEqualTo(t2);
    }

    public static Matcher<String> matchesPattern(String str) {
        return MatchesPattern.matchesPattern(str);
    }

    public static Matcher<String> matchesPattern(Pattern pattern) {
        return MatchesPattern.matchesPattern(pattern);
    }

    public static Matcher<String> matchesRegex(String str) {
        return StringRegularExpression.matchesRegex(Pattern.compile(str));
    }

    public static Matcher<String> matchesRegex(Pattern pattern) {
        return StringRegularExpression.matchesRegex(pattern);
    }

    public static <T> Matcher<T> not(T t2) {
        return IsNot.not(t2);
    }

    public static <T> Matcher<T> not(Matcher<T> matcher) {
        return IsNot.not((Matcher) matcher);
    }

    public static Matcher<Double> notANumber() {
        return IsNaN.notANumber();
    }

    public static Matcher<Object> notNullValue() {
        return IsNull.notNullValue();
    }

    public static <T> Matcher<T> notNullValue(Class<T> cls) {
        return IsNull.notNullValue(cls);
    }

    public static Matcher<Object> nullValue() {
        return IsNull.nullValue();
    }

    public static <T> Matcher<T> nullValue(Class<T> cls) {
        return IsNull.nullValue(cls);
    }

    @SafeVarargs
    public static <T> Matcher<T> oneOf(T... tArr) {
        return IsIn.oneOf(tArr);
    }

    public static <T> Matcher<T> sameInstance(T t2) {
        return IsSame.sameInstance(t2);
    }

    public static <B> Matcher<B> samePropertyValuesAs(B b2, String... strArr) {
        return SamePropertyValuesAs.samePropertyValuesAs(b2, strArr);
    }

    public static Matcher<String> startsWith(String str) {
        return StringStartsWith.startsWith(str);
    }

    public static Matcher<String> startsWithIgnoringCase(String str) {
        return StringStartsWith.startsWithIgnoringCase(str);
    }

    public static Matcher<String> stringContainsInOrder(Iterable<String> iterable) {
        return StringContainsInOrder.stringContainsInOrder(iterable);
    }

    public static Matcher<String> stringContainsInOrder(String... strArr) {
        return StringContainsInOrder.stringContainsInOrder(strArr);
    }

    public static <T> Matcher<T> theInstance(T t2) {
        return IsSame.theInstance(t2);
    }

    public static <T> Matcher<Class<?>> typeCompatibleWith(Class<T> cls) {
        return IsCompatibleType.typeCompatibleWith(cls);
    }
}
