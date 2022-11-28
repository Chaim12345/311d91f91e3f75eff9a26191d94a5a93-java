package com.google.maps.model;

import com.google.maps.internal.StringJoin;
import java.io.Serializable;
/* loaded from: classes2.dex */
public class AddressComponent implements Serializable {
    private static final long serialVersionUID = 1;
    public String longName;
    public String shortName;
    public AddressComponentType[] types;

    public String toString() {
        StringBuilder sb = new StringBuilder("[AddressComponent: ");
        sb.append("\"");
        sb.append(this.longName);
        sb.append("\"");
        if (this.shortName != null) {
            sb.append(" (\"");
            sb.append(this.shortName);
            sb.append("\")");
        }
        sb.append(" (");
        sb.append(StringJoin.join(", ", this.types));
        sb.append(")");
        sb.append("]");
        return sb.toString();
    }
}
