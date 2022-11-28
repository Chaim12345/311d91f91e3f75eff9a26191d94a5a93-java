package com.google.maps.internal;

import com.google.firebase.messaging.Constants;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.maps.GeolocationApi;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import org.apache.http.cookie.ClientCookie;
/* loaded from: classes2.dex */
public class GeolocationResponseAdapter extends TypeAdapter<GeolocationApi.Response> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public GeolocationApi.Response read(JsonReader jsonReader) {
        if (jsonReader.peek() == JsonToken.NULL) {
            jsonReader.nextNull();
            return null;
        }
        GeolocationApi.Response response = new GeolocationApi.Response();
        LatLngAdapter latLngAdapter = new LatLngAdapter();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equals("location")) {
                response.location = latLngAdapter.read(jsonReader);
            } else if (nextName.equals("accuracy")) {
                response.accuracy = jsonReader.nextDouble();
            } else if (nextName.equals(Constants.IPC_BUNDLE_KEY_SEND_ERROR)) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String nextName2 = jsonReader.nextName();
                    if (nextName2.equals("code")) {
                        response.code = jsonReader.nextInt();
                    } else if (nextName2.equals(AppConstants.ARG_MESSAGE)) {
                        response.message = jsonReader.nextString();
                    } else if (nextName2.equals("errors")) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            jsonReader.beginObject();
                            while (jsonReader.hasNext()) {
                                String nextName3 = jsonReader.nextName();
                                if (nextName3.equals("reason")) {
                                    response.reason = jsonReader.nextString();
                                } else if (nextName3.equals(ClientCookie.DOMAIN_ATTR)) {
                                    response.domain = jsonReader.nextString();
                                } else if (nextName3.equals("debugInfo")) {
                                    response.debugInfo = jsonReader.nextString();
                                } else if (nextName3.equals(AppConstants.ARG_MESSAGE) || nextName3.equals("location") || nextName3.equals("locationType")) {
                                    jsonReader.nextString();
                                }
                            }
                            jsonReader.endObject();
                        }
                        jsonReader.endArray();
                    }
                }
                jsonReader.endObject();
            }
        }
        jsonReader.endObject();
        return response;
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, GeolocationApi.Response response) {
        throw new UnsupportedOperationException("Unimplemented method.");
    }
}
