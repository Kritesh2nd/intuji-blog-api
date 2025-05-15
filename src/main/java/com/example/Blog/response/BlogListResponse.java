package com.example.Blog.response;

import com.example.Blog.model.Blog;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BlogListResponse extends Response {

    private List<Blog> blogList;

    public BlogListResponse(String message, boolean success, List<Blog> blogList){
        super(message, success);
        this.blogList = blogList;
    }
}

