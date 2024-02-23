package com.imon.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Long companyId);

    boolean createReview(Long companyId, Review review);

    Review getReview(Long companyId, Long reviewId);
}
