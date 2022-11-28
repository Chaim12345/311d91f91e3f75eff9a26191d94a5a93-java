package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.value.Keyframe;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
abstract class BaseAnimatableValue<V, O> implements AnimatableValue<V, O> {

    /* renamed from: a  reason: collision with root package name */
    final List f4430a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseAnimatableValue(Object obj) {
        this(Collections.singletonList(new Keyframe(obj)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseAnimatableValue(List list) {
        this.f4430a = list;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public List<Keyframe<V>> getKeyframes() {
        return this.f4430a;
    }

    @Override // com.airbnb.lottie.model.animatable.AnimatableValue
    public boolean isStatic() {
        return this.f4430a.isEmpty() || (this.f4430a.size() == 1 && ((Keyframe) this.f4430a.get(0)).isStatic());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f4430a.isEmpty()) {
            sb.append("values=");
            sb.append(Arrays.toString(this.f4430a.toArray()));
        }
        return sb.toString();
    }
}
