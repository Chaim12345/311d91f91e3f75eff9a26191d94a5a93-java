package com.google.android.libraries.places.internal;

import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.model.AddressComponent;
import com.google.android.libraries.places.api.model.AddressComponents;
import com.google.android.libraries.places.api.model.DayOfWeek;
import com.google.android.libraries.places.api.model.LocalTime;
import com.google.android.libraries.places.api.model.OpeningHours;
import com.google.android.libraries.places.api.model.Period;
import com.google.android.libraries.places.api.model.PhotoMetadata;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.PlusCode;
import com.google.android.libraries.places.api.model.TimeOfWeek;
import com.google.android.libraries.places.internal.zzdv;
import com.psa.mym.mycitroenconnect.common.AppConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zzds {
    private static final zzhv zza;
    private static final zzhv zzb;

    static {
        zzhu zzhuVar = new zzhu();
        zzhuVar.zza("OPERATIONAL", Place.BusinessStatus.OPERATIONAL);
        zzhuVar.zza("CLOSED_TEMPORARILY", Place.BusinessStatus.CLOSED_TEMPORARILY);
        zzhuVar.zza("CLOSED_PERMANENTLY", Place.BusinessStatus.CLOSED_PERMANENTLY);
        zza = zzhuVar.zzb();
        zzhu zzhuVar2 = new zzhu();
        zzhuVar2.zza("accounting", Place.Type.ACCOUNTING);
        zzhuVar2.zza("administrative_area_level_1", Place.Type.ADMINISTRATIVE_AREA_LEVEL_1);
        zzhuVar2.zza("administrative_area_level_2", Place.Type.ADMINISTRATIVE_AREA_LEVEL_2);
        zzhuVar2.zza("administrative_area_level_3", Place.Type.ADMINISTRATIVE_AREA_LEVEL_3);
        zzhuVar2.zza("administrative_area_level_4", Place.Type.ADMINISTRATIVE_AREA_LEVEL_4);
        zzhuVar2.zza("administrative_area_level_5", Place.Type.ADMINISTRATIVE_AREA_LEVEL_5);
        zzhuVar2.zza("airport", Place.Type.AIRPORT);
        zzhuVar2.zza("amusement_park", Place.Type.AMUSEMENT_PARK);
        zzhuVar2.zza("aquarium", Place.Type.AQUARIUM);
        zzhuVar2.zza("archipelago", Place.Type.ARCHIPELAGO);
        zzhuVar2.zza("art_gallery", Place.Type.ART_GALLERY);
        zzhuVar2.zza("atm", Place.Type.ATM);
        zzhuVar2.zza("bakery", Place.Type.BAKERY);
        zzhuVar2.zza("bank", Place.Type.BANK);
        zzhuVar2.zza("bar", Place.Type.BAR);
        zzhuVar2.zza("beauty_salon", Place.Type.BEAUTY_SALON);
        zzhuVar2.zza("bicycle_store", Place.Type.BICYCLE_STORE);
        zzhuVar2.zza("book_store", Place.Type.BOOK_STORE);
        zzhuVar2.zza("bowling_alley", Place.Type.BOWLING_ALLEY);
        zzhuVar2.zza("bus_station", Place.Type.BUS_STATION);
        zzhuVar2.zza("cafe", Place.Type.CAFE);
        zzhuVar2.zza("campground", Place.Type.CAMPGROUND);
        zzhuVar2.zza("car_dealer", Place.Type.CAR_DEALER);
        zzhuVar2.zza("car_rental", Place.Type.CAR_RENTAL);
        zzhuVar2.zza("car_repair", Place.Type.CAR_REPAIR);
        zzhuVar2.zza("car_wash", Place.Type.CAR_WASH);
        zzhuVar2.zza("casino", Place.Type.CASINO);
        zzhuVar2.zza("cemetery", Place.Type.CEMETERY);
        zzhuVar2.zza("church", Place.Type.CHURCH);
        zzhuVar2.zza("city_hall", Place.Type.CITY_HALL);
        zzhuVar2.zza("clothing_store", Place.Type.CLOTHING_STORE);
        zzhuVar2.zza("colloquial_area", Place.Type.COLLOQUIAL_AREA);
        zzhuVar2.zza("continent", Place.Type.CONTINENT);
        zzhuVar2.zza("convenience_store", Place.Type.CONVENIENCE_STORE);
        zzhuVar2.zza("country", Place.Type.COUNTRY);
        zzhuVar2.zza("courthouse", Place.Type.COURTHOUSE);
        zzhuVar2.zza("dentist", Place.Type.DENTIST);
        zzhuVar2.zza("department_store", Place.Type.DEPARTMENT_STORE);
        zzhuVar2.zza("doctor", Place.Type.DOCTOR);
        zzhuVar2.zza("drugstore", Place.Type.DRUGSTORE);
        zzhuVar2.zza("electrician", Place.Type.ELECTRICIAN);
        zzhuVar2.zza("electronics_store", Place.Type.ELECTRONICS_STORE);
        zzhuVar2.zza("embassy", Place.Type.EMBASSY);
        zzhuVar2.zza("establishment", Place.Type.ESTABLISHMENT);
        zzhuVar2.zza("finance", Place.Type.FINANCE);
        zzhuVar2.zza("fire_station", Place.Type.FIRE_STATION);
        zzhuVar2.zza("floor", Place.Type.FLOOR);
        zzhuVar2.zza("florist", Place.Type.FLORIST);
        zzhuVar2.zza("food", Place.Type.FOOD);
        zzhuVar2.zza("funeral_home", Place.Type.FUNERAL_HOME);
        zzhuVar2.zza("furniture_store", Place.Type.FURNITURE_STORE);
        zzhuVar2.zza("gas_station", Place.Type.GAS_STATION);
        zzhuVar2.zza("general_contractor", Place.Type.GENERAL_CONTRACTOR);
        zzhuVar2.zza("geocode", Place.Type.GEOCODE);
        zzhuVar2.zza("grocery_or_supermarket", Place.Type.GROCERY_OR_SUPERMARKET);
        zzhuVar2.zza("gym", Place.Type.GYM);
        zzhuVar2.zza("hair_care", Place.Type.HAIR_CARE);
        zzhuVar2.zza("hardware_store", Place.Type.HARDWARE_STORE);
        zzhuVar2.zza("health", Place.Type.HEALTH);
        zzhuVar2.zza("hindu_temple", Place.Type.HINDU_TEMPLE);
        zzhuVar2.zza("home_goods_store", Place.Type.HOME_GOODS_STORE);
        zzhuVar2.zza("hospital", Place.Type.HOSPITAL);
        zzhuVar2.zza("insurance_agency", Place.Type.INSURANCE_AGENCY);
        zzhuVar2.zza("intersection", Place.Type.INTERSECTION);
        zzhuVar2.zza("jewelry_store", Place.Type.JEWELRY_STORE);
        zzhuVar2.zza("laundry", Place.Type.LAUNDRY);
        zzhuVar2.zza("lawyer", Place.Type.LAWYER);
        zzhuVar2.zza("library", Place.Type.LIBRARY);
        zzhuVar2.zza("light_rail_station", Place.Type.LIGHT_RAIL_STATION);
        zzhuVar2.zza("liquor_store", Place.Type.LIQUOR_STORE);
        zzhuVar2.zza("local_government_office", Place.Type.LOCAL_GOVERNMENT_OFFICE);
        zzhuVar2.zza("locality", Place.Type.LOCALITY);
        zzhuVar2.zza("locksmith", Place.Type.LOCKSMITH);
        zzhuVar2.zza("lodging", Place.Type.LODGING);
        zzhuVar2.zza("meal_delivery", Place.Type.MEAL_DELIVERY);
        zzhuVar2.zza("meal_takeaway", Place.Type.MEAL_TAKEAWAY);
        zzhuVar2.zza("mosque", Place.Type.MOSQUE);
        zzhuVar2.zza("movie_rental", Place.Type.MOVIE_RENTAL);
        zzhuVar2.zza("movie_theater", Place.Type.MOVIE_THEATER);
        zzhuVar2.zza("moving_company", Place.Type.MOVING_COMPANY);
        zzhuVar2.zza("museum", Place.Type.MUSEUM);
        zzhuVar2.zza("natural_feature", Place.Type.NATURAL_FEATURE);
        zzhuVar2.zza("neighborhood", Place.Type.NEIGHBORHOOD);
        zzhuVar2.zza("night_club", Place.Type.NIGHT_CLUB);
        zzhuVar2.zza("painter", Place.Type.PAINTER);
        zzhuVar2.zza("park", Place.Type.PARK);
        zzhuVar2.zza("parking", Place.Type.PARKING);
        zzhuVar2.zza("pet_store", Place.Type.PET_STORE);
        zzhuVar2.zza("pharmacy", Place.Type.PHARMACY);
        zzhuVar2.zza("physiotherapist", Place.Type.PHYSIOTHERAPIST);
        zzhuVar2.zza("place_of_worship", Place.Type.PLACE_OF_WORSHIP);
        zzhuVar2.zza("plumber", Place.Type.PLUMBER);
        zzhuVar2.zza("plus_code", Place.Type.PLUS_CODE);
        zzhuVar2.zza("point_of_interest", Place.Type.POINT_OF_INTEREST);
        zzhuVar2.zza("police", Place.Type.POLICE);
        zzhuVar2.zza("political", Place.Type.POLITICAL);
        zzhuVar2.zza("post_box", Place.Type.POST_BOX);
        zzhuVar2.zza("post_office", Place.Type.POST_OFFICE);
        zzhuVar2.zza("postal_code_prefix", Place.Type.POSTAL_CODE_PREFIX);
        zzhuVar2.zza("postal_code_suffix", Place.Type.POSTAL_CODE_SUFFIX);
        zzhuVar2.zza("postal_code", Place.Type.POSTAL_CODE);
        zzhuVar2.zza("postal_town", Place.Type.POSTAL_TOWN);
        zzhuVar2.zza("premise", Place.Type.PREMISE);
        zzhuVar2.zza("primary_school", Place.Type.PRIMARY_SCHOOL);
        zzhuVar2.zza("real_estate_agency", Place.Type.REAL_ESTATE_AGENCY);
        zzhuVar2.zza("restaurant", Place.Type.RESTAURANT);
        zzhuVar2.zza("roofing_contractor", Place.Type.ROOFING_CONTRACTOR);
        zzhuVar2.zza("room", Place.Type.ROOM);
        zzhuVar2.zza(AppConstants.GEO_FENCE_MODE_ROUTE, Place.Type.ROUTE);
        zzhuVar2.zza("rv_park", Place.Type.RV_PARK);
        zzhuVar2.zza("school", Place.Type.SCHOOL);
        zzhuVar2.zza("secondary_school", Place.Type.SECONDARY_SCHOOL);
        zzhuVar2.zza("shoe_store", Place.Type.SHOE_STORE);
        zzhuVar2.zza("shopping_mall", Place.Type.SHOPPING_MALL);
        zzhuVar2.zza("spa", Place.Type.SPA);
        zzhuVar2.zza("stadium", Place.Type.STADIUM);
        zzhuVar2.zza("storage", Place.Type.STORAGE);
        zzhuVar2.zza("store", Place.Type.STORE);
        zzhuVar2.zza("street_address", Place.Type.STREET_ADDRESS);
        zzhuVar2.zza("street_number", Place.Type.STREET_NUMBER);
        zzhuVar2.zza("sublocality_level_1", Place.Type.SUBLOCALITY_LEVEL_1);
        zzhuVar2.zza("sublocality_level_2", Place.Type.SUBLOCALITY_LEVEL_2);
        zzhuVar2.zza("sublocality_level_3", Place.Type.SUBLOCALITY_LEVEL_3);
        zzhuVar2.zza("sublocality_level_4", Place.Type.SUBLOCALITY_LEVEL_4);
        zzhuVar2.zza("sublocality_level_5", Place.Type.SUBLOCALITY_LEVEL_5);
        zzhuVar2.zza("sublocality", Place.Type.SUBLOCALITY);
        zzhuVar2.zza("subpremise", Place.Type.SUBPREMISE);
        zzhuVar2.zza("subway_station", Place.Type.SUBWAY_STATION);
        zzhuVar2.zza("supermarket", Place.Type.SUPERMARKET);
        zzhuVar2.zza("synagogue", Place.Type.SYNAGOGUE);
        zzhuVar2.zza("taxi_stand", Place.Type.TAXI_STAND);
        zzhuVar2.zza("tourist_attraction", Place.Type.TOURIST_ATTRACTION);
        zzhuVar2.zza("town_square", Place.Type.TOWN_SQUARE);
        zzhuVar2.zza("train_station", Place.Type.TRAIN_STATION);
        zzhuVar2.zza("transit_station", Place.Type.TRANSIT_STATION);
        zzhuVar2.zza("travel_agency", Place.Type.TRAVEL_AGENCY);
        zzhuVar2.zza("university", Place.Type.UNIVERSITY);
        zzhuVar2.zza("veterinary_care", Place.Type.VETERINARY_CARE);
        zzhuVar2.zza("zoo", Place.Type.ZOO);
        zzb = zzhuVar2.zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List zza(@Nullable List list) {
        return list != null ? list : new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public static List zzb(@Nullable List list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        zziq listIterator = ((zzhs) list).listIterator(0);
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            zzhv zzhvVar = zzb;
            if (zzhvVar.containsKey(str)) {
                arrayList.add((Place.Type) zzhvVar.get(str));
            } else {
                z = true;
            }
        }
        if (z) {
            arrayList.add(Place.Type.OTHER);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Place zzc(@Nullable zzdv zzdvVar, @Nullable List list) {
        AddressComponents newInstance;
        AddressComponent build;
        LatLng latLng;
        LatLngBounds latLngBounds;
        Integer valueOf;
        zzhs zze;
        ArrayList arrayList;
        zzdv.zzc zzb2;
        OpeningHours openingHours;
        zzdv.zze zzc;
        ArrayList arrayList2;
        Period period;
        PhotoMetadata build2;
        Place.Builder builder = Place.builder();
        builder.setAttributions(list);
        if (zzdvVar != null) {
            zzhs zzd = zzdvVar.zzd();
            PlusCode plusCode = null;
            if (zzd == null) {
                newInstance = null;
            } else {
                ArrayList arrayList3 = new ArrayList();
                zziq listIterator = zzd.listIterator(0);
                while (listIterator.hasNext()) {
                    zzdv.zza zzaVar = (zzdv.zza) listIterator.next();
                    if (zzaVar == null) {
                        build = null;
                    } else {
                        try {
                            String zzb3 = zzaVar.zzb();
                            Objects.requireNonNull(zzb3);
                            zzhs zza2 = zzaVar.zza();
                            Objects.requireNonNull(zza2);
                            AddressComponent.Builder builder2 = AddressComponent.builder(zzb3, zza2);
                            builder2.setShortName(zzaVar.zzc());
                            build = builder2.build();
                        } catch (IllegalStateException | NullPointerException e2) {
                            throw zzd(String.format("AddressComponent not properly defined (%s).", e2.getMessage()));
                        }
                    }
                    zzg(arrayList3, build);
                }
                newInstance = AddressComponents.newInstance(arrayList3);
            }
            zzdv.zzb zza3 = zzdvVar.zza();
            if (zza3 != null) {
                latLng = zze(zza3.zza());
                zzdv.zzb.C0018zzb zzb4 = zza3.zzb();
                if (zzb4 != null) {
                    LatLng zze2 = zze(zzb4.zzb());
                    LatLng zze3 = zze(zzb4.zza());
                    if (zze2 != null && zze3 != null) {
                        latLngBounds = new LatLngBounds(zze2, zze3);
                    }
                }
                latLngBounds = null;
            } else {
                latLng = null;
                latLngBounds = null;
            }
            String zzr = zzdvVar.zzr();
            Uri parse = zzr != null ? Uri.parse(zzr) : null;
            String zzn = zzdvVar.zzn();
            String concat = zzn != null ? zzn.concat(".png") : null;
            String zzm = zzdvVar.zzm();
            if (zzm != null) {
                try {
                    valueOf = Integer.valueOf(Color.parseColor(zzm));
                } catch (IllegalArgumentException unused) {
                }
                builder.setAddress(zzdvVar.zzl());
                builder.setAddressComponents(newInstance);
                builder.setBusinessStatus((Place.BusinessStatus) zza.getOrDefault(zzdvVar.zzk(), null));
                builder.setId(zzdvVar.zzq());
                builder.setLatLng(latLng);
                builder.setName(zzdvVar.zzp());
                builder.setPhoneNumber(zzdvVar.zzo());
                zze = zzdvVar.zze();
                if (zze == null) {
                    arrayList = new ArrayList();
                    zziq listIterator2 = zze.listIterator(0);
                    while (listIterator2.hasNext()) {
                        zzdv.zzd zzdVar = (zzdv.zzd) listIterator2.next();
                        if (zzdVar == null) {
                            build2 = null;
                        } else {
                            String zzd2 = zzdVar.zzd();
                            if (TextUtils.isEmpty(zzd2)) {
                                throw zzd("Photo reference not provided for a PhotoMetadata result.");
                            }
                            Integer zzb5 = zzdVar.zzb();
                            Integer zzc2 = zzdVar.zzc();
                            PhotoMetadata.Builder builder3 = PhotoMetadata.builder(zzd2);
                            zzhs zza4 = zzdVar.zza();
                            builder3.setAttributions((zza4 == null || zza4.isEmpty()) ? "" : zzgv.zzc(", ").zzd().zzf(zza4));
                            builder3.setHeight(zzb5 == null ? 0 : zzb5.intValue());
                            builder3.setWidth(zzc2 == null ? 0 : zzc2.intValue());
                            build2 = builder3.build();
                        }
                        zzg(arrayList, build2);
                    }
                } else {
                    arrayList = null;
                }
                builder.setPhotoMetadatas(arrayList);
                zzb2 = zzdvVar.zzb();
                if (zzb2 == null) {
                    OpeningHours.Builder builder4 = OpeningHours.builder();
                    zzhs zza5 = zzb2.zza();
                    if (zza5 != null) {
                        arrayList2 = new ArrayList();
                        zziq listIterator3 = zza5.listIterator(0);
                        while (listIterator3.hasNext()) {
                            zzdv.zzc.zza zzaVar2 = (zzdv.zzc.zza) listIterator3.next();
                            if (zzaVar2 != null) {
                                Period.Builder builder5 = Period.builder();
                                builder5.setOpen(zzf(zzaVar2.zzb()));
                                builder5.setClose(zzf(zzaVar2.zza()));
                                period = builder5.build();
                            } else {
                                period = null;
                            }
                            zzg(arrayList2, period);
                        }
                    } else {
                        arrayList2 = null;
                    }
                    builder4.setPeriods(zza(arrayList2));
                    builder4.setWeekdayText(zza(zzb2.zzb()));
                    openingHours = builder4.build();
                } else {
                    openingHours = null;
                }
                builder.setOpeningHours(openingHours);
                zzc = zzdvVar.zzc();
                if (zzc != null) {
                    PlusCode.Builder builder6 = PlusCode.builder();
                    builder6.setCompoundCode(zzc.zza());
                    builder6.setGlobalCode(zzc.zzb());
                    plusCode = builder6.build();
                }
                builder.setPlusCode(plusCode);
                builder.setPriceLevel(zzdvVar.zzh());
                builder.setRating(zzdvVar.zzg());
                builder.setTypes(zzb(zzdvVar.zzf()));
                builder.setUserRatingsTotal(zzdvVar.zzi());
                builder.setUtcOffsetMinutes(zzdvVar.zzj());
                builder.setViewport(latLngBounds);
                builder.setWebsiteUri(parse);
                builder.setIconUrl(concat);
                builder.setIconBackgroundColor(valueOf);
            }
            valueOf = null;
            builder.setAddress(zzdvVar.zzl());
            builder.setAddressComponents(newInstance);
            builder.setBusinessStatus((Place.BusinessStatus) zza.getOrDefault(zzdvVar.zzk(), null));
            builder.setId(zzdvVar.zzq());
            builder.setLatLng(latLng);
            builder.setName(zzdvVar.zzp());
            builder.setPhoneNumber(zzdvVar.zzo());
            zze = zzdvVar.zze();
            if (zze == null) {
            }
            builder.setPhotoMetadatas(arrayList);
            zzb2 = zzdvVar.zzb();
            if (zzb2 == null) {
            }
            builder.setOpeningHours(openingHours);
            zzc = zzdvVar.zzc();
            if (zzc != null) {
            }
            builder.setPlusCode(plusCode);
            builder.setPriceLevel(zzdvVar.zzh());
            builder.setRating(zzdvVar.zzg());
            builder.setTypes(zzb(zzdvVar.zzf()));
            builder.setUserRatingsTotal(zzdvVar.zzi());
            builder.setUtcOffsetMinutes(zzdvVar.zzj());
            builder.setViewport(latLngBounds);
            builder.setWebsiteUri(parse);
            builder.setIconUrl(concat);
            builder.setIconBackgroundColor(valueOf);
        }
        return builder.build();
    }

    private static ApiException zzd(String str) {
        String valueOf = String.valueOf(str);
        return new ApiException(new Status(8, valueOf.length() != 0 ? "Unexpected server error: ".concat(valueOf) : new String("Unexpected server error: ")));
    }

    @Nullable
    private static LatLng zze(@Nullable zzdv.zzb.zza zzaVar) {
        if (zzaVar == null) {
            return null;
        }
        Double zza2 = zzaVar.zza();
        Double zzb2 = zzaVar.zzb();
        if (zza2 == null || zzb2 == null) {
            return null;
        }
        return new LatLng(zza2.doubleValue(), zzb2.doubleValue());
    }

    @Nullable
    private static TimeOfWeek zzf(@Nullable zzdv.zzc.zzb zzbVar) {
        DayOfWeek dayOfWeek;
        if (zzbVar != null) {
            try {
                Integer zza2 = zzbVar.zza();
                zzha.zzc(zza2, "Unable to convert Pablo response to TimeOfWeek: The \"day\" field is missing.");
                String zzb2 = zzbVar.zzb();
                zzha.zzc(zzb2, "Unable to convert Pablo response to TimeOfWeek: The \"time\" field is missing.");
                boolean z = true;
                String format = String.format("Unable to convert %s to LocalTime, must be of format \"hhmm\".", zzb2);
                if (zzb2.length() != 4) {
                    z = false;
                }
                zzha.zze(z, format);
                try {
                    LocalTime newInstance = LocalTime.newInstance(Integer.parseInt(zzb2.substring(0, 2)), Integer.parseInt(zzb2.substring(2, 4)));
                    switch (zza2.intValue()) {
                        case 0:
                            dayOfWeek = DayOfWeek.SUNDAY;
                            break;
                        case 1:
                            dayOfWeek = DayOfWeek.MONDAY;
                            break;
                        case 2:
                            dayOfWeek = DayOfWeek.TUESDAY;
                            break;
                        case 3:
                            dayOfWeek = DayOfWeek.WEDNESDAY;
                            break;
                        case 4:
                            dayOfWeek = DayOfWeek.THURSDAY;
                            break;
                        case 5:
                            dayOfWeek = DayOfWeek.FRIDAY;
                            break;
                        case 6:
                            dayOfWeek = DayOfWeek.SATURDAY;
                            break;
                        default:
                            throw new IllegalArgumentException("pabloDayOfWeek can only be an integer between 0 and 6");
                    }
                    return TimeOfWeek.newInstance(dayOfWeek, newInstance);
                } catch (NumberFormatException e2) {
                    throw new IllegalArgumentException(format, e2);
                }
            } catch (NullPointerException e3) {
                throw new IllegalArgumentException(e3.getMessage(), e3);
            }
        }
        return null;
    }

    private static boolean zzg(Collection collection, @Nullable Object obj) {
        if (obj != null) {
            return collection.add(obj);
        }
        return false;
    }
}
