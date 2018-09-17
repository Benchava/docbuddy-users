package docbuddy.users.service;

import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.TableResult;
import docbuddy.users.model.User;
import docbuddy.users.persistence.BigQueryManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserService {
    BigQueryManager bigQueryManager;

    public UserService() {
        this.bigQueryManager = new BigQueryManager();
    }

    public List<User> getAllUsers() throws InterruptedException {
        String query = "SELECT * FROM docbuddyDev.User LIMIT 10";
        List<User> usersList = null;

        TableResult result = bigQueryManager.executeQuery(query);

        if (result.getTotalRows() > 0) {
            usersList = new ArrayList<>();
            for (FieldValueList row : result.iterateAll()) {
                User user = User.builder()
                        .id(row.get("ID").getStringValue())
                        .admin(row.get("ADMIN").getBooleanValue())
                        .doctor(row.get("DOCTOR").getBooleanValue())
                        .firstName(row.get("FIRST_NAME").getStringValue())
                        .lastName(row.get("LAST_NAME").getStringValue())
                        .userName(row.get("USERNAME").getStringValue())
                        .password(row.get("PASSWORD").getStringValue())
                        .build();

                usersList.add(user);
            }
        }

        return usersList;
    }
}
