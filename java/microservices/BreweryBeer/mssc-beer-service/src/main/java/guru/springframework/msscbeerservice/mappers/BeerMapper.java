package guru.springframework.msscbeerservice.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE, componentModel = "spring", uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto entityToDto(Beer beer);

    BeerDto entityToDtoWithInventory(Beer beer);

    Beer dtoToEntity(BeerDto beerDto);

}
