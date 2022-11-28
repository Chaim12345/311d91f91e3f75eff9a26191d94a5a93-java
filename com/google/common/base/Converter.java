package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    @NullableDecl
    @LazyInit
    private transient Converter<B, A> reverse;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Converter f8133a;

        /* renamed from: b  reason: collision with root package name */
        final Converter f8134b;

        ConverterComposition(Converter converter, Converter converter2) {
            this.f8133a = converter;
            this.f8134b = converter2;
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        Object a(@NullableDecl Object obj) {
            return this.f8133a.a(this.f8134b.a(obj));
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        Object b(@NullableDecl Object obj) {
            return this.f8134b.b(this.f8133a.b(obj));
        }

        @Override // com.google.common.base.Converter
        protected Object d(Object obj) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        protected Object e(Object obj) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ConverterComposition) {
                ConverterComposition converterComposition = (ConverterComposition) obj;
                return this.f8133a.equals(converterComposition.f8133a) && this.f8134b.equals(converterComposition.f8134b);
            }
            return false;
        }

        public int hashCode() {
            return (this.f8133a.hashCode() * 31) + this.f8134b.hashCode();
        }

        public String toString() {
            return this.f8133a + ".andThen(" + this.f8134b + ")";
        }
    }

    /* loaded from: classes2.dex */
    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(function);
            this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
        }

        @Override // com.google.common.base.Converter
        protected Object d(Object obj) {
            return this.backwardFunction.apply(obj);
        }

        @Override // com.google.common.base.Converter
        protected Object e(Object obj) {
            return this.forwardFunction.apply(obj);
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof FunctionBasedConverter) {
                FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
                return this.forwardFunction.equals(functionBasedConverter.forwardFunction) && this.backwardFunction.equals(functionBasedConverter.backwardFunction);
            }
            return false;
        }

        public int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        public String toString() {
            return "Converter.from(" + this.forwardFunction + ", " + this.backwardFunction + ")";
        }
    }

    /* loaded from: classes2.dex */
    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {

        /* renamed from: a  reason: collision with root package name */
        static final IdentityConverter f8135a = new IdentityConverter();
        private static final long serialVersionUID = 0;

        private IdentityConverter() {
        }

        private Object readResolve() {
            return f8135a;
        }

        @Override // com.google.common.base.Converter
        Converter c(Converter converter) {
            return (Converter) Preconditions.checkNotNull(converter, "otherConverter");
        }

        @Override // com.google.common.base.Converter
        protected Object d(Object obj) {
            return obj;
        }

        @Override // com.google.common.base.Converter
        protected Object e(Object obj) {
            return obj;
        }

        @Override // com.google.common.base.Converter
        public IdentityConverter<T> reverse() {
            return this;
        }

        public String toString() {
            return "Converter.identity()";
        }
    }

    /* loaded from: classes2.dex */
    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Converter f8136a;

        ReverseConverter(Converter converter) {
            this.f8136a = converter;
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        Object a(@NullableDecl Object obj) {
            return this.f8136a.b(obj);
        }

        @Override // com.google.common.base.Converter
        @NullableDecl
        Object b(@NullableDecl Object obj) {
            return this.f8136a.a(obj);
        }

        @Override // com.google.common.base.Converter
        protected Object d(Object obj) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter
        protected Object e(Object obj) {
            throw new AssertionError();
        }

        @Override // com.google.common.base.Converter, com.google.common.base.Function
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.f8136a.equals(((ReverseConverter) obj).f8136a);
            }
            return false;
        }

        public int hashCode() {
            return ~this.f8136a.hashCode();
        }

        @Override // com.google.common.base.Converter
        public Converter<A, B> reverse() {
            return this.f8136a;
        }

        public String toString() {
            return this.f8136a + ".reverse()";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.f8135a;
    }

    @NullableDecl
    Object a(@NullableDecl Object obj) {
        if (this.handleNullAutomatically) {
            if (obj == null) {
                return null;
            }
            return Preconditions.checkNotNull(d(obj));
        }
        return d(obj);
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return c(converter);
    }

    @Override // com.google.common.base.Function
    @CanIgnoreReturnValue
    @NullableDecl
    @Deprecated
    public final B apply(@NullableDecl A a2) {
        return convert(a2);
    }

    @NullableDecl
    Object b(@NullableDecl Object obj) {
        if (this.handleNullAutomatically) {
            if (obj == null) {
                return null;
            }
            return Preconditions.checkNotNull(e(obj));
        }
        return e(obj);
    }

    Converter c(Converter converter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    @CanIgnoreReturnValue
    @NullableDecl
    public final B convert(@NullableDecl A a2) {
        return (B) b(a2);
    }

    @CanIgnoreReturnValue
    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() { // from class: com.google.common.base.Converter.1
            @Override // java.lang.Iterable
            public Iterator<B> iterator() {
                return new Iterator<B>() { // from class: com.google.common.base.Converter.1.1
                    private final Iterator<? extends A> fromIterator;

                    {
                        this.fromIterator = iterable.iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    @Override // java.util.Iterator
                    public B next() {
                        return (B) Converter.this.convert(this.fromIterator.next());
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    @ForOverride
    protected abstract Object d(Object obj);

    @ForOverride
    protected abstract Object e(Object obj);

    @Override // com.google.common.base.Function
    public boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @CanIgnoreReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter == null) {
            ReverseConverter reverseConverter = new ReverseConverter(this);
            this.reverse = reverseConverter;
            return reverseConverter;
        }
        return converter;
    }
}
