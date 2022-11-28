package com.google.maps.android.data.kml;

import android.graphics.Color;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.data.Style;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import org.bouncycastle.i18n.TextBundle;
/* loaded from: classes2.dex */
public class KmlStyle extends Style {
    private static final int HSV_VALUES = 3;
    private static final int HUE_VALUE = 0;
    private static final int INITIAL_SCALE = 1;
    private String mIconUrl;
    private boolean mFill = true;
    private boolean mOutline = true;
    private String mStyleId = null;
    private final HashMap<String, String> mBalloonOptions = new HashMap<>();
    private final HashSet<String> mStylesSet = new HashSet<>();
    private double mScale = 1.0d;
    @VisibleForTesting

    /* renamed from: d  reason: collision with root package name */
    float f10323d = 0.0f;
    private boolean mIconRandomColorMode = false;
    private boolean mLineRandomColorMode = false;
    private boolean mPolyRandomColorMode = false;

    public static int computeRandomColor(int i2) {
        Random random = new Random();
        int red = Color.red(i2);
        int green = Color.green(i2);
        int blue = Color.blue(i2);
        if (red != 0) {
            red = random.nextInt(red);
        }
        if (blue != 0) {
            blue = random.nextInt(blue);
        }
        if (green != 0) {
            green = random.nextInt(green);
        }
        return Color.rgb(red, green, blue);
    }

    private static String convertColor(String str) {
        StringBuilder sb;
        String substring;
        String trim = str.trim();
        if (trim.length() > 6) {
            sb = new StringBuilder();
            sb.append(trim.substring(0, 2));
            sb.append(trim.substring(6, 8));
            sb.append(trim.substring(4, 6));
            substring = trim.substring(2, 4);
        } else {
            sb = new StringBuilder();
            sb.append(trim.substring(4, 6));
            sb.append(trim.substring(2, 4));
            substring = trim.substring(0, 2);
        }
        sb.append(substring);
        return sb.toString();
    }

    private static MarkerOptions createMarkerOptions(MarkerOptions markerOptions, boolean z, float f2) {
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.rotation(markerOptions.getRotation());
        markerOptions2.anchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        if (z) {
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(getHueValue(computeRandomColor((int) f2))));
        }
        markerOptions2.icon(markerOptions.getIcon());
        return markerOptions2;
    }

    private static PolygonOptions createPolygonOptions(PolygonOptions polygonOptions, boolean z, boolean z2) {
        float f2;
        PolygonOptions polygonOptions2 = new PolygonOptions();
        if (z) {
            polygonOptions2.fillColor(polygonOptions.getFillColor());
        }
        if (z2) {
            polygonOptions2.strokeColor(polygonOptions.getStrokeColor());
            f2 = polygonOptions.getStrokeWidth();
        } else {
            f2 = 0.0f;
        }
        polygonOptions2.strokeWidth(f2);
        polygonOptions2.clickable(polygonOptions.isClickable());
        return polygonOptions2;
    }

    private static PolylineOptions createPolylineOptions(PolylineOptions polylineOptions) {
        PolylineOptions polylineOptions2 = new PolylineOptions();
        polylineOptions2.color(polylineOptions.getColor());
        polylineOptions2.width(polylineOptions.getWidth());
        polylineOptions2.clickable(polylineOptions.isClickable());
        return polylineOptions2;
    }

    private static float getHueValue(int i2) {
        float[] fArr = new float[3];
        Color.colorToHSV(i2, fArr);
        return fArr[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String a() {
        return this.mStyleId;
    }

    boolean b() {
        return this.mIconRandomColorMode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(String str) {
        setPolygonFillColor(Color.parseColor("#" + convertColor(str)));
        this.mStylesSet.add("fillColor");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d(float f2) {
        setMarkerRotation(f2);
        this.mStylesSet.add("heading");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(float f2, float f3, String str, String str2) {
        setMarkerHotSpot(f2, f3, str, str2);
        this.mStylesSet.add("hotSpot");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(String str) {
        this.mIconRandomColorMode = str.equals("random");
        this.mStylesSet.add("iconColorMode");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void g(double d2) {
        this.mScale = d2;
        this.mStylesSet.add("iconScale");
    }

    public HashMap<String, String> getBalloonOptions() {
        return this.mBalloonOptions;
    }

    public double getIconScale() {
        return this.mScale;
    }

    public String getIconUrl() {
        return this.mIconUrl;
    }

    public MarkerOptions getMarkerOptions() {
        return createMarkerOptions(this.f10318a, b(), this.f10323d);
    }

    public PolygonOptions getPolygonOptions() {
        return createPolygonOptions(this.f10320c, this.mFill, this.mOutline);
    }

    public PolylineOptions getPolylineOptions() {
        return createPolylineOptions(this.f10319b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void h(String str) {
        this.mIconUrl = str;
        this.mStylesSet.add("iconUrl");
    }

    public boolean hasBalloonStyle() {
        return this.mBalloonOptions.size() > 0;
    }

    public boolean hasFill() {
        return this.mFill;
    }

    public boolean hasOutline() {
        return this.mOutline;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(String str) {
        this.mBalloonOptions.put(TextBundle.TEXT_ENTRY, str);
    }

    public boolean isLineRandomColorMode() {
        return this.mLineRandomColorMode;
    }

    public boolean isPolyRandomColorMode() {
        return this.mPolyRandomColorMode;
    }

    public boolean isStyleSet(String str) {
        return this.mStylesSet.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void j(String str) {
        this.mLineRandomColorMode = str.equals("random");
        this.mStylesSet.add("lineColorMode");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void k(String str) {
        float hueValue = getHueValue(Color.parseColor("#" + convertColor(str)));
        this.f10323d = hueValue;
        this.f10318a.icon(BitmapDescriptorFactory.defaultMarker(hueValue));
        this.mStylesSet.add("markerColor");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void l(boolean z) {
        this.mOutline = z;
        this.mStylesSet.add("outline");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void m(String str) {
        PolylineOptions polylineOptions = this.f10319b;
        polylineOptions.color(Color.parseColor("#" + convertColor(str)));
        PolygonOptions polygonOptions = this.f10320c;
        polygonOptions.strokeColor(Color.parseColor("#" + convertColor(str)));
        this.mStylesSet.add("outlineColor");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void n(String str) {
        this.mPolyRandomColorMode = str.equals("random");
        this.mStylesSet.add("polyColorMode");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void o(String str) {
        this.mStyleId = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void p(Float f2) {
        setLineStringWidth(f2.floatValue());
        setPolygonStrokeWidth(f2.floatValue());
        this.mStylesSet.add("width");
    }

    public void setFill(boolean z) {
        this.mFill = z;
    }

    public String toString() {
        return "Style{\n balloon options=" + this.mBalloonOptions + ",\n fill=" + this.mFill + ",\n outline=" + this.mOutline + ",\n icon url=" + this.mIconUrl + ",\n scale=" + this.mScale + ",\n style id=" + this.mStyleId + "\n}\n";
    }
}
