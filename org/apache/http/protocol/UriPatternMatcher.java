package org.apache.http.protocol;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.apache.http.annotation.Contract;
import org.apache.http.annotation.ThreadingBehavior;
import org.apache.http.util.Args;
import org.slf4j.Marker;
@Contract(threading = ThreadingBehavior.SAFE)
/* loaded from: classes3.dex */
public class UriPatternMatcher<T> {
    private final Map<String, T> map = new LinkedHashMap();

    public synchronized Set<Map.Entry<String, T>> entrySet() {
        return new HashSet(this.map.entrySet());
    }

    @Deprecated
    public synchronized Map<String, T> getObjects() {
        return this.map;
    }

    public synchronized T lookup(String str) {
        T t2;
        Args.notNull(str, "Request path");
        t2 = this.map.get(str);
        if (t2 == null) {
            String str2 = null;
            for (String str3 : this.map.keySet()) {
                if (matchUriRequestPattern(str3, str) && (str2 == null || str2.length() < str3.length() || (str2.length() == str3.length() && str3.endsWith(Marker.ANY_MARKER)))) {
                    t2 = this.map.get(str3);
                    str2 = str3;
                }
            }
        }
        return t2;
    }

    protected boolean matchUriRequestPattern(String str, String str2) {
        if (str.equals(Marker.ANY_MARKER)) {
            return true;
        }
        if (str.endsWith(Marker.ANY_MARKER) && str2.startsWith(str.substring(0, str.length() - 1))) {
            return true;
        }
        return str.startsWith(Marker.ANY_MARKER) && str2.endsWith(str.substring(1, str.length()));
    }

    public synchronized void register(String str, T t2) {
        Args.notNull(str, "URI request pattern");
        this.map.put(str, t2);
    }

    @Deprecated
    public synchronized void setHandlers(Map<String, T> map) {
        Args.notNull(map, "Map of handlers");
        this.map.clear();
        this.map.putAll(map);
    }

    @Deprecated
    public synchronized void setObjects(Map<String, T> map) {
        Args.notNull(map, "Map of handlers");
        this.map.clear();
        this.map.putAll(map);
    }

    public String toString() {
        return this.map.toString();
    }

    public synchronized void unregister(String str) {
        if (str == null) {
            return;
        }
        this.map.remove(str);
    }
}
