package com.begum.librarymanagement.controller;
import com.begum.librarymanagement.entity.Book;
import com.begum.librarymanagement.entity.SavedBook;
import com.begum.librarymanagement.entity.User;
import com.begum.librarymanagement.service.BookService;
import com.begum.librarymanagement.service.UserService;
import com.begum.librarymanagement.service.SavedBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saved")
public class SavedBookController {
    private final UserService userService;
    private final BookService bookService;
    private final SavedBookService savedBookService;
    @PostMapping("/add")
    public ResponseEntity<String> saveBookForUser(@RequestParam String tcNo, @RequestParam Long bookId) {
        Optional<User> userOpt = userService.findByTcNo(tcNo);
        Optional<Book> bookOpt = bookService.findById(bookId);
        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Kullanıcı veya kitap bulunamadı");
        }
        User user = userOpt.get();
        Book book = bookOpt.get();
        if (savedBookService.existsByUserAndBook(user, book)) {
            return ResponseEntity.badRequest().body("Bu kitap zaten kaydedilmiştir.");
        }
        SavedBook savedBook = new SavedBook(null,user,book);
        savedBookService.save(savedBook);
        return ResponseEntity.ok("kitap başarıyla kaydedildi.");
    }
    @GetMapping("/list")
    public ResponseEntity<List<SavedBook>> getSavedBooksForUser(@RequestParam String tcNo) {
        Optional<User> userOpt = userService.findByTcNo(tcNo);
    if (userOpt.isEmpty()) {
        return ResponseEntity.badRequest().build();
    }
    List<SavedBook>savedBooks = savedBookService.findByUser(userOpt.get());
    return ResponseEntity.ok(savedBooks);
    }

}
