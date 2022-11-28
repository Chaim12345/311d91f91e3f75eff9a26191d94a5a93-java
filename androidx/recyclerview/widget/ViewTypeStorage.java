package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
interface ViewTypeStorage {

    /* loaded from: classes.dex */
    public static class IsolatedViewTypeStorage implements ViewTypeStorage {

        /* renamed from: a  reason: collision with root package name */
        SparseArray f3831a = new SparseArray();

        /* renamed from: b  reason: collision with root package name */
        int f3832b = 0;

        /* loaded from: classes.dex */
        class WrapperViewTypeLookup implements ViewTypeLookup {

            /* renamed from: a  reason: collision with root package name */
            final NestedAdapterWrapper f3833a;
            private SparseIntArray mLocalToGlobalMapping = new SparseIntArray(1);
            private SparseIntArray mGlobalToLocalMapping = new SparseIntArray(1);

            WrapperViewTypeLookup(NestedAdapterWrapper nestedAdapterWrapper) {
                this.f3833a = nestedAdapterWrapper;
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public void dispose() {
                IsolatedViewTypeStorage.this.b(this.f3833a);
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int globalToLocal(int i2) {
                int indexOfKey = this.mGlobalToLocalMapping.indexOfKey(i2);
                if (indexOfKey >= 0) {
                    return this.mGlobalToLocalMapping.valueAt(indexOfKey);
                }
                throw new IllegalStateException("requested global type " + i2 + " does not belong to the adapter:" + this.f3833a.adapter);
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int localToGlobal(int i2) {
                int indexOfKey = this.mLocalToGlobalMapping.indexOfKey(i2);
                if (indexOfKey > -1) {
                    return this.mLocalToGlobalMapping.valueAt(indexOfKey);
                }
                int a2 = IsolatedViewTypeStorage.this.a(this.f3833a);
                this.mLocalToGlobalMapping.put(i2, a2);
                this.mGlobalToLocalMapping.put(a2, i2);
                return a2;
            }
        }

        int a(NestedAdapterWrapper nestedAdapterWrapper) {
            int i2 = this.f3832b;
            this.f3832b = i2 + 1;
            this.f3831a.put(i2, nestedAdapterWrapper);
            return i2;
        }

        void b(@NonNull NestedAdapterWrapper nestedAdapterWrapper) {
            for (int size = this.f3831a.size() - 1; size >= 0; size--) {
                if (((NestedAdapterWrapper) this.f3831a.valueAt(size)) == nestedAdapterWrapper) {
                    this.f3831a.removeAt(size);
                }
            }
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public ViewTypeLookup createViewTypeWrapper(@NonNull NestedAdapterWrapper nestedAdapterWrapper) {
            return new WrapperViewTypeLookup(nestedAdapterWrapper);
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public NestedAdapterWrapper getWrapperForGlobalType(int i2) {
            NestedAdapterWrapper nestedAdapterWrapper = (NestedAdapterWrapper) this.f3831a.get(i2);
            if (nestedAdapterWrapper != null) {
                return nestedAdapterWrapper;
            }
            throw new IllegalArgumentException("Cannot find the wrapper for global view type " + i2);
        }
    }

    /* loaded from: classes.dex */
    public static class SharedIdRangeViewTypeStorage implements ViewTypeStorage {

        /* renamed from: a  reason: collision with root package name */
        SparseArray f3835a = new SparseArray();

        /* loaded from: classes.dex */
        class WrapperViewTypeLookup implements ViewTypeLookup {

            /* renamed from: a  reason: collision with root package name */
            final NestedAdapterWrapper f3836a;

            WrapperViewTypeLookup(NestedAdapterWrapper nestedAdapterWrapper) {
                this.f3836a = nestedAdapterWrapper;
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public void dispose() {
                SharedIdRangeViewTypeStorage.this.a(this.f3836a);
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int globalToLocal(int i2) {
                return i2;
            }

            @Override // androidx.recyclerview.widget.ViewTypeStorage.ViewTypeLookup
            public int localToGlobal(int i2) {
                List list = (List) SharedIdRangeViewTypeStorage.this.f3835a.get(i2);
                if (list == null) {
                    list = new ArrayList();
                    SharedIdRangeViewTypeStorage.this.f3835a.put(i2, list);
                }
                if (!list.contains(this.f3836a)) {
                    list.add(this.f3836a);
                }
                return i2;
            }
        }

        void a(@NonNull NestedAdapterWrapper nestedAdapterWrapper) {
            for (int size = this.f3835a.size() - 1; size >= 0; size--) {
                List list = (List) this.f3835a.valueAt(size);
                if (list.remove(nestedAdapterWrapper) && list.isEmpty()) {
                    this.f3835a.removeAt(size);
                }
            }
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public ViewTypeLookup createViewTypeWrapper(@NonNull NestedAdapterWrapper nestedAdapterWrapper) {
            return new WrapperViewTypeLookup(nestedAdapterWrapper);
        }

        @Override // androidx.recyclerview.widget.ViewTypeStorage
        @NonNull
        public NestedAdapterWrapper getWrapperForGlobalType(int i2) {
            List list = (List) this.f3835a.get(i2);
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException("Cannot find the wrapper for global view type " + i2);
            }
            return (NestedAdapterWrapper) list.get(0);
        }
    }

    /* loaded from: classes.dex */
    public interface ViewTypeLookup {
        void dispose();

        int globalToLocal(int i2);

        int localToGlobal(int i2);
    }

    @NonNull
    ViewTypeLookup createViewTypeWrapper(@NonNull NestedAdapterWrapper nestedAdapterWrapper);

    @NonNull
    NestedAdapterWrapper getWrapperForGlobalType(int i2);
}
