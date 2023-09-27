package partner_activities;

public class Animal {
    String name;
    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
}

class Fish extends Animal {
    public Fish(String name) {
        super(name);
    }
}

class Dragon extends Animal {
    public Dragon(String name) {
        super(name);
    }
}