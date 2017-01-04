package com.example.kai.travelvietnamapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kai on 01/03/2017.
 */

public class EnImage {
    @SerializedName("id")
    private int id;
    @SerializedName("url")
    private String url;
    @SerializedName("created_at")
    private String createAt;
    @SerializedName("updated_at")
    private String updateAt;
    @SerializedName("o_height")
    private int oHeight;
    @SerializedName("o_wight")
    private int oWight;
    @SerializedName("o_size")
    private int oSize;
    @SerializedName("domination_color")
    private String domination_color;
    @SerializedName("pivot")
    private EnPivot enPivot;
    @SerializedName("cover")
    private int typeImage;

    public int getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(int typeImage) {
        this.typeImage = typeImage;
    }

    public EnPivot getEnPivot() {
        return enPivot;
    }

    public void setEnPivot(EnPivot enPivot) {
        this.enPivot = enPivot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getoHeight() {
        return oHeight;
    }

    public void setoHeight(int oHeight) {
        this.oHeight = oHeight;
    }

    public int getoWight() {
        return oWight;
    }

    public void setoWight(int oWight) {
        this.oWight = oWight;
    }

    public int getoSize() {
        return oSize;
    }

    public void setoSize(int oSize) {
        this.oSize = oSize;
    }

    public String getDomination_color() {
        return domination_color;
    }

    public void setDomination_color(String domination_color) {
        this.domination_color = domination_color;
    }
}
