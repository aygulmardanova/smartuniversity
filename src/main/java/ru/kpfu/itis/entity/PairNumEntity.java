package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "pair_num", schema = "uni", catalog = "smartuniversity")
public class PairNumEntity extends IdObject<Long> {

    @Basic
    @Column(name = "pair_st_time", nullable = true, length = -1)
    private String pairStTime;

    @Basic
    @Column(name = "pair_end_time", nullable = true, length = -1)
    private String pairEndTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PairNumEntity that = (PairNumEntity) o;
        return Objects.equals(pairStTime, that.pairStTime) &&
                Objects.equals(pairEndTime, that.pairEndTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pairStTime, pairEndTime);
    }
}
