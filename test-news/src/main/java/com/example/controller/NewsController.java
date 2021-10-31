package com.example.controller;


import com.example.entity.User;
import com.example.payload.ApiResponse;
import com.example.payload.NewsDto;
import com.example.security.CurrentUser;
import com.example.service.NewsService;
import com.example.utills.ApplicationConstance;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NewsController {


    final
    NewsService newsService;


    @PostMapping("/addNews")
    public HttpEntity<?> addNews(@RequestBody NewsDto newsDto, @CurrentUser User user) {
        ApiResponse apiResponse = newsService.addNews(newsDto, user);
        return ResponseEntity
                .status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST)
                .body(apiResponse);
    }

    @PutMapping("/editNews")
    public HttpEntity<?> edit(@RequestBody NewsDto newsDto,@CurrentUser User user){
        ApiResponse apiResponse = newsService.editNews(newsDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/allNews")
    public HttpEntity<?> getApprovedNews(@RequestParam(value = "page", defaultValue = ApplicationConstance.DEFAULT_PAGE_NUMBER) Integer page,
                                         @RequestParam(value = "size", defaultValue = ApplicationConstance.DEFAULT_PAGE_SIZE) Integer size,
                                         @CurrentUser User user) {
        ApiResponse apiResponse = newsService.getNews(page, size, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("/approvedNews")
    public HttpEntity<?> approveNews(@RequestParam(value = "status") boolean status,
                                     @RequestParam(value = "newsId") UUID newsId
    ) {
        ApiResponse apiResponse = newsService.appByAdmin(status, newsId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/allNewsForAdmin")
    public HttpEntity<?> getNewsForAdmin(@RequestParam(value = "page", defaultValue = ApplicationConstance.DEFAULT_PAGE_NUMBER) Integer page,
                                         @RequestParam(value = "size", defaultValue = ApplicationConstance.DEFAULT_PAGE_SIZE) Integer size
                                         ) {
        ApiResponse apiResponse = newsService.newsListForAdmin(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


}
