import java.util.*;

//clasa de baza User

abstract class User {
    protected String id;
    protected String name;
    protected String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class Student extends User {
    private int gradeLevel;

    public Student(String id, String name, String email, int gradeLevel) {
        super(id, name, email);
        this.gradeLevel = gradeLevel;
    }

    public int getGradeLevel() { return gradeLevel; }
}

class Teacher extends User {
    private String specialization;

    public Teacher(String id, String name, String email, String specialization) {
        super(id, name, email);
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }
}


//concurs
class Contest {
    private String contestId;
    private String name;
    private TreeMap<Integer, List<Student>> scoreboard;

    public Contest(String contestId, String name) {
        this.contestId = contestId;
        this.name = name;
        this.scoreboard = new TreeMap<>(Collections.reverseOrder());
    }

    public String getContestId() { return contestId; }
    public String getName() { return name; }

    public void registerStudent(Student student, int score) {
        scoreboard.computeIfAbsent(score, k -> new ArrayList<>()).add(student);
    }

    public void printScoreboard() {
        System.out.println("\nClasament pentru concursul " + name + " (" + contestId + "):");
        System.out.printf("%-10s %-10s %-20s %-10s\n", "Poziție", "Scor", "Nume Student", "ID Student");
        System.out.println("--------------------------------------------------");

        int rank = 1;
        for (Map.Entry<Integer, List<Student>> entry : scoreboard.entrySet()) {
            int score = entry.getKey();
            for (Student student : entry.getValue()) {
                // Coloane aliniate (Poziție: 10 caractere, Scor: 10, Nume: 20, ID: 10)
                System.out.printf("%-10d %-10d %-20s %-10s\n",
                        rank, score, student.getName(), student.getId());
            }
            rank += entry.getValue().size();
        }
    }
}

//curs
class Course {
    private String courseId;
    private String name;
    private Teacher instructor;
    private List<Student> enrolledStudents;
    private Set<String> materials;
    private Map<Student, Integer> grades;

    public Course(String courseId, String name, Teacher instructor) {
        this.courseId = courseId;
        this.name = name;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
        this.materials = new TreeSet<>();
        this.grades = new HashMap<>();
    }

    public String getCourseId() { return courseId; }
    public String getName() { return name; }
    public Teacher getInstructor() { return instructor; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }
    public Set<String> getMaterials() { return materials; }
    public Map<Student, Integer> getGrades() { return grades; }
}

class Sponsor {
    private String companyName;
    private String phoneNumber;
    private String email;

    public Sponsor(String companyName, String phoneNumber, String email) {
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getCompanyName() { return companyName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return companyName + " | Tel: " + phoneNumber + " | Email: " + email;
    }
}

class Bursa {
    private String studentId;
    private String sponsorId;
    private double amount;

    public Bursa(String studentId, String sponsorId, double amount) {
        this.studentId = studentId;
        this.sponsorId = sponsorId;
        this.amount = amount;
    }

    public String getStudentId() { return studentId; }
    public String getSponsorId() { return sponsorId; }
    public double getAmount() { return amount; }
}

//echipa de studenti; poate avea oricati
class Echipa {
    private String nume;
    private List<String> membriIds; // List of student IDs in the team

    public Echipa(String nume) {
        this.nume = nume;
        this.membriIds = new ArrayList<>();
    }

    public String getNume() { return nume; }
    public List<String> getMembriIds() { return membriIds; }
    public int getNumarMembri() { return membriIds.size(); }

    public void adaugaMembru(String studentId) {
        membriIds.add(studentId);
    }

    public boolean eliminaMembru(String studentId) {
        return membriIds.remove(studentId);
    }

    @Override
    public String toString() {
        return nume + " (" + getNumarMembri() + " membri)";
    }
}

class Recenzie {
    private String idRecenzie;
    private String studentId;
    private String cursId;
    private String comentariu;
    private int rating; // 1-5

