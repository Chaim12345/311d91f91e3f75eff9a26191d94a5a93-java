package org.slf4j;

import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;
/* loaded from: classes4.dex */
public class MarkerFactory {

    /* renamed from: a  reason: collision with root package name */
    static IMarkerFactory f15228a;

    static {
        try {
            f15228a = bwCompatibleGetMarkerFactoryFromBinder();
        } catch (Exception e2) {
            Util.report("Unexpected failure while binding MarkerFactory", e2);
        } catch (NoClassDefFoundError unused) {
            f15228a = new BasicMarkerFactory();
        }
    }

    private MarkerFactory() {
    }

    private static IMarkerFactory bwCompatibleGetMarkerFactoryFromBinder() {
        try {
            return StaticMarkerBinder.getSingleton().getMarkerFactory();
        } catch (NoSuchMethodError unused) {
            return StaticMarkerBinder.SINGLETON.getMarkerFactory();
        }
    }

    public static Marker getDetachedMarker(String str) {
        return f15228a.getDetachedMarker(str);
    }

    public static IMarkerFactory getIMarkerFactory() {
        return f15228a;
    }

    public static Marker getMarker(String str) {
        return f15228a.getMarker(str);
    }
}
