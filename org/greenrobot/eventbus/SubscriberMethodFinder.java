package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.text.Typography;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;
/* loaded from: classes4.dex */
class SubscriberMethodFinder {
    private static final int BRIDGE = 64;
    private static final int MODIFIERS_IGNORE = 5192;
    private static final int POOL_SIZE = 4;
    private static final int SYNTHETIC = 4096;
    private final boolean ignoreGeneratedIndex;
    private final boolean strictMethodVerification;
    private List<SubscriberInfoIndex> subscriberInfoIndexes;
    private static final Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE = new ConcurrentHashMap();
    private static final FindState[] FIND_STATE_POOL = new FindState[4];

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class FindState {

        /* renamed from: a  reason: collision with root package name */
        final List f15183a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        final Map f15184b = new HashMap();

        /* renamed from: c  reason: collision with root package name */
        final Map f15185c = new HashMap();

        /* renamed from: d  reason: collision with root package name */
        final StringBuilder f15186d = new StringBuilder(128);

        /* renamed from: e  reason: collision with root package name */
        Class f15187e;

        /* renamed from: f  reason: collision with root package name */
        boolean f15188f;

        /* renamed from: g  reason: collision with root package name */
        SubscriberInfo f15189g;

        FindState() {
        }

        private boolean checkAddWithMethodSignature(Method method, Class<?> cls) {
            this.f15186d.setLength(0);
            this.f15186d.append(method.getName());
            StringBuilder sb = this.f15186d;
            sb.append(Typography.greater);
            sb.append(cls.getName());
            String sb2 = this.f15186d.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class cls2 = (Class) this.f15185c.put(sb2, declaringClass);
            if (cls2 == null || cls2.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.f15185c.put(sb2, cls2);
            return false;
        }

        boolean a(Method method, Class cls) {
            Object put = this.f15184b.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (!checkAddWithMethodSignature((Method) put, cls)) {
                    throw new IllegalStateException();
                }
                this.f15184b.put(cls, this);
            }
            return checkAddWithMethodSignature(method, cls);
        }

        void b(Class cls) {
            this.f15187e = cls;
            this.f15188f = false;
            this.f15189g = null;
        }

        void c() {
            if (!this.f15188f) {
                Class superclass = this.f15187e.getSuperclass();
                this.f15187e = superclass;
                String name = superclass.getName();
                if (!name.startsWith("java.") && !name.startsWith("javax.") && !name.startsWith("android.") && !name.startsWith("androidx.")) {
                    return;
                }
            }
            this.f15187e = null;
        }

        void d() {
            this.f15183a.clear();
            this.f15184b.clear();
            this.f15185c.clear();
            this.f15186d.setLength(0);
            this.f15187e = null;
            this.f15188f = false;
            this.f15189g = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscriberMethodFinder(List list, boolean z, boolean z2) {
        this.subscriberInfoIndexes = list;
        this.strictMethodVerification = z;
        this.ignoreGeneratedIndex = z2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        METHOD_CACHE.clear();
    }

    private List<SubscriberMethod> findUsingInfo(Class<?> cls) {
        SubscriberMethod[] subscriberMethods;
        FindState prepareFindState = prepareFindState();
        prepareFindState.b(cls);
        while (prepareFindState.f15187e != null) {
            SubscriberInfo subscriberInfo = getSubscriberInfo(prepareFindState);
            prepareFindState.f15189g = subscriberInfo;
            if (subscriberInfo != null) {
                for (SubscriberMethod subscriberMethod : subscriberInfo.getSubscriberMethods()) {
                    if (prepareFindState.a(subscriberMethod.f15177a, subscriberMethod.f15179c)) {
                        prepareFindState.f15183a.add(subscriberMethod);
                    }
                }
            } else {
                findUsingReflectionInSingleClass(prepareFindState);
            }
            prepareFindState.c();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    private List<SubscriberMethod> findUsingReflection(Class<?> cls) {
        FindState prepareFindState = prepareFindState();
        prepareFindState.b(cls);
        while (prepareFindState.f15187e != null) {
            findUsingReflectionInSingleClass(prepareFindState);
            prepareFindState.c();
        }
        return getMethodsAndRelease(prepareFindState);
    }

    private void findUsingReflectionInSingleClass(FindState findState) {
        StringBuilder sb;
        String str;
        Method[] methods;
        try {
            try {
                methods = findState.f15187e.getDeclaredMethods();
            } catch (LinkageError e2) {
                String str2 = "Could not inspect methods of " + findState.f15187e.getName();
                if (this.ignoreGeneratedIndex) {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str = ". Please consider using EventBus annotation processor to avoid reflection.";
                } else {
                    sb = new StringBuilder();
                    sb.append(str2);
                    str = ". Please make this class visible to EventBus annotation processor to avoid reflection.";
                }
                sb.append(str);
                throw new EventBusException(sb.toString(), e2);
            }
        } catch (Throwable unused) {
            methods = findState.f15187e.getMethods();
            findState.f15188f = true;
        }
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & MODIFIERS_IGNORE) == 0) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
                    if (subscribe != null) {
                        Class<?> cls = parameterTypes[0];
                        if (findState.a(method, cls)) {
                            findState.f15183a.add(new SubscriberMethod(method, cls, subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
                        }
                    }
                } else if (this.strictMethodVerification && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.strictMethodVerification && method.isAnnotationPresent(Subscribe.class)) {
                throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }

    private List<SubscriberMethod> getMethodsAndRelease(FindState findState) {
        ArrayList arrayList = new ArrayList(findState.f15183a);
        findState.d();
        synchronized (FIND_STATE_POOL) {
            int i2 = 0;
            while (true) {
                if (i2 >= 4) {
                    break;
                }
                FindState[] findStateArr = FIND_STATE_POOL;
                if (findStateArr[i2] == null) {
                    findStateArr[i2] = findState;
                    break;
                }
                i2++;
            }
        }
        return arrayList;
    }

    private SubscriberInfo getSubscriberInfo(FindState findState) {
        SubscriberInfo subscriberInfo = findState.f15189g;
        if (subscriberInfo != null && subscriberInfo.getSuperSubscriberInfo() != null) {
            SubscriberInfo superSubscriberInfo = findState.f15189g.getSuperSubscriberInfo();
            if (findState.f15187e == superSubscriberInfo.getSubscriberClass()) {
                return superSubscriberInfo;
            }
        }
        List<SubscriberInfoIndex> list = this.subscriberInfoIndexes;
        if (list != null) {
            for (SubscriberInfoIndex subscriberInfoIndex : list) {
                SubscriberInfo subscriberInfo2 = subscriberInfoIndex.getSubscriberInfo(findState.f15187e);
                if (subscriberInfo2 != null) {
                    return subscriberInfo2;
                }
            }
            return null;
        }
        return null;
    }

    private FindState prepareFindState() {
        synchronized (FIND_STATE_POOL) {
            for (int i2 = 0; i2 < 4; i2++) {
                FindState[] findStateArr = FIND_STATE_POOL;
                FindState findState = findStateArr[i2];
                if (findState != null) {
                    findStateArr[i2] = null;
                    return findState;
                }
            }
            return new FindState();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List b(Class cls) {
        Map<Class<?>, List<SubscriberMethod>> map = METHOD_CACHE;
        List<SubscriberMethod> list = map.get(cls);
        if (list != null) {
            return list;
        }
        List<SubscriberMethod> findUsingReflection = this.ignoreGeneratedIndex ? findUsingReflection(cls) : findUsingInfo(cls);
        if (!findUsingReflection.isEmpty()) {
            map.put(cls, findUsingReflection);
            return findUsingReflection;
        }
        throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
    }
}
