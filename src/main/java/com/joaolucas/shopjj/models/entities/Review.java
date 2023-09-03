package com.joaolucas.shopjj.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "commentary", length = 2000)
    private String commentary;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    public Review(){

    }

    public Review(Long id, Product product, Double rating, String commentary, User author) {
        this.id = id;
        this.product = product;
        this.rating = rating;
        this.commentary = commentary;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Review review = (Review) object;
        return Objects.equals(id, review.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", product=" + product +
                ", rating=" + rating +
                ", commentary='" + commentary + '\'' +
                ", author=" + author +
                '}';
    }
}
