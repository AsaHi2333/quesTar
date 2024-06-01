package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@NoArgsConstructor
@AllArgsConstructor
//单个问卷的标题和所有问题的答案
public class CompleteAnswerPlusPaperTitle {

    private String paperTitle;
    private List<CompleteAnswer> completeAnswerList;


}
