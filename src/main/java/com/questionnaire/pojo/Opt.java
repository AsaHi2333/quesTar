package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Opt {
    //选项的基本信息
    private Integer id;//选项id
    private Integer paperId;//问卷id
    private Integer questionId;//问题id
    private String optName;//选项名称
}