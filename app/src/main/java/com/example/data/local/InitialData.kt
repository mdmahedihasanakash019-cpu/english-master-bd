package com.example.data.local

import com.example.data.model.AchievementEntity
import com.example.data.model.ExamEntity
import com.example.data.model.GrammarTopicEntity
import com.example.data.model.ParagraphEntity
import com.example.data.model.QuestionEntity
import com.example.data.model.UserEntity
import com.example.data.model.VideoLessonEntity
import com.example.data.model.VocabularyEntity

object InitialData {

    val defaultUser = UserEntity(
        id = "local_user",
        name = "শিক্ষার্থী (Student)",
        email = "student@englishmasterbd.com",
        profileImage = "avatar_1",
        level = 1,
        levelTitle = "Level 1 — Beginner (শিক্ষানবিস)",
        xp = 180,
        streakDays = 3,
        lastActiveDate = "2026-10-08",
        lastDailyQuizDate = "",
        isGuest = false,
        isAdmin = false
    )

    val grammarTopics: List<GrammarTopicEntity> = listOf(
        GrammarTopicEntity(
            id = "parts_of_speech",
            topicNumber = 1,
            titleEn = "Parts of Speech",
            titleBn = "পদ প্রকরণ",
            category = "Fundamentals",
            summaryBn = "বাক্যে ব্যবহৃত প্রতিটি অর্থপূর্ণ শব্দকেই Parts of Speech বলে। ইংরেজি ব্যাকরণে এটি মোট ৮ প্রকার।",
            explanationBn = "ইংরেজিতে বাক্য গঠন করার মূল ভিত্তি হলো Parts of Speech। বাক্যে প্রতিটি শব্দের সুনির্দিষ্ট কাজ রয়েছে। যেমন: Noun কোনো কিছুর নাম প্রকাশ করে, Verb দ্বারা কাজ করা বোঝায় এবং Adjective কোনো কিছুর দোষ-গুণ নির্দেশ করে। বাক্যের অর্থ সুস্পষ্ট করতে এদের সঠিক ব্যবহার অত্যন্ত জরুরি।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Words are categorized by their function in a sentence.","ruleBn":"বাক্যে একটি শব্দের অবস্থান ও কাজ অনুযায়ী তার Parts of Speech নির্ধারিত হয়।","exampleEn":"Water is life (Noun). I water the plants (Verb).","exampleBn":"প্রথম বাক্যে Water নাম (Noun), দ্বিতীয় বাক্যে পানি দেওয়া (Verb)।"},
                {"ruleNo":2,"ruleEn":"Eight parts: Noun, Pronoun, Adjective, Verb, Adverb, Preposition, Conjunction, Interjection.","ruleBn":"মোট আটটি প্রকারভেদ রয়েছে।","exampleEn":"Alas! He ran very fast and won the race.","exampleBn":"এই বাক্যে একাধিক প্রকারভেদের সমাবেশ ঘটেছে।"}
            ]""",
            examplesJson = """[
                {"english":"Rahim plays cricket very well.","bangla":"রহিম খুব ভালো ক্রিকেট খেলে। (Rahim=Noun, plays=Verb, well=Adverb)"},
                {"english":"She is an intelligent student.","bangla":"সে একজন বুদ্ধিমতী ছাত্রী। (intelligent=Adjective)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He works hardly every day.","correct":"He works hard every day.","explanationBn":"Hardly মানে 'কদাচিৎ' বা প্রায় না। কঠোর পরিশ্রম বোঝাতে 'hard' ব্যবহার করতে হয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "noun",
            topicNumber = 2,
            titleEn = "Noun",
            titleBn = "বিশেষ্য",
            category = "Fundamentals",
            summaryBn = "যে শব্দ দ্বারা কোনো ব্যক্তি, বস্তু, স্থান, গুণ বা অবস্থার নাম বোঝায় তাকে Noun বলে।",
            explanationBn = "Noun প্রধানত ৫ প্রকার: Proper Noun, Common Noun, Collective Noun, Material Noun এবং Abstract Noun। গণনার ভিত্তিতে আবার Noun দুই প্রকার: Countable Noun (যা গণনা করা যায়) ও Uncountable Noun (যা গণনা করা যায় না, শুধু পরিমাপ করা যায়)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Proper Nouns always start with a capital letter.","ruleBn":"Proper Noun-এর প্রথম অক্ষর সর্বদা বড় হাতের (Capital Letter) হয়।","exampleEn":"Dhaka is the capital of Bangladesh.","exampleBn":"ঢাকা বাংলাদেশের রাজধানী।"},
                {"ruleNo":2,"ruleEn":"Uncountable nouns take singular verbs and do not take 'a/an'.","ruleBn":"Uncountable Noun-এর পূর্বে সাধারণত a/an বসে না এবং সর্বদা Singular Verb নেয়।","exampleEn":"Advice is always valuable. (Not: An advice)","exampleBn":"উপদেশ সর্বদা মূল্যবান।"}
            ]""",
            examplesJson = """[
                {"english":"Honesty is the best policy.","bangla":"সততা সর্বোৎকৃষ্ট পন্থা। (Honesty = Abstract Noun)"},
                {"english":"The committee has taken the decision.","bangla":"কমিটি সিদ্ধান্তটি গ্রহণ করেছে। (Collective Noun)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He gave me many informations.","correct":"He gave me much information / a lot of information.","explanationBn":"Information একটি Uncountable Noun, তাই এর সাথে 's' যোগ করে বহুবচন করা যায় না।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "pronoun",
            topicNumber = 3,
            titleEn = "Pronoun",
            titleBn = "সর্বনাম",
            category = "Fundamentals",
            summaryBn = "Noun-এর পুনরাবৃত্তি এড়াতে তার পরিবর্তে যে শব্দ ব্যবহৃত হয় তাকে Pronoun বলে।",
            explanationBn = "Pronoun মোট ৮ প্রকার, যেমন Personal Pronoun (I, we, he, they), Demonstrative (this, that), Relative (who, which, that), Interrogative (who, what), Reflexive (myself, himself), Indefinite (someone, any), Distributive (each, either) এবং Reciprocal (each other, one another)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Good deed pronoun order: 2nd -> 3rd -> 1st person (231 rule).","ruleBn":"স্বাভাবিক বা ভালো কাজের ক্ষেত্রে Pronoun বসার নিয়ম: Second -> Third -> First Person (তুমি, সে ও আমি)।","exampleEn":"You, he and I will go there.","exampleBn":"তুমি, সে এবং আমি সেখানে যাব।"},
                {"ruleNo":2,"ruleEn":"Guilt / confession order: 1st -> 2nd -> 3rd person (123 rule).","ruleBn":"দোষ স্বীকারের ক্ষেত্রে: First -> Second -> Third Person (আমি, তুমি ও সে)।","exampleEn":"I, you and he are guilty.","exampleBn":"আমি, তুমি এবং সে অপরাধী।"}
            ]""",
            examplesJson = """[
                {"english":"Each of the boys has done his duty.","bangla":"ছেলেদের প্রত্যেকেই নিজ নিজ দায়িত্ব পালন করেছে।"},
                {"english":"The man who came yesterday is my uncle.","bangla":"গতকাল যে লোকটি এসেছিল সে আমার চাচা। (who = Relative Pronoun)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"One should do his duty.","correct":"One should do one's duty.","explanationBn":"বাক্যের Subject 'One' হলে Possessive রূপ হিসেবে 'one's' বসে, 'his' নয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "adjective",
            topicNumber = 4,
            titleEn = "Adjective",
            titleBn = "বিশেষণ",
            category = "Fundamentals",
            summaryBn = "যে শব্দ Noun বা Pronoun-এর দোষ, গুণ, অবস্থা, সংখ্যা বা পরিমাণ প্রকাশ করে তাকে Adjective বলে।",
            explanationBn = "Adjective Noun-এর পূর্বে বসে (Attributive use) অথবা Verb-এর পরে বসে Subject-কে নির্দেশ করে (Predicative use)। Adjective-এর ৪টি প্রধান রূপ: Adjective of Quality, Adjective of Quantity, Adjective of Number এবং Pronominal Adjective।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Adjectives of quality answer 'What kind?'.","ruleBn":"কোনো কিছুর গুণ বা অবস্থা বোঝাতে Quality Adjective ব্যবহৃত হয়।","exampleEn":"She wears an elegant dress.","exampleBn":"সে একটি চমৎকার পোশাক পরেছে।"},
                {"ruleNo":2,"ruleEn":"Order of multiple adjectives: Opinion -> Size -> Age -> Shape -> Color -> Origin -> Material.","ruleBn":"একাধিক Adjective পাশাপাশি বসলে তাদের সুনির্দিষ্ট ক্রম রক্ষা করতে হয় (OSASCOM rule)।","exampleEn":"A beautiful big old wooden table.","exampleBn":"একটি সুন্দর বড় পুরনো কাঠের টেবিল।"}
            ]""",
            examplesJson = """[
                {"english":"There is little water in the glass.","bangla":"গ্লাসে প্রায় পানি নেই বললেই চলে। (little = Adjective of Quantity)"},
                {"english":"Several students participated in the debate.","bangla":"বেশ কয়েকজন শিক্ষার্থী বিতর্কে অংশ নিয়েছিল।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He is more taller than his brother.","correct":"He is taller than his brother.","explanationBn":"Double comparative (more taller) ব্যবহার করা ব্যাকরণগতভাবে ভুল। কেবল 'taller' ব্যবহার করুন।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "verb",
            topicNumber = 5,
            titleEn = "Verb",
            titleBn = "ক্রিয়া",
            category = "Verbs & Tense",
            summaryBn = "যে শব্দ দ্বারা কোনো কিছু করা, হওয়া, থাকা বা কোনো অ্যাকশন বোঝায় তাকে Verb বলে।",
            explanationBn = "Verb হলো একটি বাক্যের প্রাণ (Heart of a sentence)। Verb ছাড়া কোনো পূর্ণাঙ্গ ইংরেজি বাক্য তৈরি হতে পারে না। Verb প্রধানত Finite (সমাপিকা) ও Non-Finite (অসমাপিকা)। এছাড়া Transitive (সকর্মক), Intransitive (অকর্মক), Linking Verb এবং Auxiliary/Modal Verbs রয়েছে।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Transitive verbs require a direct object to complete meaning.","ruleBn":"Transitive Verb-এর অর্থ সম্পূর্ণ করতে Object প্রয়োজন হয়।","exampleEn":"He wrote a letter.","exampleBn":"সে একটি চিঠি লিখেছিল।"},
                {"ruleNo":2,"ruleEn":"Modal auxiliaries are followed by the base form of the verb.","ruleBn":"Modal Auxiliary (can, could, may, might, must, should) এর পর সর্বদা Verb-এর Base Form বসে।","exampleEn":"You must obey your parents.","exampleBn":"তোমার অবশ্যই মা-বাবাকে মান্য করা উচিত।"}
            ]""",
            examplesJson = """[
                {"english":"Birds fly in the sky.","bangla":"পাখিরা আকাশে ওড়ে। (fly = Intransitive Verb)"},
                {"english":"Honey tastes sweet.","bangla":"মধু খেতে মিষ্টি। (tastes = Linking Verb)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He can to speak English.","correct":"He can speak English.","explanationBn":"Modal auxiliary (can)-এর পর 'to' যুক্ত infinitive বসে না, কেবল মূল verb বসে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "adverb",
            topicNumber = 6,
            titleEn = "Adverb",
            titleBn = "ক্রিয়া বিশেষণ",
            category = "Fundamentals",
            summaryBn = "যে শব্দ Verb, Adjective বা অন্য কোনো Adverb-কে বিশেষিত বা modify করে তাকে Adverb বলে।",
            explanationBn = "Adverb সাধারণত How (কিভাবে), Where (কোথায়), When (কখন) এবং To what degree (কতটুকু) এই প্রশ্নগুলোর উত্তর দেয়। যেমন Adverb of Manner (slowly), Adverb of Place (here, there), Adverb of Time (now, yesterday), Adverb of Degree (very, quite)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Adverbs of manner usually take '-ly' ending, but not always.","ruleBn":"অধিকাংশ Adverb of Manner-এর শেষে -ly যুক্ত থাকে, তবে fast, hard ইত্যাদিতে -ly যুক্ত হয় না।","exampleEn":"He runs fast. (Not: fastly)","exampleBn":"সে দ্রুত দৌড়ায়।"},
                {"ruleNo":2,"ruleEn":"Position of frequency adverbs: Before main verbs, after auxiliary verbs.","ruleBn":"Always, never, often, seldom সাধারণত Main Verb-এর পূর্বে এবং Auxiliary Verb-এর পরে বসে।","exampleEn":"She has never visited Paris.","exampleBn":"সে কখনো প্যারিস ভ্রমণ করেনি।"}
            ]""",
            examplesJson = """[
                {"english":"The train arrived punctually.","bangla":"ট্রেনটি সময়মতো পৌঁছাল। (punctually = Adverb of Manner)"},
                {"english":"He is extremely talented.","bangla":"সে অত্যন্ত প্রতিভাবান। (extremely = Adverb of Degree)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"The flower smells sweetly.","correct":"The flower smells sweet.","explanationBn":"Smell একটি Linking Verb, তাই এর পর Adverb নয় বরং Adjective (sweet) বসবে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "preposition",
            topicNumber = 7,
            titleEn = "Preposition",
            titleBn = "পদান্বয়ী অব্যয়",
            category = "Fundamentals",
            summaryBn = "Noun বা Pronoun-এর পূর্বে বসে বাক্যের অন্য শব্দের সাথে সম্পর্ক স্থাপনকারী শব্দকে Preposition বলে।",
            explanationBn = "Preposition সময় (Time), স্থান (Place), দিক (Direction) ইত্যাদি বোঝাতে ব্যবহৃত হয়। যেমন: at (নির্দিষ্ট সময়/ছোট স্থান), in (মাস/বছর/বড় স্থান), on (দিন/তারিখ/তলের ওপর), by (মাধ্যমে/দ্বারা), with (সাথে)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Use 'in' for years/months, 'on' for days/dates, 'at' for specific clock times.","ruleBn":"সালের/মাসের পূর্বে in, বার/তারিখের পূর্বে on, এবং নির্দিষ্ট সময়ের পূর্বে at বসে।","exampleEn":"At 7 PM on Sunday in December.","exampleBn":"ডিসেম্বরের রোববারে সন্ধ্যা ৭টায়।"},
                {"ruleNo":2,"ruleEn":"Between is used for two entities; Among is used for more than two.","ruleBn":"দুজনের মধ্যে বোঝাতে between এবং অনেকের মধ্যে বোঝাতে among বসে।","exampleEn":"Divide the mangoes between Rahim and Karim.","exampleBn":"আমগুলো রহিম ও করিমের মধ্যে ভাগ করে দাও।"}
            ]""",
            examplesJson = """[
                {"english":"He is proficient in English.","bangla":"সে ইংরেজিতে দক্ষ।"},
                {"english":"The book is on the table.","bangla":"বইটি টেবিলের উপরে আছে।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He discussed about the matter.","correct":"He discussed the matter.","explanationBn":"Discuss শব্দের পর 'about' বসে না, কারণ discuss নিজেই 'talk about' নির্দেশ করে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "conjunction",
            topicNumber = 8,
            titleEn = "Conjunction",
            titleBn = "সংযোজক অব্যয়",
            category = "Fundamentals",
            summaryBn = "যে শব্দ দুই বা ততোধিক শব্দ, বাক্যাংশ (phrase) বা বাক্যকে (clause) যুক্ত করে তাকে Conjunction বলে।",
            explanationBn = "Conjunction ৩ প্রকার: Coordinating (and, but, or, yet, so), Subordinating (because, although, if, since, when), Correlative (either...or, neither...nor, not only...but also)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Correlative conjunctions require parallel structures.","ruleBn":"Correlative Conjunction (not only...but also) ব্যবহার করলে উভয় পাশে একই grammatical কাঠামো বজায় রাখতে হয়।","exampleEn":"He is not only polite but also helpful.","exampleBn":"সে শুধু ভদ্রই নয়, পরোপকারীও।"},
                {"ruleNo":2,"ruleEn":"Avoid double conjunctions in the same clause.","ruleBn":"একই বাক্যে যদিও/তবুও বোঝাতে although এবং but একসাথে ব্যবহার করা যাবে না।","exampleEn":"Although he is poor, he is honest. (Not: but he is honest)","exampleBn":"যদিও সে গরিব, সে সৎ।"}
            ]""",
            examplesJson = """[
                {"english":"Read attentively or you will fail.","bangla":"মনোযোগ দিয়ে পড়ো নতুবা তুমি অকৃতকার্য হবে।"},
                {"english":"Neither Suman nor his friends were present.","bangla":"সুমন কিংবা তার বন্ধুরা কেউই উপস্থিত ছিল না।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"Though he worked hard, but he failed.","correct":"Though he worked hard, he failed.","explanationBn":"Though/Although যুক্ত বাক্যে 'but' ব্যবহার করা ব্যাকরণগত ত্রুটি।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "interjection",
            topicNumber = 9,
            titleEn = "Interjection",
            titleBn = "আবেগসূচক অব্যয়",
            category = "Fundamentals",
            summaryBn = "যে শব্দ দ্বারা মনের আকস্মিক আনন্দ, দুঃখ, বিস্ময় বা ক্ষোভের তীব্র আবেগ প্রকাশ পায় তাকে Interjection বলে।",
            explanationBn = "Interjection-এর শেষে সর্বদা বিস্ময়বোধক চিহ্ন (!) ব্যবহৃত হয়। যেমন: Hurrah! (আনন্দ), Alas! (দুঃখ), Bravo! (বাহবা), Ouch! (ব্যথা), Wow! (বিস্ময়)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Interjections are grammatically independent of the rest of the sentence.","ruleBn":"Interjection ব্যাকরণগতভাবে বাক্যের অন্যান্য অংশের ওপর সরাসরি নির্ভরশীল নয়।","exampleEn":"Hurrah! We have won the match.","exampleBn":"কি আনন্দ! আমরা খেলায় জিতেছি।"},
                {"ruleNo":2,"ruleEn":"Exclamation mark (!) usually comes immediately after the interjection.","ruleBn":"সাধারণত Interjection শব্দের সাথে সাথেই (!) চিহ্ন বসে এবং পরবর্তী বাক্যের প্রথম অক্ষর Capital হয়।","exampleEn":"Alas! His father passed away.","exampleBn":"হায়! তার বাবা মারা গেছেন।"}
            ]""",
            examplesJson = """[
                {"english":"Bravo! You performed exceptionally well.","bangla":"সাবাশ! তুমি অসাধারণ নৈপুণ্য প্রদর্শন করেছ।"},
                {"english":"Hush! The baby is sleeping.","bangla":"চুপ! শিশুটি ঘুমাচ্ছে।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"Alas, we won the game!","correct":"Hurrah! We won the game.","explanationBn":"বিজয়ের আনন্দের ক্ষেত্রে 'Alas' নয়, 'Hurrah!' ব্যবহার করতে হয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "tense",
            topicNumber = 10,
            titleEn = "Tense",
            titleBn = "কাল ও সময়",
            category = "Verbs & Tense",
            summaryBn = "কোনো কাজ সম্পন্ন হওয়ার সময়কে Tense বলে। এটি ইংরেজি ভাষার সবচেয়ে গুরুত্বপূর্ণ স্তম্ভ।",
            explanationBn = "Tense প্রধানত ৩ প্রকার: Present, Past, এবং Future। প্রত্যেকটি আবার ৪ ভাগে বিভক্ত (Indefinite, Continuous, Perfect, Perfect Continuous)। ফলে মোট ১২টি Tense রূপ গঠিত হয়।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Present Indefinite: 3rd person singular subject takes 's/es' with the verb.","ruleBn":"Present Indefinite Tense-এ Subject যদি 3rd Person Singular Number হয়, তবে মূল Verb-এর শেষে s বা es যোগ করতে হয়।","exampleEn":"He goes to school every day.","exampleBn":"সে প্রতিদিন স্কুলে যায়।"},
                {"ruleNo":2,"ruleEn":"Past Perfect: The earlier action takes 'had + V3', later action takes Past Simple.","ruleBn":"অতীতে দুটি কাজের মধ্যে অপেক্ষাকৃত পূর্ববর্তী কাজটি Past Perfect এবং পরেরটি Past Simple হয়।","exampleEn":"The patient had died before the doctor came.","exampleBn":"ডাক্তার আসার পূর্বে রোগীটি মারা গিয়েছিল।"}
            ]""",
            examplesJson = """[
                {"english":"The sun rises in the east.","bangla":"সূর্য পূর্ব দিকে উদিত হয়। (চিরন্তন সত্য = Present Indefinite)"},
                {"english":"I have been reading for two hours.","bangla":"আমি দুই ঘণ্টা ধরে পড়ছি। (Present Perfect Continuous)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He did not went to college.","correct":"He did not go to college.","explanationBn":"Did ব্যবহারের পর সর্বদা Verb-এর Present/Base Form (go) বসে, Past Form (went) নয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "right_form_of_verb",
            topicNumber = 11,
            titleEn = "Right Form of Verb",
            titleBn = "ক্রিয়ার সঠিক রূপ",
            category = "Verbs & Tense",
            summaryBn = "Subject, Tense, Voice ও Mood অনুযায়ী ক্রিয়ার সঠিক রূপ বসানোর নিয়মাবলি।",
            explanationBn = "পরীক্ষায় সবচেয়ে বেশি আসা বিষয়ের মধ্যে অন্যতম হলো Right Form of Verbs। নির্দিষ্ট কিছু Clue/Word থাকলে Verb-এর নির্দিষ্ট রূপ বসাতে হয়। যেমন: just, already থাকলে Present Perfect; yesterday, ago থাকলে Past Simple; modal verb-এর পর base form ইত্যাদি।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"With 'as if' / 'as though': Present takes past simple (were), Past takes past perfect (had + V3).","ruleBn":"As if/as though-এর পূর্বে Present Tense থাকলে পরে Past Tense (to be verb হলে were) বসে।","exampleEn":"He speaks as if he knew everything.","exampleBn":"সে এমনভাবে কথা বলে যেন সে সবকিছু জানত।"},
                {"ruleNo":2,"ruleEn":"Phrases like 'look forward to', 'with a view to', 'get used to' take V+ing.","ruleBn":"With a view to, look forward to, cannot help, would you mind এর পর verb-এর সাথে ing যুক্ত হয়।","exampleEn":"I went to the library with a view to reading books.","exampleBn":"বই পড়ার উদ্দেশ্যে আমি লাইব্রেরিতে গিয়েছিলাম।"}
            ]""",
            examplesJson = """[
                {"english":"It is high time we changed our bad habits.","bangla":"আমাদের বাজে অভ্যাসগুলো পরিবর্তন করার এখনই উপযুক্ত সময়।"},
                {"english":"Would you mind having a cup of tea?","bangla":"এক কাপ চা খেলে কি কিছু মনে করবেন?"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"I look forward to hear from you.","correct":"I look forward to hearing from you.","explanationBn":"Look forward to-এর পর verb-এর সাথে -ing যুক্ত করতে হয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "subject_verb_agreement",
            topicNumber = 12,
            titleEn = "Subject-Verb Agreement",
            titleBn = "কর্তা ও ক্রিয়ার সঙ্গতি",
            category = "Sentence Structure",
            summaryBn = "Subject-এর Number (বচন) ও Person (পুরুষ) অনুসারে Verb-এর সঠিক রূপ নির্ধারণ করার নিয়ম।",
            explanationBn = "Singular Subject সর্বদা Singular Verb গ্রহণ করে এবং Plural Subject Plural Verb গ্রহণ করে। কিন্তু কিছু বিশেষ নিয়মে বিভ্রান্তি তৈরি হয়, যেমন 'Neither of', 'Along with', 'A number of' ইত্যাদি ক্ষেত্রে।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"When connected by 'along with', 'as well as', 'together with', verb follows the first subject.","ruleBn":"As well as, with, along with দ্বারা দুটি Subject যুক্ত হলে Verb প্রথম Subject অনুযায়ী নির্ধারিত হয়।","exampleEn":"The teacher, along with his students, was present.","exampleBn":"শিক্ষক, তার ছাত্রদের সাথে, উপস্থিত ছিলেন।"},
                {"ruleNo":2,"ruleEn":"'A number of' takes plural verb, while 'The number of' takes singular verb.","ruleBn":"'A number of' থাকলে Plural Verb বসে, কিন্তু 'The number of' থাকলে Singular Verb বসে।","exampleEn":"The number of students in the class is fifty.","exampleBn":"শ্রেণিকক্ষে শিক্ষার্থীর সংখ্যা পঞ্চাশ।"}
            ]""",
            examplesJson = """[
                {"english":"Slow and steady wins the race.","bangla":"ধীর ও অবিচল ব্যক্তিরাই প্রতিযোগিতায় জেতে। (একক ধারণা প্রকাশে Singular verb)"},
                {"english":"Neither the teacher nor the students were satisfied.","bangla":"শিক্ষক বা ছাত্র কেউই সন্তুষ্ট ছিলেন না। (nor-এর ক্ষেত্রে নিকটবর্তী subject)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"One of my friends are a doctor.","correct":"One of my friends is a doctor.","explanationBn":"'One of'-এর পর Noun বহুবচন হলেও Subject হলো 'One', তাই Verb singular (is) হবে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "article",
            topicNumber = 13,
            titleEn = "Article",
            titleBn = "আর্টিকেল (A, An, The)",
            category = "Sentence Structure",
            summaryBn = "A, An এবং The-কে Article বলে। এরা Noun-এর নির্দিষ্টতা বা অনির্দিষ্টতা প্রকাশ করে।",
            explanationBn = "Article দুই প্রকার: Indefinite Article (A, An) যা কোনো অনির্দিষ্ট ব্যক্তি বা বস্তুকে নির্দেশ করে, এবং Definite Article (The) যা নির্দিষ্ট কোনো ব্যক্তি, বস্তু বা শ্রেণিকে বোঝায়। স্বরবর্ণের ধ্বনি (Vowel sound) থাকলে An এবং ব্যঞ্জনবর্ণের ধ্বনি (Consonant sound) থাকলে A বসে।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Silent 'h' takes 'an' due to vowel sound.","ruleBn":"শব্দের শুরুতে 'h' অনুচ্চারিত থাকলে তার পূর্বে 'an' বসে।","exampleEn":"He is an honest man. It took an hour.","exampleBn":"তিনি একজন সৎ মানুষ। এতে এক ঘণ্টা সময় লাগল।"},
                {"ruleNo":2,"ruleEn":"Musical instruments, rivers, newspapers, unique objects take 'The'.","ruleBn":"নদী, সংবাদপত্র, বাদ্যযন্ত্র এবং অদ্বিতীয় বস্তুর পূর্বে 'The' বসে।","exampleEn":"The Padma, The Daily Star, The earth.","exampleBn":"পদ্মা, দ্য ডেইলি স্টার, পৃথিবী।"}
            ]""",
            examplesJson = """[
                {"english":"He is a European gentleman.","bangla":"তিনি একজন ইউরোপীয় ভদ্রলোক। ('Eu' এর উচ্চারণ 'ইউ' এর মতো হওয়ায় 'a' বসেছে)"},
                {"english":"The rich are not always happy.","bangla":"ধনীরা সর্বদা সুখী হয় না। (সমগ্র ধনী শ্রেণিকে বোঝাতে The rich)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He is a university student, but an unique talent.","correct":"He is a university student, but a unique talent.","explanationBn":"'Unique' এবং 'University'-র শুরুতে 'ইউ' (Ju:) ধ্বনি উচ্চারিত হওয়ায় 'a' বসবে, 'an' নয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "voice",
            topicNumber = 14,
            titleEn = "Voice Change",
            titleBn = "বাচ্য পরিবর্তন",
            category = "Sentence Structure",
            summaryBn = "ক্রিয়ার প্রকাশভঙ্গিকে Voice বলে, যা নির্দেশ করে Subject নিজে কাজ করছে নাকি কাজ তার ওপর আরোপিত হচ্ছে।",
            explanationBn = "Voice দুই প্রকার: Active Voice (কর্তা নিজে সক্রিয়) এবং Passive Voice (কর্ম প্রধান)। Passive রূপান্তরের সাধারণ নিয়ম: Active-এর Object -> Passive-এর Subject + Tense অনুযায়ী be verb + মূল verb-এর Past Participle (V3) + by + Active-এর Subject-এর Objective রূপ।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Present Continuous passive: am/is/are + being + V3.","ruleBn":"Continuous Tense-কে Passive করতে হলে Tense অনুযায়ী be verb-এর পর 'being' যোগ করতে হয়।","exampleEn":"They are playing football -> Football is being played by them.","exampleBn":"তারা ফুটবল খেলছে -> তাদের দ্বারা ফুটবল খেলা হচ্ছে।"},
                {"ruleNo":2,"ruleEn":"Imperative passive: Let + object + be + V3.","ruleBn":"আদেশ বা উপদেশমূলক বাক্যকে Passive করতে 'Let + Object + be + V3' ব্যবহৃত হয়।","exampleEn":"Do the work -> Let the work be done.","exampleBn":"কাজটি করো -> কাজটি করা হোক।"}
            ]""",
            examplesJson = """[
                {"english":"Who broke the glass? -> By whom was the glass broken?","bangla":"কে গ্লাসটি ভেঙেছে? -> কার দ্বারা গ্লাসটি ভাঙা হয়েছে?"},
                {"english":"I know him. -> He is known to me.","bangla":"আমি তাকে চিনি -> সে আমার কাছে পরিচিত। (know এর পর to বসে)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He is known by me.","correct":"He is known to me.","explanationBn":"'Known'-এর পর Passive-এ 'by' বসে না, Preposition হিসেবে 'to' বসে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "narration",
            topicNumber = 15,
            titleEn = "Narration",
            titleBn = "উক্তি পরিবর্তন",
            category = "Sentence Structure",
            summaryBn = "বক্তার বক্তব্য অন্যের কাছে প্রকাশ করার পদ্ধতিকে Narration বা Speech বলে।",
            explanationBn = "Narration দুই প্রকার: Direct Speech (বক্তার মুখের হুবহু কথা ইনভার্টেড কমার ভেতরে) এবং Indirect Speech (বক্তার বক্তব্য নিজের ভাষায় বর্ণনা করা)। Reporting Verb যদি Past Tense হয়, তবে Reported Speech-এর Tense পরিবর্তিত হয়ে সংশ্লিষ্ট Past রূপ নেয়।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Present Simple changes to Past Simple in indirect speech.","ruleBn":"Reporting Verb Past হলে Present Indefinite পরিবর্তিত হয়ে Past Indefinite হয়।","exampleEn":"He said, 'I write a letter.' -> He said that he wrote a letter.","exampleBn":"সে বলল, 'আমি চিঠি লিখি।' -> সে বলল যে সে চিঠি লিখেছিল।"},
                {"ruleNo":2,"ruleEn":"Universal truth never changes tense.","ruleBn":"Reported Speech যদি চিরন্তন সত্য (Universal Truth) হয়, তবে Tense-এর কোনো পরিবর্তন ঘটে না।","exampleEn":"The teacher said, 'The earth moves round the sun.' -> The teacher said that the earth moves round the sun.","exampleBn":"শিক্ষক বললেন পৃথিবী সূর্যের চারদিকে ঘোরে।"}
            ]""",
            examplesJson = """[
                {"english":"He said to me, 'Are you happy?' -> He asked me if I was happy.","bangla":"সে আমাকে জিজ্ঞাসা করল আমি সুখী কিনা।"},
                {"english":"She said, 'May Allah bless you.' -> She prayed that Allah might bless me.","bangla":"সে প্রার্থনা করল যে আল্লাহ যেন আমার মঙ্গল করেন।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He told to me that he was ill.","correct":"He told me that he was ill.","explanationBn":"'Told'-এর পর কোনো Preposition (to) বসে না, সরাসরি Object বসে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "transformation_of_sentence",
            topicNumber = 16,
            titleEn = "Transformation of Sentence",
            titleBn = "বাক্য রূপান্তর",
            category = "Sentence Structure",
            summaryBn = "অর্থ অপরিবর্তিত রেখে বাক্যের গঠনগত বা ব্যাকরণগত পরিবর্তনকে Transformation বলে।",
            explanationBn = "এর মধ্যে রয়েছে Affirmative to Negative, Assertive to Interrogative/Exclamatory, এবং Simple, Complex ও Compound বাক্যের পারস্পরিক রূপান্তর। এটি সকল শ্রেণির একাডেমিক ও প্রতিযোগিতামূলক পরীক্ষায় সর্বাধিক গুরুত্ব বহন করে।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"'Only' with a person -> 'None but'; with a thing -> 'Nothing but'.","ruleBn":"Affirmative বাক্যে 'Only' ব্যক্তি নির্দেশ করলে Negative-এ 'None but' এবং বস্তু হলে 'Nothing but' বসে।","exampleEn":"Only Allah can help us -> None but Allah can help us.","exampleBn":"কেবল আল্লাহ আমাদের সাহায্য করতে পারেন।"},
                {"ruleNo":2,"ruleEn":"'Too...to' becomes 'So...that...cannot/could not'.","ruleBn":"Simple বাক্যে Too...to থাকলে Complex করতে So...that...cannot/could not বসে।","exampleEn":"He is too weak to walk -> He is so weak that he cannot walk.","exampleBn":"সে এতটাই দুর্বল যে সে হাঁটতে পারে না।"}
            ]""",
            examplesJson = """[
                {"english":"Every mother loves her child -> There is no mother but loves her child.","bangla":"প্রত্যেক মা তার সন্তানকে ভালোবাসেন।"},
                {"english":"Though he is poor, he is honest -> He is poor but he is honest.","bangla":"যদিও সে দরিদ্র, সে সৎ -> সে দরিদ্র কিন্তু সৎ।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He is so weak that he could not walk. (Present context)","correct":"He is so weak that he cannot walk.","explanationBn":"প্রথম অংশে 'is' থাকলে that-এর পর 'cannot' হবে, 'could not' কেবল past tense (was) থাকলে ব্যবহৃত হয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "degree",
            topicNumber = 17,
            titleEn = "Degree of Comparison",
            titleBn = "তুলনামূলক রূপ",
            category = "Fundamentals",
            summaryBn = "Adjective বা Adverb-এর তুলনামূলক তারতম্য বা পরিমাপকে Degree of Comparison বলে।",
            explanationBn = "Degree ৩ প্রকার: Positive Degree (সাধারণ গুণ, যেমন: good, tall), Comparative Degree (দুজনের মধ্যে তুলনা, যেমন: better, taller), এবং Superlative Degree (সকলের মধ্যে শ্রেষ্ঠত্ব, যেমন: best, tallest)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"'One of the' superlative turns into 'Very few...as...as' in positive.","ruleBn":"Superlative-এ 'One of the' থাকলে Positive করার সময় শুরুতে 'Very few' বসে এবং Verb বহুবচন হয়।","exampleEn":"He is one of the best boys -> Very few boys are as good as he.","exampleBn":"খুব কম ছেলেই তার মতো ভালো।"},
                {"ruleNo":2,"ruleEn":"Superlative takes 'The' before the adjective.","ruleBn":"Superlative Adjective-এর পূর্বে সর্বদা 'The' বসে।","exampleEn":"Mount Everest is the highest peak in the world.","exampleBn":"মাউন্ট এভারেস্ট পৃথিবীর সর্বোচ্চ পর্বতশৃঙ্গ।"}
            ]""",
            examplesJson = """[
                {"english":"No other city in Bangladesh is as big as Dhaka. (Positive)","bangla":"বাংলাদেশে ঢাকার মতো এত বড় শহর আর নেই।"},
                {"english":"Dhaka is bigger than any other city in Bangladesh. (Comparative)","bangla":"ঢাকা বাংলাদেশের অন্য যেকোনো শহরের চেয়ে বড়।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He is senior than me.","correct":"He is senior to me.","explanationBn":"Senior, junior, superior, inferior-এর পর 'than' বসে না, 'to' বসে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "tag_question",
            topicNumber = 18,
            titleEn = "Tag Question",
            titleBn = "ট্যাগ প্রশ্ন",
            category = "Sentence Structure",
            summaryBn = "কথোপকথনের সময় শ্রোতার স্বীকৃতি বা সম্মতি যাচাই করতে বাক্যের শেষে ছোট যে প্রশ্ন জোড়া হয়।",
            explanationBn = "সাধারণ নিয়ম: মূল বাক্যটি যদি Affirmative (হ্যাঁ-বোধক) হয়, তবে Tag হবে Negative (সংক্ষিপ্ত রূপে: isn't, aren't, don't ইত্যাদি)। আর মূল বাক্য Negative হলে Tag হবে Affirmative। Tag-এ সর্বদা Pronoun ব্যবহৃত হয়।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Affirmative statement takes negative tag; negative takes affirmative tag.","ruleBn":"হ্যাঁ-বোধক বাক্যে না-বোধক ট্যাগ এবং না-বোধক বাক্যে হ্যাঁ-বোধক ট্যাগ বসে।","exampleEn":"She sings beautifully, doesn't she?","exampleBn":"সে সুন্দর গান গায়, তাই না?"},
                {"ruleNo":2,"ruleEn":"'Let's' takes 'shall we?', Imperative order/request takes 'will you?'.","ruleBn":"Let's বা Let us থাকলে Tag হবে 'shall we?', অন্যান্য Imperative বাক্যে সাধারণত 'will you?' বসে।","exampleEn":"Let us arrange a picnic, shall we?","exampleBn":"চলো আমরা একটি বনভোজনের আয়োজন করি, কেমন?"}
            ]""",
            examplesJson = """[
                {"english":"Barking dogs seldom bite, do they?","bangla":"ঘেউ ঘেউ করা কুকুর কদাচিৎ কামড়ায়, তাই না? (seldom না-বোধক শব্দ)"},
                {"english":"I am a student, aren't I? / ain't I?","bangla":"আমি একজন ছাত্র, তাই না?"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"I am happy, amn't I?","correct":"I am happy, aren't I?","explanationBn":"ইংরেজিতে 'amn't I' বলে কোনো বৈধ রূপ নেই; এর পরিবর্তে 'aren't I' ব্যবহৃত হয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "conditional_sentence",
            topicNumber = 19,
            titleEn = "Conditional Sentence",
            titleBn = "শর্তযুক্ত বাক্য",
            category = "Sentence Structure",
            summaryBn = "যেসব বাক্যে কোনো শর্ত (Condition) আরোপ করা হয় এবং তার সম্ভাব্য ফলাফল ব্যক্ত করা হয়।",
            explanationBn = "Conditional বাক্য ৪ প্রকার: Zero Conditional (সাধারণ সত্য: If + Present, Present), First Conditional (বাস্তব সম্ভাবনা: If + Present, Future Simple), Second Conditional (কাল্পনিক বর্তমান: If + Past Simple, would + V1), Third Conditional (অতীতের অপূর্ণ সম্ভাবনা: If + Past Perfect, would have + V3)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"1st Conditional: If + Present Simple, will + base verb.","ruleBn":"প্রথম শর্তে If যুক্ত অংশ Present হলে পরের অংশ Future Simple হবে।","exampleEn":"If it rains, we will stay at home.","exampleBn":"যদি বৃষ্টি হয়, তবে আমরা বাড়িতে থাকব।"},
                {"ruleNo":2,"ruleEn":"3rd Conditional: If + Past Perfect, would have + V3.","ruleBn":"তৃতীয় শর্তে If যুক্ত অংশ Past Perfect হলে পরের অংশে would have / could have + V3 বসে।","exampleEn":"If you had studied sincerely, you would have passed.","exampleBn":"তুমি যদি আন্তরিকভাবে পড়তে, তবে তুমি পাস করতে।"}
            ]""",
            examplesJson = """[
                {"english":"If I were a king, I would help the poor.","bangla":"আমি যদি রাজা হতাম, তবে গরিবদের সাহায্য করতাম। (2nd Conditional)"},
                {"english":"Had I seen him, I would have told him the news.","bangla":"যদি আমি তাকে দেখতাম, তবে খবরটি বলতাম।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"If I will study hard, I will pass the exam.","correct":"If I study hard, I will pass the exam.","explanationBn":"If-যুক্ত clause-এ কখনো 'will' বসে না, সেখানে Present Simple বসে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "completing_sentence",
            topicNumber = 20,
            titleEn = "Completing Sentence",
            titleBn = "বাক্য সম্পূর্ণকরণ",
            category = "Sentence Structure",
            summaryBn = "অসম্পূর্ণ বাক্যকে উপযুক্ত Clause বা Phrase ব্যবহার করে অর্থপূর্ণভাবে সম্পূর্ণ করার কৌশল।",
            explanationBn = "Completing Sentence-এর জন্য বেশ কিছু স্ট্রাকচার আত্মস্থ করতে হয়। যেমন: Lest (পাছে কিছু ঘটে এই ভয়ে -> Subject + should + V1), So that (যাতে করে -> Subject + can/could + V1), It is time / It is high time (উপযুক্ত সময় -> Subject + V2), No sooner had...than ইত্যাদি।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"'Lest' takes 'should + base verb' without negative words.","ruleBn":"Lest-এর পর Subject + should + V1 বসে। Lest নিজেই না-বোধক, তাই এর পর not বসে না।","exampleEn":"Walk fast lest you should miss the train.","exampleBn":"দ্রুত হাঁটো পাছে তুমি ট্রেনটি মিস করো।"},
                {"ruleNo":2,"ruleEn":"'No sooner had...than' requires past perfect then past simple.","ruleBn":"No sooner had + Subject + V3 + than + Subject + V2 বসে।","exampleEn":"No sooner had the bell rung than the students entered the classroom.","exampleBn":"ঘণ্টা পড়তে না পড়তেই ছাত্ররা শ্রেণিকক্ষে প্রবেশ করল।"}
            ]""",
            examplesJson = """[
                {"english":"We eat so that we may live.","bangla":"আমরা খাই যাতে আমরা বেঁচে থাকতে পারি।"},
                {"english":"Scarcely had I reached the station when the train left.","bangla":"আমি স্টেশনে পৌঁছাতে না পৌঁছাতেই ট্রেনটি ছেড়ে গেল।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"Walk carefully lest you should not fall down.","correct":"Walk carefully lest you should fall down.","explanationBn":"Lest-এর পর 'not' ব্যবহার করা যাবে না কারণ lest নিজেই না-বোধক অর্থ বহন করে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "appropriate_preposition",
            topicNumber = 21,
            titleEn = "Appropriate Preposition",
            titleBn = "যথাযথ অব্যয়",
            category = "Vocabulary & Advanced",
            summaryBn = "নির্দিষ্ট কিছু শব্দের সাথে সর্বদা সুনির্দিষ্ট Preposition ব্যবহৃত হয়, যাদের মুখস্থ রাখা অপরিহার্য।",
            explanationBn = "ইংরেজিতে কোনো কোনো Verb, Noun বা Adjective নির্দিষ্ট Preposition ছাড়া অন্য কিছু গ্রহণ করে না। ভিন্ন Preposition বসালে অর্থের আমূল পরিবর্তন ঘটে। যেমন: Die of (রোগে মৃত্যু), Die from (অন্য কোনো কারণে মৃত্যু), Die for (দেশের জন্য আত্মত্যাগ)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Die of a disease, Die from overeating/wound, Die for country.","ruleBn":"রোগে মারা গেলে die of, অতিভোজনে die from, এবং দেশের তরে প্রাণ দিলে die for বসে।","exampleEn":"He died of cholera. The soldier died for his country.","exampleBn":"সে কলেরায় মারা গেল। সৈনিকটি দেশের জন্য প্রাণ দিল।"},
                {"ruleNo":2,"ruleEn":"Accustomed to, Addicted to, Abide by, Congratulate on.","ruleBn":"Abide by (মেনে চলা), Congratulate on (অভিনন্দন জানানো), Abstain from (বিরত থাকা)।","exampleEn":"I congratulated him on his brilliant success.","exampleBn":"তার উজ্জ্বল সাফল্যে আমি তাকে অভিনন্দন জানালাম।"}
            ]""",
            examplesJson = """[
                {"english":"She is good at mathematics.","bangla":"সে গণিতে দক্ষ। (দক্ষতা বোঝাতে good at)"},
                {"english":"We must abide by the rules.","bangla":"আমাদের অবশ্যই নিয়মকানুন মেনে চলতে হবে।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He is angry upon me.","correct":"He is angry with me.","explanationBn":"কোনো ব্যক্তির ওপর রাগান্বিত হলে 'angry with' এবং কোনো আচরণের ওপর রাগ হলে 'angry at' বসে।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "synonym",
            topicNumber = 22,
            titleEn = "Synonym",
            titleBn = "সমার্থক শব্দ",
            category = "Vocabulary & Advanced",
            summaryBn = "একই বা প্রায় অভিন্ন অর্থ প্রকাশকারী ভিন্ন ভিন্ন শব্দকে Synonym বা সমার্থক শব্দ বলে।",
            explanationBn = "সমার্থক শব্দের সমৃদ্ধ ভাণ্ডার ইংরেজি লেখা ও বলার দক্ষতাকে নাটকীয়ভাবে বৃদ্ধি করে। পরীক্ষার প্রশ্নের উত্তর করার ক্ষেত্রে শব্দের অন্তর্নিহিত সূক্ষ্ম ভাব বোঝা জরুরি। যেমন: Huge, Gigantic, Enormous, Colossal সবগুলোর অর্থই 'বিশাল'।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"A synonym must maintain the same part of speech as the target word.","ruleBn":"মূল শব্দ যে Parts of Speech হবে, তার Synonym-ও একই Parts of Speech হতে হবে।","exampleEn":"Happy (Adj) -> Joyful (Adj); Happiness (Noun) -> Joy (Noun).","exampleBn":"Happy-র সমার্থক Joyful, কিন্তু Joy নয়।"},
                {"ruleNo":2,"ruleEn":"Context matters: Words have subtle shades of difference.","ruleBn":"বাক্যের ভাবার্থ অনুযায়ী সঠিক সমার্থক শব্দটি বাছাই করতে হয়।","exampleEn":"Slender (আকর্ষণীয় পাতলা) vs Skinny (রুগ্ণ পাতলা).","exampleBn":"উভয় শব্দ পাতলা নির্দেশ করলেও ইতিবাচক ও নেতিবাচক পার্থক্য রয়েছে।"}
            ]""",
            examplesJson = """[
                {"english":"Diligent = Hardworking, Industrious, Assiduous","bangla":"পরিশ্রমী, উদ্যমী।"},
                {"english":"Abundant = Plentiful, Ample, Copious","bangla":"প্রচুর, অপর্যাপ্ত।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"Using 'listen' and 'hear' interchangeably.","correct":"Listen requires focused attention; Hear is passive perception.","explanationBn":"মনোযোগ দিয়ে শোনার ক্ষেত্রে 'listen' এবং অনিচ্ছাকৃতভাবে কানে আওয়াজ আসায় 'hear' ব্যবহৃত হয়।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "antonym",
            topicNumber = 23,
            titleEn = "Antonym",
            titleBn = "বিপরীতার্থক শব্দ",
            category = "Vocabulary & Advanced",
            summaryBn = "পরস্পর বিপরীত বা বিপরীতমুখী অর্থ প্রকাশকারী শব্দযুগলকে Antonym বলে।",
            explanationBn = "উপসর্গ (Prefix যেমন: un-, in-, dis-, mis-, non-) যোগ করে অথবা সম্পূর্ণ ভিন্ন শব্দ ব্যবহার করে Antonym গঠিত হয়। যেমন: Honest -> Dishonest, Legal -> Illegal, Ancient -> Modern, Expand -> Contract।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Use correct negative prefixes (un-, in-, im-, dis-, ir-).","ruleBn":"সঠিক Prefix নির্বাচন করুন: Possible -> Impossible, Regular -> Irregular, Pure -> Impure.","exampleEn":"He was polite, but his brother was impolite.","exampleBn":"সে ভদ্র ছিল, কিন্তু তার ভাই ছিল অভদ্র।"},
                {"ruleNo":2,"ruleEn":"Antonyms must match the target word's grammatical form.","ruleBn":"Noun-এর বিপরীত Noun, Adjective-এর বিপরীত Adjective হতে হবে।","exampleEn":"Friendship (Noun) -> Enmity (Noun); Friendly (Adj) -> Hostile (Adj).","exampleBn":"বন্ধুত্ব -> শত্রুতা; বন্ধুত্বপূর্ণ -> শত্রুতামূলক।"}
            ]""",
            examplesJson = """[
                {"english":"Optimistic (আশাবাদী) <-> Pessimistic (হতাশাবাদী)","bangla":"জীবনদৃষ্টির ক্ষেত্রে বিপরীত দুটি অবস্থা।"},
                {"english":"Barren (অনুর্বর) <-> Fertile (উর্বর)","bangla":"জমির উৎপাদন ক্ষমতার ক্ষেত্রে ব্যবহৃত বিপরীত শব্দ।"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"The antonym of 'Famous' is 'Infamous' in meaning.","correct":"Famous = celebrated; Notorious = known for bad deeds; Unknown = not known.","explanationBn":"Infamous এবং Notorious মানে কুখ্যাত, যা Famous-এর ভালো-খারাপের দ্বন্দ্বে আসে; আর খ্যাতির বিপরীতে আসে Unknown।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "punctuation",
            topicNumber = 24,
            titleEn = "Punctuation & Capitalization",
            titleBn = "বিরামচিহ্ন ও ক্যাপিটালাইজেশন",
            category = "Sentence Structure",
            summaryBn = "বাক্যের অর্থ সুস্পষ্ট ও সুনির্দিষ্ট করতে সঠিক স্থানে বিরামচিহ্ন বসানোর নিয়মাবলি।",
            explanationBn = "সঠিক Punctuation ছাড়া একটি বাক্যের অর্থ পুরোপুরি উল্টো হয়ে যেতে পারে! যেমন: 'Let's eat, Grandpa!' (দাদাকে খেতে ডাকার আনন্দ) বনাম 'Let's eat Grandpa!' (দাদাকে খেয়ে ফেলার ভয়াবহ ভুল!)। প্রধান চিহ্ন: Full Stop (.), Comma (,), Semicolon (;), Colon (:), Apostrophe ('), Question Mark (?), Exclamation Mark (!)।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Apostrophe shows possession (Rahim's book) or contraction (It's = It is).","ruleBn":"মালিকানা বোঝাতে অথবা সংক্ষিপ্ত রূপে Apostrophe বসে। 'It's' মানে It is, কিন্তু 'Its' হলো এর অধিকারসূচক রূপ।","exampleEn":"It's a beautiful day. The bird injured its wing.","exampleBn":"আজ চমৎকার দিন। পাখিটি তার ডানা আহত করেছিল।"},
                {"ruleNo":2,"ruleEn":"Capitalize proper nouns, first word of sentence, and pronoun 'I'.","ruleBn":"বাক্যের প্রথম শব্দ, নির্দিষ্ট নাম এবং সর্বনাম 'I' সর্বদা বড় হাতের অক্ষরে হবে।","exampleEn":"Yesterday, Kabir and I went to Sylhet.","exampleBn":"গতকাল কবির এবং আমি সিলেটে গিয়েছিলাম।"}
            ]""",
            examplesJson = """[
                {"english":"'Where are you going?' asked Mother.","bangla":"'তুমি কোথায় যাচ্ছ?' মা জিজ্ঞাসা করলেন।"},
                {"english":"We bought apples, oranges, and bananas.","bangla":"আমরা আপেল, কমলা এবং কলা কিনলাম। (Oxford Comma)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"The dog wagged it's tail.","correct":"The dog wagged its tail.","explanationBn":"এখানে অধিকার বোঝাতে 'its' হবে। 'It's' মানে 'it is'।"}
            ]"""
        ),
        GrammarTopicEntity(
            id = "sentence_correction",
            topicNumber = 25,
            titleEn = "Sentence Correction",
            titleBn = "ভুল বাক্য সংশোধন",
            category = "Vocabulary & Advanced",
            summaryBn = "ব্যাকরণগত, শব্দচয়নগত বা গঠনগত ত্রুটি শনাক্ত করে শুদ্ধ বাক্য গঠনের কৌশল।",
            explanationBn = "প্রতিযোগিতামূলক পরীক্ষা ও বোর্ড পরীক্ষায় সর্বাধিক নম্বর কাটার অন্যতম জায়গা হলো Sentence Correction। এতে Tense, Subject-Verb Agreement, Preposition, Redundancy (অতিরিক্ত শব্দ ব্যবহার) ইত্যাদির সমষ্টিগত পরীক্ষা নেওয়া হয়।",
            rulesJson = """[
                {"ruleNo":1,"ruleEn":"Avoid redundant / double negatives or repetitive words.","ruleBn":"একই বাক্যে পুনরাবৃত্তিমূলক শব্দ (Redundancy) পরিহার করতে হবে। যেমন: 'Return back' নয়, শুধু 'Return'।","exampleEn":"He returned home yesterday. (Not: returned back)","exampleBn":"সে গতকাল বাড়ি ফিরেছিল।"},
                {"ruleNo":2,"ruleEn":"Dangling modifiers must logically refer to the following subject.","ruleBn":"বাক্যের শুরুতে Participle থাকলে কমার পরের Subject-এর সাথে তার যৌক্তিক সংযোগ থাকতে হবে।","exampleEn":"Walking in the garden, I was bitten by a snake.","exampleBn":"বাগানে হাঁটার সময় একটি সাপ আমাকে কামড় দিয়েছিল।"}
            ]""",
            examplesJson = """[
                {"english":"Incorrect: He is superior than me. -> Correct: He is superior to me.","bangla":"সে আমার চেয়ে জ্যেষ্ঠ/উচ্চতর।"},
                {"english":"Incorrect: I, you and he are friends. -> Correct: You, he and I are friends.","bangla":"তুমি, সে এবং আমি বন্ধু। (231 নিয়ম)"}
            ]""",
            commonMistakesJson = """[
                {"wrong":"He gave me a good advice.","correct":"He gave me good advice / a piece of good advice.","explanationBn":"Advice uncountable noun হওয়ায় এর আগে সরাসরি 'a' বসে না।"}
            ]"""
        )
    )

    val paragraphs: List<ParagraphEntity> = listOf(
        ParagraphEntity(
            id = "aim_in_life",
            titleEn = "My Aim in Life",
            titleBn = "আমার জীবনের লক্ষ্য",
            category = "Personal & Career",
            englishContent = "Every person should have a definite aim in life. A life without an aim is like a ship without a rudder. My aim in life is to become a dedicated teacher. Teaching is a noble profession that shapes the future generation of a nation. In our country, many children in rural areas are deprived of quality education. After completing my graduation and post-graduation in English literature from a reputed university, I intend to return to my ancestral village. I wish to eradicate illiteracy and illuminate young minds with the light of modern education. Moreover, I will provide free coaching to underprivileged and meritorious students who cannot afford educational materials. A committed teacher not only imparts textbook knowledge but also inspires students to become honest, patriotic, and humane citizens. I pray to the Almighty to grant me wisdom and dedication so that I can realize my dream and serve my motherland selflessly.",
            banglaMeaning = "প্রত্যেক মানুষের জীবনে একটি সুনির্দিষ্ট লক্ষ্য থাকা উচিত। লক্ষ্যহীন জীবন হালবিহীন নৌকার মতো। আমার জীবনের লক্ষ্য একজন নিবেদিতপ্রাণ শিক্ষক হওয়া। শিক্ষকতা একটি মহৎ পেশা যা একটি জাতির ভবিষ্যৎ প্রজন্মকে গড়ে তোলে। আমাদের দেশে গ্রামাঞ্চলের বহু শিশু মানসম্মত শিক্ষা থেকে বঞ্চিত। একটি খ্যাতনামা বিশ্ববিদ্যালয় থেকে ইংরেজি সাহিত্যে স্নাতক ও স্নাতকোত্তর সম্পন্ন করার পর আমি আমার পৈতৃক গ্রামে ফিরে যাওয়ার ইচ্ছা রাখি। আমি নিরক্ষরতা দূর করতে এবং আধুনিক শিক্ষার আলোয় তরুণ মনগুলোকে আলোকিত করতে চাই। তাছাড়া, আমি এমন সুবিধাবঞ্চিত ও মেধাবী শিক্ষার্থীদের বিনামূল্যে পড়াব যারা শিক্ষা উপকরণ কিনতে পারে না। একজন নিবেদিত শিক্ষক শুধু পাঠ্যপুস্তকের জ্ঞান দেন না, বরং শিক্ষার্থীদের সৎ, দেশপ্রেমিক ও মানবিক নাগরিক হতে অনুপ্রাণিত করেন। আমি সৃষ্টিকর্তার কাছে প্রার্থনা করি তিনি যেন আমাকে প্রজ্ঞা ও নিষ্ঠা দান করেন যাতে আমি নিঃস্বার্থভাবে মাতৃভূমির সেবা করতে পারি।",
            vocabularyJson = """[
                {"word":"Definite","pronunciation":"ডেফিনিট","meaningBn":"সুনির্দিষ্ট"},
                {"word":"Rudder","pronunciation":"রাডার","meaningBn":"হাল (নৌকা বা জাহাজের)"},
                {"word":"Dedicated","pronunciation":"ডেডিকেটেড","meaningBn":"উৎসর্গীকৃত বা নিবেদিতপ্রাণ"},
                {"word":"Eradicate","pronunciation":"ইরাডিকেট","meaningBn":"নির্মূল করা / দূর করা"},
                {"word":"Illiteracy","pronunciation":"ইলিটারেসি","meaningBn":"নিরক্ষরতা"},
                {"word":"Underprivileged","pronunciation":"আন্ডারপ্রিভিলেজড","meaningBn":"সুবিধাবঞ্চিত"},
                {"word":"Humane","pronunciation":"হিউমেন","meaningBn":"মানবিক / দয়ালু"}
            ]""",
            keySentencesJson = """[
                "A life without an aim is like a ship without a rudder.",
                "Teaching is a noble profession that shapes the future generation of a nation.",
                "A committed teacher inspires students to become honest, patriotic, and humane citizens."
            ]""",
            memorizationTipsBn = "প্রথমে লক্ষ্য থাকার প্রয়োজনীয়তা (ভূমিকা), এরপর আপনার পছন্দ (পেশা ও কারণ), তারপর দেশের জন্য পরিকল্পনা (গ্রামের শিশুদের শিক্ষা), এবং শেষে সংকল্প ও প্রার্থনা—এই ৪টি ধাপে মুখস্থ রাখুন।"
        ),
        ParagraphEntity(
            id = "tree_plantation",
            titleEn = "Tree Plantation",
            titleBn = "বৃক্ষরোপণ কর্মসূচি",
            category = "Nature & Environment",
            englishContent = "Tree plantation means planting trees in large numbers in a planned manner. Trees are our most trusted and indispensable friends in nature. They play a vital role in maintaining the ecological balance of our environment. Trees absorb harmful carbon dioxide from the atmosphere and release life-giving oxygen, which is essential for all living beings. In addition, trees provide us with sweet fruits, timber, medicines, and shade. They prevent soil erosion, absorb flood waters, and bring seasonal rainfall. Unfortunately, due to rapid urbanization, industrialization, and greed, deforestation is taking place at an alarming rate. As a consequence, our mother Earth is facing extreme weather anomalies, global warming, and natural disasters. June and July are the ideal months for tree plantation in Bangladesh during the monsoon season. We should plant trees along road sides, embankments, educational institutions, and vacant spaces. Mass awareness campaigns should be launched through print and electronic media to encourage citizens to plant at least two saplings every year. By preserving trees, we preserve human civilization.",
            banglaMeaning = "বৃক্ষরোপণ বলতে পরিকল্পিত উপায়ে বিপুল সংখ্যায় গাছ লাগানোকে বোঝায়। গাছপালা প্রকৃতিতে আমাদের সবচেয়ে বিশ্বস্ত ও অপরিহার্য বন্ধু। পরিবেশের বাস্তুসংস্থানিক ভারসাম্য বজায় রাখতে তারা গুরুত্বপূর্ণ ভূমিকা পালন করে। গাছ বাতাস থেকে ক্ষতিকারক কার্বন ডাই-অক্সাইড শোষণ করে এবং জীবনদায়ী অক্সিজেন নির্গমন করে, যা সকল জীবের বেঁচে থাকার জন্য অপরিহার্য। তাছাড়া গাছ আমাদের মিষ্টি ফল, কাঠ, ওষুধ ও ছায়া দেয়। তারা ভূমিক্ষয় রোধ করে, বন্যার পানি শোষণ করে এবং মৌসুমি বৃষ্টিপাত ঘটায়। দুর্ভাগ্যবশত, দ্রুত নগরায়ণ, শিল্পায়ন ও লোভের কারণে আশঙ্কাজনক হারে বন নিধন চলছে। ফলে আমাদের পৃথিবী চরম আবহাওয়ার অসঙ্গতি, বৈশ্বিক উষ্ণতা ও প্রাকৃতিক দুর্যোগের মুখোমুখি হচ্ছে। বাংলাদেশে বর্ষাকালে জুন ও জুলাই মাস বৃক্ষরোপণের জন্য আদর্শ সময়। রাস্তাঘাট, বাঁধ, শিক্ষাপ্রতিষ্ঠান ও খালি জায়গায় আমাদের গাছ লাগানো উচিত। প্রতি বছর অন্তত দুটি চারা রোপণের জন্য জনসচেতনতা গড়ে তোলা প্রয়োজন। গাছ রক্ষার মাধ্যমেই আমরা মানব সভ্যতা রক্ষা করতে পারি।",
            vocabularyJson = """[
                {"word":"Indispensable","pronunciation":"ইনডিসপেনসেবল","meaningBn":"অপরিহার্য"},
                {"word":"Ecological balance","pronunciation":"ইকোলজিক্যাল ব্যালেন্স","meaningBn":"বাস্তুসংস্থানিক ভারসাম্য"},
                {"word":"Deforestation","pronunciation":"ডিফরেস্টেশন","meaningBn":"বন উজাড়করণ"},
                {"word":"Soil erosion","pronunciation":"সয়েল ইরোজান","meaningBn":"ভূমির ক্ষয়"},
                {"word":"Sapling","pronunciation":"স্যাপলিং","meaningBn":"ছোট চারাগাছ"},
                {"word":"Anomalies","pronunciation":"অ্যানোমালিজ","meaningBn":"অস্বাভাবিকতা বা অসঙ্গতি"}
            ]""",
            keySentencesJson = """[
                "Trees are our most trusted and indispensable friends in nature.",
                "They absorb harmful carbon dioxide and release life-giving oxygen.",
                "By preserving trees, we preserve human civilization."
            ]""",
            memorizationTipsBn = "১. গাছের উপকারিতা (অক্সিজেন, খাদ্য, পরিবেশ) -> ২. বন কাটার অপকারিতা (উষ্ণায়ন, দুর্যোগ) -> ৩. সমাধানের উপায় (কোথায় ও কখন গাছ লাগাতে হবে) -> ৪. উপসংহার।"
        ),
        ParagraphEntity(
            id = "environment_pollution",
            titleEn = "Environment Pollution",
            titleBn = "পরিবেশ দূষণ",
            category = "Nature & Environment",
            englishContent = "Environment pollution is one of the most alarming challenges confronting the modern world today. The environment consists of air, water, soil, and living species surrounding us. When these vital elements are degraded by hazardous wastes, chemicals, and pollutants, environmental pollution occurs. Air is polluted by toxic smoke emitted from motor vehicles, brick kilns, and industrial plants. Water is contaminated by untreated industrial effluents, agricultural fertilizers, plastic residues, and human excreta. Soil is polluted through excessive pesticides and non-biodegradable plastics. Noise pollution is caused by loud hydraulic horns, loudspeakers, and construction machinery. As a result of this comprehensive pollution, humans suffer from chronic respiratory illnesses, water-borne epidemics, and hearing impairments. Marine ecosystems are also collapsing. To safeguard our future, we must enforce rigorous environmental laws, adopt renewable energy, ban single-use plastics, and implement effective waste management systems.",
            banglaMeaning = "পরিবেশ দূষণ আধুনিক বিশ্বের সবচেয়ে উদ্বেগজনক চ্যালেঞ্জগুলোর অন্যতম। আমাদের চারপাশের বায়ু, পানি, মাটি ও জীবকূল নিয়ে পরিবেশ গঠিত। যখন এই অপরিহার্য উপাদানগুলো ক্ষতিকর বর্জ্য ও বিষাক্ত রাসায়নিকের মাধ্যমে ক্ষতিগ্রস্ত হয়, তখন পরিবেশ দূষণ ঘটে। যানবাহন, ইটভাটা ও কারখানার বিষাক্ত ধোঁয়ায় বায়ু দূষিত হয়। কারখানার অপরিশোধিত বর্জ্য ও প্লাস্টিকের কারণে পানি দূষিত হয়। কীটনাশক ও অপচনশীল প্লাস্টিকের মাধ্যমে মাটি দূষিত হয়। হাইড্রোলিক হর্ন ও মাইকের উচ্চ শব্দে শব্দদূষণ ঘটে। এর ফলে মানুষ শ্বাসকষ্টজনিত রোগ, পানিবাহিত মহামারি ও শ্রবণ সমস্যায় ভোগে। ভবিষ্যৎ সুরক্ষিত করতে আমাদের কঠোর আইন প্রয়োগ ও নবায়নযোগ্য শক্তির ব্যবহার নিশ্চিত করতে হবে।",
            vocabularyJson = """[
                {"word":"Confronting","pronunciation":"কনফ্রন্টিং","meaningBn":"মুখোমুখি হওয়া"},
                {"word":"Effluents","pronunciation":"এফলুয়েন্টস","meaningBn":"শিল্পকারখানার তরল বর্জ্য"},
                {"word":"Non-biodegradable","pronunciation":"নন-বায়োডিগ্রেডেবল","meaningBn":"প্রাকৃতিকভাবে অপচনশীল"},
                {"word":"Impairments","pronunciation":"ইম্পেয়ারমেন্টস","meaningBn":"ক্ষতি বা বৈকল্য"},
                {"word":"Rigorous","pronunciation":"রিগোরাস","meaningBn":"কঠোর বা যথাযথ"}
            ]""",
            keySentencesJson = """[
                "Environment pollution is one of the most alarming challenges confronting the modern world.",
                "Air is polluted by toxic smoke, while water is contaminated by industrial effluents.",
                "To safeguard our future, we must adopt renewable energy and manage wastes efficiently."
            ]""",
            memorizationTipsBn = "চারটি প্রধান দূষণ মনে রাখুন: ১. Air (বায়ু) ২. Water (পানি) ৩. Soil (মাটি) ৪. Sound (শব্দ)। প্রতিটির কারণ ও প্রভাব লিখুন।"
        ),
        ParagraphEntity(
            id = "climate_change",
            titleEn = "Climate Change",
            titleBn = "জলবায়ু পরিবর্তন",
            category = "Nature & Environment",
            englishContent = "Climate change refers to significant, long-term shifts in global temperatures and weather patterns over decades. While climate variations have occurred naturally over millennia, human activities since the Industrial Revolution have become the dominant driver. The unabated burning of fossil fuels such as coal, oil, and natural gas produces greenhouse gases like carbon dioxide and methane. These gases trap solar heat in the atmosphere, causing global warming. Bangladesh is regarded as one of the most vulnerable nations on earth to the adverse impacts of climate change. Rising sea levels threaten to submerge substantial portions of our coastal lowlands, potentially displacing millions of environmental refugees. Furthermore, devastating cyclones, erratic monsoon downpours, flash floods, and prolonged droughts have become frequent. Global leaders must honor international climate commitments, reduce greenhouse emissions, and provide green climate financing to vulnerable nations.",
            banglaMeaning = "জলবায়ু পরিবর্তন বলতে কয়েক দশক ধরে বৈশ্বিক তাপমাত্রা এবং আবহাওয়ার ধরনসমূহের দীর্ঘমেয়াদি তাৎপর্যপূর্ণ পরিবর্তনকে বোঝায়। যদিও প্রাকৃতিকভাবে পরিবর্তন হয়েছে, কিন্তু শিল্প বিপ্লবের পর থেকে মানুষের কর্মকাণ্ড এর প্রধান কারণ হয়ে দাঁড়িয়েছে। কয়লা, তেল ও প্রাকৃতিক গ্যাসের মতো জীবাশ্ম জ্বালানি পোড়ানোর ফলে কার্বন ডাই-অক্সাইড ও মিথেনের মতো গ্রিনহাউস গ্যাস উৎপন্ন হয়। এসব গ্যাস বায়ুমণ্ডলে সৌর তাপ ধরে রাখে, যা বৈশ্বিক উষ্ণতা বাড়িয়ে তোলে। বাংলাদেশ জলবায়ু পরিবর্তনের ক্ষতিকর প্রভাবে বিশ্বের অন্যতম ঝুঁকিপূর্ণ দেশ। সমুদ্রপৃষ্ঠের উচ্চতা বৃদ্ধি আমাদের উপকূলীয় নিম্নাঞ্চলের বিস্তীর্ণ এলাকা প্লাবিত করার ঝুঁকি তৈরি করেছে, যা লাখ লাখ মানুষকে বাস্তুচ্যুত করতে পারে। বৈশ্বিক নেতাদের অবশ্যই কার্বন নিঃসরণ কমাতে হবে এবং ঝুঁকিপূর্ণ দেশগুলোকে জলবায়ু তহবিল সরবরাহ করতে হবে।",
            vocabularyJson = """[
                {"word":"Unabated","pronunciation":"আনঅ্যাবেটেড","meaningBn":"অবিরাম বা অপ্রতিরোধ্য"},
                {"word":"Greenhouse gas","pronunciation":"গ্রিনহাউস গ্যাস","meaningBn":"তাপ ধরে রাখা গ্যাস"},
                {"word":"Vulnerable","pronunciation":"ভালনারেবল","meaningBn":"ঝুঁকিপূর্ণ / অরক্ষিত"},
                {"word":"Displacing","pronunciation":"ডিসপ্লেসিং","meaningBn":"বাস্তুচ্যুত করা"},
                {"word":"Erratic","pronunciation":"ইরাটিক","meaningBn":"অনিয়মিত বা খামখেয়ালি"}
            ]""",
            keySentencesJson = """[
                "Climate change refers to significant, long-term shifts in global weather patterns.",
                "Bangladesh is one of the most vulnerable nations to the adverse impacts of climate change.",
                "Rising sea levels threaten to submerge coastal areas and displace millions."
            ]""",
            memorizationTipsBn = "সংজ্ঞা -> কারণ (Greenhouse effect) -> বাংলাদেশে এর প্রত্যক্ষ প্রভাব (সমুদ্রপৃষ্ঠের উচ্চতা, বন্যা, সাইক্লোন) -> বৈশ্বিক সমাধান।"
        ),
        ParagraphEntity(
            id = "importance_of_education",
            titleEn = "Importance of Education",
            titleBn = "শিক্ষার গুরুত্ব",
            category = "Academic & Knowledge",
            englishContent = "Education is the backbone of a nation. Just as a human body cannot stand upright without a solid backbone, a nation cannot achieve prosperity without enlightened citizens. Education dispels the darkness of ignorance and instills rational thinking, moral values, and creative imagination. It broadens our horizons and equips young minds with technical proficiencies required in the competitive 21st century. An educated individual distinguishes right from wrong and makes sensible contributions toward societal harmony. Conversely, an illiterate person often falls prey to superstitions, poverty, and unemployment. Furthermore, female education plays a paramount role in national upliftment, as educating a woman educates an entire family. Government and civil society must cooperate to ensure inclusive, free, and vocational education for every child regardless of socioeconomic status.",
            banglaMeaning = "শিক্ষা জাতির মেরুদণ্ড। একটি সুদৃঢ় মেরুদণ্ড ছাড়া মানবদেহ যেমন সোজা হয়ে দাঁড়াতে পারে না, তেমনি আলোকিত নাগরিক ছাড়া একটি জাতি সমৃদ্ধি অর্জন করতে পারে না। শিক্ষা অজ্ঞতার অন্ধকার দূর করে এবং যুক্তিবাদী চিন্তা, নৈতিক মূল্যবোধ ও সৃজনশীল কল্পনার বিকাশ ঘটায়। এটি আমাদের দৃষ্টিভঙ্গিকে প্রসারিত করে এবং একবিংশ শতাব্দীর প্রতিযোগিতামূলক বাজারে টিকে থাকার কারিগরি দক্ষতা প্রদান করে। একজন শিক্ষিত ব্যক্তি সঠিক ও ভুলের পার্থক্য করতে পারেন এবং সমাজে ইতিবাচক অবদান রাখেন। বিপরীতভাবে, একজন অশিক্ষিত ব্যক্তি কুসংস্কার, দারিদ্র্য ও বেকারত্বের শিকার হন। এছাড়া নারী শিক্ষা জাতীয় উন্নয়নে অত্যন্ত গুরুত্বপূর্ণ ভূমিকা পালন করে। সরকার ও নাগরিক সমাজকে প্রতিটি শিশুর জন্য অন্তর্ভুক্তিমূলক ও কর্মমুখী শিক্ষা নিশ্চিত করতে একযোগে কাজ করতে হবে।",
            vocabularyJson = """[
                {"word":"Backbone","pronunciation":"ব্যাকবোন","meaningBn":"মেরুদণ্ড"},
                {"word":"Dispels","pronunciation":"ডিসপেলস","meaningBn":"দূর করে / বিতাড়িত করে"},
                {"word":"Proficiencies","pronunciation":"প্রফিশিয়েন্সিজ","meaningBn":"দক্ষতাসমূহ"},
                {"word":"Paramount","pronunciation":"প্যারামাউন্ট","meaningBn":"সর্বোচ্চ / অত্যন্ত গুরুত্বপূর্ণ"},
                {"word":"Vocational","pronunciation":"ভোকেশনাল","meaningBn":"কর্মমুখী বা কারিগরি"}
            ]""",
            keySentencesJson = """[
                "Education is the backbone of a nation.",
                "Education dispels the darkness of ignorance and instills rational thinking.",
                "An educated person distinguishes right from wrong and serves society selflessly."
            ]""",
            memorizationTipsBn = "প্রবাদ (Backbone of a nation) দিয়ে শুরু করুন -> ব্যক্তি ও সমাজে শিক্ষার প্রভাব -> নারী শিক্ষা ও কারিগরি শিক্ষা -> সমাপনী।"
        ),
        ParagraphEntity(
            id = "digital_bangladesh",
            titleEn = "Digital Bangladesh",
            titleBn = "ডিজিটাল বাংলাদেশ ও স্মার্ট বাংলাদেশ",
            category = "Technology",
            englishContent = "Digital Bangladesh represents a transformative vision that revolutionized technological infrastructure across Bangladesh. The core objective was the comprehensive integration of Information and Communication Technology (ICT) into governance, commerce, health, and education. Today, submarine cable connectivity and high-speed broadband reach even remote union parishads. Students attend multimedia classrooms and access digital books, while citizens receive essential civic documents through union digital centers. Mobile financial services like bKash and Nagad have enabled unprecedented financial inclusion for millions of previously unbanked rural people. Freelancing and software development have opened boundless career avenues for youth. As Bangladesh progresses from a digital nation toward a Smart Bangladesh, cybersecurity and artificial intelligence literacy will be pivotal in driving sustainable development.",
            banglaMeaning = "ডিজিটাল বাংলাদেশ একটি রূপান্তরমূলক দৃষ্টিভঙ্গি যা সমগ্র বাংলাদেশের প্রযুক্তিগত অবকাঠামোকে আধুনিকায়ন করেছে। এর মূল লক্ষ্য ছিল শাসনব্যবস্থা, বাণিজ্য, স্বাস্থ্য ও শিক্ষাক্ষেত্রে তথ্য ও যোগাযোগ প্রযুক্তির (আইসিটি) সর্বাত্মক সংহতি সাধন। আজ সাবমেরিন ক্যাবল ও দ্রুতগতির ইন্টারনেট প্রত্যন্ত ইউনিয়ন পর্যন্ত পৌঁছে গেছে। শিক্ষার্থীরা মাল্টিমিডিয়া শ্রেণিকক্ষে পড়াশোনা করছে এবং ডিজিটাল বইয়ের সুবিধা পাচ্ছে। বিকাশ ও নগদের মতো মোবাইল আর্থিক সেবা ব্যাংকিং বহির্ভূত লাখ লাখ মানুষের কাছে সেবা পৌঁছে দিয়েছে। ফ্রিল্যান্সিং ও সফটওয়্যার খাত তরুণদের জন্য অপার সম্ভাবনার দুয়ার খুলে দিয়েছে। ডিজিটাল থেকে 'স্মার্ট বাংলাদেশ'-এর অভিযাত্রায় সাইবার নিরাপত্তা ও কৃত্রিম বুদ্ধিমত্তা অত্যন্ত গুরুত্বপূর্ণ ভূমিকা রাখবে।",
            vocabularyJson = """[
                {"word":"Transformative","pronunciation":"ট্রান্সফরমেটিভ","meaningBn":"রূপান্তরমূলক"},
                {"word":"Broadband","pronunciation":"ব্রডব্যান্ড","meaningBn":"উচ্চগতির ইন্টারনেট"},
                {"word":"Financial inclusion","pronunciation":"ফিনান্সিয়াল ইনক্লুশন","meaningBn":"আর্থিক অন্তর্ভুক্তি"},
                {"word":"Unprecedented","pronunciation":"আনপ্রেসিডেন্টেড","meaningBn":"অভূতপূর্ব"},
                {"word":"Pivotal","pronunciation":"পিভোটাল","meaningBn":"কেন্দ্রীয় বা অতীব গুরুত্বপূর্ণ"}
            ]""",
            keySentencesJson = """[
                "Digital Bangladesh revolutionized technological infrastructure across the nation.",
                "Mobile financial services enabled unprecedented financial inclusion.",
                "The journey from Digital to Smart Bangladesh relies on innovation and tech literacy."
            ]""",
            memorizationTipsBn = "রূপকল্পের ধারণা -> শিক্ষায় ও ব্যাংকিংয়ে পরিবর্তন (MFS, Broadband) -> তরুণদের সুযোগ (Freelancing) -> ভবিষ্যৎ সম্ভাবনা।"
        ),
        ParagraphEntity(
            id = "social_media",
            titleEn = "Social Media",
            titleBn = "সামাজিক যোগাযোগ মাধ্যম",
            category = "Technology",
            englishContent = "Social media platforms such as Facebook, YouTube, Instagram, and LinkedIn have transformed interpersonal communication worldwide. They enable instantaneous connection with loved ones regardless of geographic distances. Social media serves as a potent tool for rapid news dissemination, online entrepreneurship, educational sharing, and philanthropic mobilization during disasters. However, it is a double-edged sword. Unregulated usage often leads to addiction, sleep deprivation, and reduced academic productivity among teenagers. Cyberbullying, propagation of fake news, and privacy breaches pose grave societal hazards. Therefore, users must exercise discernment, digital literacy, and self-restraint to reap the benefits of social media while mitigating its pitfalls.",
            banglaMeaning = "ফেসবুক, ইউটিউব, ইনস্টাগ্রাম ও লিংকডইনের মতো সোশ্যাল মিডিয়া প্ল্যাটফর্মগুলো বিশ্বব্যাপী যোগাযোগ ব্যবস্থাকে আমূল বদলে দিয়েছে। ভৌগোলিক দূরত্ব ভুলে এটি মানুষের তাৎক্ষণিক যোগাযোগের সুযোগ করে দিয়েছে। খবর ছড়ানো, অনলাইন ব্যবসা ও দুর্যোগে তহবিল সংগ্রহের জন্য এটি একটি শক্তিশালী মাধ্যম। তবে এটি একটি দুমুখো তরবারি। অতিরিক্ত ব্যবহারের ফলে আসক্তি, ঘুমের ব্যাঘাত ও পড়াশোনায় ক্ষতি হয়। সাইবার বুলিং ও গুজব সমাজে বড় বিপদ ডেকে আনে। তাই এর সুফল পেতে হলে সতর্কতা ও পরিমিতিবোধ বজায় রাখা আবশ্যক।",
            vocabularyJson = """[
                {"word":"Instantaneous","pronunciation":"ইনস্ট্যান্টেনিয়াস","meaningBn":"তাৎক্ষণিক"},
                {"word":"Double-edged sword","pronunciation":"ডাবল-এজড সোর্ড","meaningBn":"দুমুখো তরবারি"},
                {"word":"Discernment","pronunciation":"ডিসার্নমেন্ট","meaningBn":"বিচক্ষণতা বা দূরদর্শিতা"},
                {"word":"Propagation","pronunciation":"প্রোপাগেশন","meaningBn":"প্রচার বা বিস্তার"},
                {"word":"Mitigating","pronunciation":"মিটিগেটিং","meaningBn":"হ্রাস করা বা উপশম করা"}
            ]""",
            keySentencesJson = """[
                "Social media has transformed human communication worldwide.",
                "It is a double-edged sword with immense benefits and grave risks.",
                "Self-restraint and digital literacy are vital to prevent screen addiction."
            ]""",
            memorizationTipsBn = "ইতিবাচক দিক (যোগাযোগ, ব্যবসা, তথ্য) -> নেতিবাচক দিক (আসক্তি, গুজব, সাইবার ক্রাইম) -> সঠিক ব্যবহারের পরামর্শ।"
        ),
        ParagraphEntity(
            id = "a_book_fair",
            titleEn = "A Book Fair",
            titleBn = "একটি বইমেলা (অমর একুশে বইমেলা)",
            category = "Culture & Society",
            englishContent = "A book fair is an attractive cultural exhibition where diverse publishers assemble to display and sell books. In Bangladesh, the Amar Ekushey Boi Mela, organized annually by Bangla Academy throughout February, holds sublime national significance. It commemorates the supreme sacrifices of the language martyrs of 1952. The fairgrounds buzz with enthusiastic bibliophiles, writers, critics, and students. Vibrant stalls showcase novels, poetry anthologies, history volumes, and science literature. Cultural shows, seminars, and poetry recitation sessions further enrich the vibrant atmosphere. A book fair broadens our intellectual horizons, stimulates reading habits, and unites people in a celebration of literature and heritage.",
            banglaMeaning = "বইমেলা একটি আকর্ষণীয় সাংস্কৃতিক প্রদর্শনী যেখানে বিভিন্ন প্রকাশনী বই প্রদর্শন ও বিক্রির জন্য সমবেত হয়। বাংলাদেশে বাংলা একাডেমি কর্তৃক ফেব্রুয়ারি মাসজুড়ে আয়োজিত 'অমর একুশে বইমেলা' জাতীয়ভাবে অত্যন্ত মর্যাদাপূর্ণ। এটি ১৯৫২ সালের ভাষা শহীদদের সর্বোচ্চ আত্মত্যাগের স্মরণে অনুষ্ঠিত হয়। বইপ্রেমী, লেখক, সমালোচক ও শিক্ষার্থীদের পদচারণায় মেলা মুখরিত হয়ে ওঠে। স্টলগুলোতে উপন্যাস, কবিতা, ইতিহাস ও বিজ্ঞানের বই সাজানো থাকে। বইমেলা মানুষের পাঠাভ্যাস বাড়ায় এবং চিন্তাশক্তিকে শাণিত করে।",
            vocabularyJson = """[
                {"word":"Bibliophiles","pronunciation":"বিবলিওফাইলস","meaningBn":"বইপ্রেমী ব্যক্তিবর্গ"},
                {"word":"Sublime","pronunciation":"সাবলাইম","meaningBn":"উন্নত / মহিমান্বিত"},
                {"word":"Commemorates","pronunciation":"কমেমোরেটস","meaningBn":"স্মরণ করে / স্মরণীয় করে রাখে"},
                {"word":"Anthologies","pronunciation":"অ্যান্থোলজিস","meaningBn":"সংকলনসমূহ"},
                {"word":"Intellectual","pronunciation":"ইন্টেলেকচুয়াল","meaningBn":"বৌদ্ধিক / বুদ্ধিবৃত্তিক"}
            ]""",
            keySentencesJson = """[
                "A book fair is an attractive cultural exhibition for book lovers.",
                "Amar Ekushey Boi Mela commemorates the supreme sacrifices of 1952 language martyrs.",
                "A book fair stimulates reading habits and broadens intellectual outlook."
            ]""",
            memorizationTipsBn = "বইমেলার সংজ্ঞা -> অমর একুশে বইমেলার ঐতিহাসিক প্রেক্ষাপট -> মেলার পরিবেশ ও বইয়ের ধরন -> বই পড়ার গুরুত্ব।"
        ),
        ParagraphEntity(
            id = "a_rainy_day",
            titleEn = "A Rainy Day",
            titleBn = "একটি বৃষ্টির দিন",
            category = "Nature & Lifestyle",
            englishContent = "A rainy day is a day characterized by continuous downpours and an overcast sky. The sun remains obscured behind heavy dark clouds, and gloom blankets the horizon. Streets become waterlogged and muddy, creating acute hardship for daily wage earners, rickshaw pullers, and office commuters. Schools often declare holidays or witness minimal attendance. For people staying indoors, however, a rainy day evokes nostalgia and poetic charm. Families relish warm delicacies like Khichuri with fried Hilsa fish while listening to the soothing rhythm of falling raindrops. While affluent citizens romanticize rain from comfortable balconies, the impoverished struggle under leaking roofs, illustrating the contrasting realities of life.",
            banglaMeaning = "বৃষ্টির দিন বলতে এমন একটি দিনকে বোঝায় যখন অবিরাম বর্ষণ হয় এবং আকাশ মেঘলা থাকে। কালো মেঘের আড়ালে সূর্য ঢাকা পড়ে থাকে এবং চারদিকে অন্ধকার নেমে আসে। রাস্তাঘাট কর্দমাক্ত ও জলমগ্ন হয়ে পড়ে, যা দিনমজুর ও রিকশাচালকদের জন্য প্রচণ্ড দুর্ভোগ ডেকে আনে। শিক্ষাপ্রতিষ্ঠানে উপস্থিতি অনেক কমে যায়। তবে ঘরে থাকা মানুষদের জন্য বৃষ্টির দিন এক মধুর আবহ ও নস্টালজিয়া বয়ে আনে। ইলিশ মাছ ভাজা ও গরম খিচুড়ি খাওয়ার ধুম পড়ে। ধনীরা বারান্দায় বসে বৃষ্টি উপভোগ করলেও দরিদ্ররা ফুটো চালার নিচে কষ্ট পায়, যা জীবনের দ্বৈত রূপ তুলে ধরে।",
            vocabularyJson = """[
                {"word":"Downpour","pronunciation":"ডাউনপোর","meaningBn":"ভারী বর্ষণ / মুষলধারে বৃষ্টি"},
                {"word":"Overcast","pronunciation":"ওভারকাস্ট","meaningBn":"মেঘলা / মেঘাচ্ছন্ন"},
                {"word":"Waterlogged","pronunciation":"ওয়াটারলগড","meaningBn":"জলমগ্ন বা জলাবদ্ধ"},
                {"word":"Nostalgia","pronunciation":"নস্টালজিয়া","meaningBn":"স্মৃতিকাতরতা"},
                {"word":"Impoverished","pronunciation":"ইমপভারিশড","meaningBn":"দরিদ্র / নিঃস্ব"}
            ]""",
            keySentencesJson = """[
                "A rainy day is characterized by continuous rain and dark clouds.",
                "Streets become muddy and waterlogged, causing distress to day laborers.",
                "It offers poetic comfort to some, while bringing misery to the poor."
            ]""",
            memorizationTipsBn = "আকাশের বর্ণনা -> সাধারণ মানুষের ভোগান্তি (শ্রমিক, রিকশাচালক) -> ঘরে থাকা মানুষের আনন্দ (খিচুড়ি) -> দুই শ্রেণির বিপরীত চিত্র।"
        ),
        ParagraphEntity(
            id = "traffic_jam",
            titleEn = "Traffic Jam",
            titleBn = "যানজট",
            category = "Social Issue",
            englishContent = "Traffic jam is a pervasive urban crisis in Bangladesh, especially in megacities like Dhaka and Chittagong. It occurs when a congested line of vehicles halts on roads, impeding normal transit. The primary culprits include disproportionate population growth, narrow thoroughfares, an influx of unlicensed vehicles, illegal roadside parking, and reckless disregard for traffic regulations. Reckless driving and jaywalking further exacerbate the stalemate. Commuters endure hours trapped in stationary vehicles, resulting in wasted working hours, psychological distress, and surging fuel consumption. Emergency patients in ambulances tragically lose precious moments. To curb traffic congestion, authorities must enforce stringent traffic laws, remove encroachments, expand public transit such as the Metro Rail and elevated expressways, and decentralize administrative hubs.",
            banglaMeaning = "যানজট বাংলাদেশের শহরগুলোর একটি তীব্র ও নিত্যদিনের সমস্যা, বিশেষ করে ঢাকা ও চট্টগ্রামের মতো জনবহুল শহরে। যখন সড়কে যানবাহনের দীর্ঘ সারি আটকে যায় এবং চলাচল বন্ধ হয়ে পড়ে, তখন যানজট সৃষ্টি হয়। অপর্যাপ্ত প্রশস্ত সড়ক, জনসংখ্যার আধিক্য, অবৈধ পার্কিং এবং ট্রাফিক নিয়ম না মানা এর প্রধান কারণ। এতে যাত্রীদের ঘণ্টার পর ঘণ্টা গাড়িতে বসে থাকতে হয়, যার ফলে কর্মঘণ্টা নষ্ট হয় ও মানসিক ক্লান্তি বাড়ে। অ্যাম্বুলেন্সে থাকা মুমূর্ষু রোগীরা সঠিক সময়ে হাসপাতালে পৌঁছাতে পারে না। এই সমস্যা সমাধানে ট্রাফিক আইনের কঠোর প্রয়োগ, মেট্রো রেল ও এক্সপ্রেসওয়ের সম্প্রসারণ এবং প্রশাসনিক বিকেন্দ্রীকরণ জরুরি।",
            vocabularyJson = """[
                {"word":"Pervasive","pronunciation":"পারভেসিভ","meaningBn":"সর্বব্যাপী / প্রকট"},
                {"word":"Impeding","pronunciation":"ইম্পিডিং","meaningBn":"বাধাগ্রস্ত করা"},
                {"word":"Encroachments","pronunciation":"এনক্রোচমেন্টস","meaningBn":"অবৈধ দখল"},
                {"word":"Decentralize","pronunciation":"ডিসেন্ট্রালাইজ","meaningBn":"বিকেন্দ্রীকরণ করা"},
                {"word":"Surging","pronunciation":"সার্জিং","meaningBn":"উদ্বেগজনকভাবে বৃদ্ধি পাওয়া"}
            ]""",
            keySentencesJson = """[
                "Traffic jam is a pervasive urban crisis causing severe daily distress.",
                "Narrow roads, reckless driving, and lack of civic rules worsen congestion.",
                "Metro Rail, flyovers, and decentralization are vital remedies."
            ]""",
            memorizationTipsBn = "যানজটের তীব্রতা -> মূল কারণসমূহ (গাড়ি বৃদ্ধি, অবৈধ পার্কিং) -> ক্ষতিকর প্রভাব (কর্মঘণ্টা নষ্ট, রোগীর কষ্ট) -> সমাধান (মেট্রোরেল, কঠোর আইন)।"
        )
    )

    val questions: List<QuestionEntity> = listOf(
        QuestionEntity(
            id = "q1",
            questionEn = "Identify the part of speech of the underlined word: 'Water the plants daily.'",
            optionA = "Noun",
            optionB = "Verb",
            optionC = "Adjective",
            optionD = "Adverb",
            correctOption = "B",
            explanationBn = "এখানে 'Water' শব্দটি গাছে পানি দেওয়ার কাজ (Action) বোঝাচ্ছে, তাই এটি একটি Verb।",
            category = "Parts of Speech",
            difficulty = "Medium",
            grammarTopicId = "parts_of_speech"
        ),
        QuestionEntity(
            id = "q2",
            questionEn = "Which of the following is an Abstract Noun?",
            optionA = "Flock",
            optionB = "Gold",
            optionC = "Childhood",
            optionD = "City",
            correctOption = "C",
            explanationBn = "'Childhood' (শৈশব) একটি অবস্থা নির্দেশ করে যা পঞ্চ ইন্দ্রিয় দিয়ে স্পর্শ করা যায় না, শুধু অনুভব করা যায়। তাই এটি Abstract Noun।",
            category = "Noun",
            difficulty = "Easy",
            grammarTopicId = "noun"
        ),
        QuestionEntity(
            id = "q3",
            questionEn = "Choose the correct sentence following the Pronoun order for good deeds:",
            optionA = "I, you and he will go.",
            optionB = "You, he and I will go.",
            optionC = "He, you and I will go.",
            optionD = "You, I and he will go.",
            correctOption = "B",
            explanationBn = "স্বাভাবিক বা ভালো কাজের ক্ষেত্রে 231 নিয়ম মেনে Pronoun বসে: Second (You) -> Third (He) -> First (I)।",
            category = "Pronoun",
            difficulty = "Medium",
            grammarTopicId = "pronoun"
        ),
        QuestionEntity(
            id = "q4",
            questionEn = "Fill in the blank: 'He is proficient _____ English.'",
            optionA = "at",
            optionB = "in",
            optionC = "with",
            optionD = "for",
            correctOption = "B",
            explanationBn = "কোনো ভাষা বা বিষয়ে দক্ষতা প্রকাশ করতে 'proficient in' ব্যবহৃত হয়। (Note: Good at কিন্তু Proficient in)।",
            category = "Appropriate Preposition",
            difficulty = "Medium",
            grammarTopicId = "appropriate_preposition"
        ),
        QuestionEntity(
            id = "q5",
            questionEn = "The patient had died before the doctor _____.",
            optionA = "came",
            optionB = "comes",
            optionC = "had come",
            optionD = "has come",
            correctOption = "A",
            explanationBn = "Past Perfect Tense-এ 'before'-এর পূর্বের অংশ Past Perfect হলে পরের অংশ Past Simple (came) হয়।",
            category = "Tense",
            difficulty = "Easy",
            grammarTopicId = "tense"
        ),
        QuestionEntity(
            id = "q6",
            questionEn = "I look forward to _____ from you soon.",
            optionA = "hear",
            optionB = "hearing",
            optionC = "heard",
            optionD = "be heard",
            correctOption = "B",
            explanationBn = "'Look forward to'-এর পর মূল Verb-এর সাথে '-ing' যুক্ত করতে হয়। তাই 'hearing' সঠিক উত্তর।",
            category = "Right Form of Verb",
            difficulty = "Medium",
            grammarTopicId = "right_form_of_verb"
        ),
        QuestionEntity(
            id = "q7",
            questionEn = "One of my friends _____ a famous surgeon.",
            optionA = "are",
            optionB = "is",
            optionC = "were",
            optionD = "have been",
            correctOption = "B",
            explanationBn = "'One of'-এর পর Noun plural হলেও আসল Subject হলো 'One', তাই Singular Verb (is) বসবে।",
            category = "Subject-Verb Agreement",
            difficulty = "Medium",
            grammarTopicId = "subject_verb_agreement"
        ),
        QuestionEntity(
            id = "q8",
            questionEn = "He is _____ European scholar.",
            optionA = "a",
            optionB = "an",
            optionC = "the",
            optionD = "no article",
            correctOption = "A",
            explanationBn = "'European'-এর শুরুতে 'E' ভাওয়েল হলেও এর উচ্চারণ 'ইউ' (Ju:) এর মতো হওয়ায় 'a' বসবে।",
            category = "Article",
            difficulty = "Easy",
            grammarTopicId = "article"
        ),
        QuestionEntity(
            id = "q9",
            questionEn = "Change into Passive: 'Do the work.'",
            optionA = "The work should do.",
            optionB = "Let the work be done.",
            optionC = "Let the work do.",
            optionD = "You are done the work.",
            correctOption = "B",
            explanationBn = "Imperative বাক্যে Voice রূপান্তরের নিয়ম: Let + Object (the work) + be + V3 (done)।",
            category = "Voice",
            difficulty = "Easy",
            grammarTopicId = "voice"
        ),
        QuestionEntity(
            id = "q10",
            questionEn = "Barking dogs seldom bite, _____?",
            optionA = "don't they",
            optionB = "do they",
            optionC = "aren't they",
            optionD = "can't they",
            correctOption = "B",
            explanationBn = "'Seldom' একটি না-বোধক শব্দ। মূল বাক্য না-বোধক হলে Tag Question হ্যাঁ-বোধক (do they) হয়।",
            category = "Tag Question",
            difficulty = "Hard",
            grammarTopicId = "tag_question"
        ),
        QuestionEntity(
            id = "q11",
            questionEn = "Walk fast lest you _____ miss the bus.",
            optionA = "will",
            optionB = "should",
            optionC = "can",
            optionD = "might not",
            correctOption = "B",
            explanationBn = "'Lest'-এর পর Subject + should + V1 বসে। 'Lest' নিজেই না-বোধক অর্থ প্রকাশ করে।",
            category = "Completing Sentence",
            difficulty = "Medium",
            grammarTopicId = "completing_sentence"
        ),
        QuestionEntity(
            id = "q12",
            questionEn = "If I had seen you, I _____ you the message.",
            optionA = "would give",
            optionB = "would have given",
            optionC = "will give",
            optionD = "had given",
            correctOption = "B",
            explanationBn = "Third Conditional: If + Past Perfect হলে অপর অংশে 'would have + V3' বসে।",
            category = "Conditional Sentence",
            difficulty = "Medium",
            grammarTopicId = "conditional_sentence"
        ),
        QuestionEntity(
            id = "q13",
            questionEn = "What is the synonym of 'Diligent'?",
            optionA = "Lazy",
            optionB = "Careless",
            optionC = "Industrious",
            optionD = "Hesitant",
            correctOption = "C",
            explanationBn = "'Diligent' মানে পরিশ্রমী। এর সঠিক সমার্থক শব্দ 'Industrious' (উদ্যমী বা পরিশ্রমী)।",
            category = "Synonym",
            difficulty = "Easy",
            grammarTopicId = "synonym"
        ),
        QuestionEntity(
            id = "q14",
            questionEn = "What is the antonym of 'Barren'?",
            optionA = "Dry",
            optionB = "Fertile",
            optionC = "Empty",
            optionD = "Rough",
            correctOption = "B",
            explanationBn = "'Barren' মানে অনুর্বর। এর বিপরীত শব্দ হলো 'Fertile' (উর্বর)।",
            category = "Antonym",
            difficulty = "Easy",
            grammarTopicId = "antonym"
        ),
        QuestionEntity(
            id = "q15",
            questionEn = "Which sentence has correct punctuation?",
            optionA = "The dog wagged it's tail.",
            optionB = "The dog wagged its tail.",
            optionC = "The dog wagged its' tail.",
            optionD = "The dog, wagged its tail.",
            correctOption = "B",
            explanationBn = "'Its' হলো অধিকারসূচক (Possessive pronoun)। 'It's' মানে 'It is', যা এখানে অপ্রাসঙ্গিক।",
            category = "Punctuation",
            difficulty = "Medium",
            grammarTopicId = "punctuation"
        ),
        QuestionEntity(
            id = "dq1",
            questionEn = "Today's Quiz: 'Neither the teacher nor the students _____ present.'",
            optionA = "was",
            optionB = "were",
            optionC = "is",
            optionD = "are",
            correctOption = "B",
            explanationBn = "'Neither...nor' দিয়ে যুক্ত হলে Verb-এর নিকটবর্তী Subject (students) বহুবচন হওয়ায় Verb 'were' হবে।",
            category = "Daily Quiz",
            difficulty = "Medium",
            isDailyQuiz = true
        ),
        QuestionEntity(
            id = "dq2",
            questionEn = "Today's Quiz: Choose the correct spelling:",
            optionA = "Accomodation",
            optionB = "Accommodation",
            optionC = "Acommodation",
            optionD = "Accomadation",
            correctOption = "B",
            explanationBn = "সঠিক বানান হলো 'Accommodation' (ডাবল c এবং ডাবল m)।",
            category = "Daily Quiz",
            difficulty = "Easy",
            isDailyQuiz = true
        ),
        QuestionEntity(
            id = "dq3",
            questionEn = "Today's Quiz: 'He died _____ overeating.'",
            optionA = "of",
            optionB = "from",
            optionC = "by",
            optionD = "for",
            correctOption = "B",
            explanationBn = "রোগ ছাড়া অতিরিক্ত খাওয়া, পরিশ্রম বা আঘাতের কারণে মৃত্যু হলে 'die from' বসে।",
            category = "Daily Quiz",
            difficulty = "Medium",
            isDailyQuiz = true
        )
    )

    val exams: List<ExamEntity> = listOf(
        ExamEntity(
            id = "exam_ssc_model",
            titleEn = "SSC Grammar Model Test 2026",
            titleBn = "এসএসসি গ্রামার পূর্ণাঙ্গ মডেল টেস্ট",
            examType = "Model Test",
            durationMinutes = 15,
            totalQuestions = 15,
            difficulty = "Standard (এসএসসি মান)",
            category = "Grammar & Board"
        ),
        ExamEntity(
            id = "exam_hsc_mixed",
            titleEn = "HSC English 2nd Paper Mega Exam",
            titleBn = "এইচএসসি দ্বিতীয় পত্র বিশেষ পরীক্ষা",
            examType = "Model Test",
            durationMinutes = 20,
            totalQuestions = 20,
            difficulty = "Intermediate (এইচএসসি মান)",
            category = "Grammar & Board"
        ),
        ExamEntity(
            id = "exam_admission",
            titleEn = "Dhaka University Admission Test Prep",
            titleBn = "বিশ্ববিদ্যালয় ভর্তি প্রস্তুতি মডেল টেস্ট",
            examType = "Mixed English Exam",
            durationMinutes = 15,
            totalQuestions = 15,
            difficulty = "Advanced (ভর্তি পরীক্ষা মান)",
            category = "Competitive"
        ),
        ExamEntity(
            id = "exam_bcs_grammar",
            titleEn = "BCS & Bank Job English Exam",
            titleBn = "বিসিএস ও সরকারি চাকরি প্রস্তুতি পরীক্ষা",
            examType = "Grammar Exam",
            durationMinutes = 20,
            totalQuestions = 20,
            difficulty = "Expert (চাকরি পরীক্ষা মান)",
            category = "Competitive"
        ),
        ExamEntity(
            id = "exam_daily_test",
            titleEn = "Quick 5-Minute Daily Test",
            titleBn = "দৈনিক ৫ মিনিটের স্পিড টেস্ট",
            examType = "Daily Test",
            durationMinutes = 5,
            totalQuestions = 5,
            difficulty = "Easy/Medium",
            category = "Daily"
        )
    )

    val vocabularyList: List<VocabularyEntity> = listOf(
        VocabularyEntity(
            id = "v1",
            word = "Diligent",
            pronunciation = "ডিলিজেন্ট",
            meaningBn = "পরিশ্রমী, অধ্যবসায়ী",
            partsOfSpeech = "Adjective",
            exampleSentence = "A diligent student always overcomes difficulties.",
            exampleSentenceBn = "একজন পরিশ্রমী শিক্ষার্থী সর্বদা বাধাবিপত্তি অতিক্রম করে।",
            category = "High Frequency"
        ),
        VocabularyEntity(
            id = "v2",
            word = "Pivotal",
            pronunciation = "পিভোটাল",
            meaningBn = "কেন্দ্রীয়, অত্যন্ত গুরুত্বপূর্ণ",
            partsOfSpeech = "Adjective",
            exampleSentence = "Education plays a pivotal role in national prosperity.",
            exampleSentenceBn = "শিক্ষা জাতীয় সমৃদ্ধিতে একটি অত্যন্ত গুরুত্বপূর্ণ ভূমিকা পালন করে।",
            category = "Academic"
        ),
        VocabularyEntity(
            id = "v3",
            word = "Eradicate",
            pronunciation = "ইরাডিকেট",
            meaningBn = "নির্মূল করা, সমূলে বিনষ্ট করা",
            partsOfSpeech = "Verb",
            exampleSentence = "We must unite to eradicate poverty from society.",
            exampleSentenceBn = "সমাজ থেকে দারিদ্র্য দূর করতে আমাদের অবশ্যই ঐক্যবদ্ধ হতে হবে।",
            category = "Academic"
        ),
        VocabularyEntity(
            id = "v4",
            word = "Pragmatic",
            pronunciation = "প্র্যাগম্যাটিক",
            meaningBn = "বাস্তবমুখী, প্রয়োগধর্মী",
            partsOfSpeech = "Adjective",
            exampleSentence = "We need pragmatic solutions rather than mere theories.",
            exampleSentenceBn = "কেবলমাত্র তত্ত্বের চেয়ে আমাদের বাস্তবমুখী সমাধানের প্রয়োজন।",
            category = "Advanced"
        ),
        VocabularyEntity(
            id = "v5",
            word = "Benevolent",
            pronunciation = "বেনেভোলেন্ট",
            meaningBn = "দয়ালু, পরোপকারী",
            partsOfSpeech = "Adjective",
            exampleSentence = "The benevolent teacher helped poor students with books.",
            exampleSentenceBn = "দয়ালু শিক্ষক দরিদ্র শিক্ষার্থীদের বই দিয়ে সাহায্য করেছিলেন।",
            category = "High Frequency"
        ),
        VocabularyEntity(
            id = "v6",
            word = "Ephemeral",
            pronunciation = "ইফেমারাল",
            meaningBn = "ক্ষণস্থায়ী, ক্ষণভঙ্গুর",
            partsOfSpeech = "Adjective",
            exampleSentence = "Worldly pleasures are ephemeral, but noble deeds last.",
            exampleSentenceBn = "পার্থিব সুখ ক্ষণস্থায়ী, তবে মহৎ কাজ চিরস্থায়ী।",
            category = "Advanced"
        )
    )

    val achievements: List<AchievementEntity> = listOf(
        AchievementEntity(
            id = "first_quiz",
            titleEn = "First Quiz",
            titleBn = "প্রথম কুইজ",
            descriptionBn = "প্রথম কোনো কুইজ সফলভাবে সম্পন্ন করুন।",
            badgeIcon = "quiz",
            isUnlocked = true,
            requiredProgress = 1,
            currentProgress = 1
        ),
        AchievementEntity(
            id = "quiz_10",
            titleEn = "10 Quizzes Completed",
            titleBn = "১০টি কুইজ সম্পন্ন",
            descriptionBn = "যেকোনো ১০টি কুইজ পরীক্ষা সম্পন্ন করুন।",
            badgeIcon = "stars",
            isUnlocked = false,
            requiredProgress = 10,
            currentProgress = 3
        ),
        AchievementEntity(
            id = "correct_100",
            titleEn = "100 Correct Answers",
            titleBn = "১০০টি সঠিক উত্তর",
            descriptionBn = "কুইজ ও পরীক্ষায় ১০০টি সঠিক উত্তর দিন।",
            badgeIcon = "done_all",
            isUnlocked = false,
            requiredProgress = 100,
            currentProgress = 24
        ),
        AchievementEntity(
            id = "grammar_master",
            titleEn = "Grammar Master",
            titleBn = "গ্রামার মাস্টার",
            descriptionBn = "সবগুলো মৌলিক গ্রামার পাঠ অধ্যয়ন করুন।",
            badgeIcon = "menu_book",
            isUnlocked = false,
            requiredProgress = 25,
            currentProgress = 5
        ),
        AchievementEntity(
            id = "paragraph_master",
            titleEn = "Paragraph Master",
            titleBn = "প্যারাগ্রাফ মাস্টার",
            descriptionBn = "১০টি ভিন্ন ভিন্ন প্যারাগ্রাফ ভালোভাবে আয়ত্ত করুন।",
            badgeIcon = "article",
            isUnlocked = false,
            requiredProgress = 10,
            currentProgress = 2
        ),
        AchievementEntity(
            id = "streak_7",
            titleEn = "7 Day Streak",
            titleBn = "৭ দিনের স্ট্রিক",
            descriptionBn = "টানা ৭ দিন অ্যাপে নিয়মিত অনুশীলন বজায় রাখুন।",
            badgeIcon = "local_fire_department",
            isUnlocked = false,
            requiredProgress = 7,
            currentProgress = 3
        ),
        AchievementEntity(
            id = "exam_champion",
            titleEn = "Exam Champion",
            titleBn = "পরীক্ষার চ্যাম্পিয়ন",
            descriptionBn = "যেকোনো পূর্ণাঙ্গ মডেল টেস্টে ৮০%+ নম্বর অর্জন করুন।",
            badgeIcon = "military_tech",
            isUnlocked = false,
            requiredProgress = 1,
            currentProgress = 0
        )
    )

    val videoLessons: List<VideoLessonEntity> = listOf(
        VideoLessonEntity(
            id = "vid_tense",
            titleEn = "Complete Tense Masterclass in Bangla",
            titleBn = "টেন্স সহজে মনে রাখার সেরা কৌশল",
            youtubeVideoId = "gG164oE4kQI",
            category = "Grammar Masterclass",
            duration = "24:15",
            instructor = "English Master BD",
            descriptionBn = "১২টি Tense-এর গঠন ও উদাহরণ বাংলা ব্যাখ্যার মাধ্যমে সহজে আয়ত্ত করুন।"
        ),
        VideoLessonEntity(
            id = "vid_rfv",
            titleEn = "Right Form of Verbs Top 10 Rules",
            titleBn = "Right Form of Verbs-এর গুরুত্বপূর্ণ ১০টি নিয়ম",
            youtubeVideoId = "V_P4K9f5a0M",
            category = "Grammar Masterclass",
            duration = "18:30",
            instructor = "English Master BD",
            descriptionBn = "বোর্ড ও ভর্তি পরীক্ষায় আসা কমন নিয়মগুলো সহজ শর্টকাট টেকনিকে শিখুন।"
        ),
        VideoLessonEntity(
            id = "vid_spoken",
            titleEn = "Daily Spoken English for Beginners",
            titleBn = "দৈনন্দিন জীবনে প্রয়োজনীয় ৫০টি ইংরেজি বাক্য",
            youtubeVideoId = "kJQP7kiw5Fk",
            category = "Spoken English",
            duration = "15:40",
            instructor = "English Master BD",
            descriptionBn = "অনর্গল ইংরেজি বলার জন্য প্রতিদিনের কথোপকথনে ব্যবহৃত বাক্যসমূহ।"
        ),
        VideoLessonEntity(
            id = "vid_voice",
            titleEn = "Voice Change Active to Passive Magic Rules",
            titleBn = "বাচ্য পরিবর্তন (Active & Passive Voice) সহজ টেকনিক",
            youtubeVideoId = "M7lc1UVf-VE",
            category = "Grammar Masterclass",
            duration = "21:10",
            instructor = "English Master BD",
            descriptionBn = "Voice Change-এর সকল প্যাটার্ন ও ব্যতিক্রমী নিয়ম বাংলা ব্যাখাসহ।"
        ),
        VideoLessonEntity(
            id = "vid_paragraph",
            titleEn = "How to Write Full Marks Paragraph in Board Exams",
            titleBn = "প্যারাগ্রাফে পূর্ণ নম্বর পাওয়ার কার্যকরী কৌশল",
            youtubeVideoId = "ZbZSe6N_BXs",
            category = "Writing Skills",
            duration = "16:20",
            instructor = "English Master BD",
            descriptionBn = "ভূমিকা, মূল বক্তব্য ও উপসংহার সাজানোর সঠিক দিকনির্দেশনা।"
        ),
        VideoLessonEntity(
            id = "vid_narration",
            titleEn = "Direct and Indirect Speech (Narration) Bangla",
            titleBn = "উক্তি পরিবর্তন (Narration) সহজে শেখার পদ্ধতি",
            youtubeVideoId = "fJ9rUzIMcZQ",
            category = "Grammar Masterclass",
            duration = "22:45",
            instructor = "English Master BD",
            descriptionBn = "Person পরিবর্তন ও Tense পরিবর্তনের স্পষ্ট ব্যাখ্যা।"
        ),
        VideoLessonEntity(
            id = "vid_preposition",
            titleEn = "Appropriate Preposition Master Session",
            titleBn = "যথাযথ অব্যয় (Appropriate Preposition) শর্টকাট",
            youtubeVideoId = "3JZ_D3ELwOQ",
            category = "Admission & BCS",
            duration = "19:50",
            instructor = "English Master BD",
            descriptionBn = "ভর্তি পরীক্ষা ও চাকুরির পরীক্ষায় বারবার আসা Preposition সমূহ।"
        ),
        VideoLessonEntity(
            id = "vid_hsc_ssc",
            titleEn = "HSC & SSC English 2nd Paper Mega Revision",
            titleBn = "এসএসসি ও এইচএসসি ইংরেজি ২য় পত্র রিভিশন ক্লাস",
            youtubeVideoId = "OPf0YbXqDm0",
            category = "HSC & SSC Special",
            duration = "28:10",
            instructor = "English Master BD",
            descriptionBn = "টপ গ্রেড নিশ্চিত করার জন্য মডেল প্রশ্ন সলভিং ক্লাস।"
        )
    )
}
