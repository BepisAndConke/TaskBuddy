package nl.fontys.TaskBuddy.business;


import nl.fontys.TaskBuddy.domain.User;
import nl.fontys.TaskBuddy.persistence.entity.UserEntity;

public final class UserConverter {
    private UserConverter(){
    }

    public static User convert(UserEntity user){
        return User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .address(user.getAddress())
                .role(user.getRole())
                .exp(user.getExp())
                .level(user.getLevel())
                .tasks(user.getTasks()
                        .stream()
                        .map(TaskConverter::convert)
                        .toList())
                .build();
    }

}
