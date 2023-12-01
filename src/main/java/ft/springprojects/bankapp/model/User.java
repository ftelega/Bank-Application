package ft.springprojects.bankapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private BigDecimal balance;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "auth_id", nullable = false)
    private Address address;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "user_auth",
            joinColumns = @JoinColumn(name = "user_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "auth_id", nullable = false))
    private Set<Authority> authorities;
    @OneToMany(mappedBy = "fromUser")
    private Set<Transaction> transactionsSent;
    @OneToMany(mappedBy = "toUser")
    private Set<Transaction> transactionsReceived;
}
