package docbuddy.users.persistence;

import com.google.cloud.bigquery.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BigQueryManager {
    private BigQuery bigQueryClient;

    public BigQueryManager() {
        this.bigQueryClient = BigQueryOptions.getDefaultInstance().getService();
    }

    private QueryJobConfiguration getQueryJobConfiguration(String query) {
        return QueryJobConfiguration.newBuilder(query)
                .setUseLegacySql(false)
                .build();
    }

    public TableResult executeQuery(String query) throws InterruptedException {
        QueryJobConfiguration queryJobConfiguration = getQueryJobConfiguration(query);

        JobId jobId = JobId.of(UUID.randomUUID().toString());

        Job queryJob = bigQueryClient.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());

        queryJob = queryJob.waitFor();

        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        return queryJob.getQueryResults();
    }
}
