package DTOs;

// import Driver.*;
// import DAOs.*;


public class UserDTO {
    private String userId;
    private String password;
    private String name;
    private String role;

    public UserDTO() {}

    public UserDTO(String userId, String password, String name, String role) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
