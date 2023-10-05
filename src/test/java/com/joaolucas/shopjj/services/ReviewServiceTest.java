package com.joaolucas.shopjj.services;

import com.joaolucas.shopjj.models.dto.ReviewDTO;
import com.joaolucas.shopjj.models.entities.Product;
import com.joaolucas.shopjj.models.entities.Review;
import com.joaolucas.shopjj.models.entities.User;
import com.joaolucas.shopjj.repositories.ProductRepository;
import com.joaolucas.shopjj.repositories.ReviewRepository;
import com.joaolucas.shopjj.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    private ReviewService underTest;
    private Review review;
    private Product product;
    private User user;

    @BeforeEach
    void setUp() {
        underTest = new ReviewService(reviewRepository, userRepository, productRepository);
        review = new Review();
    }

    @Test
    void itShouldFindAllReviews() {
        when(reviewRepository.findAll()).thenReturn(List.of(review));

        var result = underTest.findAll();

        assertThat(result).isEqualTo(List.of(new ReviewDTO(review)));
    }

    @Test
    void itShouldFindReviewById() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        var result = underTest.findById(1L);

        assertThat(result).isEqualTo(new ReviewDTO(review));
    }

    @Test
    void itShouldCreateReview() {
        product = new Product();
        user = new User();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(reviewRepository.save(review)).thenReturn(review);

        var result = underTest.create(1L, 1L, new ReviewDTO(review));

        assertThat(result).isNotNull();
        assertThat(product.getReviews().contains(review) && user.getReviews().contains(review)).isTrue();
    }

    @Test
    void itShouldUpdateReview() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        when(reviewRepository.save(review)).thenReturn(review);

        ReviewDTO toUpdate = new ReviewDTO(review);
        toUpdate.setCommentary("muito bom");

        var result = underTest.update(1L, toUpdate);
        assertThat(result.getCommentary()).isEqualTo("muito bom");
    }

    @Test
    void itShouldDeleteReview() {
        when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));

        underTest.delete(1L);

        verify(reviewRepository, times(1)).delete(review);
    }
}