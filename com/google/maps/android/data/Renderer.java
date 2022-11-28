package com.google.maps.android.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.maps.android.R;
import com.google.maps.android.collections.GroundOverlayManager;
import com.google.maps.android.collections.MarkerManager;
import com.google.maps.android.collections.PolygonManager;
import com.google.maps.android.collections.PolylineManager;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.BiMultiMap;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonGeometryCollection;
import com.google.maps.android.data.geojson.GeoJsonLineString;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;
import com.google.maps.android.data.geojson.GeoJsonMultiLineString;
import com.google.maps.android.data.geojson.GeoJsonMultiPoint;
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon;
import com.google.maps.android.data.geojson.GeoJsonPoint;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;
import com.google.maps.android.data.geojson.GeoJsonPolygon;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;
import com.google.maps.android.data.kml.KmlContainer;
import com.google.maps.android.data.kml.KmlGroundOverlay;
import com.google.maps.android.data.kml.KmlMultiGeometry;
import com.google.maps.android.data.kml.KmlPlacemark;
import com.google.maps.android.data.kml.KmlPoint;
import com.google.maps.android.data.kml.KmlPolygon;
import com.google.maps.android.data.kml.KmlStyle;
import com.google.maps.android.data.kml.KmlUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.bouncycastle.i18n.TextBundle;
/* loaded from: classes2.dex */
public class Renderer {
    private static final Object FEATURE_NOT_ON_MAP = null;
    private static final int MARKER_ICON_SIZE = 32;
    private static final DecimalFormat sScaleFormat = new DecimalFormat("#.####");
    private final BiMultiMap<Feature> mContainerFeatures;
    private ArrayList<KmlContainer> mContainers;
    private Context mContext;
    private final GeoJsonLineStringStyle mDefaultLineStringStyle;
    private final GeoJsonPointStyle mDefaultPointStyle;
    private final GeoJsonPolygonStyle mDefaultPolygonStyle;
    private final BiMultiMap<Feature> mFeatures;
    private HashMap<KmlGroundOverlay, GroundOverlay> mGroundOverlayMap;
    private final GroundOverlayManager.Collection mGroundOverlays;
    private ImagesCache mImagesCache;
    private boolean mLayerOnMap;
    private GoogleMap mMap;
    private final Set<String> mMarkerIconUrls;
    private final MarkerManager.Collection mMarkers;
    private int mNumActiveDownloads;
    private final PolygonManager.Collection mPolygons;
    private final PolylineManager.Collection mPolylines;
    private HashMap<String, String> mStyleMaps;
    private HashMap<String, KmlStyle> mStyles;
    private HashMap<String, KmlStyle> mStylesRenderer;

    /* loaded from: classes2.dex */
    public static final class ImagesCache {

        /* renamed from: a  reason: collision with root package name */
        final Map f10315a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        final Map f10316b = new HashMap();

        /* renamed from: c  reason: collision with root package name */
        final Map f10317c = new HashMap();
    }

