package guru.springframework.mssc_beer_order_service.web.mappers;


import guru.springframework.mssc_beer_order_service.domain.BeerOrderLine;
import guru.springframework.mssc_beer_order_service.web.model.BeerOrderLineDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}
