package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class MoreObjects {

    /* loaded from: classes2.dex */
    public static final class ToStringHelper {
        private final String className;
        private final ValueHolder holderHead;
        private ValueHolder holderTail;
        private boolean omitNullValues;

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static final class ValueHolder {
            @NullableDecl

            /* renamed from: a  reason: collision with root package name */
            String f8155a;
            @NullableDecl

            /* renamed from: b  reason: collision with root package name */
            Object f8156b;
            @NullableDecl

            /* renamed from: c  reason: collision with root package name */
            ValueHolder f8157c;

            private ValueHolder() {
            }
        }

        private ToStringHelper(String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.f8157c = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        private ToStringHelper addHolder(@NullableDecl Object obj) {
            addHolder().f8156b = obj;
            return this;
        }

        private ToStringHelper addHolder(String str, @NullableDecl Object obj) {
            ValueHolder addHolder = addHolder();
            addHolder.f8156b = obj;
            addHolder.f8155a = (String) Preconditions.checkNotNull(str);
            return this;
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, char c2) {
            return addHolder(str, String.valueOf(c2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, double d2) {
            return addHolder(str, String.valueOf(d2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, float f2) {
            return addHolder(str, String.valueOf(f2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, int i2) {
            return addHolder(str, String.valueOf(i2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, long j2) {
            return addHolder(str, String.valueOf(j2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, @NullableDecl Object obj) {
            return addHolder(str, obj);
        }

        @CanIgnoreReturnValue
        public ToStringHelper add(String str, boolean z) {
            return addHolder(str, String.valueOf(z));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(char c2) {
            return addHolder(String.valueOf(c2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(double d2) {
            return addHolder(String.valueOf(d2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(float f2) {
            return addHolder(String.valueOf(f2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(int i2) {
            return addHolder(String.valueOf(i2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(long j2) {
            return addHolder(String.valueOf(j2));
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(@NullableDecl Object obj) {
            return addHolder(obj);
        }

        @CanIgnoreReturnValue
        public ToStringHelper addValue(boolean z) {
            return addHolder(String.valueOf(z));
        }

        @CanIgnoreReturnValue
        public ToStringHelper omitNullValues() {
            this.omitNullValues = true;
            return this;
        }

        public String toString() {
            boolean z = this.omitNullValues;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append(AbstractJsonLexerKt.BEGIN_OBJ);
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.f8157c; valueHolder != null; valueHolder = valueHolder.f8157c) {
                Object obj = valueHolder.f8156b;
                if (!z || obj != null) {
                    sb.append(str);
                    String str2 = valueHolder.f8155a;
                    if (str2 != null) {
                        sb.append(str2);
                        sb.append('=');
                    }
                    if (obj == null || !obj.getClass().isArray()) {
                        sb.append(obj);
                    } else {
                        String deepToString = Arrays.deepToString(new Object[]{obj});
                        sb.append((CharSequence) deepToString, 1, deepToString.length() - 1);
                    }
                    str = ", ";
                }
            }
            sb.append(AbstractJsonLexerKt.END_OBJ);
            return sb.toString();
        }
    }

    private MoreObjects() {
    }

    public static <T> T firstNonNull(@NullableDecl T t2, @NullableDecl T t3) {
        if (t2 != null) {
            return t2;
        }
        java.util.Objects.requireNonNull(t3, "Both parameters are null");
        return t3;
    }

    public static ToStringHelper toStringHelper(Class<?> cls) {
        return new ToStringHelper(cls.getSimpleName());
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static ToStringHelper toStringHelper(String str) {
        return new ToStringHelper(str);
    }
}
