package com.fastcon.producttoexcelscanner.network.local.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fastcon.producttoexcelscanner.data.entity.local.Products;
import com.fastcon.producttoexcelscanner.network.local.repository.ProductsRepository;

import java.util.List;

public class ProductsViewModel extends AndroidViewModel {
    private ProductsRepository repository;
    private LiveData<List<Products>> allProducts;

    public ProductsViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
        allProducts = repository.getAllProducts();
    }

    public void insert(Products products) {
        repository.insert(products);
    }

    public void update(Products products) {
        repository.insert(products);
    }

    public void delete(Products products) {
        repository.insert(products);
    }

    public void deleteAllProducts() {
        repository.deleteAllProducts();
    }

    public LiveData<List<Products>> getAllProducts() {
        return allProducts;
    }
}