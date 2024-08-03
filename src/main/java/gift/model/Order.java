package gift.model;

import gift.exception.InvalidInputValueException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private Option option;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "order_date_time", nullable = false)
    private LocalDateTime orderDateTime;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    protected Order() {
    }

    public Order(Long id, Option option, Long quantity, LocalDateTime orderDateTime, String message,
        Member member) {
        validateQuantity(quantity);
        this.id = id;
        this.option = option;
        this.quantity = quantity;
        this.orderDateTime = orderDateTime;
        this.message = message;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public Option getOption() {
        return option;
    }

    public Long getQuantity() {
        return quantity;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public String getMessage() {
        return message;
    }

    public Member getMember() {
        return member;
    }

    private void validateQuantity(Long quantity) {
        if (quantity == null) {
            throw new InvalidInputValueException("주문 수량을 입력해야 합니다.");
        }
        if (quantity <= 0) {
            throw new InvalidInputValueException("주문 수량은 1 이상이어야 합니다.");
        }
    }
}