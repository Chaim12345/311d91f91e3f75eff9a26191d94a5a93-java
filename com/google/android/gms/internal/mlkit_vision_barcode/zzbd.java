package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class zzbd extends zzbb implements List {

    /* renamed from: f  reason: collision with root package name */
    final /* synthetic */ zzbe f6264f;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbd(zzbe zzbeVar, Object obj, @CheckForNull List list, zzbb zzbbVar) {
        super(zzbeVar, obj, list, zzbbVar);
        this.f6264f = zzbeVar;
    }

    @Override // java.util.List
    public final void add(int i2, Object obj) {
        zzb();
        boolean isEmpty = this.f6259b.isEmpty();
        ((List) this.f6259b).add(i2, obj);
        zzbe.e(this.f6264f);
        if (isEmpty) {
            a();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i2, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.f6259b).addAll(i2, collection);
        if (addAll) {
            zzbe.g(this.f6264f, this.f6259b.size() - size);
            if (size == 0) {
                a();
                return true;
            }
            return addAll;
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        zzb();
        return ((List) this.f6259b).get(i2);
    }

    @Override // java.util.List
    public final int indexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.f6259b).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.f6259b).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        zzb();
        return new zzbc(this);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i2) {
        zzb();
        return new zzbc(this, i2);
    }

    @Override // java.util.List
    public final Object remove(int i2) {
        zzb();
        Object remove = ((List) this.f6259b).remove(i2);
        zzbe.f(this.f6264f);
        b();
        return remove;
    }

    @Override // java.util.List
    public final Object set(int i2, Object obj) {
        zzb();
        return ((List) this.f6259b).set(i2, obj);
    }

    @Override // java.util.List
    public final List subList(int i2, int i3) {
        zzb();
        zzbe zzbeVar = this.f6264f;
        Object obj = this.f6258a;
        List subList = ((List) this.f6259b).subList(i2, i3);
        zzbb zzbbVar = this.f6260c;
        if (zzbbVar == null) {
            zzbbVar = this;
        }
        return zzbeVar.i(obj, subList, zzbbVar);
    }
}
