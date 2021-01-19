package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.DTOEntities.ExamDTO;
import com.examcreator.finalproject.entities.DTOEntities.OptionalQuestionDTO;
import com.examcreator.finalproject.entities.DTOEntities.StudentAnswerDTO;
import com.examcreator.finalproject.entities.DTOEntities.TextQuestionDTO;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Course;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.Exam;
import com.examcreator.finalproject.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/student")
public class StudentController {
    private final ApplicationContext context;
    private final CourseService courseService;
    private final ExamService examService;
    private final TextQuestionService textQuestionService;
    private final OptionalQuestionService optionalQuestionService;
    private final StudentAnswerService studentAnswerService;

    public StudentController(ApplicationContext context, CourseService courseService, ExamService examService, TextQuestionService textQuestionService, OptionalQuestionService optionalQuestionService, StudentAnswerService studentAnswerService) {
        this.context = context;
        this.courseService = courseService;
        this.examService = examService;
        this.textQuestionService = textQuestionService;
        this.optionalQuestionService = optionalQuestionService;
        this.studentAnswerService = studentAnswerService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        List<Course> coursesOfStudent = courseService.findCoursesOfStudent(username);
        model.addAttribute("courses", coursesOfStudent);
        return "User/Student/dashboard";
    }

    @GetMapping("/show-exams/{course-id}")
    public String showExamsOfCoursePage(@PathVariable("course-id") Long courseId, Model model) {
        Function<Exam, ExamDTO> function = a -> {
            ExamDTO examDTO = context.getBean(ExamDTO.class, a);
            return examDTO;
        };
        List<ExamDTO> exams = examService.findExamsOfCourse(courseId).stream().map(function).collect(Collectors.toList());
        model.addAttribute("exams", exams);
        return "User/Student/show-exams";
    }

    @GetMapping("/take-exam/{exam-id}")
    public String showTakeExamPage(@PathVariable("exam-id") Long examId, Model model, Principal principal) throws ParseException {
        Exam exam = examService.findByID(examId).get();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date(System.currentTimeMillis());
        if (exam.getStartTime().isBefore(LocalTime.parse(timeFormat.format(now))) && exam.getEndTime().isAfter(LocalTime.parse(timeFormat.format(now)))) {
            model.addAttribute("answer", context.getBean(StudentAnswerDTO.class));
            model.addAttribute("examId",examId);

            List<TextQuestionDTO> textQuestionsDTOList = textQuestionService.findQuestionsWithAnswer(exam, principal.getName());
            List<OptionalQuestionDTO> optionalQuestionDTOList = optionalQuestionService.findQuestionsWithAnswer(exam, principal.getName());

            model.addAttribute("textQuestions", textQuestionsDTOList);
            model.addAttribute("optionalQuestions", optionalQuestionDTOList);


            String s1 = simpleDateFormat.format(now) + " " + exam.getEndTime().getHour() + ":" + exam.getEndTime().getMinute();
            model.addAttribute("endDate", new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(s1));

            return "User/Student/take-exam";
        } else if (exam.getEndTime().isBefore(LocalTime.parse(timeFormat.format(now)))) {
            return "redirect:/student/dashboard";
        } else {
            return "redirect:/student/dashboard";
        }
    }

    @PostMapping("/add-answer")
    public String addAnswer(@ModelAttribute("answer") StudentAnswerDTO answerDTO , Principal principal) {
        studentAnswerService.saveAnswer(answerDTO,principal.getName());
        return "redirect:/student/take-exam/" + answerDTO.getExamId();
    }
}
