package com.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String = "local_user",
    val name: String = "শিক্ষার্থী (Student)",
    val email: String = "student@englishmasterbd.com",
    val profileImage: String = "avatar_1",
    val level: Int = 1,
    val levelTitle: String = "Beginner (শিক্ষানবিস)",
    val xp: Int = 120,
    val streakDays: Int = 3,
    val lastActiveDate: String = "",
    val lastDailyQuizDate: String = "",
    val isGuest: Boolean = true,
    val isAdmin: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "grammar_topics")
data class GrammarTopicEntity(
    @PrimaryKey val id: String,
    val topicNumber: Int,
    val titleEn: String,
    val titleBn: String,
    val category: String,
    val summaryBn: String,
    val explanationBn: String,
    val rulesJson: String,
    val examplesJson: String,
    val commonMistakesJson: String,
    val isBookmarked: Boolean = false,
    val isCompleted: Boolean = false,
    val completedQuizScore: Int = 0
)

@Entity(tableName = "paragraphs")
data class ParagraphEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleBn: String,
    val category: String,
    val englishContent: String,
    val banglaMeaning: String,
    val vocabularyJson: String,
    val keySentencesJson: String,
    val memorizationTipsBn: String,
    val isBookmarked: Boolean = false,
    val isCompleted: Boolean = false
)

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id: String,
    val questionEn: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctOption: String, // "A", "B", "C", "D"
    val explanationBn: String,
    val category: String,
    val difficulty: String = "Medium", // Easy, Medium, Hard
    val grammarTopicId: String? = null,
    val paragraphId: String? = null,
    val isDailyQuiz: Boolean = false,
    val isBookmarked: Boolean = false
)

@Entity(tableName = "exams")
data class ExamEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleBn: String,
    val examType: String, // "Grammar Exam", "Paragraph Exam", "Mixed English Exam", "Model Test", "Daily Test"
    val durationMinutes: Int,
    val totalQuestions: Int,
    val difficulty: String,
    val category: String
)

@Entity(tableName = "exam_results")
data class ExamResultEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val examId: String,
    val examTitle: String,
    val score: Int,
    val totalQuestions: Int,
    val percentage: Int,
    val timeTakenSeconds: Int,
    val dateTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "vocabulary")
data class VocabularyEntity(
    @PrimaryKey val id: String,
    val word: String,
    val pronunciation: String,
    val meaningBn: String,
    val partsOfSpeech: String,
    val exampleSentence: String,
    val exampleSentenceBn: String,
    val category: String,
    val isBookmarked: Boolean = false
)

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleBn: String,
    val descriptionBn: String,
    val badgeIcon: String,
    val isUnlocked: Boolean = false,
    val requiredProgress: Int = 1,
    val currentProgress: Int = 0
)

@Entity(tableName = "video_lessons")
data class VideoLessonEntity(
    @PrimaryKey val id: String,
    val titleEn: String,
    val titleBn: String,
    val youtubeVideoId: String,
    val category: String,
    val duration: String,
    val instructor: String,
    val descriptionBn: String,
    val isBookmarked: Boolean = false
)

data class GrammarRule(
    val ruleNo: Int,
    val ruleEn: String,
    val ruleBn: String,
    val exampleEn: String,
    val exampleBn: String
)

data class GrammarExample(
    val english: String,
    val bangla: String,
    val note: String? = null
)

data class CommonMistake(
    val wrong: String,
    val correct: String,
    val explanationBn: String
)

data class VocabWord(
    val word: String,
    val pronunciation: String,
    val meaningBn: String
)
