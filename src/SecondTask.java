import java.util.*;

public class SecondTask {
    public static void main(String[] args) {
        List<Vacancy> vacancies = new ArrayList<>();
        List<Candidate> candidates = new ArrayList<>();
        List<String> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        //число вакансий
        short n = Short.parseShort(sc.next());

        //вакансии
        for (int i = 0; i < n; i++) {
            String vac = sc.next();
            String[] arr = vac.split(",");
            Vacancy vc = new Vacancy(arr[0], Short.parseShort(arr[1]));
            vacancies.add(vc);
        }

        //число участников в отборочном соревн
        int k = sc.nextInt();

        //имя, вакансия, кол-во решенных задач, штраф
        for (int i = 0; i < k; i++) {
            String res = sc.next();
            String[] resArr = res.split(",");
            Candidate cand = new Candidate(
                    resArr[0],
                    resArr[1],
                    Integer.parseInt(resArr[2]),
                    Integer.parseInt(resArr[3]));
            candidates.add(cand);
        }
        sc.close();

        candidates.sort(Collections.reverseOrder());

        for (Vacancy v : vacancies) {
            String nameOfVacancy = v.getName();
            short countOfCandidates = v.getCountFree();
            short count = 0;
            while (count < countOfCandidates) {
                for (Candidate candidate : candidates) {
                    if ((Objects.equals(candidate.getVacancy(), nameOfVacancy)) && (count < countOfCandidates)) {
                        result.add(candidate.getName());
                        count++;
                    }
                }
            }
        }


        Collections.sort(result);
        for (String r : result) {
            System.out.println(r);
        }
    }
}

class Vacancy {
    private String name;
    private short countFree;

    public Vacancy(String name, short countFree) {
        this.name = name;
        this.countFree = countFree;
    }

    public String getName() {
        return name;
    }

    public short getCountFree() {
        return countFree;
    }

}

class Candidate implements Comparable<Candidate> {
    private String name;
    private String vacancy;
    private int countTasks;
    private int penalty;

    public Candidate(String name, String vacancy, int countTasks, int penalty) {
        this.name = name;
        this.vacancy = vacancy;
        this.countTasks = countTasks;
        this.penalty = penalty;
    }

    @Override
    public int compareTo(Candidate o) {
        int i = vacancy.compareTo(o.vacancy);
        if (i != 0) return i;

        i = Integer.compare(countTasks, o.countTasks);
        if (i != 0) return i;

        return Integer.compare(-penalty, -o.penalty);
    }

    public String getName() {
        return name;
    }

    public String getVacancy() {
        return vacancy;
    }
}

