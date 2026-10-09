package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.local.AppDatabase
import com.example.data.repository.AppRepository
import com.example.ui.components.AppBottomNavigation
import com.example.ui.components.AppTopBar
import com.example.ui.screens.AdminScreen
import com.example.ui.screens.BookmarksScreen
import com.example.ui.screens.DailyQuizScreen
import com.example.ui.screens.ExamListScreen
import com.example.ui.screens.ExamResultDetailScreen
import com.example.ui.screens.ExamSessionScreen
import com.example.ui.screens.GrammarDetailScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LeaderboardScreen
import com.example.ui.screens.LearnScreen
import com.example.ui.screens.ParagraphDetailScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.QuizScreen
import com.example.ui.screens.QuizSessionScreen
import com.example.ui.screens.SearchScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = AppRepository(database)

        setContent {
            val viewModel: MainViewModel = viewModel(
                factory = object : ViewModelProvider.Factory {
                    @Suppress("UNCHECKED_CAST")
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return MainViewModel(repository) as T
                    }
                }
            )

            val isDarkMode by viewModel.isDarkMode.collectAsState()

            MyApplicationTheme(darkTheme = isDarkMode) {
                MainApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainApp(viewModel: MainViewModel) {
    val currentScreen by viewModel.currentScreen.collectAsState()
    val user by viewModel.user.collectAsState()
    val grammarTopics by viewModel.grammarTopics.collectAsState()
    val paragraphs by viewModel.paragraphs.collectAsState()
    val allQuestions by viewModel.allQuestions.collectAsState()
    val exams by viewModel.exams.collectAsState()
    val examResults by viewModel.examResults.collectAsState()
    val vocabularyList by viewModel.vocabularyList.collectAsState()
    val achievements by viewModel.achievements.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    val bookmarkedTopics by viewModel.bookmarkedTopics.collectAsState()
    val bookmarkedParagraphs by viewModel.bookmarkedParagraphs.collectAsState()
    val bookmarkedVocab by viewModel.bookmarkedVocabulary.collectAsState()
    val bookmarkedQuestions by viewModel.bookmarkedQuestions.collectAsState()
    val dailyQuestions by viewModel.dailyQuestions.collectAsState()

    val quizState by viewModel.quizState.collectAsState()
    val examState by viewModel.examState.collectAsState()

    // Handle Hardware/Gesture Back press
    if (currentScreen !is Screen.Home) {
        BackHandler {
            viewModel.navigateBack()
        }
    }

    val isRootScreen = currentScreen is Screen.Home ||
            currentScreen is Screen.Learn ||
            currentScreen is Screen.Quiz ||
            currentScreen is Screen.Exam ||
            currentScreen is Screen.Profile

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (isRootScreen) {
                AppTopBar(
                    currentScreen = currentScreen,
                    onSearchClick = { viewModel.navigateTo(Screen.Search) },
                    onBookmarkClick = { viewModel.navigateTo(Screen.Bookmarks) },
                    onAdminClick = { viewModel.navigateTo(Screen.Admin) }
                )
            }
        },
        bottomBar = {
            if (isRootScreen) {
                AppBottomNavigation(
                    currentScreen = currentScreen,
                    onNavigate = { screen -> viewModel.navigateTo(screen) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        user = user,
                        isDailyQuizClaimed = viewModel.isDailyQuizClaimedToday(),
                        onNavigate = { dest -> viewModel.navigateTo(dest) },
                        onStartDailyQuiz = { viewModel.navigateTo(Screen.DailyQuiz) }
                    )
                }

                is Screen.Learn -> {
                    LearnScreen(
                        initialTab = screen.initialTab,
                        grammarTopics = grammarTopics,
                        paragraphs = paragraphs,
                        vocabularyList = vocabularyList,
                        onTopicClick = { topicId -> viewModel.navigateTo(Screen.GrammarDetail(topicId)) },
                        onParagraphClick = { paraId -> viewModel.navigateTo(Screen.ParagraphDetail(paraId)) },
                        onToggleGrammarBookmark = { id, cur -> viewModel.toggleGrammarBookmark(id, cur) },
                        onToggleParagraphBookmark = { id, cur -> viewModel.toggleParagraphBookmark(id, cur) },
                        onToggleVocabBookmark = { id, cur -> viewModel.toggleVocabularyBookmark(id, cur) }
                    )
                }

                is Screen.Quiz -> {
                    QuizScreen(
                        questions = allQuestions,
                        onStartCategoryQuiz = { cat ->
                            viewModel.startQuiz(category = cat)
                            viewModel.navigateTo(Screen.QuizSession(category = cat))
                        },
                        onStartQuickRandomQuiz = {
                            viewModel.startQuiz()
                            viewModel.navigateTo(Screen.QuizSession())
                        }
                    )
                }

                is Screen.Exam -> {
                    ExamListScreen(
                        exams = exams,
                        results = examResults,
                        onStartExam = { examId ->
                            viewModel.startExam(examId)
                            viewModel.navigateTo(Screen.ExamSession(examId))
                        }
                    )
                }

                is Screen.Profile -> {
                    ProfileScreen(
                        user = user,
                        achievements = achievements,
                        isDarkMode = isDarkMode,
                        onToggleDarkMode = { viewModel.toggleDarkMode() },
                        onLoginAsGuest = { viewModel.loginAsGuest() },
                        onLoginWithGoogle = { email, name -> viewModel.loginWithGoogle(email, name) },
                        onLoginWithEmail = { email, name -> viewModel.loginWithEmail(email, name) },
                        onOpenAdmin = { viewModel.navigateTo(Screen.Admin) }
                    )
                }

                is Screen.GrammarDetail -> {
                    val topic = grammarTopics.find { it.id == screen.topicId }
                    GrammarDetailScreen(
                        topic = topic,
                        onBack = { viewModel.navigateBack() },
                        onStartTopicQuiz = { topicId ->
                            viewModel.startQuiz(topicId = topicId)
                            viewModel.navigateTo(Screen.QuizSession(topicId = topicId))
                        },
                        onToggleBookmark = { id, cur -> viewModel.toggleGrammarBookmark(id, cur) }
                    )
                }

                is Screen.ParagraphDetail -> {
                    val para = paragraphs.find { it.id == screen.paragraphId }
                    ParagraphDetailScreen(
                        paragraph = para,
                        onBack = { viewModel.navigateBack() },
                        onCompleteStudying = { id -> viewModel.markParagraphStudied(id) },
                        onToggleBookmark = { id, cur -> viewModel.toggleParagraphBookmark(id, cur) }
                    )
                }

                is Screen.QuizSession -> {
                    QuizSessionScreen(
                        state = quizState,
                        onSelectOption = { opt -> viewModel.selectQuizOption(opt) },
                        onSubmitAnswer = { viewModel.submitQuizAnswer() },
                        onNextQuestion = { viewModel.nextQuizQuestion() },
                        onBack = { viewModel.navigateBack() },
                        onRestartQuiz = { viewModel.startQuiz(topicId = screen.topicId, category = screen.category) },
                        onToggleBookmark = { id, cur -> viewModel.toggleQuestionBookmark(id, cur) }
                    )
                }

                is Screen.ExamSession -> {
                    ExamSessionScreen(
                        state = examState,
                        onSelectOption = { qId, opt -> viewModel.setExamAnswer(qId, opt) },
                        onJumpToQuestion = { idx -> viewModel.jumpToExamQuestion(idx) },
                        onSubmitExam = { viewModel.submitExam() },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                is Screen.ExamResult -> {
                    ExamResultDetailScreen(
                        result = screen.result,
                        questions = examState.questions,
                        userAnswers = examState.userAnswers,
                        onBackToHome = { viewModel.navigateTo(Screen.Home) }
                    )
                }

                is Screen.DailyQuiz -> {
                    DailyQuizScreen(
                        dailyQuestions = dailyQuestions,
                        isAlreadyClaimed = viewModel.isDailyQuizClaimedToday(),
                        onClaimReward = {
                            viewModel.claimDailyQuizReward()
                        },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                is Screen.Search -> {
                    SearchScreen(
                        grammarTopics = grammarTopics,
                        paragraphs = paragraphs,
                        vocabulary = vocabularyList,
                        questions = allQuestions,
                        onTopicClick = { id -> viewModel.navigateTo(Screen.GrammarDetail(id)) },
                        onParagraphClick = { id -> viewModel.navigateTo(Screen.ParagraphDetail(id)) },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                is Screen.Leaderboard -> {
                    LeaderboardScreen(
                        user = user,
                        onBack = { viewModel.navigateBack() }
                    )
                }

                is Screen.Bookmarks -> {
                    BookmarksScreen(
                        bookmarkedTopics = bookmarkedTopics,
                        bookmarkedParagraphs = bookmarkedParagraphs,
                        bookmarkedVocab = bookmarkedVocab,
                        bookmarkedQuestions = bookmarkedQuestions,
                        onTopicClick = { id -> viewModel.navigateTo(Screen.GrammarDetail(id)) },
                        onParagraphClick = { id -> viewModel.navigateTo(Screen.ParagraphDetail(id)) },
                        onBack = { viewModel.navigateBack() }
                    )
                }

                is Screen.Admin -> {
                    AdminScreen(
                        grammarTopics = grammarTopics,
                        paragraphs = paragraphs,
                        questions = allQuestions,
                        exams = exams,
                        onSaveGrammar = { t -> viewModel.saveGrammarTopic(t) },
                        onDeleteGrammar = { t -> viewModel.deleteGrammarTopic(t) },
                        onSaveParagraph = { p -> viewModel.saveParagraph(p) },
                        onDeleteParagraph = { p -> viewModel.deleteParagraph(p) },
                        onSaveQuestion = { q -> viewModel.saveQuestion(q) },
                        onDeleteQuestion = { q -> viewModel.deleteQuestion(q) },
                        onSaveExam = { e -> viewModel.saveExam(e) },
                        onDeleteExam = { e -> viewModel.deleteExam(e) },
                        onResetDatabase = { viewModel.resetData() },
                        onBack = { viewModel.navigateBack() }
                    )
                }
            }
        }
    }
}
