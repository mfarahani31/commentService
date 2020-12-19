package com.medobay.commentservice.repository;

import com.medobay.commentservice.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


/**
 * @author Mohammad Farahani (mfarahani31@gmail.com)
 **/

@RepositoryRestResource(collectionResourceRel = "comment", path = "comment")
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
