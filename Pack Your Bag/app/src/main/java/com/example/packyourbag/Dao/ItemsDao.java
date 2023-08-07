package com.example.packyourbag.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;
import com.example.packyourbag.Model.Items;

import java.util.List;

@Dao
public interface ItemsDao {

    @Insert(onConflict = REPLACE)
    void saveItems(Items items);

    @Query("select * from items where category=:category order by id asc")
    List<Items> getAll(String category);

    @Delete
    void delete(Items items);

    @Query("update items set checked=:checked where ID=:id")
    void checkUnCheck(int id, boolean checked);

    @Query("select count(*) from items")
    int getItemCount();

    @Query("delete from items where addedby=:addedBy")
    void deleteAllSystemItems(String addedBy);

    @Query("delete from items where category=:category")
    int deleteAllByCategory(String category);

    @Query("delete from items where category=:category and addedby=:addedBy")
    int deleteAllByCategoryAndAddedBy(String category, String addedBy);

    @Query("select * from items where checked=:checked order by id asc")
    List<Items> getAllSelected(boolean checked);

}
