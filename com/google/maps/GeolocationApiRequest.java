package com.google.maps;

import com.google.gson.Gson;
import com.google.maps.GeolocationApi;
import com.google.maps.model.CellTower;
import com.google.maps.model.GeolocationPayload;
import com.google.maps.model.GeolocationResult;
import com.google.maps.model.WifiAccessPoint;
/* loaded from: classes2.dex */
public class GeolocationApiRequest extends PendingResultBase<GeolocationResult, GeolocationApiRequest, GeolocationApi.Response> {
    private GeolocationPayload.GeolocationPayloadBuilder builder;
    private GeolocationPayload payload;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GeolocationApiRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, GeolocationApi.f10270a, GeolocationApi.Response.class);
        this.payload = null;
        this.builder = null;
        this.builder = new GeolocationPayload.GeolocationPayloadBuilder();
    }

    public GeolocationApiRequest AddCellTower(CellTower cellTower) {
        this.builder.AddCellTower(cellTower);
        return this;
    }

    public GeolocationApiRequest AddWifiAccessPoint(WifiAccessPoint wifiAccessPoint) {
        this.builder.AddWifiAccessPoint(wifiAccessPoint);
        return this;
    }

    public GeolocationApiRequest Carrier(String str) {
        this.builder.Carrier(str);
        return this;
    }

    public GeolocationApiRequest CellTowers(CellTower[] cellTowerArr) {
        this.builder.CellTowers(cellTowerArr);
        return this;
    }

    public GeolocationApiRequest ConsiderIp(boolean z) {
        this.builder.ConsiderIp(z);
        return this;
    }

    public GeolocationApiRequest CreatePayload() {
        if (this.payload == null) {
            this.payload = this.builder.createGeolocationPayload();
        }
        return (GeolocationApiRequest) c("_payload", new Gson().toJson(this.payload));
    }

    public GeolocationApiRequest HomeMobileCountryCode(int i2) {
        this.builder.HomeMobileCountryCode(i2);
        return this;
    }

    public GeolocationApiRequest HomeMobileNetworkCode(int i2) {
        this.builder.HomeMobileNetworkCode(i2);
        return this;
    }

    public GeolocationApiRequest Payload(GeolocationPayload geolocationPayload) {
        this.payload = geolocationPayload;
        return this;
    }

    public GeolocationApiRequest RadioType(String str) {
        this.builder.RadioType(str);
        return this;
    }

    public GeolocationApiRequest WifiAccessPoints(WifiAccessPoint[] wifiAccessPointArr) {
        this.builder.WifiAccessPoints(wifiAccessPointArr);
        return this;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.GeolocationApiRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ GeolocationApiRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.GeolocationApiRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ GeolocationApiRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        WifiAccessPoint[] wifiAccessPointArr;
        Boolean bool = this.payload.considerIp;
        if (bool != null && !bool.booleanValue() && (wifiAccessPointArr = this.payload.wifiAccessPoints) != null && wifiAccessPointArr.length < 2) {
            throw new IllegalArgumentException("Request must contain two or more 'Wifi Access Points'");
        }
    }
}
