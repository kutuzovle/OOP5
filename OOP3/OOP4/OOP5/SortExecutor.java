import OOP5.models.User;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortExecutor {
    public <T extends User> List<T> sort(List<T> items) {
        Collections.sort(items);
        return items;
    }

    public <T extends User> List<T> sortByFullName(List<T> items) {
        items.sort(Comparator.comparing(User::getFullName));
        return items;
    }

    public <T extends User> List<T> sortById(List<T> items) {
        items.sort(Comparator.comparing(User::getId));
        return items;
    }
}
