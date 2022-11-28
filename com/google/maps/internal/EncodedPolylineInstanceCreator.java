package com.google.maps.internal;

import com.google.gson.InstanceCreator;
import com.google.maps.model.EncodedPolyline;
import java.lang.reflect.Type;
/* loaded from: classes2.dex */
public class EncodedPolylineInstanceCreator implements InstanceCreator<EncodedPolyline> {
    private String points;

    public EncodedPolylineInstanceCreator(String str) {
        this.points = str;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.InstanceCreator
    public EncodedPolyline createInstance(Type type) {
        return new EncodedPolyline(this.points);
    }
}
