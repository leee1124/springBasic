package com.example.Sbb.Sbb.Controller;

import com.example.Sbb.Sbb.Entity.QuestionEntity;
import com.example.Sbb.Sbb.Service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor //questionRepository 속성을 포함하는 생성자 생성
//롬복에서 제공하는 애너테이션으로 final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<QuestionEntity> questionEntityList = this.questionService.getList();
        //질문목록 데이터를 생성하여 Model객체에 questionList라는 이름으로 값 저장
        //Model 객체는 자바 클래스와 템플릿 간의 연결고리를 한다.
        //Model 객체에 값을 담아두면 템플릿에서 그 값을 사용할 수 있다.
        model.addAttribute("questionEntityList", questionEntityList);
        return "question_list";
    }


    //변하는 id값을 얻을 때 @PathVariabsse애너테이션 사용,
    //@RequestMapping(value = "/question/detail/{id}")에서 사용한 id와
    //@PathVariable("id")의 매개변수 이름이 동일해야함
    @RequestMapping(value = "/detail/{id}")

    public String detail(Model model, @PathVariable("id") Integer id){
        QuestionEntity questionEntity = this.questionService.getQuestion(id);
        model.addAttribute("question", questionEntity);
        return "question_detail";
    }
}