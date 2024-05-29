package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
        //问卷的基本信息，不包含答案
        private Integer id;//试卷ID
        private Integer userId;//用户ID，外键
        private String title;//试卷标题
        private LocalDateTime createTime;//试卷创建时间
        private Integer status;//状态值：0：未发布1：已发布2：已结束
        private LocalDateTime startTime;//开始时间
        private LocalDateTime endTime;//结束时间

        private List<Question> questions;//问卷内包含的问题
}
