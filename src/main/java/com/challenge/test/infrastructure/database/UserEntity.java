package com.challenge.test.infrastructure.database;

import com.challenge.test.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "test")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    private String id;

    private String name;

    private String phone;

    public User getUserDomain() {
        return new User(
                this.id,
                this.name,
                this.phone
        );
    }

    public static UserEntity getFromDomain(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getPhone()
        );
    }

}
