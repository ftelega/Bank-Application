package ft.springprojects.bankapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    @JoinColumn(name = "from_user", nullable = false)
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user", nullable = false)
    private User toUser;
}
