package kotlinx.coroutines.flow;
/* loaded from: classes3.dex */
public interface MutableStateFlow<T> extends StateFlow<T>, MutableSharedFlow<T> {
    boolean compareAndSet(T t2, T t3);

    @Override // kotlinx.coroutines.flow.StateFlow
    T getValue();

    void setValue(T t2);
}
