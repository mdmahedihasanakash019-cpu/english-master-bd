package com.example.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.model.AchievementEntity
import com.example.data.model.ExamEntity
import com.example.data.model.ExamResultEntity
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.data.model.VideoLessonEntity
import com.example.data.model.VocabularyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    fun getUser(userId: String = "local_user"): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    suspend fun getUserOnce(userId: String = "local_user"): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(user: UserEntity)

    @Query("UPDATE users SET xp = :xp, level = :level, levelTitle = :levelTitle WHERE id = :userId")
    suspend fun updateXpAndLevel(userId: String = "local_user", xp: Int, level: Int, levelTitle: String)

    @Query("UPDATE users SET streakDays = :streak, lastActiveDate = :lastActive WHERE id = :userId")
    suspend fun updateStreak(userId: String = "local_user", streak: Int, lastActive: String)

    @Query("UPDATE users SET lastDailyQuizDate = :date WHERE id = :userId")
    suspend fun updateDailyQuizDate(userId: String = "local_user", date: String)
}

@Dao
interface GrammarDao {
    @Query("SELECT * FROM grammar_topics ORDER BY topicNumber ASC")
    fun getAllTopics(): Flow<List<GrammarTopicEntity>>

    @Query("SELECT * FROM grammar_topics WHERE id = :id LIMIT 1")
    fun getTopicById(id: String): Flow<GrammarTopicEntity?>

    @Query("SELECT * FROM grammar_topics WHERE isBookmarked = 1 ORDER BY topicNumber ASC")
    fun getBookmarkedTopics(): Flow<List<GrammarTopicEntity>>

