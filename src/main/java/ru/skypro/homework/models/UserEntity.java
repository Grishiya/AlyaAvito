package ru.skypro.homework.models;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;
import ru.skypro.homework.dto.RoleDto;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserEntity implements EntityWithImage{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(value = EnumType.STRING)
    private RoleDto role;
    private String image;
    @OneToMany(mappedBy = "author")
    private List<Ad> ads;
    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(email, that.email) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(phone, that.phone) && Objects.equals(image, that.image) && role == that.role && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, firstName, lastName, phone, image, role);
    }

    @Override
    public String toString() {
        return "UserDomain{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + image + '\'' +
                ", userRole=" + role +
                ", passwordHash='" + password + '\'' +
                '}';
    }
}
