package com.google.maps.android.data;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
/* loaded from: classes2.dex */
public class Feature extends Observable {

    /* renamed from: a  reason: collision with root package name */
    protected String f10307a;
    private Geometry mGeometry;
    private final Map<String, String> mProperties;

    public Feature(Geometry geometry, String str, Map<String, String> map) {
        this.mGeometry = geometry;
        this.f10307a = str;
        if (map == null) {
            this.mProperties = new HashMap();
        } else {
            this.mProperties = map;
        }
    }

    public Geometry getGeometry() {
        return this.mGeometry;
    }

    public String getId() {
        return this.f10307a;
    }

    public Iterable getProperties() {
        return this.mProperties.entrySet();
    }

    public String getProperty(String str) {
        return this.mProperties.get(str);
    }

    public Iterable<String> getPropertyKeys() {
        return this.mProperties.keySet();
    }

    public boolean hasGeometry() {
        return this.mGeometry != null;
    }

    public boolean hasProperties() {
        return this.mProperties.size() > 0;
    }

    public boolean hasProperty(String str) {
        return this.mProperties.containsKey(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String removeProperty(String str) {
        return this.mProperties.remove(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setGeometry(Geometry geometry) {
        this.mGeometry = geometry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String setProperty(String str, String str2) {
        return this.mProperties.put(str, str2);
    }
}
