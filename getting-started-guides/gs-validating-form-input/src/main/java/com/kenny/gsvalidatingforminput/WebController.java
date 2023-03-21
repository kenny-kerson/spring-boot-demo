package com.kenny.gsvalidatingforminput;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class WebController implements WebMvcConfigurer {

    private final static Logger logger = LoggerFactory.getLogger(WebController.class);

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/results")
                .setViewName("results");
    }

    @GetMapping("/")
    public String showForm(final PersonForm personForm) {
        return "form";
    }

    @PostMapping("/")
    public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {

        if ( bindingResult.hasErrors() ) {
            logger.info( "bindingResult.hasErrors() : {}", bindingResult.getFieldErrors());
            return "form";
        }

        return "redirect:/results";
    }
}
