package androidx.camera.core.impl;

import android.util.ArrayMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.Config;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/* loaded from: classes.dex */
public class OptionsBundle implements Config {
    private static final OptionsBundle EMPTY_BUNDLE;

    /* renamed from: b  reason: collision with root package name */
    protected static final Comparator f1142b;

    /* renamed from: a  reason: collision with root package name */
    protected final TreeMap f1143a;

    static {
        l lVar = l.f1174a;
        f1142b = lVar;
        EMPTY_BUNDLE = new OptionsBundle(new TreeMap(lVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OptionsBundle(TreeMap treeMap) {
        this.f1143a = treeMap;
    }

    @NonNull
    public static OptionsBundle emptyBundle() {
        return EMPTY_BUNDLE;
    }

    @NonNull
    public static OptionsBundle from(@NonNull Config config) {
        if (OptionsBundle.class.equals(config.getClass())) {
            return (OptionsBundle) config;
        }
        TreeMap treeMap = new TreeMap(f1142b);
        for (Config.Option<?> option : config.listOptions()) {
            Set<Config.OptionPriority> priorities = config.getPriorities(option);
            ArrayMap arrayMap = new ArrayMap();
            for (Config.OptionPriority optionPriority : priorities) {
                arrayMap.put(optionPriority, config.retrieveOptionWithPriority(option, optionPriority));
            }
            treeMap.put(option, arrayMap);
        }
        return new OptionsBundle(treeMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$static$0(Config.Option option, Config.Option option2) {
        return option.getId().compareTo(option2.getId());
    }

    @Override // androidx.camera.core.impl.Config
    public boolean containsOption(@NonNull Config.Option<?> option) {
        return this.f1143a.containsKey(option);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x001a  */
    @Override // androidx.camera.core.impl.Config
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void findOptions(@NonNull String str, @NonNull Config.OptionMatcher optionMatcher) {
        for (Map.Entry entry : this.f1143a.tailMap(Config.Option.create(str, Void.class)).entrySet()) {
            if (!((Config.Option) entry.getKey()).getId().startsWith(str) || !optionMatcher.onOptionMatched((Config.Option) entry.getKey())) {
                return;
            }
            while (r0.hasNext()) {
            }
        }
    }

    @Override // androidx.camera.core.impl.Config
    @NonNull
    public Config.OptionPriority getOptionPriority(@NonNull Config.Option<?> option) {
        Map map = (Map) this.f1143a.get(option);
        if (map != null) {
            return (Config.OptionPriority) Collections.min(map.keySet());
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    @Override // androidx.camera.core.impl.Config
    @NonNull
    public Set<Config.OptionPriority> getPriorities(@NonNull Config.Option<?> option) {
        Map map = (Map) this.f1143a.get(option);
        return map == null ? Collections.emptySet() : Collections.unmodifiableSet(map.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    @NonNull
    public Set<Config.Option<?>> listOptions() {
        return Collections.unmodifiableSet(this.f1143a.keySet());
    }

    @Override // androidx.camera.core.impl.Config
    @Nullable
    public <ValueT> ValueT retrieveOption(@NonNull Config.Option<ValueT> option) {
        Map map = (Map) this.f1143a.get(option);
        if (map != null) {
            return (ValueT) map.get((Config.OptionPriority) Collections.min(map.keySet()));
        }
        throw new IllegalArgumentException("Option does not exist: " + option);
    }

    @Override // androidx.camera.core.impl.Config
    @Nullable
    public <ValueT> ValueT retrieveOption(@NonNull Config.Option<ValueT> option, @Nullable ValueT valuet) {
        try {
            return (ValueT) retrieveOption(option);
        } catch (IllegalArgumentException unused) {
            return valuet;
        }
    }

    @Override // androidx.camera.core.impl.Config
    @Nullable
    public <ValueT> ValueT retrieveOptionWithPriority(@NonNull Config.Option<ValueT> option, @NonNull Config.OptionPriority optionPriority) {
        Map map = (Map) this.f1143a.get(option);
        if (map == null) {
            throw new IllegalArgumentException("Option does not exist: " + option);
        } else if (map.containsKey(optionPriority)) {
            return (ValueT) map.get(optionPriority);
        } else {
            throw new IllegalArgumentException("Option does not exist: " + option + " with priority=" + optionPriority);
        }
    }
}
