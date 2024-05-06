package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paper {
        private String id;//试卷ID
        private String userId;//用户ID，外键
        private String title;//试卷标题
        private LocalDateTime createTime;//试卷创建时间
        private Integer status;//状态值：0：未发布1：已发布2：已结束
        private LocalDateTime startTime;//开始时间
        private LocalDateTime endTime;//结束时间
}
