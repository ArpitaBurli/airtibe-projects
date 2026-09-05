package org.example.entity;

import org.example.enums.BookAvailabilityStatus;
import org.example.exception.BookNotFoundException;
import org.example.exception.BranchNotFoundException;
import org.example.exception.LibraryException;

import java.util.HashMap;
import java.util.Map;

public class Library {


    private final Map<String, Branch> branches = new HashMap<>();

    public void addBranch(Branch branch) {

        if (branch == null) {
            return;
        }

        branches.put(branch.getBranchId(), branch);
    }

    public Branch getBranch(String branchId) {

        Branch branch = branches.get(branchId);

        if (branch == null) {
            throw new BranchNotFoundException(
                    "Branch not found with ID: " + branchId
            );
        }

        return branch;
    }

    public void transferBook(
            String isbn,
            String fromBranchId,
            String toBranchId) {

        if (fromBranchId.equals(toBranchId)) {
            throw new LibraryException(
                    "Book cannot be transferred to the same branch."
            );
        }

        Branch fromBranch = getBranch(fromBranchId);
        Branch toBranch = getBranch(toBranchId);

        Book book = fromBranch.getBook(isbn);

        if (book == null) {
            throw new BookNotFoundException(
                    "Book not found in branch " + fromBranchId
                            + " with ISBN: " + isbn
            );
        }

        if (book.getStatus() == BookAvailabilityStatus.NOT_AVAILABLE) {
            throw new LibraryException(
                    "Book cannot be transferred because it is currently borrowed: "
                            + book.getTitle()
            );
        }

        fromBranch.removeBook(isbn);
        toBranch.addBook(book);
    }
    }