    public Recenzie(String idRecenzie, String studentId, String cursId, String comentariu, int rating) {
        this.idRecenzie = idRecenzie;
        this.studentId = studentId;
        this.cursId = cursId;
        this.comentariu = comentariu;
        this.rating = rating;
    }

    public String getIdRecenzie() { return idRecenzie; }
    public String getStudentId() { return studentId; }
    public String getCursId() { return cursId; }
    public String getComentariu() { return comentariu; }
    public int getRating() { return rating; }
}

class Service { //singleton
    private static Service instance;

    private Map<String, Recenzie> recenzii = new HashMap<>();
    private int recenzieCounter = 1;
    private Map<String, Echipa> echipe = new HashMap<>();
    private int echipaCounter = 1;
    private Map<String, Sponsor> sponsors = new HashMap<>();
    private List<Bursa> burse = new ArrayList<>();
    private int sponsorCounter = 1;
    private int bursaCounter = 1;
    private List<Course> courses;
    private Map<String, User> users;
    private Map<String, Contest> contests;
    private int teacherCounter;
    private int studentCounter;
    private int courseCounter;
    private int contestCounter;

    private Service() {
        this.courses = new ArrayList<>();
        this.users = new HashMap<>();
        this.contests = new HashMap<>();
        this.teacherCounter = 1;
        this.studentCounter = 1;
        this.courseCounter = 1;
        this.contestCounter = 1;
        System.out.println("Service singleton inițializat.");
    }
    public static synchronized Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public void createContest(String name) {
        String contestId = "CT" + contestCounter++;
        contests.put(contestId, new Contest(contestId, name));
        System.out.println("Concurs creat cu ID-ul: " + contestId);
    }

    public void registerStudentToContest(String studentId, String contestId, int score) {
        Student student = (Student) users.get(studentId);
        Contest contest = contests.get(contestId);

        if (student == null || contest == null) {
            System.out.println("Student sau concurs invalid!");
            return;
        }

        contest.registerStudent(student, score);
        System.out.println("Studentul " + student.getName() + " a fost înscris la concursul " + contest.getName() + " cu scorul " + score);
    }

    public void printContestScoreboard(String contestId) {
        Contest contest = contests.get(contestId);
        if (contest == null) {
            System.out.println("Concurs invalid!");
            return;
        }
        contest.printScoreboard();
    }

    public Map<String, Contest> getContests() {
        return contests;
    }

    public void registerTeacher(String name, String email, String specialization) {
        String teacherId = "T" + teacherCounter++;
        users.put(teacherId, new Teacher(teacherId, name, email, specialization));
        System.out.println("Profesor înregistrat cu ID-ul: " + teacherId);
    }

    public void registerStudent(String name, String email, int gradeLevel) {
        String studentId = "S" + studentCounter++;
        users.put(studentId, new Student(studentId, name, email, gradeLevel));
        System.out.println("Student înregistrat cu ID-ul: " + studentId);
    }

    public void createCourse(String name, Teacher teacher) {
        if (teacher == null) {
            System.out.println("Eroare: Nu există profesori disponibili!");
            return;
        }
        String courseId = "C" + courseCounter++;
        courses.add(new Course(courseId, name, teacher));
        System.out.println("Curs creat cu ID-ul: " + courseId);
    }

    public void enrollStudent(Student student, Course course) {
        if (student == null || course == null) {
            System.out.println("Student sau curs invalid!");
            return;
        }
        course.getEnrolledStudents().add(student);
        System.out.println("Studentul " + student.getName() + " a fost înscris la " + course.getName());
    }

    public void addMaterial(Course course, String material) {
        if (course == null) {
            System.out.println("Curs invalid!");
            return;
        }
        course.getMaterials().add(material);
        System.out.println("Material adăugat la " + course.getName());
    }

    public void recordGrade(Student student, Course course, int grade) {
        if (student == null || course == null) {
            System.out.println("Student sau curs invalid!");
            return;
        }
        course.getGrades().put(student, grade);
        System.out.println("Nota " + grade + " a fost înregistrată pentru " + student.getName());
    }

