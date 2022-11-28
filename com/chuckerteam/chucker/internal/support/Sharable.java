package com.chuckerteam.chucker.internal.support;

import android.content.Context;
import kotlin.Metadata;
import okio.Source;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0006"}, d2 = {"Lcom/chuckerteam/chucker/internal/support/Sharable;", "", "Landroid/content/Context;", "context", "Lokio/Source;", "toSharableContent", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public interface Sharable {
    @NotNull
    Source toSharableContent(@NotNull Context context);
}
