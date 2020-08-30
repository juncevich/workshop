package guru.springframework.msscbeerservice.services.brewing;

import guru.springframework.msscbeerservice.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.events.NewInventoryEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import static guru.springframework.msscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;
import static guru.springframework.msscbeerservice.config.JmsConfig.NEW_INVENTORY_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate    jmsTemplate;


    @JmsListener(destination = BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        final var beerDto = event.getBeerDto();

        final var beer = beerRepository.getOne(beerDto.getId());
        beerDto.setQuantityOnHand(beer.getQuantityToBrew());
        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer " + beer.getMinOnHand() + " : QOH " + beerDto.getQuantityOnHand());

        jmsTemplate.convertAndSend(NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
