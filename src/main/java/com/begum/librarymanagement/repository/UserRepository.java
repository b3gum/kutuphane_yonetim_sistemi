package com.begum.librarymanagement.repository;
//repository bir sınıfın veya arayüzün "veri tabanı ile ilgili işlemleri yapmaktan sorumlu" olduğunu gösterir.
//CRUD (create,read,update,delete)= veri tabanı işlemleri
// veri tabanı kodunu service veya controller katmanından izole eder.
import com.begum.librarymanagement.entity.User;
// user sınıfını (entity) import ettik.
//UserRepository bu sınıfla çalışacak.
import org.springframework.data.jpa.repository.JpaRepository;
//Spring'in JPA altyapısını içeri aktarıyoruz.
// Bu sayede CRUD işlemleri (save(),findById() gibi işlemleri otomatik hale getiriyoruz.
// JPA, java'da veritabanı ile nesneler arasında köprü kuran bir standarttır.
import java.util.Optional;
//bir şey bulunursa onu döner, bulunmazsa Optinal.empty () verir. (NullPointerException riskini azaltır.)

public interface UserRepository extends JpaRepository<User, Long> {
 //Ben user nesnesi için bir repository istiyorum, ve bu nesnelerin ID'si Long tipinde. (bir interface (arayüz) yapıyorum. sınıf değil sadece tanımlar)
    // bu satır sayesinde ben hiçbir SQL yazmadan userRepository.findAll(),userRepository.findById() gibi metotları kullanabilirim.
    Optional<User> findByTcNo(String tcNo);
    //özel bir metottur. Spring'e User tablosunda tc_no alanı şu olan kullanıcıyı bul.derim.(SELECT * FROM kullanici WHERE tc_no =?; ve sonucu Optinal<User> olarak döner. null olabilir ihtimaline karşı.
    boolean existsByTcNo(String tcNo);
    //bu da özel bir sorgudur. Veritabanında bu Tc kimlik numarasına sahip bir kullanıcı var mı? sorgusudur. Tc zaten varsa kayıt yapılmaz.
}
