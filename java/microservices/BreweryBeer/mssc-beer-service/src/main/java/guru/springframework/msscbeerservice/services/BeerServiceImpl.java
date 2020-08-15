package guru.springframework.msscbeerservice.services;

import guru.springframework.msscbeerservice.mappers.BeerMapper;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.web.controller.BeerNotFound;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper     beerMapper;


    @Override
    public BeerDto getById(UUID beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::entityToDto)
                .orElseThrow(BeerNotFound::new);
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return Stream.of(beerDto)
                .map(beerMapper::dtoToEntity)
                .map(beerRepository::save)
                .map(beerMapper::entityToDto)
                .findAny()
                .orElseThrow();
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        final var beer = beerRepository.findById(beerId).orElseThrow(BeerNotFound::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        final var savedBeer = beerRepository.save(beer);

        return beerMapper.entityToDto(savedBeer);
    }
}
