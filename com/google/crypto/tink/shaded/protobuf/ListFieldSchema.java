package com.google.crypto.tink.shaded.protobuf;

import com.google.crypto.tink.shaded.protobuf.Internal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class ListFieldSchema {
    private static final ListFieldSchema FULL_INSTANCE = new ListFieldSchemaFull();
    private static final ListFieldSchema LITE_INSTANCE = new ListFieldSchemaLite();

    /* loaded from: classes2.dex */
    private static final class ListFieldSchemaFull extends ListFieldSchema {
        private static final Class<?> UNMODIFIABLE_LIST_CLASS = Collections.unmodifiableList(Collections.emptyList()).getClass();

        private ListFieldSchemaFull() {
            super();
        }

        static List f(Object obj, long j2) {
            return (List) UnsafeUtil.u(obj, j2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private static <L> List<L> mutableListAt(Object obj, long j2, int i2) {
            List<L> mutableCopyWithCapacity;
            LazyStringArrayList lazyStringArrayList;
            List<L> f2 = f(obj, j2);
            if (!f2.isEmpty()) {
                if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(f2.getClass())) {
                    ArrayList arrayList = new ArrayList(f2.size() + i2);
                    arrayList.addAll(f2);
                    lazyStringArrayList = arrayList;
                } else if (f2 instanceof UnmodifiableLazyStringList) {
                    LazyStringArrayList lazyStringArrayList2 = new LazyStringArrayList(f2.size() + i2);
                    lazyStringArrayList2.addAll((UnmodifiableLazyStringList) f2);
                    lazyStringArrayList = lazyStringArrayList2;
                } else if (!(f2 instanceof PrimitiveNonBoxingCollection) || !(f2 instanceof Internal.ProtobufList)) {
                    return f2;
                } else {
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) f2;
                    if (protobufList.isModifiable()) {
                        return f2;
                    }
                    mutableCopyWithCapacity = protobufList.mutableCopyWithCapacity(f2.size() + i2);
                }
                UnsafeUtil.G(obj, j2, lazyStringArrayList);
                return lazyStringArrayList;
            }
            mutableCopyWithCapacity = f2 instanceof LazyStringList ? new LazyStringArrayList(i2) : ((f2 instanceof PrimitiveNonBoxingCollection) && (f2 instanceof Internal.ProtobufList)) ? ((Internal.ProtobufList) f2).mutableCopyWithCapacity(i2) : new ArrayList<>(i2);
            UnsafeUtil.G(obj, j2, mutableCopyWithCapacity);
            return mutableCopyWithCapacity;
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void c(Object obj, long j2) {
            Object unmodifiableList;
            List list = (List) UnsafeUtil.u(obj, j2);
            if (list instanceof LazyStringList) {
                unmodifiableList = ((LazyStringList) list).getUnmodifiableView();
            } else if (UNMODIFIABLE_LIST_CLASS.isAssignableFrom(list.getClass())) {
                return;
            } else {
                if ((list instanceof PrimitiveNonBoxingCollection) && (list instanceof Internal.ProtobufList)) {
                    Internal.ProtobufList protobufList = (Internal.ProtobufList) list;
                    if (protobufList.isModifiable()) {
                        protobufList.makeImmutable();
                        return;
                    }
                    return;
                }
                unmodifiableList = Collections.unmodifiableList(list);
            }
            UnsafeUtil.G(obj, j2, unmodifiableList);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void d(Object obj, Object obj2, long j2) {
            List f2 = f(obj2, j2);
            List mutableListAt = mutableListAt(obj, j2, f2.size());
            int size = mutableListAt.size();
            int size2 = f2.size();
            if (size > 0 && size2 > 0) {
                mutableListAt.addAll(f2);
            }
            if (size > 0) {
                f2 = mutableListAt;
            }
            UnsafeUtil.G(obj, j2, f2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        List e(Object obj, long j2) {
            return mutableListAt(obj, j2, 10);
        }
    }

    /* loaded from: classes2.dex */
    private static final class ListFieldSchemaLite extends ListFieldSchema {
        private ListFieldSchemaLite() {
            super();
        }

        static Internal.ProtobufList f(Object obj, long j2) {
            return (Internal.ProtobufList) UnsafeUtil.u(obj, j2);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void c(Object obj, long j2) {
            f(obj, j2).makeImmutable();
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        void d(Object obj, Object obj2, long j2) {
            Internal.ProtobufList f2 = f(obj, j2);
            Internal.ProtobufList f3 = f(obj2, j2);
            int size = f2.size();
            int size2 = f3.size();
            if (size > 0 && size2 > 0) {
                if (!f2.isModifiable()) {
                    f2 = f2.mutableCopyWithCapacity(size2 + size);
                }
                f2.addAll(f3);
            }
            if (size > 0) {
                f3 = f2;
            }
            UnsafeUtil.G(obj, j2, f3);
        }

        @Override // com.google.crypto.tink.shaded.protobuf.ListFieldSchema
        List e(Object obj, long j2) {
            Internal.ProtobufList f2 = f(obj, j2);
            if (f2.isModifiable()) {
                return f2;
            }
            int size = f2.size();
            Internal.ProtobufList mutableCopyWithCapacity = f2.mutableCopyWithCapacity(size == 0 ? 10 : size * 2);
            UnsafeUtil.G(obj, j2, mutableCopyWithCapacity);
            return mutableCopyWithCapacity;
        }
    }

    private ListFieldSchema() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListFieldSchema a() {
        return FULL_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ListFieldSchema b() {
        return LITE_INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void c(Object obj, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void d(Object obj, Object obj2, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract List e(Object obj, long j2);
}
