import entity.MasterNumber;
import entity.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonService {

    private Map<List<String>, Optional<PersonSet>> people;

    public PersonService() {
        this.people = new HashMap<>();
        people.put(Arrays.asList("1"), Optional.of(PersonSetDataProvider.providePersonSetWithNumber1()));
        people.put(Arrays.asList("1", "2"), Optional.of(PersonSetDataProvider.providePersonSetWithNumber1And2()));
    }

    public Stream<Person> getPersonByMasterNumbers(List<MasterNumber> numbers) {
        //TODO: Add the code to return people by numbers
        // Use groupToPeople() method

        List<String> list = numbers.stream().map(m -> m.toString()).collect(Collectors.toList());
        return people.getOrDefault(list, Optional.empty()).map(PersonSet::groupToPeople).orElse(Stream.empty());

//        try {
//            people.get(list).isPresent();
//            return people.get(list).get().groupToPeople();
//        } catch (NullPointerException e) {
//
//        }
//        return Stream.empty();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonService that = (PersonService) o;
        return Objects.equals(people, that.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(people);
    }
}
