package teamparkinglot.parkinggo.parking.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {
    private String zipcode;
    private String street;
    private String parcel;

    protected Address() {
    }

    public Address(String zipcode, String street, String parcel) {
        this.zipcode = zipcode;
        this.street = street;
        this.parcel = parcel;
    }
}
