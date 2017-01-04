package com.example.kai.travelvietnamapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Kai on 01/03/2017.
 */

public class EnCategory
{
    @SerializedName("id")
    private int id;
    @SerializedName("name_en")
    private String nameEn;
    @SerializedName("name_vi")
    private String nameVi;
    @SerializedName("icon_id")
    private int iconId;
    @SerializedName("created_at")
    private String createAt;
    @SerializedName("updated_at")
    private String updateAt;
    @SerializedName("icon_small_id")
    private int iconSmallId;
    @SerializedName("icon")
    private String icon;
    @SerializedName("icon_small")
    private String iconSmall;

    public EnCategory(int id, String nameEn, String nameVi, int iconId, String createAt, String updateAt, int iconSmallId, String icon, String iconSmall) {
        this.id = id;
        this.nameEn = nameEn;
        this.nameVi = nameVi;
        this.iconId = iconId;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.iconSmallId = iconSmallId;
        this.icon = icon;
        this.iconSmall = iconSmall;
    }

    public EnCategory(int id) {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNameEn()
    {
        return nameEn;
    }

    public void setNameEn(String nameEn)
    {
        this.nameEn = nameEn;
    }

    public String getNameVi()
    {
        return nameVi;
    }

    public void setNameVi(String nameVi)
    {
        this.nameVi = nameVi;
    }

    public int getIconId()
    {
        return iconId;
    }

    public void setIconId(int iconId)
    {
        this.iconId = iconId;
    }

    public String getCreateAt()
    {
        return createAt;
    }

    public void setCreateAt(String createAt)
    {
        this.createAt = createAt;
    }

    public String getUpdateAt()
    {
        return updateAt;
    }

    public void setUpdateAt(String updateAt)
    {
        this.updateAt = updateAt;
    }

    public int getIconSmallId()
    {
        return iconSmallId;
    }

    public void setIconSmallId(int iconSmallId)
    {
        this.iconSmallId = iconSmallId;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)

    {
        this.icon = icon;
    }

    public String getIconSmall()
    {
        return iconSmall;
    }

    public void setIconSmall(String iconSmall) {
        this.iconSmall = iconSmall;
    }
}
