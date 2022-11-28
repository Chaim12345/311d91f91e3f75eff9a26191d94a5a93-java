package androidx.car.app.connection;

import androidx.lifecycle.LiveData;
/* loaded from: classes.dex */
final class AutomotiveCarConnectionTypeLiveData extends LiveData<Integer> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.lifecycle.LiveData
    public void onActive() {
        setValue(1);
    }
}
