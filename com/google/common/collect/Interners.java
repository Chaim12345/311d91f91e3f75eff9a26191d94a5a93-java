package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.MapMakerInternalMap;
@Beta
@GwtIncompatible
/* loaded from: classes2.dex */
public final class Interners {

    /* loaded from: classes2.dex */
    public static class InternerBuilder {
        private final MapMaker mapMaker;
        private boolean strong;

        private InternerBuilder() {
            this.mapMaker = new MapMaker();
            this.strong = true;
        }

        public <E> Interner<E> build() {
            if (!this.strong) {
                this.mapMaker.weakKeys();
            }
            return new InternerImpl(this.mapMaker);
        }

        public InternerBuilder concurrencyLevel(int i2) {
            this.mapMaker.concurrencyLevel(i2);
            return this;
        }

        public InternerBuilder strong() {
            this.strong = true;
            return this;
        }

        @GwtIncompatible("java.lang.ref.WeakReference")
        public InternerBuilder weak() {
            this.strong = false;
            return this;
        }
    }

    /* loaded from: classes2.dex */
    private static class InternerFunction<E> implements Function<E, E> {
        private final Interner<E> interner;

        public InternerFunction(Interner<E> interner) {
            this.interner = interner;
        }

        @Override // com.google.common.base.Function
        public E apply(E e2) {
            return this.interner.intern(e2);
        }

        @Override // com.google.common.base.Function
        public boolean equals(Object obj) {
            if (obj instanceof InternerFunction) {
                return this.interner.equals(((InternerFunction) obj).interner);
            }
            return false;
        }

        public int hashCode() {
            return this.interner.hashCode();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static final class InternerImpl<E> implements Interner<E> {
        @VisibleForTesting

        /* renamed from: a  reason: collision with root package name */
        final MapMakerInternalMap f8628a;

        private InternerImpl(MapMaker mapMaker) {
            this.f8628a = MapMakerInternalMap.d(mapMaker.f(Equivalence.equals()));
        }

        @Override // com.google.common.collect.Interner
        public E intern(E e2) {
            E e3;
            do {
                MapMakerInternalMap.InternalEntry e4 = this.f8628a.e(e2);
                if (e4 != null && (e3 = (E) e4.getKey()) != null) {
                    return e3;
                }
            } while (((MapMaker.Dummy) this.f8628a.putIfAbsent(e2, MapMaker.Dummy.VALUE)) != null);
            return e2;
        }
    }

    private Interners() {
    }

    public static <E> Function<E, E> asFunction(Interner<E> interner) {
        return new InternerFunction((Interner) Preconditions.checkNotNull(interner));
    }

    public static InternerBuilder newBuilder() {
        return new InternerBuilder();
    }

    public static <E> Interner<E> newStrongInterner() {
        return newBuilder().strong().build();
    }

    @GwtIncompatible("java.lang.ref.WeakReference")
    public static <E> Interner<E> newWeakInterner() {
        return newBuilder().weak().build();
    }
}
