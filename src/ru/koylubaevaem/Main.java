package ru.koylubaevaem;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harries", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long minorsCount = persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();

        System.out.println(minorsCount);

        List<String> conscripts = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN && p.getAge() >= 18 && p.getAge() < 27)
                .map(Person::getSurname)
                .collect(Collectors.toList());

        System.out.println(conscripts);

        List<Person> productivePerson = persons.stream()
                .filter(p -> p.getAge() >= 18 && p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() < 60)
                        || (p.getSex() == Sex.MAN && p.getAge() < 65))
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList());

        System.out.println(productivePerson);

    }
}





// .map(person -> person.getSurname())
//         T    ->  R
// map ->>>Function

//(Person s) -> {return s.getSurname()}


//.map(new Function<Person, String>() {
//@Override
//public String apply(Person person) {
//        return person.getSurname();
//        }
//        })