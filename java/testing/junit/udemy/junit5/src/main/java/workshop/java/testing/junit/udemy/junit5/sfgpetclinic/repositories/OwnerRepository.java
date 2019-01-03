package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.repositories;


import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
