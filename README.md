#  Kütüphane Yönetim Sistemi

Bu proje, Spring Boot ve PostgreSQL kullanılarak geliştirilmiş bir **Kütüphane Yönetim Sistemidir**. Kitapların eklenmesi, silinmesi, aranması ve listelenmesini sağlayan RESTful API mimarisine dayalıdır.

---

##  Kullanılan Teknolojiler

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Data JPA**
- **Lombok**
- **PostgreSQL 17**
- **Gradle**
- **PgAdmin**
- **IntelliJ IDEA**

---

##  Proje Yapısı

```plaintext
com.begum.librarymanagement
│
├── controller      → HTTP isteklerini yöneten katman
├── service         → İş mantığını içeren servis katmanı
├── repository      → Veritabanı işlemleri
├── entity          → Veritabanı tablolarını temsil eden sınıflar
└── LibrarymanagementApplication.java → Uygulamanın giriş noktası

 Özellikler
 Kitap ekleme (POST /api/book/add)

 Kitap silme (DELETE /api/book/delete/{id})

 Kitap adına göre arama (GET /api/book/search/name)

 Yazara göre arama (GET /api/book/search/author)

 Yayınlanma yılına göre arama (GET /api/book/search/date)

 Tüm kitapları listeleme (GET /api/book/all)

