package org.example.recommendation;

import org.example.entity.Book;
import org.example.entity.BorrowingRecord;
import org.example.entity.Patron;
import org.example.enums.BookAvailabilityStatus;

import java.util.*;

public class HistoryBasedRecommendationStrategy implements RecommendationStrategy{

    @Override
    public List<Book> recommend(
            Patron patron,
            Collection<Book> books) {

        List<Book> recommendations = new ArrayList<>();

        if (patron == null || books == null) {
            return recommendations;
        }

        Set<String> borrowedAuthors = new HashSet<>();

        for (BorrowingRecord record : patron.getBorrowingHistory()) {
            borrowedAuthors.add(record.getBook().getAuthor());
        }

        for (Book book : books) {

            if (book.getStatus() == BookAvailabilityStatus.AVAILABLE
                    && borrowedAuthors.contains(book.getAuthor())) {

                recommendations.add(book);
            }
        }

        return recommendations;
    }
}
