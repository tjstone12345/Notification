package org.ow2.proactive.notification.web;

import java.util.Map;

import org.ow2.proactive.notification.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.ow2.proactive.notification.service.NotificationService;
import org.ow2.proactive.notification.service.TODOService;

@Controller
public class WebController {

    private final Logger logger = LoggerFactory.getLogger(WebController.class);
    private final NotificationService notificationService;
    private final TODOService todoService;


    @Autowired
    public WebController(NotificationService notificationService, TODOService todoService) {
        this.notificationService = notificationService;
        this.todoService = todoService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("title", notificationService.getTitle(""));
        model.put("msg", notificationService.getDesc());

        return "index";
    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        logger.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", notificationService.getTitle(name));
        model.addObject("msg", notificationService.getDesc());

        return model;

    }

}