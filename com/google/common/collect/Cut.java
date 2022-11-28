package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes2.dex */
public abstract class Cut<C extends Comparable> implements Comparable<Cut<C>>, Serializable {
    private static final long serialVersionUID = 0;
    @NullableDecl

    /* renamed from: a  reason: collision with root package name */
    final Comparable f8492a;

    /* renamed from: com.google.common.collect.Cut$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f8493a;

        static {
            int[] iArr = new int[BoundType.values().length];
            f8493a = iArr;
            try {
                iArr[BoundType.CLOSED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8493a[BoundType.OPEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class AboveAll extends Cut<Comparable<?>> {
        private static final AboveAll INSTANCE = new AboveAll();
        private static final long serialVersionUID = 0;

        private AboveAll() {
            super(null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : 1;
        }

        @Override // com.google.common.collect.Cut
        void f(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        void g(StringBuilder sb) {
            sb.append("+∞)");
        }

        @Override // com.google.common.collect.Cut
        Comparable h() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.Cut
        Comparable i(DiscreteDomain discreteDomain) {
            return discreteDomain.maxValue();
        }

        @Override // com.google.common.collect.Cut
        boolean j(Comparable comparable) {
            return false;
        }

        @Override // com.google.common.collect.Cut
        Comparable k(DiscreteDomain discreteDomain) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        BoundType l() {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut
        BoundType m() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut
        Cut n(BoundType boundType, DiscreteDomain discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut
        Cut o(BoundType boundType, DiscreteDomain discreteDomain) {
            throw new IllegalStateException();
        }

        public String toString() {
            return "+∞";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class AboveValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        AboveValue(Comparable comparable) {
            super((Comparable) Preconditions.checkNotNull(comparable));
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((Cut) ((Cut) obj));
        }

        @Override // com.google.common.collect.Cut
        Cut e(DiscreteDomain discreteDomain) {
            Comparable k2 = k(discreteDomain);
            return k2 != null ? Cut.d(k2) : Cut.a();
        }

        @Override // com.google.common.collect.Cut
        void f(StringBuilder sb) {
            sb.append('(');
            sb.append(this.f8492a);
        }

        @Override // com.google.common.collect.Cut
        void g(StringBuilder sb) {
            sb.append(this.f8492a);
            sb.append(AbstractJsonLexerKt.END_LIST);
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return ~this.f8492a.hashCode();
        }

        @Override // com.google.common.collect.Cut
        Comparable i(DiscreteDomain discreteDomain) {
            return this.f8492a;
        }

        @Override // com.google.common.collect.Cut
        boolean j(Comparable comparable) {
            return Range.a(this.f8492a, comparable) < 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Cut
        Comparable k(DiscreteDomain discreteDomain) {
            return discreteDomain.next(this.f8492a);
        }

        @Override // com.google.common.collect.Cut
        BoundType l() {
            return BoundType.OPEN;
        }

        @Override // com.google.common.collect.Cut
        BoundType m() {
            return BoundType.CLOSED;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Cut
        Cut n(BoundType boundType, DiscreteDomain discreteDomain) {
            int i2 = AnonymousClass1.f8493a[boundType.ordinal()];
            if (i2 == 1) {
                Comparable next = discreteDomain.next(this.f8492a);
                return next == null ? Cut.c() : Cut.d(next);
            } else if (i2 == 2) {
                return this;
            } else {
                throw new AssertionError();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Cut
        Cut o(BoundType boundType, DiscreteDomain discreteDomain) {
            int i2 = AnonymousClass1.f8493a[boundType.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    Comparable next = discreteDomain.next(this.f8492a);
                    return next == null ? Cut.a() : Cut.d(next);
                }
                throw new AssertionError();
            }
            return this;
        }

        public String toString() {
            return "/" + this.f8492a + "\\";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class BelowAll extends Cut<Comparable<?>> {
        private static final BelowAll INSTANCE = new BelowAll();
        private static final long serialVersionUID = 0;

        private BelowAll() {
            super(null);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public int compareTo(Cut<Comparable<?>> cut) {
            return cut == this ? 0 : -1;
        }

        @Override // com.google.common.collect.Cut
        Cut e(DiscreteDomain discreteDomain) {
            try {
                return Cut.d(discreteDomain.minValue());
            } catch (NoSuchElementException unused) {
                return this;
            }
        }

        @Override // com.google.common.collect.Cut
        void f(StringBuilder sb) {
            sb.append("(-∞");
        }

        @Override // com.google.common.collect.Cut
        void g(StringBuilder sb) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        Comparable h() {
            throw new IllegalStateException("range unbounded on this side");
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return System.identityHashCode(this);
        }

        @Override // com.google.common.collect.Cut
        Comparable i(DiscreteDomain discreteDomain) {
            throw new AssertionError();
        }

        @Override // com.google.common.collect.Cut
        boolean j(Comparable comparable) {
            return true;
        }

        @Override // com.google.common.collect.Cut
        Comparable k(DiscreteDomain discreteDomain) {
            return discreteDomain.minValue();
        }

        @Override // com.google.common.collect.Cut
        BoundType l() {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut
        BoundType m() {
            throw new AssertionError("this statement should be unreachable");
        }

        @Override // com.google.common.collect.Cut
        Cut n(BoundType boundType, DiscreteDomain discreteDomain) {
            throw new IllegalStateException();
        }

        @Override // com.google.common.collect.Cut
        Cut o(BoundType boundType, DiscreteDomain discreteDomain) {
            throw new AssertionError("this statement should be unreachable");
        }

        public String toString() {
            return "-∞";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class BelowValue<C extends Comparable> extends Cut<C> {
        private static final long serialVersionUID = 0;

        BelowValue(Comparable comparable) {
            super((Comparable) Preconditions.checkNotNull(comparable));
        }

        @Override // com.google.common.collect.Cut, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return super.compareTo((Cut) ((Cut) obj));
        }

        @Override // com.google.common.collect.Cut
        void f(StringBuilder sb) {
            sb.append(AbstractJsonLexerKt.BEGIN_LIST);
            sb.append(this.f8492a);
        }

        @Override // com.google.common.collect.Cut
        void g(StringBuilder sb) {
            sb.append(this.f8492a);
            sb.append(')');
        }

        @Override // com.google.common.collect.Cut
        public int hashCode() {
            return this.f8492a.hashCode();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Cut
        Comparable i(DiscreteDomain discreteDomain) {
            return discreteDomain.previous(this.f8492a);
        }

        @Override // com.google.common.collect.Cut
        boolean j(Comparable comparable) {
            return Range.a(this.f8492a, comparable) <= 0;
        }

        @Override // com.google.common.collect.Cut
        Comparable k(DiscreteDomain discreteDomain) {
            return this.f8492a;
        }

        @Override // com.google.common.collect.Cut
        BoundType l() {
            return BoundType.CLOSED;
        }

        @Override // com.google.common.collect.Cut
        BoundType m() {
            return BoundType.OPEN;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Cut
        Cut n(BoundType boundType, DiscreteDomain discreteDomain) {
            int i2 = AnonymousClass1.f8493a[boundType.ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    Comparable previous = discreteDomain.previous(this.f8492a);
                    return previous == null ? Cut.c() : new AboveValue(previous);
                }
                throw new AssertionError();
            }
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Cut
        Cut o(BoundType boundType, DiscreteDomain discreteDomain) {
            int i2 = AnonymousClass1.f8493a[boundType.ordinal()];
            if (i2 == 1) {
                Comparable previous = discreteDomain.previous(this.f8492a);
                return previous == null ? Cut.a() : new AboveValue(previous);
            } else if (i2 == 2) {
                return this;
            } else {
                throw new AssertionError();
            }
        }

        public String toString() {
            return "\\" + this.f8492a + "/";
        }
    }

    Cut(@NullableDecl Comparable comparable) {
        this.f8492a = comparable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cut a() {
        return AboveAll.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cut b(Comparable comparable) {
        return new AboveValue(comparable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cut c() {
        return BelowAll.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Cut d(Comparable comparable) {
        return new BelowValue(comparable);
    }

    public int compareTo(Cut<C> cut) {
        if (cut == c()) {
            return 1;
        }
        if (cut == a()) {
            return -1;
        }
        int a2 = Range.a(this.f8492a, cut.f8492a);
        return a2 != 0 ? a2 : Booleans.compare(this instanceof AboveValue, cut instanceof AboveValue);
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((Cut) ((Cut) obj));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cut e(DiscreteDomain discreteDomain) {
        return this;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Cut) {
            try {
                return compareTo((Cut) ((Cut) obj)) == 0;
            } catch (ClassCastException unused) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void f(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void g(StringBuilder sb);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Comparable h() {
        return this.f8492a;
    }

    public abstract int hashCode();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Comparable i(DiscreteDomain discreteDomain);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean j(Comparable comparable);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Comparable k(DiscreteDomain discreteDomain);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract BoundType l();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract BoundType m();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Cut n(BoundType boundType, DiscreteDomain discreteDomain);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Cut o(BoundType boundType, DiscreteDomain discreteDomain);
}
