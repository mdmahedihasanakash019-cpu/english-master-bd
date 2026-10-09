package com.example.data.repository

import com.example.data.local.AppDatabase
import com.example.data.local.InitialData
import com.example.data.model.AchievementEntity
import com.example.data.model.ExamEntity
import com.example.data.model.ExamResultEntity
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.data.model.VocabularyEntity
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppRepository(private val db: AppDatabase) {

    private val userDao = db.userDao()
    private val grammarDao = db.grammarDao()
    private val paragraphDao = db.paragraphDao()
    private val questionDao = db.questionDao()
    private val examDao = db.examDao()
    private val examResultDao = db.examResultDao()
    private val vocabularyDao = db.vocabularyDao()
    private val achievementDao = db.achievementDao()

    val user: Flow<UserEntity?> = userDao.getUser()
    val allGrammarTopics: Flow<List<GrammarTopicEntity>> = grammarDao.getAllTopics()
    val allParagraphs: Flow<List<ParagraphEntity>> = paragraphDao.getAllParagraphs()
    val allQuestions: Flow<List<QuestionEntity>> = questionDao.getAllQuestions()
    val allExams: Flow<List<ExamEntity>> = examDao.getAllExams()
    val allExamResults: Flow<List<ExamResultEntity>> = examResultDao.getAllResults()
    val allVocabulary: Flow<List<VocabularyEntity>> = vocabularyDao.getAllVocabulary()
    val allAchievements: Flow<List<AchievementEntity>> = achievementDao.getAllAchievements()
    val allVideos: Flow<List<com.example.data.model.VideoLessonEntity>> = db.videoLessonDao().getAllVideos()

    val bookmarkedTopics: Flow<List<GrammarTopicEntity>> = grammarDao.getBookmarkedTopics()
    val bookmarkedParagraphs: Flow<List<ParagraphEntity>> = paragraphDao.getBookmarkedParagraphs()
    val bookmarkedVocabulary: Flow<List<VocabularyEntity>> = vocabularyDao.getBookmarkedVocabulary()
    val bookmarkedQuestions: Flow<List<QuestionEntity>> = questionDao.getBookmarkedQuestions()

    val dailyQuestions: Flow<List<QuestionEntity>> = questionDao.getDailyQuestions()

    fun getTodayDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    suspend fun addXp(earnedXp: Int): Pair<Int, String> {
        val currentUser = userDao.getUserOnce() ?: InitialData.defaultUser
        val newXp = currentUser.xp + earnedXp
        val (newLevel, levelTitle) = calculateLevel(newXp)
        userDao.updateXpAndLevel(
            userId = currentUser.id,
            xp = newXp,
            level = newLevel,
            levelTitle = levelTitle
        )
        return Pair(newLevel, levelTitle)
    }

    fun calculateLevel(xp: Int): Pair<Int, String> {
        return when {
            xp >= 5200 -> Pair(8, "Level 8 — Master (মাস্টার)")
            xp >= 3800 -> Pair(7, "Level 7 — Expert (দক্ষ)")
            xp >= 2700 -> Pair(6, "Level 6 — Advanced (উন্নত)")
            xp >= 1800 -> Pair(5, "Level 5 — Upper Intermediate (উচ্চ মধ্যবর্তী)")
            xp >= 1100 -> Pair(4, "Level 4 — Intermediate (মধ্যবর্তী)")
            xp >= 600  -> Pair(3, "Level 3 — Elementary (এলিমেন্টারি)")
            xp >= 250  -> Pair(2, "Level 2 — Basic (প্রাথমিক)")
            else       -> Pair(1, "Level 1 — Beginner (শিক্ষানবিস)")
        }
    }

    suspend fun claimDailyQuizReward(): Boolean {
        val today = getTodayDateString()
        val user = userDao.getUserOnce() ?: return false
        if (user.lastDailyQuizDate == today) {
            return false // Already claimed today
        }
        userDao.updateDailyQuizDate(user.id, today)
        addXp(100) // 100 Bonus XP for daily quiz
        // Update streak
        val newStreak = user.streakDays + 1
        userDao.updateStreak(user.id, newStreak, today)
        return true
    }

    suspend fun recordQuizFinished(topicId: String?, correctCount: Int, totalCount: Int) {
        val earnedXp = 50 + (correctCount * 10)
        addXp(earnedXp)

        if (topicId != null) {
            val scorePercent = if (totalCount > 0) (correctCount * 100) / totalCount else 0
            grammarDao.setCompleted(topicId, scorePercent)
        }

        // Check achievements
        achievementDao.unlockAchievement("first_quiz")
    }

    suspend fun recordParagraphStudied(paragraphId: String) {
        paragraphDao.setCompleted(paragraphId)
        addXp(30)
    }

    suspend fun recordExamSubmission(
        examId: String,
        examTitle: String,
        score: Int,
        totalQuestions: Int,
        timeTakenSeconds: Int
    ): ExamResultEntity {
        val percentage = if (totalQuestions > 0) (score * 100) / totalQuestions else 0
        val earnedXp = 150 + (score * 15)
        addXp(earnedXp)

        val result = ExamResultEntity(
            id = "res_${System.currentTimeMillis()}",
            userId = "local_user",
            examId = examId,
            examTitle = examTitle,
            score = score,
            totalQuestions = totalQuestions,
            percentage = percentage,
            timeTakenSeconds = timeTakenSeconds
        )
        examResultDao.insertResult(result)

        if (percentage >= 80) {
            achievementDao.unlockAchievement("exam_champion")
        }
        return result
    }

    suspend fun getRandomQuestionsForExam(count: Int): List<QuestionEntity> {
        val list = questionDao.getRandomQuestions(count)
        return if (list.isNotEmpty()) list else InitialData.questions.take(count)
    }

    // Bookmark operations
    suspend fun toggleGrammarBookmark(id: String, current: Boolean) {
        grammarDao.setBookmark(id, !current)
    }

    suspend fun toggleParagraphBookmark(id: String, current: Boolean) {
        paragraphDao.setBookmark(id, !current)
    }

    suspend fun toggleQuestionBookmark(id: String, current: Boolean) {
        questionDao.setBookmark(id, !current)
    }

    suspend fun toggleVocabularyBookmark(id: String, current: Boolean) {
        vocabularyDao.setBookmark(id, !current)
    }

    suspend fun toggleVideoBookmark(id: String, current: Boolean) {
        db.videoLessonDao().setBookmark(id, !current)
    }

    // Search
    fun searchGrammar(query: String): Flow<List<GrammarTopicEntity>> = grammarDao.searchTopics(query)
    fun searchParagraphs(query: String): Flow<List<ParagraphEntity>> = paragraphDao.searchParagraphs(query)
    fun searchVocabulary(query: String): Flow<List<VocabularyEntity>> = vocabularyDao.searchVocabulary(query)
    fun searchQuestions(query: String): Flow<List<QuestionEntity>> = questionDao.searchQuestions(query)

    // Admin CRUD Operations
    suspend fun insertOrUpdateGrammar(topic: GrammarTopicEntity) {
        grammarDao.insertTopic(topic)
    }

    suspend fun deleteGrammar(topic: GrammarTopicEntity) {
        grammarDao.deleteTopic(topic)
    }

    suspend fun insertOrUpdateParagraph(paragraph: ParagraphEntity) {
        paragraphDao.insertParagraph(paragraph)
    }

    suspend fun deleteParagraph(paragraph: ParagraphEntity) {
        paragraphDao.deleteParagraph(paragraph)
    }

    suspend fun insertOrUpdateQuestion(question: QuestionEntity) {
        questionDao.insertQuestion(question)
    }

    suspend fun deleteQuestion(question: QuestionEntity) {
        questionDao.deleteQuestion(question)
    }

    suspend fun insertOrUpdateExam(exam: ExamEntity) {
        examDao.insertExam(exam)
    }

    suspend fun deleteExam(exam: ExamEntity) {
        examDao.deleteExam(exam)
    }

    suspend fun resetDatabaseToDefaults() {
        AppDatabase.populateInitialData(db)
    }

    suspend fun updateUserProfile(user: UserEntity) {
        userDao.insertOrUpdate(user)
    }
}
