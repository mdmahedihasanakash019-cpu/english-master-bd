package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ads.StartIoBannerAd
import com.example.ads.StartIoInterstitialAdDialog
import com.example.data.model.QuestionEntity
import com.example.ui.theme.ErrorContainer
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldYellow
import com.example.ui.theme.SuccessContainer
import com.example.ui.theme.SuccessGreen
import com.example.ui.viewmodel.MainViewModel

@Composable
fun QuizScreen(
    questions: List<QuestionEntity>,
    onStartCategoryQuiz: (String) -> Unit,
    onStartQuickRandomQuiz: () -> Unit
) {
    val categories = remember(questions) {
        questions.map { it.category }.distinct()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("quiz_screen_root")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            // Quick practice hero card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("random_quiz_banner"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "দ্রুত কুইজ অনুশীলন",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "+৫০ XP",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "সকল গ্রামার ও ভোকাবুলারি বিষয় থেকে দৈবচয়ন পদ্ধতিতে ১০টি প্রশ্নের দ্রুত পরীক্ষা দিন।",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = onStartQuickRandomQuiz,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("start_quick_quiz_btn"),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Start"
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "এলোমেলো কুইজ শুরু করুন",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        item {
            Text(
                text = "অধ্যায়ভিত্তিক কুইজ নির্বাচন",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "নির্দিষ্ট গ্রামার বিষয়ের উপর অনুশীলন করে দক্ষতা যাচাই করুন",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        items(categories) { category ->
            val count = questions.count { it.category == category }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartCategoryQuiz(category) }
                    .testTag("category_quiz_$category"),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "$count টি প্রশ্ন সংকলিত",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = "কুইজ দিন",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
            }
        }

        item {
            StartIoBannerAd(adPlacement = "quiz_screen_banner")
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizSessionScreen(
    state: MainViewModel.QuizState,
    onSelectOption: (String) -> Unit,
    onSubmitAnswer: () -> Unit,
    onNextQuestion: () -> Unit,
    onBack: () -> Unit,
    onRestartQuiz: () -> Unit,
    onToggleBookmark: (String, Boolean) -> Unit
) {
    var showInterstitialAd by remember { mutableStateOf(false) }

    if (state.isCompleted) {
        // Quiz Completion Screen
        if (showInterstitialAd) {
            StartIoInterstitialAdDialog(onDismiss = { showInterstitialAd = false })
        }

        val total = state.questions.size
        val correct = state.correctCount
        val wrong = state.wrongCount
        val percentage = if (total > 0) (correct * 100) / total else 0

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("কুইজ ফলাফল", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = onBack) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .clip(CircleShape)
                        .background(if (percentage >= 60) SuccessContainer else MaterialTheme.colorScheme.errorContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (percentage >= 80) "🏆" else if (percentage >= 60) "🎉" else "📚",
                        fontSize = 44.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = if (percentage >= 80) "চমৎকার নৈপুণ্য!" else if (percentage >= 60) "ভালো হয়েছে!" else "আরও অনুশীলন প্রয়োজন!",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "আপনি কুইজটি সফলভাবে সম্পন্ন করেছেন",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Stats Summary Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("quiz_result_summary_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("মোট প্রশ্ন (Total Questions):", color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$total", fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("সঠিক উত্তর (Correct Answers):", color = SuccessGreen)
                            Text("$correct 🎉", fontWeight = FontWeight.Bold, color = SuccessGreen)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("ভুল উত্তর (Wrong Answers):", color = ErrorRed)
                            Text("$wrong", fontWeight = FontWeight.Bold, color = ErrorRed)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("শতাংশ (Percentage):", color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text("$percentage%", fontWeight = FontWeight.Bold)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("অর্জিত পয়েন্ট (XP Earned):", color = MaterialTheme.colorScheme.primary)
                            Text("+${state.earnedXp} XP ✨", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = onRestartQuiz,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("পুনরায় দিন")
                    }

                    Button(
                        onClick = onBack,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("হোমে ফিরুন")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                StartIoBannerAd(adPlacement = "quiz_result_banner")
            }
        }
        return
    }

    val currentQ = state.questions.getOrNull(state.currentIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "প্রশ্ন ${state.currentIndex + 1} / ${state.questions.size}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    currentQ?.let { q ->
                        IconButton(onClick = { onToggleBookmark(q.id, q.isBookmarked) }) {
                            Icon(
                                imageVector = if (q.isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                contentDescription = "Bookmark",
                                tint = if (q.isBookmarked) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        if (currentQ == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("কোনো প্রশ্ন পাওয়া যায়নি।")
            }
            return@Scaffold
        }

        val progress = (state.currentIndex + 1).toFloat() / state.questions.size.toFloat()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Question Card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("active_question_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.primaryContainer)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = currentQ.category,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = currentQ.questionEn,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 24.sp
                        )
                    }
                }
            }

            // Options A, B, C, D
            val options = listOf(
                Pair("A", currentQ.optionA),
                Pair("B", currentQ.optionB),
                Pair("C", currentQ.optionC),
                Pair("D", currentQ.optionD)
            )

            items(options) { (key, text) ->
                val isSelected = state.selectedOption == key
                val isCorrect = key == currentQ.correctOption
                val isSubmitted = state.isAnswerSubmitted

                val (bgColor, borderColor, textColor) = when {
                    isSubmitted && isCorrect -> Triple(SuccessContainer, SuccessGreen, SuccessGreen)
                    isSubmitted && isSelected && !isCorrect -> Triple(ErrorContainer, ErrorRed, ErrorRed)
                    isSelected -> Triple(MaterialTheme.colorScheme.primaryContainer, MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.primary)
                    else -> Triple(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.colorScheme.onSurface)
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(enabled = !isSubmitted) {
                            onSelectOption(key)
                        }
                        .testTag("quiz_option_$key"),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = bgColor),
                    border = CardDefaults.outlinedCardBorder().copy(
                        brush = androidx.compose.ui.graphics.SolidColor(borderColor)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(borderColor.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = key,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = if (isSelected || (isSubmitted && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.weight(1f)
                        )

                        if (isSubmitted && isCorrect) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Correct",
                                tint = SuccessGreen
                            )
                        } else if (isSubmitted && isSelected && !isCorrect) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Wrong",
                                tint = ErrorRed
                            )
                        }
                    }
                }
            }

            // Immediate Answer Feedback and Explanation
            if (state.isAnswerSubmitted) {
                item {
                    val wasCorrect = state.selectedOption == currentQ.correctOption
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("answer_explanation_card"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (wasCorrect) SuccessContainer else ErrorContainer
                        )
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = if (wasCorrect) "সঠিক উত্তর! 🎉" else "ভুল উত্তর। সঠিক উত্তর দেখে নিন।",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (wasCorrect) SuccessGreen else ErrorRed
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "সঠিক বিকল্প: [${currentQ.correctOption}]",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "ব্যাখ্যা: ${currentQ.explanationBn}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }

            // Bottom Action Button (Submit or Next)
            item {
                Spacer(modifier = Modifier.height(8.dp))
                if (!state.isAnswerSubmitted) {
                    Button(
                        onClick = onSubmitAnswer,
                        enabled = state.selectedOption != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("submit_answer_button"),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "উত্তর নিশ্চিত করুন (Submit)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                } else {
                    Button(
                        onClick = onNextQuestion,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("next_question_button"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            text = if (state.currentIndex + 1 < state.questions.size) "পরবর্তী প্রশ্ন (Next) ➔" else "ফলাফল দেখুন (View Result) 🎉",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
