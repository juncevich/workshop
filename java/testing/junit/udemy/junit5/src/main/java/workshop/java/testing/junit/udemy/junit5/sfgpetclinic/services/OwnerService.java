package workshop.java.testing.junit.udemy.junit5.sfgpetclinic.services;


import workshop.java.testing.junit.udemy.junit5.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
 }
