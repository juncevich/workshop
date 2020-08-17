package guru.springframework.msscbeerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BeerDto {

    @Null
    private UUID           id;
    @Null
    private Integer        version;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;
    @NotBlank
    @Size(min = 3, max = 100)
    private String         beerName;
    @NotNull
    private BeerStyleEnum  beerStyle;

    @NotNull
    private String upc;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    @Positive
    private Integer quantityOnHand;

    public BeerDto(@Null UUID id, @Null Integer version, @Null OffsetDateTime createdDate, @Null OffsetDateTime lastModifiedDate, @NotBlank @Size(min = 3, max = 100) String beerName, @NotNull BeerStyleEnum beerStyle, @NotNull String upc, @Positive @NotNull BigDecimal price, @Positive Integer quantityOnHand) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.price = price;
        this.quantityOnHand = quantityOnHand;
    }

    public BeerDto() {
    }

    public static BeerDtoBuilder builder() {
        return new BeerDtoBuilder();
    }

    public @Null UUID getId() {
        return this.id;
    }

    public void setId(@Null UUID id) {
        this.id = id;
    }

    public @Null Integer getVersion() {
        return this.version;
    }

    public void setVersion(@Null Integer version) {
        this.version = version;
    }

    public @Null OffsetDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(@Null OffsetDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public @Null OffsetDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(@Null OffsetDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public @NotBlank @Size(min = 3, max = 100) String getBeerName() {
        return this.beerName;
    }

    public void setBeerName(@NotBlank @Size(min = 3, max = 100) String beerName) {
        this.beerName = beerName;
    }

    public @NotNull BeerStyleEnum getBeerStyle() {
        return this.beerStyle;
    }

    public void setBeerStyle(@NotNull BeerStyleEnum beerStyle) {
        this.beerStyle = beerStyle;
    }

    public @NotNull String getUpc() {
        return this.upc;
    }

    public void setUpc(@NotNull String upc) {
        this.upc = upc;
    }

    public @Positive @NotNull BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(@Positive @NotNull BigDecimal price) {
        this.price = price;
    }

    public @Positive Integer getQuantityOnHand() {
        return this.quantityOnHand;
    }

    public void setQuantityOnHand(@Positive Integer quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof BeerDto)) return false;
        final BeerDto other = (BeerDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id  = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$version  = this.getVersion();
        final Object other$version = other.getVersion();
        if (this$version == null ? other$version != null : !this$version.equals(other$version)) return false;
        final Object this$createdDate  = this.getCreatedDate();
        final Object other$createdDate = other.getCreatedDate();
        if (this$createdDate == null ? other$createdDate != null : !this$createdDate.equals(other$createdDate))
            return false;
        final Object this$lastModifiedDate  = this.getLastModifiedDate();
        final Object other$lastModifiedDate = other.getLastModifiedDate();
        if (this$lastModifiedDate == null ? other$lastModifiedDate != null : !this$lastModifiedDate.equals(other$lastModifiedDate))
            return false;
        final Object this$beerName  = this.getBeerName();
        final Object other$beerName = other.getBeerName();
        if (this$beerName == null ? other$beerName != null : !this$beerName.equals(other$beerName)) return false;
        final Object this$beerStyle  = this.getBeerStyle();
        final Object other$beerStyle = other.getBeerStyle();
        if (this$beerStyle == null ? other$beerStyle != null : !this$beerStyle.equals(other$beerStyle)) return false;
        final Object this$upc  = this.getUpc();
        final Object other$upc = other.getUpc();
        if (this$upc == null ? other$upc != null : !this$upc.equals(other$upc)) return false;
        final Object this$price  = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$quantityOnHand  = this.getQuantityOnHand();
        final Object other$quantityOnHand = other.getQuantityOnHand();
        if (this$quantityOnHand == null ? other$quantityOnHand != null : !this$quantityOnHand.equals(other$quantityOnHand))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof BeerDto;
    }

    public int hashCode() {
        final int    PRIME  = 59;
        int          result = 1;
        final Object $id    = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $version = this.getVersion();
        result = result * PRIME + ($version == null ? 43 : $version.hashCode());
        final Object $createdDate = this.getCreatedDate();
        result = result * PRIME + ($createdDate == null ? 43 : $createdDate.hashCode());
        final Object $lastModifiedDate = this.getLastModifiedDate();
        result = result * PRIME + ($lastModifiedDate == null ? 43 : $lastModifiedDate.hashCode());
        final Object $beerName = this.getBeerName();
        result = result * PRIME + ($beerName == null ? 43 : $beerName.hashCode());
        final Object $beerStyle = this.getBeerStyle();
        result = result * PRIME + ($beerStyle == null ? 43 : $beerStyle.hashCode());
        final Object $upc = this.getUpc();
        result = result * PRIME + ($upc == null ? 43 : $upc.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $quantityOnHand = this.getQuantityOnHand();
        result = result * PRIME + ($quantityOnHand == null ? 43 : $quantityOnHand.hashCode());
        return result;
    }

    public String toString() {
        return "BeerDto(id=" + this.getId() + ", version=" + this.getVersion() + ", createdDate=" + this.getCreatedDate() + ", lastModifiedDate=" + this.getLastModifiedDate() + ", beerName=" + this.getBeerName() + ", beerStyle=" + this.getBeerStyle() + ", upc=" + this.getUpc() + ", price=" + this.getPrice() + ", quantityOnHand=" + this.getQuantityOnHand() + ")";
    }

    public static class BeerDtoBuilder {
        private @Null                               UUID           id;
        private @Null                               Integer        version;
        private @Null                               OffsetDateTime createdDate;
        private @Null                               OffsetDateTime lastModifiedDate;
        private @NotBlank @Size(min = 3, max = 100) String         beerName;
        private @NotNull                            BeerStyleEnum  beerStyle;
        private @NotNull                            String         upc;
        private @Positive @NotNull                  BigDecimal     price;
        private @Positive                           Integer        quantityOnHand;

        BeerDtoBuilder() {
        }

        public BeerDto.BeerDtoBuilder id(@Null UUID id) {
            this.id = id;
            return this;
        }

        public BeerDto.BeerDtoBuilder version(@Null Integer version) {
            this.version = version;
            return this;
        }

        public BeerDto.BeerDtoBuilder createdDate(@Null OffsetDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public BeerDto.BeerDtoBuilder lastModifiedDate(@Null OffsetDateTime lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public BeerDto.BeerDtoBuilder beerName(@NotBlank @Size(min = 3, max = 100) String beerName) {
            this.beerName = beerName;
            return this;
        }

        public BeerDto.BeerDtoBuilder beerStyle(@NotNull BeerStyleEnum beerStyle) {
            this.beerStyle = beerStyle;
            return this;
        }

        public BeerDto.BeerDtoBuilder upc(@NotNull String upc) {
            this.upc = upc;
            return this;
        }

        public BeerDto.BeerDtoBuilder price(@Positive @NotNull BigDecimal price) {
            this.price = price;
            return this;
        }

        public BeerDto.BeerDtoBuilder quantityOnHand(@Positive Integer quantityOnHand) {
            this.quantityOnHand = quantityOnHand;
            return this;
        }

        public BeerDto build() {
            return new BeerDto(id, version, createdDate, lastModifiedDate, beerName, beerStyle, upc, price, quantityOnHand);
        }

        public String toString() {
            return "BeerDto.BeerDtoBuilder(id=" + this.id + ", version=" + this.version + ", createdDate=" + this.createdDate + ", lastModifiedDate=" + this.lastModifiedDate + ", beerName=" + this.beerName + ", beerStyle=" + this.beerStyle + ", upc=" + this.upc + ", price=" + this.price + ", quantityOnHand=" + this.quantityOnHand + ")";
        }
    }
}
