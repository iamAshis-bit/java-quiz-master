package quizgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {

    public static List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();

        // ── JAVA CATEGORY ──
        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            2, "☕ Java", 10));

        questions.add(new Question(
            "What is the default value of an int variable in Java?",
            new String[]{"null", "1", "0", "-1"},
            3, "☕ Java", 10));

        questions.add(new Question(
            "Which of these is NOT a Java access modifier?",
            new String[]{"public", "private", "protected", "friend"},
            4, "☕ Java", 15));

        questions.add(new Question(
            "What does JVM stand for?",
            new String[]{"Java Virtual Machine", "Java Variable Method", "Java Verified Module", "Java Version Manager"},
            1, "☕ Java", 10));

        questions.add(new Question(
            "Which interface must be implemented for a class to be used in a thread?",
            new String[]{"Serializable", "Comparable", "Runnable", "Cloneable"},
            3, "☕ Java", 15));

        questions.add(new Question(
            "What is the size of an int in Java?",
            new String[]{"8 bits", "16 bits", "32 bits", "64 bits"},
            3, "☕ Java", 10));

        // ── TECHNOLOGY CATEGORY ──
        questions.add(new Question(
            "What does HTML stand for?",
            new String[]{"Hyper Text Markup Language", "High Tech Modern Language", "Hyper Transfer Markup Logic", "Home Tool Markup Language"},
            1, "💻 Technology", 10));

        questions.add(new Question(
            "Which company created the Java programming language?",
            new String[]{"Microsoft", "Apple", "Sun Microsystems", "Google"},
            3, "💻 Technology", 10));

        questions.add(new Question(
            "What does CPU stand for?",
            new String[]{"Central Processing Unit", "Computer Personal Unit", "Central Program Utility", "Core Processing Unit"},
            1, "💻 Technology", 10));

        questions.add(new Question(
            "What does API stand for?",
            new String[]{"Application Programming Interface", "Automated Program Integration", "Application Process Input", "Advanced Programming Interface"},
            1, "💻 Technology", 10));

        questions.add(new Question(
            "Which data structure works on LIFO principle?",
            new String[]{"Queue", "Stack", "Array", "Tree"},
            2, "💻 Technology", 15));

        questions.add(new Question(
            "What is Git primarily used for?",
            new String[]{"Database management", "Version control", "Server hosting", "UI design"},
            2, "💻 Technology", 10));

        // ── SCIENCE CATEGORY ──
        questions.add(new Question(
            "What is the chemical symbol for Gold?",
            new String[]{"Go", "Gd", "Au", "Ag"},
            3, "🔬 Science", 10));

        questions.add(new Question(
            "How many planets are in our Solar System?",
            new String[]{"7", "8", "9", "10"},
            2, "🔬 Science", 10));

        questions.add(new Question(
            "What is the speed of light approximately?",
            new String[]{"3 x 10^6 m/s", "3 x 10^8 m/s", "3 x 10^10 m/s", "3 x 10^4 m/s"},
            2, "🔬 Science", 15));

        questions.add(new Question(
            "What gas do plants absorb from the atmosphere?",
            new String[]{"Oxygen", "Nitrogen", "Carbon Dioxide", "Hydrogen"},
            3, "🔬 Science", 10));

        questions.add(new Question(
            "What is the powerhouse of the cell?",
            new String[]{"Nucleus", "Ribosome", "Mitochondria", "Vacuole"},
            3, "🔬 Science", 10));

        // ── GENERAL KNOWLEDGE ──
        questions.add(new Question(
            "Which is the largest continent on Earth?",
            new String[]{"Africa", "Europe", "Asia", "North America"},
            3, "🌍 General Knowledge", 10));

        questions.add(new Question(
            "Who painted the Mona Lisa?",
            new String[]{"Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Michelangelo"},
            3, "🌍 General Knowledge", 10));

        questions.add(new Question(
            "What is the capital of Japan?",
            new String[]{"Beijing", "Seoul", "Tokyo", "Bangkok"},
            3, "🌍 General Knowledge", 10));

        questions.add(new Question(
            "How many sides does a hexagon have?",
            new String[]{"5", "6", "7", "8"},
            2, "🌍 General Knowledge", 10));

        questions.add(new Question(
            "What is the longest river in the world?",
            new String[]{"Amazon", "Nile", "Yangtze", "Mississippi"},
            2, "🌍 General Knowledge", 10));

        Collections.shuffle(questions);
        return questions;
    }
}
