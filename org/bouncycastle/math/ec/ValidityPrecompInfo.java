package org.bouncycastle.math.ec;
/* loaded from: classes3.dex */
class ValidityPrecompInfo implements PreCompInfo {
    private boolean failed = false;
    private boolean curveEquationPassed = false;
    private boolean orderPassed = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean a() {
        return this.curveEquationPassed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return this.failed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c() {
        return this.orderPassed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void d() {
        this.curveEquationPassed = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e() {
        this.failed = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f() {
        this.orderPassed = true;
    }
}
