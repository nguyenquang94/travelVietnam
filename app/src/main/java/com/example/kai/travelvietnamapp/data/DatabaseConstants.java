package com.example.kai.travelvietnamapp.data;

import android.provider.BaseColumns;

/**
 * Created by Kai on 01/03/2016.
 */

public class DatabaseConstants implements BaseColumns {
    public static final String DATABASE_NAME = "travel.db";
    public static final int DATABASE_VERSION = 1;
    public static final String CATEGORY_TABLE = "tbl_category";
    public static final String PLACE_TABLE = "tbl_place";
    public static final String IMAGE_TABLE = "tbl_image";
    public static final String CONVE_IMAGE_TABLE = "tbl_coverimage";

    public static final String _ID = "_id";
    // category table
    public static final String NAME_VI = "name_vi";
    public static final String NAME_EN = "name_en";
    public static final String ICON_ID = "icon_id";
    public static final String CREATE_AT = "create_at";
    public static final String UPDATE_AT = "update_at";
    public static final String ICON_SMALL_ID = "icon_small_id";
    public static final String ICON = "icon";
    public static final String ICON_SMALL = "icon_small";

    //place table
    public static final String NAME_PLACE_VI = "name_place_vi";
    public static final String NAME_PLACE_EN = "name_place_en";
    public static final String CATEGORY_ID = "category_id";
    public static final String DESCRIPTION_VI = "description_vi";
    public static final String DESCRIPTION_EN = "description_en";
    public static final String SHORT_DESCRIPTION_VI = "short_description_vi";
    public static final String SHORT_DESCRIPTION_EN = "short_description_en";
    public static final String ENABLE_VI = "enable_vi";
    public static final String ENABLE_EN = "enable_en";
    public static final String NAME_IN_URL = "name_in_url";
    public static final String ADDRESS_VI = "address_vi";
    public static final String ADDRESS_EN = "address_en";
    public static final String PARENT_ID = "parent_id";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";

    //table image
    public static final String IMAGE_ID = "image_id";
    public static final String URL = "url";
    public static final String O_HEIGHT = "o_height";
    public static final String O_WEIGHT = "o_weight";
    public static final String O_SIZE = "o_size";
    public static final String DOMINATION_COLOR = "domination_color";
    public static final String PLACE_ID = "place_id";
    public static final String TYPE_IMAGE = "type_image";

    public static final String CATEGORY_ID_CRITERIA = DatabaseConstants.CATEGORY_ID + " = ?";
    public static final String ID_CRITERIA = DatabaseConstants._ID + " = ?";
    public static final String PLACE_ID_CRITERIA = DatabaseConstants.PLACE_ID + " = ?";
    public static final int TYPE_IMAGE_CONSTANTS = 1;

}
