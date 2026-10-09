package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.AchievementEntity
import com.example.data.model.ExamEntity
import com.example.data.model.ExamResultEntity
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.data.model.VocabularyEntity
import com.example.data.repository.AppRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

sealed class Screen {
    object Home : Screen()
    data class Learn(val initialTab: Int = 0) : Screen() // 0: Grammar, 1: Paragraph, 2: Vocabulary
    object Quiz : Screen()
    object Exam : Screen()
    object Profile : Screen()
    data class GrammarDetail(val topicId: String) : Screen()
    data class ParagraphDetail(val paragraphId: String) : Screen()
    data class QuizSession(val topicId: String? = null, val category: String? = null) : Screen()
    data class ExamSession(val examId: String) : Screen()
    data class ExamResult(val result: ExamResultEntity) : Screen()
    object DailyQuiz : Screen()
    object Search : Screen()
    object Leaderboard : Screen()
    object Bookmarks : Screen()
    object VideoTutorials : Screen()
    object Admin : Screen()
}

class MainViewModel(private val repository: AppRepository) : ViewModel() {

    // Current navigation screen stack
    private val _screenStack = MutableStateFlow<List<Screen>>(listOf(Screen.Home))
    val currentScreen: StateFlow<Screen> = MutableStateFlow<Screen>(Screen.Home).apply {
        viewModelScope.launch {
            _screenStack.collect { stack ->
                this@apply.value = stack.lastOrNull() ?: Screen.Home
            }
        }
    }

    val user: StateFlow<UserEntity?> = repository.user
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val grammarTopics: StateFlow<List<GrammarTopicEntity>> = repository.allGrammarTopics
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val paragraphs: StateFlow<List<ParagraphEntity>> = repository.allParagraphs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val allQuestions: StateFlow<List<QuestionEntity>> = repository.allQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val exams: StateFlow<List<ExamEntity>> = repository.allExams
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val examResults: StateFlow<List<ExamResultEntity>> = repository.allExamResults
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val vocabularyList: StateFlow<List<VocabularyEntity>> = repository.allVocabulary
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val achievements: StateFlow<List<AchievementEntity>> = repository.allAchievements
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedTopics = repository.bookmarkedTopics
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedParagraphs = repository.bookmarkedParagraphs
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedVocabulary = repository.bookmarkedVocabulary
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarkedQuestions = repository.bookmarkedQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val dailyQuestions: StateFlow<List<QuestionEntity>> = repository.dailyQuestions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val videoLessons: StateFlow<List<com.example.data.model.VideoLessonEntity>> = repository.allVideos
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggleVideoBookmark(id: String, current: Boolean) {
        viewModelScope.launch {
            repository.toggleVideoBookmark(id, current)
        }
    }

    // App Preferences
    private val _isDarkMode = MutableStateFlow(false)
    val isDarkMode: StateFlow<Boolean> = _isDarkMode.asStateFlow()

    fun toggleDarkMode() {
        _isDarkMode.value = !_isDarkMode.value
    }

    // Navigation methods
    fun navigateTo(screen: Screen) {
        val currentStack = _screenStack.value.toMutableList()
        if (screen is Screen.Home && currentStack.size > 1) {
            _screenStack.value = listOf(Screen.Home)
        } else {
            currentStack.add(screen)
            _screenStack.value = currentStack
        }
    }

    fun navigateBack(): Boolean {
        val currentStack = _screenStack.value.toMutableList()
        return if (currentStack.size > 1) {
            currentStack.removeAt(currentStack.size - 1)
            _screenStack.value = currentStack
            true
        } else {
            false
        }
    }

    // Active Quiz Session State
    data class QuizState(
        val questions: List<QuestionEntity> = emptyList(),
        val currentIndex: Int = 0,
        val selectedOption: String? = null,
        val isAnswerSubmitted: Boolean = false,
        val correctCount: Int = 0,
        val wrongCount: Int = 0,
        val isCompleted: Boolean = false,
        val earnedXp: Int = 0,
        val topicId: String? = null
    )

    private val _quizState = MutableStateFlow(QuizState())
    val quizState: StateFlow<QuizState> = _quizState.asStateFlow()

