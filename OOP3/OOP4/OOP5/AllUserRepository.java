import OOP5.models.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AllUserRepository <T extends User> implements Iterable <T> {

    private final List<T> allUsers;

    public AllUserRepository() {
        this.allUsers = new ArrayList<>();
    }

    public AllUserRepository(List<T> allUsers) {
        this.allUsers = allUsers;
    }


    public void create(T user) {
        user.setId(getMaxId() + 1);
        allUsers.add(user);
    }


    public List<T> getAll() {
        return allUsers;
    }


    public int remove(String fullName) {
        int removeCount = 0;

        Iterator<T> iterator = allUsers.iterator();
        while (iterator.hasNext()) {
            T user = iterator.next();
            if (user.getFullName().equals(fullName)) {
                iterator.remove();
                removeCount++;
            }
        }
        return removeCount;
    }


    public List<T> getAllByGroupTitle(String groupTitle) {
        return allUsers.stream()
                .filter(student -> student.getGroupTitle().equals(groupTitle))
                .collect(Collectors.toList());
    }

    private Long getMaxId() {
        Long maxId = 0L;
        for (T user : allUsers) {
            if (user.getId() > maxId) {
                maxId = user.getId();
            }
        }
        return maxId;
    }

    @Override
    public Iterator<T> iterator() {
        return allUsers.iterator();
    }
}
