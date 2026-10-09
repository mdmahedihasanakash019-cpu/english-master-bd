package com.example.ui.screens

import android.content.Intent
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Brightness4
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.example.ads.AdConfig
import com.example.data.model.AchievementEntity
import com.example.data.model.ExamEntity
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.ui.theme.ErrorRed
import com.example.ui.theme.GoldYellow
import com.example.ui.theme.SuccessContainer
import com.example.ui.theme.SuccessGreen
import com.example.ui.theme.WarningOrange

@Composable
fun ProfileScreen(
    user: UserEntity?,
    achievements: List<AchievementEntity>,
    isDarkMode: Boolean,
    onToggleDarkMode: () -> Unit,
    onLoginAsGuest: () -> Unit,
    onLoginWithGoogle: (String, String) -> Unit,
    onLoginWithEmail: (String, String) -> Unit,
    onOpenAdmin: () -> Unit
) {
    var showAuthDialog by remember { mutableStateOf(false) }
    var isTestAds by remember { mutableStateOf(AdConfig.isTestMode) }

    if (showAuthDialog) {
        AuthDialog(
            onDismiss = { showAuthDialog = false },
            onGuestLogin = {
                onLoginAsGuest()
                showAuthDialog = false
            },
            onGoogleLogin = { email, name ->
                onLoginWithGoogle(email, name)
                showAuthDialog = false
            },
            onEmailLogin = { email, name ->
                onLoginWithEmail(email, name)
                showAuthDialog = false
            }
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag("profile_screen_root")
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
            // Profile Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🎓",
                            fontSize = 36.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = user?.name ?: "শিক্ষার্থী",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Text(
                        text = user?.email ?: "student@englishmasterbd.com",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = user?.levelTitle ?: "Level 1 — Beginner",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    OutlinedButton(
                        onClick = { showAuthDialog = true },
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (user?.isGuest == true) "অ্যাকাউন্ট তৈরি / লগইন করুন" else "অ্যাকাউন্ট পরিবর্তন করুন")
                    }
                }
            }
        }

        // 8 Progressive Levels Information
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "লেভেল ও র্যাঙ্ক ধাপসমূহ (Levels & Progression)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    val levels = listOf(
                        Pair("Level 1 — Beginner (শিক্ষানবিস)", "০ - ২৪৯ XP"),
                        Pair("Level 2 — Basic (প্রাথমিক)", "২৫০ - ৫৯৯ XP"),
                        Pair("Level 3 — Elementary (এলিমেন্টারি)", "৬০০ - ১,০৯৯ XP"),
                        Pair("Level 4 — Intermediate (মধ্যবর্তী)", "১,১০০ - ১,৭৯৯ XP"),
                        Pair("Level 5 — Upper Intermediate (উচ্চ মধ্যবর্তী)", "১,৮০০ - ২,৬৯৯ XP"),
                        Pair("Level 6 — Advanced (উন্নত)", "২,৭০০ - ৩,৭৯৯ XP"),
                        Pair("Level 7 — Expert (দক্ষ)", "৩,৮০০ - ৫,১৯৯ XP"),
                        Pair("Level 8 — Master (মাস্টার)", "৫,২০০+ XP")
                    )

                    levels.forEachIndexed { idx, (title, xpReq) ->
                        val isReached = (user?.level ?: 1) >= (idx + 1)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = if (isReached) "✅" else "🔒",
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = title,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontWeight = if (isReached) FontWeight.Bold else FontWeight.Normal,
                                    color = if (isReached) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                                )
                            }
                            Text(
                                text = xpReq,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.outline,
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }
        }

        // Achievements & Badges
        item {
            Text(
                text = "অর্জন ও ব্যাজ (Achievements)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        items(achievements) { ach ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (ach.isUnlocked) SuccessContainer else MaterialTheme.colorScheme.surface
                ),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(if (ach.isUnlocked) SuccessGreen else MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (ach.isUnlocked) "🏅" else "🔒",
                            fontSize = 20.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${ach.titleBn} (${ach.titleEn})",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (ach.isUnlocked) SuccessGreen else MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = ach.descriptionBn,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    if (ach.isUnlocked) {
                        Text(
                            text = "অর্জিত",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = SuccessGreen
                        )
                    }
                }
            }
        }

        // Settings (Dark Mode, Ads Toggle, Admin)
        item {
            Text(
                text = "অ্যাপ সেটিংস (Settings)",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                border = CardDefaults.outlinedCardBorder()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    // Dark Mode Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("ডার্ক মোড (Dark Theme)", fontWeight = FontWeight.SemiBold)
                            Text("রাতের বেলায় চোখের সুরক্ষার জন্য", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                        }
                        Switch(
                            checked = isDarkMode,
                            onCheckedChange = { onToggleDarkMode() }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Start.io Test Ads Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("Start.io টেস্ট অ্যাড মোড", fontWeight = FontWeight.SemiBold)
                            Text("App ID: 209381272", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                        }
                        Switch(
                            checked = isTestAds,
                            onCheckedChange = {
                                isTestAds = it
                                AdConfig.isTestMode = it
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Admin Access Button
                    Button(
                        onClick = onOpenAdmin,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.outline)
                    ) {
                        Icon(Icons.Default.AdminPanelSettings, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("অ্যাডমিন প্যানেলে প্রবেশ করুন")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Share App Button
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "English Master BD - সহজে বাংলা ব্যাখ্যায় ইংরেজি গ্রামার ও প্যারাগ্রাফ শিখুন!\nঅ্যাপ লিংক: https://ais-pre-khwv4lcqpoczw2rdd7rh5h-95902515875.asia-east1.run.app"
                                )
                            }
                            context.startActivity(Intent.createChooser(shareIntent, "বন্ধুদের সাথে শেয়ার করুন"))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("share_app_button"),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("বন্ধুদের সাথে অ্যাপটি শেয়ার করুন")
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun AuthDialog(
    onDismiss: () -> Unit,
    onGuestLogin: () -> Unit,
    onGoogleLogin: (String, String) -> Unit,
    onEmailLogin: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var isEmailMode by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("শিক্ষার্থী লগইন ও অ্যাকাউন্ট", fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "আপনার শিক্ষাগত অগ্রগতি এবং XP স্থায়ীভাবে সংরক্ষণ করতে একটি মাধ্যম নির্বাচন করুন:",
                    style = MaterialTheme.typography.bodySmall
                )

                if (!isEmailMode) {
                    Button(
                        onClick = { onGoogleLogin("student@gmail.com", "মাহাদী হাসান") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Google দিয়ে দ্রুত সাইন-ইন")
                    }

                    OutlinedButton(
                        onClick = { isEmailMode = true },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("ইমেইল ও পাসওয়ার্ড দিয়ে লগইন")
                    }

                    TextButton(
                        onClick = onGuestLogin,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("অতিথি হিসেবে চালিয়ে যান (Guest Mode)")
                    }
                } else {
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("আপনার নাম") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("ইমেইল ঠিকানা") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = {
                            if (email.isNotBlank() && name.isNotBlank()) {
                                onEmailLogin(email, name)
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("অ্যাকাউন্ট তৈরি করুন")
                    }
                }
            }
        },
        confirmButton = {},
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("বাতিল")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(
    grammarTopics: List<GrammarTopicEntity>,
    paragraphs: List<ParagraphEntity>,
    questions: List<QuestionEntity>,
    exams: List<ExamEntity>,
    onSaveGrammar: (GrammarTopicEntity) -> Unit,
    onDeleteGrammar: (GrammarTopicEntity) -> Unit,
    onSaveParagraph: (ParagraphEntity) -> Unit,
    onDeleteParagraph: (ParagraphEntity) -> Unit,
    onSaveQuestion: (QuestionEntity) -> Unit,
    onDeleteQuestion: (QuestionEntity) -> Unit,
    onSaveExam: (ExamEntity) -> Unit,
    onDeleteExam: (ExamEntity) -> Unit,
    onResetDatabase: () -> Unit,
    onBack: () -> Unit
) {
    var isUnlocked by remember { mutableStateOf(false) }
    var pinCode by remember { mutableStateOf("") }
    var pinError by remember { mutableStateOf(false) }

    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("গ্রামার", "প্যারাগ্রাফ", "প্রশ্ন ব্যাংক", "পরীক্ষা", "রিসেট")

    var showAddGrammarDialog by remember { mutableStateOf(false) }
    var showAddParagraphDialog by remember { mutableStateOf(false) }
    var showAddQuestionDialog by remember { mutableStateOf(false) }
    var showAddExamDialog by remember { mutableStateOf(false) }
    var showResetConfirmation by remember { mutableStateOf(false) }

    if (!isUnlocked) {
        // Admin PIN lock protection
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("অ্যাডমিন যাচাইকরণ") },
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
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Security,
                    contentDescription = "Security",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "অ্যাডমিন প্যানেল সুরক্ষাবিধি",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "অননুমোদিত পরিবর্তন রোধে অ্যাডমিন পিন প্রদান করুন (ডিফল্ট পিন: 1234)",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = pinCode,
                    onValueChange = {
                        pinCode = it
                        pinError = false
                    },
                    label = { Text("অ্যাডমিন পিন (PIN)") },
                    isError = pinError,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("admin_pin_input")
                )

                if (pinError) {
                    Text("ভুল পিন কোড! দয়া করে 1234 ব্যবহার করুন।", color = ErrorRed, style = MaterialTheme.typography.bodySmall)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (pinCode == "1234" || pinCode == "admin") {
                            isUnlocked = true
                        } else {
                            pinError = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("admin_unlock_button")
                ) {
                    Text("প্যানেল আনলক করুন")
                }
            }
        }
        return
    }

    // Unlocked Admin Panel
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("অ্যাডমিন ড্যাশবোর্ড (Admin Panel)") },
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

            when (selectedTab) {
                0 -> {
                    // Grammar Management
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("মোট ${grammarTopics.size}টি গ্রামার বিষয়", fontWeight = FontWeight.Bold)
                            Button(onClick = { showAddGrammarDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("নতুন বিষয় যোগ")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(grammarTopics) { g ->
                                Card(modifier = Modifier.fillMaxWidth(), border = CardDefaults.outlinedCardBorder()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text("${g.topicNumber}. ${g.titleEn} (${g.titleBn})", fontWeight = FontWeight.Bold)
                                            Text("ক্যাটাগরি: ${g.category}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                                        }
                                        IconButton(onClick = { onDeleteGrammar(g) }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ErrorRed)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                1 -> {
                    // Paragraph Management
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("মোট ${paragraphs.size}টি প্যারাগ্রাফ", fontWeight = FontWeight.Bold)
                            Button(onClick = { showAddParagraphDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("নতুন প্যারাগ্রাফ")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(paragraphs) { p ->
                                Card(modifier = Modifier.fillMaxWidth(), border = CardDefaults.outlinedCardBorder()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(p.titleEn, fontWeight = FontWeight.Bold)
                                            Text(p.titleBn, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
                                        }
                                        IconButton(onClick = { onDeleteParagraph(p) }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ErrorRed)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                2 -> {
                    // Question Bank Management
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("মোট ${questions.size}টি প্রশ্ন সংকলিত", fontWeight = FontWeight.Bold)
                            Button(onClick = { showAddQuestionDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("নতুন MCQ প্রশ্ন")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(questions) { q ->
                                Card(modifier = Modifier.fillMaxWidth(), border = CardDefaults.outlinedCardBorder()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(q.questionEn, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodyMedium)
                                            Text("সঠিক: [${q.correctOption}] | ক্যাটাগরি: ${q.category}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
                                        }
                                        IconButton(onClick = { onDeleteQuestion(q) }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ErrorRed)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                3 -> {
                    // Exams Management
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("মোট ${exams.size}টি পরীক্ষা", fontWeight = FontWeight.Bold)
                            Button(onClick = { showAddExamDialog = true }) {
                                Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("নতুন পরীক্ষা তৈরি")
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            items(exams) { ex ->
                                Card(modifier = Modifier.fillMaxWidth(), border = CardDefaults.outlinedCardBorder()) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(ex.titleBn, fontWeight = FontWeight.Bold)
                                            Text("${ex.examType} • ${ex.durationMinutes} মিনিট • ${ex.totalQuestions} টি প্রশ্ন", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.outline)
                                        }
                                        IconButton(onClick = { onDeleteExam(ex) }) {
                                            Icon(Icons.Default.Delete, contentDescription = "Delete", tint = ErrorRed)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                4 -> {
                    // System Stats & Reset
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.RestartAlt, contentDescription = null, modifier = Modifier.size(54.dp), tint = WarningOrange)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text("ডাটাবেজ পরিসংখ্যান ও রিসেট", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            "সকল ২৫টি গ্রামার বিষয়, ২০টি স্ট্যান্ডার্ড প্যারাগ্রাফ ও প্রশ্ন ব্যাংককে মূল ডিফল্ট অবস্থায় ফিরিয়ে নিতে চান?",
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { showResetConfirmation = true },
                            colors = ButtonDefaults.buttonColors(containerColor = ErrorRed),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("ডিফল্ট শিক্ষামূলক ডাটাবেজ রিস্টোর করুন")
                        }
                    }
                }
            }
        }
    }

    if (showResetConfirmation) {
        AlertDialog(
            onDismissRequest = { showResetConfirmation = false },
            title = { Text("ডাটাবেজ রিসেট নিশ্চিতকরণ") },
            text = { Text("এটি অ্যাপের সকল শিক্ষামূলক কনটেন্ট ও গ্রামার বিষয়কে মূল স্ট্যান্ডার্ড সংস্করণে রিস্টোর করবে।") },
            confirmButton = {
                Button(
                    onClick = {
                        onResetDatabase()
                        showResetConfirmation = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                ) {
                    Text("রিসেট করুন")
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirmation = false }) { Text("বাতিল") }
            }
        )
    }

    // Add Grammar Dialog
    if (showAddGrammarDialog) {
        var titleEn by remember { mutableStateOf("") }
        var titleBn by remember { mutableStateOf("") }
        var category by remember { mutableStateOf("Fundamentals") }
        var explanation by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddGrammarDialog = false },
            title = { Text("নতুন গ্রামার বিষয় যোগ করুন") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = titleEn, onValueChange = { titleEn = it }, label = { Text("Title (English)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = titleBn, onValueChange = { titleBn = it }, label = { Text("শিরোনাম (বাংলা)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = explanation, onValueChange = { explanation = it }, label = { Text("বাংলা ব্যাখ্যা") }, modifier = Modifier.fillMaxWidth(), maxLines = 4)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (titleEn.isNotBlank() && titleBn.isNotBlank()) {
                            onSaveGrammar(
                                GrammarTopicEntity(
                                    id = "topic_${System.currentTimeMillis()}",
                                    topicNumber = grammarTopics.size + 1,
                                    titleEn = titleEn,
                                    titleBn = titleBn,
                                    category = category,
                                    summaryBn = titleBn,
                                    explanationBn = explanation,
                                    rulesJson = "[]",
                                    examplesJson = "[]",
                                    commonMistakesJson = "[]"
                                )
                            )
                            showAddGrammarDialog = false
                        }
                    }
                ) { Text("সংরক্ষণ করুন") }
            },
            dismissButton = {
                TextButton(onClick = { showAddGrammarDialog = false }) { Text("বাতিল") }
            }
        )
    }

    // Add Paragraph Dialog
    if (showAddParagraphDialog) {
        var titleEn by remember { mutableStateOf("") }
        var titleBn by remember { mutableStateOf("") }
        var content by remember { mutableStateOf("") }
        var meaningBn by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddParagraphDialog = false },
            title = { Text("নতুন প্যারাগ্রাফ যোগ করুন") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = titleEn, onValueChange = { titleEn = it }, label = { Text("Paragraph Title (English)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = titleBn, onValueChange = { titleBn = it }, label = { Text("শিরোনাম (বাংলা)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = content, onValueChange = { content = it }, label = { Text("English Content") }, modifier = Modifier.fillMaxWidth(), maxLines = 4)
                    OutlinedTextField(value = meaningBn, onValueChange = { meaningBn = it }, label = { Text("বাংলা অনুবাদ") }, modifier = Modifier.fillMaxWidth(), maxLines = 4)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (titleEn.isNotBlank() && content.isNotBlank()) {
                            onSaveParagraph(
                                ParagraphEntity(
                                    id = "para_${System.currentTimeMillis()}",
                                    titleEn = titleEn,
                                    titleBn = titleBn,
                                    category = "Academic",
                                    englishContent = content,
                                    banglaMeaning = meaningBn,
                                    vocabularyJson = "[]",
                                    keySentencesJson = "[]",
                                    memorizationTipsBn = "ধাপে ধাপে মুখস্থ করুন।"
                                )
                            )
                            showAddParagraphDialog = false
                        }
                    }
                ) { Text("সংরক্ষণ করুন") }
            },
            dismissButton = {
                TextButton(onClick = { showAddParagraphDialog = false }) { Text("বাতিল") }
            }
        )
    }

    // Add Question Dialog
    if (showAddQuestionDialog) {
        var questionEn by remember { mutableStateOf("") }
        var optA by remember { mutableStateOf("") }
        var optB by remember { mutableStateOf("") }
        var optC by remember { mutableStateOf("") }
        var optD by remember { mutableStateOf("") }
        var correct by remember { mutableStateOf("A") }
        var explanation by remember { mutableStateOf("") }

        AlertDialog(
            onDismissRequest = { showAddQuestionDialog = false },
            title = { Text("নতুন MCQ প্রশ্ন যোগ করুন") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    OutlinedTextField(value = questionEn, onValueChange = { questionEn = it }, label = { Text("প্রশ্ন (English)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = optA, onValueChange = { optA = it }, label = { Text("Option A") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = optB, onValueChange = { optB = it }, label = { Text("Option B") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = optC, onValueChange = { optC = it }, label = { Text("Option C") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = optD, onValueChange = { optD = it }, label = { Text("Option D") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = correct, onValueChange = { correct = it.uppercase() }, label = { Text("Correct (A/B/C/D)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = explanation, onValueChange = { explanation = it }, label = { Text("বাংলা ব্যাখ্যা") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (questionEn.isNotBlank() && optA.isNotBlank()) {
                            onSaveQuestion(
                                QuestionEntity(
                                    id = "q_${System.currentTimeMillis()}",
                                    questionEn = questionEn,
                                    optionA = optA,
                                    optionB = optB,
                                    optionC = optC,
                                    optionD = optD,
                                    correctOption = correct.take(1),
                                    explanationBn = explanation,
                                    category = "Grammar"
                                )
                            )
                            showAddQuestionDialog = false
                        }
                    }
                ) { Text("সংরক্ষণ করুন") }
            },
            dismissButton = {
                TextButton(onClick = { showAddQuestionDialog = false }) { Text("বাতিল") }
            }
        )
    }

    // Add Exam Dialog
    if (showAddExamDialog) {
        var titleBn by remember { mutableStateOf("") }
        var duration by remember { mutableStateOf("15") }
        var totalQ by remember { mutableStateOf("15") }

        AlertDialog(
            onDismissRequest = { showAddExamDialog = false },
            title = { Text("নতুন পরীক্ষা তৈরি করুন") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(value = titleBn, onValueChange = { titleBn = it }, label = { Text("পরীক্ষার নাম (বাংলা)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = duration, onValueChange = { duration = it }, label = { Text("সময়কাল (মিনিট)") }, modifier = Modifier.fillMaxWidth())
                    OutlinedTextField(value = totalQ, onValueChange = { totalQ = it }, label = { Text("মোট প্রশ্ন সংখ্যা") }, modifier = Modifier.fillMaxWidth())
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (titleBn.isNotBlank()) {
                            onSaveExam(
                                ExamEntity(
                                    id = "exam_${System.currentTimeMillis()}",
                                    titleEn = titleBn,
                                    titleBn = titleBn,
                                    examType = "Model Test",
                                    durationMinutes = duration.toIntOrNull() ?: 15,
                                    totalQuestions = totalQ.toIntOrNull() ?: 15,
                                    difficulty = "Standard",
                                    category = "Competitive"
                                )
                            )
                            showAddExamDialog = false
                        }
                    }
                ) { Text("তৈরি করুন") }
            },
            dismissButton = {
                TextButton(onClick = { showAddExamDialog = false }) { Text("বাতিল") }
            }
        )
    }
}
