package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class Photo implements Serializable {
    private static final long serialVersionUID = 1;
    public int height;
    public String[] htmlAttributions;
    public String photoReference;
    public int width;

    public String toString() {
        String format = String.format("[Photo %s (%d x %d)", this.photoReference, Integer.valueOf(this.width), Integer.valueOf(this.height));
        String[] strArr = this.htmlAttributions;
        if (strArr != null && strArr.length > 0) {
            format = format + " " + this.htmlAttributions.length + " attributions";
        }
        return format + "]";
    }
}
