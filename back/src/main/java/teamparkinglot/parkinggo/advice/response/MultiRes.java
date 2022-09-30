package teamparkinglot.parkinggo.advice.response;

import lombok.Getter;

import java.util.List;

@Getter
public class MultiRes<T> {

    private List<T> data;

    public MultiRes(List<T> data) {
        this.data = data;
    }
}
