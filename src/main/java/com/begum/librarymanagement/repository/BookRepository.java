package com.begum.librarymanagement.repository;
//bu dosya kitap veri tabanı tablosuyla konuşan arayüzdür.
//bu sınıf, java projesindeki repository paketine aittir. veri erişim katmanını temsil eder.
import com.begum.librarymanagement.entity.Book;
// book sınıfını içeri aktarır. BookRepository, book nesnesiyle çalışır.
//kitap tablosunun java karşılığıdır (@Entity)
import org.springframework.data.jpa.repository.JpaRepository;
//Spring’in JPA için sağladığı temel CRUD arayüzüdür.
//Onun sayesinde: save(), findAll() ,findById() ,deleteById() gibi metotlar otomatik tanımlanır, ekstra yazmana gerek kalmaz.
import java.util.List;
//Özel arama metotları birden fazla kitap döndüreceği için liste kullanıyoruz
public interface BookRepository extends JpaRepository<Book, Long> {
    //Bu bir Java arayüzüdür, sınıf değil
    //BookRepository	Arayüzün adı
    //extends JpaRepository<Book, Long>	Book türünde nesneler için CRUD işlemleri sağla ve id alanı Long türünde
     List<Book> findByYazarAdiContainingIgnoreCase(String yazarAdi);
     // SELECT * FROM kitap WHERE LOWER (yazar_adi) LIKE LOWER (%arananKelime%); anlamına gelen sql sorgusunun metodudur. büyük/küçük harf duyarlılığı olmasın diye ignoreCase dedik.
    List<Book> findByKitapAdiContainingIgnoreCase(String kitapAdi);
    //Kitap adına göre filtreler, Büyük küçük harf farkı gözetmez
    List<Book> findByYayinlanmaYili(String yayinlanmaYili);
}
