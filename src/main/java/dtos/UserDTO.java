package dtos;

import entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String userName;
    private String userPass;


    public static List<UserDTO> getDtos(List<User> u){
        List<UserDTO> userDTOSdtos = new ArrayList();
        u.forEach(um -> userDTOSdtos.add(new UserDTO(um)));
        return userDTOSdtos;
    }

    public UserDTO(User user) {
        this.userName = user.getUserName();
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }




}
