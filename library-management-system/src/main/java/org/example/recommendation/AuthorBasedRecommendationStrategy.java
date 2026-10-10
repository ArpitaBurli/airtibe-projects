package org.example.recommendation;

import org.example.entity.Book;
import org.example.entity.Patron;
import org.example.enums.BookAvailabilityStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthorBasedRecommendationStrategy implements RecommendationStrategy{
    private final String preferredAuthor;

    public AuthorBasedRecommendationStrategy(String preferredAuthor) {
        this.preferredAuthor = preferredAuthor;
    }

    @Override
    public List<Book> recommend(
            Patron patron,
            Collection<Book> books) {

        List<Book> recommendations = new ArrayList<>();

        if (books == null || preferredAuthor == null
                || preferredAuthor.isBlank()) {
            return recommendations;
        }

        for (Book book : books) {

            if (book.getStatus() == BookAvailabilityStatus.AVAILABLE
                    && book.getAuthor().equalsIgnoreCase(preferredAuthor)) {

                recommendations.add(book);
            }
        }

        return recommendations;
    }
}
