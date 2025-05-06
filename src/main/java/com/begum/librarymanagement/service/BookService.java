package com.begum.librarymanagement.service;

import com.begum.librarymanagement.entity.Book;
import com.begum.librarymanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bu sınıf bir Spring servisidir
@RequiredArgsConstructor // final alanlar için constructor otomatik oluşturulur
public class BookService {

    private final BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByKitapAdi(String kitapAdi) {
        return bookRepository.findByKitapAdiContainingIgnoreCase(kitapAdi);
    }

    public List<Book> findByYazarAdi(String yazarAdi) {
        return bookRepository.findByYazarAdiContainingIgnoreCase(yazarAdi);
    }

    public List<Book> findByYayinlanmaYili(String yayinlanmaYili) {
        return bookRepository.findByYayinlanmaYili(yayinlanmaYili);
    }
}
