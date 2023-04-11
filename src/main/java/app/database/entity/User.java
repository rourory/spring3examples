package app.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode(of = "username")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@NamedEntityGraph(name = "User.company", attributeNodes = {
        @NamedAttributeNode("company")
})
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private LocalDate birthDate;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @JoinColumn(name = "company_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Company company;


    @Builder.Default
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @NotAudited
    private List<UserChat> userChats = new ArrayList<>();
}
