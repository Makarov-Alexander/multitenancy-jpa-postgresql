package ru.home.multitenancyjpapostgresql.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "role.set",
                query = "set role :role"
        ),
        @NamedNativeQuery(
                name = "role.set2",
                query = "SELECT c FROM customer c WHERE c.first_name LIKE :role",
                resultClass = Customer.class
        ),
        @NamedNativeQuery(
                name = "role.reset",
                query = "RESET ROLE"
        ),
        @NamedNativeQuery(
                name = "role.create",
                query = "CREATE ROLE ?1"
        ),
        @NamedNativeQuery(
                name = "role.grant.table",
                query = "GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE customer TO ?"
        )
        ,
        @NamedNativeQuery(
                name = "role.grant.sequence",
                query = "GRANT USAGE, SELECT ON SEQUENCE hibernate_sequence TO ?"
        )
        ,
        @NamedNativeQuery(
                name = "role.create.partition",
                query = "CREATE TABLE customer_:rolename PARTITION OF customer FOR VALUES IN (?)"
        )
})

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String department;

    public Customer(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
}
