package com.google.maps.model;

import com.google.maps.internal.StringJoin;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class Size implements StringJoin.UrlValue, Serializable {
    private static final long serialVersionUID = 1;
    public int height;
    public int width;

    public Size() {
    }

    public Size(int i2, int i3) {
        this.width = i2;
        this.height = i3;
    }

    public String toString() {
        return toUrlValue();
    }

    @Override // com.google.maps.internal.StringJoin.UrlValue
    public String toUrlValue() {
        return String.format("%dx%d", Integer.valueOf(this.width), Integer.valueOf(this.height));
    }
}
