package androidx.core.app;

import android.app.Service;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobServiceEngine;
import android.app.job.JobWorkItem;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.psa.mym.mycitroenconnect.car_console.utils.AutoConstants;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public abstract class JobIntentService extends Service {

    /* renamed from: h  reason: collision with root package name */
    static final Object f2385h = new Object();

    /* renamed from: i  reason: collision with root package name */
    static final HashMap f2386i = new HashMap();

    /* renamed from: a  reason: collision with root package name */
    CompatJobEngine f2387a;

    /* renamed from: b  reason: collision with root package name */
    WorkEnqueuer f2388b;

    /* renamed from: c  reason: collision with root package name */
    CommandProcessor f2389c;

    /* renamed from: d  reason: collision with root package name */
    boolean f2390d = false;

    /* renamed from: e  reason: collision with root package name */
    boolean f2391e = false;

    /* renamed from: f  reason: collision with root package name */
    boolean f2392f = false;

    /* renamed from: g  reason: collision with root package name */
    final ArrayList f2393g;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class CommandProcessor extends AsyncTask<Void, Void, Void> {
        CommandProcessor() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            while (true) {
                GenericWorkItem a2 = JobIntentService.this.a();
                if (a2 == null) {
                    return null;
                }
                JobIntentService.this.e(a2.getIntent());
                a2.complete();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onCancelled(Void r1) {
            JobIntentService.this.f();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: c */
        public void onPostExecute(Void r1) {
            JobIntentService.this.f();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface CompatJobEngine {
        IBinder compatGetBinder();

        GenericWorkItem dequeueWork();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class CompatWorkEnqueuer extends WorkEnqueuer {

        /* renamed from: d  reason: collision with root package name */
        boolean f2395d;

        /* renamed from: e  reason: collision with root package name */
        boolean f2396e;
        private final Context mContext;
        private final PowerManager.WakeLock mLaunchWakeLock;
        private final PowerManager.WakeLock mRunWakeLock;

        CompatWorkEnqueuer(Context context, ComponentName componentName) {
            super(componentName);
            this.mContext = context.getApplicationContext();
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            PowerManager.WakeLock newWakeLock = powerManager.newWakeLock(1, componentName.getClassName() + ":launch");
            this.mLaunchWakeLock = newWakeLock;
            newWakeLock.setReferenceCounted(false);
            PowerManager.WakeLock newWakeLock2 = powerManager.newWakeLock(1, componentName.getClassName() + ":run");
            this.mRunWakeLock = newWakeLock2;
            newWakeLock2.setReferenceCounted(false);
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        void a(Intent intent) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(this.f2405a);
            if (this.mContext.startService(intent2) != null) {
                synchronized (this) {
                    if (!this.f2395d) {
                        this.f2395d = true;
                        if (!this.f2396e) {
                            this.mLaunchWakeLock.acquire(AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
                        }
                    }
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void serviceProcessingFinished() {
            synchronized (this) {
                if (this.f2396e) {
                    if (this.f2395d) {
                        this.mLaunchWakeLock.acquire(AutoConstants.UPDATE_INTERVAL_IN_MILLISECONDS);
                    }
                    this.f2396e = false;
                    this.mRunWakeLock.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void serviceProcessingStarted() {
            synchronized (this) {
                if (!this.f2396e) {
                    this.f2396e = true;
                    this.mRunWakeLock.acquire(600000L);
                    this.mLaunchWakeLock.release();
                }
            }
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        public void serviceStartReceived() {
            synchronized (this) {
                this.f2395d = false;
            }
        }
    }

    /* loaded from: classes.dex */
    final class CompatWorkItem implements GenericWorkItem {

        /* renamed from: a  reason: collision with root package name */
        final Intent f2397a;

        /* renamed from: b  reason: collision with root package name */
        final int f2398b;

        CompatWorkItem(Intent intent, int i2) {
            this.f2397a = intent;
            this.f2398b = i2;
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public void complete() {
            JobIntentService.this.stopSelf(this.f2398b);
        }

        @Override // androidx.core.app.JobIntentService.GenericWorkItem
        public Intent getIntent() {
            return this.f2397a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface GenericWorkItem {
        void complete();

        Intent getIntent();
    }

    @RequiresApi(26)
    /* loaded from: classes.dex */
    static final class JobServiceEngineImpl extends JobServiceEngine implements CompatJobEngine {

        /* renamed from: a  reason: collision with root package name */
        final JobIntentService f2400a;

        /* renamed from: b  reason: collision with root package name */
        final Object f2401b;

        /* renamed from: c  reason: collision with root package name */
        JobParameters f2402c;

        /* loaded from: classes.dex */
        final class WrapperWorkItem implements GenericWorkItem {

            /* renamed from: a  reason: collision with root package name */
            final JobWorkItem f2403a;

            WrapperWorkItem(JobWorkItem jobWorkItem) {
                this.f2403a = jobWorkItem;
            }

            @Override // androidx.core.app.JobIntentService.GenericWorkItem
            public void complete() {
                synchronized (JobServiceEngineImpl.this.f2401b) {
                    JobParameters jobParameters = JobServiceEngineImpl.this.f2402c;
                    if (jobParameters != null) {
                        jobParameters.completeWork(this.f2403a);
                    }
                }
            }

            @Override // androidx.core.app.JobIntentService.GenericWorkItem
            public Intent getIntent() {
                return this.f2403a.getIntent();
            }
        }

        JobServiceEngineImpl(JobIntentService jobIntentService) {
            super(jobIntentService);
            this.f2401b = new Object();
            this.f2400a = jobIntentService;
        }

        @Override // androidx.core.app.JobIntentService.CompatJobEngine
        public IBinder compatGetBinder() {
            return getBinder();
        }

        @Override // androidx.core.app.JobIntentService.CompatJobEngine
        public GenericWorkItem dequeueWork() {
            synchronized (this.f2401b) {
                JobParameters jobParameters = this.f2402c;
                if (jobParameters == null) {
                    return null;
                }
                JobWorkItem dequeueWork = jobParameters.dequeueWork();
                if (dequeueWork != null) {
                    dequeueWork.getIntent().setExtrasClassLoader(this.f2400a.getClassLoader());
                    return new WrapperWorkItem(dequeueWork);
                }
                return null;
            }
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStartJob(JobParameters jobParameters) {
            this.f2402c = jobParameters;
            this.f2400a.c(false);
            return true;
        }

        @Override // android.app.job.JobServiceEngine
        public boolean onStopJob(JobParameters jobParameters) {
            boolean b2 = this.f2400a.b();
            synchronized (this.f2401b) {
                this.f2402c = null;
            }
            return b2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RequiresApi(26)
    /* loaded from: classes.dex */
    public static final class JobWorkEnqueuer extends WorkEnqueuer {
        private final JobInfo mJobInfo;
        private final JobScheduler mJobScheduler;

        JobWorkEnqueuer(Context context, ComponentName componentName, int i2) {
            super(componentName);
            b(i2);
            this.mJobInfo = new JobInfo.Builder(i2, this.f2405a).setOverrideDeadline(0L).build();
            this.mJobScheduler = (JobScheduler) context.getApplicationContext().getSystemService("jobscheduler");
        }

        @Override // androidx.core.app.JobIntentService.WorkEnqueuer
        void a(Intent intent) {
            this.mJobScheduler.enqueue(this.mJobInfo, new JobWorkItem(intent));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class WorkEnqueuer {

        /* renamed from: a  reason: collision with root package name */
        final ComponentName f2405a;

        /* renamed from: b  reason: collision with root package name */
        boolean f2406b;

        /* renamed from: c  reason: collision with root package name */
        int f2407c;

        WorkEnqueuer(ComponentName componentName) {
            this.f2405a = componentName;
        }

        abstract void a(Intent intent);

        void b(int i2) {
            if (!this.f2406b) {
                this.f2406b = true;
                this.f2407c = i2;
            } else if (this.f2407c == i2) {
            } else {
                throw new IllegalArgumentException("Given job ID " + i2 + " is different than previous " + this.f2407c);
            }
        }

        public void serviceProcessingFinished() {
        }

        public void serviceProcessingStarted() {
        }

        public void serviceStartReceived() {
        }
    }

    public JobIntentService() {
        this.f2393g = Build.VERSION.SDK_INT >= 26 ? null : new ArrayList();
    }

    static WorkEnqueuer d(Context context, ComponentName componentName, boolean z, int i2) {
        WorkEnqueuer compatWorkEnqueuer;
        HashMap hashMap = f2386i;
        WorkEnqueuer workEnqueuer = (WorkEnqueuer) hashMap.get(componentName);
        if (workEnqueuer == null) {
            if (Build.VERSION.SDK_INT < 26) {
                compatWorkEnqueuer = new CompatWorkEnqueuer(context, componentName);
            } else if (!z) {
                throw new IllegalArgumentException("Can't be here without a job id");
            } else {
                compatWorkEnqueuer = new JobWorkEnqueuer(context, componentName, i2);
            }
            WorkEnqueuer workEnqueuer2 = compatWorkEnqueuer;
            hashMap.put(componentName, workEnqueuer2);
            return workEnqueuer2;
        }
        return workEnqueuer;
    }

    public static void enqueueWork(@NonNull Context context, @NonNull ComponentName componentName, int i2, @NonNull Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("work must not be null");
        }
        synchronized (f2385h) {
            WorkEnqueuer d2 = d(context, componentName, true, i2);
            d2.b(i2);
            d2.a(intent);
        }
    }

    public static void enqueueWork(@NonNull Context context, @NonNull Class<?> cls, int i2, @NonNull Intent intent) {
        enqueueWork(context, new ComponentName(context, cls), i2, intent);
    }

    GenericWorkItem a() {
        CompatJobEngine compatJobEngine = this.f2387a;
        if (compatJobEngine != null) {
            return compatJobEngine.dequeueWork();
        }
        synchronized (this.f2393g) {
            if (this.f2393g.size() > 0) {
                return (GenericWorkItem) this.f2393g.remove(0);
            }
            return null;
        }
    }

    boolean b() {
        CommandProcessor commandProcessor = this.f2389c;
        if (commandProcessor != null) {
            commandProcessor.cancel(this.f2390d);
        }
        this.f2391e = true;
        return onStopCurrentWork();
    }

    void c(boolean z) {
        if (this.f2389c == null) {
            this.f2389c = new CommandProcessor();
            WorkEnqueuer workEnqueuer = this.f2388b;
            if (workEnqueuer != null && z) {
                workEnqueuer.serviceProcessingStarted();
            }
            this.f2389c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    protected abstract void e(@NonNull Intent intent);

    void f() {
        ArrayList arrayList = this.f2393g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f2389c = null;
                ArrayList arrayList2 = this.f2393g;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    c(false);
                } else if (!this.f2392f) {
                    this.f2388b.serviceProcessingFinished();
                }
            }
        }
    }

    public boolean isStopped() {
        return this.f2391e;
    }

    @Override // android.app.Service
    public IBinder onBind(@NonNull Intent intent) {
        CompatJobEngine compatJobEngine = this.f2387a;
        if (compatJobEngine != null) {
            return compatJobEngine.compatGetBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f2387a = new JobServiceEngineImpl(this);
            this.f2388b = null;
            return;
        }
        this.f2387a = null;
        this.f2388b = d(this, new ComponentName(this, getClass()), false, 0);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ArrayList arrayList = this.f2393g;
        if (arrayList != null) {
            synchronized (arrayList) {
                this.f2392f = true;
                this.f2388b.serviceProcessingFinished();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i2, int i3) {
        if (this.f2393g != null) {
            this.f2388b.serviceStartReceived();
            synchronized (this.f2393g) {
                ArrayList arrayList = this.f2393g;
                if (intent == null) {
                    intent = new Intent();
                }
                arrayList.add(new CompatWorkItem(intent, i3));
                c(true);
            }
            return 3;
        }
        return 2;
    }

    public boolean onStopCurrentWork() {
        return true;
    }

    public void setInterruptIfStopped(boolean z) {
        this.f2390d = z;
    }
}
