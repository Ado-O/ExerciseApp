package com.tech387.wokroutapp.data.storage.local.shoppackage;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech387.wokroutapp.data.storage.local.Tag.Tag;

import java.util.List;

@Dao
public interface ShopPackageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ShopPackage> shopPackages);

    @Query("SELECT * FROM package_table")
    List<ShopPackage> getShopPackage();

    @Query("SELECT tag_table.* FROM tag_table INNER JOIN shoppackage_tag_table ON " +
            "tag_table._id= shoppackage_tag_table.tag_id WHERE shoppackage_tag_table.shoppackage_id = :shoppackageId")
    List<Tag> getShopPackageTag(long shoppackageId);

    @Insert
    void insertShopPackageTags(List<ShopPackageTag> shopPackageTags);

    @Query("DELETE FROM shoppackage_tag_table WHERE shoppackage_id=:shoppackageId")
    void clearTags(long shoppackageId);
}
