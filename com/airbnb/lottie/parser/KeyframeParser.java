package com.airbnb.lottie.parser;

import android.graphics.PointF;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.parser.moshi.JsonReader;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.utils.Utils;
import com.airbnb.lottie.value.Keyframe;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
class KeyframeParser {
    private static final float MAX_CP_VALUE = 100.0f;
    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache;
    private static final Interpolator LINEAR_INTERPOLATOR = new LinearInterpolator();

    /* renamed from: a  reason: collision with root package name */
    static JsonReader.Options f4443a = JsonReader.Options.of("t", "s", "e", "o", "i", "h", TypedValues.Transition.S_TO, "ti");

    /* renamed from: b  reason: collision with root package name */
    static JsonReader.Options f4444b = JsonReader.Options.of("x", "y");

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Keyframe a(JsonReader jsonReader, LottieComposition lottieComposition, float f2, ValueParser valueParser, boolean z, boolean z2) {
        return (z && z2) ? parseMultiDimensionalKeyframe(lottieComposition, jsonReader, f2, valueParser) : z ? parseKeyframe(lottieComposition, jsonReader, f2, valueParser) : parseStaticValue(jsonReader, f2, valueParser);
    }

    @Nullable
    private static WeakReference<Interpolator> getInterpolator(int i2) {
        WeakReference<Interpolator> weakReference;
        synchronized (KeyframeParser.class) {
            weakReference = pathInterpolatorCache().get(i2);
        }
        return weakReference;
    }

    private static Interpolator interpolatorFor(PointF pointF, PointF pointF2) {
        Interpolator create;
        pointF.x = MiscUtils.clamp(pointF.x, -1.0f, 1.0f);
        pointF.y = MiscUtils.clamp(pointF.y, -100.0f, (float) MAX_CP_VALUE);
        pointF2.x = MiscUtils.clamp(pointF2.x, -1.0f, 1.0f);
        float clamp = MiscUtils.clamp(pointF2.y, -100.0f, (float) MAX_CP_VALUE);
        pointF2.y = clamp;
        int hashFor = Utils.hashFor(pointF.x, pointF.y, pointF2.x, clamp);
        WeakReference<Interpolator> interpolator = getInterpolator(hashFor);
        Interpolator interpolator2 = interpolator != null ? interpolator.get() : null;
        if (interpolator == null || interpolator2 == null) {
            try {
                create = PathInterpolatorCompat.create(pointF.x, pointF.y, pointF2.x, pointF2.y);
            } catch (IllegalArgumentException e2) {
                create = "The Path cannot loop back on itself.".equals(e2.getMessage()) ? PathInterpolatorCompat.create(Math.min(pointF.x, 1.0f), pointF.y, Math.max(pointF2.x, 0.0f), pointF2.y) : new LinearInterpolator();
            }
            interpolator2 = create;
            try {
                putInterpolator(hashFor, new WeakReference(interpolator2));
            } catch (ArrayIndexOutOfBoundsException unused) {
            }
        }
        return interpolator2;
    }

