package com.chuckerteam.chucker.internal.ui.transaction;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.chuckerteam.chucker.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u001d\b\u0002\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\t\u0010\nR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006\u0082\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources;", "", "", "icon", "I", "getIcon", "()I", TypedValues.Custom.S_COLOR, "getColor", "<init>", "(II)V", "Http", "Https", "Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources$Http;", "Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources$Https;", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public abstract class ProtocolResources {
    private final int color;
    private final int icon;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources$Http;", "Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Http extends ProtocolResources {
        public Http() {
            super(R.drawable.chucker_ic_http, R.color.chucker_color_error, null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources$Https;", "Lcom/chuckerteam/chucker/internal/ui/transaction/ProtocolResources;", "<init>", "()V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class Https extends ProtocolResources {
        public Https() {
            super(R.drawable.chucker_ic_https, R.color.chucker_color_primary, null);
        }
    }

    private ProtocolResources(@DrawableRes int i2, @ColorRes int i3) {
        this.icon = i2;
        this.color = i3;
    }

    public /* synthetic */ ProtocolResources(int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, i3);
    }

    public final int getColor() {
        return this.color;
    }

    public final int getIcon() {
        return this.icon;
    }
}
