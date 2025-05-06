package com.begum.librarymanagement.controller;
/*Bu satır dosyanın ait olduğu package’i tanımlar.
Java’da paketler dosyaları düzenlemek için kullanılır.
Bu sınıf controller katmanında yer alıyor.
(API giriş noktası).
 */
import com.begum.librarymanagement.entity.Book;
/*Book sınıfını (entity sınıfı) projeye dahil eder.
Bu sınıf veritabanındaki kitap tablosunun Java karşılığıdır.
 */
import com.begum.librarymanagement.service.BookService;
/*Servis katmanındaki BookService sınıfı içe aktarılıyor.
Business logic (iş mantığı) burada yer alır.
 */
import lombok.RequiredArgsConstructor;
/* Sınıftaki final değişkenler için constructor otomatik oluşturur.
 Kod yazmadan dependency injection yapılır.
 */
import org.springframework.web.bind.annotation.*;
/* Spring Web MVC anotasyonlarını getirir:
@RestController, @RequestMapping, @PostMapping, @GetMapping, vs.
 */
import org.springframework.http.ResponseEntity;
/*HTTP cevaplarını yönetmek için ResponseEntity sınıfı kullanılır.
Status kod + veri içerebilir. Örn: 200 OK + JSON veri.
 */
import java.util.List;
/* Çoklu kitapları (Book) dönebilmek için Java'nın List koleksiyonu kullanılır.
 */
@RestController
/* Bu sınıf bir REST API controller’dır.
Otomatik olarak JSON veri döner.
 */
@RequestMapping("/api/book")
/* Bu controller’daki tüm endpointler /api/book ile başlar.
Örn: /api/book/add, /api/book/search/name, vs.
Endpoint, bir API’nin dış dünyaya açılan "giriş noktasıdır".
 Bir istemcinin (örneğin bir frontend uygulaması, mobil uygulama ya da Postman gibi bir araç)
  belirli bir işlemi gerçekleştirmek için eriştiği URL adresidir.
  Bir endpoint, bir URL + HTTP method birleşimidir ve bir sunucudaki işlemi temsil eder.
 */
@RequiredArgsConstructor
/*final BookService bookService; alanı için constructor otomatik oluşturulur.
Manual olarak constructor yazmana gerek kalmaz.
 */
public class BookController {
/* Controller sınıfının tanımı başlıyor. Bu sınıf dış dünyadan gelen kitap isteklerini karşılar.
 */
    private final BookService bookService;
    /*BookService servisine bağımlılık tanımıdır.
Final olduğu için sadece constructor ile atanabilir, dışarıdan değiştirilmez.
     */

    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return ResponseEntity.ok(savedBook);
    }
/*POST tipi bir istek /api/book/add adresine geldiğinde bu metot çalışır.
POST, HTTP protokolünde kullanılan bir istek türüdür (HTTP Method) ve sunucuya veri göndermek için kullanılır.
 */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {//URL'deki {id} parametresi @PathVariable ile alınır.
        bookService.deleteBookById(id); // verilen id ye sahip kitap silinir
        return ResponseEntity.ok("Kitap silindi !");
    }
/*DELETE isteği /api/book/delete/{id} adresine gelince çalışır.
 */
    @GetMapping("/search/name") // get metodu veri alır
    public ResponseEntity<List<Book>> searchByKitapAdi(@RequestParam String kitapAdi) { // url parametresi (kitapAdi) alınır.
        List<Book> books = bookService.findByKitapAdi(kitapAdi); // kitapAdi içeren tüm kitaplar bulunur.
        return ResponseEntity.ok(books); // kitap listesi HTTP 200 OK ile JSON olarak dönülür.
    }
// veri istemek (okumak) veri url'de dir. veri değiştirilemez. (güvenli)
    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> searchByYazarAdi(@RequestParam String yazarAdi) {
        List<Book> books = bookService.findByYazarAdi(yazarAdi);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/date")
    public ResponseEntity<List<Book>> searchByYayinlanmaYili(@RequestParam String yayinlanmaYili) {
        // serviste findByYayinlanmaYili() metodu çağrılır.
        List<Book> books = bookService.findByYayinlanmaYili(yayinlanmaYili);
        return ResponseEntity.ok(books); // sonuçlar json olarak döner.
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBook() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
}
/* endpoint genel bilgi
 POST /api/book/add -> yeni kitap ekler
 GET /api/book/all -> bütün kitapları listeler
 DELETE /api/book/delete/1 -> ID'si 1 olan kitabı siler
 bu taraf endpointlerdir -> bu taraf endpointlerin açıklamasıdır.
 Endpoint, bir HTTP isteğinin hedefidir
 genelde bir API'nin dış dünyaya sunduğu belirli bir hizmeti temsil eder.
 */