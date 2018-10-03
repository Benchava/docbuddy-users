package docbuddy.users.persistence;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
public class DataStoreManager {
    private Datastore datastoreClient;

    public DataStoreManager() {
        this.datastoreClient = DatastoreOptions.getDefaultInstance().getService();
    }

    public DataStoreManager(Datastore client) {
        this.datastoreClient = client;
    }
}
