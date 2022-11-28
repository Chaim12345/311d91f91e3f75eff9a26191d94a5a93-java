package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Comparator;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes2.dex */
public final class GeneralRange<T> implements Serializable {
    private final Comparator<? super T> comparator;
    private final boolean hasLowerBound;
    private final boolean hasUpperBound;
    private final BoundType lowerBoundType;
    @NullableDecl
    private final T lowerEndpoint;
    @NullableDecl
    private transient GeneralRange<T> reverse;
    private final BoundType upperBoundType;
    @NullableDecl
    private final T upperEndpoint;

    /* JADX WARN: Multi-variable type inference failed */
    private GeneralRange(Comparator<? super T> comparator, boolean z, @NullableDecl T t2, BoundType boundType, boolean z2, @NullableDecl T t3, BoundType boundType2) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        this.hasLowerBound = z;
        this.hasUpperBound = z2;
        this.lowerEndpoint = t2;
        this.lowerBoundType = (BoundType) Preconditions.checkNotNull(boundType);
        this.upperEndpoint = t3;
        this.upperBoundType = (BoundType) Preconditions.checkNotNull(boundType2);
        if (z) {
            comparator.compare(t2, t2);
        }
        if (z2) {
            comparator.compare(t3, t3);
        }
        if (z && z2) {
            int compare = comparator.compare(t2, t3);
            Preconditions.checkArgument(compare <= 0, "lowerEndpoint (%s) > upperEndpoint (%s)", t2, t3);
            if (compare == 0) {
                BoundType boundType3 = BoundType.OPEN;
                Preconditions.checkArgument((boundType != boundType3) | (boundType2 != boundType3));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GeneralRange a(Comparator comparator) {
        BoundType boundType = BoundType.OPEN;
        return new GeneralRange(comparator, false, null, boundType, false, null, boundType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GeneralRange d(Comparator comparator, @NullableDecl Object obj, BoundType boundType) {
        return new GeneralRange(comparator, true, obj, boundType, false, null, BoundType.OPEN);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static GeneralRange n(Comparator comparator, @NullableDecl Object obj, BoundType boundType) {
        return new GeneralRange(comparator, false, null, BoundType.OPEN, true, obj, boundType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Comparator b() {
        return this.comparator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean c(@NullableDecl Object obj) {
        return (m(obj) || l(obj)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundType e() {
        return this.lowerBoundType;
    }

    public boolean equals(@NullableDecl Object obj) {
        if (obj instanceof GeneralRange) {
            GeneralRange generalRange = (GeneralRange) obj;
            return this.comparator.equals(generalRange.comparator) && this.hasLowerBound == generalRange.hasLowerBound && this.hasUpperBound == generalRange.hasUpperBound && e().equals(generalRange.e()) && g().equals(generalRange.g()) && Objects.equal(f(), generalRange.f()) && Objects.equal(h(), generalRange.h());
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object f() {
        return this.lowerEndpoint;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BoundType g() {
        return this.upperBoundType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object h() {
        return this.upperEndpoint;
    }

    public int hashCode() {
        return Objects.hashCode(this.comparator, f(), e(), h(), g());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean i() {
        return this.hasLowerBound;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean j() {
        return this.hasUpperBound;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0047, code lost:
        if (r12.e() == com.google.common.collect.BoundType.OPEN) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0084, code lost:
        if (r12.g() == com.google.common.collect.BoundType.OPEN) goto L8;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008b A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public GeneralRange k(GeneralRange generalRange) {
        boolean z;
        Object obj;
        BoundType boundType;
        BoundType boundType2;
        int compare;
        BoundType boundType3;
        Preconditions.checkNotNull(generalRange);
        Preconditions.checkArgument(this.comparator.equals(generalRange.comparator));
        boolean z2 = this.hasLowerBound;
        Object f2 = f();
        BoundType e2 = e();
        if (i()) {
            if (generalRange.i()) {
                int compare2 = this.comparator.compare((Object) f(), generalRange.f());
                if (compare2 >= 0) {
                    if (compare2 == 0) {
                    }
                }
            }
            z = z2;
            boolean z3 = this.hasUpperBound;
            Object h2 = h();
            BoundType g2 = g();
            if (!j()) {
                if (generalRange.j()) {
                    int compare3 = this.comparator.compare((Object) h(), generalRange.h());
                    if (compare3 <= 0) {
                        if (compare3 == 0) {
                        }
                    }
                }
                boolean z4 = z3;
                Object obj2 = h2;
                if (z || !z4 || ((compare = this.comparator.compare(f2, obj2)) <= 0 && !(compare == 0 && e2 == (boundType3 = BoundType.OPEN) && g2 == boundType3))) {
                    obj = f2;
                    boundType = e2;
                    boundType2 = g2;
                } else {
                    boundType = BoundType.OPEN;
                    boundType2 = BoundType.CLOSED;
                    obj = obj2;
                }
                return new GeneralRange(this.comparator, z, obj, boundType, z4, obj2, boundType2);
            }
            z3 = generalRange.hasUpperBound;
            h2 = generalRange.h();
            g2 = generalRange.g();
            boolean z42 = z3;
            Object obj22 = h2;
            if (z) {
            }
            obj = f2;
            boundType = e2;
            boundType2 = g2;
            return new GeneralRange(this.comparator, z, obj, boundType, z42, obj22, boundType2);
        }
        z2 = generalRange.hasLowerBound;
        f2 = generalRange.f();
        e2 = generalRange.e();
        z = z2;
        boolean z32 = this.hasUpperBound;
        Object h22 = h();
        BoundType g22 = g();
        if (!j()) {
        }
        h22 = generalRange.h();
        g22 = generalRange.g();
        boolean z422 = z32;
        Object obj222 = h22;
        if (z) {
        }
        obj = f2;
        boundType = e2;
        boundType2 = g22;
        return new GeneralRange(this.comparator, z, obj, boundType, z422, obj222, boundType2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean l(@NullableDecl Object obj) {
        if (j()) {
            int compare = this.comparator.compare(obj, h());
            return ((compare == 0) & (g() == BoundType.OPEN)) | (compare > 0);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean m(@NullableDecl Object obj) {
        if (i()) {
            int compare = this.comparator.compare(obj, f());
            return ((compare == 0) & (e() == BoundType.OPEN)) | (compare < 0);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.comparator);
        sb.append(":");
        BoundType boundType = this.lowerBoundType;
        BoundType boundType2 = BoundType.CLOSED;
        sb.append(boundType == boundType2 ? AbstractJsonLexerKt.BEGIN_LIST : '(');
        sb.append(this.hasLowerBound ? this.lowerEndpoint : "-∞");
        sb.append(AbstractJsonLexerKt.COMMA);
        sb.append(this.hasUpperBound ? this.upperEndpoint : "∞");
        sb.append(this.upperBoundType == boundType2 ? AbstractJsonLexerKt.END_LIST : ')');
        return sb.toString();
    }
}
