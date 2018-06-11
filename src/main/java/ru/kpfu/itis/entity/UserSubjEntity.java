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
@Table(name = "user_subj", schema = "uni", catalog = "smartuniversity")
public class UserSubjEntity extends IdObject<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private @Getter @Setter SubjectEntity subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubjEntity that = (UserSubjEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