    @Query("SELECT * FROM grammar_topics WHERE titleEn LIKE '%' || :query || '%' OR titleBn LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%' ORDER BY topicNumber ASC")
    fun searchTopics(query: String): Flow<List<GrammarTopicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopics(topics: List<GrammarTopicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopic(topic: GrammarTopicEntity)

    @Update
    suspend fun updateTopic(topic: GrammarTopicEntity)

    @Delete
    suspend fun deleteTopic(topic: GrammarTopicEntity)

    @Query("UPDATE grammar_topics SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun setBookmark(id: String, isBookmarked: Boolean)

    @Query("UPDATE grammar_topics SET isCompleted = 1, completedQuizScore = :score WHERE id = :id")
    suspend fun setCompleted(id: String, score: Int)

    @Query("SELECT COUNT(*) FROM grammar_topics")
    suspend fun getCount(): Int
}

@Dao
interface ParagraphDao {
    @Query("SELECT * FROM paragraphs ORDER BY titleEn ASC")
    fun getAllParagraphs(): Flow<List<ParagraphEntity>>

    @Query("SELECT * FROM paragraphs WHERE id = :id LIMIT 1")
    fun getParagraphById(id: String): Flow<ParagraphEntity?>

    @Query("SELECT * FROM paragraphs WHERE isBookmarked = 1 ORDER BY titleEn ASC")
    fun getBookmarkedParagraphs(): Flow<List<ParagraphEntity>>

    @Query("SELECT * FROM paragraphs WHERE titleEn LIKE '%' || :query || '%' OR titleBn LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchParagraphs(query: String): Flow<List<ParagraphEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParagraphs(paragraphs: List<ParagraphEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertParagraph(paragraph: ParagraphEntity)

    @Update
    suspend fun updateParagraph(paragraph: ParagraphEntity)

    @Delete
    suspend fun deleteParagraph(paragraph: ParagraphEntity)

    @Query("UPDATE paragraphs SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun setBookmark(id: String, isBookmarked: Boolean)

    @Query("UPDATE paragraphs SET isCompleted = 1 WHERE id = :id")
    suspend fun setCompleted(id: String)

    @Query("SELECT COUNT(*) FROM paragraphs")
    suspend fun getCount(): Int
}

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions ORDER BY id ASC")
    fun getAllQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE grammarTopicId = :topicId")
    fun getQuestionsByTopic(topicId: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE paragraphId = :paragraphId")
    fun getQuestionsByParagraph(paragraphId: String): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE isDailyQuiz = 1 LIMIT 5")
    fun getDailyQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestions(limit: Int): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE category = :category ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestionsByCategory(category: String, limit: Int): List<QuestionEntity>

    @Query("SELECT * FROM questions WHERE isBookmarked = 1")
    fun getBookmarkedQuestions(): Flow<List<QuestionEntity>>

    @Query("SELECT * FROM questions WHERE questionEn LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%'")
    fun searchQuestions(query: String): Flow<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestions(questions: List<QuestionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity)

    @Update
    suspend fun updateQuestion(question: QuestionEntity)

    @Delete
    suspend fun deleteQuestion(question: QuestionEntity)

    @Query("UPDATE questions SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun setBookmark(id: String, isBookmarked: Boolean)

    @Query("SELECT COUNT(*) FROM questions")
    suspend fun getCount(): Int
}

@Dao
interface ExamDao {
    @Query("SELECT * FROM exams ORDER BY titleEn ASC")
    fun getAllExams(): Flow<List<ExamEntity>>

    @Query("SELECT * FROM exams WHERE id = :id LIMIT 1")
    fun getExamById(id: String): Flow<ExamEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExams(exams: List<ExamEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExam(exam: ExamEntity)

    @Delete
    suspend fun deleteExam(exam: ExamEntity)

    @Query("SELECT COUNT(*) FROM exams")
    suspend fun getCount(): Int
}

@Dao
interface ExamResultDao {
    @Query("SELECT * FROM exam_results ORDER BY dateTimestamp DESC")
    fun getAllResults(): Flow<List<ExamResultEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(result: ExamResultEntity)

    @Query("SELECT COUNT(*) FROM exam_results")
    suspend fun getCount(): Int
}

@Dao
interface VocabularyDao {
    @Query("SELECT * FROM vocabulary ORDER BY word ASC")
    fun getAllVocabulary(): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE isBookmarked = 1 ORDER BY word ASC")
    fun getBookmarkedVocabulary(): Flow<List<VocabularyEntity>>

    @Query("SELECT * FROM vocabulary WHERE word LIKE '%' || :query || '%' OR meaningBn LIKE '%' || :query || '%'")
    fun searchVocabulary(query: String): Flow<List<VocabularyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabularyList(list: List<VocabularyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVocabulary(item: VocabularyEntity)

    @Query("UPDATE vocabulary SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun setBookmark(id: String, isBookmarked: Boolean)

    @Query("SELECT COUNT(*) FROM vocabulary")
    suspend fun getCount(): Int
}

@Dao
interface AchievementDao {
    @Query("SELECT * FROM achievements ORDER BY isUnlocked DESC, id ASC")
    fun getAllAchievements(): Flow<List<AchievementEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAchievements(list: List<AchievementEntity>)

    @Update
    suspend fun updateAchievement(achievement: AchievementEntity)

    @Query("UPDATE achievements SET isUnlocked = 1, currentProgress = requiredProgress WHERE id = :id")
    suspend fun unlockAchievement(id: String)
}

@Dao
interface VideoLessonDao {
    @Query("SELECT * FROM video_lessons ORDER BY id ASC")
    fun getAllVideos(): Flow<List<VideoLessonEntity>>

    @Query("SELECT * FROM video_lessons WHERE category = :category ORDER BY id ASC")
    fun getVideosByCategory(category: String): Flow<List<VideoLessonEntity>>

    @Query("SELECT * FROM video_lessons WHERE isBookmarked = 1")
    fun getBookmarkedVideos(): Flow<List<VideoLessonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideos(videos: List<VideoLessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVideo(video: VideoLessonEntity)

    @Delete
    suspend fun deleteVideo(video: VideoLessonEntity)

    @Query("UPDATE video_lessons SET isBookmarked = :isBookmarked WHERE id = :id")
    suspend fun setBookmark(id: String, isBookmarked: Boolean)

    @Query("SELECT COUNT(*) FROM video_lessons")
    suspend fun getCount(): Int
}
