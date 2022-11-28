package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class GeneratorSequence$iterator$1 implements Iterator<T>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ GeneratorSequence f11152a;
    @Nullable
    private T nextItem;
    private int nextState = -2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeneratorSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.f11152a = generatorSequence;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void calcNext() {
        Function1 function1;
        T t2;
        Function0 function0;
        if (this.nextState == -2) {
            function0 = this.f11152a.getInitialValue;
            t2 = function0.invoke();
        } else {
            function1 = this.f11152a.getNextValue;
            T t3 = this.nextItem;
            Intrinsics.checkNotNull(t3);
            t2 = function1.invoke(t3);
        }
        this.nextItem = t2;
        this.nextState = t2 == 0 ? 0 : 1;
    }

    @Nullable
    public final T getNextItem() {
        return this.nextItem;
    }

    public final int getNextState() {
        return this.nextState;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState < 0) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    @NotNull
    public T next() {
        if (this.nextState < 0) {
            calcNext();
        }
        if (this.nextState != 0) {
            T t2 = this.nextItem;
            Objects.requireNonNull(t2, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
            this.nextState = -1;
            return t2;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setNextItem(@Nullable T t2) {
        this.nextItem = t2;
    }

    public final void setNextState(int i2) {
        this.nextState = i2;
    }
}