    fun startQuiz(topicId: String? = null, category: String? = null, customQuestions: List<QuestionEntity>? = null) {
        val pool = customQuestions ?: when {
            topicId != null -> allQuestions.value.filter { it.grammarTopicId == topicId }
            category != null -> allQuestions.value.filter { it.category == category }
            else -> allQuestions.value.shuffled().take(10)
        }
        val safePool = if (pool.isNotEmpty()) pool else allQuestions.value.take(5)

        _quizState.value = QuizState(
            questions = safePool,
            currentIndex = 0,
            selectedOption = null,
            isAnswerSubmitted = false,
            correctCount = 0,
            wrongCount = 0,
            isCompleted = false,
            earnedXp = 0,
            topicId = topicId
        )
    }

    fun selectQuizOption(option: String) {
        if (!_quizState.value.isAnswerSubmitted) {
            _quizState.value = _quizState.value.copy(selectedOption = option)
        }
    }

    fun submitQuizAnswer() {
        val current = _quizState.value
        if (current.selectedOption == null || current.isAnswerSubmitted) return

        val q = current.questions.getOrNull(current.currentIndex) ?: return
        val isCorrect = current.selectedOption == q.correctOption

        _quizState.value = current.copy(
            isAnswerSubmitted = true,
            correctCount = if (isCorrect) current.correctCount + 1 else current.correctCount,
            wrongCount = if (!isCorrect) current.wrongCount + 1 else current.wrongCount
        )
    }

    fun nextQuizQuestion() {
        val current = _quizState.value
        if (current.currentIndex + 1 < current.questions.size) {
            _quizState.value = current.copy(
                currentIndex = current.currentIndex + 1,
                selectedOption = null,
                isAnswerSubmitted = false
            )
        } else {
            // Quiz completed!
            val earned = 50 + (current.correctCount * 10)
            _quizState.value = current.copy(
                isCompleted = true,
                earnedXp = earned
            )
            viewModelScope.launch {
                repository.recordQuizFinished(
                    topicId = current.topicId,
                    correctCount = current.correctCount,
                    totalCount = current.questions.size
                )
            }
        }
    }

    // Active Exam Session State
    data class ExamState(
        val exam: ExamEntity? = null,
        val questions: List<QuestionEntity> = emptyList(),
        val currentIndex: Int = 0,
        val userAnswers: Map<String, String> = emptyMap(), // questionId -> chosenOption
        val remainingSeconds: Int = 0,
        val isTimerRunning: Boolean = false,
        val isSubmitted: Boolean = false,
        val result: ExamResultEntity? = null
    )

    private val _examState = MutableStateFlow(ExamState())
    val examState: StateFlow<ExamState> = _examState.asStateFlow()
    private var examTimerJob: Job? = null

    fun startExam(examId: String) {
        viewModelScope.launch {
            val exam = exams.value.find { it.id == examId } ?: return@launch
            val questions = repository.getRandomQuestionsForExam(exam.totalQuestions)
            val durationSeconds = exam.durationMinutes * 60

            _examState.value = ExamState(
                exam = exam,
                questions = questions,
                currentIndex = 0,
                userAnswers = emptyMap(),
                remainingSeconds = durationSeconds,
                isTimerRunning = true,
                isSubmitted = false,
                result = null
            )

            examTimerJob?.cancel()
            examTimerJob = viewModelScope.launch {
                while (_examState.value.remainingSeconds > 0 && !_examState.value.isSubmitted) {
                    delay(1000)
                    _examState.value = _examState.value.copy(
                        remainingSeconds = _examState.value.remainingSeconds - 1
                    )
                }
                if (!_examState.value.isSubmitted) {
                    submitExam()
                }
            }
        }
    }

    fun setExamAnswer(questionId: String, option: String) {
        if (_examState.value.isSubmitted) return
        val currentAnswers = _examState.value.userAnswers.toMutableMap()
        currentAnswers[questionId] = option
        _examState.value = _examState.value.copy(userAnswers = currentAnswers)
    }

    fun jumpToExamQuestion(index: Int) {
        if (index in 0 until _examState.value.questions.size) {
            _examState.value = _examState.value.copy(currentIndex = index)
        }
    }

