package org.slf4j.helpers;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.http.message.TokenParser;
import org.slf4j.Marker;
/* loaded from: classes4.dex */
public class BasicMarker implements Marker {
    private static String CLOSE = " ]";
    private static String OPEN = "[ ";
    private static String SEP = ", ";
    private static final long serialVersionUID = -2849567615646933777L;
    private final String name;
    private List<Marker> referenceList = new CopyOnWriteArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public BasicMarker(String str) {
        if (str == null) {
            throw new IllegalArgumentException("A marker name cannot be null");
        }
        this.name = str;
    }

    @Override // org.slf4j.Marker
    public void add(Marker marker) {
        if (marker == null) {
            throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
        }
        if (contains(marker) || marker.contains(this)) {
            return;
        }
        this.referenceList.add(marker);
    }

    @Override // org.slf4j.Marker
    public boolean contains(String str) {
        if (str != null) {
            if (this.name.equals(str)) {
                return true;
            }
            if (hasReferences()) {
                for (Marker marker : this.referenceList) {
                    if (marker.contains(str)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("Other cannot be null");
    }

    @Override // org.slf4j.Marker
    public boolean contains(Marker marker) {
        if (marker != null) {
            if (equals(marker)) {
                return true;
            }
            if (hasReferences()) {
                for (Marker marker2 : this.referenceList) {
                    if (marker2.contains(marker)) {
                        return true;
                    }
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("Other cannot be null");
    }

    @Override // org.slf4j.Marker
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof Marker)) {
            return this.name.equals(((Marker) obj).getName());
        }
        return false;
    }

    @Override // org.slf4j.Marker
    public String getName() {
        return this.name;
    }

    @Override // org.slf4j.Marker
    public boolean hasChildren() {
        return hasReferences();
    }

    @Override // org.slf4j.Marker
    public boolean hasReferences() {
        return this.referenceList.size() > 0;
    }

    @Override // org.slf4j.Marker
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override // org.slf4j.Marker
    public Iterator<Marker> iterator() {
        return this.referenceList.iterator();
    }

    @Override // org.slf4j.Marker
    public boolean remove(Marker marker) {
        return this.referenceList.remove(marker);
    }

    public String toString() {
        if (!hasReferences()) {
            return getName();
        }
        Iterator<Marker> it = iterator();
        StringBuilder sb = new StringBuilder(getName());
        sb.append(TokenParser.SP);
        String str = OPEN;
        while (true) {
            sb.append(str);
            while (it.hasNext()) {
                sb.append(it.next().getName());
                if (it.hasNext()) {
                    break;
                }
            }
            sb.append(CLOSE);
            return sb.toString();
            str = SEP;
        }
    }
}
