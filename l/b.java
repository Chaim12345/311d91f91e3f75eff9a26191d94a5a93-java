package l;

import android.content.DialogInterface;
import com.psa.mym.mycitroenconnect.controller.fragments.geo_fence.GeoFenceSetTimeFragment;
/* loaded from: classes3.dex */
public final /* synthetic */ class b implements DialogInterface.OnCancelListener {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ b f12471a = new b();

    private /* synthetic */ b() {
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        GeoFenceSetTimeFragment.m142showDatePicker$lambda11(dialogInterface);
    }
}
