package com.google.maps.model;

import androidx.core.os.EnvironmentCompat;
import com.psa.mym.mycitroenconnect.common.AppConstants;
/* loaded from: classes2.dex */
public enum AddressComponentType {
    STREET_ADDRESS("street_address"),
    ROUTE(AppConstants.GEO_FENCE_MODE_ROUTE),
    INTERSECTION("intersection"),
    CONTINENT("continent"),
    POLITICAL("political"),
    COUNTRY("country"),
    ADMINISTRATIVE_AREA_LEVEL_1("administrative_area_level_1"),
    ADMINISTRATIVE_AREA_LEVEL_2("administrative_area_level_2"),
    ADMINISTRATIVE_AREA_LEVEL_3("administrative_area_level_3"),
    ADMINISTRATIVE_AREA_LEVEL_4("administrative_area_level_4"),
    ADMINISTRATIVE_AREA_LEVEL_5("administrative_area_level_5"),
    COLLOQUIAL_AREA("colloquial_area"),
    LOCALITY("locality"),
    WARD("ward"),
    SUBLOCALITY("sublocality"),
    SUBLOCALITY_LEVEL_1("sublocality_level_1"),
    SUBLOCALITY_LEVEL_2("sublocality_level_2"),
    SUBLOCALITY_LEVEL_3("sublocality_level_3"),
    SUBLOCALITY_LEVEL_4("sublocality_level_4"),
    SUBLOCALITY_LEVEL_5("sublocality_level_5"),
    NEIGHBORHOOD("neighborhood"),
    PREMISE("premise"),
    SUBPREMISE("subpremise"),
    POSTAL_CODE("postal_code"),
    POSTAL_CODE_PREFIX("postal_code_prefix"),
    POSTAL_CODE_SUFFIX("postal_code_suffix"),
    NATURAL_FEATURE("natural_feature"),
    AIRPORT("airport"),
    PARK("park"),
    POINT_OF_INTEREST("point_of_interest"),
    FLOOR("floor"),
    ESTABLISHMENT("establishment"),
    PARKING("parking"),
    POST_BOX("post_box"),
    POSTAL_TOWN("postal_town"),
    ROOM("room"),
    STREET_NUMBER("street_number"),
    BUS_STATION("bus_station"),
    TRAIN_STATION("train_station"),
    SUBWAY_STATION("subway_station"),
    TRANSIT_STATION("transit_station"),
    LIGHT_RAIL_STATION("light_rail_station"),
    GENERAL_CONTRACTOR("general_contractor"),
    FOOD("food"),
    REAL_ESTATE_AGENCY("real_estate_agency"),
    CAR_RENTAL("car_rental"),
    TRAVEL_AGENCY("travel_agency"),
    ELECTRONICS_STORE("electronics_store"),
    HOME_GOODS_STORE("home_goods_store"),
    SCHOOL("school"),
    STORE("store"),
    SHOPPING_MALL("shopping_mall"),
    LODGING("lodging"),
    ART_GALLERY("art_gallery"),
    LAWYER("lawyer"),
    RESTAURANT("restaurant"),
    BAR("bar"),
    MEAL_TAKEAWAY("meal_takeaway"),
    CLOTHING_STORE("clothing_store"),
    LOCAL_GOVERNMENT_OFFICE("local_government_office"),
    FINANCE("finance"),
    MOVING_COMPANY("moving_company"),
    STORAGE("storage"),
    CAFE("cafe"),
    CAR_REPAIR("car_repair"),
    HEALTH("health"),
    INSURANCE_AGENCY("insurance_agency"),
    PAINTER("painter"),
    ARCHIPELAGO("archipelago"),
    MUSEUM("museum"),
    CAMPGROUND("campground"),
    RV_PARK("rv_park"),
    MEAL_DELIVERY("meal_delivery"),
    PRIMARY_SCHOOL("primary_school"),
    SECONDARY_SCHOOL("secondary_school"),
    TOWN_SQUARE("town_square"),
    TOURIST_ATTRACTION("tourist_attraction"),
    PLUS_CODE("plus_code"),
    DRUGSTORE("drugstore"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN);
    
    private final String addressComponentType;

    AddressComponentType(String str) {
        this.addressComponentType = str;
    }

    public String toCanonicalLiteral() {
        return toString();
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.addressComponentType;
    }
}
