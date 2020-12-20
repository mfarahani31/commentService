package com.medobay.commentservice.service;

import com.medobay.commentservice.constants.ErrorMessages;
import com.medobay.commentservice.exp.BusinessException;
import com.medobay.commentservice.model.Comment;
import com.medobay.commentservice.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) throws BusinessException {
        try {
            return this.commentRepository.save(comment);
        } catch (HttpServerErrorException e) {
            throw new BusinessException(ErrorMessages.DUPLICATE_NUMBERFORMAT);
        }
    }

    public Comment getOneByServiceIdAndCommentId(Long serviceId, Long commentId) {
        return this.commentRepository.findByServiceIdAndId(serviceId, commentId).get();
    }

    public void delete(Long id) throws BusinessException {
        try {
            this.commentRepository.deleteById(id);
        } catch (HttpServerErrorException e) {
            throw new BusinessException(ErrorMessages.NOT_EXIST);
        }
    }


}
