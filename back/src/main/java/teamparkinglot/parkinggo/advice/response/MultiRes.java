package teamparkinglot.parkinggo.advice.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiRes<T> {

    private List<T> data;

    private PageInfo pageInfo;

    public MultiRes(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber(), page.getSize(), page.getTotalElements(), page.getTotalPages());
    }
}
