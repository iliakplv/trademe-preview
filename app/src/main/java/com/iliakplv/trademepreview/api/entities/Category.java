package com.iliakplv.trademepreview.api.entities;


import com.google.gson.annotations.SerializedName;

import static com.iliakplv.trademepreview.common.StringUtils.getLastDigitGroup;

public class Category {

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

    @Deprecated
    public String getFullNumber() {
        return number;
    }

    public String getNumber() {
        return getLastDigitGroup(number, NUMBER_SEPARATOR);
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

    public Category[] getSubcategories() {
        return subcategories;
    }
}
