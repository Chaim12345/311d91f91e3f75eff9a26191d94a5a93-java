package com.google.maps.android.data.kml;

import com.google.android.gms.maps.model.GroundOverlay;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class KmlParser {
    private static final String CONTAINER_REGEX = "Folder|Document";
    private static final String GROUND_OVERLAY = "GroundOverlay";
    private static final String PLACEMARK = "Placemark";
    private static final String STYLE = "Style";
    private static final String STYLE_MAP = "StyleMap";
    private static final String UNSUPPORTED_REGEX = "altitude|altitudeModeGroup|altitudeMode|begin|bottomFov|cookie|displayName|displayMode|end|expires|extrude|flyToView|gridOrigin|httpQuery|leftFov|linkDescription|linkName|linkSnippet|listItemType|maxSnippetLines|maxSessionLength|message|minAltitude|minFadeExtent|minLodPixels|minRefreshPeriod|maxAltitude|maxFadeExtent|maxLodPixels|maxHeight|maxWidth|near|NetworkLink|NetworkLinkControl|overlayXY|range|refreshMode|refreshInterval|refreshVisibility|rightFov|roll|rotationXY|screenXY|shape|sourceHref|state|targetHref|tessellate|tileSize|topFov|viewBoundScale|viewFormat|viewRefreshMode|viewRefreshTime|when";
    private final XmlPullParser mParser;
    private final HashMap<KmlPlacemark, Object> mPlacemarks = new HashMap<>();
    private final ArrayList<KmlContainer> mContainers = new ArrayList<>();
    private final HashMap<String, KmlStyle> mStyles = new HashMap<>();
    private final HashMap<String, String> mStyleMaps = new HashMap<>();
    private final HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlays = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    public KmlParser(XmlPullParser xmlPullParser) {
        this.mParser = xmlPullParser;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void g(XmlPullParser xmlPullParser) {
        if (xmlPullParser.getEventType() != 2) {
            throw new IllegalStateException();
        }
        int i2 = 1;
        while (i2 != 0) {
            int next = xmlPullParser.next();
            if (next == 2) {
                i2++;
            } else if (next == 3) {
                i2--;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayList a() {
        return this.mContainers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap b() {
        return this.mGroundOverlays;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap c() {
        return this.mPlacemarks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap d() {
        return this.mStyleMaps;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HashMap e() {
        return this.mStyles;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        int eventType = this.mParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                if (this.mParser.getName().matches(UNSUPPORTED_REGEX)) {
                    g(this.mParser);
                }
                if (this.mParser.getName().matches(CONTAINER_REGEX)) {
                    this.mContainers.add(KmlContainerParser.a(this.mParser));
                }
                if (this.mParser.getName().equals(STYLE)) {
                    KmlStyle a2 = KmlStyleParser.a(this.mParser);
                    this.mStyles.put(a2.a(), a2);
                }
                if (this.mParser.getName().equals(STYLE_MAP)) {
                    this.mStyleMaps.putAll(KmlStyleParser.b(this.mParser));
                }
                if (this.mParser.getName().equals(PLACEMARK)) {
                    this.mPlacemarks.put(KmlFeatureParser.b(this.mParser), null);
                }
                if (this.mParser.getName().equals(GROUND_OVERLAY)) {
                    this.mGroundOverlays.put(KmlFeatureParser.a(this.mParser), null);
                }
            }
            eventType = this.mParser.next();
        }
        this.mStyles.put(null, new KmlStyle());
    }
}
