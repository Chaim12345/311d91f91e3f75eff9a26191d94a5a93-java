package androidx.constraintlayout.core;

import androidx.constraintlayout.core.Pools;
/* loaded from: classes.dex */
public class Cache {

    /* renamed from: a  reason: collision with root package name */
    Pools.Pool f1653a = new Pools.SimplePool(256);

    /* renamed from: b  reason: collision with root package name */
    Pools.Pool f1654b = new Pools.SimplePool(256);

    /* renamed from: c  reason: collision with root package name */
    Pools.Pool f1655c = new Pools.SimplePool(256);

    /* renamed from: d  reason: collision with root package name */
    SolverVariable[] f1656d = new SolverVariable[32];
}
