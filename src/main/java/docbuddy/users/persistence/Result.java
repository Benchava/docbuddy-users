package docbuddy.users.persistence;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Result<K> {

    private String cursor;
    private  List<K> result;

    public Result(List<K> result, String cursor) {
        this.result = result;
        this.cursor = cursor;
    }

    @JsonCreator
    public Result(@JsonProperty("result") List<K> result) {
        this.result = result;
        this.cursor = null;
    }
}
