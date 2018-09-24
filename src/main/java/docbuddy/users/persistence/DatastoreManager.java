package docbuddy.users.persistence;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@Repository
public class DatastoreManager {
    private Datastore datastoreClient;

    public DatastoreManager() {
        this.datastoreClient = DatastoreOptions.getDefaultInstance().getService();
    }

    public DatastoreManager(Datastore client) {
        this.datastoreClient = client;
    }
}
