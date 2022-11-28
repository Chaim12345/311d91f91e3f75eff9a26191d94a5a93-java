package com.bumptech.glide.load.engine;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DataFetcherGenerator;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.util.LogTime;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SourceGenerator implements DataFetcherGenerator, DataFetcherGenerator.FetcherReadyCallback {
    private static final String TAG = "SourceGenerator";
    private final DataFetcherGenerator.FetcherReadyCallback cb;
    private volatile Object dataToCache;
    private final DecodeHelper<?> helper;
    private volatile ModelLoader.LoadData<?> loadData;
    private volatile int loadDataListIndex;
    private volatile DataCacheKey originalKey;
    private volatile DataCacheGenerator sourceCacheGenerator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SourceGenerator(DecodeHelper decodeHelper, DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback) {
        this.helper = decodeHelper;
        this.cb = fetcherReadyCallback;
    }

    private boolean cacheData(Object obj) {
        long logTime = LogTime.getLogTime();
        boolean z = true;
        try {
            DataRewinder o2 = this.helper.o(obj);
            Object rewindAndGet = o2.rewindAndGet();
            Encoder q2 = this.helper.q(rewindAndGet);
            DataCacheWriter dataCacheWriter = new DataCacheWriter(q2, rewindAndGet, this.helper.k());
            DataCacheKey dataCacheKey = new DataCacheKey(this.loadData.sourceKey, this.helper.p());
            DiskCache d2 = this.helper.d();
            d2.put(dataCacheKey, dataCacheWriter);
            if (Log.isLoggable(TAG, 2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Finished encoding source to cache, key: ");
                sb.append(dataCacheKey);
                sb.append(", data: ");
                sb.append(obj);
                sb.append(", encoder: ");
                sb.append(q2);
                sb.append(", duration: ");
                sb.append(LogTime.getElapsedMillis(logTime));
            }
            if (d2.get(dataCacheKey) != null) {
                this.originalKey = dataCacheKey;
                this.sourceCacheGenerator = new DataCacheGenerator(Collections.singletonList(this.loadData.sourceKey), this.helper, this);
                this.loadData.fetcher.cleanup();
                return true;
            }
            if (Log.isLoggable(TAG, 3)) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Attempt to write: ");
                sb2.append(this.originalKey);
                sb2.append(", data: ");
                sb2.append(obj);
                sb2.append(" to the disk cache failed, maybe the disk cache is disabled? Trying to decode the data directly...");
            }
            try {
                this.cb.onDataFetcherReady(this.loadData.sourceKey, o2.rewindAndGet(), this.loadData.fetcher, this.loadData.fetcher.getDataSource(), this.loadData.sourceKey);
                return false;
            } catch (Throwable th) {
                th = th;
                if (!z) {
                    this.loadData.fetcher.cleanup();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
        }
    }

    private boolean hasNextModelLoader() {
        return this.loadDataListIndex < this.helper.g().size();
    }

    private void startNextLoad(final ModelLoader.LoadData<?> loadData) {
        this.loadData.fetcher.loadData(this.helper.l(), new DataFetcher.DataCallback<Object>() { // from class: com.bumptech.glide.load.engine.SourceGenerator.1
            @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
            public void onDataReady(@Nullable Object obj) {
                if (SourceGenerator.this.a(loadData)) {
                    SourceGenerator.this.b(loadData, obj);
                }
            }

            @Override // com.bumptech.glide.load.data.DataFetcher.DataCallback
            public void onLoadFailed(@NonNull Exception exc) {
                if (SourceGenerator.this.a(loadData)) {
                    SourceGenerator.this.c(loadData, exc);
                }
            }
        });
    }

    boolean a(ModelLoader.LoadData loadData) {
        ModelLoader.LoadData<?> loadData2 = this.loadData;
        return loadData2 != null && loadData2 == loadData;
    }

    void b(ModelLoader.LoadData loadData, Object obj) {
        DiskCacheStrategy e2 = this.helper.e();
        if (obj != null && e2.isDataCacheable(loadData.fetcher.getDataSource())) {
            this.dataToCache = obj;
            this.cb.reschedule();
            return;
        }
        DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback = this.cb;
        Key key = loadData.sourceKey;
        DataFetcher<Data> dataFetcher = loadData.fetcher;
        fetcherReadyCallback.onDataFetcherReady(key, obj, dataFetcher, dataFetcher.getDataSource(), this.originalKey);
    }

    void c(ModelLoader.LoadData loadData, @NonNull Exception exc) {
        DataFetcherGenerator.FetcherReadyCallback fetcherReadyCallback = this.cb;
        Key key = this.originalKey;
        DataFetcher<Data> dataFetcher = loadData.fetcher;
        fetcherReadyCallback.onDataFetcherFailed(key, exc, dataFetcher, dataFetcher.getDataSource());
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public void cancel() {
        ModelLoader.LoadData<?> loadData = this.loadData;
        if (loadData != null) {
            loadData.fetcher.cancel();
        }
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherFailed(Key key, Exception exc, DataFetcher<?> dataFetcher, DataSource dataSource) {
        this.cb.onDataFetcherFailed(key, exc, dataFetcher, this.loadData.fetcher.getDataSource());
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void onDataFetcherReady(Key key, Object obj, DataFetcher<?> dataFetcher, DataSource dataSource, Key key2) {
        this.cb.onDataFetcherReady(key, obj, dataFetcher, this.loadData.fetcher.getDataSource(), key);
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator.FetcherReadyCallback
    public void reschedule() {
        throw new UnsupportedOperationException();
    }

    @Override // com.bumptech.glide.load.engine.DataFetcherGenerator
    public boolean startNext() {
        if (this.dataToCache != null) {
            Object obj = this.dataToCache;
            this.dataToCache = null;
            try {
                if (!cacheData(obj)) {
                    return true;
                }
            } catch (IOException unused) {
                Log.isLoggable(TAG, 3);
            }
        }
        if (this.sourceCacheGenerator == null || !this.sourceCacheGenerator.startNext()) {
            this.sourceCacheGenerator = null;
            this.loadData = null;
            boolean z = false;
            while (!z && hasNextModelLoader()) {
                List g2 = this.helper.g();
                int i2 = this.loadDataListIndex;
                this.loadDataListIndex = i2 + 1;
                this.loadData = (ModelLoader.LoadData) g2.get(i2);
                if (this.loadData != null && (this.helper.e().isDataCacheable(this.loadData.fetcher.getDataSource()) || this.helper.u(this.loadData.fetcher.getDataClass()))) {
                    startNextLoad(this.loadData);
                    z = true;
                }
            }
            return z;
        }
        return true;
    }
}