    private static <T> Keyframe<T> parseKeyframe(LottieComposition lottieComposition, JsonReader jsonReader, float f2, ValueParser<T> valueParser) {
        Interpolator interpolatorFor;
        T t2;
        jsonReader.beginObject();
        PointF pointF = null;
        boolean z = false;
        T t3 = null;
        T t4 = null;
        PointF pointF2 = null;
        PointF pointF3 = null;
        float f3 = 0.0f;
        PointF pointF4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(f4443a)) {
                case 0:
                    f3 = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    t4 = valueParser.parse(jsonReader, f2);
                    break;
                case 2:
                    t3 = valueParser.parse(jsonReader, f2);
                    break;
                case 3:
                    pointF = JsonUtils.b(jsonReader, 1.0f);
                    break;
                case 4:
                    pointF4 = JsonUtils.b(jsonReader, 1.0f);
                    break;
                case 5:
                    if (jsonReader.nextInt() != 1) {
                        z = false;
                        break;
                    } else {
                        z = true;
                        break;
                    }
                case 6:
                    pointF2 = JsonUtils.b(jsonReader, f2);
                    break;
                case 7:
                    pointF3 = JsonUtils.b(jsonReader, f2);
                    break;
                default:
                    jsonReader.skipValue();
                    break;
            }
        }
        jsonReader.endObject();
        if (z) {
            interpolatorFor = LINEAR_INTERPOLATOR;
            t2 = t4;
        } else {
            interpolatorFor = (pointF == null || pointF4 == null) ? LINEAR_INTERPOLATOR : interpolatorFor(pointF, pointF4);
            t2 = t3;
        }
        Keyframe<T> keyframe = new Keyframe<>(lottieComposition, t4, t2, interpolatorFor, f3, null);
        keyframe.pathCp1 = pointF2;
        keyframe.pathCp2 = pointF3;
        return keyframe;
    }

    private static <T> Keyframe<T> parseMultiDimensionalKeyframe(LottieComposition lottieComposition, JsonReader jsonReader, float f2, ValueParser<T> valueParser) {
        Interpolator interpolator;
        Interpolator interpolatorFor;
        Interpolator interpolatorFor2;
        T t2;
        PointF pointF;
        Keyframe<T> keyframe;
        PointF pointF2;
        float f3;
        jsonReader.beginObject();
        PointF pointF3 = null;
        boolean z = false;
        PointF pointF4 = null;
        PointF pointF5 = null;
        PointF pointF6 = null;
        T t3 = null;
        PointF pointF7 = null;
        PointF pointF8 = null;
        PointF pointF9 = null;
        float f4 = 0.0f;
        PointF pointF10 = null;
        T t4 = null;
        while (jsonReader.hasNext()) {
            switch (jsonReader.selectName(f4443a)) {
                case 0:
                    pointF2 = pointF3;
                    f4 = (float) jsonReader.nextDouble();
                    break;
                case 1:
                    pointF2 = pointF3;
                    t3 = valueParser.parse(jsonReader, f2);
                    break;
                case 2:
                    pointF2 = pointF3;
                    t4 = valueParser.parse(jsonReader, f2);
                    break;
                case 3:
                    pointF2 = pointF3;
                    f3 = f4;
                    PointF pointF11 = pointF10;
                    if (jsonReader.peek() != JsonReader.Token.BEGIN_OBJECT) {
                        pointF4 = JsonUtils.b(jsonReader, f2);
                        f4 = f3;
                        pointF10 = pointF11;
                        break;
                    } else {
                        jsonReader.beginObject();
                        float f5 = 0.0f;
                        float f6 = 0.0f;
                        float f7 = 0.0f;
                        float f8 = 0.0f;
                        while (jsonReader.hasNext()) {
                            int selectName = jsonReader.selectName(f4444b);
                            if (selectName == 0) {
                                JsonReader.Token peek = jsonReader.peek();
                                JsonReader.Token token = JsonReader.Token.NUMBER;
                                if (peek == token) {
                                    f7 = (float) jsonReader.nextDouble();
                                    f5 = f7;
                                } else {
                                    jsonReader.beginArray();
                                    f5 = (float) jsonReader.nextDouble();
                                    f7 = jsonReader.peek() == token ? (float) jsonReader.nextDouble() : f5;
                                    jsonReader.endArray();
                                }
                            } else if (selectName != 1) {
                                jsonReader.skipValue();
                            } else {
                                JsonReader.Token peek2 = jsonReader.peek();
                                JsonReader.Token token2 = JsonReader.Token.NUMBER;
                                if (peek2 == token2) {
                                    f8 = (float) jsonReader.nextDouble();
                                    f6 = f8;
                                } else {
                                    jsonReader.beginArray();
                                    f6 = (float) jsonReader.nextDouble();
                                    f8 = jsonReader.peek() == token2 ? (float) jsonReader.nextDouble() : f6;
                                    jsonReader.endArray();
                                }
                            }
                        }
                        PointF pointF12 = new PointF(f5, f6);
                        PointF pointF13 = new PointF(f7, f8);
                        jsonReader.endObject();
                        pointF7 = pointF13;
                        pointF6 = pointF12;
                        pointF10 = pointF11;
                        f4 = f3;
                        break;
                    }
                case 4:
                    if (jsonReader.peek() != JsonReader.Token.BEGIN_OBJECT) {
                        pointF2 = pointF3;
                        pointF5 = JsonUtils.b(jsonReader, f2);
                        break;
                    } else {
                        jsonReader.beginObject();
                        float f9 = 0.0f;
                        float f10 = 0.0f;
                        float f11 = 0.0f;
                        float f12 = 0.0f;
                        while (jsonReader.hasNext()) {
                            PointF pointF14 = pointF10;
                            int selectName2 = jsonReader.selectName(f4444b);
                            PointF pointF15 = pointF3;
                            if (selectName2 == 0) {
                                float f13 = f4;
                                JsonReader.Token peek3 = jsonReader.peek();
                                JsonReader.Token token3 = JsonReader.Token.NUMBER;
                                if (peek3 == token3) {
                                    f11 = (float) jsonReader.nextDouble();
                                    f4 = f13;
                                    f9 = f11;
                                } else {
                                    jsonReader.beginArray();
                                    f9 = (float) jsonReader.nextDouble();
                                    f11 = jsonReader.peek() == token3 ? (float) jsonReader.nextDouble() : f9;
                                    jsonReader.endArray();
                                    f4 = f13;
                                }
                            } else if (selectName2 != 1) {
                                jsonReader.skipValue();
                            } else {
                                JsonReader.Token peek4 = jsonReader.peek();
                                JsonReader.Token token4 = JsonReader.Token.NUMBER;
                                if (peek4 == token4) {
                                    f12 = (float) jsonReader.nextDouble();
                                    f4 = f4;
                                    f10 = f12;
                                } else {
                                    float f14 = f4;
                                    jsonReader.beginArray();
                                    float nextDouble = (float) jsonReader.nextDouble();
                                    float nextDouble2 = jsonReader.peek() == token4 ? (float) jsonReader.nextDouble() : nextDouble;
                                    jsonReader.endArray();
                                    f4 = f14;
                                    pointF10 = pointF14;
                                    pointF3 = pointF15;
                                    f12 = nextDouble2;
                                    f10 = nextDouble;
                                }
                            }
                            pointF10 = pointF14;
                            pointF3 = pointF15;
                        }
                        pointF2 = pointF3;
                        f3 = f4;
                        PointF pointF16 = new PointF(f9, f10);
                        PointF pointF17 = new PointF(f11, f12);
                        jsonReader.endObject();
                        pointF9 = pointF17;
                        pointF8 = pointF16;
                        f4 = f3;
                        break;
                    }
                case 5:
                    if (jsonReader.nextInt() == 1) {
                        z = true;
                    } else {
                        z = false;
                        continue;
                    }
                case 6:
                    pointF10 = JsonUtils.b(jsonReader, f2);
                    continue;
                case 7:
                    pointF3 = JsonUtils.b(jsonReader, f2);
                    continue;
                default:
                    pointF2 = pointF3;
                    jsonReader.skipValue();
                    break;
            }
            pointF3 = pointF2;
        }
        PointF pointF18 = pointF3;
        float f15 = f4;
        PointF pointF19 = pointF10;
        jsonReader.endObject();
        if (z) {
            interpolator = LINEAR_INTERPOLATOR;
            t2 = t3;
        } else {
            if (pointF4 != null && pointF5 != null) {
                interpolator = interpolatorFor(pointF4, pointF5);
            } else if (pointF6 != null && pointF7 != null && pointF8 != null && pointF9 != null) {
                interpolatorFor = interpolatorFor(pointF6, pointF8);
                interpolatorFor2 = interpolatorFor(pointF7, pointF9);
                t2 = t4;
                interpolator = null;
                if (interpolatorFor != null || interpolatorFor2 == null) {
                    pointF = pointF19;
                    keyframe = new Keyframe<>(lottieComposition, t3, t2, interpolator, f15, null);
                } else {
                    pointF = pointF19;
                    keyframe = new Keyframe<>(lottieComposition, t3, t2, interpolatorFor, interpolatorFor2, f15, null);
                }
                keyframe.pathCp1 = pointF;
                keyframe.pathCp2 = pointF18;
                return keyframe;
            } else {
                interpolator = LINEAR_INTERPOLATOR;
            }
            t2 = t4;
        }
        interpolatorFor = null;
        interpolatorFor2 = null;
        if (interpolatorFor != null) {
        }
        pointF = pointF19;
        keyframe = new Keyframe<>(lottieComposition, t3, t2, interpolator, f15, null);
        keyframe.pathCp1 = pointF;
        keyframe.pathCp2 = pointF18;
        return keyframe;
    }

    private static <T> Keyframe<T> parseStaticValue(JsonReader jsonReader, float f2, ValueParser<T> valueParser) {
        return new Keyframe<>(valueParser.parse(jsonReader, f2));
    }

    private static SparseArrayCompat<WeakReference<Interpolator>> pathInterpolatorCache() {
        if (pathInterpolatorCache == null) {
            pathInterpolatorCache = new SparseArrayCompat<>();
        }
        return pathInterpolatorCache;
    }

    private static void putInterpolator(int i2, WeakReference<Interpolator> weakReference) {
        synchronized (KeyframeParser.class) {
            pathInterpolatorCache.put(i2, weakReference);
        }
    }
}
