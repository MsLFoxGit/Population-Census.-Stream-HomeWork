package Netology.MSL.JavaCore.Stream.HwPopulationCensus;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
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
        long counter =
                persons.stream()
                        .filter(person -> person.getAge() < 18)
                        .count();
        System.out.println("Количество несовершеннолетних в списке: " + counter);

        List<String> conscripts =
                persons.stream()
                        .filter(person -> person.getAge() > 17 && person.getAge() < 28 && person.getSex() == Sex.MAN)
                        .map(person -> person.getName())
                        .collect(Collectors.toList());

        List<Person> ableToWork =
                persons.stream()
                        .filter(person -> person.getAge() > 17 && person.getEducation() == Education.HIGHER &&
                                (person.getAge() < 61 && person.getSex() == Sex.WOMAN ||
                                        person.getAge() < 66 && person.getSex() == Sex.MAN))
                        .sorted(Comparator.comparing(person -> person.getFamily()))
                        .collect(Collectors.toList());
    }

}
