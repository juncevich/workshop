package guru.springframework.msscbeer_inventory_service.services;


import guru.springframework.commons.events.NewInventoryEvent;
import guru.springframework.msscbeer_inventory_service.domain.BeerInventory;
import guru.springframework.msscbeer_inventory_service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import static guru.springframework.msscbeer_inventory_service.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Slf4j
@RequiredArgsConstructor
@Service
public class NewInventoryListener {
    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {

        log.debug("Got inventory: " + event.toString());
        final var beerDto = event.getBeerDto();
        final var beerInventory = BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .build();
        beerInventoryRepository.save(beerInventory);

    }
}
