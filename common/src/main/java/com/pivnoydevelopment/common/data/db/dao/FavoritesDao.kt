package com.pivnoydevelopment.common.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.pivnoydevelopment.common.data.db.entity.FavoritesCourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    // Чтение
    @Query("SELECT * FROM favorites_table")
    fun getCourses(): Flow<List<FavoritesCourseEntity>>
    @Query("SELECT * FROM favorites_table")
    suspend fun getCoursesOnce(): List<FavoritesCourseEntity>
    @Query("SELECT * FROM favorites_table WHERE id = :id")
    suspend fun getCourseById(id: Long): FavoritesCourseEntity
    @Query("SELECT * FROM favorites_table WHERE title LIKE :search")
    suspend fun searchCourseByName(search: String): List<FavoritesCourseEntity>

    // Cохранение
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewCourse(favoritesCourseEntity: FavoritesCourseEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<FavoritesCourseEntity>)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCourse(favoritesCourseEntity: FavoritesCourseEntity)

    // Удаление
    @Delete()
    suspend fun deleteCourse(favoritesCourseEntity: FavoritesCourseEntity)

}