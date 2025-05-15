package com.example.Blog.controller;

import com.example.Blog.model.Blog;
import com.example.Blog.response.BlogListResponse;
import com.example.Blog.response.BlogResponse;
import com.example.Blog.response.Response;
import com.example.Blog.service.BlogService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/all")
    public ResponseEntity<BlogListResponse> getAllBlogs(){
        logger.info("Requesting all blog post");
        return ResponseEntity.status(200).body(blogService.getAllBlogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlogById(@PathVariable Long id){
        logger.info("Requesting blog post with id: {}", id);
        return ResponseEntity.status(200).body(blogService.getBlogById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createBlog(@Valid @RequestBody Blog blog){
        logger.info("Creating new blog post");
        return ResponseEntity.status(200).body(blogService.createBlog(blog));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Response> updateBlog(@PathVariable Long id, @Valid @RequestBody Blog blog){
        logger.info("Updating old blog post of id: {}", id);
        return ResponseEntity.status(200).body(blogService.updateBlog(id, blog));
    }
}
