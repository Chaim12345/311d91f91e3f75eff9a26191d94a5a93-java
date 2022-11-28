package com.google.maps;

import com.google.maps.ImageResult;
import com.google.maps.internal.ApiConfig;
import com.google.maps.internal.StringJoin;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes2.dex */
public class StaticMapsRequest extends PendingResultBase<ImageResult, StaticMapsRequest, ImageResult.Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10281a = new ApiConfig("/maps/api/staticmap");

    /* loaded from: classes2.dex */
    public enum ImageFormat implements StringJoin.UrlValue {
        png("png"),
        png8("png8"),
        png32("png32"),
        gif("gif"),
        jpg("jpg"),
        jpgBaseline("jpg-baseline");
        
        private final String format;

        ImageFormat(String str) {
            this.format = str;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return this.format;
        }
    }

    /* loaded from: classes2.dex */
    public static class Markers implements StringJoin.UrlValue {
        private static final Pattern labelPattern = Pattern.compile("^[A-Z0-9]$");
        private CustomIconAnchor anchorPoint;
        private String color;
        private String customIconURL;
        private String label;
        private final List<String> locations = new ArrayList();
        private Integer scale;
        private MarkersSize size;

        /* loaded from: classes2.dex */
        public enum CustomIconAnchor implements StringJoin.UrlValue {
            top,
            bottom,
            left,
            right,
            center,
            topleft,
            topright,
            bottomleft,
            bottomright;

            @Override // com.google.maps.internal.StringJoin.UrlValue
            public String toUrlValue() {
                return name();
            }
        }

        /* loaded from: classes2.dex */
        public enum MarkersSize implements StringJoin.UrlValue {
            tiny,
            mid,
            small,
            normal;

            @Override // com.google.maps.internal.StringJoin.UrlValue
            public String toUrlValue() {
                return name();
            }
        }

        public void addLocation(LatLng latLng) {
            this.locations.add(latLng.toUrlValue());
        }

        public void addLocation(String str) {
            this.locations.add(str);
        }

        public void color(String str) {
            this.color = str;
        }

        public void customIcon(String str, CustomIconAnchor customIconAnchor) {
            this.customIconURL = str;
            this.anchorPoint = customIconAnchor;
        }

        public void customIcon(String str, CustomIconAnchor customIconAnchor, int i2) {
            this.customIconURL = str;
            this.anchorPoint = customIconAnchor;
            this.scale = Integer.valueOf(i2);
        }

        public void label(String str) {
            if (labelPattern.matcher(str).matches()) {
                this.label = str;
                return;
            }
            throw new IllegalArgumentException("Label '" + str + "' doesn't match acceptable label pattern.");
        }

        public void size(MarkersSize markersSize) {
            this.size = markersSize;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            ArrayList arrayList = new ArrayList();
            if (this.customIconURL != null) {
                arrayList.add("icon:" + this.customIconURL);
            }
            if (this.anchorPoint != null) {
                arrayList.add("anchor:" + this.anchorPoint.toUrlValue());
            }
            if (this.scale != null) {
                arrayList.add("scale:" + this.scale);
            }
            MarkersSize markersSize = this.size;
            if (markersSize != null && markersSize != MarkersSize.normal) {
                arrayList.add("size:" + this.size.toUrlValue());
            }
            if (this.color != null) {
                arrayList.add("color:" + this.color);
            }
            if (this.label != null) {
                arrayList.add("label:" + this.label);
            }
            arrayList.addAll(this.locations);
            return StringJoin.join('|', (String[]) arrayList.toArray(new String[arrayList.size()]));
        }
    }

    /* loaded from: classes2.dex */
    public static class Path implements StringJoin.UrlValue {
        private String color;
        private String fillcolor;
        private boolean geodesic;
        private final List<String> points = new ArrayList();
        private int weight;

        public void addPoint(LatLng latLng) {
            this.points.add(latLng.toUrlValue());
        }

        public void addPoint(String str) {
            this.points.add(str);
        }

        public void color(String str) {
            this.color = str;
        }

        public void fillcolor(String str) {
            this.fillcolor = str;
        }

        public void geodesic(boolean z) {
            this.geodesic = z;
        }

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            ArrayList arrayList = new ArrayList();
            if (this.weight > 0) {
                arrayList.add("weight:" + this.weight);
            }
            if (this.color != null) {
                arrayList.add("color:" + this.color);
            }
            if (this.fillcolor != null) {
                arrayList.add("fillcolor:" + this.fillcolor);
            }
            if (this.geodesic) {
                arrayList.add("geodesic:" + this.geodesic);
            }
            arrayList.addAll(this.points);
            return StringJoin.join('|', (String[]) arrayList.toArray(new String[arrayList.size()]));
        }

        public void weight(int i2) {
            this.weight = i2;
        }
    }

    /* loaded from: classes2.dex */
    public enum StaticMapType implements StringJoin.UrlValue {
        roadmap,
        satellite,
        terrain,
        hybrid;

        @Override // com.google.maps.internal.StringJoin.UrlValue
        public String toUrlValue() {
            return name();
        }
    }

    public StaticMapsRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10281a, ImageResult.Response.class);
    }

    public StaticMapsRequest center(LatLng latLng) {
        return (StaticMapsRequest) b("center", latLng);
    }

    public StaticMapsRequest center(String str) {
        return (StaticMapsRequest) c("center", str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.StaticMapsRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ StaticMapsRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.StaticMapsRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ StaticMapsRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    public StaticMapsRequest format(ImageFormat imageFormat) {
        return (StaticMapsRequest) b("format", imageFormat);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if ((!f().containsKey("center") || !f().containsKey("zoom")) && !f().containsKey("markers") && !f().containsKey(ClientCookie.PATH_ATTR)) {
            throw new IllegalArgumentException("Request must contain 'center' and 'zoom' if 'markers' or 'path' aren't present.");
        }
        if (!f().containsKey("size")) {
            throw new IllegalArgumentException("Request must contain 'size'.");
        }
    }

    public StaticMapsRequest maptype(StaticMapType staticMapType) {
        return (StaticMapsRequest) b("maptype", staticMapType);
    }

    public StaticMapsRequest markers(Markers markers) {
        return (StaticMapsRequest) d("markers", markers);
    }

    public StaticMapsRequest path(Path path) {
        return (StaticMapsRequest) d(ClientCookie.PATH_ATTR, path);
    }

    public StaticMapsRequest path(EncodedPolyline encodedPolyline) {
        return (StaticMapsRequest) e(ClientCookie.PATH_ATTR, "enc:" + encodedPolyline.getEncodedPath());
    }

    public StaticMapsRequest region(String str) {
        return (StaticMapsRequest) c("region", str);
    }

    public StaticMapsRequest scale(int i2) {
        return (StaticMapsRequest) a("scale", i2);
    }

    public StaticMapsRequest size(Size size) {
        return (StaticMapsRequest) b("size", size);
    }

    public StaticMapsRequest visible(LatLng latLng) {
        return (StaticMapsRequest) b("visible", latLng);
    }

    public StaticMapsRequest visible(String str) {
        return (StaticMapsRequest) c("visible", str);
    }

    public StaticMapsRequest zoom(int i2) {
        return (StaticMapsRequest) a("zoom", i2);
    }
}
