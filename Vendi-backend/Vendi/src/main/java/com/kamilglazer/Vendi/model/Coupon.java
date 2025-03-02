package com.kamilglazer.Vendi.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String code;

    private double discountPercentage;
    private LocalDateTime validityStartDate;
    private LocalDateTime validityEndDate;
    private double minimumOrderValue;
    private boolean isActive;

    public void updateIsActive(){
        LocalDateTime now = LocalDateTime.now();
        this.isActive = validityStartDate != null && validityEndDate !=null &&
                !now.isBefore(validityStartDate) && !now.isAfter(validityEndDate);
    }

}
