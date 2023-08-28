package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.ReviewDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Review;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.ReviewRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<ReviewDTO> findAll(){
        return reviewRepository.findAll().stream().map(ReviewDTO::new).toList();
    }

    public ReviewDTO findById(Long id){
        return new ReviewDTO(reviewRepository.findById(id).orElseThrow());
    }

    public ReviewDTO create(Long authorId, Long productId, ReviewDTO reviewDTO){
        User author = userRepository.findById(authorId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        Review review = new Review();

        review.setAuthor(author);
        review.setProduct(product);
        review.setRating(reviewDTO.getRating());
        review.setCommentary(reviewDTO.getCommentary());

        var savedReview = reviewRepository.save(review);

        author.getReviews().add(savedReview);
        product.getReviews().add(savedReview);

        userRepository.save(author);
        productRepository.save(product);
        return new ReviewDTO(savedReview);
    }

    public ReviewDTO update(Long id, ReviewDTO reviewDTO){
        Review review = reviewRepository.findById(id).orElseThrow();
        if(reviewDTO.getRating() != null) review.setRating(reviewDTO.getRating());
        if(reviewDTO.getCommentary() != null) review.setCommentary(reviewDTO.getCommentary());

        return new ReviewDTO(reviewRepository.save(review));
    }

    public void delete(Long id){
        Review review = reviewRepository.findById(id).orElseThrow();
        reviewRepository.delete(review);
    }
}
