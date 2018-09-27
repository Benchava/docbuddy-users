package docbuddy.users.persistence;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
@AllArgsConstructor
public class DataStoreManager {
    private Datastore dataStoreClient;

    public DataStoreManager() {
        this.dataStoreClient = DatastoreOptions.getDefaultInstance().getService();

    }
}
