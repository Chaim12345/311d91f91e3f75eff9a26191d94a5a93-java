package okhttp3;

import java.io.IOException;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public interface Callback {
    void onFailure(@NotNull Call call, @NotNull IOException iOException);

    void onResponse(@NotNull Call call, @NotNull Response response);
}
