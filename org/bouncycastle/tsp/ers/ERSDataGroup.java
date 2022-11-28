package org.bouncycastle.tsp.ers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.operator.DigestCalculator;
/* loaded from: classes4.dex */
public class ERSDataGroup extends ERSCachingData {

    /* renamed from: a  reason: collision with root package name */
    protected List f15098a;

    public ERSDataGroup(List<ERSData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        this.f15098a = arrayList;
        arrayList.addAll(list);
    }

    public ERSDataGroup(ERSData eRSData) {
        this.f15098a = Collections.singletonList(eRSData);
    }

    public ERSDataGroup(ERSData... eRSDataArr) {
        ArrayList arrayList = new ArrayList(eRSDataArr.length);
        this.f15098a = arrayList;
        arrayList.addAll(Arrays.asList(eRSDataArr));
    }

    @Override // org.bouncycastle.tsp.ers.ERSCachingData
    protected byte[] a(DigestCalculator digestCalculator) {
        List<byte[]> hashes = getHashes(digestCalculator);
        return hashes.size() > 1 ? ERSUtil.f(digestCalculator, hashes.iterator()) : hashes.get(0);
    }

    public List<byte[]> getHashes(DigestCalculator digestCalculator) {
        return ERSUtil.a(digestCalculator, this.f15098a);
    }

    public int size() {
        return this.f15098a.size();
    }
}
