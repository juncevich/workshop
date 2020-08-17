package guru.springframework.msscbeerservice.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Beer {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID id;
    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;
    @UpdateTimestamp
    private Timestamp lastModifiedDate;
    private String    beerName;
    private String    beerStyle;

    @Column(unique = true)
    private String     upc;
    private BigDecimal price;

    private Integer minOnHand;
    private Integer quantityToBrew;

    public Beer(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String beerName, String beerStyle, String upc, BigDecimal price, Integer minOnHand, Integer quantityToBrew) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.beerName = beerName;
        this.beerStyle = beerStyle;
        this.upc = upc;
        this.price = price;
        this.minOnHand = minOnHand;
        this.quantityToBrew = quantityToBrew;
    }

    public Beer() {
    }

    public static BeerBuilder builder() {
        return new BeerBuilder();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Timestamp getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getBeerName() {
        return this.beerName;
    }

    public void setBeerName(String beerName) {
        this.beerName = beerName;
    }

    public String getBeerStyle() {
        return this.beerStyle;
    }

    public void setBeerStyle(String beerStyle) {
        this.beerStyle = beerStyle;
    }

    public String getUpc() {
        return this.upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMinOnHand() {
        return this.minOnHand;
    }

    public void setMinOnHand(Integer minOnHand) {
        this.minOnHand = minOnHand;
    }

    public Integer getQuantityToBrew() {
        return this.quantityToBrew;
    }

    public void setQuantityToBrew(Integer quantityToBrew) {
        this.quantityToBrew = quantityToBrew;
    }

    public static class BeerBuilder {
        private UUID       id;
        private Long       version;
        private Timestamp  createdDate;
        private Timestamp  lastModifiedDate;
        private String     beerName;
        private String     beerStyle;
        private String     upc;
        private BigDecimal price;
        private Integer    minOnHand;
        private Integer    quantityToBrew;

        BeerBuilder() {
        }

        public Beer.BeerBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public Beer.BeerBuilder version(Long version) {
            this.version = version;
            return this;
        }

        public Beer.BeerBuilder createdDate(Timestamp createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Beer.BeerBuilder lastModifiedDate(Timestamp lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
            return this;
        }

        public Beer.BeerBuilder beerName(String beerName) {
            this.beerName = beerName;
            return this;
        }

        public Beer.BeerBuilder beerStyle(String beerStyle) {
            this.beerStyle = beerStyle;
            return this;
        }

        public Beer.BeerBuilder upc(String upc) {
            this.upc = upc;
            return this;
        }

        public Beer.BeerBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Beer.BeerBuilder minOnHand(Integer minOnHand) {
            this.minOnHand = minOnHand;
            return this;
        }

        public Beer.BeerBuilder quantityToBrew(Integer quantityToBrew) {
            this.quantityToBrew = quantityToBrew;
            return this;
        }

        public Beer build() {
            return new Beer(id, version, createdDate, lastModifiedDate, beerName, beerStyle, upc, price, minOnHand, quantityToBrew);
        }

        public String toString() {
            return "Beer.BeerBuilder(id=" + this.id + ", version=" + this.version + ", createdDate=" + this.createdDate + ", lastModifiedDate=" + this.lastModifiedDate + ", beerName=" + this.beerName + ", beerStyle=" + this.beerStyle + ", upc=" + this.upc + ", price=" + this.price + ", minOnHand=" + this.minOnHand + ", quantityToBrew=" + this.quantityToBrew + ")";
        }
    }
}
