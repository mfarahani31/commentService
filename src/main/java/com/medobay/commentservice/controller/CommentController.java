package com.medobay.commentservice.controller;


import com.medobay.commentservice.model.Comment;
import com.medobay.commentservice.model.UpdateStatusModel;
import com.medobay.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @author Mohammad Farahani (mfarahani31@gmail.com)
 **/

@RestController
@RequestMapping(CommentController.BASEURL)
public class CommentController {
    public static final String BASEURL = "/api/v1/services";
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{serviceId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllCommentsByServiceId(@PathVariable(value = "serviceId") Long serviceId) {
        return commentService.findByServiceId(serviceId);
    }

    @GetMapping("/{serviceId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment getOneComment(@PathVariable(value = "serviceId") Long serviceID,
                                 @PathVariable(value = "commentId") Long commentId) {
        return this.commentService.getOneByServiceIdAndCommentId(serviceID, commentId);
    }


    @PostMapping("/{serviceId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@PathVariable(value = "serviceId") Long serviceId,
                                 @Valid @RequestBody Comment comment) {
        return this.commentService.save(serviceId, comment);
    }

    @PutMapping("{serviceId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@PathVariable(value = "serviceId") Long serviceId,
                                 @PathVariable(value = "commentId") Long commentId,
                                 @Valid @RequestBody Comment comment) {
        return this.commentService.updateComment(serviceId, commentId, comment);
    }


    @DeleteMapping("/{serviceId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable(value = "serviceId") Long serviceId,
                              @PathVariable(value = "commentId") Long commentId) {
        this.commentService.deleteOneComment(serviceId, commentId);
    }

    @PatchMapping("/{serviceId}/comments/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateStatus(@PathVariable(value = "serviceId") Long serviceId,
                                               @PathVariable(value = "commentId") Long commentId,
                                               @Valid @RequestBody UpdateStatusModel updateStatusModel) {
        this.commentService.updateStatus(serviceId, commentId, updateStatusModel);
        return ResponseEntity.ok("Status updated!");
    }
}
