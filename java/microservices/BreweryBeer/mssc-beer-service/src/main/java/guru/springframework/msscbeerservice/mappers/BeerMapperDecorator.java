package guru.springframework.msscbeerservice.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class BeerMapperDecorator implements BeerMapper {
    @Autowired
    private BeerInventoryService beerInventoryService;
    @Autowired
    @Qualifier("delegate")
    private BeerMapper           mapper;

    @Override
    public BeerDto entityToDto(Beer beer) {
        return mapper.entityToDto(beer);
    }

    @Override
    public BeerDto entityToDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.entityToDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer dtoToEntity(BeerDto beerDto) {
        return mapper.dtoToEntity(beerDto);
    }
}
