package com.google.maps.model;

import java.io.Serializable;
/* loaded from: classes2.dex */
public class PlusCode implements Serializable {
    private static final long serialVersionUID = 1;
    public String compoundCode;
    public String globalCode;

    public String toString() {
        StringBuilder sb = new StringBuilder("[PlusCode: ");
        sb.append(this.globalCode);
        if (this.compoundCode != null) {
            sb.append(", compoundCode=");
            sb.append(this.compoundCode);
        }
        sb.append("]");
        return sb.toString();
    }
}
