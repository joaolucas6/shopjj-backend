package com.joaolucas.shopjj.models.dto;

import com.joaolucas.shopjj.models.entities.Review;

public class ReviewDTO {

    private Long id;
    private Long productId;
    private Double rating;
    private String commentary;
    private Long authorId;

    public ReviewDTO(){

    }

    public ReviewDTO(Review review){
        setId(review.getId());
        setProductId(review.getId());
        setRating(review.getRating());
        setCommentary(review.getCommentary());
        setAuthorId(review.getAuthor().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
