package com.iliakplv.trademepreview.model;


import com.google.gson.annotations.SerializedName;
import com.iliakplv.trademepreview.common.StringUtils;

public class Category {

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
        return number;
    }

    public String getShortNumber() {
        // TODO refactor
        return StringUtils.getLastDigitGroup(number, NUMBER_SEPARATOR);
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
