package org.example.recommendation;

import org.example.entity.Book;
import org.example.entity.Patron;

import java.util.Collection;
import java.util.List;

public interface RecommendationStrategy {


    List<Book> recommend(Patron patron, Collection<Book> books);
}
