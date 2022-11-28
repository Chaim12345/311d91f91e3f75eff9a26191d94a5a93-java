package com.psa.mym.mycitroenconnect.views.infowindow;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes3.dex */
public class MapWrapperLayout extends RelativeLayout {
    private int bottomOffsetPixels;
    private View infoWindow;
    private GoogleMap map;
    private Marker marker;

    public MapWrapperLayout(Context context) {
        super(context);
    }

    public MapWrapperLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MapWrapperLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z;
        GoogleMap googleMap;
        Marker marker = this.marker;
        if (marker == null || !marker.isInfoWindowShown() || (googleMap = this.map) == null || this.infoWindow == null) {
            z = false;
        } else {
            Point screenLocation = googleMap.getProjection().toScreenLocation(this.marker.getPosition());
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.offsetLocation((-screenLocation.x) + (this.infoWindow.getWidth() / 2), (-screenLocation.y) + this.infoWindow.getHeight() + this.bottomOffsetPixels);
            z = this.infoWindow.dispatchTouchEvent(obtain);
        }
        return z || super.dispatchTouchEvent(motionEvent);
    }

    public void init(GoogleMap googleMap, int i2) {
        this.map = googleMap;
        this.bottomOffsetPixels = i2;
    }

    public void setMarkerWithInfoWindow(Marker marker, View view) {
        this.marker = marker;
        this.infoWindow = view;
    }
}
