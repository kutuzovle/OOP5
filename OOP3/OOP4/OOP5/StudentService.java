import OOP5.models.Student;
import OOP5.repositories.AllUserRepository;

import java.util.List;

public class StudentService implements UserService<Student> {

    private final AllUserRepository<Student> studentRepository;
    private SortExecutor sortExecutor;


    public StudentService(AllUserRepository<Student> studentRepository) {
        this.studentRepository = studentRepository;
        sortExecutor = new SortExecutor();
    }

    @Override
    public void create(String fullName, Integer age, String phoneNumber, String groupTitle) {

        studentRepository.create(new Student(fullName, age, phoneNumber, groupTitle));
    }

    @Override
    public List<Student> getAll() {
        return sortExecutor.sort(studentRepository.getAll());
    }

    @Override
    public List<Student> getAllSortByFullName() {
        return sortExecutor.sortByFullName(studentRepository.getAll());
    }

    @Override
    public List<Student> getAllSortById() {
        return sortExecutor.sortById(studentRepository.getAll());
    }


    @Override
    public int remove(String fullName) {
        return studentRepository.remove(fullName);
    }

    @Override
    public List<Student> getAllByTitile(String groupTitle) {
        return studentRepository.getAllByGroupTitle(groupTitle);
    }
}
