package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class TransitAgency implements Serializable {
    private static final long serialVersionUID = 1;
    public String name;
    public String phone;
    public String url;

    public String toString() {
        StringBuilder sb = new StringBuilder("[TransitAgency: ");
        sb.append(this.name);
        if (this.url != null) {
            sb.append(", url=");
            sb.append(this.url);
        }
        if (this.phone != null) {
            sb.append(", phone=");
            sb.append(this.phone);
        }
        sb.append("]");
        return sb.toString();
    }
}
