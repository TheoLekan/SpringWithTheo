package com.springwiththeo.week17.advanced_jpa;

import com.springwiththeo.week17.advanced_jpa.model.Author;
import com.springwiththeo.week17.advanced_jpa.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RunnerService {

    private final AuthorRepository authorRepository;

    @Transactional
    public void lazyAuthorBooksAndSales() {
        System.out.println("---- Lazy Load Example ----");
        assert authorRepository != null;
        Author loadedWithNoBooksAndSales = authorRepository.findById(1L).orElseThrow();
        System.out.println("Books size (lazy): " + loadedWithNoBooksAndSales.getBooks().size());
    }

    @Transactional
    public void entityGraphAuthorBooksAndSales() {
        System.out.println("---- Entity Graph Example ----");
        assert authorRepository != null;
        Author loadedWithBooksAndSales = authorRepository.findAllWithBooksAndSales().getFirst();
        System.out.println("Books size (eager): " + loadedWithBooksAndSales.getBooks().size());
    }


}
