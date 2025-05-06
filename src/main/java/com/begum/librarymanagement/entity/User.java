package com.begum.librarymanagement.entity;
import jakarta.persistence.*;
//JPA anotasyonları (@entity @ıd vb.) JPA(nesne yönelimli (oop) şekilde veritabanı işlemleri yapmamı sağlayan standarttır.)
import lombok.*;
// kod tekrarını azaltmak için getter/ settter vb.otomatize  eder.
import java.time.LocalDate;
//doğum tarihleri için java tarih sınıfı
@Entity
// bu sınıf veri tablosuna karşılık gelen bir entity (jpa anatasyonu) olduğu anlamına gelir. spring boot bu sınıf için bir tablo yönetimi başlatır.
//entity eklediğimde spring boot bir veri tabanı tablosu oluşturur veya eşleştirir.
@Table(name = "kullanici")
@Data //lomboktan gelir. get ve set metodları(sınıfın özelliklerine dışarıdan güvenli bir şekilde erişmek için kullanırız,equals() ve hashcode()(nesneleri karşılaştırmak ve koleksiyonlarda (set,map)düzgün çalışmak için kullanılır),toStirng() (bir nesneyi yazdırmak istediğinde veya log'lamak istediğinde, nesneyi string olarak temsil eder.)
@AllArgsConstructor//tüm alanları alan kurucu metod (tüm alanları parametre alan bir constructor oluşturur.)(tek satırda tüm veriyi alıp hızlıca nesne oluşturursun.)
@NoArgsConstructor//parametresiz kurucu metod (parametresiz (boş) bir constructor (kurucu metod) otomatik oluşturur.)(spring jpa gibi kütüphaneler bazı işlemleri yaparken (entityleri yüklerken vb.)boş constructor ister.)
@Builder // (java da karmaşık nesneleri daha okunabilir, daha esnek ve güvenli bir şekilde oluşturmak için kullanılan bir tasarım desenidir.)
public class User
{
    @Id // bu alan birincil anahtar (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // veritabanında otomatik olarak artar
    private Long id; // otomatik artan birincil anahtar (her kullanıcıya benzersiz bir id verilir)
    @Column (name = "tc_no",nullable = false, unique = true) // veri tabanında bulunan adı "tc_no", nullable = false (boş bırakılamaz), unique = true (aynı tc'den sadece 1 tane olabilir (benzersiz)
    private String tcNo; // private olduğu için doğrudan erişilemez oldu get ve set metodları ile erişebilirim. ve string bir yapı tutuyor içinde (metin verisi) tcNo ise sınıfın içinde kullanılacak değişken adını temsil eder.
    @Column(name = "sifre", nullable = false) // şifre de boş bırakılamaz yaptık.
    private String sifre;
    @Column (name = "ad")
    private String ad;
    @Column(name = "soyad")
    private String soyad;
    @Column (name = "dogum_tarihi")
    private String dogumTarihi;
    @Column (name = "rol")
    private String rol;
}
