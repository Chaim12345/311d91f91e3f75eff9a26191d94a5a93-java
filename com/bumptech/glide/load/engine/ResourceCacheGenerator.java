package com.bumptech.glide.load.engine;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.pool.GlideTrace;
import java.io.File;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class ResourceCacheGenerator implements DataFetcherGenerator, DataFetcher.DataCallback<Object> {
    private File cacheFile;
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private ResourceCacheKey currentKey;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private int modelLoaderIndex;
    private List<ModelLoader<File, ?>> modelLoaders;
    private int resourceClassIndex = -1;
    private int sourceIdIndex;
    private Key sourceKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResourceCacheGenerator(DecodeHelper decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.cb = fetcherReadyCallback;
    }

    private boolean hasNextModelLoader() {
        return this.modelLoaderIndex < this.modelLoaders.size();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.loadData;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onDataReady(Object obj) {
        this.cb.onDataFetcherReady(this.sourceKey, obj, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE, this.currentKey);
    }

    @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
    public void onLoadFailed(@NonNull Exception exc) {
        this.cb.onDataFetcherFailed(this.currentKey, exc, this.loadData.fetcher, DataSource.RESOURCE_DISK_CACHE);
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        GlideTrace.beginSection("ResourceCacheGenerator.startNext");
        try {
            List c2 = this.helper.c();
            boolean z = false;
            if (c2.isEmpty()) {
                return false;
            }
            List m2 = this.helper.m();
            if (m2.isEmpty()) {
                if (File.class.equals(this.helper.r())) {
                    return false;
                }
                throw new IllegalStateException("Failed to find any load path from " + this.helper.i() + " to " + this.helper.r());
            }
            while (true) {
                if (this.modelLoaders != null && hasNextModelLoader()) {
                    this.loadData = null;
                    while (!z && hasNextModelLoader()) {
                        List<ModelLoader<File, ?>> list = this.modelLoaders;
                        int i2 = this.modelLoaderIndex;
                        this.modelLoaderIndex = i2 + 1;
                        this.loadData = list.get(i2).buildLoadData(this.cacheFile, this.helper.t(), this.helper.f(), this.helper.k());
                        if (this.loadData != null && this.helper.u(this.loadData.fetcher.getDataClass())) {
                            this.loadData.fetcher.loadData(this.helper.l(), this);
                            z = true;
                        }
                    }
                    return z;
                }
                int i3 = this.resourceClassIndex + 1;
                this.resourceClassIndex = i3;
                if (i3 >= m2.size()) {
                    int i4 = this.sourceIdIndex + 1;
                    this.sourceIdIndex = i4;
                    if (i4 >= c2.size()) {
                        return false;
                    }
                    this.resourceClassIndex = 0;
                }
                Key key = (Key) c2.get(this.sourceIdIndex);
                Class cls = (Class) m2.get(this.resourceClassIndex);
                this.currentKey = new ResourceCacheKey(this.helper.b(), key, this.helper.p(), this.helper.t(), this.helper.f(), this.helper.s(cls), cls, this.helper.k());
                File file = this.helper.d().get(this.currentKey);
                this.cacheFile = file;
                if (file != null) {
                    this.sourceKey = key;
                    this.modelLoaders = this.helper.j(file);
                    this.modelLoaderIndex = 0;
                }
            }
        } finally {
            GlideTrace.endSection();
        }
    }
}
