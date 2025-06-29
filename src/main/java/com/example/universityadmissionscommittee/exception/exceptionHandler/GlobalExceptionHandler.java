package com.example.universityadmissionscommittee.exception.exceptionHandler;

import com.example.universityadmissionscommittee.exception.applicant.ApplicantCreationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicantCreationException.class)
    public String handleApplicantCreation(ApplicantCreationException ex,
                                          RedirectAttributes redirectAttributes,
                                          HttpServletRequest request) {

        redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
