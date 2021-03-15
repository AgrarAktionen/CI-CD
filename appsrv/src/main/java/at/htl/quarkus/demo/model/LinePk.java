package at.htl.quarkus.demo.model;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class LinePk implements Serializable {

    private int bill_id;
    private int row_id;

    public LinePk() {
    }

    public LinePk(int bill_id, int row_id) {
        this.bill_id = bill_id;
        this.row_id = row_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinePk)) return false;
        LinePk linePk = (LinePk) o;
        return bill_id == linePk.bill_id &&
                row_id == linePk.row_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bill_id, row_id);
    }

}
