package com.google.maps;

import com.google.maps.ImageResult;
import com.google.maps.internal.ApiConfig;
/* loaded from: classes2.dex */
public class PhotoRequest extends PendingResultBase<ImageResult, PhotoRequest, ImageResult.Response> {

    /* renamed from: a  reason: collision with root package name */
    static final ApiConfig f10274a = new ApiConfig("/maps/api/place/photo");

    public PhotoRequest(GeoApiContext geoApiContext) {
        super(geoApiContext, f10274a, ImageResult.Response.class);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PhotoRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ PhotoRequest channel(String str) {
        return super.channel(str);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.google.maps.PhotoRequest, com.google.maps.PendingResultBase] */
    @Override // com.google.maps.PendingResultBase
    public /* bridge */ /* synthetic */ PhotoRequest custom(String str, String str2) {
        return super.custom(str, str2);
    }

    @Override // com.google.maps.PendingResultBase
    protected void g() {
        if (!f().containsKey("photoreference")) {
            throw new IllegalArgumentException("Request must contain 'photoReference'.");
        }
        if (!f().containsKey("maxheight") && !f().containsKey("maxwidth")) {
            throw new IllegalArgumentException("Request must contain 'maxHeight' or 'maxWidth'.");
        }
    }

    public PhotoRequest maxHeight(int i2) {
        return (PhotoRequest) c("maxheight", String.valueOf(i2));
    }

    public PhotoRequest maxWidth(int i2) {
        return (PhotoRequest) c("maxwidth", String.valueOf(i2));
    }

    public PhotoRequest photoReference(String str) {
        return (PhotoRequest) c("photoreference", str);
    }
}
