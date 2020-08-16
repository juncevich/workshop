package guru.springframework.msscbeer_inventory_service.web.mappers;

import guru.springframework.msscbeer_inventory_service.domain.BeerInventory;
import guru.springframework.msscbeer_inventory_service.web.model.BeerInventoryDto;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-31.
 */
@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDTO);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
