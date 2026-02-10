package com.example.PMS.DTO;

public class StudentDTO {
    private Long studentId;
    private String name;
    private String email;
    private String dept;
    private Double cgpa;
    private String skills;

    public StudentDTO(Long studentId, String name, String email,
                      String dept, Double cgpa, String skills) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.dept = dept;
        this.cgpa = cgpa;
        this.skills = skills;
    }

    public Long getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDept() { return dept; }
    public Double getCgpa() { return cgpa; }
    public String getSkills() { return skills; }
}
