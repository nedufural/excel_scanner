package com.fastcon.producttoexcelscanner.network.local.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fastcon.producttoexcelscanner.data.entity.local.Products;
import com.fastcon.producttoexcelscanner.network.local.dao.ProductsDao;
import com.fastcon.producttoexcelscanner.network.local.db.ProductsDatabase;

import java.util.List;

public class ProductsRepository {

    private ProductsDao productsDao;
    private LiveData<List<Products>> allProducts;

    public ProductsRepository(Application application) {
        ProductsDatabase database = ProductsDatabase.getInstance(application);
        productsDao = database.productsDao();
        allProducts = productsDao.getAllProducts();
    }

    public void insert(Products products) {
        new InsertProductsAsyncTask(productsDao).execute(products);
    }

    public void update(Products products) {
        new UpdateProductsAsyncTask(productsDao).execute(products);
    }

    public void delete(Products products) {
        new DeleteNoteAsyncTask(productsDao).execute(products);
    }

    public void deleteAllProducts() {
        new DeleteAllProductsAsyncTask(productsDao).execute();
    }

    public LiveData<List<Products>> getAllProducts() {
        return allProducts;
    }

    private static class InsertProductsAsyncTask extends AsyncTask<Products, Void, Void> {
        private ProductsDao productsDao;

        private InsertProductsAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(Products... products) {
            productsDao.insert(products[0]);
            return null;
        }
    }

    private static class UpdateProductsAsyncTask extends AsyncTask<Products, Void, Void> {
        private ProductsDao productsDao;

        private UpdateProductsAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(Products... products) {
            productsDao.update(products[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Products, Void, Void> {
        private ProductsDao productsDao;

        private DeleteNoteAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(Products... products) {
            productsDao.delete(products[0]);
            return null;
        }
    }

    private static class DeleteAllProductsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductsDao productsDao;

        private DeleteAllProductsAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productsDao.deleteAllProducts();
            return null;
        }
    }
}