package com.begum.librarymanagement.service;
// bu sınıf kullanıcı işlemleriyle ilgili iş mantığını ( businnes logic) barındırır.
//bu katman controller (dış dünya) ile repository (veri tabanı) arasındaki köprüdür
//bu sınıf projenin service katmanında yer alıyor.
import com.begum.librarymanagement.entity.User;
// user sınıfı veri tabanındaki kullanici tablosunun java karşılığıdır
import com.begum.librarymanagement.repository.UserRepository;
//bu sınıf veritabanına bağlanmak için UserRepository kullanır.
// Spring JPA sayesinde hazır CRUD ve özel sorgulara sahiptir.
// JPA Java da veritabanı ile nesneler arasında köprü kuran bir standarttır.
import lombok.RequiredArgsConstructor;
//@RequiredArgsConstructor anotasyonunu sağlar.
// Bu anotasyon sınıf içindeki final değişkenlerin constructor'ını otomatik olarak oluşturur.
import org.springframework.stereotype.Service;
//Bu sınıfın bir Spring Service olduğunu belirtir.
//Spring bu sınıfı otomatik olarak tarar ve @Autowired ile kullanılabilir hale getirir.
import java.util.Optional;
//Optional<T> sınıfı, null değerler için güvenli bir yapı sağlar.
//Boş gelen veri null yerine Optional.empty() olur.
@Service
// bu sınıf bir servis katmanıdır. Spring Boot otomatik tanır.
@RequiredArgsConstructor
//Aşağıdaki final alan bir constructor ekler
public class UserService {
    private final UserRepository userRepository;
    //final olduğu için bu değişken sonradan değiştirilemez, UserRepository veritabanıyla konuşur, @RequiredArgsConstructor sayesinde bu alan constructor ile enjekte edilir.
    public Optional<User> findByTcNo(String TcNo) {
        return userRepository.findByTcNo(TcNo);
        //findByTcNo metodu ile veritabanında tc_no ile eşleşen kullanıcıyı döner.
    }
    public boolean existsByTcNo(String TcNo) {
        return userRepository.existsByTcNo(TcNo);
        //veri tabanında bu Tc kimlik numaasıyla kayıtlı bir kullanıcı var mı? kontrol eder.
    }
    public User saveUser(User user) {
        return userRepository.save(user);
        //Verilen kullanıcı nesnesini veritabanına kaydeder (INSERT veya UPDATE yapar)
    }

}
