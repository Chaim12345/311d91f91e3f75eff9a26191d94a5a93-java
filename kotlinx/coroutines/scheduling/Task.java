package kotlinx.coroutines.scheduling;

import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public abstract class Task implements Runnable {
    @JvmField
    public long submissionTime;
    @JvmField
    @NotNull
    public TaskContext taskContext;

    public Task() {
        this(0L, TasksKt.NonBlockingContext);
    }

    public Task(long j2, @NotNull TaskContext taskContext) {
        this.submissionTime = j2;
        this.taskContext = taskContext;
    }

    public final int getMode() {
        return this.taskContext.getTaskMode();
    }
}
