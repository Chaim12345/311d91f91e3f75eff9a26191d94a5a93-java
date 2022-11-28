package com.psa.mym.mycitroenconnect.model.trip;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class TripListResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("tripListResponseDTO")
    @NotNull
    private List<TripResponseDTO> tripListResponseDTO;

    public TripListResponse() {
        this(0, null, 3, null);
    }

    public TripListResponse(int i2, @NotNull List<TripResponseDTO> tripListResponseDTO) {
        Intrinsics.checkNotNullParameter(tripListResponseDTO, "tripListResponseDTO");
        this.count = i2;
        this.tripListResponseDTO = tripListResponseDTO;
    }

    public /* synthetic */ TripListResponse(int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i2, (i3 & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TripListResponse copy$default(TripListResponse tripListResponse, int i2, List list, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = tripListResponse.count;
        }
        if ((i3 & 2) != 0) {
            list = tripListResponse.tripListResponseDTO;
        }
        return tripListResponse.copy(i2, list);
    }

    public final int component1() {
        return this.count;
    }

    @NotNull
    public final List<TripResponseDTO> component2() {
        return this.tripListResponseDTO;
    }

    @NotNull
    public final TripListResponse copy(int i2, @NotNull List<TripResponseDTO> tripListResponseDTO) {
        Intrinsics.checkNotNullParameter(tripListResponseDTO, "tripListResponseDTO");
        return new TripListResponse(i2, tripListResponseDTO);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TripListResponse) {
            TripListResponse tripListResponse = (TripListResponse) obj;
            return this.count == tripListResponse.count && Intrinsics.areEqual(this.tripListResponseDTO, tripListResponse.tripListResponseDTO);
        }
        return false;
    }

    public final int getCount() {
        return this.count;
    }

    @NotNull
    public final List<TripResponseDTO> getTripListResponseDTO() {
        return this.tripListResponseDTO;
    }

    public int hashCode() {
        return (Integer.hashCode(this.count) * 31) + this.tripListResponseDTO.hashCode();
    }

    public final void setCount(int i2) {
        this.count = i2;
    }

    public final void setTripListResponseDTO(@NotNull List<TripResponseDTO> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.tripListResponseDTO = list;
    }

    @NotNull
    public String toString() {
        return "TripListResponse(count=" + this.count + ", tripListResponseDTO=" + this.tripListResponseDTO + ')';
    }
}
