package quizgame;

import java.util.*;

public class QuizGame {

    // ANSI Color Codes
    static final String RESET   = "\u001B[0m";
    static final String BOLD    = "\u001B[1m";
    static final String RED     = "\u001B[31m";
    static final String GREEN   = "\u001B[32m";
    static final String YELLOW  = "\u001B[33m";
    static final String BLUE    = "\u001B[34m";
    static final String MAGENTA = "\u001B[35m";
    static final String CYAN    = "\u001B[36m";
    static final String WHITE   = "\u001B[37m";
    static final String BG_BLUE = "\u001B[44m";

    static Scanner scanner = new Scanner(System.in);
    static final int TIME_LIMIT_SECONDS = 15;

    public static void main(String[] args) {
        clearScreen();
        showBanner();
        System.out.print(CYAN + BOLD + "  Enter your name, Player: " + RESET);
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Player";

        Player player = new Player(name);

        boolean playing = true;
        while (playing) {
            clearScreen();
            showMenu(player);
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1": playGame(player, 10, "STANDARD");  break;
                case "2": playGame(player, 5,  "SPEED");     break;
                case "3": playGame(player, 15, "MARATHON");  break;
                case "4": showLeaderboard(player);           break;
                case "5": showHelp();                        break;
                case "6": playing = false;                   break;
                default:
                    System.out.println(RED + "  Invalid choice! Try again." + RESET);
                    sleep(1000);
            }
        }

