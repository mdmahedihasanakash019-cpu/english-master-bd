package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ads.AdverticaAdContainer
import com.example.ads.StartIoBannerAd
import com.example.data.model.ExamEntity
import com.example.data.model.ExamResultEntity
import com.example.data.model.QuestionEntity
import com.example.ui.theme.ErrorContainer
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldYellow
import com.example.ui.theme.SuccessContainer
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningOrange
import com.example.ui.viewmodel.MainViewModel

@Composable
fun ExamListScreen(
    exams: List<ExamEntity>,
    results: List<ExamResultEntity>,
    onStartExam: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("exam_list_screen")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "মডেল টেস্ট ও পরীক্ষা কেন্দ্র",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "টাইমার সহ রিয়েল বোর্ড ও ভর্তি পরীক্ষার আদলে পরীক্ষা দিয়ে নিজের প্রস্তুতি যাচাই করুন।",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.9f)
                    )
                }
            }
        }

        item {
            Text(
                text = "উপলব্ধ পরীক্ষাসমূহ (Available Exams)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(exams, key = { it.id }) { exam ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartExam(exam.id) }
                    .testTag("exam_card_${exam.id}"),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = exam.examType,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = "Duration",
                                tint = MaterialTheme.colorScheme.outline,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${exam.durationMinutes} মিনিট",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.outline,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = exam.titleBn,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = exam.titleEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "মোট প্রশ্ন: ${exam.totalQuestions} টি | মান: ${exam.difficulty}",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.outline
                        )

                        Button(
                            onClick = { onStartExam(exam.id) },
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.testTag("start_exam_btn_${exam.id}")
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("পরীক্ষা দিন")
                        }
                    }
                }
            }
        }

        // Recent Exam Results
        if (results.isNotEmpty()) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "পূর্ববর্তী পরীক্ষার ফলাফল (Recent Scores)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            items(results.take(3)) { res ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = res.examTitle,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "সময় ব্যয়: ${res.timeTakenSeconds / 60} মিনিট ${res.timeTakenSeconds % 60} সেকেন্ড",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (res.percentage >= 60) SuccessContainer else ErrorContainer)
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "${res.score}/${res.totalQuestions} (${res.percentage}%)",
                                fontWeight = FontWeight.Bold,
                                color = if (res.percentage >= 60) SuccessGreen else ErrorRed
                            )
                        }
                    }
                }
            }
        }

        item {
            StartIoBannerAd(adPlacement = "exam_list_banner")
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamSessionScreen(
    state: MainViewModel.ExamState,
    onSelectOption: (String, String) -> Unit, // questionId, chosenOption
    onJumpToQuestion: (Int) -> Unit,
    onSubmitExam: () -> Unit,
    onBack: () -> Unit
) {
    var showSubmitConfirmationDialog by remember { mutableStateOf(false) }

    val remainingMinutes = state.remainingSeconds / 60
    val remainingSecs = state.remainingSeconds % 60
    val timeFormatted = String.format(java.util.Locale.US, "%02d:%02d", remainingMinutes, remainingSecs)
    val isTimeLow = state.remainingSeconds <= 120

    val currentQ = state.questions.getOrNull(state.currentIndex)

    if (showSubmitConfirmationDialog) {
        AlertDialog(
            onDismissRequest = { showSubmitConfirmationDialog = false },
            title = { Text("পরীক্ষা জমা দিতে চান?") },
            text = {
                val answeredCount = state.userAnswers.size
                val totalCount = state.questions.size
                Text("আপনি $totalCount টি প্রশ্নের মধ্যে $answeredCount টি উত্তর দিয়েছেন। নিশ্চিতভাবে জমা দিতে চান?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSubmitConfirmationDialog = false
                        onSubmitExam()
                    },
                    modifier = Modifier.testTag("confirm_submit_exam_button")
                ) {
                    Text("হ্যাঁ, জমা দিন")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSubmitConfirmationDialog = false }) {
                    Text("ফিরে যান")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = state.exam?.titleBn ?: "পরীক্ষা",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "প্রশ্ন ${state.currentIndex + 1} / ${state.questions.size}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { showSubmitConfirmationDialog = true }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Exit exam")
                    }
                },
                actions = {
                    // Live Countdown Timer Badge
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(if (isTimeLow) ErrorRed else MaterialTheme.colorScheme.primary)
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Alarm,
                                contentDescription = "Timer",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = timeFormatted,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        if (currentQ == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("প্রশ্ন লোড হচ্ছে...")
            }
            return@Scaffold
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Horizontal Question Navigation Pills
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(state.questions) { index, q ->
                    val isCurrent = index == state.currentIndex
                    val isAnswered = state.userAnswers.containsKey(q.id)

                    val bgColor = when {
                        isCurrent -> MaterialTheme.colorScheme.primary
                        isAnswered -> SuccessGreen
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                    val textColor = when {
                        isCurrent || isAnswered -> Color.White
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(bgColor)
                            .clickable { onJumpToQuestion(index) }
                            .testTag("exam_pill_$index"),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${index + 1}",
                            color = textColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                item {
                    // Question text
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("exam_question_card"),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "প্রশ্ন নং ${state.currentIndex + 1}:",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
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

                // Options (A, B, C, D)
                val options = listOf(
                    Pair("A", currentQ.optionA),
                    Pair("B", currentQ.optionB),
                    Pair("C", currentQ.optionC),
                    Pair("D", currentQ.optionD)
                )

                val selectedAnswer = state.userAnswers[currentQ.id]

                items(options) { (optKey, optText) ->
                    val isChosen = selectedAnswer == optKey

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                onSelectOption(currentQ.id, optKey)
                            }
                            .testTag("exam_option_${currentQ.id}_$optKey"),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isChosen) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder().copy(
                            brush = androidx.compose.ui.graphics.SolidColor(
                                if (isChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                            )
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
                                    .background(if (isChosen) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = optKey,
                                    color = if (isChosen) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = optText,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = if (isChosen) FontWeight.Bold else FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            // Bottom Exam Control Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (state.currentIndex > 0) {
                    Button(
                        onClick = { onJumpToQuestion(state.currentIndex - 1) },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Text("পূর্ববর্তী", color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                if (state.currentIndex + 1 < state.questions.size) {
                    Button(
                        onClick = { onJumpToQuestion(state.currentIndex + 1) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("পরবর্তী ➔")
                    }
                } else {
                    Button(
                        onClick = { showSubmitConfirmationDialog = true },
                        modifier = Modifier
                            .weight(1f)
                            .testTag("submit_exam_button"),
                        colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen)
                    ) {
                        Text("পরীক্ষা জমা দিন (Submit)")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamResultDetailScreen(
    result: ExamResultEntity,
    questions: List<QuestionEntity>,
    userAnswers: Map<String, String>,
    onBackToHome: () -> Unit
) {
    val score = result.score
    val total = result.totalQuestions
    val percentage = result.percentage
    val isPassed = percentage >= 60

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("পরীক্ষার চূড়ান্ত ফলাফল ও সমাধান") },
                navigationIcon = {
                    IconButton(onClick = onBackToHome) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Home")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                // Overall Score Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("exam_final_result_card"),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isPassed) SuccessContainer else ErrorContainer
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (isPassed) "অভিনন্দন! উত্তীর্ণ হয়েছেন 🎉" else "দুঃখিত! উত্তীর্ণ হতে পারেননি",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = if (isPassed) SuccessGreen else ErrorRed
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "${result.examTitle}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = "$score / $total",
                            style = MaterialTheme.typography.displayMedium,
                            fontWeight = FontWeight.Bold,
                            color = if (isPassed) SuccessGreen else ErrorRed
                        )
                        Text(
                            text = "প্রাপ্ত নম্বর: $percentage%",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "সময় লেগেছে: ${result.timeTakenSeconds / 60} মিনিট ${result.timeTakenSeconds % 60} সেকেন্ড",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            item {
                // Allowed ad placement on Exam Result screen
                StartIoBannerAd(adPlacement = "exam_result_screen_banner")
                Spacer(modifier = Modifier.height(8.dp))
                AdverticaAdContainer()
            }

            item {
                Text(
                    text = "বিস্তারিত সমাধান ও ব্যাখ্যা (Detailed Solutions)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // Detailed review of each question
            itemsIndexed(questions) { index, q ->
                val userChoice = userAnswers[q.id]
                val isCorrect = userChoice == q.correctOption

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = CardDefaults.outlinedCardBorder()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "প্রশ্ন ${index + 1}:",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (isCorrect) Icons.Default.CheckCircle else Icons.Default.Close,
                                    contentDescription = null,
                                    tint = if (isCorrect) SuccessGreen else ErrorRed,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isCorrect) "সঠিক (+১)" else "ভুল (০)",
                                    color = if (isCorrect) SuccessGreen else ErrorRed,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = q.questionEn,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "আপনার উত্তর: [${userChoice ?: "উত্তর দেননি"}] | সঠিক উত্তর: [${q.correctOption}]",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isCorrect) SuccessGreen else ErrorRed
                        )

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "ব্যাখ্যা: ${q.explanationBn}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item {
                Button(
                    onClick = onBackToHome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("হোমে ফিরে যান")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
