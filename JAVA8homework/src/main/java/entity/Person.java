package entity;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.*;

public class Person {

    private String masterNumber;
    private Address address;

    private List<Telephone> telephones;

    private List<Email> emails;

    public Person() {
    }

    public Person(String masterNumber, List<Telephone> telephones, Address address,
                  List<Email> emails) {
        this.masterNumber = masterNumber;
        this.address = address;
        this.telephones = telephones;
        this.emails = emails;
    }

    public Optional<SimpleAddress> getSimpleAddress() {
        //TODO: return Optional<SimpleAddress>

        Optional<Address> address = Optional.ofNullable(this.address);
        return address.map(value->new SimpleAddress(value.getStreet(), value.getCity()));

//        try {
//            Optional<SimpleAddress> optionalSimpleAddress = Optional.ofNullable(new SimpleAddress(address.getStreet(), address.getCity()));
//            return optionalSimpleAddress;
//        } catch (NullPointerException e) {
//
//        }
//        return Optional.empty();
    }

    public Address getAddress() {
        return address;
    }

    public List<Email> getEmails() {
        List list = Collections.emptyList();
        try {
            list.addAll(emails);
            return list;
        } catch (NullPointerException e) {

        }
        return list;
    }

    public List<Telephone> getTelephones() {
        List list = Collections.emptyList();
        try {
            list.addAll(telephones);
            return list;
        } catch (NullPointerException e) {

        }
        return list;
    }

    @Override
    public String toString() {
        return "Person{" +
                "masterNumber='" + masterNumber + '\'' +
                ", address=" + address +
                ", telephones=" + telephones +
                ", emails=" + emails +
                '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(masterNumber, person.masterNumber) &&
                Objects.equals(address, person.address) &&
                Objects.equals(telephones, person.telephones) &&
                Objects.equals(emails, person.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterNumber, address, telephones, emails);
    }


}
