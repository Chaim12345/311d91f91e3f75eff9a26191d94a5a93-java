package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.annotation.CheckForNull;
/* loaded from: classes2.dex */
final class zzau extends zzck {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ zzaw f6245a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzau(zzaw zzawVar) {
        this.f6245a = zzawVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzck
    final Map a() {
        return this.f6245a;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzck, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(@CheckForNull Object obj) {
        Set entrySet = this.f6245a.f6249a.entrySet();
        Objects.requireNonNull(entrySet);
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new zzav(this.f6245a);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzck, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(@CheckForNull Object obj) {
        if (contains(obj)) {
            Map.Entry entry = (Map.Entry) obj;
            entry.getClass();
            zzbe.k(this.f6245a.f6250b, entry.getKey());
            return true;
        }
        return false;
    }
}