    public double calculateAverageGrade(Course course) {
        if (course == null || course.getGrades().isEmpty()) {
            return 0.0;
        }
        return course.getGrades().values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public Map<Course, Integer> getStudentGrades(Student student) {
        Map<Course, Integer> studentGrades = new HashMap<>();
        for (Course course : courses) {
            if (course.getGrades().containsKey(student)) {
                studentGrades.put(course, course.getGrades().get(student));
            }
        }
        return studentGrades;
    }

    public List<Course> getTeacherCourses(Teacher teacher) {
        List<Course> teacherCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.getInstructor().equals(teacher)) {
                teacherCourses.add(course);
            }
        }
        return teacherCourses;
    }

    public Map<Student, Integer> getCourseGrades(Course course) {
        return new HashMap<>(course.getGrades());
    }

    public List<Course> getCourses() { return courses; }
    public Map<String, User> getUsers() { return users; }
    public User getUser(String id) { return users.get(id); }

    public Course getCourse(String id) {
        return courses.stream()
                .filter(c -> c.getCourseId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void registerSponsor(String companyName, String phoneNumber, String email) {
        String sponsorId = "SP" + sponsorCounter++;
        sponsors.put(sponsorId, new Sponsor(companyName, phoneNumber, email));
        System.out.println("Sponsor înregistrat cu ID-ul: " + sponsorId);
    }

    public void createBursa(String studentId, String sponsorId, double amount) {
        if (!users.containsKey(studentId)) {
            System.out.println("Studentul nu există!");
            return;
        }
        if (!sponsors.containsKey(sponsorId)) {
            System.out.println("Sponsorul nu există!");
            return;
        }

        burse.add(new Bursa(studentId, sponsorId, amount));
        System.out.println("Bursă creată pentru studentul " + studentId +
                " de la sponsorul " + sponsorId +
                " în valoare de " + amount + "RON");
    }

    public double getTotalBursaForStudent(String studentId) {
        return burse.stream()
                .filter(b -> b.getStudentId().equals(studentId))
                .mapToDouble(Bursa::getAmount)
                .sum();
    }

    public double getTotalBursaForSponsor(String sponsorId) {
        return burse.stream()
                .filter(b -> b.getSponsorId().equals(sponsorId))
                .mapToDouble(Bursa::getAmount)
                .sum();
    }

    public Map<String, Sponsor> getSponsors() { return sponsors; }
    public List<Bursa> getBurse() { return burse; }

    public void adaugaEchipa(String nume) {
        String echipaId = "E" + echipaCounter++;
        echipe.put(echipaId, new Echipa(nume));
        System.out.println("Echipă creată cu ID-ul: " + echipaId);
    }

    public void adaugaMembruInEchipa(String echipaId, String studentId) {
        if (!echipe.containsKey(echipaId)) {
            System.out.println("Echipă invalidă!");
            return;
        }
        if (!users.containsKey(studentId) || !(users.get(studentId) instanceof Student)) {
            System.out.println("Student invalid!");
            return;
        }

        echipe.get(echipaId).adaugaMembru(studentId);
        System.out.println("Studentul " + users.get(studentId).getName() +
                " a fost adăugat în echipa " + echipe.get(echipaId).getNume());
    }

    public void afiseazaToateEchipele() {
        System.out.println("\nToate echipele (sortate după număr de membri):");
        echipe.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().getNumarMembri(),
                        e1.getValue().getNumarMembri()))
                .forEach(entry -> {
                    Echipa e = entry.getValue();
                    System.out.printf("%s: %s - %d membri%n",
                            entry.getKey(), e.getNume(), e.getNumarMembri());
                });
    }

    public void afiseazaEchipeleStudentului(String studentId) {
        if (!users.containsKey(studentId)) {
            System.out.println("Student invalid!");
            return;
        }

        System.out.println("\nEchipele studentului " + users.get(studentId).getName() + ":");
        echipe.entrySet().stream()
                .filter(entry -> entry.getValue().getMembriIds().contains(studentId))
                .forEach(entry -> {
                    Echipa e = entry.getValue();
                    System.out.printf("%s: %s (%d membri)%n",
                            entry.getKey(), e.getNume(), e.getNumarMembri());
                });
    }



