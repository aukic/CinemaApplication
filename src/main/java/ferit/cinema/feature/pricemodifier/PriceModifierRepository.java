package ferit.cinema.feature.pricemodifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceModifierRepository extends JpaRepository<PriceModifier, Long> {

}
