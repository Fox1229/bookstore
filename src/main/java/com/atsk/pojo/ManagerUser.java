package com.atsk.pojo;

/**
 * @author Ly
 * @date 2021-07-16 21:04
 * 管理员表
 */
public class ManagerUser {
    private Integer id;
    private String managerName;
    private String password;

    public ManagerUser() {
    }

    public ManagerUser(Integer id, String managerName, String password) {
        this.id = id;
        this.managerName = managerName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagerUser{" +
                "id=" + id +
                ", managerName='" + managerName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
