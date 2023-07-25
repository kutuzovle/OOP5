import OOP5.controllers.GroupController;
import OOP5.controllers.StudentController;
import OOP5.controllers.TeacherController;
import OOP5.models.Student;
import OOP5.models.Teacher;
import OOP5.repositories.AllUserRepository;

import OOP5.services.GroupService;
import OOP5.services.StudentService;
import OOP55.services.TeacherService;
import OOP5.view.AllUsersView;
import OOP5.view.GroupView;
import OOP5.view.SortType;

import static OOP5.UniversityApp.*;


public class Main {



    public static void main(String[] args) {

        UniversityApp app = new UniversityApp();
        app.run();

    }


}
