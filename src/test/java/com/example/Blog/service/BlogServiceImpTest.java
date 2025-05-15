package com.example.Blog.service;

import com.example.Blog.model.Blog;
import com.example.Blog.repository.BlogRepository;
import com.example.Blog.response.BlogListResponse;
import com.example.Blog.response.BlogResponse;
import com.example.Blog.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlogServiceImpTest {

    @Mock
    private BlogRepository blogRepository;

    private BlogServiceImpl blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        blogService = new BlogServiceImpl(blogRepository);
    }

    @Test
    void testGetAllBlogs_WhenBlogsExist() {
        System.out.println("Get all blogs - unit test");
        Blog blog1 = new Blog(1L, "Car", "Red Car in road", "car");
        Blog blog2 = new Blog(2L, "Book", "Book Red in car", "book");

        given(blogRepository.findAll())
                .willReturn(List.of(blog1, blog2));

        BlogListResponse blogListResponse = blogService.getAllBlogs();

        assertThat(blogListResponse.isSuccess()).isTrue();
        assertThat(blogListResponse.getBlogList().size()).isEqualTo(2);
    }

    @Test
    void testGetAllBlogs_WhenNoBlogsExist() {
        when(blogRepository.findAll()).thenReturn(Collections.emptyList());

        BlogListResponse blogListResponse = blogService.getAllBlogs();

        assertThat(blogListResponse.isSuccess()).isFalse();
        assertThat(blogListResponse.getBlogList().size()).isEqualTo(0);
    }

    @Test
    void testGetBlogById_WhenBlogExists() {
        Blog blog = new Blog(1L, "New Car", "Ferrari", "car");
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));

        BlogResponse blogResponse = blogService.getBlogById(1L);

        assertThat(blogResponse.isSuccess()).isTrue();
        assertThat(blog).isEqualTo(blogResponse.getBlog());
    }

    @Test
    void testGetBlogById_WhenBlogDoesNotExist() {
        when(blogRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(
                NoSuchElementException.class, () -> {
                    blogService.getBlogById(1L);
                });

        assertThat("Blog post not found of given ID").isEqualTo(exception.getMessage());
    }

    @Test
    void testCreateBlog_Success() {
        Blog blog1 = new Blog(1L, "Car", "Red Car in road", "car");

        given(blogRepository.save(blog1))
                .willReturn(blog1);

        Response response = blogService.createBlog(blog1);
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void testUpdateBlog_WhenBlogExists() {
        Blog existingBlog = new Blog(1L, "Old Title", "Old Desc", "Old Cat");
        Blog updatedData = new Blog(1L, "New Title", "New Desc", "New Cat");

        when(blogRepository.findById(1L)).thenReturn(Optional.of(existingBlog));

        Response response = blogService.updateBlog(1L, updatedData);

        assertThat(response.isSuccess()).isTrue();
        assertThat("New Title").isEqualTo(existingBlog.getTitle());
        assertThat("New Desc").isEqualTo(existingBlog.getDescription());
        assertThat("New Cat").isEqualTo(existingBlog.getCategory());
    }

    @Test
    void testUpdateBlog_WhenBlogDoesNotExist() {
        Blog updatedData = new Blog(1L, "New Title", "New Desc", "New Cat");

        when(blogRepository.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException exception = Assertions.assertThrows(
                NoSuchElementException.class, () -> {
                    blogService.updateBlog(1L, updatedData);
                });

        assertThat("Blog post not found of given ID").isEqualTo(exception.getMessage());
    }
}
