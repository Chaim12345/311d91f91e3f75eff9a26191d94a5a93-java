package com.airbnb.lottie.parser;

import android.graphics.Color;
import android.graphics.Rect;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.util.ArrayList;
import java.util.Collections;
/* loaded from: classes.dex */
public class LayerParser {
    private static final JsonReader.Options NAMES = JsonReader.Options.of("nm", "ind", "refId", "ty", "parent", "sw", "sh", "sc", "ks", "tt", "masksProperties", "shapes", "t", "ef", "sr", "st", "w", "h", "ip", "op", "tm", "cl", "hd");
    private static final JsonReader.Options TEXT_NAMES = JsonReader.Options.of("d", "a");
    private static final JsonReader.Options EFFECTS_NAMES = JsonReader.Options.of("nm");

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.airbnb.lottie.parser.LayerParser$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {

        /* renamed from: a  reason: collision with root package name */
        static final /* synthetic */ int[] f4446a;

        static {
            int[] iArr = new int[Layer.MatteType.values().length];
            f4446a = iArr;
            try {
                iArr[Layer.MatteType.LUMA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4446a[Layer.MatteType.LUMA_INVERTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private LayerParser() {
    }

    public static Layer parse(LottieComposition lottieComposition) {
        Rect bounds = lottieComposition.getBounds();
        return new Layer(Collections.emptyList(), lottieComposition, "__container", -1L, Layer.LayerType.PRE_COMP, -1L, null, Collections.emptyList(), new AnimatableTransform(), 0, 0, 0, 0.0f, 0.0f, bounds.width(), bounds.height(), null, null, Collections.emptyList(), Layer.MatteType.NONE, null, false);
    }

    public static Layer parse(JsonReader jsonReader, LottieComposition lottieComposition) {
        ArrayList arrayList;
        ArrayList arrayList2;
        float f2;
        StringBuilder sb;
        String str;
        Layer.MatteType matteType = Layer.MatteType.NONE;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        jsonReader.beginObject();
        Float valueOf = Float.valueOf(1.0f);
        Float valueOf2 = Float.valueOf(0.0f);
        Layer.MatteType matteType2 = matteType;
        float f3 = 1.0f;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z = false;
        Layer.LayerType layerType = null;
        String str2 = null;
        AnimatableTransform animatableTransform = null;
        AnimatableTextFrame animatableTextFrame = null;
        AnimatableTextProperties animatableTextProperties = null;
        AnimatableFloatValue animatableFloatValue = null;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        long j2 = -1;
        long j3 = 0;
        String str3 = null;
        String str4 = "UNSET";
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(NAMES)) {
                case 0:
                    str4 = jsonReader.nextString();
                    break;
                case 1:
                    j3 = jsonReader.nextInt();
                    break;
                case 2:
                    str2 = jsonReader.nextString();
                    break;
                case 3:
                    int nextInt = jsonReader.nextInt();
                    layerType = Layer.LayerType.UNKNOWN;
                    if (nextInt >= layerType.ordinal()) {
                        break;
                    } else {
                        layerType = Layer.LayerType.values()[nextInt];
                        break;
                    }
                case 4:
                    j2 = jsonReader.nextInt();
                    break;
                case 5:
                    i2 = (int) (jsonReader.nextInt() * Utils.dpScale());
                    break;
                case 6:
                    i3 = (int) (jsonReader.nextInt() * Utils.dpScale());
                    break;
                case 7:
                    i4 = Color.parseColor(jsonReader.nextString());
                    break;
                case 8:
                    animatableTransform = AnimatableTransformParser.parse(jsonReader, lottieComposition);
                    break;
                case 9:
                    int nextInt2 = jsonReader.nextInt();
                    if (nextInt2 >= Layer.MatteType.values().length) {
                        sb = new StringBuilder();
                        sb.append("Unsupported matte type: ");
                        sb.append(nextInt2);
                        lottieComposition.addWarning(sb.toString());
                        break;
                    } else {
                        matteType2 = Layer.MatteType.values()[nextInt2];
                        int i7 = AnonymousClass1.f4446a[matteType2.ordinal()];
                        if (i7 != 1) {
                            str = i7 == 2 ? "Unsupported matte type: Luma Inverted" : "Unsupported matte type: Luma";
                            lottieComposition.incrementMatteOrMaskCount(1);
                            break;
                        }
                        lottieComposition.addWarning(str);
                        lottieComposition.incrementMatteOrMaskCount(1);
                    }
                case 10:
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        arrayList3.add(MaskParser.a(jsonReader, lottieComposition));
                    }
                    lottieComposition.incrementMatteOrMaskCount(arrayList3.size());
                    jsonReader.endArray();
                    break;
                case 11:
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        ContentModel a2 = ContentModelParser.a(jsonReader, lottieComposition);
                        if (a2 != null) {
                            arrayList4.add(a2);
                        }
                    }
                    jsonReader.endArray();
                    break;
                case 12:
                    jsonReader.beginObject();
                    while (jsonReader.hasNext()) {
                        int selectName = jsonReader.selectName(TEXT_NAMES);
                        if (selectName == 0) {
                            animatableTextFrame = AnimatableValueParser.b(jsonReader, lottieComposition);
                        } else if (selectName != 1) {
                            jsonReader.skipName();
                            jsonReader.skipValue();
                        } else {
                            jsonReader.beginArray();
                            if (jsonReader.hasNext()) {
                                animatableTextProperties = AnimatableTextPropertiesParser.parse(jsonReader, lottieComposition);
                            }
                            while (jsonReader.hasNext()) {
                                jsonReader.skipValue();
                            }
                            jsonReader.endArray();
                        }
                    }
                    jsonReader.endObject();
                    break;
                case 13:
                    jsonReader.beginArray();
                    ArrayList arrayList5 = new ArrayList();
                    while (jsonReader.hasNext()) {
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            if (jsonReader.selectName(EFFECTS_NAMES) != 0) {
                                jsonReader.skipName();
                                jsonReader.skipValue();
                            } else {
                                arrayList5.add(jsonReader.nextString());
                            }
                        }
                        jsonReader.endObject();
                    }
                    jsonReader.endArray();
                    sb = new StringBuilder();
                    sb.append("Lottie doesn't support layer effects. If you are using them for  fills, strokes, trim paths etc. then try adding them directly as contents  in your shape. Found: ");
                    sb.append(arrayList5);
                    lottieComposition.addWarning(sb.toString());
                    break;
                case 14:
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 15:
                    f6 = (float) jsonReader.nextDouble();
                    break;
                case 16:
                    i5 = (int) (jsonReader.nextInt() * Utils.dpScale());
                    break;
                case 17:
                    i6 = (int) (jsonReader.nextInt() * Utils.dpScale());
                    break;
                case 18:
                    f4 = (float) jsonReader.nextDouble();
                    break;
                case 19:
                    f5 = (float) jsonReader.nextDouble();
                    break;
                case 20:
                    animatableFloatValue = AnimatableValueParser.parseFloat(jsonReader, lottieComposition, false);
                    break;
                case 21:
                    str3 = jsonReader.nextString();
                    break;
                case 22:
                    z = jsonReader.nextBoolean();
                    break;
                default:
                    jsonReader.skipName();
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        float f7 = f4 / f3;
        float f8 = f5 / f3;
        ArrayList arrayList6 = new ArrayList();
        if (f7 > 0.0f) {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            arrayList2.add(new Keyframe(lottieComposition, valueOf2, valueOf2, null, 0.0f, Float.valueOf(f7)));
            f2 = 0.0f;
        } else {
            arrayList = arrayList3;
            arrayList2 = arrayList6;
            f2 = 0.0f;
        }
        if (f8 <= f2) {
            f8 = lottieComposition.getEndFrame();
        }
        arrayList2.add(new Keyframe(lottieComposition, valueOf, valueOf, null, f7, Float.valueOf(f8)));
        arrayList2.add(new Keyframe(lottieComposition, valueOf2, valueOf2, null, f8, Float.valueOf(Float.MAX_VALUE)));
        if (str4.endsWith(".ai") || "ai".equals(str3)) {
            lottieComposition.addWarning("Convert your Illustrator layers to shape layers.");
        }
        return new Layer(arrayList4, lottieComposition, str4, j3, layerType, j2, str2, arrayList, animatableTransform, i2, i3, i4, f3, f6, i5, i6, animatableTextFrame, animatableTextProperties, arrayList2, matteType2, animatableFloatValue, z);
    }
}
