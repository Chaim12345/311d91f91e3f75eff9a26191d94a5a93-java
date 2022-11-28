package org.bouncycastle.asn1.eac;

import java.util.Hashtable;
/* loaded from: classes3.dex */
public class BidirectionalMap extends Hashtable {
    private static final long serialVersionUID = -7457289971962812909L;

    /* renamed from: a  reason: collision with root package name */
    Hashtable f12775a = new Hashtable();

    public Object getReverse(Object obj) {
        return this.f12775a.get(obj);
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object put(Object obj, Object obj2) {
        this.f12775a.put(obj2, obj);
        return super.put(obj, obj2);
    }
}
