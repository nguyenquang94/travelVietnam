package com.example.kai.travelvietnamapp.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kai on 01/03/2017.
 */

public class EnPlace {
    @SerializedName("id")
    private int id;
    @SerializedName("name_vi")
    private String nameVi;
    @SerializedName("name_en")
    private String nameEn;
    @SerializedName("category_id")
    private int categoryId;
    @SerializedName("description_vi")
    private String descriptionVi;
    @SerializedName("description_en")
    private String descriptionEn;
    @SerializedName("enable_vi")
    private int enableVi;
    @SerializedName("enable_en")
    private int enableEn;
    @SerializedName("name_in_url")
    private String nameinUrl;
    @SerializedName("address_vi")
    private String addressVi;
    @SerializedName("address_en")
    private String addressEn;
    @SerializedName("parent_id")
    private String parentId;
    @SerializedName("images")
    private List<EnImage> listImage;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("cover")
    private List<EnImage> cover;
    @SerializedName("short_description_vi")
    private String shortDescriptionVi;
    @SerializedName("short_description_en")
    private String shortDescriptionEn;

    private String categoryName;

    private String urlImage;

    public EnPlace(int placeId, String nameVi,int categoryId, String categoryName, double latitude, double longitude,
                   String urlImage, String addressVi, String descriptionVi) {
        this.nameVi = nameVi;
        this.id = placeId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.urlImage = urlImage;
        this.addressVi = addressVi;
        this.descriptionVi = descriptionVi;
    }

    public List<EnImage> getCover() {
        return cover;
    }

    public void setConver(List<EnImage> conver) {
        this.cover = conver;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public List<EnImage> getListImage() {
        return listImage;
    }

    public void setListImage(List<EnImage> listImage) {
        this.listImage = listImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameVi() {
        return nameVi;
    }

    public void setNameVi(String nameVi) {
        this.nameVi = nameVi;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescriptionVi() {
        return descriptionVi;
    }

    public void setDescriptionvi(String description_vi) {
        this.descriptionVi = description_vi;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescription_en(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public int getEnableVi() {
        return enableVi;
    }

    public void setEnableVi(int enableVi) {
        this.enableVi = enableVi;
    }

    public int getEnableEn() {
        return enableEn;
    }

    public void setEnableEn(int enableEn) {
        this.enableEn = enableEn;
    }

    public String getNameinUrl() {
        return nameinUrl;
    }

    public void setNameinUrl(String nameinUrl) {
        this.nameinUrl = nameinUrl;
    }

    public String getAddressVi() {
        return addressVi;
    }

    public void setAddressVi(String addressVi) {
        this.addressVi = addressVi;
    }

    public String getAddressEn() {
        return addressEn;
    }

    public void setAddressEn(String addressEn) {
        this.addressEn = addressEn;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
         return  longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCover(List<EnImage> cover) {
        this.cover = cover;
    }

    public String getShortDescriptionVi() {
        return shortDescriptionVi;
    }

    public void setShortDescriptionVi(String shortDescriptionVi) {
        this.shortDescriptionVi = shortDescriptionVi;
    }

    public String getShortDescriptionEn() {
        return shortDescriptionEn;
    }

    public void setShortDescriptionEn(String shortDescriptionEn) {
        this.shortDescriptionEn = shortDescriptionEn;
    }
}
