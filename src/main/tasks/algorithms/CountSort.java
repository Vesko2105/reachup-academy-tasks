package tasks.algorithms;

import java.util.*;
import java.util.stream.Stream;

class CompetitionScore {
    private String name;
    private int score;

    public CompetitionScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

class CountSort {
    static void countSortWithTreeMap(List<CompetitionScore> scores) {
        Map<Integer, List<String>> sortedMap = new TreeMap<>();
        scores.forEach(score -> {
            if (sortedMap.containsKey(score.getScore())) {
                sortedMap.get(score.getScore()).add(score.getName());
            } else {
                List<String> newList = new LinkedList<>();
                newList.add(score.getName());
                sortedMap.put(score.getScore(), newList);
            }
        });
        sortedMap.forEach((score, names) -> {
            names.forEach(name -> System.out.printf("%d - %s%n ", score, name));
        });
    }

    static void countSortWithLists(List<CompetitionScore> scores) {
        List<ArrayList<String>> sortedScores = Stream.generate(ArrayList<String>::new).limit(100).toList();
        scores.forEach(score -> {
            sortedScores.get(score.getScore() - 1).add(score.getName());
        });
        for (int score = 0; score < 100; score++) {
            int currentScore = score;
            sortedScores.get(score).forEach(name -> System.out.printf("Name: %12s | Score: %3d%n", name, currentScore + 1));
        }
    }

    public static void main(String[] args) {
        List<CompetitionScore> scores = List.of(
                new CompetitionScore("Brianna", 31),
                new CompetitionScore("Hayden", 7),
                new CompetitionScore("Kate", 4),
                new CompetitionScore("Luise", 25),
                new CompetitionScore("Arley", 53),
                new CompetitionScore("Gael", 81),
                new CompetitionScore("Norrie", 17),
                new CompetitionScore("Thedrick", 48),
                new CompetitionScore("Adara", 90),
                new CompetitionScore("Lynette", 86),
                new CompetitionScore("Carly", 69),
                new CompetitionScore("Miltie", 99),
                new CompetitionScore("Man", 63),
                new CompetitionScore("Stace", 38),
                new CompetitionScore("Toddie", 10),
                new CompetitionScore("Mallory", 12),
                new CompetitionScore("Olin", 4),
                new CompetitionScore("Cyril", 64),
                new CompetitionScore("Clemmie", 85),
                new CompetitionScore("Morrie", 44),
                new CompetitionScore("Abigale", 51),
                new CompetitionScore("Monroe", 32),
                new CompetitionScore("Olivette", 49),
                new CompetitionScore("Erroll", 14),
                new CompetitionScore("Papagena", 14),
                new CompetitionScore("Karlan", 85),
                new CompetitionScore("Iver", 51),
                new CompetitionScore("Cammie", 76),
                new CompetitionScore("Sidonia", 69),
                new CompetitionScore("Brennen", 100),
                new CompetitionScore("Lorry", 86),
                new CompetitionScore("Jody", 66),
                new CompetitionScore("Aldin", 42),
                new CompetitionScore("Reeta", 5),
                new CompetitionScore("Adair", 11),
                new CompetitionScore("Sabine", 9),
                new CompetitionScore("Magdalene", 11),
                new CompetitionScore("Carlie", 41),
                new CompetitionScore("Gerri", 38),
                new CompetitionScore("Tomlin", 39),
                new CompetitionScore("Aguistin", 96),
                new CompetitionScore("Webb", 74),
                new CompetitionScore("Bonita", 48),
                new CompetitionScore("Elisha", 65),
                new CompetitionScore("Verna", 99),
                new CompetitionScore("Anabella", 89),
                new CompetitionScore("Hans", 40),
                new CompetitionScore("Nancie", 16),
                new CompetitionScore("Lucias", 75),
                new CompetitionScore("Urbano", 3),
                new CompetitionScore("Allen", 90),
                new CompetitionScore("Cecelia", 71),
                new CompetitionScore("Kathryn", 30),
                new CompetitionScore("Jackqueline", 97),
                new CompetitionScore("Kelcie", 62),
                new CompetitionScore("Brockie", 35),
                new CompetitionScore("Sande", 81),
                new CompetitionScore("Trevor", 31),
                new CompetitionScore("Ogden", 100),
                new CompetitionScore("Tedra", 34),
                new CompetitionScore("Bartholomeo", 9),
                new CompetitionScore("Mile", 53),
                new CompetitionScore("Corbie", 21),
                new CompetitionScore("Mead", 4),
                new CompetitionScore("Nessi", 71),
                new CompetitionScore("Delcina", 4),
                new CompetitionScore("Nathalia", 60),
                new CompetitionScore("Amos", 29),
                new CompetitionScore("Olga", 100),
                new CompetitionScore("Manya", 26),
                new CompetitionScore("Leoine", 50),
                new CompetitionScore("Leanora", 91),
                new CompetitionScore("Sutton", 80),
                new CompetitionScore("Alf", 14),
                new CompetitionScore("Rubin", 32),
                new CompetitionScore("Adelind", 74),
                new CompetitionScore("Gretchen", 10),
                new CompetitionScore("Josias", 59),
                new CompetitionScore("Carlie", 70)
        );
        countSortWithLists(scores);
    }
}