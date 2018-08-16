package com.tech387.wokroutapp.data.storage.local.Tag;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TagDao {

    //onCOnfilctStrategy we we identify the duplicate data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Tag> tags);

    //in this line we teak everting from workout table
    @Query("SELECT * FROM tag_table")
    List<Tag> getTags();

}
