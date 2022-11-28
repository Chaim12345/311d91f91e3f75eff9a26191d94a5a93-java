package com.google.common.base;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes2.dex */
public final class Suppliers {

    @VisibleForTesting
    /* loaded from: classes2.dex */
    static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Supplier f8181a;

        /* renamed from: b  reason: collision with root package name */
        final long f8182b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        volatile transient Object f8183c;

        /* renamed from: d  reason: collision with root package name */
        volatile transient long f8184d;

        ExpiringMemoizingSupplier(Supplier supplier, long j2, TimeUnit timeUnit) {
            this.f8181a = (Supplier) Preconditions.checkNotNull(supplier);
            this.f8182b = timeUnit.toNanos(j2);
            Preconditions.checkArgument(j2 > 0, "duration (%s %s) must be > 0", j2, timeUnit);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            long j2 = this.f8184d;
            long i2 = Platform.i();
            if (j2 == 0 || i2 - j2 >= 0) {
                synchronized (this) {
                    if (j2 == this.f8184d) {
                        T t2 = (T) this.f8181a.get();
                        this.f8183c = t2;
                        long j3 = i2 + this.f8182b;
                        if (j3 == 0) {
                            j3 = 1;
                        }
                        this.f8184d = j3;
                        return t2;
                    }
                }
            }
            return (T) this.f8183c;
        }

        public String toString() {
            return "Suppliers.memoizeWithExpiration(" + this.f8181a + ", " + this.f8182b + ", NANOS)";
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Supplier f8185a;

        /* renamed from: b  reason: collision with root package name */
        volatile transient boolean f8186b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        transient Object f8187c;

        MemoizingSupplier(Supplier supplier) {
            this.f8185a = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (!this.f8186b) {
                synchronized (this) {
                    if (!this.f8186b) {
                        T t2 = (T) this.f8185a.get();
                        this.f8187c = t2;
                        this.f8186b = true;
                        return t2;
                    }
                }
            }
            return (T) this.f8187c;
        }

        public String toString() {
            Object obj;
            StringBuilder sb = new StringBuilder();
            sb.append("Suppliers.memoize(");
            if (this.f8186b) {
                obj = "<supplier that returned " + this.f8187c + ">";
            } else {
                obj = this.f8185a;
            }
            sb.append(obj);
            sb.append(")");
            return sb.toString();
        }
    }

    @VisibleForTesting
    /* loaded from: classes2.dex */
    static class NonSerializableMemoizingSupplier<T> implements Supplier<T> {

        /* renamed from: a  reason: collision with root package name */
        volatile Supplier f8188a;

        /* renamed from: b  reason: collision with root package name */
        volatile boolean f8189b;
        @NullableDecl

        /* renamed from: c  reason: collision with root package name */
        Object f8190c;

        NonSerializableMemoizingSupplier(Supplier supplier) {
            this.f8188a = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            if (!this.f8189b) {
                synchronized (this) {
                    if (!this.f8189b) {
                        T t2 = (T) this.f8188a.get();
                        this.f8190c = t2;
                        this.f8189b = true;
                        this.f8188a = null;
                        return t2;
                    }
                }
            }
            return (T) this.f8190c;
        }

        public String toString() {
            Object obj = this.f8188a;
            StringBuilder sb = new StringBuilder();
            sb.append("Suppliers.memoize(");
            if (obj == null) {
                obj = "<supplier that returned " + this.f8190c + ">";
            }
            sb.append(obj);
            sb.append(")");
            return sb.toString();
        }
    }

    /* loaded from: classes2.dex */
    private static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Function f8191a;

        /* renamed from: b  reason: collision with root package name */
        final Supplier f8192b;

        SupplierComposition(Function function, Supplier supplier) {
            this.f8191a = (Function) Preconditions.checkNotNull(function);
            this.f8192b = (Supplier) Preconditions.checkNotNull(supplier);
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof SupplierComposition) {
                SupplierComposition supplierComposition = (SupplierComposition) obj;
                return this.f8191a.equals(supplierComposition.f8191a) && this.f8192b.equals(supplierComposition.f8192b);
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.base.Supplier
        public T get() {
            return (T) this.f8191a.apply(this.f8192b.get());
        }

        public int hashCode() {
            return Objects.hashCode(this.f8191a, this.f8192b);
        }

        public String toString() {
            return "Suppliers.compose(" + this.f8191a + ", " + this.f8192b + ")";
        }
    }

    /* loaded from: classes2.dex */
    private interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    /* loaded from: classes2.dex */
    private enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        @Override // com.google.common.base.Function
        public Object apply(Supplier<Object> supplier) {
            return supplier.get();
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Suppliers.supplierFunction()";
        }
    }

    /* loaded from: classes2.dex */
    private static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        @NullableDecl

        /* renamed from: a  reason: collision with root package name */
        final Object f8193a;

        SupplierOfInstance(@NullableDecl Object obj) {
            this.f8193a = obj;
        }

        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof SupplierOfInstance) {
                return Objects.equal(this.f8193a, ((SupplierOfInstance) obj).f8193a);
            }
            return false;
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            return (T) this.f8193a;
        }

        public int hashCode() {
            return Objects.hashCode(this.f8193a);
        }

        public String toString() {
            return "Suppliers.ofInstance(" + this.f8193a + ")";
        }
    }

    /* loaded from: classes2.dex */
    private static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;

        /* renamed from: a  reason: collision with root package name */
        final Supplier f8194a;

        ThreadSafeSupplier(Supplier supplier) {
            this.f8194a = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @Override // com.google.common.base.Supplier
        public T get() {
            T t2;
            synchronized (this.f8194a) {
                t2 = (T) this.f8194a.get();
            }
            return t2;
        }

        public String toString() {
            return "Suppliers.synchronizedSupplier(" + this.f8194a + ")";
        }
    }

    private Suppliers() {
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        return new SupplierComposition(function, supplier);
    }

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        return ((supplier instanceof NonSerializableMemoizingSupplier) || (supplier instanceof MemoizingSupplier)) ? supplier : supplier instanceof Serializable ? new MemoizingSupplier(supplier) : new NonSerializableMemoizingSupplier(supplier);
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> supplier, long j2, TimeUnit timeUnit) {
        return new ExpiringMemoizingSupplier(supplier, j2, timeUnit);
    }

    public static <T> Supplier<T> ofInstance(@NullableDecl T t2) {
        return new SupplierOfInstance(t2);
    }

    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> supplier) {
        return new ThreadSafeSupplier(supplier);
    }
}
