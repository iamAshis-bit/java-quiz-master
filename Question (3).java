# 🧠 Java Quiz Master

A feature-rich console-based Quiz Game built in Java with colorful terminal UI, multiple game modes, streak bonuses, and a leaderboard.

## 🎮 Features

- **3 Game Modes** — Standard (10Q), Speed (5Q), Marathon (15Q)
- **4 Categories** — ☕ Java, 💻 Technology, 🔬 Science, 🌍 General Knowledge
- **Timed Questions** — 15 seconds per question
- **Speed Bonus** — Answer fast = extra points
- **Streak System** — 3+ correct in a row = bonus multiplier
- **Rank System** — 🌱 Novice → 🏆 Legend
- **Hall of Fame** — Leaderboard screen
- **Colorful UI** — ANSI color codes for a rich console experience

## 📁 Project Structure

```
QuizGame/
└── src/
    └── quizgame/
        ├── QuizGame.java       ← Main game engine + UI
        ├── Question.java       ← Question model
        ├── Player.java         ← Player model + scoring
        └── QuestionBank.java   ← All quiz questions
```

## 🚀 How to Run

### Compile
```bash
cd QuizGame/src
javac quizgame/*.java
```

### Run
```bash
java quizgame.QuizGame
```

> Run in a terminal that supports ANSI colors (Linux/Mac Terminal, Windows Terminal, Git Bash)

## 🏆 Rank System

| Score     | Rank           |
|-----------|----------------|
| 0–29      | 🌱 Novice      |
| 30–59     | 🥉 Beginner    |
| 60–99     | 🥈 Intermediate|
| 100–149   | 🥇 Advanced    |
| 150–199   | 💎 Expert      |
| 200+      | 🏆 Legend      |

## 📝 Scoring

- Base points: **10–15 per question**
- Time bonus: `(15 - seconds_taken)` points
- Streak bonus: Every 3 correct in a row = +5 bonus

## 🛠 Requirements

- Java 11 or higher
- Terminal with ANSI color support

## 👨‍💻 Author

**Ashis Kumar Sahoo**  
NexaCore Technologies  
