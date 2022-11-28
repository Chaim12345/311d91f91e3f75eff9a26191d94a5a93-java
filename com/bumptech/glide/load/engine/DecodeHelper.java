package com.bumptech.glide.load.engine;

import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.Encoder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceEncoder;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.data.DataRewinder;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.UnitTransformation;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class DecodeHelper<Transcode> {
    private DecodeJob.DiskCacheProvider diskCacheProvider;
    private DiskCacheStrategy diskCacheStrategy;
    private GlideContext glideContext;
    private int height;
    private boolean isCacheKeysSet;
    private boolean isLoadDataSet;
    private boolean isScaleOnlyOrNoTransform;
    private boolean isTransformationRequired;
    private Object model;
    private Options options;
    private Priority priority;
    private Class<?> resourceClass;
    private Key signature;
    private Class<Transcode> transcodeClass;
    private Map<Class<?>, Transformation<?>> transformations;
    private int width;
    private final List<ModelLoader.LoadData<?>> loadData = new ArrayList();
    private final List<Key> cacheKeys = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        this.glideContext = null;
        this.model = null;
        this.signature = null;
        this.resourceClass = null;
        this.transcodeClass = null;
        this.options = null;
        this.priority = null;
        this.transformations = null;
        this.diskCacheStrategy = null;
        this.loadData.clear();
        this.isLoadDataSet = false;
        this.cacheKeys.clear();
        this.isCacheKeysSet = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ArrayPool b() {
        return this.glideContext.getArrayPool();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List c() {
        if (!this.isCacheKeysSet) {
            this.isCacheKeysSet = true;
            this.cacheKeys.clear();
            List g2 = g();
            int size = g2.size();
            for (int i2 = 0; i2 < size; i2++) {
                ModelLoader.LoadData loadData = (ModelLoader.LoadData) g2.get(i2);
                if (!this.cacheKeys.contains(loadData.sourceKey)) {
                    this.cacheKeys.add(loadData.sourceKey);
                }
                for (int i3 = 0; i3 < loadData.alternateKeys.size(); i3++) {
                    if (!this.cacheKeys.contains(loadData.alternateKeys.get(i3))) {
                        this.cacheKeys.add(loadData.alternateKeys.get(i3));
                    }
                }
            }
        }
        return this.cacheKeys;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiskCache d() {
        return this.diskCacheProvider.getDiskCache();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DiskCacheStrategy e() {
        return this.diskCacheStrategy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int f() {
        return this.height;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List g() {
        if (!this.isLoadDataSet) {
            this.isLoadDataSet = true;
            this.loadData.clear();
            List modelLoaders = this.glideContext.getRegistry().getModelLoaders(this.model);
            int size = modelLoaders.size();
            for (int i2 = 0; i2 < size; i2++) {
                ModelLoader.LoadData<?> buildLoadData = ((ModelLoader) modelLoaders.get(i2)).buildLoadData(this.model, this.width, this.height, this.options);
                if (buildLoadData != null) {
                    this.loadData.add(buildLoadData);
                }
            }
        }
        return this.loadData;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoadPath h(Class cls) {
        return this.glideContext.getRegistry().getLoadPath(cls, this.resourceClass, this.transcodeClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class i() {
        return this.model.getClass();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List j(File file) {
        return this.glideContext.getRegistry().getModelLoaders(file);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Options k() {
        return this.options;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Priority l() {
        return this.priority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List m() {
        return this.glideContext.getRegistry().getRegisteredResourceClasses(this.model.getClass(), this.resourceClass, this.transcodeClass);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ResourceEncoder n(Resource resource) {
        return this.glideContext.getRegistry().getResultEncoder(resource);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DataRewinder o(Object obj) {
        return this.glideContext.getRegistry().getRewinder(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Key p() {
        return this.signature;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Encoder q(Object obj) {
        return this.glideContext.getRegistry().getSourceEncoder(obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class r() {
        return this.transcodeClass;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Transformation s(Class cls) {
        Transformation<?> transformation = this.transformations.get(cls);
        if (transformation == null) {
            Iterator<Map.Entry<Class<?>, Transformation<?>>> it = this.transformations.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<Class<?>, Transformation<?>> next = it.next();
                if (next.getKey().isAssignableFrom(cls)) {
                    transformation = next.getValue();
                    break;
                }
            }
        }
        if (transformation == null) {
            if (this.transformations.isEmpty() && this.isTransformationRequired) {
                throw new IllegalArgumentException("Missing transformation for " + cls + ". If you wish to ignore unknown resource types, use the optional transformation methods.");
            }
            return UnitTransformation.get();
        }
        return transformation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int t() {
        return this.width;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean u(Class cls) {
        return h(cls) != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void v(GlideContext glideContext, Object obj, Key key, int i2, int i3, DiskCacheStrategy diskCacheStrategy, Class cls, Class cls2, Priority priority, Options options, Map map, boolean z, boolean z2, DecodeJob.DiskCacheProvider diskCacheProvider) {
        this.glideContext = glideContext;
        this.model = obj;
        this.signature = key;
        this.width = i2;
        this.height = i3;
        this.diskCacheStrategy = diskCacheStrategy;
        this.resourceClass = cls;
        this.diskCacheProvider = diskCacheProvider;
        this.transcodeClass = cls2;
        this.priority = priority;
        this.options = options;
        this.transformations = map;
        this.isTransformationRequired = z;
        this.isScaleOnlyOrNoTransform = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean w(Resource resource) {
        return this.glideContext.getRegistry().isResourceEncoderAvailable(resource);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean x() {
        return this.isScaleOnlyOrNoTransform;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean y(Key key) {
        List g2 = g();
        int size = g2.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((ModelLoader.LoadData) g2.get(i2)).sourceKey.equals(key)) {
                return true;
            }
        }
        return false;
    }
}
