package com.tech387.wokroutapp.data.storage.local.shoppackage;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "shoppackage_tag_table")
public class ShopPackageTag {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "shoppackage_id")
    private long mShopPackageId;

    @ColumnInfo(name = "tag_id")
    private long mTagId;

    public ShopPackageTag(Integer id, long shopPackageId, long tagId) {
        mId = id;
        mShopPackageId = shopPackageId;
        mTagId = tagId;
    }

    @Ignore
    public ShopPackageTag(long shopPackageId, long tagId) {
        mId = null;
        mShopPackageId = shopPackageId;
        mTagId = tagId;
    }


    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public long getShopPackageId() {
        return mShopPackageId;
    }

    public void setShopPackageId(long shopPackageId) {
        mShopPackageId = shopPackageId;
    }

    public long getTagId() {
        return mTagId;
    }

    public void setTagId(long tagId) {
        mTagId = tagId;
    }
}
