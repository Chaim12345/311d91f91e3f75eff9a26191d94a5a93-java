package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class IdleDetailsResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("tripListResponseDTO")
    @NotNull
    private List<TripResponseDTOX> tripListResponseDTO;

    public IdleDetailsResponse() {
        this(0, null, 3, null);
    }

    public IdleDetailsResponse(int i2, @NotNull List<TripResponseDTOX> tripListResponseDTO) {
        Intrinsics.checkNotNullParameter(tripListResponseDTO, "tripListResponseDTO");
        this.count = i2;
        this.tripListResponseDTO = tripListResponseDTO;
    }

    public /* synthetic */ IdleDetailsResponse(int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ IdleDetailsResponse copy$default(IdleDetailsResponse idleDetailsResponse, int i2, List list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = idleDetailsResponse.count;
        }
        if ((i3 & 2) != 0) {
            list = idleDetailsResponse.tripListResponseDTO;
        }
        return idleDetailsResponse.copy(i2, list);
    }

    public final int component1() {
        return this.count;
    }

    @NotNull
    public final List<TripResponseDTOX> component2() {
        return this.tripListResponseDTO;
    }

    @NotNull
    public final IdleDetailsResponse copy(int i2, @NotNull List<TripResponseDTOX> tripListResponseDTO) {
        Intrinsics.checkNotNullParameter(tripListResponseDTO, "tripListResponseDTO");
        return new IdleDetailsResponse(i2, tripListResponseDTO);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IdleDetailsResponse) {
            IdleDetailsResponse idleDetailsResponse = (IdleDetailsResponse) obj;
            return this.count == idleDetailsResponse.count && Intrinsics.areEqual(this.tripListResponseDTO, idleDetailsResponse.tripListResponseDTO);
        }
        return false;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final List<TripResponseDTOX> getTripListResponseDTO() {
        return this.tripListResponseDTO;
    }

    public int hashCode() {
        return (Integer.hashCode(this.count) * 31) + this.tripListResponseDTO.hashCode();
    }

    public final void setCount(int i2) {
        this.count = i2;
    }

    public final void setTripListResponseDTO(@NotNull List<TripResponseDTOX> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.tripListResponseDTO = list;
    }

    @NotNull
    public String toString() {
        return "IdleDetailsResponse(count=" + this.count + ", tripListResponseDTO=" + this.tripListResponseDTO + ')';
    }
}
