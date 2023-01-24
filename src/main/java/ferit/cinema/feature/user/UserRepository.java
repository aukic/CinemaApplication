package ferit.cinema.feature.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update User user set user.loyaltyPoints = user.loyaltyPoints + ?1 where user.id = ?2")
    void updateLoyaltyPoints(Double loyaltyPoints, Long userId);
}