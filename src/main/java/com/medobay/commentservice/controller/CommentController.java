package com.medobay.commentservice.controller;


import com.medobay.commentservice.model.Comment;
import com.medobay.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment-service/api/v1/services")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{serviceId}/comments")
    public List<Comment> getAllCommentsByServiceId(@PathVariable(value = "serviceId") Long serviceId) {
        return commentService.findByPostId(serviceId);
    }

    @GetMapping("/{serviceId}/comments/{commentId}")
    public Comment getCommentByServiceIdAndCommentId(@PathVariable(value = "serviceId") Long serviceId,
                                                     @PathVariable(value = "commentId") Long commentId) {
        return commentService. (serviceId);
    }
}
