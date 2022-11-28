package kotlinx.coroutines.selects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.selects.SelectBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@PublishedApi
/* loaded from: classes3.dex */
public final class UnbiasedSelectBuilderImpl<R> implements SelectBuilder<R> {
    @NotNull
    private final ArrayList<Function0<Unit>> clauses = new ArrayList<>();
    @NotNull
    private final SelectBuilderImpl<R> instance;

    public UnbiasedSelectBuilderImpl(@NotNull Continuation<? super R> continuation) {
        this.instance = new SelectBuilderImpl<>(continuation);
    }

    @NotNull
    public final ArrayList<Function0<Unit>> getClauses() {
        return this.clauses;
    }

    @NotNull
    public final SelectBuilderImpl<R> getInstance() {
        return this.instance;
    }

    @PublishedApi
    public final void handleBuilderException(@NotNull Throwable th) {
        this.instance.handleBuilderException(th);
    }

    @PublishedApi
    @Nullable
    public final Object initSelectResult() {
        if (!this.instance.isSelected()) {
            try {
                Collections.shuffle(this.clauses);
                Iterator<T> it = this.clauses.iterator();
                while (it.hasNext()) {
                    ((Function0) it.next()).invoke();
                }
            } catch (Throwable th) {
                this.instance.handleBuilderException(th);
            }
        }
        return this.instance.getResult();
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void invoke(@NotNull SelectClause0 selectClause0, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new UnbiasedSelectBuilderImpl$invoke$1(selectClause0, this, function1));
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <Q> void invoke(@NotNull SelectClause1<? extends Q> selectClause1, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new UnbiasedSelectBuilderImpl$invoke$2(selectClause1, this, function2));
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, P p2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        this.clauses.add(new UnbiasedSelectBuilderImpl$invoke$3(selectClause2, this, p2, function2));
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public <P, Q> void invoke(@NotNull SelectClause2<? super P, ? extends Q> selectClause2, @NotNull Function2<? super Q, ? super Continuation<? super R>, ? extends Object> function2) {
        SelectBuilder.DefaultImpls.invoke(this, selectClause2, function2);
    }

    @Override // kotlinx.coroutines.selects.SelectBuilder
    public void onTimeout(long j2, @NotNull Function1<? super Continuation<? super R>, ? extends Object> function1) {
        this.clauses.add(new UnbiasedSelectBuilderImpl$onTimeout$1(this, j2, function1));
    }
}
