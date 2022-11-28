package com.google.android.material.transition;
/* loaded from: classes2.dex */
class FadeModeEvaluators {
    private static final FadeModeEvaluator IN = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.1
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            return FadeModeResult.a(255, TransitionUtils.l(0, 255, f3, f4, f2));
        }
    };
    private static final FadeModeEvaluator OUT = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.2
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            return FadeModeResult.b(TransitionUtils.l(255, 0, f3, f4, f2), 255);
        }
    };
    private static final FadeModeEvaluator CROSS = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.3
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            return FadeModeResult.b(TransitionUtils.l(255, 0, f3, f4, f2), TransitionUtils.l(0, 255, f3, f4, f2));
        }
    };
    private static final FadeModeEvaluator THROUGH = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.4
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f3, float f4, float f5) {
            float f6 = ((f4 - f3) * f5) + f3;
            return FadeModeResult.b(TransitionUtils.l(255, 0, f3, f6, f2), TransitionUtils.l(0, 255, f6, f4, f2));
        }
    };

    private FadeModeEvaluators() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FadeModeEvaluator a(int i2, boolean z) {
        if (i2 == 0) {
            return z ? IN : OUT;
        } else if (i2 == 1) {
            return z ? OUT : IN;
        } else if (i2 != 2) {
            if (i2 == 3) {
                return THROUGH;
            }
            throw new IllegalArgumentException("Invalid fade mode: " + i2);
        } else {
            return CROSS;
        }
    }
}