    public void afiseazaEchipa(String echipaId) {
        if (!echipe.containsKey(echipaId)) {
            System.out.println("Echipă invalidă!");
            return;
        }

        Echipa e = echipe.get(echipaId);
        System.out.println("\nDetalii echipă " + e.getNume() + ":");
        System.out.println("ID echipă: " + echipaId);
        System.out.println("Număr membri: " + e.getNumarMembri());
        System.out.println("Membri:");

        e.getMembriIds().forEach(studentId -> {
            User student = users.get(studentId);
            if (student != null) {
                System.out.printf("- %s: %s (An %d)%n",
                        studentId, student.getName(),
                        ((Student)student).getGradeLevel());
            }
        });
    }

    public Map<String, Echipa> getEchipe() { return echipe; }

    public void eliminaStudentDinEchipa(String echipaId, String studentId) {
        if (!echipe.containsKey(echipaId)) {
            System.out.println("Echipă invalidă!");
            return;
        }
        if (!users.containsKey(studentId) || !(users.get(studentId) instanceof Student)) {
            System.out.println("Student invalid!");
            return;
        }

        Echipa echipa = echipe.get(echipaId);
        if (echipa.eliminaMembru(studentId)) {
            System.out.println("Studentul " + users.get(studentId).getName() +
                    " a fost eliminat din echipa " + echipa.getNume());
        } else {
            System.out.println("Studentul nu era în această echipă!");
        }
    }

    public void inregistreazaRecenzie(String studentId, String cursId, String comentariu, int rating) {
        if (!users.containsKey(studentId) || !(users.get(studentId) instanceof Student)) {
            System.out.println("Student invalid!");
            return;
        }
        if (getCourse(cursId) == null) {
            System.out.println("Curs invalid!");
            return;
        }
        if (rating < 1 || rating > 5) {
            System.out.println("Rating trebuie să fie între 1 și 5!");
            return;
        }

        String idRecenzie = "R" + recenzieCounter++;
        recenzii.put(idRecenzie, new Recenzie(idRecenzie, studentId, cursId, comentariu, rating));
        System.out.println("Recenzie înregistrată cu ID-ul: " + idRecenzie);
    }

    public void afiseazaRecenziiCurs(String cursId) {
        System.out.println("\nRecenzii pentru cursul " + getCourse(cursId).getName() + ":");
        recenzii.values().stream()
                .filter(r -> r.getCursId().equals(cursId))
                .forEach(r -> {
                    User student = users.get(r.getStudentId());
                    System.out.printf("- %s: %s (Rating: %d/5)\n  Comentariu: \"%s\"\n",
                            r.getStudentId(), student.getName(), r.getRating(), r.getComentariu());
                });
    }

    public double calculeazaRatingCurs(String cursId) {
        return recenzii.values().stream()
                .filter(r -> r.getCursId().equals(cursId))
                .mapToInt(Recenzie::getRating)
                .average()
                .orElse(0.0);
    }

