package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.CallAdapter;
import retrofit2.DefaultCallAdapterFactory;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class DefaultCallAdapterFactory extends CallAdapter.Factory {
    @Nullable
    private final Executor callbackExecutor;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static final class ExecutorCallbackCall<T> implements Call<T> {

        /* renamed from: a  reason: collision with root package name */
        final Executor f15314a;

        /* renamed from: b  reason: collision with root package name */
        final Call f15315b;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: retrofit2.DefaultCallAdapterFactory$ExecutorCallbackCall$1  reason: invalid class name */
        /* loaded from: classes4.dex */
        public class AnonymousClass1 implements Callback<T> {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ Callback f15316a;

            AnonymousClass1(Callback callback) {
                this.f15316a = callback;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$onFailure$1(Callback callback, Throwable th) {
                callback.onFailure(ExecutorCallbackCall.this, th);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public /* synthetic */ void lambda$onResponse$0(Callback callback, Response response) {
                if (ExecutorCallbackCall.this.f15315b.isCanceled()) {
                    callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                } else {
                    callback.onResponse(ExecutorCallbackCall.this, response);
                }
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<T> call, final Throwable th) {
                Executor executor = ExecutorCallbackCall.this.f15314a;
                final Callback callback = this.f15316a;
                executor.execute(new Runnable() { // from class: retrofit2.a
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultCallAdapterFactory.ExecutorCallbackCall.AnonymousClass1.this.lambda$onFailure$1(callback, th);
                    }
                });
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<T> call, final Response<T> response) {
                Executor executor = ExecutorCallbackCall.this.f15314a;
                final Callback callback = this.f15316a;
                executor.execute(new Runnable() { // from class: retrofit2.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        DefaultCallAdapterFactory.ExecutorCallbackCall.AnonymousClass1.this.lambda$onResponse$0(callback, response);
                    }
                });
            }
        }

        ExecutorCallbackCall(Executor executor, Call call) {
            this.f15314a = executor;
            this.f15315b = call;
        }

        @Override // retrofit2.Call
        public void cancel() {
            this.f15315b.cancel();
        }

        @Override // retrofit2.Call
        public Call<T> clone() {
            return new ExecutorCallbackCall(this.f15314a, this.f15315b.clone());
        }

        @Override // retrofit2.Call
        public void enqueue(Callback<T> callback) {
            Objects.requireNonNull(callback, "callback == null");
            this.f15315b.enqueue(new AnonymousClass1(callback));
        }

        @Override // retrofit2.Call
        public Response<T> execute() {
            return this.f15315b.execute();
        }

        @Override // retrofit2.Call
        public boolean isCanceled() {
            return this.f15315b.isCanceled();
        }

        @Override // retrofit2.Call
        public boolean isExecuted() {
            return this.f15315b.isExecuted();
        }

        @Override // retrofit2.Call
        public Request request() {
            return this.f15315b.request();
        }

        @Override // retrofit2.Call
        public Timeout timeout() {
            return this.f15315b.timeout();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultCallAdapterFactory(@Nullable Executor executor) {
        this.callbackExecutor = executor;
    }

    @Override // retrofit2.CallAdapter.Factory
    @Nullable
    public CallAdapter<?, ?> get(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (CallAdapter.Factory.b(type) != Call.class) {
            return null;
        }
        if (type instanceof ParameterizedType) {
            final Type f2 = Utils.f(0, (ParameterizedType) type);
            final Executor executor = Utils.j(annotationArr, SkipCallbackExecutor.class) ? null : this.callbackExecutor;
            return new CallAdapter<Object, Call<?>>(this) { // from class: retrofit2.DefaultCallAdapterFactory.1
                @Override // retrofit2.CallAdapter
                public Call<?> adapt(Call<Object> call) {
                    Executor executor2 = executor;
                    return executor2 == null ? call : new ExecutorCallbackCall(executor2, call);
                }

                @Override // retrofit2.CallAdapter
                public Type responseType() {
                    return f2;
                }
            };
        }
        throw new IllegalArgumentException("Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
    }
}
