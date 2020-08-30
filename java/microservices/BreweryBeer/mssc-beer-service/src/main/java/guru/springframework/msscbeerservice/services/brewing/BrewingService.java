package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.mappers.BeerMapper;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static guru.springframework.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository       beerRepository;
    private final BeerInventoryService inventoryService;
    private final JmsTemplate          jmsTemplate;
    private final BeerMapper           beerMapper;


    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        final var beers = beerRepository.findAll();

        beers.forEach(beer -> {
            final var invQOH = inventoryService.getOnhandInventory(beer.getId());
            log.debug("Min OnHand is: " + beer.getMinOnHand());
            log.debug("Inventory is: " + invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                final var beerDto       = beerMapper.entityToDto(beer);
                final var brewBeerEvent = new BrewBeerEvent(beerDto);
                jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE, brewBeerEvent);
            }
        });
    }
}
