package com.example.service;


import com.example.entity.News;
import com.example.entity.User;
import com.example.payload.ApiResponse;
import com.example.payload.NewsDto;
import com.example.repository.NewsRepository;
import com.example.repository.UserRepository;
import com.example.utills.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NewsService {


    final
    NewsRepository newsRepository;

    final
    UserRepository userRepository;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse addNews(NewsDto newsDto, User user) {

        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) {
            return new ApiResponse("User not found", false);
        }
        User user1 = optionalUser.get();

        News news = new News();
        news.setNewsTitle(newsDto.getNewsTitle());
        news.setNewsText(newsDto.getNewsText());
        news.setUser(user1);
        newsRepository.save(news);

        return new ApiResponse("News saved", true, news);
    }

    public ApiResponse getNews(Integer page, Integer size, User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) return new ApiResponse("User not found", false);
        Page<News> newsPage;
        newsPage = newsRepository.findAllByApprovedByAdminTrue(CommonUtils.getPageableByCreatedAtAsc(page, size));
        return new ApiResponse("ALL NEWS HERE", true,
                newsPage.getContent()
                        .stream().map(this::getNewsFromDb)
                        .collect(Collectors.toList()), newsPage.getTotalElements(), page);
    }


    public NewsDto getNewsFromDb(News news) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(news.getId());
        newsDto.setNewsTitle(news.getNewsTitle());
        newsDto.setNewsText(news.getNewsText());
        newsDto.setApprovedByAdmin(news.isApprovedByAdmin());  //???
        return newsDto;
    }

    public ApiResponse appByAdmin(boolean status, UUID newsId) {
        Optional<News> optionalNews = newsRepository.findById(newsId);
        if (optionalNews.isEmpty()) return new ApiResponse("News not found", false);
        optionalNews.get().setApprovedByAdmin(status);
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        optionalNews.get().setApprovedTime(timestamp2);
        newsRepository.save(optionalNews.get());
        return new ApiResponse("News Approved by ADMIN", true);

    }

    public ApiResponse newsListForAdmin(Integer page, Integer size) {
        Page<News> newsPage;
        newsPage = newsRepository.findAll(CommonUtils.getPageableByCreatedAtAsc(page, size));
        return new ApiResponse("ALL NEWS HERE", true,
                newsPage.getContent()
                        .stream().map(this::getNewsFromDb)
                        .collect(Collectors.toList()), newsPage.getTotalElements(), page);
    }

    public ApiResponse editNews(NewsDto newsDto, User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isEmpty()) return new ApiResponse("User not found", false);
        User user1=optionalUser.get();
        Optional<News> optionalNews = newsRepository.findById(newsDto.getId());
        if (optionalNews.isEmpty()) return new ApiResponse("News not found", false);
        News news = optionalNews.get();
        news.setNewsTitle(newsDto.getNewsTitle());
        news.setNewsText(newsDto.getNewsText());
        news.setApprovedByAdmin(false);
        news.setUser(user1);
        newsRepository.save(news);
        return new ApiResponse("News edited by", true);
    }
}














