package ru.kpfu.itis.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "iup_subj", schema = "uni", catalog = "smartuniversity")
public class IupSubjEntity extends IdObject<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "subj_id", referencedColumnName = "id")
    private SubjectEntity subject;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IupSubjEntity that = (IupSubjEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(user, that.user) &&
                Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), user, subject);
    }
}
