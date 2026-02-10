package com.example.PMS.DTO;

public class AdminDTO {
    private Long adminId;
    private String name;
    private String email;

    public AdminDTO(Long adminId, String name, String email) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
    }

    public Long getAdminId() { return adminId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}
