package com.github.mikephil.charting.utils;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.ObjectPool;
import java.util.List;
/* loaded from: classes.dex */
public class MPPointF extends ObjectPool.Poolable {
    public static final Parcelable.Creator<MPPointF> CREATOR;
    private static ObjectPool<MPPointF> pool;
    public float x;
    public float y;

    static {
        ObjectPool<MPPointF> create = ObjectPool.create(32, new MPPointF(0.0f, 0.0f));
        pool = create;
        create.setReplenishPercentage(0.5f);
        CREATOR = new Parcelable.Creator<MPPointF>() { // from class: com.github.mikephil.charting.utils.MPPointF.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MPPointF createFromParcel(Parcel parcel) {
                MPPointF mPPointF = new MPPointF(0.0f, 0.0f);
                mPPointF.my_readFromParcel(parcel);
                return mPPointF;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public MPPointF[] newArray(int i2) {
                return new MPPointF[i2];
            }
        };
    }

    public MPPointF() {
    }

    public MPPointF(float f2, float f3) {
        this.x = f2;
        this.y = f3;
    }

    public static MPPointF getInstance() {
        return pool.get();
    }

    public static MPPointF getInstance(float f2, float f3) {
        MPPointF mPPointF = pool.get();
        mPPointF.x = f2;
        mPPointF.y = f3;
        return mPPointF;
    }

    public static MPPointF getInstance(MPPointF mPPointF) {
        MPPointF mPPointF2 = pool.get();
        mPPointF2.x = mPPointF.x;
        mPPointF2.y = mPPointF.y;
        return mPPointF2;
    }

    public static void recycleInstance(MPPointF mPPointF) {
        pool.recycle((ObjectPool<MPPointF>) mPPointF);
    }

    public static void recycleInstances(List<MPPointF> list) {
        pool.recycle(list);
    }

    @Override // com.github.mikephil.charting.utils.ObjectPool.Poolable
    protected ObjectPool.Poolable a() {
        return new MPPointF(0.0f, 0.0f);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void my_readFromParcel(Parcel parcel) {
        this.x = parcel.readFloat();
        this.y = parcel.readFloat();
    }
}
