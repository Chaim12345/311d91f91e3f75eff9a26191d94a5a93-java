package androidx.activity.result;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
/* loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    private static final int INITIAL_REQUEST_CODE_VALUE = 65536;
    private static final String KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS = "KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_PENDING_RESULTS = "KEY_COMPONENT_ACTIVITY_PENDING_RESULT";
    private static final String KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT = "KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS";
    private static final String LOG_TAG = "ActivityResultRegistry";
    private Random mRandom = new Random();
    private final Map<Integer, String> mRcToKey = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    final Map f181a = new HashMap();
    private final Map<String, LifecycleContainer> mKeyToLifecycleContainers = new HashMap();

    /* renamed from: b  reason: collision with root package name */
    ArrayList f182b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    final transient Map f183c = new HashMap();

    /* renamed from: d  reason: collision with root package name */
    final Map f184d = new HashMap();

    /* renamed from: e  reason: collision with root package name */
    final Bundle f185e = new Bundle();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class CallbackAndContract<O> {

        /* renamed from: a  reason: collision with root package name */
        final ActivityResultCallback f198a;

        /* renamed from: b  reason: collision with root package name */
        final ActivityResultContract f199b;

        CallbackAndContract(ActivityResultCallback activityResultCallback, ActivityResultContract activityResultContract) {
            this.f198a = activityResultCallback;
            this.f199b = activityResultContract;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class LifecycleContainer {

        /* renamed from: a  reason: collision with root package name */
        final Lifecycle f200a;
        private final ArrayList<LifecycleEventObserver> mObservers = new ArrayList<>();

        LifecycleContainer(@NonNull Lifecycle lifecycle) {
            this.f200a = lifecycle;
        }

        void a(@NonNull LifecycleEventObserver lifecycleEventObserver) {
            this.f200a.addObserver(lifecycleEventObserver);
            this.mObservers.add(lifecycleEventObserver);
        }

        void b() {
            Iterator<LifecycleEventObserver> it = this.mObservers.iterator();
            while (it.hasNext()) {
                this.f200a.removeObserver(it.next());
            }
            this.mObservers.clear();
        }
    }

    private void bindRcKey(int i2, String str) {
        this.mRcToKey.put(Integer.valueOf(i2), str);
        this.f181a.put(str, Integer.valueOf(i2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <O> void doDispatch(String str, int i2, @Nullable Intent intent, @Nullable CallbackAndContract<O> callbackAndContract) {
        ActivityResultCallback activityResultCallback;
        if (callbackAndContract != null && (activityResultCallback = callbackAndContract.f198a) != 0) {
            activityResultCallback.onActivityResult(callbackAndContract.f199b.parseResult(i2, intent));
            return;
        }
        this.f184d.remove(str);
        this.f185e.putParcelable(str, new ActivityResult(i2, intent));
    }

    private int generateRandomNumber() {
        int nextInt = this.mRandom.nextInt(2147418112);
        while (true) {
            int i2 = nextInt + 65536;
            if (!this.mRcToKey.containsKey(Integer.valueOf(i2))) {
                return i2;
            }
            nextInt = this.mRandom.nextInt(2147418112);
        }
    }

    private int registerKey(String str) {
        Integer num = (Integer) this.f181a.get(str);
        if (num != null) {
            return num.intValue();
        }
        int generateRandomNumber = generateRandomNumber();
        bindRcKey(generateRandomNumber, str);
        return generateRandomNumber;
    }

    @MainThread
    final void a(@NonNull String str) {
        Integer num;
        if (!this.f182b.contains(str) && (num = (Integer) this.f181a.remove(str)) != null) {
            this.mRcToKey.remove(num);
        }
        this.f183c.remove(str);
        if (this.f184d.containsKey(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Dropping pending result for request ");
            sb.append(str);
            sb.append(": ");
            sb.append(this.f184d.get(str));
            this.f184d.remove(str);
        }
        if (this.f185e.containsKey(str)) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Dropping pending result for request ");
            sb2.append(str);
            sb2.append(": ");
            sb2.append(this.f185e.getParcelable(str));
            this.f185e.remove(str);
        }
        LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
        if (lifecycleContainer != null) {
            lifecycleContainer.b();
            this.mKeyToLifecycleContainers.remove(str);
        }
    }

    @MainThread
    public final boolean dispatchResult(int i2, int i3, @Nullable Intent intent) {
        String str = this.mRcToKey.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.f182b.remove(str);
        doDispatch(str, i3, intent, (CallbackAndContract) this.f183c.get(str));
        return true;
    }

    @MainThread
    public final <O> boolean dispatchResult(int i2, @SuppressLint({"UnknownNullness"}) O o2) {
        ActivityResultCallback activityResultCallback;
        String str = this.mRcToKey.get(Integer.valueOf(i2));
        if (str == null) {
            return false;
        }
        this.f182b.remove(str);
        CallbackAndContract callbackAndContract = (CallbackAndContract) this.f183c.get(str);
        if (callbackAndContract != null && (activityResultCallback = callbackAndContract.f198a) != null) {
            activityResultCallback.onActivityResult(o2);
            return true;
        }
        this.f185e.remove(str);
        this.f184d.put(str, o2);
        return true;
    }

    @MainThread
    public abstract <I, O> void onLaunch(int i2, @NonNull ActivityResultContract<I, O> activityResultContract, @SuppressLint({"UnknownNullness"}) I i3, @Nullable ActivityOptionsCompat activityOptionsCompat);

    public final void onRestoreInstanceState(@Nullable Bundle bundle) {
        if (bundle == null) {
            return;
        }
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS);
        ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS);
        if (stringArrayList == null || integerArrayList == null) {
            return;
        }
        this.f182b = bundle.getStringArrayList(KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS);
        this.mRandom = (Random) bundle.getSerializable(KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT);
        this.f185e.putAll(bundle.getBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS));
        for (int i2 = 0; i2 < stringArrayList.size(); i2++) {
            String str = stringArrayList.get(i2);
            if (this.f181a.containsKey(str)) {
                Integer num = (Integer) this.f181a.remove(str);
                if (!this.f185e.containsKey(str)) {
                    this.mRcToKey.remove(num);
                }
            }
            bindRcKey(integerArrayList.get(i2).intValue(), stringArrayList.get(i2));
        }
    }

    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        bundle.putIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS, new ArrayList<>(this.f181a.values()));
        bundle.putStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS, new ArrayList<>(this.f181a.keySet()));
        bundle.putStringArrayList(KEY_COMPONENT_ACTIVITY_LAUNCHED_KEYS, new ArrayList<>(this.f182b));
        bundle.putBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS, (Bundle) this.f185e.clone());
        bundle.putSerializable(KEY_COMPONENT_ACTIVITY_RANDOM_OBJECT, this.mRandom);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull final String str, @NonNull final ActivityResultContract<I, O> activityResultContract, @NonNull ActivityResultCallback<O> activityResultCallback) {
        final int registerKey = registerKey(str);
        this.f183c.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
        if (this.f184d.containsKey(str)) {
            Object obj = this.f184d.get(str);
            this.f184d.remove(str);
            activityResultCallback.onActivityResult(obj);
        }
        ActivityResult activityResult = (ActivityResult) this.f185e.getParcelable(str);
        if (activityResult != null) {
            this.f185e.remove(str);
            activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
        }
        return new ActivityResultLauncher<I>() { // from class: androidx.activity.result.ActivityResultRegistry.3
            @Override // androidx.activity.result.ActivityResultLauncher
            @NonNull
            public ActivityResultContract<I, ?> getContract() {
                return activityResultContract;
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void launch(I i2, @Nullable ActivityOptionsCompat activityOptionsCompat) {
                ActivityResultRegistry.this.f182b.add(str);
                Integer num = (Integer) ActivityResultRegistry.this.f181a.get(str);
                ActivityResultRegistry.this.onLaunch(num != null ? num.intValue() : registerKey, activityResultContract, i2, activityOptionsCompat);
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.a(str);
            }
        };
    }

    @NonNull
    public final <I, O> ActivityResultLauncher<I> register(@NonNull final String str, @NonNull LifecycleOwner lifecycleOwner, @NonNull final ActivityResultContract<I, O> activityResultContract, @NonNull final ActivityResultCallback<O> activityResultCallback) {
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + lifecycleOwner + " is attempting to register while current state is " + lifecycle.getCurrentState() + ". LifecycleOwners must call register before they are STARTED.");
        }
        final int registerKey = registerKey(str);
        LifecycleContainer lifecycleContainer = this.mKeyToLifecycleContainers.get(str);
        if (lifecycleContainer == null) {
            lifecycleContainer = new LifecycleContainer(lifecycle);
        }
        lifecycleContainer.a(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner2, @NonNull Lifecycle.Event event) {
                if (!Lifecycle.Event.ON_START.equals(event)) {
                    if (Lifecycle.Event.ON_STOP.equals(event)) {
                        ActivityResultRegistry.this.f183c.remove(str);
                        return;
                    } else if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                        ActivityResultRegistry.this.a(str);
                        return;
                    } else {
                        return;
                    }
                }
                ActivityResultRegistry.this.f183c.put(str, new CallbackAndContract(activityResultCallback, activityResultContract));
                if (ActivityResultRegistry.this.f184d.containsKey(str)) {
                    Object obj = ActivityResultRegistry.this.f184d.get(str);
                    ActivityResultRegistry.this.f184d.remove(str);
                    activityResultCallback.onActivityResult(obj);
                }
                ActivityResult activityResult = (ActivityResult) ActivityResultRegistry.this.f185e.getParcelable(str);
                if (activityResult != null) {
                    ActivityResultRegistry.this.f185e.remove(str);
                    activityResultCallback.onActivityResult(activityResultContract.parseResult(activityResult.getResultCode(), activityResult.getData()));
                }
            }
        });
        this.mKeyToLifecycleContainers.put(str, lifecycleContainer);
        return new ActivityResultLauncher<I>() { // from class: androidx.activity.result.ActivityResultRegistry.2
            @Override // androidx.activity.result.ActivityResultLauncher
            @NonNull
            public ActivityResultContract<I, ?> getContract() {
                return activityResultContract;
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void launch(I i2, @Nullable ActivityOptionsCompat activityOptionsCompat) {
                ActivityResultRegistry.this.f182b.add(str);
                Integer num = (Integer) ActivityResultRegistry.this.f181a.get(str);
                ActivityResultRegistry.this.onLaunch(num != null ? num.intValue() : registerKey, activityResultContract, i2, activityOptionsCompat);
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.a(str);
            }
        };
    }
}
