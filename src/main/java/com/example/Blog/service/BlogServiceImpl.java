package com.example.Blog.service;

import com.example.Blog.model.Blog;
import com.example.Blog.repository.BlogRepository;
import com.example.Blog.response.BlogListResponse;
import com.example.Blog.response.BlogResponse;
import com.example.Blog.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    private final BlogRepository blogRepository;

    Logger logger = LoggerFactory.getLogger(BlogServiceImpl.class);

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogListResponse getAllBlogs() {

        logger.info("Fetching all blog posts from the database");
        List<Blog> blogList = blogRepository.findAll();

        if(blogList.isEmpty()){
            logger.warn("No blog posts found in the database");
            return new BlogListResponse("No blog post in database",false,List.of());
        }

        logger.info("Found {} blog(s) in the database", blogList.size());
        return new BlogListResponse("List of blogs",true,blogList);
    }

    @Override
    public BlogResponse getBlogById(Long id) {

        logger.info("Fetching blog post by ID: {}", id);
        Optional<Blog> blog = blogRepository.findById(id);

        if(blog.isEmpty()){
            logger.error("Blog post not found for ID: {}", id);
            throw new NoSuchElementException("Blog post not found of given ID");
        }

        logger.info("Blog post found for ID: {}", id);
        return new BlogResponse("Blog post of given Id",true,blog.get());
    }

    @Override
    public Response createBlog(Blog blog) {

        logger.info("Creating new blog post with title: {}", blog.getTitle());
        blog.setId(null);
        blogRepository.save(blog);

        logger.info("Blog post created successfully with new ID assigned.");
        return new Response("New blog post created successfully",true);
    }

    @Override
    public Response updateBlog(Long id, Blog blog) {

        logger.info("Updating blog post with ID: {}", id);
        Optional<Blog> oldBlog = blogRepository.findById(id);

        if(oldBlog.isEmpty()){
            logger.error("Blog post not found for update. ID: {}", id);
            throw new NoSuchElementException("Blog post not found of given ID");
        }

        oldBlog.get().setTitle(blog.getTitle());
        oldBlog.get().setDescription(blog.getDescription());
        oldBlog.get().setCategory(blog.getCategory());

        blogRepository.save(oldBlog.get());
        logger.info("Blog post with ID {} updated successfully", id);

        return new Response("Blog updated successfully",true);
    }
}
