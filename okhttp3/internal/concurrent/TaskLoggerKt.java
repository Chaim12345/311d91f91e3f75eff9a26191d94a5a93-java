package okhttp3.internal.concurrent;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.time.DurationKt;
import org.apache.http.message.TokenParser;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class TaskLoggerKt {
    public static final /* synthetic */ void access$log(Task task, TaskQueue taskQueue, String str) {
        log(task, taskQueue, str);
    }

    @NotNull
    public static final String formatDuration(long j2) {
        StringBuilder sb;
        long j3;
        long j4;
        long j5;
        if (j2 > -999500000) {
            if (j2 > -999500) {
                if (j2 <= 0) {
                    sb = new StringBuilder();
                    j5 = j2 - 500;
                } else if (j2 < 999500) {
                    sb = new StringBuilder();
                    j5 = j2 + 500;
                } else if (j2 < 999500000) {
                    sb = new StringBuilder();
                    j4 = j2 + 500000;
                } else {
                    sb = new StringBuilder();
                    j3 = j2 + 500000000;
                }
                sb.append(j5 / 1000);
                sb.append(" µs");
                String sb2 = sb.toString();
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("%6s", Arrays.copyOf(new Object[]{sb2}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                return format;
            }
            sb = new StringBuilder();
            j4 = j2 - 500000;
            sb.append(j4 / ((long) DurationKt.NANOS_IN_MILLIS));
            sb.append(" ms");
            String sb22 = sb.toString();
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String format2 = String.format("%6s", Arrays.copyOf(new Object[]{sb22}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
            return format2;
        }
        sb = new StringBuilder();
        j3 = j2 - 500000000;
        sb.append(j3 / 1000000000);
        sb.append(" s ");
        String sb222 = sb.toString();
        StringCompanionObject stringCompanionObject22 = StringCompanionObject.INSTANCE;
        String format22 = String.format("%6s", Arrays.copyOf(new Object[]{sb222}, 1));
        Intrinsics.checkNotNullExpressionValue(format22, "format(format, *args)");
        return format22;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void log(Task task, TaskQueue taskQueue, String str) {
        Logger logger = TaskRunner.Companion.getLogger();
        StringBuilder sb = new StringBuilder();
        sb.append(taskQueue.getName$okhttp());
        sb.append(TokenParser.SP);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
        sb.append(format);
        sb.append(": ");
        sb.append(task.getName());
        logger.fine(sb.toString());
    }

    public static final <T> T logElapsed(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0<? extends T> block) {
        long j2;
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(block, "block");
        boolean isLoggable = TaskRunner.Companion.getLogger().isLoggable(Level.FINE);
        if (isLoggable) {
            j2 = queue.getTaskRunner$okhttp().getBackend().nanoTime();
            log(task, queue, "starting");
        } else {
            j2 = -1;
        }
        try {
            T invoke = block.invoke();
            InlineMarker.finallyStart(1);
            if (isLoggable) {
                log(task, queue, Intrinsics.stringPlus("finished run in ", formatDuration(queue.getTaskRunner$okhttp().getBackend().nanoTime() - j2)));
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (isLoggable) {
                log(task, queue, Intrinsics.stringPlus("failed a run in ", formatDuration(queue.getTaskRunner$okhttp().getBackend().nanoTime() - j2)));
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final void taskLog(@NotNull Task task, @NotNull TaskQueue queue, @NotNull Function0<String> messageBlock) {
        Intrinsics.checkNotNullParameter(task, "task");
        Intrinsics.checkNotNullParameter(queue, "queue");
        Intrinsics.checkNotNullParameter(messageBlock, "messageBlock");
        if (TaskRunner.Companion.getLogger().isLoggable(Level.FINE)) {
            log(task, queue, messageBlock.invoke());
        }
    }
}
