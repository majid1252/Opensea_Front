/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mine.opensea.database.RoomTypeConvertors
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.models.Collection

/**
 * initializing room database to store and retrieve data
 */

@Database(entities = [Collection::class], version = 1, exportSchema = false)
@TypeConverters(RoomTypeConvertors::class)
abstract class OpenseaDatabase : RoomDatabase() {

    abstract fun collectionDao(): CollectionDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: OpenseaDatabase? = null

        fun getInstance(context: Context): OpenseaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): OpenseaDatabase {
            return Room.databaseBuilder(context, OpenseaDatabase::class.java, "DATABASE_NAME")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            /**
                             * here we can pre populate database TODO()
                             */
                        }
                    }
                ).build()
        }
    }
}
