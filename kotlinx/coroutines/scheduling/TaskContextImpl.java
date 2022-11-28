package kotlinx.coroutines.scheduling;
/* loaded from: classes3.dex */
final class TaskContextImpl implements TaskContext {
    private final int taskMode;

    public TaskContextImpl(int i2) {
        this.taskMode = i2;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }
}
