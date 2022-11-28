package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.utils.ObjectPool.Poolable;
import java.util.List;
/* loaded from: classes.dex */
public class ObjectPool<T extends Poolable> {
    private static int ids;
    private int desiredCapacity;
    private T modelObject;
    private Object[] objects;
    private int objectsPointer;
    private int poolId;
    private float replenishPercentage;

    /* loaded from: classes.dex */
    public static abstract class Poolable {
        public static int NO_OWNER = -1;

        /* renamed from: a  reason: collision with root package name */
        int f5461a = NO_OWNER;

        protected abstract Poolable a();
    }

    private ObjectPool(int i2, T t2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("Object Pool must be instantiated with a capacity greater than 0!");
        }
        this.desiredCapacity = i2;
        this.objects = new Object[i2];
        this.objectsPointer = 0;
        this.modelObject = t2;
        this.replenishPercentage = 1.0f;
        refillPool();
    }

    public static synchronized ObjectPool create(int i2, Poolable poolable) {
        ObjectPool objectPool;
        synchronized (ObjectPool.class) {
            objectPool = new ObjectPool(i2, poolable);
            int i3 = ids;
            objectPool.poolId = i3;
            ids = i3 + 1;
        }
        return objectPool;
    }

    private void refillPool() {
        refillPool(this.replenishPercentage);
    }

    private void refillPool(float f2) {
        int i2 = this.desiredCapacity;
        int i3 = (int) (i2 * f2);
        if (i3 < 1) {
            i2 = 1;
        } else if (i3 <= i2) {
            i2 = i3;
        }
        for (int i4 = 0; i4 < i2; i4++) {
            this.objects[i4] = this.modelObject.a();
        }
        this.objectsPointer = i2 - 1;
    }

    private void resizePool() {
        int i2 = this.desiredCapacity;
        int i3 = i2 * 2;
        this.desiredCapacity = i3;
        Object[] objArr = new Object[i3];
        for (int i4 = 0; i4 < i2; i4++) {
            objArr[i4] = this.objects[i4];
        }
        this.objects = objArr;
    }

    public synchronized T get() {
        T t2;
        if (this.objectsPointer == -1 && this.replenishPercentage > 0.0f) {
            refillPool();
        }
        Object[] objArr = this.objects;
        int i2 = this.objectsPointer;
        t2 = (T) objArr[i2];
        t2.f5461a = Poolable.NO_OWNER;
        this.objectsPointer = i2 - 1;
        return t2;
    }

    public int getPoolCapacity() {
        return this.objects.length;
    }

    public int getPoolCount() {
        return this.objectsPointer + 1;
    }

    public int getPoolId() {
        return this.poolId;
    }

    public float getReplenishPercentage() {
        return this.replenishPercentage;
    }

    public synchronized void recycle(T t2) {
        int i2 = t2.f5461a;
        if (i2 != Poolable.NO_OWNER) {
            if (i2 == this.poolId) {
                throw new IllegalArgumentException("The object passed is already stored in this pool!");
            }
            throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t2.f5461a + ".  Object cannot belong to two different pool instances simultaneously!");
        }
        int i3 = this.objectsPointer + 1;
        this.objectsPointer = i3;
        if (i3 >= this.objects.length) {
            resizePool();
        }
        t2.f5461a = this.poolId;
        this.objects[this.objectsPointer] = t2;
    }

    public synchronized void recycle(List<T> list) {
        while (list.size() + this.objectsPointer + 1 > this.desiredCapacity) {
            resizePool();
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            T t2 = list.get(i2);
            int i3 = t2.f5461a;
            if (i3 != Poolable.NO_OWNER) {
                if (i3 == this.poolId) {
                    throw new IllegalArgumentException("The object passed is already stored in this pool!");
                }
                throw new IllegalArgumentException("The object to recycle already belongs to poolId " + t2.f5461a + ".  Object cannot belong to two different pool instances simultaneously!");
            }
            t2.f5461a = this.poolId;
            this.objects[this.objectsPointer + 1 + i2] = t2;
        }
        this.objectsPointer += size;
    }

    public void setReplenishPercentage(float f2) {
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        this.replenishPercentage = f2;
    }
}
