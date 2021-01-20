package com.test.randomuser_mvvm_test.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("SELECT * FROM DatabaseUser")
    fun getLocalDBUsers(): LiveData<List<DatabaseUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: List<DatabaseUser>)

}

@Database(entities = [DatabaseUser::class], version = 6, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}