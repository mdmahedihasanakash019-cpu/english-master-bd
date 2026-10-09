package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.data.model.AchievementEntity
import com.example.data.model.ExamEntity
import com.example.data.model.ExamResultEntity
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.data.model.VideoLessonEntity
import com.example.data.model.VocabularyEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserEntity::class,
        GrammarTopicEntity::class,
        ParagraphEntity::class,
        QuestionEntity::class,
        ExamEntity::class,
        ExamResultEntity::class,
        VocabularyEntity::class,
        AchievementEntity::class,
        VideoLessonEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun grammarDao(): GrammarDao
    abstract fun paragraphDao(): ParagraphDao
    abstract fun questionDao(): QuestionDao
    abstract fun examDao(): ExamDao
    abstract fun examResultDao(): ExamResultDao
    abstract fun vocabularyDao(): VocabularyDao
    abstract fun achievementDao(): AchievementDao
    abstract fun videoLessonDao(): VideoLessonDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "english_master_bd.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Populate database in background
                            CoroutineScope(Dispatchers.IO).launch {
                                INSTANCE?.let { database ->
                                    populateInitialData(database)
                                }
                            }
                        }
                    })
                    .build()
                INSTANCE = instance
                // Also ensure data is present if tables are empty
                CoroutineScope(Dispatchers.IO).launch {
                    ensureSeedData(instance)
                }
                instance
            }
        }

        suspend fun populateInitialData(db: AppDatabase) {
            db.userDao().insertOrUpdate(InitialData.defaultUser)
            db.grammarDao().insertTopics(InitialData.grammarTopics)
            db.paragraphDao().insertParagraphs(InitialData.paragraphs)
            db.questionDao().insertQuestions(InitialData.questions)
            db.examDao().insertExams(InitialData.exams)
            db.vocabularyDao().insertVocabularyList(InitialData.vocabularyList)
            db.achievementDao().insertAchievements(InitialData.achievements)
            db.videoLessonDao().insertVideos(InitialData.videoLessons)
        }

        suspend fun ensureSeedData(db: AppDatabase) {
            try {
                if (db.grammarDao().getCount() == 0) {
                    populateInitialData(db)
                }
            } catch (e: Exception) {
                // Ignore or log
            }
        }
    }
}
