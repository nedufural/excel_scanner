package com.fastcon.producttoexcelscanner.network.local.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fastcon.producttoexcelscanner.data.entity.local.Products;
import com.fastcon.producttoexcelscanner.network.local.dao.ProductsDao;

@Database(entities = {Products.class}, version = 1)
public abstract class ProductsDatabase extends RoomDatabase {

    private static ProductsDatabase instance;

    public abstract ProductsDao productsDao();

    public static synchronized ProductsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ProductsDatabase.class, "products_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProductsDao productsDao;

        private PopulateDbAsyncTask(ProductsDatabase db) {
            productsDao = db.productsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            productsDao.insert(new Products("", "", "",""));
            return null;
        }
    }
}