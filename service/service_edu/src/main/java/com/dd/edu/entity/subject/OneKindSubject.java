package com.dd.edu.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneKindSubject {
    private String id;
    private String title;
    private List<TwoKindSubject> children=new ArrayList<>();
}
