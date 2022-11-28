package com.airbnb.lottie.parser;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ContentModelParser {
    private static JsonReader.Options NAMES = JsonReader.Options.of("ty", "d");

    private ContentModelParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00b4, code lost:
        if (r2.equals("gf") == false) goto L21;
     */
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ContentModel a(JsonReader jsonReader, LottieComposition lottieComposition) {
        ContentModel contentModel;
        String str;
        jsonReader.beginObject();
        char c2 = 2;
        int i2 = 2;
        while (true) {
            contentModel = null;
            if (!jsonReader.hasNext()) {
                str = null;
                break;
            }
            int selectName = jsonReader.selectName(NAMES);
            if (selectName == 0) {
                str = jsonReader.nextString();
                break;
            } else if (selectName != 1) {
                jsonReader.skipName();
                jsonReader.skipValue();
            } else {
                i2 = jsonReader.nextInt();
            }
        }
        if (str == null) {
            return null;
        }
        switch (str.hashCode()) {
            case 3239:
                if (str.equals("el")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 3270:
                if (str.equals("fl")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 3295:
                break;
            case 3307:
                if (str.equals("gr")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 3308:
                if (str.equals("gs")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 3488:
                if (str.equals("mm")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 3633:
                if (str.equals("rc")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 3646:
                if (str.equals("rp")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 3669:
                if (str.equals("sh")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 3679:
                if (str.equals("sr")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 3681:
                if (str.equals("st")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 3705:
                if (str.equals("tm")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case 3710:
                if (str.equals("tr")) {
                    c2 = '\f';
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
                contentModel = CircleShapeParser.a(jsonReader, lottieComposition, i2);
                break;
            case 1:
                contentModel = ShapeFillParser.a(jsonReader, lottieComposition);
                break;
            case 2:
                contentModel = GradientFillParser.a(jsonReader, lottieComposition);
                break;
            case 3:
                contentModel = ShapeGroupParser.a(jsonReader, lottieComposition);
                break;
            case 4:
                contentModel = GradientStrokeParser.a(jsonReader, lottieComposition);
                break;
            case 5:
                contentModel = MergePathsParser.a(jsonReader);
                lottieComposition.addWarning("Animation contains merge paths. Merge paths are only supported on KitKat+ and must be manually enabled by calling enableMergePathsForKitKatAndAbove().");
                break;
            case 6:
                contentModel = RectangleShapeParser.a(jsonReader, lottieComposition);
                break;
            case 7:
                contentModel = RepeaterParser.a(jsonReader, lottieComposition);
                break;
            case '\b':
                contentModel = ShapePathParser.a(jsonReader, lottieComposition);
                break;
            case '\t':
                contentModel = PolystarShapeParser.a(jsonReader, lottieComposition);
                break;
            case '\n':
                contentModel = ShapeStrokeParser.a(jsonReader, lottieComposition);
                break;
            case 11:
                contentModel = ShapeTrimPathParser.a(jsonReader, lottieComposition);
                break;
            case '\f':
                contentModel = AnimatableTransformParser.parse(jsonReader, lottieComposition);
                break;
            default:
                Logger.warning("Unknown shape type " + str);
                break;
        }
        while (jsonReader.hasNext()) {
            jsonReader.skipValue();
        }
        jsonReader.endObject();
        return contentModel;
    }
}
