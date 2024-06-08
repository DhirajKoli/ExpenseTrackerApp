package com.example.Tracker.dto;

import com.example.Tracker.model.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubCategoryDto {

    private String name;

    private Long categoryId;
}
