package com.begum.librarymanagement.entity;
import jakarta.persistence.*;
//JPA anotasyonları (@entity @ıd vb.) JPA(nesne yönelimli (oop) şekilde veritabanı işlemleri yapmamı sağlayan standarttır.)
import lombok.*;
// kod tekrarını azaltmak için getter/ settter vb.otomatize  eder.
@Entity
// bu sınıf veri tablosuna karşılık gelen bir entity (jpa anatasyonu) olduğu anlamına gelir. spring boot bu sınıf için bir tablo yönetimi başlatır.
//entity eklediğimde spring boot bir veri tabanı tablosu oluşturur veya eşleştirir.
@Table(name = "kitap")
@Data
//lomboktan gelir. get ve set metodları(sınıfın özelliklerine dışarıdan güvenli bir şekilde erişmek için kullanırız,equals() ve hashcode()(nesneleri karşılaştırmak ve koleksiyonlarda (set,map)düzgün çalışmak için kullanılır),toStirng() (bir nesneyi yazdırmak istediğinde veya log'lamak istediğinde, nesneyi string olarak temsil eder.)
@NoArgsConstructor
//parametresiz kurucu metod (parametresiz (boş) bir constructor (kurucu metod) otomatik oluşturur.)(spring jpa gibi kütüphaneler bazı işlemleri yaparken (entityleri yüklerken vb.)boş constructor ister.)
@AllArgsConstructor
//tüm alanları alan kurucu metod (tüm alanları parametre alan bir constructor oluşturur.)(tek satırda tüm veriyi alıp hızlıca nesne oluşturursun.)
@Builder
// (java da karmaşık nesneleri daha okunabilir, daha esnek ve güvenli bir şekilde oluşturmak için kullanılan bir tasarım desenidir.)


public class Book {
    @Id // bu alan birincil anahtar (primary key)
    @GeneratedValue(strategy = GenerationType.IDENTITY)// veritabanında otomatik olarak artar
    private Long id;// otomatik artan birincil anahtar (her kullanıcıya benzersiz bir id verilir)
    @Column (name = "kitap_adi",nullable = false) // boş bırakılamaz
    private String kitapAdi;
    @Column(name = "yazar_adi",nullable = false) // boş bırakılamaz
    private String yazarAdi;
    @Column(name = "yayinlanma_yili")
    private String yayinlanmaYili;
}
