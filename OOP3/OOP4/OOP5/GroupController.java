import OOP5.models.User;
import OOP5.services.GroupService;

import java.util.List;

public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    public List<User> getAllUsersFromGroup(String groupTitle) {
        return groupService.getAllUsersFromGroup(groupTitle);
    }
}
