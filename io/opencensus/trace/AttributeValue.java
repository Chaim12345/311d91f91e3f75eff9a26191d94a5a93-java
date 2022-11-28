package io.opencensus.trace;

import io.opencensus.common.Function;
import io.opencensus.internal.Utils;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class AttributeValue {

    /* JADX INFO: Access modifiers changed from: package-private */
    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class AttributeValueBoolean extends AttributeValue {
        static AttributeValue a(Boolean bool) {
            return new AutoValue_AttributeValue_AttributeValueBoolean((Boolean) Utils.checkNotNull(bool, "booleanValue"));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Boolean b();

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function2.apply(b());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function2.apply(b());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Immutable
    /* loaded from: classes3.dex */
    public static abstract class AttributeValueDouble extends AttributeValue {
        static AttributeValue a(Double d2) {
            return new AutoValue_AttributeValue_AttributeValueDouble((Double) Utils.checkNotNull(d2, "doubleValue"));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Double b();

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function4.apply(b());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function4.apply(b());
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    static abstract class AttributeValueLong extends AttributeValue {
        static AttributeValue a(Long l2) {
            return new AutoValue_AttributeValue_AttributeValueLong((Long) Utils.checkNotNull(l2, "longValue"));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Long b();

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function3.apply(b());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function3.apply(b());
        }
    }

    @Immutable
    /* loaded from: classes3.dex */
    static abstract class AttributeValueString extends AttributeValue {
        static AttributeValue a(String str) {
            return new AutoValue_AttributeValue_AttributeValueString((String) Utils.checkNotNull(str, "stringValue"));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String b();

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4) {
            return function.apply(b());
        }

        @Override // io.opencensus.trace.AttributeValue
        public final <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5) {
            return function.apply(b());
        }
    }

    AttributeValue() {
    }

    public static AttributeValue booleanAttributeValue(boolean z) {
        return AttributeValueBoolean.a(Boolean.valueOf(z));
    }

    public static AttributeValue doubleAttributeValue(double d2) {
        return AttributeValueDouble.a(Double.valueOf(d2));
    }

    public static AttributeValue longAttributeValue(long j2) {
        return AttributeValueLong.a(Long.valueOf(j2));
    }

    public static AttributeValue stringAttributeValue(String str) {
        return AttributeValueString.a(str);
    }

    @Deprecated
    public abstract <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<Object, T> function4);

    public abstract <T> T match(Function<? super String, T> function, Function<? super Boolean, T> function2, Function<? super Long, T> function3, Function<? super Double, T> function4, Function<Object, T> function5);
}
