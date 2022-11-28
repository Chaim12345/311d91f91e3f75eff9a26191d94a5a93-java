package org.bouncycastle.math.field;

import java.math.BigInteger;
import org.bouncycastle.util.Integers;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public class GenericPolynomialExtensionField implements PolynomialExtensionField {

    /* renamed from: a  reason: collision with root package name */
    protected final FiniteField f14354a;

    /* renamed from: b  reason: collision with root package name */
    protected final Polynomial f14355b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericPolynomialExtensionField(FiniteField finiteField, Polynomial polynomial) {
        this.f14354a = finiteField;
        this.f14355b = polynomial;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GenericPolynomialExtensionField) {
            GenericPolynomialExtensionField genericPolynomialExtensionField = (GenericPolynomialExtensionField) obj;
            return this.f14354a.equals(genericPolynomialExtensionField.f14354a) && this.f14355b.equals(genericPolynomialExtensionField.f14355b);
        }
        return false;
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.f14354a.getCharacteristic();
    }

    @Override // org.bouncycastle.math.field.ExtensionField
    public int getDegree() {
        return this.f14355b.getDegree();
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public int getDimension() {
        return this.f14354a.getDimension() * this.f14355b.getDegree();
    }

    @Override // org.bouncycastle.math.field.PolynomialExtensionField
    public Polynomial getMinimalPolynomial() {
        return this.f14355b;
    }

    @Override // org.bouncycastle.math.field.ExtensionField
    public FiniteField getSubfield() {
        return this.f14354a;
    }

    public int hashCode() {
        return this.f14354a.hashCode() ^ Integers.rotateLeft(this.f14355b.hashCode(), 16);
    }
}
