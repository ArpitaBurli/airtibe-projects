package org.example.recommendation;

import org.example.entity.Book;
import org.example.entity.Patron;

import java.util.Collection;
import java.util.List;

public class RecommendationService {
    private RecommendationStrategy strategy;

    public RecommendationService(RecommendationStrategy strategy) {

        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Recommendation strategy cannot be null."
            );
        }

        this.strategy = strategy;
    }

    public void setStrategy(RecommendationStrategy strategy) {

        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Recommendation strategy cannot be null."
            );
        }

        this.strategy = strategy;
    }

    public List<Book> recommend(
            Patron patron,
            Collection<Book> books) {

        return strategy.recommend(patron, books);
    }
}
