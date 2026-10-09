package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.ads.StartIoBannerAd
import com.example.data.model.QuestionEntity
import com.example.ui.theme.GoldYellow
import com.example.ui.theme.SuccessContainer
import com.example.ui.theme.SuccessGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyQuizScreen(
    dailyQuestions: List<QuestionEntity>,
    isAlreadyClaimed: Boolean,
    onClaimReward: () -> Unit,
    onBack: () -> Unit
) {
    var hasAnsweredAll by remember { mutableStateOf(isAlreadyClaimed) }
    var selectedAnswers by remember { mutableStateOf<Map<String, String>>(emptyMap()) }
    var showResults by remember { mutableStateOf(isAlreadyClaimed) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("আজকের কুইজ (Daily Quiz)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
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
                // Daily Quiz Header Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.secondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Stars,
                                contentDescription = null,
                                tint = GoldYellow,
                                modifier = Modifier.size(30.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "আজকের চ্যালেঞ্জ ও বোনাস",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Text(
                                text = if (isAlreadyClaimed)
                                    "আপনি আজকের ১০০ XP বোনাস ইতোমধ্যে গ্রহণ করেছেন!"
                                else
                                    "সকল প্রশ্নের উত্তর দিয়ে +১০০ বোনাস XP এবং স্ট্রিক বৃদ্ধি করুন!",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }

            if (isAlreadyClaimed) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = SuccessContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = SuccessGreen,
                                modifier = Modifier.size(48.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "আজকের কুইজ সম্পন্ন হয়েছে! 🎉",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = SuccessGreen
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "আগামীকাল নতুন প্রশ্ন নিয়ে পুনরায় আপনার জন্য কুইজ উন্মুক্ত হবে।",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(dailyQuestions) { q ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface
                        ),
                        border = CardDefaults.outlinedCardBorder()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Text(
                                text = q.questionEn,
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                            val options = listOf(
                                Pair("A", q.optionA),
                                Pair("B", q.optionB),
                                Pair("C", q.optionC),
                                Pair("D", q.optionD)
                            )

                            options.forEach { (key, optText) ->
                                val isSelected = selectedAnswers[q.id] == key
                                val isCorrect = key == q.correctOption

                                val bgColor = when {
                                    showResults && isCorrect -> SuccessContainer
                                    isSelected -> MaterialTheme.colorScheme.primaryContainer
                                    else -> MaterialTheme.colorScheme.surface
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(bgColor)
                                        .clickable(enabled = !showResults) {
                                            selectedAnswers = selectedAnswers + (q.id to key)
                                        }
                                        .padding(10.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "[$key] $optText",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (isSelected || (showResults && isCorrect)) FontWeight.Bold else FontWeight.Normal,
                                        color = if (showResults && isCorrect) SuccessGreen else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            if (showResults) {
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = "ব্যাখ্যা: ${q.explanationBn}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }

                item {
                    val allAnswered = dailyQuestions.isNotEmpty() && dailyQuestions.all { selectedAnswers.containsKey(it.id) }
                    Button(
                        onClick = {
                            showResults = true
                            onClaimReward()
                        },
                        enabled = allAnswered && !showResults,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("claim_daily_quiz_btn"),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                    ) {
                        Text(
                            text = if (showResults) "বোনাস ১০০ XP অর্জিত হয়েছে 🎉" else "কুইজ জমা দিন ও +১০০ XP বোনাস নিন",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            item {
                StartIoBannerAd(adPlacement = "daily_quiz_banner")
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
