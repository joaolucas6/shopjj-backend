package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.exceptions.BadRequestException;
import com.joaolucas.shopjj.exceptions.ResourceNotFoundException;
import com.joaolucas.shopjj.models.dto.ReviewDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Review;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.ReviewRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import com.joaolucas.shopjj.utils.DataValidation;
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
        return new ReviewDTO(reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review was not found with ID: " + id)));
    }

    public ReviewDTO create(Long authorId, Long productId, ReviewDTO reviewDTO){
        if(!DataValidation.isReviewInfoValid(reviewDTO)) throw new BadRequestException("Review info is invalid!");

        User author = userRepository.findById(authorId).orElseThrow(() -> new ResourceNotFoundException("User was not found with ID: " + authorId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product was not found with ID: " + productId));
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
        if(!DataValidation.isReviewInfoValid(reviewDTO)) throw new BadRequestException("Review info is invalid!");

        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review was not found with ID: " + id));
        if(reviewDTO.getRating() != null) review.setRating(reviewDTO.getRating());
        if(reviewDTO.getCommentary() != null) review.setCommentary(reviewDTO.getCommentary());

        return new ReviewDTO(reviewRepository.save(review));
    }

    public void delete(Long id){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review was not found with ID: " + id));
        reviewRepository.delete(review);
    }
}