    public Renderer(GoogleMap googleMap, Context context, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager, @Nullable ImagesCache imagesCache) {
        this(googleMap, new HashSet(), null, null, null, new BiMultiMap(), markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.mContext = context;
        this.mStylesRenderer = new HashMap<>();
        this.mImagesCache = imagesCache == null ? new ImagesCache() : imagesCache;
    }

    public Renderer(GoogleMap googleMap, HashMap<? extends Feature, Object> hashMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        this(googleMap, null, new GeoJsonPointStyle(), new GeoJsonLineStringStyle(), new GeoJsonPolygonStyle(), null, markerManager, polygonManager, polylineManager, groundOverlayManager);
        this.mFeatures.putAll(hashMap);
        this.mImagesCache = null;
    }

    private Renderer(GoogleMap googleMap, Set<String> set, GeoJsonPointStyle geoJsonPointStyle, GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonPolygonStyle geoJsonPolygonStyle, BiMultiMap<Feature> biMultiMap, MarkerManager markerManager, PolygonManager polygonManager, PolylineManager polylineManager, GroundOverlayManager groundOverlayManager) {
        GroundOverlayManager.Collection collection;
        this.mFeatures = new BiMultiMap<>();
        this.mNumActiveDownloads = 0;
        this.mMap = googleMap;
        this.mLayerOnMap = false;
        this.mMarkerIconUrls = set;
        this.mDefaultPointStyle = geoJsonPointStyle;
        this.mDefaultLineStringStyle = geoJsonLineStringStyle;
        this.mDefaultPolygonStyle = geoJsonPolygonStyle;
        this.mContainerFeatures = biMultiMap;
        if (googleMap != null) {
            this.mMarkers = (markerManager == null ? new MarkerManager(googleMap) : markerManager).newCollection();
            this.mPolygons = (polygonManager == null ? new PolygonManager(googleMap) : polygonManager).newCollection();
            this.mPolylines = (polylineManager == null ? new PolylineManager(googleMap) : polylineManager).newCollection();
            collection = (groundOverlayManager == null ? new GroundOverlayManager(googleMap) : groundOverlayManager).newCollection();
        } else {
            collection = null;
            this.mMarkers = null;
            this.mPolygons = null;
            this.mPolylines = null;
        }
        this.mGroundOverlays = collection;
    }

    private ArrayList<Object> addGeometryCollectionToMap(GeoJsonFeature geoJsonFeature, List<Geometry> list) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Geometry geometry : list) {
            arrayList.add(d(geoJsonFeature, geometry));
        }
        return arrayList;
    }

    private Polyline addLineStringToMap(PolylineOptions polylineOptions, LineString lineString) {
        polylineOptions.addAll(lineString.getGeometryObject());
        Polyline addPolyline = this.mPolylines.addPolyline(polylineOptions);
        addPolyline.setClickable(polylineOptions.isClickable());
        return addPolyline;
    }

    private void addMarkerIcons(String str, double d2, MarkerOptions markerOptions) {
        BitmapDescriptor n2 = n(str, d2);
        if (n2 != null) {
            markerOptions.icon(n2);
        } else {
            this.mMarkerIconUrls.add(str);
        }
    }

    private ArrayList<Object> addMultiGeometryToMap(KmlPlacemark kmlPlacemark, KmlMultiGeometry kmlMultiGeometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z) {
        ArrayList<Object> arrayList = new ArrayList<>();
        Iterator<Geometry> it = kmlMultiGeometry.getGeometryObject().iterator();
        while (it.hasNext()) {
            arrayList.add(e(kmlPlacemark, it.next(), kmlStyle, kmlStyle2, z));
        }
        return arrayList;
    }

    private ArrayList<Polyline> addMultiLineStringToMap(GeoJsonLineStringStyle geoJsonLineStringStyle, GeoJsonMultiLineString geoJsonMultiLineString) {
        ArrayList<Polyline> arrayList = new ArrayList<>();
        for (GeoJsonLineString geoJsonLineString : geoJsonMultiLineString.getLineStrings()) {
            arrayList.add(addLineStringToMap(geoJsonLineStringStyle.toPolylineOptions(), geoJsonLineString));
        }
        return arrayList;
    }

    private ArrayList<Marker> addMultiPointToMap(GeoJsonPointStyle geoJsonPointStyle, GeoJsonMultiPoint geoJsonMultiPoint) {
        ArrayList<Marker> arrayList = new ArrayList<>();
        for (GeoJsonPoint geoJsonPoint : geoJsonMultiPoint.getPoints()) {
            arrayList.add(addPointToMap(geoJsonPointStyle.toMarkerOptions(), geoJsonPoint));
        }
        return arrayList;
    }

    private ArrayList<Polygon> addMultiPolygonToMap(GeoJsonPolygonStyle geoJsonPolygonStyle, GeoJsonMultiPolygon geoJsonMultiPolygon) {
        ArrayList<Polygon> arrayList = new ArrayList<>();
        for (GeoJsonPolygon geoJsonPolygon : geoJsonMultiPolygon.getPolygons()) {
            arrayList.add(addPolygonToMap(geoJsonPolygonStyle.toPolygonOptions(), geoJsonPolygon));
        }
        return arrayList;
    }

    private Marker addPointToMap(MarkerOptions markerOptions, Point point) {
        markerOptions.position(point.getGeometryObject());
        return this.mMarkers.addMarker(markerOptions);
    }

    private Polygon addPolygonToMap(PolygonOptions polygonOptions, DataPolygon dataPolygon) {
        polygonOptions.addAll(dataPolygon.getOuterBoundaryCoordinates());
        for (List<LatLng> list : dataPolygon.getInnerBoundaryCoordinates()) {
            polygonOptions.addHole(list);
        }
        Polygon addPolygon = this.mPolygons.addPolygon(polygonOptions);
        addPolygon.setClickable(polygonOptions.isClickable());
        return addPolygon;
    }

    private void createInfoWindow() {
        this.mMarkers.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() { // from class: com.google.maps.android.data.Renderer.1
            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoContents(Marker marker) {
                String title;
                View inflate = LayoutInflater.from(Renderer.this.mContext).inflate(R.layout.amu_info_window, (ViewGroup) null);
                TextView textView = (TextView) inflate.findViewById(R.id.window);
                if (marker.getSnippet() != null) {
                    title = marker.getTitle() + "<br>" + marker.getSnippet();
                } else {
                    title = marker.getTitle();
                }
                textView.setText(Html.fromHtml(title));
                return inflate;
            }

            @Override // com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
            public View getInfoWindow(Marker marker) {
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<?> multiObjectHandler(Object obj) {
        for (Object obj2 : getValues()) {
            if (obj2.getClass().getSimpleName().equals("ArrayList")) {
                ArrayList<?> arrayList = (ArrayList) obj2;
                if (arrayList.contains(obj)) {
                    return arrayList;
                }
            }
        }
        return null;
    }

    private void putMarkerImagesCache(String str, String str2, BitmapDescriptor bitmapDescriptor) {
        Map map = (Map) this.mImagesCache.f10315a.get(str);
        if (map == null) {
            map = new HashMap();
            this.mImagesCache.f10315a.put(str, map);
        }
        map.put(str2, bitmapDescriptor);
    }

    private void removeFeatures(Collection collection) {
        for (Object obj : collection) {
            if (obj instanceof Collection) {
                removeFeatures((Collection) obj);
            } else if (obj instanceof Marker) {
                this.mMarkers.remove((Marker) obj);
            } else if (obj instanceof Polyline) {
                this.mPolylines.remove((Polyline) obj);
            } else if (obj instanceof Polygon) {
                this.mPolygons.remove((Polygon) obj);
            }
        }
    }

    private BitmapDescriptor scaleIcon(Bitmap bitmap, double d2) {
        int i2;
        int i3 = (int) (this.mContext.getResources().getDisplayMetrics().density * 32.0f * d2);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < height) {
            i2 = (int) ((height * i3) / width);
        } else if (width > height) {
            int i4 = (int) ((width * i3) / height);
            i2 = i3;
            i3 = i4;
        } else {
            i2 = i3;
        }
        return BitmapDescriptorFactory.fromBitmap(Bitmap.createScaledBitmap(bitmap, i3, i2, false));
    }

    private void setFeatureDefaultStyles(GeoJsonFeature geoJsonFeature) {
        if (geoJsonFeature.getPointStyle() == null) {
            geoJsonFeature.setPointStyle(this.mDefaultPointStyle);
        }
        if (geoJsonFeature.getLineStringStyle() == null) {
            geoJsonFeature.setLineStringStyle(this.mDefaultLineStringStyle);
        }
        if (geoJsonFeature.getPolygonStyle() == null) {
            geoJsonFeature.setPolygonStyle(this.mDefaultPolygonStyle);
        }
    }

    private void setInlineLineStringStyle(PolylineOptions polylineOptions, KmlStyle kmlStyle) {
        PolylineOptions polylineOptions2 = kmlStyle.getPolylineOptions();
        if (kmlStyle.isStyleSet("outlineColor")) {
            polylineOptions.color(polylineOptions2.getColor());
        }
        if (kmlStyle.isStyleSet("width")) {
            polylineOptions.width(polylineOptions2.getWidth());
        }
        if (kmlStyle.isLineRandomColorMode()) {
            polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions2.getColor()));
        }
    }

    private void setInlinePointStyle(MarkerOptions markerOptions, KmlStyle kmlStyle, KmlStyle kmlStyle2) {
        String iconUrl;
        MarkerOptions markerOptions2 = kmlStyle.getMarkerOptions();
        if (kmlStyle.isStyleSet("heading")) {
            markerOptions.rotation(markerOptions2.getRotation());
        }
        if (kmlStyle.isStyleSet("hotSpot")) {
            markerOptions.anchor(markerOptions2.getAnchorU(), markerOptions2.getAnchorV());
        }
        if (kmlStyle.isStyleSet("markerColor")) {
            markerOptions.icon(markerOptions2.getIcon());
        }
        double iconScale = kmlStyle.isStyleSet("iconScale") ? kmlStyle.getIconScale() : kmlStyle2.isStyleSet("iconScale") ? kmlStyle2.getIconScale() : 1.0d;
        if (kmlStyle.isStyleSet("iconUrl")) {
            iconUrl = kmlStyle.getIconUrl();
        } else if (kmlStyle2.getIconUrl() == null) {
            return;
        } else {
            iconUrl = kmlStyle2.getIconUrl();
        }
        addMarkerIcons(iconUrl, iconScale, markerOptions);
    }

    private void setInlinePolygonStyle(PolygonOptions polygonOptions, KmlStyle kmlStyle) {
        PolygonOptions polygonOptions2 = kmlStyle.getPolygonOptions();
        if (kmlStyle.hasFill() && kmlStyle.isStyleSet("fillColor")) {
            polygonOptions.fillColor(polygonOptions2.getFillColor());
        }
        if (kmlStyle.hasOutline()) {
            if (kmlStyle.isStyleSet("outlineColor")) {
                polygonOptions.strokeColor(polygonOptions2.getStrokeColor());
            }
            if (kmlStyle.isStyleSet("width")) {
                polygonOptions.strokeWidth(polygonOptions2.getStrokeWidth());
            }
        }
        if (kmlStyle.isPolyRandomColorMode()) {
            polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions2.getFillColor()));
        }
    }

    private void setMarkerInfoWindow(KmlStyle kmlStyle, Marker marker, KmlPlacemark kmlPlacemark) {
        String property;
        boolean hasProperty = kmlPlacemark.hasProperty(AppMeasurementSdk.ConditionalUserProperty.NAME);
        boolean hasProperty2 = kmlPlacemark.hasProperty("description");
        boolean hasBalloonStyle = kmlStyle.hasBalloonStyle();
        boolean containsKey = kmlStyle.getBalloonOptions().containsKey(TextBundle.TEXT_ENTRY);
        if (hasBalloonStyle && containsKey) {
            property = KmlUtil.substituteProperties(kmlStyle.getBalloonOptions().get(TextBundle.TEXT_ENTRY), kmlPlacemark);
        } else {
            if (!hasBalloonStyle || !hasProperty) {
                if (hasProperty && hasProperty2) {
                    marker.setTitle(kmlPlacemark.getProperty(AppMeasurementSdk.ConditionalUserProperty.NAME));
                    marker.setSnippet(kmlPlacemark.getProperty("description"));
                    createInfoWindow();
                } else if (hasProperty2) {
                    property = kmlPlacemark.getProperty("description");
                } else if (!hasProperty) {
                    return;
                }
            }
            property = kmlPlacemark.getProperty(AppMeasurementSdk.ConditionalUserProperty.NAME);
        }
        marker.setTitle(property);
        createInfoWindow();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean w(Feature feature) {
        return (feature.hasProperty("visibility") && Integer.parseInt(feature.getProperty("visibility")) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void A(Object obj, Feature feature) {
        this.mContainerFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void B(Feature feature, Object obj) {
        this.mFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void C() {
        this.mStylesRenderer.putAll(this.mStyles);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void D(HashMap hashMap) {
        this.mStylesRenderer.putAll(hashMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void E(Feature feature) {
        if (this.mFeatures.containsKey(feature)) {
            G(this.mFeatures.remove(feature));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void F(HashMap hashMap) {
        removeFeatures(hashMap.values());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void G(Object obj) {
        if (obj instanceof Marker) {
            this.mMarkers.remove((Marker) obj);
        } else if (obj instanceof Polyline) {
            this.mPolylines.remove((Polyline) obj);
        } else if (obj instanceof Polygon) {
            this.mPolygons.remove((Polygon) obj);
        } else if (obj instanceof GroundOverlay) {
            this.mGroundOverlays.remove((GroundOverlay) obj);
        } else if (obj instanceof ArrayList) {
            Iterator it = ((ArrayList) obj).iterator();
            while (it.hasNext()) {
                G(it.next());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void H(HashMap hashMap) {
        for (GroundOverlay groundOverlay : hashMap.values()) {
            if (groundOverlay != null) {
                this.mGroundOverlays.remove(groundOverlay);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void I(boolean z) {
        this.mLayerOnMap = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void J(final Layer.OnFeatureClickListener onFeatureClickListener) {
        this.mPolygons.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() { // from class: com.google.maps.android.data.Renderer.2
            @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
            public void onPolygonClick(Polygon polygon) {
                Layer.OnFeatureClickListener onFeatureClickListener2;
                Feature o2;
                Object obj;
                Renderer renderer;
                if (Renderer.this.t(polygon) != null) {
                    onFeatureClickListener2 = onFeatureClickListener;
                    renderer = Renderer.this;
                    obj = polygon;
                } else if (Renderer.this.o(polygon) != null) {
                    onFeatureClickListener2 = onFeatureClickListener;
                    o2 = Renderer.this.o(polygon);
                    onFeatureClickListener2.onFeatureClick(o2);
                } else {
                    onFeatureClickListener2 = onFeatureClickListener;
                    Renderer renderer2 = Renderer.this;
                    Object multiObjectHandler = renderer2.multiObjectHandler(polygon);
                    renderer = renderer2;
                    obj = multiObjectHandler;
                }
                o2 = renderer.t(obj);
                onFeatureClickListener2.onFeatureClick(o2);
            }
        });
        this.mMarkers.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() { // from class: com.google.maps.android.data.Renderer.3
            @Override // com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
            public boolean onMarkerClick(Marker marker) {
                Layer.OnFeatureClickListener onFeatureClickListener2;
                Feature o2;
                Object obj;
                Renderer renderer;
                if (Renderer.this.t(marker) != null) {
                    onFeatureClickListener2 = onFeatureClickListener;
                    renderer = Renderer.this;
                    obj = marker;
                } else if (Renderer.this.o(marker) != null) {
                    onFeatureClickListener2 = onFeatureClickListener;
                    o2 = Renderer.this.o(marker);
                    onFeatureClickListener2.onFeatureClick(o2);
                    return false;
                } else {
                    onFeatureClickListener2 = onFeatureClickListener;
                    Renderer renderer2 = Renderer.this;
                    Object multiObjectHandler = renderer2.multiObjectHandler(marker);
                    renderer = renderer2;
                    obj = multiObjectHandler;
                }
                o2 = renderer.t(obj);
                onFeatureClickListener2.onFeatureClick(o2);
                return false;
            }
        });
        this.mPolylines.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() { // from class: com.google.maps.android.data.Renderer.4
            @Override // com.google.android.gms.maps.GoogleMap.OnPolylineClickListener
            public void onPolylineClick(Polyline polyline) {
                Layer.OnFeatureClickListener onFeatureClickListener2;
                Feature o2;
                Object obj;
                Renderer renderer;
                if (Renderer.this.t(polyline) != null) {
                    onFeatureClickListener2 = onFeatureClickListener;
                    renderer = Renderer.this;
                    obj = polyline;
                } else if (Renderer.this.o(polyline) != null) {
                    onFeatureClickListener2 = onFeatureClickListener;
                    o2 = Renderer.this.o(polyline);
                    onFeatureClickListener2.onFeatureClick(o2);
                } else {
                    onFeatureClickListener2 = onFeatureClickListener;
                    Renderer renderer2 = Renderer.this;
                    Object multiObjectHandler = renderer2.multiObjectHandler(polyline);
                    renderer = renderer2;
                    obj = multiObjectHandler;
                }
                o2 = renderer.t(obj);
                onFeatureClickListener2.onFeatureClick(o2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void K(HashMap hashMap, HashMap hashMap2, HashMap hashMap3, ArrayList arrayList, HashMap hashMap4) {
        this.mStyles = hashMap;
        this.mStyleMaps = hashMap2;
        this.mFeatures.putAll(hashMap3);
        this.mContainers = arrayList;
        this.mGroundOverlayMap = hashMap4;
    }

    public void assignStyleMap(HashMap<String, String> hashMap, HashMap<String, KmlStyle> hashMap2) {
        for (String str : hashMap.keySet()) {
            String str2 = hashMap.get(str);
            if (hashMap2.containsKey(str2)) {
                hashMap2.put(str, hashMap2.get(str2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(Feature feature) {
        Object obj = FEATURE_NOT_ON_MAP;
        if (feature instanceof GeoJsonFeature) {
            setFeatureDefaultStyles((GeoJsonFeature) feature);
        }
        if (this.mLayerOnMap) {
            if (this.mFeatures.containsKey(feature)) {
                G(this.mFeatures.get(feature));
            }
            if (feature.hasGeometry()) {
                if (feature instanceof KmlPlacemark) {
                    KmlPlacemark kmlPlacemark = (KmlPlacemark) feature;
                    obj = e(kmlPlacemark, feature.getGeometry(), v(feature.getId()), kmlPlacemark.getInlineStyle(), w(feature));
                } else {
                    obj = d(feature, feature.getGeometry());
                }
            }
        }
        this.mFeatures.put((BiMultiMap<Feature>) feature, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object d(Feature feature, Geometry geometry) {
        String geometryType = geometry.getGeometryType();
        geometryType.hashCode();
        char c2 = 65535;
        switch (geometryType.hashCode()) {
            case -2116761119:
                if (geometryType.equals("MultiPolygon")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1065891849:
                if (geometryType.equals("MultiPoint")) {
                    c2 = 1;
                    break;
                }
                break;
            case -627102946:
                if (geometryType.equals("MultiLineString")) {
                    c2 = 2;
                    break;
                }
                break;
            case 77292912:
                if (geometryType.equals("Point")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1267133722:
                if (geometryType.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c2 = 4;
                    break;
                }
                break;
            case 1806700869:
                if (geometryType.equals("LineString")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1950410960:
                if (geometryType.equals("GeometryCollection")) {
                    c2 = 6;
                    break;
                }
                break;
        }
        MarkerOptions markerOptions = null;
        PolylineOptions polylineOptions = null;
        PolygonOptions polygonOptions = null;
        switch (c2) {
            case 0:
                return addMultiPolygonToMap(((GeoJsonFeature) feature).getPolygonStyle(), (GeoJsonMultiPolygon) geometry);
            case 1:
                return addMultiPointToMap(((GeoJsonFeature) feature).getPointStyle(), (GeoJsonMultiPoint) geometry);
            case 2:
                return addMultiLineStringToMap(((GeoJsonFeature) feature).getLineStringStyle(), (GeoJsonMultiLineString) geometry);
            case 3:
                if (feature instanceof GeoJsonFeature) {
                    markerOptions = ((GeoJsonFeature) feature).getMarkerOptions();
                } else if (feature instanceof KmlPlacemark) {
                    markerOptions = ((KmlPlacemark) feature).getMarkerOptions();
                }
                return addPointToMap(markerOptions, (GeoJsonPoint) geometry);
            case 4:
                if (feature instanceof GeoJsonFeature) {
                    polygonOptions = ((GeoJsonFeature) feature).getPolygonOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polygonOptions = ((KmlPlacemark) feature).getPolygonOptions();
                }
                return addPolygonToMap(polygonOptions, (DataPolygon) geometry);
            case 5:
                if (feature instanceof GeoJsonFeature) {
                    polylineOptions = ((GeoJsonFeature) feature).getPolylineOptions();
                } else if (feature instanceof KmlPlacemark) {
                    polylineOptions = ((KmlPlacemark) feature).getPolylineOptions();
                }
                return addLineStringToMap(polylineOptions, (GeoJsonLineString) geometry);
            case 6:
                return addGeometryCollectionToMap((GeoJsonFeature) feature, ((GeoJsonGeometryCollection) geometry).getGeometries());
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
        if (r0.equals("Point") == false) goto L5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object e(KmlPlacemark kmlPlacemark, Geometry geometry, KmlStyle kmlStyle, KmlStyle kmlStyle2, boolean z) {
        String geometryType = geometry.getGeometryType();
        boolean hasProperty = kmlPlacemark.hasProperty("drawOrder");
        char c2 = 0;
        float f2 = 0.0f;
        if (hasProperty) {
            try {
                f2 = Float.parseFloat(kmlPlacemark.getProperty("drawOrder"));
            } catch (NumberFormatException unused) {
                hasProperty = false;
            }
        }
        geometryType.hashCode();
        switch (geometryType.hashCode()) {
            case 77292912:
                break;
            case 89139371:
                if (geometryType.equals("MultiGeometry")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 1267133722:
                if (geometryType.equals(KmlPolygon.GEOMETRY_TYPE)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1806700869:
                if (geometryType.equals("LineString")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                MarkerOptions markerOptions = kmlStyle.getMarkerOptions();
                if (kmlStyle2 != null) {
                    setInlinePointStyle(markerOptions, kmlStyle2, kmlStyle);
                } else if (kmlStyle.getIconUrl() != null) {
                    addMarkerIcons(kmlStyle.getIconUrl(), kmlStyle.getIconScale(), markerOptions);
                }
                Marker addPointToMap = addPointToMap(markerOptions, (KmlPoint) geometry);
                addPointToMap.setVisible(z);
                setMarkerInfoWindow(kmlStyle, addPointToMap, kmlPlacemark);
                if (hasProperty) {
                    addPointToMap.setZIndex(f2);
                }
                return addPointToMap;
            case 1:
                return addMultiGeometryToMap(kmlPlacemark, (KmlMultiGeometry) geometry, kmlStyle, kmlStyle2, z);
            case 2:
                PolygonOptions polygonOptions = kmlStyle.getPolygonOptions();
                if (kmlStyle2 != null) {
                    setInlinePolygonStyle(polygonOptions, kmlStyle2);
                } else if (kmlStyle.isPolyRandomColorMode()) {
                    polygonOptions.fillColor(KmlStyle.computeRandomColor(polygonOptions.getFillColor()));
                }
                Polygon addPolygonToMap = addPolygonToMap(polygonOptions, (DataPolygon) geometry);
                addPolygonToMap.setVisible(z);
                if (hasProperty) {
                    addPolygonToMap.setZIndex(f2);
                }
                return addPolygonToMap;
            case 3:
                PolylineOptions polylineOptions = kmlStyle.getPolylineOptions();
                if (kmlStyle2 != null) {
                    setInlineLineStringStyle(polylineOptions, kmlStyle2);
                } else if (kmlStyle.isLineRandomColorMode()) {
                    polylineOptions.color(KmlStyle.computeRandomColor(polylineOptions.getColor()));
                }
                Polyline addLineStringToMap = addLineStringToMap(polylineOptions, (LineString) geometry);
                addLineStringToMap.setVisible(z);
                if (hasProperty) {
                    addLineStringToMap.setZIndex(f2);
                }
                return addLineStringToMap;
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GroundOverlay f(GroundOverlayOptions groundOverlayOptions) {
        return this.mGroundOverlays.addGroundOverlay(groundOverlayOptions);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void g(String str, Bitmap bitmap) {
        this.mImagesCache.f10317c.put(str, bitmap);
    }

    public Set<Feature> getFeatures() {
        return this.mFeatures.keySet();
    }

    public HashMap<KmlGroundOverlay, GroundOverlay> getGroundOverlayMap() {
        return this.mGroundOverlayMap;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }

    public Collection<Object> getValues() {
        return this.mFeatures.values();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void h() {
        ImagesCache imagesCache;
        if (this.mNumActiveDownloads != 0 || (imagesCache = this.mImagesCache) == null || imagesCache.f10317c.isEmpty()) {
            return;
        }
        this.mImagesCache.f10317c.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void i() {
        this.mStylesRenderer.clear();
    }

    public boolean isLayerOnMap() {
        return this.mLayerOnMap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j() {
        this.mNumActiveDownloads--;
        h();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void k() {
        this.mNumActiveDownloads++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HashMap l() {
        return this.mFeatures;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BitmapDescriptor m(String str) {
        Bitmap bitmap;
        BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) this.mImagesCache.f10316b.get(str);
        if (bitmapDescriptor != null || (bitmap = (Bitmap) this.mImagesCache.f10317c.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor fromBitmap = BitmapDescriptorFactory.fromBitmap(bitmap);
        this.mImagesCache.f10316b.put(str, fromBitmap);
        return fromBitmap;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BitmapDescriptor n(String str, double d2) {
        Bitmap bitmap;
        String format = sScaleFormat.format(d2);
        Map map = (Map) this.mImagesCache.f10315a.get(str);
        BitmapDescriptor bitmapDescriptor = map != null ? (BitmapDescriptor) map.get(format) : null;
        if (bitmapDescriptor != null || (bitmap = (Bitmap) this.mImagesCache.f10317c.get(str)) == null) {
            return bitmapDescriptor;
        }
        BitmapDescriptor scaleIcon = scaleIcon(bitmap, d2);
        putMarkerImagesCache(str, format, scaleIcon);
        return scaleIcon;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Feature o(Object obj) {
        BiMultiMap<Feature> biMultiMap = this.mContainerFeatures;
        if (biMultiMap != null) {
            return biMultiMap.getKey(obj);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ArrayList p() {
        return this.mContainers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoJsonLineStringStyle q() {
        return this.mDefaultLineStringStyle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoJsonPointStyle r() {
        return this.mDefaultPointStyle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeoJsonPolygonStyle s() {
        return this.mDefaultPolygonStyle;
    }

    public void setMap(GoogleMap googleMap) {
        this.mMap = googleMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Feature t(Object obj) {
        return this.mFeatures.getKey(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Set u() {
        return this.mMarkerIconUrls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public KmlStyle v(String str) {
        return this.mStylesRenderer.get(str) != null ? this.mStylesRenderer.get(str) : this.mStylesRenderer.get(null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HashMap x() {
        return this.mStyleMaps;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HashMap y() {
        return this.mStylesRenderer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean z() {
        return this.mFeatures.size() > 0;
    }
}
