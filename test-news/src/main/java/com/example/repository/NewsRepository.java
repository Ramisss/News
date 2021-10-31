package com.example.repository;

import com.example.entity.News;
import com.example.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NewsRepository extends JpaRepository<News, UUID> {


   Page<News> findAllByApprovedByAdminTrue(Pageable pageable);


   Optional<News> findById(UUID uuid);
}
