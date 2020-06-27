package com.fastcon.producttoexcelscanner.network.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.fastcon.producttoexcelscanner.data.entity.local.Products;

import java.util.List;

@Dao
public interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Products products);

    @Update
    void update(Products products);

    @Delete
    void delete(Products products);

    @Query("DELETE FROM products_table")
    void deleteAllProducts();

    @Query("SELECT * FROM products_table")
    LiveData<List<Products>> getAllProducts();
}