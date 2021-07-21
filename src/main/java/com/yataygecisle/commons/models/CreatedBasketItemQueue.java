package com.yataygecisle.commons.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedBasketItemQueue implements Serializable {

    static final long serialVersionUID = 87657890876542L;

    private String basketItemId;

    private String basketId;

    private String collegeId;

    private String facultyId;

    private String courseId;

    private String collegeName;

    private String facultyName;

    private String courseName;

    private Timestamp createdAt;

    private Timestamp lastModifiedAt;

}
