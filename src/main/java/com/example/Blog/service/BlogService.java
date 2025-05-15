package com.example.Blog.service;

import com.example.Blog.model.Blog;
import com.example.Blog.response.BlogListResponse;
import com.example.Blog.response.BlogResponse;
import com.example.Blog.response.Response;

public interface BlogService {

    BlogListResponse getAllBlogs();

    BlogResponse getBlogById(Long id);

    Response createBlog(Blog blog);

    Response updateBlog(Long id, Blog blog);
}
