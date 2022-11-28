package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class DropWhileSequence$iterator$1 implements Iterator<T>, KMappedMarker {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ DropWhileSequence f11149a;
    private int dropState;
    @NotNull
    private final Iterator<T> iterator;
    @Nullable
    private T nextItem;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DropWhileSequence$iterator$1(DropWhileSequence dropWhileSequence) {
        Sequence sequence;
        this.f11149a = dropWhileSequence;
        sequence = dropWhileSequence.sequence;
        this.iterator = sequence.iterator();
        this.dropState = -1;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
    private final void drop() {
        int i2;
        Function1 function1;
        while (true) {
            if (!this.iterator.hasNext()) {
                i2 = 0;
                break;
            }
            ?? next = this.iterator.next();
            function1 = this.f11149a.predicate;
            if (!((Boolean) function1.invoke(next)).booleanValue()) {
                this.nextItem = next;
                i2 = 1;
                break;
            }
        }
        this.dropState = i2;
    }

    public final int getDropState() {
        return this.dropState;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    @Nullable
    public final T getNextItem() {
        return this.nextItem;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.dropState == -1) {
            drop();
        }
        return this.dropState == 1 || this.iterator.hasNext();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [T, java.lang.Object] */
    @Override // java.util.Iterator
    public T next() {
        if (this.dropState == -1) {
            drop();
        }
        if (this.dropState == 1) {
            T t2 = this.nextItem;
            this.nextItem = null;
            this.dropState = 0;
            return t2;
        }
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setDropState(int i2) {
        this.dropState = i2;
    }

    public final void setNextItem(@Nullable T t2) {
        this.nextItem = t2;
    }
}
