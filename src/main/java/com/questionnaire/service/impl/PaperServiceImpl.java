package com.questionnaire.service.impl;

import com.questionnaire.mapper.OptMapper;
import com.questionnaire.mapper.PaperMapper;
import com.questionnaire.mapper.QuestionMapper;
import com.questionnaire.pojo.Paper;
import com.questionnaire.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    OptMapper optMapper;

    @Override
    //根据用户id查询问卷
    public List<Paper> list(String userId) {
        List<Paper> paperList=paperMapper.listPaperByUserId(userId);
        if(!paperList.isEmpty()) {
            for (Paper paper : paperList) {
                //将单个问卷的所有问题列出来
                paper.setQuestions(questionMapper.listQuestionsByPaperId(paper.getId()));
            }
            for (Paper paper : paperList) {
                for (int j = 0; j < paper.getQuestions().size(); j++) {
                    //将单个问题中的所有选项列出来
                    paper.getQuestions().get(j).setOptList(optMapper.listOptByQuestionId(paper.getQuestions().get(j).getId()));
                }
            }
        }
        return paperList;
    }

    @Override
    //根据问卷id查询问卷
    public Paper list(Integer id) {
        Paper paper= paperMapper.listPaperById(id);
        //将单个问卷的所有问题列出来
        paper.setQuestions(questionMapper.listQuestionsByPaperId(paper.getId()));
        for (int j = 0; j < paper.getQuestions().size(); j++) {
            //将单个问题中的所有选项列出来
            paper.getQuestions().get(j).setOptList(optMapper.listOptByQuestionId(paper.getQuestions().get(j).getId()));
        }
        return paper;
    }

    @Override
    //根据问卷id删除问卷
    public void delete(Integer id){
        paperMapper.deletePaperById(id);
    }

    @Override
    //新建问卷
    public void add(Paper paper) {
        paper.setCreateTime(LocalDateTime.now());
        paper.setStartTime(LocalDateTime.now());
        paper.setEndTime(LocalDateTime.now());
        paperMapper.insert(paper);

        //如果问卷里面有问题
        if(paper.getQuestions()!=null&& !paper.getQuestions().isEmpty()) {
            for (int i = 0; i < paper.getQuestions().size(); i++) {
                //将问卷里面的所有问题的paperId置为当前问卷id
                paper.getQuestions().get(i).setPaperId(paper.getId());
            }
            //插入所有问题
            questionMapper.insert(paper.getQuestions());

            for (int i = 0; i < paper.getQuestions().size(); i++) {
                //如果问题里面有选项
                if(paper.getQuestions().get(i).getOptList()!=null&& !paper.getQuestions().get(i).getOptList().isEmpty())
                    for (int j = 0; j < paper.getQuestions().get(i).getOptList().size(); j++) {
                        //将所有选项的paperId和questionId置为当前问卷id和对应问题id
                        paper.getQuestions().get(i).getOptList().get(j).setPaperId(paper.getId());
                        paper.getQuestions().get(i).getOptList().get(j).setQuestionId(paper.getQuestions().get(i).getId());
                    }
            }
            //在对应问题中插入选项
            for (int i = 0; i < paper.getQuestions().size(); i++) {
                if(paper.getQuestions().get(i).getOptList()!=null&& !paper.getQuestions().get(i).getOptList().isEmpty())
                    optMapper.insert(paper.getQuestions().get(i).getOptList());
            }
        }
    }

    @Override
    //修改问卷（标题、开始时间、结束时间）
    public void update(Paper paper) {
        //如果问卷里面有问题
        if(paper.getQuestions()!=null&& !paper.getQuestions().isEmpty()) {
            //先删除原先的问题
            questionMapper.deleteQuestionByPaperId(paper.getId());

            for (int i = 0; i < paper.getQuestions().size(); i++) {
                //将问卷里面的所有问题的paperId置为当前问卷id
                paper.getQuestions().get(i).setPaperId(paper.getId());
            }
            //插入所有问题
            questionMapper.insert(paper.getQuestions());

            for (int i = 0; i < paper.getQuestions().size(); i++) {
                //如果问题里面有选项
                if(paper.getQuestions().get(i).getOptList()!=null&& !paper.getQuestions().get(i).getOptList().isEmpty())
                    for (int j = 0; j < paper.getQuestions().get(i).getOptList().size(); j++) {
                        //将所有选项的paperId和questionId置为当前问卷id和对应问题id
                        paper.getQuestions().get(i).getOptList().get(j).setPaperId(paper.getId());
                        paper.getQuestions().get(i).getOptList().get(j).setQuestionId(paper.getQuestions().get(i).getId());
                    }
            }
            //在对应问题中插入选项
            for (int i = 0; i < paper.getQuestions().size(); i++) {
                if(paper.getQuestions().get(i).getOptList()!=null&& !paper.getQuestions().get(i).getOptList().isEmpty())
                    optMapper.insert(paper.getQuestions().get(i).getOptList());
            }
        }
        paperMapper.update(paper);
    }
}
