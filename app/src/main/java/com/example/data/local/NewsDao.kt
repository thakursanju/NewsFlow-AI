package com.example.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Query("SELECT * FROM articles ORDER BY savedAt DESC")
    fun getSavedArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: ArticleEntity)

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
    
    @Query("SELECT EXISTS(SELECT 1 FROM articles WHERE url = :url)")
    fun isArticleSaved(url: String): Flow<Boolean>
}
