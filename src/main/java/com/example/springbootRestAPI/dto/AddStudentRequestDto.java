package com.example.springbootRestAPI.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
public class AddStudentRequestDto {
    private String name;
    private String email;
    private String branch;
}
