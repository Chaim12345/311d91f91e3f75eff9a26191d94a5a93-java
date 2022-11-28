package com.psa.mym.mycitroenconnect.model.secondary_user;

import java.util.ArrayList;
/* loaded from: classes3.dex */
public final class SecondaryUserResponse extends ArrayList<SecondaryUser> {
    public /* bridge */ boolean contains(SecondaryUser secondaryUser) {
        return super.contains((Object) secondaryUser);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof SecondaryUser) {
            return contains((SecondaryUser) obj);
        }
        return false;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    public /* bridge */ int indexOf(SecondaryUser secondaryUser) {
        return super.indexOf((Object) secondaryUser);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof SecondaryUser) {
            return indexOf((SecondaryUser) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(SecondaryUser secondaryUser) {
        return super.lastIndexOf((Object) secondaryUser);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof SecondaryUser) {
            return lastIndexOf((SecondaryUser) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ SecondaryUser remove(int i2) {
        return removeAt(i2);
    }

    public /* bridge */ boolean remove(SecondaryUser secondaryUser) {
        return super.remove((Object) secondaryUser);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof SecondaryUser) {
            return remove((SecondaryUser) obj);
        }
        return false;
    }

    public /* bridge */ SecondaryUser removeAt(int i2) {
        return (SecondaryUser) super.remove(i2);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }
}
