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
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "from_user")
    private User fromUser;
    @ManyToOne
    @JoinColumn(name = "to_user")
    private User toUser;
}
