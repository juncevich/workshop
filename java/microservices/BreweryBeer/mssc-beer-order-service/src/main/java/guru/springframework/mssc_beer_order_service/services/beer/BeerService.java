package guru.springframework.mssc_beer_order_service.services.beer;

import guru.springframework.mssc_beer_order_service.web.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}
