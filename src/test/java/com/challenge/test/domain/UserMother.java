package com.challenge.test.domain;


import com.github.javafaker.Faker;

public final class UserMother {

    private static final Faker faker = new Faker();

    public static User random() {
        return new User(
                faker.idNumber().valid(),
                faker.artist().name(),
                faker.phoneNumber().phoneNumber()
        );
    }

}
