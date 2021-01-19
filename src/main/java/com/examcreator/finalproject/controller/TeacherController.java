package com.examcreator.finalproject.controller;

import com.examcreator.finalproject.entities.DTOEntities.*;
import com.examcreator.finalproject.entities.classEntities.OtherObjects.*;
import com.examcreator.finalproject.entities.enumEntities.TypeOfExam;
import com.examcreator.finalproject.service.*;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final ApplicationContext context;
    private final TeacherService teacherService;
    private final CourseService courseService;
    private final ExamService examService;
    private final QuestionService questionService;
    private final OptionalQuestionService optionalQuestionService;
    private final TextQuestionService textQuestionService;

    public TeacherController(ApplicationContext context, TeacherService teacherService, CourseService courseService, ExamService examService, QuestionService questionService, OptionalQuestionService optionalQuestionService, TextQuestionService textQuestionService) {
        this.context = context;
        this.teacherService = teacherService;
        this.courseService = courseService;
        this.examService = examService;
        this.questionService = questionService;
        this.optionalQuestionService = optionalQuestionService;
        this.textQuestionService = textQuestionService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        String username = principal.getName();
        List<Course> coursesOfTeacher = courseService.findCoursesOfTeacher(username);
        model.addAttribute("courses", coursesOfTeacher);
        return "User/Teacher/dashboard";
    }

    @GetMapping("/add-exam/{course-id}")
    public String showAddExamPage(@PathVariable("course-id") Long courseId, Model model) {
        Function<Exam, ExamDTO> function = a -> {
            ExamDTO examDTO = context.getBean(ExamDTO.class, a);
            return examDTO;
        };
        List<ExamDTO> exams = examService.findExamsOfCourse(courseId).stream().map(function).collect(Collectors.toList());
        model.addAttribute("courseId", courseId);
        model.addAttribute("exams", exams);
        model.addAttribute("exam", context.getBean(ExamDTO.class));
        return "User/Teacher/add-exam";
    }

    @PostMapping("/add-exam/{course-id}")
    public String addExam(@ModelAttribute("exam") ExamDTO examDTO, @PathVariable("course-id") Long courseId) {
        examService.saveExam(examDTO, courseId);
        return "redirect:/teacher/add-exam/" + courseId;
    }

    @GetMapping("/edit-exam/{exam-id}")
    public String showEditExamPage(@PathVariable("exam-id") Long examId, Model model) {
        Exam oldExam = examService.findByID(examId).get();
        ExamDTO oldExamDTO = context.getBean(ExamDTO.class, oldExam);
        ExamDTO examDTO = context.getBean(ExamDTO.class);
        model.addAttribute("courseId", oldExam.getCourse().getId());
        model.addAttribute("oldExam", oldExamDTO);
        model.addAttribute("newExam", examDTO);
        return "User/Teacher/edit-exam";
    }

    @PostMapping("/edit-exam/{exam-id}/{course-id}")
    public String editExam(@ModelAttribute("newExam") ExamDTO newExam, @PathVariable("exam-id") Long examId, @PathVariable("course-id") String courseId) {
        examService.editExam(newExam, examId);
        return "redirect:/teacher/add-exam/" + courseId;
    }

    @GetMapping("/delete-exam/{exam-id}")
    public String deleteExamById(@PathVariable("exam-id") Long examId) {
        Exam exam = examService.findByID(examId).get();
        Long id = exam.getCourse().getId();
        examService.delete(exam);
        return "redirect:/teacher/add-exam/" + id;
    }

    @GetMapping("/add-question/{exam-id}")
    public String showAddQuestionPage(@PathVariable("exam-id") Long examId, Model model) {
        Exam exam = examService.findByID(examId).get();
        model.addAttribute("bankQuestion", context.getBean(BankQuestionDTO.class));
        model.addAttribute("examId", examId);


        Function<Question, QuestionDTO> function = a -> context.getBean(QuestionDTO.class, a, exam);

        Set<QuestionDTO> bankQuestionsDTOList = questionService.findQuestionsOfCourse(exam.getCourse().getId()).stream().map(function).collect(Collectors.toSet());
        List<QuestionDTO> pastQuestionDTOList = questionService.findQuestionsOfExam(examId).stream().map(function).collect(Collectors.toList());

        model.addAttribute("questionsBank", bankQuestionsDTOList);
        model.addAttribute("pastQuestions", pastQuestionDTOList);

        if (exam.getTypeOfExam().equals(TypeOfExam.TYPE_OPTIONAL)) {

            model.addAttribute("newOptionalQuestion", context.getBean(OptionalQuestionDTO.class));
            return "User/Teacher/add-optional-question";

        } else if (exam.getTypeOfExam().equals(TypeOfExam.TYPE_TEXT)) {

            model.addAttribute("newTextQuestion", context.getBean(TextQuestionDTO.class));
            return "User/Teacher/add-text-question";

        } else if (exam.getTypeOfExam().equals(TypeOfExam.TYPE_COMPOUND)) {

            model.addAttribute("newOptionalQuestion", context.getBean(OptionalQuestionDTO.class));
            model.addAttribute("newTextQuestion", context.getBean(TextQuestionDTO.class));
            return "User/Teacher/add-question";

        }
        return "redirect:/teacher/add-exam/" + exam.getCourse().getId();
    }

    @PostMapping("/add-question/{exam-id}/optional")
    public String addOptionalQuestion(@ModelAttribute("newOptionalQuestion") OptionalQuestionDTO optionalQuestionDTO, @PathVariable("exam-id") Long examId) {
        optionalQuestionService.save(optionalQuestionDTO, examId);
        return "redirect:/teacher/add-question/" + examId;
    }

    @PostMapping("/add-question/{exam-id}/text")
    public String addTextQuestion(@ModelAttribute("newTextQuestion") TextQuestionDTO textQuestionDTO, @PathVariable("exam-id") Long examId) {
        textQuestionService.save(textQuestionDTO, examId);
        return "redirect:/teacher/add-question/" + examId;
    }

    @PostMapping("/add-question/from-bank")
    public String addQuestionFromBank(@ModelAttribute("bankQuestion") BankQuestionDTO bankQuestionDTO) {
        questionService.addQuestionFromBank(bankQuestionDTO);
        Long examId = bankQuestionDTO.getExamId();
        return "redirect:/teacher/add-question/" + examId;
    }

    @GetMapping("/edit-question/{exam-id}/{q-id}")
    public String showEditQuestionPage(@PathVariable("q-id") Long qId, Model model, @PathVariable("exam-id") Long examId) {
        Question question = questionService.findByID(qId).get();
        Exam exam = examService.findByID(examId).get();
        model.addAttribute("examId", examId);
        if (question instanceof OptionalQuestion) {
            //question = optionalQuestionService.findByID(qId).get();
            model.addAttribute("optionalQuestion", context.getBean(OptionalQuestionDTO.class, question, exam));
            return "User/Teacher/edit-optional-question";
        } else if (question instanceof TextQuestion) {
            //question = textQuestionService.findByID(qId).get();
            model.addAttribute("textQuestion", context.getBean(TextQuestionDTO.class, question, exam));
            return "User/Teacher/edit-text-question";
        } else {
            return "redirect:/teacher/dashboard";
        }
    }

    @PostMapping("/edit-question/{exam-id}/{q-id}/text")
    public String editTextQuestion(@ModelAttribute("question") TextQuestionDTO question, @PathVariable("exam-id") Long examId, @PathVariable("q-id") Long qId) {
        textQuestionService.edit(question, qId, examId);
        return "redirect:/teacher/add-question/" + examId;
    }

    @PostMapping("/edit-question/{exam-id}/{q-id}/optional")
    public String editOptionalQuestion(@ModelAttribute("question") OptionalQuestionDTO question, @PathVariable("exam-id") Long examId, @PathVariable("q-id") Long qId) {
        optionalQuestionService.edit(question, qId, examId);
        return "redirect:/teacher/add-question/" + examId;
    }

    @GetMapping("/delete-question/{q-id}/{exam-id}")
    public String deleteQuestion(@PathVariable("q-id") Long qId, @PathVariable("exam-id") Long id) {
        Question question = questionService.findByID(qId).get();
        questionService.delete(question);
        return "redirect:/teacher/add-question/" + id;
    }

}
