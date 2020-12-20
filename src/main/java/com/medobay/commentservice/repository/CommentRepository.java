package com.medobay.commentservice.repository;

import com.medobay.commentservice.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


/**
 * @author Mohammad Farahani (mfarahani31@gmail.com)
 **/

//@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment findByServiceId(Long serviceId);

    Optional<Comment> findByServiceIdAndId(Long serviceId, Long commentId);
}
