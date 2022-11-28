package io.opencensus.stats;

import io.opencensus.stats.View;
import io.opencensus.tags.TagKey;
import java.util.List;
import java.util.Objects;
import javax.annotation.concurrent.Immutable;
/* JADX INFO: Access modifiers changed from: package-private */
@Immutable
/* loaded from: classes3.dex */
public final class AutoValue_View extends View {
    private final Aggregation aggregation;
    private final List<TagKey> columns;
    private final String description;
    private final Measure measure;
    private final View.Name name;
    private final View.AggregationWindow window;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_View(View.Name name, String str, Measure measure, Aggregation aggregation, List list, View.AggregationWindow aggregationWindow) {
        Objects.requireNonNull(name, "Null name");
        this.name = name;
        Objects.requireNonNull(str, "Null description");
        this.description = str;
        Objects.requireNonNull(measure, "Null measure");
        this.measure = measure;
        Objects.requireNonNull(aggregation, "Null aggregation");
        this.aggregation = aggregation;
        Objects.requireNonNull(list, "Null columns");
        this.columns = list;
        Objects.requireNonNull(aggregationWindow, "Null window");
        this.window = aggregationWindow;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof View) {
            View view = (View) obj;
            return this.name.equals(view.getName()) && this.description.equals(view.getDescription()) && this.measure.equals(view.getMeasure()) && this.aggregation.equals(view.getAggregation()) && this.columns.equals(view.getColumns()) && this.window.equals(view.getWindow());
        }
        return false;
    }

    @Override // io.opencensus.stats.View
    public Aggregation getAggregation() {
        return this.aggregation;
    }

    @Override // io.opencensus.stats.View
    public List<TagKey> getColumns() {
        return this.columns;
    }

    @Override // io.opencensus.stats.View
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.stats.View
    public Measure getMeasure() {
        return this.measure;
    }

    @Override // io.opencensus.stats.View
    public View.Name getName() {
        return this.name;
    }

    @Override // io.opencensus.stats.View
    @Deprecated
    public View.AggregationWindow getWindow() {
        return this.window;
    }

    public int hashCode() {
        return ((((((((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.measure.hashCode()) * 1000003) ^ this.aggregation.hashCode()) * 1000003) ^ this.columns.hashCode()) * 1000003) ^ this.window.hashCode();
    }

    public String toString() {
        return "View{name=" + this.name + ", description=" + this.description + ", measure=" + this.measure + ", aggregation=" + this.aggregation + ", columns=" + this.columns + ", window=" + this.window + "}";
    }
}
