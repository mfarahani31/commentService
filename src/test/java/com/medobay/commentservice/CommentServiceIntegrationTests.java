package com.medobay.commentservice;

import com.medobay.commentservice.controller.CommentController;
import com.medobay.commentservice.model.Comment;
import com.medobay.commentservice.service.CommentService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentServiceIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private CommentService commentService;

    @Test
    void contextLoads() {
        assertNotNull(commentService);
        assertNotNull(restTemplate);
    }


    @Test
    void given_getComment_when_comment_exists_then_return_comment() {
        List<Comment> comments = new ArrayList<>();
        comments.add(MotherObject.getAnyValidComment());
        doReturn(comments).when(commentService).findByServiceId(anyLong());

        ResponseEntity<String> response = restTemplate.exchange(CommentController.BASEURL + "/1/comments", HttpMethod.GET, null, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        //assertEquals(MotherObject.getAnyValidNumberingFormat().getLastAllocatedSerial().toString(), Objects.requireNonNull(response.getBody()).substring(21, 24));
        verify(commentService, times(1)).findByServiceId(anyLong());
    }

    //Todo ; more tests and more scenarios !!!

}
