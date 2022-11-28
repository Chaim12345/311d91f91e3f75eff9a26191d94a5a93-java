package com.airbnb.lottie.model.layer;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class Layer {
    private final LottieComposition composition;
    private final boolean hidden;
    private final List<Keyframe<Float>> inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List<Mask> masks;
    private final MatteType matteType;
    private final long parentId;
    private final int preCompHeight;
    private final int preCompWidth;
    @Nullable
    private final String refId;
    private final List<ContentModel> shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    @Nullable
    private final AnimatableTextFrame text;
    @Nullable
    private final AnimatableTextProperties textProperties;
    @Nullable
    private final AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final AnimatableTransform transform;

    /* loaded from: classes.dex */
    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    /* loaded from: classes.dex */
    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        LUMA,
        LUMA_INVERTED,
        UNKNOWN
    }

    public Layer(List<ContentModel> list, LottieComposition lottieComposition, String str, long j2, LayerType layerType, long j3, @Nullable String str2, List<Mask> list2, AnimatableTransform animatableTransform, int i2, int i3, int i4, float f2, float f3, int i5, int i6, @Nullable AnimatableTextFrame animatableTextFrame, @Nullable AnimatableTextProperties animatableTextProperties, List<Keyframe<Float>> list3, MatteType matteType, @Nullable AnimatableFloatValue animatableFloatValue, boolean z) {
        this.shapes = list;
        this.composition = lottieComposition;
        this.layerName = str;
        this.layerId = j2;
        this.layerType = layerType;
        this.parentId = j3;
        this.refId = str2;
        this.masks = list2;
        this.transform = animatableTransform;
        this.solidWidth = i2;
        this.solidHeight = i3;
        this.solidColor = i4;
        this.timeStretch = f2;
        this.startFrame = f3;
        this.preCompWidth = i5;
        this.preCompHeight = i6;
        this.text = animatableTextFrame;
        this.textProperties = animatableTextProperties;
        this.inOutKeyframes = list3;
        this.matteType = matteType;
        this.timeRemapping = animatableFloatValue;
        this.hidden = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LottieComposition a() {
        return this.composition;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List b() {
        return this.inOutKeyframes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List c() {
        return this.masks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MatteType d() {
        return this.matteType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String e() {
        return this.layerName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long f() {
        return this.parentId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int g() {
        return this.preCompHeight;
    }

    public long getId() {
        return this.layerId;
    }

    public LayerType getLayerType() {
        return this.layerType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int h() {
        return this.preCompWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String i() {
        return this.refId;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List j() {
        return this.shapes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int k() {
        return this.solidColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int l() {
        return this.solidHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int m() {
        return this.solidWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float n() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatableTextFrame o() {
        return this.text;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatableTextProperties p() {
        return this.textProperties;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatableFloatValue q() {
        return this.timeRemapping;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float r() {
        return this.timeStretch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatableTransform s() {
        return this.transform;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(e());
        sb.append("\n");
        Layer layerModelForId = this.composition.layerModelForId(f());
        if (layerModelForId != null) {
            String str2 = "\t\tParents: ";
            while (true) {
                sb.append(str2);
                sb.append(layerModelForId.e());
                layerModelForId = this.composition.layerModelForId(layerModelForId.f());
                if (layerModelForId == null) {
                    break;
                }
                str2 = "->";
            }
            sb.append(str);
            sb.append("\n");
        }
        if (!c().isEmpty()) {
            sb.append(str);
            sb.append("\tMasks: ");
            sb.append(c().size());
            sb.append("\n");
        }
        if (m() != 0 && l() != 0) {
            sb.append(str);
            sb.append("\tBackground: ");
            sb.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(m()), Integer.valueOf(l()), Integer.valueOf(k())));
        }
        if (!this.shapes.isEmpty()) {
            sb.append(str);
            sb.append("\tShapes:\n");
            for (ContentModel contentModel : this.shapes) {
                sb.append(str);
                sb.append("\t\t");
                sb.append(contentModel);
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
