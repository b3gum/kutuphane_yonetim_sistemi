package com.begum.librarymanagement.controller;
//bu dosya controller katmanında (kullanıcı isteklerini (API isteklerini) yöneten katmandır.)
// API = uygulamalar arasında iletişim kurmak için yazılmış bir arayüzdür.
// mesela hava durumu için uygulama meteorolojinin apisine bugün ki hava nasıl diye istek gönderir vb.
// APIler Json'la konuşur çünkü hafiftir her dilde okunurlar mesela ({tc no: "1233"}bu bir json formatıdır
import com. begum.librarymanagement.entity.User;
// kullanıcı verisini alır
import com.begum.librarymanagement.service.UserService;
//kullanıcı işlemleri için servis katmanına erişir.
import lombok.RequiredArgsConstructor;
//final değişkenler için constructor'ı otomatik oluşturur.
import org.springframework.http.ResponseEntity;
// HTTP yanıtlarını yönetir.
// spring boot projelerinde kullanıcıya yanıt dönerken sadece VERİ (JSON) dönmek YETMEZ.
//aynı zamanda yanıtın durumu (başarılı mı ? hatalı mı?) da bildirilmelidir.
// veri json veya başka bir veri tipinde cevap döner HTTP staus kodunu belirler (ResponseEntity)hatalı mı? başarılı mı? ona göre kod döner.
// mesela ResponseEntity.ok ("başarılı") = HTTP 200 döner vb.
// API'nin doğru çalışıp çalışmadığını HTTP kodlarıyla gösterebiliyoruz.
import org.springframework.web.bind.annotation.*;
// @RestController, @RequestMapping, @PostMapping gibi spring web anotasyonlarını getirir.
@RestController
// bu sınıf bir REST API'dir. JSON döner. JSON (verilerin standart,sade,okunabilir formatta gönderilmesini sağlar)
//spring boot'ta bir sınıfı RESTful Web Service (API) haline getiren özel bir anotasyondur.
// dış dünyadan HTTP istekleri (GET,POST,PUT,DELETE) alır. Bir cevap (response)üretir.
@RequiredArgsConstructor
// final alanlar (userService) için constructor otomatik oluşur.
@RequestMapping("/api/user")
//Bu controller içindeki tüm URL'ler /api/user ile başlar
public class UserController {
    private final UserService userService;
    //UserService, kullanıcı kayıt ve login işlemlerini yapar
    //final olduğu için güvenlidir ve sadece constructor ile atanır.
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user)
    // api/user/register adresine POST isteği gelince bu metot çalışır.
    //istek gövdesindeki JSON verisi User nesnesine dönüşür
    {
        if(userService.existsByTcNo(user.getTcNo())) {
            return ResponseEntity.badRequest().body("Bu TC numarası ile kayıt zaten bulunmaktadır.");
        }
        // kullanıcın TC numarası zaten varsa hata mesajı gönderir. (400 Bad Request)
        userService.saveUser(user);
        return ResponseEntity.ok("Kayıt başarılı");
    }
    // TC yoksa kullanıcıyı kaydeder ve 200 OK cevabı döner.
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user)
    // /api/user/login adresine POST isteği gelince bu metot çalışır.
    {
        return userService.findByTcNo(user.getTcNo())
                //kullanıcının Tc'sine göre veri aranır
                .filter(u ->u.getSifre().equals(user.getSifre()))
                //eğer kullanıcı bulunduysa şifresi kontrol edilir.
                //getSifre () ile veritabanındaki kayıtlı şifre, kullanıcının girdiği şifreyle karışılaştırılır.
                .map(u ->ResponseEntity.ok("Giriş başarılı"))
                // şifre doğruysa 200 OK yanıtı ve "giriş başarılı" mesajı döner
                .orElse(ResponseEntity.status(401).body("Hatalı TC veya şifre!"));
        // eğer kullanıcı bulunamazsa veya şifre yanlışsa 401 Unauthorized hatası döner.
    }
}
//
/* kısacası kodun genelinde ;
yeni kullanıcı kaydı yapılır veya hata verir (/register)
kullanıcı tc şifresi doğruysa giriş yapılır, aksi halde 401 hatası (/login)
API uçlarını net ayırdık,
giriş güvenliğini sağladık(şifre kontrolü)
Spring'in ResponseEntity sınıfıyla HTTP statü kodlarını düzgün yönetiyoruz.
* yeni kullanıcı kayıt ediliyor
* kullanıcı giriş yapılabiliyor
*yanlış girişlerde doğru hata mesajı dönebiliyor
*tüm işlemler JSON ile yapılıyor
 */