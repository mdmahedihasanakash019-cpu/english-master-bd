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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.data.model.VocabularyEntity
import com.example.ui.theme.GoldYellow
import com.example.ui.theme.SuccessGreen

data class LeaderboardStudent(
    val rank: Int,
    val name: String,
    val institution: String,
    val xp: Int,
    val levelTitle: String,
    val isCurrentUser: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    user: UserEntity?,
    onBack: () -> Unit
) {
    val userXp = user?.xp ?: 180
    val students = remember(userXp) {
        listOf(
            LeaderboardStudent(1, "তানভীর আহমেদ", "ঢাকা বিশ্ববিদ্যালয়", 6450, "Level 8 — Master"),
            LeaderboardStudent(2, "নুসরাত জাহান", "নটর ডেম কলেজ", 5820, "Level 8 — Master"),
            LeaderboardStudent(3, "সাদিয়া আফরিন", "চট্টগ্রাম বিশ্ববিদ্যালয়", 4930, "Level 7 — Expert"),
            LeaderboardStudent(4, "রাকিবুল হাসান", "রাজশাহী কলেজ", 4320, "Level 7 — Expert"),
            LeaderboardStudent(5, "মেহেদী হাসান", "বুয়েট", 3950, "Level 7 — Expert"),
            LeaderboardStudent(6, user?.name ?: "শিক্ষার্থী (আপনি)", "ইংরেজি মাস্টার বিডি", userXp, user?.levelTitle ?: "Level 1 — Beginner", isCurrentUser = true),
            LeaderboardStudent(7, "ফারহানা ইয়াসমিন", "জাহাঙ্গীরনগর বিশ্ববিদ্যালয়", 1250, "Level 4 — Intermediate"),
            LeaderboardStudent(8, "আসিফ ইকবাল", "ঢাকা রেসিডেনসিয়াল মডেল কলেজ", 980, "Level 3 — Elementary")
        ).sortedByDescending { it.xp }.mapIndexed { idx, s -> s.copy(rank = idx + 1) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("জাতীয় লিডারবোর্ড (Leaderboard)") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
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
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.EmojiEvents,
                            contentDescription = null,
                            tint = GoldYellow,
                            modifier = Modifier.size(40.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "শীর্ষ বাংলাদেশী শিক্ষার্থী তালিকা",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "নিয়মিত কুইজ, পরীক্ষা ও অনুশীলনের মাধ্যমে পয়েন্ট বাড়িয়ে শীর্ষে উঠুন!",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.85f)
                            )
                        }
                    }
                }
            }

            items(students) { student ->
                val isMe = student.isCurrentUser
                val rankBadgeColor = when (student.rank) {
                    1 -> GoldYellow
                    2 -> Color(0xFFC0C0C0)
                    3 -> Color(0xFFCD7F32)
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isMe) MaterialTheme.colorScheme.primary.copy(alpha = 0.12f) else MaterialTheme.colorScheme.surface
                    ),
                    border = if (isMe) CardDefaults.outlinedCardBorder().copy(
                        brush = androidx.compose.ui.graphics.SolidColor(MaterialTheme.colorScheme.primary)
                    ) else CardDefaults.outlinedCardBorder()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(rankBadgeColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${student.rank}",
                                fontWeight = FontWeight.Bold,
                                color = if (student.rank in 1..3) Color.Black else MaterialTheme.colorScheme.onSurfaceVariant,
                                fontSize = 13.sp
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = student.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isMe) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
                                )
                                if (isMe) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "(আপনি)",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                            Text(
                                text = "${student.institution} • ${student.levelTitle}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.outline,
                                fontSize = 11.sp
                            )
                        }

                        Text(
                            text = "${student.xp} XP",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarksScreen(
    bookmarkedTopics: List<GrammarTopicEntity>,
    bookmarkedParagraphs: List<ParagraphEntity>,
    bookmarkedVocab: List<VocabularyEntity>,
    bookmarkedQuestions: List<QuestionEntity>,
    onTopicClick: (String) -> Unit,
    onParagraphClick: (String) -> Unit,
    onBack: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("গ্রামার (${bookmarkedTopics.size})", "প্যারাগ্রাফ (${bookmarkedParagraphs.size})", "ভোকাবুলারি (${bookmarkedVocab.size})", "প্রশ্ন (${bookmarkedQuestions.size})")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("সংরক্ষিত তালিকা (Bookmarks)") },
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
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { idx, title ->
                    Tab(
                        selected = selectedTab == idx,
                        onClick = { selectedTab = idx },
                        text = { Text(title, fontSize = 12.sp) }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                when (selectedTab) {
                    0 -> {
                        if (bookmarkedTopics.isEmpty()) {
                            item {
                                Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                                    Text("কোনো গ্রামার বিষয় বুকমার্ক করা নেই।", color = MaterialTheme.colorScheme.outline)
                                }
                            }
                        } else {
                            items(bookmarkedTopics) { topic ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onTopicClick(topic.id) },
                                    shape = RoundedCornerShape(10.dp),
                                    border = CardDefaults.outlinedCardBorder()
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text("${topic.topicNumber}. ${topic.titleEn} (${topic.titleBn})", fontWeight = FontWeight.Bold)
                                        Text(topic.summaryBn, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    }
                                }
                            }
                        }
                    }
                    1 -> {
                        if (bookmarkedParagraphs.isEmpty()) {
                            item {
                                Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                                    Text("কোনো প্যারাগ্রাফ বুকমার্ক করা নেই।", color = MaterialTheme.colorScheme.outline)
                                }
                            }
                        } else {
                            items(bookmarkedParagraphs) { para ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { onParagraphClick(para.id) },
                                    shape = RoundedCornerShape(10.dp),
                                    border = CardDefaults.outlinedCardBorder()
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text(para.titleEn, fontWeight = FontWeight.Bold)
                                        Text(para.titleBn, color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                    2 -> {
                        if (bookmarkedVocab.isEmpty()) {
                            item {
                                Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                                    Text("কোনো শব্দ বুকমার্ক করা নেই।", color = MaterialTheme.colorScheme.outline)
                                }
                            }
                        } else {
                            items(bookmarkedVocab) { word ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    border = CardDefaults.outlinedCardBorder()
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text("${word.word} [${word.pronunciation}]", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                        Text("অর্থ: ${word.meaningBn}", style = MaterialTheme.typography.bodyMedium)
                                        Text("Ex: ${word.exampleSentence}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                                    }
                                }
                            }
                        }
                    }
                    3 -> {
                        if (bookmarkedQuestions.isEmpty()) {
                            item {
                                Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                                    Text("কোনো প্রশ্ন বুকমার্ক করা নেই।", color = MaterialTheme.colorScheme.outline)
                                }
                            }
                        } else {
                            items(bookmarkedQuestions) { q ->
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(10.dp),
                                    border = CardDefaults.outlinedCardBorder()
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Text(q.questionEn, fontWeight = FontWeight.Bold)
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text("সঠিক উত্তর: [${q.correctOption}]", color = SuccessGreen, fontWeight = FontWeight.Bold)
                                        Text("ব্যাখ্যা: ${q.explanationBn}", style = MaterialTheme.typography.bodySmall)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    grammarTopics: List<GrammarTopicEntity>,
    paragraphs: List<ParagraphEntity>,
    vocabulary: List<VocabularyEntity>,
    questions: List<QuestionEntity>,
    onTopicClick: (String) -> Unit,
    onParagraphClick: (String) -> Unit,
    onBack: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    val filteredGrammar = remember(query, grammarTopics) {
        if (query.isBlank()) emptyList() else grammarTopics.filter {
            it.titleEn.contains(query, ignoreCase = true) || it.titleBn.contains(query) || it.category.contains(query, ignoreCase = true)
        }
    }

    val filteredParagraphs = remember(query, paragraphs) {
        if (query.isBlank()) emptyList() else paragraphs.filter {
            it.titleEn.contains(query, ignoreCase = true) || it.titleBn.contains(query)
        }
    }

    val filteredVocab = remember(query, vocabulary) {
        if (query.isBlank()) emptyList() else vocabulary.filter {
            it.word.contains(query, ignoreCase = true) || it.meaningBn.contains(query)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("অনুসন্ধান (Search)") },
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
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("search_text_field"),
                placeholder = { Text("গ্রামার, প্যারাগ্রাফ বা শব্দ খুঁজুন...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        IconButton(onClick = { query = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Clear")
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            if (query.isBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "যেকোনো ইংরেজি বা বাংলা শব্দ লিখে অনুসন্ধান করুন।\nযেমন: Tense, Tree Plantation, Diligent",
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    if (filteredGrammar.isNotEmpty()) {
                        item {
                            Text("গ্রামার বিষয়সমূহ (${filteredGrammar.size})", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                        }
                        items(filteredGrammar) { g ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onTopicClick(g.id) },
                                shape = RoundedCornerShape(10.dp),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("${g.topicNumber}. ${g.titleEn} (${g.titleBn})", fontWeight = FontWeight.Bold)
                                    Text(g.summaryBn, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                }
                            }
                        }
                    }

                    if (filteredParagraphs.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("প্যারাগ্রাফ (${filteredParagraphs.size})", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.secondary)
                        }
                        items(filteredParagraphs) { p ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onParagraphClick(p.id) },
                                shape = RoundedCornerShape(10.dp),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(p.titleEn, fontWeight = FontWeight.Bold)
                                    Text(p.titleBn, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                                }
                            }
                        }
                    }

                    if (filteredVocab.isNotEmpty()) {
                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("শব্দভাণ্ডার (${filteredVocab.size})", fontWeight = FontWeight.Bold, color = Color(0xFF673AB7))
                        }
                        items(filteredVocab) { v ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                border = CardDefaults.outlinedCardBorder()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text("${v.word} - ${v.meaningBn}", fontWeight = FontWeight.Bold)
                                    Text("উচ্চারণ: [${v.pronunciation}]", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                                }
                            }
                        }
                    }

                    if (filteredGrammar.isEmpty() && filteredParagraphs.isEmpty() && filteredVocab.isEmpty()) {
                        item {
                            Box(modifier = Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                                Text("'$query' দিয়ে কোনো ফলাফল পাওয়া যায়নি।", color = MaterialTheme.colorScheme.outline)
                            }
                        }
                    }
                }
            }
        }
    }
}