    fun submitExam() {
        if (_examState.value.isSubmitted) return
        examTimerJob?.cancel()

        val state = _examState.value
        val exam = state.exam ?: return
        var score = 0
        state.questions.forEach { q ->
            if (state.userAnswers[q.id] == q.correctOption) {
                score++
            }
        }

        val totalTime = (exam.durationMinutes * 60) - state.remainingSeconds

        viewModelScope.launch {
            val result = repository.recordExamSubmission(
                examId = exam.id,
                examTitle = exam.titleBn,
                score = score,
                totalQuestions = state.questions.size,
                timeTakenSeconds = totalTime
            )
            _examState.value = state.copy(
                isSubmitted = true,
                isTimerRunning = false,
                result = result
            )
            navigateTo(Screen.ExamResult(result))
        }
    }

    // Daily Quiz Claiming
    fun claimDailyQuizReward() {
        viewModelScope.launch {
            repository.claimDailyQuizReward()
        }
    }

    suspend fun claimDailyQuiz(): Boolean {
        return repository.claimDailyQuizReward()
    }

    fun isDailyQuizClaimedToday(): Boolean {
        val today = repository.getTodayDateString()
        return user.value?.lastDailyQuizDate == today
    }

    // Bookmarking
    fun toggleGrammarBookmark(id: String, current: Boolean) {
        viewModelScope.launch { repository.toggleGrammarBookmark(id, current) }
    }

    fun toggleParagraphBookmark(id: String, current: Boolean) {
        viewModelScope.launch { repository.toggleParagraphBookmark(id, current) }
    }

    fun toggleQuestionBookmark(id: String, current: Boolean) {
        viewModelScope.launch { repository.toggleQuestionBookmark(id, current) }
    }

    fun toggleVocabularyBookmark(id: String, current: Boolean) {
        viewModelScope.launch { repository.toggleVocabularyBookmark(id, current) }
    }

    fun markParagraphStudied(id: String) {
        viewModelScope.launch { repository.recordParagraphStudied(id) }
    }

    // Search Query & Results
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // User authentication simulation
    fun loginAsGuest() {
        viewModelScope.launch {
            user.value?.let {
                repository.updateUserProfile(it.copy(isGuest = true, name = "অতিথি শিক্ষার্থী (Guest)"))
            }
        }
    }

    fun loginWithGoogle(email: String = "student@gmail.com", name: String = "মাহাদী হাসান (Student)") {
        viewModelScope.launch {
            user.value?.let {
                repository.updateUserProfile(it.copy(isGuest = false, email = email, name = name))
            }
        }
    }

    fun loginWithEmail(email: String, name: String) {
        viewModelScope.launch {
            user.value?.let {
                repository.updateUserProfile(it.copy(isGuest = false, email = email, name = name))
            }
        }
    }

    // Admin CRUD
    fun saveGrammarTopic(topic: GrammarTopicEntity) {
        viewModelScope.launch { repository.insertOrUpdateGrammar(topic) }
    }

    fun deleteGrammarTopic(topic: GrammarTopicEntity) {
        viewModelScope.launch { repository.deleteGrammar(topic) }
    }

    fun saveParagraph(paragraph: ParagraphEntity) {
        viewModelScope.launch { repository.insertOrUpdateParagraph(paragraph) }
    }

    fun deleteParagraph(paragraph: ParagraphEntity) {
        viewModelScope.launch { repository.deleteParagraph(paragraph) }
    }

    fun saveQuestion(question: QuestionEntity) {
        viewModelScope.launch { repository.insertOrUpdateQuestion(question) }
    }

    fun deleteQuestion(question: QuestionEntity) {
        viewModelScope.launch { repository.deleteQuestion(question) }
    }

    fun saveExam(exam: ExamEntity) {
        viewModelScope.launch { repository.insertOrUpdateExam(exam) }
    }

    fun deleteExam(exam: ExamEntity) {
        viewModelScope.launch { repository.deleteExam(exam) }
    }

    fun resetData() {
        viewModelScope.launch { repository.resetDatabaseToDefaults() }
    }
}
