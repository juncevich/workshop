package guru.springframework.msscbeerservice.mappers;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto entityToDto(Beer beer);

    Beer dtoToEntity(BeerDto beerDto);
}
