package com.website.mokshagarbatti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.website.mokshagarbatti.entity.UserFeedback;

public interface FeedbackRepo extends JpaRepository<UserFeedback, Long> {

}
