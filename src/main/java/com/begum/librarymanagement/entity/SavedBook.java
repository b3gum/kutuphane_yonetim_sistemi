package com.begum.librarymanagement.entity;
import jakarta.persistence.*;
//JPA anotasyonları (@entity @ıd vb.) JPA(nesne yönelimli (oop) şekilde veritabanı işlemleri yapmamı sağlayan standarttır.)
import lombok.*;
// kod tekrarını azaltmak için getter/ settter vb.otomatize  eder.
import java.time.LocalDate;
// java tarih sınıfı
@Entity
// bu sınıf veri tablosuna karşılık gelen bir entity (jpa anatasyonu) olduğu anlamına gelir. spring boot bu sınıf için bir tablo yönetimi başlatır.
//entity eklediğimde spring boot bir veri tabanı tablosu oluşturur veya eşleştirir.
@Table(name = "rezervasyon")
@Data
//lomboktan gelir. get ve set metodları(sınıfın özelliklerine dışarıdan güvenli bir şekilde erişmek için kullanırız,equals() ve hashcode()(nesneleri karşılaştırmak ve koleksiyonlarda (set,map)düzgün çalışmak için kullanılır),toStirng() (bir nesneyi yazdırmak istediğinde veya log'lamak istediğinde, nesneyi string olarak temsil eder.)
@NoArgsConstructor
//parametresiz kurucu metod (parametresiz (boş) bir constructor (kurucu metod) otomatik oluşturur.)(spring jpa gibi kütüphaneler bazı işlemleri yaparken (entityleri yüklerken vb.)boş constructor ister.)
@AllArgsConstructor
//tüm alanları alan kurucu metod (tüm alanları parametre alan bir constructor oluşturur.)(tek satırda tüm veriyi alıp hızlıca nesne oluşturursun.)
@Builder
// (java da karmaşık nesneleri daha okunabilir, daha esnek ve güvenli bir şekilde oluşturmak için kullanılan bir tasarım desenidir.)

public class SavedBook {
    @Id
    // bu alan birincil anahtar (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // veritabanında otomatik olarak artar
    private Long id; // otomatik artan birincil anahtar (her kullanıcıya benzersiz bir id verilir)
    @ManyToOne //	Her rezervasyon bir kullanıcıya aittir
    @JoinColumn(name = "kullanici_adi",referencedColumnName = "id",nullable = false) //veri tabanındaki sütunun adı (kullanici_adi) user tablosunun idsini referans gösterir (kullanıcı)
    private User user; //rezervasyonun ait olduğu kullanıcı nesnesi
    @ManyToOne // her rezervasyon bir kitaba aittir
    @JoinColumn (name = "kitap_adi",referencedColumnName = "id",nullable = false) // boş bırakılamaz veri tabanındaki sütunun adı (kitap_adi) book tablosunun id sine referans gösterir
    private Book book; // rezervasyonun ait olduğu kitap nesnesi
    @Column (name = "tarih") // veri tabanındaki tarih sütunu
    private LocalDate tarih;
    public SavedBook(Long id, User user, Book book) { //ben buraya kendim constructor ekledim çünkü lombok çalışmadı anlamadım ?? (SavedBookController için)
        this.id = id;
        this.user = user;
        this.book = book;
    }

}

