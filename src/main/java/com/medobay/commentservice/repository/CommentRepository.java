package com.medobay.commentservice.repository;

import com.medobay.commentservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author Mohammad Farahani (mfarahani31@gmail.com)
 **/

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByServiceId(Long serviceId);

    Optional<Comment> findByServiceIdAndId(Long serviceId, Long commentId);
}
