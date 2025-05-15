package com.example.Blog.response;

import com.example.Blog.model.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BlogResponse extends Response{
    private Blog blog;

    public BlogResponse(String message, boolean success, Blog blog){
        super(message,success);
        this.blog = blog;
    }
}
