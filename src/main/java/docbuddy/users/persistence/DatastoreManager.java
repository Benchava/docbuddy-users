package docbuddy.users.persistence;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import lombok.Getter;

@Getter
public class DatastoreManager {
    private Datastore datastoreClient;

    public DatastoreManager() {
        this.datastoreClient = DatastoreOptions.getDefaultInstance().getService();
    }
}
