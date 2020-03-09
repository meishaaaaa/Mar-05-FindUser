import entity.Address;
import entity.Email;
import entity.MasterNumber;
import entity.Person;
import entity.Telephone;

import javax.swing.text.TabExpander;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonSet {
    private List<MasterNumber> masterNumbers;

    private List<Address> addresses;

    private List<Telephone> telephones;

    private List<Email> emails;

    public PersonSet(List<MasterNumber> masterNumbers,
                     List<Telephone> telephones,
                     List<Address> addresses,
                     List<Email> emails) {
        this.masterNumbers = masterNumbers;
        this.addresses = addresses;
        this.telephones = telephones;
        this.emails = emails;
    }

    public Stream<Person> groupToPeople() {
        // TODO: group the data to Stream<Person>
        // Can use Collectors.groupingBy method
        // Can add helper method

        try {
            Map<String, List<Address>> addressMap = addresses.stream().collect(Collectors.groupingBy(Address::getMasterNumber));
            Map<String, List<Email>> emailMap = emails.stream().collect(Collectors.groupingBy(Email::getMasterNumber));
            Map<String, List<Telephone>> telMap = telephones.stream().collect(Collectors.groupingBy(Telephone::getMasterNumber));


            ArrayList<Person> person = getPeople(addressMap, emailMap, telMap);

            return person.stream().distinct();
        }
        catch (NullPointerException e){

        }
        return Stream.empty();
    }

    private ArrayList<Person> getPeople(Map<String, List<Address>> addressMap, Map<String, List<Email>> emailMap, Map<String, List<Telephone>> telMap) {
        ArrayList<Person> person = new ArrayList<>();
        try {
            for (MasterNumber i : masterNumbers) {
                List<Address> address = addressMap.get(i.toString());
                List<Email> email = emailMap.get(i.toString());
                List<Telephone> tel = telMap.get(i.toString());
                for (Address a : address) {
                    Person p = new Person(i.toString(), tel, a, email);
                    person.add(p);
                }
            }
            return person;
        } catch (NullPointerException e) {

        }
        return null;
    }


    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setMasterNumbers(List<MasterNumber> masterNumbers) {
        this.masterNumbers = masterNumbers;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonSet personSet = (PersonSet) o;
        return Objects.equals(masterNumbers, personSet.masterNumbers) &&
                Objects.equals(addresses, personSet.addresses) &&
                Objects.equals(telephones, personSet.telephones) &&
                Objects.equals(emails, personSet.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterNumbers, addresses, telephones, emails);
    }
}
