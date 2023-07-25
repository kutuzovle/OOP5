import OOP5.controllers.GroupController;
import OOP5.controllers.StudentController;
import OOP5.controllers.TeacherController;
import OOP5.models.Student;
import OOP5.models.Teacher;
import OOP5.repositories.AllUserRepository;
import OOP5.services.GroupService;
import OOP5.services.StudentService;
import OOP5.services.TeacherService;
import OOP5.view.AllUsersView;
import OOP5.view.GroupView;

import java.util.Scanner;

public class UniversityApp {
    /**
     Доступны команды создания, удаления и просмотра. exit = выход. примеры команд ниже
     studentView.create("Ivan Morozov", 18, "02234", "11Б") -> create-student Ivan_Morozov 18 02234 11A
     teacherView.create("Piotr Petrovich", 38, "32223", "11Б"); -> create-teacher Piotr_Petrovich 38 32223 11Б
     studentView.sendOnConsole(); -> get-student
     teacherView.sendOnConsole(); -> get-teacher
     studentView.removeUser("Ivan Morozov"); -> remove-student Ivan_Morozov
     teacherView.removeUser("Piotr Petrovich"); -> remove-teacher Piotr_Petrovich
     groupView.printAllFromGroup("11А"); -> get-group 11А

     */
    private static AllUserRepository studentRepository;
    private static StudentService studentService;
    private static TeacherService teacherService;

    private static StudentController studentController;
    private static TeacherController teacherController;

    private AllUsersView<Student> studentView;
    private AllUsersView<Teacher> teacherView;
    private GroupView groupView;

    private static final String EXIT_COMMAND = "exit";


    public UniversityApp() {

        studentView = getStudentController();
        teacherView = getTeacherController();
        groupView = getGroupView();

    }

    public void run() {


        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine().trim();
            if (command.equalsIgnoreCase(EXIT_COMMAND)) {
                running = false;
            } else {
                processCommand(command);
            }
        }
        scanner.close();
    }

    private void processCommand(String command) {
        String[] parts = command.split("\\s+");
        String operation = parts[0];
        switch (operation) {
            case "create-student":
                if (parts.length != 5) {
                    System.out.println("Некорректное количество аргументов для создания студента");
                } else {
                    String fullName = parts[1].replace("_", " ");
                    int age = Integer.parseInt(parts[2]);
                    String id = parts[3];
                    String group = parts[4];
                    studentView.create(fullName, age, id, group);
                }
                break;
            case "create-teacher":
                if (parts.length != 5) {
                    System.out.println("Некорректное количество аргументов для создания преподавателя");
                } else {
                    String fullName = parts[1].replace("_", " ");
                    int age = Integer.parseInt(parts[2]);
                    String id = parts[3];
                    String department = parts[4];
                    teacherView.create(fullName, age, id, department);
                }
                break;
            case "get-student":
                studentView.sendOnConsole();
                break;
            case "get-teacher":
                teacherView.sendOnConsole();
                break;
            case "remove-student":
                if (parts.length != 2) {
                    System.out.println("Некорректное количество аргументов для удаления студента");
                } else {
                    String fullName = parts[1].replace("_", " ");
                    studentView.removeUser(fullName);
                }
                break;
            case "remove-teacher":
                if (parts.length != 2) {
                    System.out.println("Некорректное количество аргументов для удаления преподавателя");
                } else {
                    String fullName = parts[1].replace("_", " ");
                    teacherView.removeUser(fullName);
                }
                break;
            case "get-group":
                if (parts.length != 2) {
                    System.out.println("Некорректное количество аргументов для вывода группы");
                } else {
                    String group = parts[1];
                    groupView.printAllFromGroup(group);
                }
                break;
            default:
                System.out.println("Неизвестная команда");
                break;
        }
    }


    private static AllUsersView<Student> getStudentController() {
        AllUserRepository<Student> studentRepository = new AllUserRepository();
        studentService = new StudentService(studentRepository);
        studentController = new StudentController(studentService);
        return new AllUsersView(studentController);

    }

    private static AllUsersView<Teacher> getTeacherController() {
        AllUserRepository<Teacher> teacherRepository = new AllUserRepository();
        teacherService = new TeacherService(teacherRepository);
        teacherController = new TeacherController(teacherService);
        return new AllUsersView(teacherController);
    }

    private static GroupView getGroupView() {
        GroupService groupService = new GroupService(studentService, teacherService);
        GroupController groupController = new GroupController(groupService);
        return new GroupView(groupController);
    }
}