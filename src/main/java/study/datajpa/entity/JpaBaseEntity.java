package study.datajpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class JpaBaseEntity {

    @Column(updatable = false) //실수로 값을 바꿔도 DB에 update 되지 않음
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @PrePersist
    private void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now; //요즘은 IDE를 다 쓰니 this를 걍 생략한다. (너무 중요해서 강조하고 싶을때 표시)
        updatedDate = now; //쿼리 할 때 update 에 null이 있으면 지저분해짐
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
