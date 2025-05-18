package util;

import com.github.javafaker.Faker;


public final class TestDataGenerator {

    private static final Faker faker = new Faker();

    private TestDataGenerator() {}

    public static String email() {
        return "user_" + faker.internet().emailAddress();
    }

    public static String name() {
        return faker.name().username();
    }


    public static String password() {
        return faker.regexify("[A-Za-z0-9]{8,10}");
    }
}
