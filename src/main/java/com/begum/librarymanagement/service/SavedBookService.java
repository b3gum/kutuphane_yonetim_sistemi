package com.begum.librarymanagement.service;
// bu sınıf spring boot projesinin service katmanında bulunur.
// bu katman, iş mantığını (business logic) içerir.
import com.begum.librarymanagement.entity.SavedBook;
//rezrevasyon tablosunun java karşılığı
import com.begum.librarymanagement.entity.User;
//kullanıcı tablosu
import com.begum.librarymanagement.entity.Book;
// kitap tablosu
import com.begum.librarymanagement.repository.SavedBookRepository;
// kullanıcının seçtiği kitaplarla ilgili veritabanı işlemleri için kullanılır.
// jpa sayesinde özel sql yazmadan işlemleri gerçekleştirir.
import lombok.RequiredArgsConstructor;
// final alanlar için constructor oluşturur.
//dependency ınjection işlemleri kolaylaşır.
// bir sınıfın ihtiyaç duyduğu başka sınıfların (dependencylerin) dışarıdan verilmesi (enjekte edilmesi) işlemidir.
import org.springframework.stereotype.Service;
// bu sınıfı spring service olarak tanımlar.
// spring otomatik olarak bu sınıfı @Autowired ile kullanıma açar
import java.util.List;
// çoklu veri dödürebilmek için (list)
@Service
// bu sınıf bir servis katmanıdır.
@RequiredArgsConstructor
//final değişkenler için constructor otomatik oluşturulur
public class SavedBookService {
    private final SavedBookRepository savedBookRepository;
    //final dediğimiz için constructor ile atanır ve değiştirilemez, SavedBookRepository rezervasyonlarla ilgili veritabanı işlemlerini yapar.
    public List<SavedBook> getUserSavedBooks(User user) {
        return savedBookRepository.findByUser(user);
    }
    // veritabanında kullanıcıya ait tüm kayıtları döndürür(user)
    public boolean isBookAlreadySaved(User user, Book book) {
        return savedBookRepository.existsByUserAndBook(user, book);
    }
    //aynı kullanıcı aynı kitabı daha önce kaydetmiş mi ? bunu sorguladığımız metoddur.
    public SavedBook save(SavedBook savedBook) {
        return savedBookRepository.save(savedBook);
    }
    // yeni bir kitap seçimini veri tabanına kaydeder.
    public boolean existsByUserAndBook(User user, Book book) {
        return savedBookRepository.existsByUserAndBook(user, book);
    }
    public List<SavedBook> findByUser(User user) {
        return savedBookRepository.findByUser(user); //veri tabanında belli bir kullanıcıya ait tüm kaydedilmiş kitapları döndürür
    }
    //savedBookRepository veri tabanı katmanı
    //findBUser JPA(spring data) tarafından otomatik tanınan bir sorgudur.
}
