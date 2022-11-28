package com.google.maps.android.data.kml;

import java.util.HashMap;
import org.bouncycastle.i18n.TextBundle;
import org.xmlpull.v1.XmlPullParser;
/* loaded from: classes2.dex */
class KmlStyleParser {
    private static final String COLOR_STYLE_COLOR = "color";
    private static final String COLOR_STYLE_MODE = "colorMode";
    private static final String ICON_STYLE_HEADING = "heading";
    private static final String ICON_STYLE_HOTSPOT = "hotSpot";
    private static final String ICON_STYLE_SCALE = "scale";
    private static final String ICON_STYLE_URL = "Icon";
    private static final String LINE_STYLE_WIDTH = "width";
    private static final String POLY_STYLE_FILL = "fill";
    private static final String POLY_STYLE_OUTLINE = "outline";
    private static final String STYLE_MAP_KEY = "key";
    private static final String STYLE_MAP_NORMAL_STYLE = "normal";
    private static final String STYLE_TAG = "styleUrl";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static KmlStyle a(XmlPullParser xmlPullParser) {
        KmlStyle kmlStyle = new KmlStyle();
        setStyleId(xmlPullParser.getAttributeValue(null, "id"), kmlStyle);
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("Style")) {
                return kmlStyle;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("IconStyle")) {
                    createIconStyle(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("LineStyle")) {
                    createLineStyle(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("PolyStyle")) {
                    createPolyStyle(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals("BalloonStyle")) {
                    createBalloonStyle(xmlPullParser, kmlStyle);
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static HashMap b(XmlPullParser xmlPullParser) {
        HashMap hashMap = new HashMap();
        String str = "#" + xmlPullParser.getAttributeValue(null, "id");
        int eventType = xmlPullParser.getEventType();
        boolean z = false;
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("StyleMap")) {
                return hashMap;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals(STYLE_MAP_KEY) && xmlPullParser.nextText().equals(STYLE_MAP_NORMAL_STYLE)) {
                    z = true;
                } else if (xmlPullParser.getName().equals(STYLE_TAG) && z) {
                    hashMap.put(str, xmlPullParser.nextText());
                    z = false;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void createBalloonStyle(XmlPullParser xmlPullParser, KmlStyle kmlStyle) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("BalloonStyle")) {
                return;
            }
            if (eventType == 2 && xmlPullParser.getName().equals(TextBundle.TEXT_ENTRY)) {
                kmlStyle.i(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void createIconStyle(XmlPullParser xmlPullParser, KmlStyle kmlStyle) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("IconStyle")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals(ICON_STYLE_HEADING)) {
                    kmlStyle.d(Float.parseFloat(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(ICON_STYLE_URL)) {
                    setIconUrl(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals(ICON_STYLE_HOTSPOT)) {
                    setIconHotSpot(xmlPullParser, kmlStyle);
                } else if (xmlPullParser.getName().equals(ICON_STYLE_SCALE)) {
                    kmlStyle.g(Double.parseDouble(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals("color")) {
                    kmlStyle.k(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(COLOR_STYLE_MODE)) {
                    kmlStyle.f(xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void createLineStyle(XmlPullParser xmlPullParser, KmlStyle kmlStyle) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("LineStyle")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("color")) {
                    kmlStyle.m(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(LINE_STYLE_WIDTH)) {
                    kmlStyle.p(Float.valueOf(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(COLOR_STYLE_MODE)) {
                    kmlStyle.j(xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void createPolyStyle(XmlPullParser xmlPullParser, KmlStyle kmlStyle) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals("PolyStyle")) {
                return;
            }
            if (eventType == 2) {
                if (xmlPullParser.getName().equals("color")) {
                    kmlStyle.c(xmlPullParser.nextText());
                } else if (xmlPullParser.getName().equals(POLY_STYLE_OUTLINE)) {
                    kmlStyle.l(KmlBoolean.parseBoolean(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(POLY_STYLE_FILL)) {
                    kmlStyle.setFill(KmlBoolean.parseBoolean(xmlPullParser.nextText()));
                } else if (xmlPullParser.getName().equals(COLOR_STYLE_MODE)) {
                    kmlStyle.n(xmlPullParser.nextText());
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void setIconHotSpot(XmlPullParser xmlPullParser, KmlStyle kmlStyle) {
        if (xmlPullParser.isEmptyElementTag()) {
            return;
        }
        kmlStyle.e(Float.parseFloat(xmlPullParser.getAttributeValue(null, "x")), Float.parseFloat(xmlPullParser.getAttributeValue(null, "y")), xmlPullParser.getAttributeValue(null, "xunits"), xmlPullParser.getAttributeValue(null, "yunits"));
    }

    private static void setIconUrl(XmlPullParser xmlPullParser, KmlStyle kmlStyle) {
        int eventType = xmlPullParser.getEventType();
        while (true) {
            if (eventType == 3 && xmlPullParser.getName().equals(ICON_STYLE_URL)) {
                return;
            }
            if (eventType == 2 && xmlPullParser.getName().equals("href")) {
                kmlStyle.h(xmlPullParser.nextText());
            }
            eventType = xmlPullParser.next();
        }
    }

    private static void setStyleId(String str, KmlStyle kmlStyle) {
        if (str != null) {
            kmlStyle.o("#" + str);
        }
    }
}
