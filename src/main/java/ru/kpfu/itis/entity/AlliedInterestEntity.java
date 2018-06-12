package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "allied_interest", schema = "uni", catalog = "smartuniversity")
public class AlliedInterestEntity extends IdObject<Long> {

    @ManyToOne
    @JoinColumn(name = "interest_id", referencedColumnName = "id")
    private InterestEntity interest;

    @ManyToOne
    @JoinColumn(name = "allied_interest_id", referencedColumnName = "id")
    private InterestEntity alliedInterest;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInterestEntity that = (UserInterestEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
