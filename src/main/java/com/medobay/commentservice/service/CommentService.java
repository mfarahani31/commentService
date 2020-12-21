package com.medobay.commentservice.service;

import com.medobay.commentservice.model.Comment;
import com.medobay.commentservice.model.UpdateStatusModel;
import com.medobay.commentservice.repository.CommentRepository;
import com.medobay.commentservice.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ServiceRepository serviceRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, ServiceRepository serviceRepository) {
        this.commentRepository = commentRepository;
        this.serviceRepository = serviceRepository;
    }

    public Comment save(Long serviceId, Comment comment) {
        return serviceRepository.findById(serviceId).map(service -> {
            comment.setService(service);
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("ServiceId " + serviceId + " not found"));
    }

    public List<Comment> findByServiceId(Long serviceId) {
        return this.commentRepository.findByServiceId(serviceId);
    }

    public Comment getOneByServiceIdAndCommentId(Long serviceId, Long commentId) {
        try {
            return this.commentRepository.findByServiceIdAndId(serviceId, commentId).get();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Comment not found!");
        }
    }

    public Long deleteOneComment(Long serviceId, Long commentId) {
        return commentRepository.findByServiceIdAndId(serviceId, commentId).map(comment -> {
            commentRepository.delete(comment);
            return commentId;
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and serviceId " + serviceId));
    }


    public Comment updateComment(Long serviceId, Long commentId, Comment commentRequest) {
        if (!serviceRepository.existsById(serviceId)) {
            throw new ResourceNotFoundException("ServiceId " + serviceId + " not found");
        }
        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            comment.setStatus(commentRequest.getStatus());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + " not found"));
    }

    public void updateStatus(Long serviceId, Long commentId, UpdateStatusModel updateStatusModel) {
        if (!serviceRepository.existsById(serviceId)) {
            throw new ResourceNotFoundException("ServiceId " + serviceId + " not found");
        }
        commentRepository.findById(commentId).map(comment -> {
            comment.setStatus(updateStatusModel.getStatus());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }
}
