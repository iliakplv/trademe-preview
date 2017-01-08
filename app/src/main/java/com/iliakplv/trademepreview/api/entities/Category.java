package com.iliakplv.trademepreview.api.entities;


import com.google.gson.annotations.SerializedName;

import static android.text.TextUtils.isEmpty;
import static com.iliakplv.trademepreview.common.StringUtils.getLastDigitGroup;

public class Category {

    public static final String ROOT_CATEGORY_NAME = "Root";

    public static final String ROOT_CATEGORY_NUMBER = "0000";

    public static final String NUMBER_SEPARATOR = "-";


    @SerializedName("Name")
    private String name;

    @SerializedName("Number")
    private String number;

    @SerializedName("Path")
    private String path;

    @SerializedName("HasClassifieds")
    private boolean hasClassifieds;

    @SerializedName("AreaOfBusiness")
    private int areaOfBusiness;

    @SerializedName("Subcategories")
    private Category[] subcategories;


    public String getName() {
        return name;
    }

    public String getNumber() {
        final String categoryNumber = getLastDigitGroup(number, NUMBER_SEPARATOR);
        return isEmpty(categoryNumber) && ROOT_CATEGORY_NAME.equals(name) ?
                ROOT_CATEGORY_NUMBER :
                categoryNumber;
    }

    public String getPath() {
        return path;
    }

    public boolean hasClassifieds() {
        return hasClassifieds;
    }

    public int getAreaOfBusiness() {
        return areaOfBusiness;
    }

    public Category getSubcategory(int position) {
        return subcategories[position];
    }

    public int subcategoriesCount() {
        return subcategories != null ? subcategories.length : 0;
    }

    public boolean hasSubcategories() {
        return subcategoriesCount() > 0;
    }
}
