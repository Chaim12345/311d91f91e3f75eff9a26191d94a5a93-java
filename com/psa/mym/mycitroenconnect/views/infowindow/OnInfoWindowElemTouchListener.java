package com.psa.mym.mycitroenconnect.views.infowindow;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import com.google.android.gms.maps.model.Marker;
/* loaded from: classes3.dex */
public abstract class OnInfoWindowElemTouchListener implements View.OnTouchListener {
    private Marker marker;
    private final View view;
    private final Handler handler = new Handler();
    private boolean pressed = false;
    private final Runnable confirmClickRunnable = new Runnable() { // from class: com.psa.mym.mycitroenconnect.views.infowindow.OnInfoWindowElemTouchListener.1
        @Override // java.lang.Runnable
        public void run() {
            if (OnInfoWindowElemTouchListener.this.endPress()) {
                OnInfoWindowElemTouchListener onInfoWindowElemTouchListener = OnInfoWindowElemTouchListener.this;
                onInfoWindowElemTouchListener.d(onInfoWindowElemTouchListener.view, OnInfoWindowElemTouchListener.this.marker);
            }
        }
    };

    public OnInfoWindowElemTouchListener(View view) {
        this.view = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean endPress() {
        if (this.pressed) {
            this.pressed = false;
            this.handler.removeCallbacks(this.confirmClickRunnable);
            Marker marker = this.marker;
            if (marker != null) {
                marker.showInfoWindow();
                return true;
            }
            return true;
        }
        return false;
    }

    private void startPress() {
        if (this.pressed) {
            return;
        }
        this.pressed = true;
        this.handler.removeCallbacks(this.confirmClickRunnable);
        Marker marker = this.marker;
        if (marker != null) {
            marker.showInfoWindow();
        }
    }

    protected abstract void d(View view, Marker marker);

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (0.0f <= motionEvent.getX() && motionEvent.getX() <= this.view.getWidth() && 0.0f <= motionEvent.getY() && motionEvent.getY() <= this.view.getHeight()) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                startPress();
                return false;
            } else if (actionMasked == 1) {
                this.handler.postDelayed(this.confirmClickRunnable, 150L);
                return false;
            } else if (actionMasked != 3) {
                return false;
            }
        }
        endPress();
        return false;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }
}
