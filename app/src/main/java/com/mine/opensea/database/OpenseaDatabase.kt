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

package com.mine.opensea.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mine.opensea.database.daos.CollectionDao
import com.mine.opensea.database.daos.CollectionRemoteKeysDao
import com.mine.opensea.database.models.Collection
import com.mine.opensea.database.models.Collections

/**
 * initializing room database to store and retrieve data
 */

@Database(
    entities = [Collection::class, Collections.CollectionRemoteKeys::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RoomTypeConvertors::class)
abstract class OpenseaDatabase : RoomDatabase() {

    abstract fun collectionDao(): CollectionDao
    abstract fun collectionsRemoteKeys(): CollectionRemoteKeysDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: OpenseaDatabase? = null

        fun getInstance(context: Context): OpenseaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildInMemoryDatabase(context).also { instance = it }
            }
        }

        /**
         * create inMemoryDatabase to test Apis
         */
        private fun buildInMemoryDatabase(context: Context): OpenseaDatabase {
            return Room.inMemoryDatabaseBuilder(context, OpenseaDatabase::class.java)
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
