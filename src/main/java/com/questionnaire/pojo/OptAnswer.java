package com.questionnaire.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//单条选项的结果
public class OptAnswer {
    String name;//选项名字
    Integer count;//选项统计
}
