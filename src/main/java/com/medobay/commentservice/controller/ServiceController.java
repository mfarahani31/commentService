package com.medobay.commentservice.controller;

import com.medobay.commentservice.model.Service;
import com.medobay.commentservice.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author Mohammad Farahani (mfarahani31@gmail.com)
 **/

@RestController
@RequestMapping(CommentController.BASEURL)
public class ServiceController {

    private final ServiceRepository serviceRepository;

    @Autowired
    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Service createPost(@Valid @RequestBody Service service) {
        return serviceRepository.save(service);
    }


}