    public void afiseazaRecenziiStudent(String studentId) {
        if (!users.containsKey(studentId)) {
            System.out.println("Student invalid!");
            return;
        }

        System.out.println("\nRecenzii lăsate de " + users.get(studentId).getName() + ":");
        recenzii.values().stream()
                .filter(r -> r.getStudentId().equals(studentId))
                .forEach(r -> {
                    Course curs = getCourse(r.getCursId());
                    System.out.printf("- Curs: %s (Rating: %d/5)\n  Comentariu: \"%s\"\n",
                            curs.getName(), r.getRating(), r.getComentariu());
                });
    }
}

public class Main {
    public static void main(String[] args) {
        Service service = Service.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MENIU PRINCIPAL ===");
            System.out.println("1. Adaugă profesor");
            System.out.println("2. Adaugă student");
            System.out.println("3. Adaugă curs nou");
            System.out.println("4. Înscrie student la curs");
            System.out.println("5. Adaugă material didactic");
            System.out.println("6. Notează student");
            System.out.println("7. Calculează medie curs");
            System.out.println("8. Afișează toți profesorii");
            System.out.println("9. Afișează toți studenții");
            System.out.println("10. Afișează toate cursurile");
            System.out.println("11. Afișează studenții înscriși la curs");
            System.out.println("12. Afișează materialele unui curs");
            System.out.println("13. Vezi notele unui student la toate cursurile");
            System.out.println("14. Vezi toate cursurile unui profesor");
            System.out.println("15. Vezi notele tuturor studenților la un curs");
            System.out.println("16. Creează un concurs nou");
            System.out.println("17. Înregistrează scorul unui student la un concurs");
            System.out.println("18. Vezi clasamentul unui concurs");
            System.out.println("19. Adaugă sponsor");
            System.out.println("20. Înregistrează o nouă bursă");
            System.out.println("21. Afișează suma totală primită de un student prin burse");
            System.out.println("22. Afișează suma totală oferită de un sponsor prin burse");
            System.out.println("23. Adaugă o echipă");
            System.out.println("24. Adaugă student în echipă");
            System.out.println("25. Afișează toate echipele");
            System.out.println("26. Afișează echipele unui student");
            System.out.println("27. Afișează detalii echipă");
            System.out.println("28. Elimină un student din echipă");
            System.out.println("29. Înregistrează o recenzie pentru un curs");
            System.out.println("30. Afișează toate recenziile unui curs");
            System.out.println("31. Afișează ratingul unui curs");
            System.out.println("32. Afișează toate recenziile unui student");
            System.out.println("0. Ieșire");
            System.out.print("Alege o opțiune: ");

            int optiune;
            try {
                optiune = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Introdu un număr valid!");
                scanner.nextLine();
                continue;
            }

            switch (optiune) {
                case 1:
                    System.out.print("Nume profesor: ");
                    String teacherName = scanner.nextLine();
                    System.out.print("Email profesor: ");
                    String teacherEmail = scanner.nextLine();
                    System.out.print("Specializare: ");
                    String specialization = scanner.nextLine();
                    service.registerTeacher(teacherName, teacherEmail, specialization);
                    break;

                case 2:
                    System.out.print("Nume student: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Email student: ");
                    String studentEmail = scanner.nextLine();
                    System.out.print("An studiu: ");
                    try {
                        int gradeLevel = scanner.nextInt();
                        scanner.nextLine();
                        service.registerStudent(studentName, studentEmail, gradeLevel);
                    } catch (InputMismatchException e) {
                        System.out.println("Introdu un număr valid pentru anul de studiu!");
                        scanner.nextLine();
                    }
                    break;

                case 3:
                    System.out.println("Profesori disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Teacher)
                            .forEach(t -> System.out.println(t.getId() + ": " + t.getName()));

                    System.out.print("ID profesor: ");
                    String teacherId = scanner.nextLine();
                    Teacher teacher = (Teacher) service.getUser(teacherId);

                    if (teacher != null) {
                        System.out.print("Nume curs: ");
                        String courseName = scanner.nextLine();
                        service.createCourse(courseName, teacher);
                    } else {
                        System.out.println("ID profesor invalid!");
                    }
                    break;

                case 4:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String studentId = scanner.nextLine();

                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseId = scanner.nextLine();

                    Student student = (Student) service.getUser(studentId);
                    Course course = service.getCourse(courseId);

                    if (student != null && course != null) {
                        service.enrollStudent(student, course);
                    } else {
                        System.out.println("Student sau curs invalid!");
                    }
                    break;

                case 5:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseIdMat = scanner.nextLine();
                    System.out.print("Nume material: ");
                    String material = scanner.nextLine();

                    Course courseMat = service.getCourse(courseIdMat);
                    if (courseMat != null) {
                        service.addMaterial(courseMat, material);
                    } else {
                        System.out.println("Curs invalid!");
                    }
                    break;

                case 6:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String studentIdGrade = scanner.nextLine();

                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseIdGrade = scanner.nextLine();
                    System.out.print("Nota: ");
                    try {
                        int grade = scanner.nextInt();
                        scanner.nextLine();

                        Student studentGrade = (Student) service.getUser(studentIdGrade);
                        Course courseGrade = service.getCourse(courseIdGrade);

                        if (studentGrade != null && courseGrade != null) {
                            service.recordGrade(studentGrade, courseGrade, grade);
                        } else {
                            System.out.println("Student sau curs invalid!");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Introdu o notă validă!");
                        scanner.nextLine();
                    }
                    break;

                case 7:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseIdAvg = scanner.nextLine();
                    Course courseAvg = service.getCourse(courseIdAvg);

                    if (courseAvg != null) {
                        double average = service.calculateAverageGrade(courseAvg);
                        System.out.println("Media cursului " + courseAvg.getName() + ": " + average);
                    } else {
                        System.out.println("Curs invalid!");
                    }
                    break;

                case 8:
                    System.out.println("\nProfesori:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Teacher)
                            .forEach(t -> System.out.println(t.getId() + ": " + t.getName() +
                                    " | Specializare: " + ((Teacher) t).getSpecialization() +
                                    " | Email: " + t.getEmail()));
                    break;

                case 9:
                    System.out.println("\nStudenți:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName() +
                                    " | An studiu: " + ((Student) s).getGradeLevel() +
                                    " | Email: " + s.getEmail()));
                    break;

                case 10:
                    System.out.println("\nCursuri:");
                    service.getCourses().forEach(c -> {
                        double rating = service.calculeazaRatingCurs(c.getCourseId());
                        System.out.println(c.getCourseId() + ": " + c.getName() +
                                " | Profesor: " + c.getInstructor().getName() +
                                " | Studenți înscriși: " + c.getEnrolledStudents().size() +
                                " | Rating: " + (rating > 0 ? String.format("%.1f/5", rating) : "N/A"));
                    });
                    break;

                case 11:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseIdEnrolled = scanner.nextLine();
                    Course courseEnrolled = service.getCourse(courseIdEnrolled);

                    if (courseEnrolled != null) {
                        System.out.println("\nStudenți înscriși la " + courseEnrolled.getName() + ":");
                        courseEnrolled.getEnrolledStudents().forEach(s ->
                                System.out.println(s.getId() + ": " + s.getName()));
                    } else {
                        System.out.println("Curs invalid!");
                    }
                    break;

                case 12:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseIdMaterials = scanner.nextLine();
                    Course courseMaterials = service.getCourse(courseIdMaterials);

                    if (courseMaterials != null) {
                        System.out.println("\nMateriale pentru " + courseMaterials.getName() + ":");
                        courseMaterials.getMaterials().forEach(System.out::println);
                    } else {
                        System.out.println("Curs invalid!");
                    }
                    break;

                case 13:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String studentIdGrades = scanner.nextLine();
                    Student studentGrades = (Student) service.getUser(studentIdGrades);

                    if (studentGrades != null) {
                        Map<Course, Integer> grades = service.getStudentGrades(studentGrades);
                        if (grades.isEmpty()) {
                            System.out.println("Studentul/a nu are note înregistrate!");
                        } else {
                            System.out.println("\nNotele studentului/ei " + studentGrades.getName() + ":");
                            grades.forEach((co, grade) ->
                                    System.out.println(co.getName() + ": " + grade));
                            double avg = grades.values().stream()
                                    .mapToInt(Integer::intValue)
                                    .average()
                                    .orElse(0.0);
                            System.out.println("Media generală: " + avg);
                        }
                    } else {
                        System.out.println("Student/ă invalid!");
                    }
                    break;

                case 14:
                    System.out.println("Profesori disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Teacher)
                            .forEach(t -> System.out.println(t.getId() + ": " + t.getName()));

                    System.out.print("ID profesor: ");
                    String teacherIdCourses = scanner.nextLine();
                    Teacher teacherCourses = (Teacher) service.getUser(teacherIdCourses);

                    if (teacherCourses != null) {
                        List<Course> courses = service.getTeacherCourses(teacherCourses);
                        if (courses.isEmpty()) {
                            System.out.println("Profesorul nu predă la niciun curs!");
                        } else {
                            System.out.println("\nCursurile predate de " + teacherCourses.getName() + ":");
                            courses.forEach(c ->
                                    System.out.println(c.getCourseId() + ": " + c.getName() +
                                            " | Studenți înscriși: " + c.getEnrolledStudents().size()));
                        }
                    } else {
                        System.out.println("Profesor invalid!");
                    }
                    break;

                case 15:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("ID curs: ");
                    String courseIdAllGrades = scanner.nextLine();
                    Course courseAllGrades = service.getCourse(courseIdAllGrades);

                    if (courseAllGrades != null) {
                        Map<Student, Integer> grades = service.getCourseGrades(courseAllGrades);
                        if (grades.isEmpty()) {
                            System.out.println("Nu există note înregistrate pentru acest curs!");
                        } else {
                            System.out.println("\nNotele la cursul " + courseAllGrades.getName() + ":");
                            grades.forEach((st, grade) ->
                                    System.out.println(st.getName() + ": " + grade));
                            System.out.println("Media cursului: " +
                                    service.calculateAverageGrade(courseAllGrades));
                        }
                    } else {
                        System.out.println("Curs invalid!");
                    }
                    break;
                case 16:
                    System.out.print("Nume concurs: ");
                    String contestName = scanner.nextLine();
                    service.createContest(contestName);
                    break;

                case 17:
                    System.out.println("Concursuri disponibile:");
                    service.getContests().values().forEach(c ->
                            System.out.println(c.getContestId() + ": " + c.getName()));

                    System.out.println("Studenţi disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String contestStudentId = scanner.nextLine();
                    System.out.print("ID concurs: ");
                    String registerContestId = scanner.nextLine();
                    System.out.print("Scorul obţinut: ");
                    try {
                        int contestScore = scanner.nextInt();
                        scanner.nextLine();
                        service.registerStudentToContest(contestStudentId, registerContestId, contestScore);
                    } catch (InputMismatchException e) {
                        System.out.println("Introduceţi un scor valid!");
                        scanner.nextLine();
                    }
                    break;

                case 18:
                    System.out.println("Concursuri disponibile:");
                    service.getContests().values().forEach(c ->
                            System.out.println(c.getContestId() + ": " + c.getName()));
                    System.out.print("ID concurs: ");
                    String scoreboardContestId = scanner.nextLine();
                    service.printContestScoreboard(scoreboardContestId);
                    break;

                case 19:
                    System.out.print("Nume companie sponsor: ");
                    String companyName = scanner.nextLine();
                    System.out.print("Număr de telefon: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Email sponsor: ");
                    String email = scanner.nextLine();
                    service.registerSponsor(companyName, phoneNumber, email);
                    break;

                case 20:
                    System.out.println("\nStudenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("\nIntrodu ID student: ");
                    String bursaStudentId = scanner.nextLine();

                    System.out.println("\nSponsori disponibili:");
                    service.getSponsors().forEach((id, sponsor) ->
                            System.out.println(id + ": " + sponsor.getCompanyName()));

                    System.out.print("\nIntrodu ID sponsor: ");
                    String bursaSponsorId = scanner.nextLine();

                    System.out.print("\nIntrodu suma bursei (lei): ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    service.createBursa(bursaStudentId, bursaSponsorId, amount);
                    break;

                case 21:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String totalStudentId = scanner.nextLine();
                    double total = service.getTotalBursaForStudent(totalStudentId);
                    System.out.println("Suma totală primită de student: " + total + " RON");
                    break;

                case 22:
                    System.out.println("Sponsori disponibili:");
                    service.getSponsors().forEach((id, sponsor) ->
                            System.out.println(id + ": " + sponsor.getCompanyName()));

                    System.out.print("ID sponsor: ");
                    String totalSponsorId = scanner.nextLine();
                    double sponsorTotal = service.getTotalBursaForSponsor(totalSponsorId);
                    System.out.println("Suma totală oferită de sponsor: " + sponsorTotal + " RON");
                    break;

                case 23:
                    System.out.print("Nume echipă: ");
                    String numeEchipa = scanner.nextLine();
                    service.adaugaEchipa(numeEchipa);
                    break;

                case 24:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String studentIdEchipa = scanner.nextLine();

                    System.out.println("Echipe disponibile:");
                    service.getEchipe().forEach((id, echipa) ->
                            System.out.println(id + ": " + echipa.getNume()));

                    System.out.print("ID echipă: ");
                    String echipaId = scanner.nextLine();

                    service.adaugaMembruInEchipa(echipaId, studentIdEchipa);
                    break;

                case 25:
                    service.afiseazaToateEchipele();
                    break;

                case 26:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student: ");
                    String studentIdCautat = scanner.nextLine();
                    service.afiseazaEchipeleStudentului(studentIdCautat);
                    break;

                case 27:
                    System.out.println("Echipe disponibile:");
                    service.getEchipe().forEach((id, echipa) ->
                            System.out.println(id + ": " + echipa.getNume()));

                    System.out.print("ID echipă: ");
                    String echipaIdAfisare = scanner.nextLine();
                    service.afiseazaEchipa(echipaIdAfisare);
                    break;

                case 28:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("ID student de eliminat: ");
                    String studentIdEliminare = scanner.nextLine();

                    System.out.println("Echipe disponibile:");
                    service.getEchipe().forEach((id, echipa) ->
                            System.out.println(id + ": " + echipa.getNume()));

                    System.out.print("ID echipă: ");
                    String echipaIdEliminare = scanner.nextLine();

                    service.eliminaStudentDinEchipa(echipaIdEliminare, studentIdEliminare);
                    break;

                case 29:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("Introdu ID student: ");
                    String recenzieStudentId = scanner.nextLine();

                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("Introdu ID curs: ");
                    String recenzieCursId = scanner.nextLine();

                    System.out.print("Comentariu (max 200 caractere): ");
                    String comentariu = scanner.nextLine();

                    System.out.print("Rating (1-5): ");
                    try {
                        int rating = scanner.nextInt();
                        scanner.nextLine();
                        service.inregistreazaRecenzie(recenzieStudentId, recenzieCursId, comentariu, rating);
                    } catch (InputMismatchException e) {
                        System.out.println("Rating trebuie să fie un număr între 1 și 5!");
                        scanner.nextLine();
                    }
                    break;

                case 30:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("Introdu ID curs: ");
                    String cursPentruRecenzii = scanner.nextLine();
                    service.afiseazaRecenziiCurs(cursPentruRecenzii);
                    break;

                case 31:
                    System.out.println("Cursuri disponibile:");
                    service.getCourses().forEach(c ->
                            System.out.println(c.getCourseId() + ": " + c.getName()));

                    System.out.print("Introdu ID curs: ");
                    String cursPentruRating = scanner.nextLine();
                    double rating = service.calculeazaRatingCurs(cursPentruRating);
                    System.out.printf("Ratingul mediu al cursului: %.1f/5\n", rating);
                    break;

                case 32:
                    System.out.println("Studenți disponibili:");
                    service.getUsers().values().stream()
                            .filter(u -> u instanceof Student)
                            .forEach(s -> System.out.println(s.getId() + ": " + s.getName()));

                    System.out.print("Introdu ID student: ");
                    String studentPentruRecenzii = scanner.nextLine();
                    service.afiseazaRecenziiStudent(studentPentruRecenzii);
                    break;

                case 0:
                    System.out.println("Aplicația se închide...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opțiune invalidă!");
            }
        }
    }
}
