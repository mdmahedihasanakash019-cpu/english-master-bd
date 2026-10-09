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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ads.AdverticaAdContainer
import com.example.ads.StartIoBannerAd
import com.example.data.model.UserEntity
import com.example.ui.theme.GoldYellow
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningOrange
import com.example.ui.viewmodel.Screen

@Composable
fun HomeScreen(
    user: UserEntity?,
    isDailyQuizClaimed: Boolean,
    onNavigate: (Screen) -> Unit,
    onStartDailyQuiz: () -> Unit
) {
    val currentXp = user?.xp ?: 0
    val currentLevel = user?.level ?: 1
    val levelTitle = user?.levelTitle ?: "Level 1 — Beginner (শিক্ষানবিস)"
    val streakDays = user?.streakDays ?: 1

    // Calculate XP thresholds for progress
    val (prevThreshold, nextThreshold) = when (currentLevel) {
        1 -> Pair(0, 250)
        2 -> Pair(250, 600)
        3 -> Pair(600, 1100)
        4 -> Pair(1100, 1800)
        5 -> Pair(1800, 2700)
        6 -> Pair(2700, 3800)
        7 -> Pair(3800, 5200)
        else -> Pair(5200, 7000)
    }
    val progressRange = (nextThreshold - prevThreshold).toFloat()
    val progressCurrent = (currentXp - prevThreshold).coerceAtLeast(0).toFloat()
    val progressFraction = (progressCurrent / progressRange).coerceIn(0f, 1f)
    val progressPercent = (progressFraction * 100).toInt()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("home_screen_content")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            // Profile & Streak Welcome Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "স্বাগতম, ${user?.name ?: "শিক্ষার্থী"}! 👋",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "সহজে ইংরেজি গ্রামার ও প্যারাগ্রাফ শিখুন",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                // Daily Streak Badge
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.LocalFireDepartment,
                            contentDescription = "Streak",
                            tint = WarningOrange,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "$streakDays দিন স্ট্রিক",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    }
                }
            }
        }

        // Level & XP Hero Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("level_hero_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "বর্তমান ধাপ (Level)",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                            )
                            Text(
                                text = levelTitle,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.2f))
                                .padding(horizontal = 10.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "✨ $currentXp XP",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Progress bar
                    LinearProgressIndicator(
                        progress = { progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = GoldYellow,
                        trackColor = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.25f)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "পরবর্তী ধাপে অগ্রগতি: $progressPercent%",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                        )
                        Text(
                            text = "$currentXp / $nextThreshold XP",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f)
                        )
                    }
                }
            }
        }

        // Daily Quiz Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartDailyQuiz() }
                    .testTag("daily_quiz_card"),
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
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isDailyQuizClaimed) Icons.Default.CheckCircle else Icons.Default.Stars,
                            contentDescription = "Daily Quiz Icon",
                            tint = MaterialTheme.colorScheme.onSecondary,
                            modifier = Modifier.size(28.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "আজকের কুইজ (Daily Quiz)",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        Text(
                            text = if (isDailyQuizClaimed)
                                "আজকের কুইজ সম্পন্ন হয়েছে! আগামীকাল আবার আসুন।"
                            else
                                "দৈনিক নতুন প্রশ্ন সমাধান করে +১০০ বোনাস XP অর্জন করুন!",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.85f)
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(if (isDailyQuizClaimed) SuccessGreen else MaterialTheme.colorScheme.secondary)
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (isDailyQuizClaimed) "সম্পন্ন" else "শুরু করুন",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Quick Navigation Grid
        item {
            Text(
                text = "প্রধান বিষয় ও অনুশীলনী",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureCard(
                        title = "গ্রামার (Grammar)",
                        subtitle = "২৫টি বিস্তারিত বিষয়",
                        icon = Icons.AutoMirrored.Filled.MenuBook,
                        accentColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f),
                        testTag = "home_grammar_button",
                        onClick = { onNavigate(Screen.Learn(initialTab = 0)) }
                    )
                    FeatureCard(
                        title = "প্যারাগ্রাফ (Writing)",
                        subtitle = "২০+ গুরুত্বপূর্ণ টপিক",
                        icon = Icons.AutoMirrored.Filled.Article,
                        accentColor = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.weight(1f),
                        testTag = "home_paragraph_button",
                        onClick = { onNavigate(Screen.Learn(initialTab = 1)) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureCard(
                        title = "শব্দভাণ্ডার (Vocab)",
                        subtitle = "উচ্চারণ ও অর্থসহ",
                        icon = Icons.Default.Translate,
                        accentColor = Color(0xFF673AB7),
                        modifier = Modifier.weight(1f),
                        testTag = "home_vocab_button",
                        onClick = { onNavigate(Screen.Learn(initialTab = 2)) }
                    )
                    FeatureCard(
                        title = "অনুশীলন (Quiz)",
                        subtitle = "বিষয়ভিত্তিক MCQ",
                        icon = Icons.Default.Quiz,
                        accentColor = Color(0xFFE91E63),
                        modifier = Modifier.weight(1f),
                        testTag = "home_quiz_button",
                        onClick = { onNavigate(Screen.Quiz) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureCard(
                        title = "মডেল পরীক্ষা (Exam)",
                        subtitle = "টাইমার ও রেজাল্টসহ",
                        icon = Icons.Default.School,
                        accentColor = Color(0xFF00897B),
                        modifier = Modifier.weight(1f),
                        testTag = "home_exam_button",
                        onClick = { onNavigate(Screen.Exam) }
                    )
                    FeatureCard(
                        title = "লিডারবোর্ড",
                        subtitle = "শীর্ষ শিক্ষার্থীদের তালিকা",
                        icon = Icons.Default.Leaderboard,
                        accentColor = Color(0xFFF57C00),
                        modifier = Modifier.weight(1f),
                        testTag = "home_leaderboard_button",
                        onClick = { onNavigate(Screen.Leaderboard) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureCard(
                        title = "ভিডিও ক্লাস",
                        subtitle = "ইউটিউব লেসন ও শর্টকাট",
                        icon = Icons.Default.VideoLibrary,
                        accentColor = Color(0xFFC2185B),
                        modifier = Modifier.weight(1f),
                        testTag = "home_video_tutorials_button",
                        onClick = { onNavigate(Screen.VideoTutorials) }
                    )
                    FeatureCard(
                        title = "সংরক্ষিত (Saved)",
                        subtitle = "বুকমার্ক করা নিয়মাবলী",
                        icon = Icons.Default.Bookmark,
                        accentColor = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.weight(1f),
                        testTag = "home_saved_button",
                        onClick = { onNavigate(Screen.Bookmarks) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    FeatureCard(
                        title = "অ্যাডমিন প্যানেল",
                        subtitle = "বিষয় ও প্রশ্ন পরিচালনা",
                        icon = Icons.Default.AdminPanelSettings,
                        accentColor = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.weight(1f),
                        testTag = "home_admin_button",
                        onClick = { onNavigate(Screen.Admin) }
                    )
                }
            }
        }

        // Ads Section: Start.io and Advertica Web Container
        item {
            Text(
                text = "স্পন্সর ও পার্টনার",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(4.dp))
            StartIoBannerAd(adPlacement = "home_banner")
            Spacer(modifier = Modifier.height(8.dp))
            AdverticaAdContainer()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun FeatureCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    accentColor: Color,
    modifier: Modifier = Modifier,
    testTag: String = "",
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() }
            .testTag(testTag),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = CardDefaults.outlinedCardBorder()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(accentColor.copy(alpha = 0.15f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = accentColor,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp
            )
        }
    }
}
