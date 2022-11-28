package com.psa.mym.mycitroenconnect.utils.google_map_direction;

import android.os.Handler;
import android.os.Message;
import com.google.android.gms.maps.model.LatLng;
import com.psa.mym.mycitroenconnect.BuildConfig;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiClient;
import com.psa.mym.mycitroenconnect.api.retrofit.ApiInterface;
import java.util.ArrayList;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilderFactory;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: classes3.dex */
public class GMapV2DirectionAsyncTask {
    private final LatLng end;
    private final Handler handler;
    private final String mode;
    private final LatLng start;
    private final ArrayList<String> viaLocations;

    public GMapV2DirectionAsyncTask(Handler handler, LatLng latLng, LatLng latLng2, ArrayList<String> arrayList, String str) {
        this.start = latLng;
        this.end = latLng2;
        this.viaLocations = arrayList;
        this.mode = str;
        this.handler = handler;
    }

    public void getDistanceFromGoogle() {
        String str;
        if (this.viaLocations.size() > 0) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = this.viaLocations.iterator();
            while (it.hasNext()) {
                sb.append("via:");
                sb.append(it.next());
                sb.append("|");
            }
            str = "https://maps.googleapis.com/maps/api/directions/xml?origin=" + this.start.latitude + "," + this.start.longitude + "&destination=" + this.end.latitude + "," + this.end.longitude + "&waypoints=" + ((Object) sb) + "&sensor=false&units=metric&mode=" + this.mode + "&key=" + BuildConfig.GOOGLE_PLACES_ANDROID_API_KEY + "&alternatives=false";
        } else {
            str = "https://maps.googleapis.com/maps/api/directions/xml?origin=" + this.start.latitude + "," + this.start.longitude + "&destination=" + this.end.latitude + "," + this.end.longitude + "&sensor=false&units=metric&mode=" + this.mode + "&key=" + BuildConfig.GOOGLE_PLACES_ANDROID_API_KEY + "&alternatives=false";
        }
        ((ApiInterface) ApiClient.getSafeRetrofit().create(ApiInterface.class)).getDistanceValues(str).enqueue(new Callback<ResponseBody>() { // from class: com.psa.mym.mycitroenconnect.utils.google_map_direction.GMapV2DirectionAsyncTask.1
            @Override // retrofit2.Callback
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable th) {
                call.cancel();
                Message message = new Message();
                message.obj = null;
                GMapV2DirectionAsyncTask.this.handler.dispatchMessage(message);
            }

            @Override // retrofit2.Callback
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                Message message;
                if (response.isSuccessful()) {
                    try {
                        Document parse = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(response.body().byteStream());
                        Message message2 = new Message();
                        message2.obj = parse;
                        GMapV2DirectionAsyncTask.this.handler.dispatchMessage(message2);
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        message = new Message();
                    }
                } else {
                    message = new Message();
                }
                message.obj = null;
                GMapV2DirectionAsyncTask.this.handler.dispatchMessage(message);
            }
        });
    }
}
