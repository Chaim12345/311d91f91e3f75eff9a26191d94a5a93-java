package io.opencensus.resource;

import io.opencensus.internal.StringUtils;
import io.opencensus.internal.Utils;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes3.dex */
public abstract class Resource {
    private static final String ERROR_MESSAGE_INVALID_CHARS = " should be a ASCII string with a length greater than 0 and not exceed 255 characters.";
    private static final String ERROR_MESSAGE_INVALID_VALUE = " should be a ASCII string with a length not exceed 255 characters.";
    private static final String LABEL_KEY_VALUE_SPLITTER = "=";
    private static final String LABEL_LIST_SPLITTER = ",";
    private static final String OC_RESOURCE_TYPE_ENV = "OC_RESOURCE_TYPE";
    @Nullable
    private static final String ENV_TYPE = b(System.getenv(OC_RESOURCE_TYPE_ENV));
    private static final String OC_RESOURCE_LABELS_ENV = "OC_RESOURCE_LABELS";
    private static final Map<String, String> ENV_LABEL_MAP = a(System.getenv(OC_RESOURCE_LABELS_ENV));

    static Map a(@Nullable String str) {
        if (str == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap();
        for (String str2 : str.split(LABEL_LIST_SPLITTER, -1)) {
            String[] split = str2.split(LABEL_KEY_VALUE_SPLITTER, -1);
            if (split.length == 2) {
                String trim = split[0].trim();
                String replaceAll = split[1].trim().replaceAll("^\"|\"$", "");
                Utils.checkArgument(isValidAndNotEmpty(trim), "Label key should be a ASCII string with a length greater than 0 and not exceed 255 characters.");
                Utils.checkArgument(isValid(replaceAll), "Label value should be a ASCII string with a length not exceed 255 characters.");
                hashMap.put(trim, replaceAll);
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }

    @Nullable
    static String b(@Nullable String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        Utils.checkArgument(isValidAndNotEmpty(str), "Type should be a ASCII string with a length greater than 0 and not exceed 255 characters.");
        return str.trim();
    }

    public static Resource create(@Nullable String str, Map<String, String> map) {
        return createInternal(str, Collections.unmodifiableMap(new LinkedHashMap((Map) Utils.checkNotNull(map, "labels"))));
    }

    public static Resource createFromEnvironmentVariables() {
        return createInternal(ENV_TYPE, ENV_LABEL_MAP);
    }

    private static Resource createInternal(@Nullable String str, Map<String, String> map) {
        return new AutoValue_Resource(str, map);
    }

    private static boolean isValid(String str) {
        return str.length() <= 255 && StringUtils.isPrintableString(str);
    }

    private static boolean isValidAndNotEmpty(String str) {
        return !str.isEmpty() && isValid(str);
    }

    @Nullable
    private static Resource merge(@Nullable Resource resource, @Nullable Resource resource2) {
        if (resource2 == null) {
            return resource;
        }
        if (resource == null) {
            return resource2;
        }
        String type = resource.getType() != null ? resource.getType() : resource2.getType();
        LinkedHashMap linkedHashMap = new LinkedHashMap(resource2.getLabels());
        for (Map.Entry<String, String> entry : resource.getLabels().entrySet()) {
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        return createInternal(type, Collections.unmodifiableMap(linkedHashMap));
    }

    @Nullable
    public static Resource mergeResources(List<Resource> list) {
        Resource resource = null;
        for (Resource resource2 : list) {
            resource = merge(resource, resource2);
        }
        return resource;
    }

    public abstract Map<String, String> getLabels();

    @Nullable
    public abstract String getType();
}
