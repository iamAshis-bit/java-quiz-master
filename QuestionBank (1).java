package quizgame;

public class Player {
    private String name;
    private int score;
    private int correctAnswers;
    private int wrongAnswers;
    private int streak;
    private int maxStreak;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
        this.streak = 0;
        this.maxStreak = 0;
    }

    public void addScore(int points) {
        this.score += points;
    }

    public void correctAnswer(int points) {
        correctAnswers++;
        streak++;
        if (streak > maxStreak) maxStreak = streak;
        // Bonus for streak
        if (streak >= 3) {
            int bonus = (streak / 3) * 5;
            score += points + bonus;
        } else {
            score += points;
        }
    }

    public void wrongAnswer() {
        wrongAnswers++;
        streak = 0;
    }

    public String getName()         { return name; }
    public int getScore()           { return score; }
    public int getCorrectAnswers()  { return correctAnswers; }
    public int getWrongAnswers()    { return wrongAnswers; }
    public int getStreak()          { return streak; }
    public int getMaxStreak()       { return maxStreak; }

    public double getAccuracy() {
        int total = correctAnswers + wrongAnswers;
        if (total == 0) return 0;
        return (correctAnswers * 100.0) / total;
    }

    public String getRank() {
        if (score >= 200)      return "🏆 LEGEND";
        else if (score >= 150) return "💎 EXPERT";
        else if (score >= 100) return "🥇 ADVANCED";
        else if (score >= 60)  return "🥈 INTERMEDIATE";
        else if (score >= 30)  return "🥉 BEGINNER";
        else                   return "🌱 NOVICE";
    }
}
