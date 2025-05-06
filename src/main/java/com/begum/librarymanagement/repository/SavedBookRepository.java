package com.begum.librarymanagement.repository;
//repository katmanı veri erişiminin yapıldığı katmandır.
// ben burda bu dosyanın bu pakette olduğunu söylüyorum.
import com.begum.librarymanagement.entity.SavedBook;
//entity sınıfını içeri aktarmak içindir. rezervasyon tablosunu içeri aktarıyorum.
import com.begum.librarymanagement.entity.User;
//kullanici tablosunu içeri aktarıyorum
import com.begum.librarymanagement.entity.Book;
//kitap tablosunu içeri aktarıyorum
import org.springframework.data.jpa.repository.JpaRepository;
//Spring'in JpaRepository arayüzünü ekler. bu sayede veritabanı işlemleri otomatik hale gelir. sql yazmaya gerek kalmaz.
import java.util.List;
//bu sayede java List tipini tanıdı. findByUser ve findAll metotlarını kullandığımız için ikisinin de geri dönüş tipi List <T> olduğu için import etmemiz gerekti.
public interface SavedBookRepository extends JpaRepository<SavedBook, Long> {
    //Bu bir Java arayüzüdür, sınıf değil
    //SavedBookRepository	Arayüzün adı
    //extends JpaRepository<SavedBook, Long> SavedBook tablosunu temsil eder. Bu tablonun id alanı Long türünde.
    List<SavedBook> findByUser(User user);
    // belirli bir kullanıcıya ait tüm kayıtları getirir.
    boolean existsByUserAndBook(User user, Book book);
    // bu kullanıcı daha önce bu kitabı kaydetmiş mi? bunu kontrol eder. aynı kitap ikinci kez seçilmesin diye konuldu. true veya false döner. (true/false= booelan)
}

