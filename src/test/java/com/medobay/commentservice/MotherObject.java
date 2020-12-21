package com.medobay.commentservice;

import com.medobay.commentservice.model.Comment;
import com.medobay.commentservice.model.Service;

import java.util.Collections;

public class MotherObject {

    public static Comment getAnyValidComment() {

        return new Comment(1L,
                "test1",
                "test1",
                new Service(1L, "test", "test", null));
    }

    public static Service getAnyValidService() {
        return new Service(1L,
                "test1",
                "test1",
                Collections.singletonList(getAnyValidComment()));
    }
}
