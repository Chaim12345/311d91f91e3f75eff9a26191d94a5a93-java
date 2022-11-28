package com.chuckerteam.chucker.internal.ui.throwable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chuckerteam.chucker.databinding.ChuckerListItemThrowableBinding;
import com.chuckerteam.chucker.internal.data.entity.RecordedThrowableTuple;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.messaging.Constants;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.text.DateFormat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u0019\u001aB\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\u0007\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u001c\u0010\u000b\u001a\u00020\n2\n\u0010\b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\u0005H\u0016J\u0014\u0010\u0010\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rR\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000f\u0010\u0011R\u0019\u0010\u0013\u001a\u00020\u00128\u0006@\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "onCreateViewHolder", "holder", AppConstants.ARG_POSITION, "", "onBindViewHolder", "getItemCount", "", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", Constants.ScionAnalytics.MessageType.DATA_MESSAGE, "setData", "Ljava/util/List;", "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;", "getListener", "()Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;", "<init>", "(Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;)V", "ThrowableClickListListener", "ThrowableViewHolder", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class ThrowableAdapter extends RecyclerView.Adapter<ThrowableViewHolder> {
    private List<RecordedThrowableTuple> data;
    @NotNull
    private final ThrowableClickListListener listener;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableClickListListener;", "", "", "throwableId", "", AppConstants.ARG_POSITION, "", "onThrowableClick", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public interface ThrowableClickListListener {
        void onThrowableClick(long j2, int i2);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\tH\u0016R\u0018\u0010\r\u001a\u0004\u0018\u00010\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\u00020\u000f8\u0002@\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter$ThrowableViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;", "throwable", "", "bind$com_github_ChuckerTeam_Chucker_library", "(Lcom/chuckerteam/chucker/internal/data/entity/RecordedThrowableTuple;)V", "bind", "Landroid/view/View;", "v", "onClick", "", "throwableId", "Ljava/lang/Long;", "Lcom/chuckerteam/chucker/databinding/ChuckerListItemThrowableBinding;", "itemBinding", "Lcom/chuckerteam/chucker/databinding/ChuckerListItemThrowableBinding;", "<init>", "(Lcom/chuckerteam/chucker/internal/ui/throwable/ThrowableAdapter;Lcom/chuckerteam/chucker/databinding/ChuckerListItemThrowableBinding;)V", "com.github.ChuckerTeam.Chucker.library"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public final class ThrowableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ ThrowableAdapter f4948a;
        private final ChuckerListItemThrowableBinding itemBinding;
        private Long throwableId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ThrowableViewHolder(@NotNull ThrowableAdapter throwableAdapter, ChuckerListItemThrowableBinding itemBinding) {
            super(itemBinding.getRoot());
            Intrinsics.checkNotNullParameter(itemBinding, "itemBinding");
            this.f4948a = throwableAdapter;
            this.itemBinding = itemBinding;
            this.itemView.setOnClickListener(this);
        }

        public final void bind$com_github_ChuckerTeam_Chucker_library(@NotNull RecordedThrowableTuple throwable) {
            Intrinsics.checkNotNullParameter(throwable, "throwable");
            ChuckerListItemThrowableBinding chuckerListItemThrowableBinding = this.itemBinding;
            this.throwableId = throwable.getId();
            TextView tag = chuckerListItemThrowableBinding.tag;
            Intrinsics.checkNotNullExpressionValue(tag, "tag");
            tag.setText(throwable.getTag());
            TextView clazz = chuckerListItemThrowableBinding.clazz;
            Intrinsics.checkNotNullExpressionValue(clazz, "clazz");
            clazz.setText(throwable.getClazz());
            TextView message = chuckerListItemThrowableBinding.message;
            Intrinsics.checkNotNullExpressionValue(message, "message");
            message.setText(throwable.getMessage());
            TextView date = chuckerListItemThrowableBinding.date;
            Intrinsics.checkNotNullExpressionValue(date, "date");
            date.setText(DateFormat.getDateTimeInstance(3, 2).format(throwable.getDate()));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@NotNull View v) {
            Intrinsics.checkNotNullParameter(v, "v");
            Long l2 = this.throwableId;
            if (l2 != null) {
                this.f4948a.getListener().onThrowableClick(l2.longValue(), getAdapterPosition());
            }
        }
    }

    public ThrowableAdapter(@NotNull ThrowableClickListListener listener) {
        List<RecordedThrowableTuple> emptyList;
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        this.data = emptyList;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @NotNull
    public final ThrowableClickListListener getListener() {
        return this.listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ThrowableViewHolder holder, int i2) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind$com_github_ChuckerTeam_Chucker_library(this.data.get(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ThrowableViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i2) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ChuckerListItemThrowableBinding inflate = ChuckerListItemThrowableBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "ChuckerListItemThrowable….context), parent, false)");
        return new ThrowableViewHolder(this, inflate);
    }

    public final void setData(@NotNull List<RecordedThrowableTuple> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        this.data = data;
        notifyDataSetChanged();
    }
}
