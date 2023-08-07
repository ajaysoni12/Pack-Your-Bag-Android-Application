package com.example.packyourbag.Data;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.example.packyourbag.Constants.MyConstants;
import com.example.packyourbag.Database.RoomDB;
import com.example.packyourbag.Model.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppData extends Application {
    RoomDB database;
    Context context;

    public static final String LAST_VERSION = "LAST_VERSION";
    public static final int NEW_VERSION = 1;

    public AppData(RoomDB database) {
        this.database = database;
    }

    public AppData(RoomDB database, Context context) {
        this.database = database;
        this.context = context;
    }

    public List<Items> getBasicData() {
        String[] data = {"Wallet", "ID/Passport", "Keys", "Cash", "Credit/debit cards",
                "Travel documents", "Pen and notebook", "Travel itinerary", "Travel insurance information",
                "Snacks"};
        return prepareItemsList(MyConstants.BASIC_NEEDS_CAMEL_CASE, data);
    }

    public List<Items> getPersonalCareData() {
        String[] data = {"Toothbrush", "Toothpaste", "Soap/body wash", "Shampoo/conditioner", "Deodorant",
                "Razor and shaving cream", "Hairbrush/comb", "Hair accessories", "Makeup", "Perfume/cologne"};
        return prepareItemsList(MyConstants.PERSONAL_CARE_CAMEL_CASE, data);
    }

    public List<Items> getClothingData() {
        String[] data = {"T-shirts", "Jeans/pants", "Shorts", "Underwear", "Socks",
                "Sweater/jacket", "Pajamas", "Hat/cap", "Belt", "Scarf", "Gloves"};
        return prepareItemsList(MyConstants.CLOTHING_CAMEL_CASE, data);
    }

    public List<Items> getBabyNeedsData() {
        String[] data = {"Diapers", "Baby wipes", "Baby clothing", "Baby food/formula", "Bottles and sippy cups",
                "Pacifiers", "Baby lotion", "Baby sunscreen", "Baby toys"};
        return prepareItemsList(MyConstants.BABY_NEEDS_CAMEL_CASE, data);
    }

    public List<Items> getHealthData() {
        String[] data = {"Prescribed medications",
                "Pain relievers",
                "Band-aids",
                "Insect repellent",
                "Sunscreen",
                "Hand sanitizer",
                "First aid kit",
                "Prescription glasses/contact lenses",
                "Allergy medication",
                "Multivitamins"};
        return prepareItemsList(MyConstants.HEALTH_CAMEL_CASE, data);
    }

    public List<Items> getTechnologyData() {
        String[] data = {"Prescribed medications",
                "Pain relievers",
                "Band-aids",
                "Insect repellent",
                "Sunscreen",
                "Hand sanitizer",
                "First aid kit",
                "Prescription glasses/contact lenses",
                "Allergy medication",
                "Multivitamins"};
        return prepareItemsList(MyConstants.TECHNOLOGY_CAMEL_CASE, data);
    }

    public List<Items> getFoodData() {
        String[] data = {"Snacks",
                "Granola bars",
                "Instant noodles",
                "Canned goods",
                "Utensils (spoon, fork, knife)",
                "Reusable water bottle",
                "Non-perishable items"};
        return prepareItemsList(MyConstants.FOOD_CAMEL_CASE, data);
    }

    public List<Items> getBeachSuppliesData() {
        String[] data = {"Swimsuit",
                "Beach towel",
                "Sun hat",
                "Sunglasses",
                "Beach bag",
                "Sunscreen",
                "Beach toys",
                "Cooler with drinks and snacks"};
        return prepareItemsList(MyConstants.BEACH_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getCarSuppliesData() {
        String[] data = {"Driver's license",
                "Vehicle registration and insurance",
                "Car keys",
                "GPS or maps",
                "Emergency car kit",
                "Travel pillows/blankets"};
        return prepareItemsList(MyConstants.CAR_SUPPLIES_CAMEL_CASE, data);
    }

    public List<Items> getNeedsData() {
        String[] data = {"Wallet", "Tooth-paste", "Car keys", "Band-aids"};
        return prepareItemsList(MyConstants.NEEDS_CAMEL_CASE, data);
    }

    public List<Items> prepareItemsList(String categoryy, String[] data) {
        List<String> list = Arrays.asList(data);
        List<Items> dataList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            dataList.add(new Items(list.get(i), categoryy, false));
        }
        return dataList;
    }

    public List<List<Items>> getAllData() {
        List<List<Items>> listOfAllItems = new ArrayList<>();

        listOfAllItems.add(getBasicData());
        listOfAllItems.add(getClothingData());
        listOfAllItems.add(getPersonalCareData());
        listOfAllItems.add(getBabyNeedsData());
        listOfAllItems.add(getHealthData());
        listOfAllItems.add(getTechnologyData());
        listOfAllItems.add(getFoodData());
        listOfAllItems.add(getBeachSuppliesData());
        listOfAllItems.add(getCarSuppliesData());
        listOfAllItems.add(getNeedsData());

        return listOfAllItems;
    }

    public void persistDataByCategory(String category, boolean onlyDelete) {
        try {
            List<Items> list = deleteAndGetListByCategory(category, onlyDelete);

            if (!onlyDelete) {
                for (Items item : list) {
                    database.mainDao().saveItems(item);
                }
                Toast.makeText(context, category + " Reset Successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, category + " Reset Successfully", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(context, "Something Went Wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    public void persistAllData() {
        List<List<Items>> listOfAllItems = getAllData();
        for (List<Items> list : listOfAllItems) {
            for (Items items : list) {
                database.mainDao().saveItems(items);
            }
        }
        System.out.println("Data added");

    }

    private List<Items> deleteAndGetListByCategory(String category, boolean onlyDelete) {
        if (onlyDelete) {
            database.mainDao().deleteAllByCategoryAndAddedBy(category, MyConstants.SYSTEM_SMALL);
        } else {
            database.mainDao().deleteAllByCategory(category);
        }

        switch (category) {
            case MyConstants.BASIC_NEEDS_CAMEL_CASE:
                return getBasicData();

            case MyConstants.CLOTHING_CAMEL_CASE:
                return getClothingData();

            case MyConstants.PERSONAL_CARE_CAMEL_CASE:
                return getPersonalCareData();

            case MyConstants.BABY_NEEDS_CAMEL_CASE:
                return getBabyNeedsData();

            case MyConstants.HEALTH_CAMEL_CASE:
                return getHealthData();

            case MyConstants.TECHNOLOGY_CAMEL_CASE:
                return getTechnologyData();

            case MyConstants.FOOD_CAMEL_CASE:
                return getFoodData();

            case MyConstants.BEACH_SUPPLIES_CAMEL_CASE:
                return getBeachSuppliesData();

            case MyConstants.CAR_SUPPLIES_CAMEL_CASE:
                return getCarSuppliesData();

            case MyConstants.NEEDS_CAMEL_CASE:
                return getNeedsData();

            default:
                return new ArrayList<>();

        }
    }
}