        farewell(player);
    }

    // ─────────────────────────────────────────────
    //  GAME LOGIC
    // ─────────────────────────────────────────────
    static void playGame(Player player, int totalQuestions, String mode) {
        List<Question> questions = QuestionBank.getAllQuestions();
        if (totalQuestions > questions.size()) totalQuestions = questions.size();

        int qNum = 0;
        int timeBonus = 0;

        clearScreen();
        printLine('=', 60);
        System.out.println(BOLD + MAGENTA + "  🎮 " + mode + " MODE  —  " + totalQuestions + " Questions" + RESET);
        printLine('=', 60);
        sleep(1500);

        for (int i = 0; i < totalQuestions; i++) {
            Question q = questions.get(i);
            qNum++;

            clearScreen();
            printLine('-', 60);
            System.out.printf(BOLD + CYAN + "  Question %d/%d   " + RESET +
                              YELLOW + "[%s]" + RESET +
                              GREEN + "  Score: %d" + RESET + "\n",
                              qNum, totalQuestions, q.getCategory(), player.getScore());

            if (player.getStreak() >= 2) {
                System.out.println(RED + BOLD + "  🔥 STREAK x" + player.getStreak() + "!" + RESET);
            }
            printLine('-', 60);

            System.out.println();
            System.out.println(BOLD + WHITE + "  " + q.getQuestion() + RESET);
            System.out.println();

            String[] opts = q.getOptions();
            String[] labels = {"A", "B", "C", "D"};
            String[] colors = {BLUE, MAGENTA, CYAN, YELLOW};

            for (int j = 0; j < opts.length; j++) {
                System.out.printf("  %s[%s]%s %s\n",
                    colors[j] + BOLD, labels[j], RESET, opts[j]);
            }

            System.out.println();
            System.out.println(YELLOW + "  ⏱  You have " + TIME_LIMIT_SECONDS + " seconds!" + RESET);
            printLine('-', 60);
            System.out.print(BOLD + "  Your answer (A/B/C/D): " + RESET);

            long startTime = System.currentTimeMillis();
            String input = scanner.nextLine().trim().toUpperCase();
            long elapsed = (System.currentTimeMillis() - startTime) / 1000;

            // Validate input
            while (!input.equals("A") && !input.equals("B") &&
                   !input.equals("C") && !input.equals("D")) {
                System.out.print(RED + "  Enter A, B, C, or D: " + RESET);
                input = scanner.nextLine().trim().toUpperCase();
            }

            int answerIndex = "ABCD".indexOf(input) + 1;
            boolean timedOut = elapsed > TIME_LIMIT_SECONDS;
            boolean correct  = answerIndex == q.getCorrectAnswer() && !timedOut;

            System.out.println();

            if (timedOut) {
                System.out.println(RED + BOLD + "  ⏰ TIME'S UP! Too slow!" + RESET);
                player.wrongAnswer();
            } else if (correct) {
                int bonus = (int) Math.max(0, (TIME_LIMIT_SECONDS - elapsed));
                timeBonus += bonus;
                System.out.println(GREEN + BOLD + "  ✅ CORRECT!  +" + q.getPoints() + " points" + RESET);
                if (bonus > 0)
                    System.out.println(CYAN + "  ⚡ Speed Bonus: +" + bonus + " points!" + RESET);
                player.correctAnswer(q.getPoints() + bonus);
            } else {
                System.out.println(RED + BOLD + "  ❌ WRONG!" + RESET);
                System.out.println(YELLOW + "  ✔ Correct answer: " +
                    labels[q.getCorrectAnswer() - 1] + ") " +
                    opts[q.getCorrectAnswer() - 1] + RESET);
                player.wrongAnswer();
            }

            System.out.println();
            System.out.print(WHITE + "  Press ENTER for next question..." + RESET);
            scanner.nextLine();
        }

        showRoundSummary(player, qNum, timeBonus, mode);
    }

    // ─────────────────────────────────────────────
    //  ROUND SUMMARY
    // ─────────────────────────────────────────────
    static void showRoundSummary(Player player, int total, int timeBonus, String mode) {
        clearScreen();
        printLine('*', 60);
        System.out.println(BOLD + YELLOW + "          🎯 ROUND COMPLETE — " + mode + " MODE" + RESET);
        printLine('*', 60);
        System.out.println();
        System.out.printf(BOLD + GREEN  + "  ✅ Correct Answers : %d\n"   + RESET, player.getCorrectAnswers());
        System.out.printf(BOLD + RED    + "  ❌ Wrong Answers   : %d\n"   + RESET, player.getWrongAnswers());
        System.out.printf(BOLD + CYAN   + "  🎯 Accuracy        : %.1f%%\n" + RESET, player.getAccuracy());
        System.out.printf(BOLD + YELLOW + "  🔥 Best Streak     : %d\n"   + RESET, player.getMaxStreak());
        System.out.printf(BOLD + MAGENTA+ "  ⚡ Time Bonus      : +%d pts\n" + RESET, timeBonus);
        System.out.println();
        printLine('-', 60);
        System.out.printf(BOLD + WHITE  + "  🏆 TOTAL SCORE     : %d pts\n" + RESET, player.getScore());
        System.out.printf(BOLD + YELLOW + "  🎖  YOUR RANK       : %s\n"    + RESET, player.getRank());
        printLine('-', 60);
        System.out.println();
        System.out.print(WHITE + "  Press ENTER to return to menu..." + RESET);
        scanner.nextLine();
    }

    // ─────────────────────────────────────────────
    //  LEADERBOARD
    // ─────────────────────────────────────────────
    static void showLeaderboard(Player player) {
        clearScreen();
        printLine('=', 60);
        System.out.println(BOLD + YELLOW + "        🏆  HALL OF FAME  🏆" + RESET);
        printLine('=', 60);
        System.out.println();

        String[][] board = {
            {"1", "💎 Alex Storm",    "320", "LEGEND"},
            {"2", "🔥 Priya Sharma",  "285", "LEGEND"},
            {"3", "⚡ Rohan Das",     "240", "EXPERT"},
            {"4", "🌟 " + player.getName(), String.valueOf(player.getScore()), player.getRank()},
            {"5", "🎯 Sneha Patel",   "130", "ADVANCED"},
        };

        System.out.printf(BOLD + "  %-4s %-25s %-10s %s\n" + RESET, "Rank", "Player", "Score", "Title");
        printLine('-', 60);

        for (String[] row : board) {
            boolean isPlayer = row[1].contains(player.getName());
            String color = isPlayer ? CYAN + BOLD : WHITE;
            System.out.printf(color + "  %-4s %-25s %-10s %s\n" + RESET,
                row[0], row[1], row[2] + " pts", row[3]);
        }

        System.out.println();
        printLine('=', 60);
        System.out.print(WHITE + "\n  Press ENTER to return..." + RESET);
        scanner.nextLine();
    }

    // ─────────────────────────────────────────────
    //  HELP SCREEN
    // ─────────────────────────────────────────────
    static void showHelp() {
        clearScreen();
        printLine('=', 60);
        System.out.println(BOLD + CYAN + "        📖  HOW TO PLAY" + RESET);
        printLine('=', 60);
        System.out.println();
        System.out.println(YELLOW + "  GAME MODES:" + RESET);
        System.out.println(GREEN  + "  • Standard  " + RESET + "— 10 questions, balanced difficulty");
        System.out.println(GREEN  + "  • Speed     " + RESET + "—  5 questions, race against time");
        System.out.println(GREEN  + "  • Marathon  " + RESET + "— 15 questions, test your endurance");
        System.out.println();
        System.out.println(YELLOW + "  SCORING:" + RESET);
        System.out.println(WHITE  + "  • Each question is worth 10–15 base points");
        System.out.println(WHITE  + "  • Answer faster = Time Bonus points added");
        System.out.println(WHITE  + "  • 3+ correct in a row = Streak Bonus!");
        System.out.println(WHITE  + "  • Exceed 15 seconds = answer marked wrong");
        System.out.println();
        System.out.println(YELLOW + "  RANKS (by score):" + RESET);
        System.out.println(WHITE  + "  🌱 Novice → 🥉 Beginner → 🥈 Intermediate");
        System.out.println(WHITE  + "  🥇 Advanced → 💎 Expert → 🏆 Legend (200+)");
        System.out.println();
        printLine('=', 60);
        System.out.print(WHITE + "\n  Press ENTER to return..." + RESET);
        scanner.nextLine();
    }

    // ─────────────────────────────────────────────
    //  UI HELPERS
    // ─────────────────────────────────────────────
    static void showBanner() {
        System.out.println(BOLD + CYAN);
        System.out.println("  ╔═══════════════════════════════════════════════╗");
        System.out.println("  ║                                               ║");
        System.out.println("  ║        🧠  JAVA QUIZ MASTER  🧠              ║");
        System.out.println("  ║         Test Your Knowledge & Win!            ║");
        System.out.println("  ║                                               ║");
        System.out.println("  ╚═══════════════════════════════════════════════╝");
        System.out.println(RESET);
    }

    static void showMenu(Player player) {
        showBanner();
        System.out.println(YELLOW + BOLD + "  Welcome back, " + player.getName() +
            "!  Score: " + GREEN + player.getScore() + " pts  " +
            MAGENTA + player.getRank() + RESET);
        System.out.println();
        printLine('-', 60);
        System.out.println(BOLD + WHITE + "  MAIN MENU" + RESET);
        printLine('-', 60);
        System.out.println(GREEN   + "  [1] 🎮 Standard Mode   (10 Questions)" + RESET);
        System.out.println(YELLOW  + "  [2] ⚡ Speed Mode      ( 5 Questions)" + RESET);
        System.out.println(MAGENTA + "  [3] 🏃 Marathon Mode   (15 Questions)" + RESET);
        System.out.println(CYAN    + "  [4] 🏆 Hall of Fame"                   + RESET);
        System.out.println(BLUE    + "  [5] 📖 How to Play"                    + RESET);
        System.out.println(RED     + "  [6] 🚪 Exit"                           + RESET);
        printLine('-', 60);
        System.out.print(BOLD + "  Choose an option: " + RESET);
    }

    static void farewell(Player player) {
        clearScreen();
        printLine('=', 60);
        System.out.println(BOLD + YELLOW + "  Thanks for playing, " + player.getName() + "!" + RESET);
        System.out.println(GREEN + "  Final Score : " + player.getScore() + " pts" + RESET);
        System.out.println(CYAN  + "  Final Rank  : " + player.getRank()  + RESET);
        System.out.println(WHITE + "  Accuracy    : " + String.format("%.1f", player.getAccuracy()) + "%" + RESET);
        printLine('=', 60);
        System.out.println(MAGENTA + BOLD + "\n  See you next time! 👋\n" + RESET);
    }

    static void printLine(char c, int length) {
        System.out.println("  " + String.valueOf(c).repeat(length));
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
