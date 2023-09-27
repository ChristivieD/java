package partner_activities;
import java.util.*;
import java.util.stream.Collectors;

public class AnimalCount {
    public static void main(String[] args) {
        Map<Person, List<Animal>> owners_and_their_pets = new HashMap<>();

        Person marc = new Person("Marc");
        List<Animal> marcs_pets = new ArrayList<>() {{
            add(new Cat("Waffles"));
            add(new Cat("Sprout"));
        }};
        owners_and_their_pets.put(marc, marcs_pets);

        Person krystal = new Person("Krystal");
        List<Animal> krystal_pets = new ArrayList<>() {{
            add(new Cat("Todd"));
            add(new Cat("Margo"));
            add(new Dog("Gus"));
        }};
        owners_and_their_pets.put(krystal, krystal_pets);

        Person bob = new Person("Bob");
        List<Animal> bobs_pets = new ArrayList<>();
        owners_and_their_pets.put(bob, bobs_pets);

        Person amy = new Person("Amy");
        List<Animal> amys_pets = new ArrayList<>();
        amys_pets.add(new Cat("Zipper"));
        owners_and_their_pets.put(amy, amys_pets);

        Person Christivie = new Person("Christivie");
        List<Animal> christivie_pets = new ArrayList<>();
        christivie_pets.add(new Dragon("Zou"));
        owners_and_their_pets.put(Christivie, christivie_pets);

        Person Ben = new Person("Ben");
        List<Animal> ben_pets = new ArrayList<>();
        ben_pets.add(new Cat("Levi"));
        ben_pets.add(new Cat("Eren"));
        owners_and_their_pets.put(Ben, ben_pets);
//        owners_and_their_pets.entrySet().forEach(System.out::println);
        for(var person : owners_and_their_pets.keySet()){
            List petList = owners_and_their_pets.get(person);
            if(petList.size() > 1) {
                System.out.println(person + "'s pets: " + owners_and_their_pets.get(person).stream().map(Object::toString).collect(Collectors.joining(", ")));
            } else if(petList.size() == 1) {
                System.out.println(person + "'s pet: " + owners_and_their_pets.get(person).stream().map(Object::toString).collect(Collectors.joining(", ")));
            } else {
                System.out.println(person + " has no pets.");
            }
        }

        Map<Object, Integer> counter = new HashMap<>();
        for(List<Animal> list : owners_and_their_pets.values()){
            for(Animal animal : list){
                String pets = animal.getClass().getSimpleName();
                counter.put(pets, counter.getOrDefault(pets,0) +1);
            }
        }
        for(Map.Entry entry : counter.entrySet()){
            if ((Integer)entry.getValue() > 1) {
                System.out.println("There are " + entry.getValue() + " " + entry.getKey() + "s");
            } else {
                System.out.println("There is " + entry.getValue() + " " + entry.getKey());

            }
        }
    }
}
