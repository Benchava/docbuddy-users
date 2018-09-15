package docbuddy.users.persistence;

import com.google.cloud.bigquery.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class BigQueryManager {
    private BigQuery bigQueryClient;

    public BigQueryManager() {
        this.bigQueryClient = BigQueryOptions.getDefaultInstance().getService();
    }

    public QueryJobConfiguration getQueryJobConfiguration(String query) {
        return QueryJobConfiguration.newBuilder(query)
                .setUseLegacySql(false)
                .build();
    }

    public JobId executeQuery(QueryJobConfiguration queryJobConfiguration) {
        JobId jobId = JobId.of(UUID.randomUUID().toString());

        bigQueryClient.create(JobInfo.newBuilder(queryJobConfiguration).setJobId(jobId).build());

        return jobId;
    }

    public void getQueryResults(JobId jobId) {
        QueryResponse response = bigQueryClient.getQueryResults(jobId);


    }

    public Map<String, Long> testBQ() throws InterruptedException {
        QueryJobConfiguration queryConfig = getQueryJobConfiguration(
                "SELECT "
                        + "CONCAT('https://stackoverflow.com/questions/', CAST(id as STRING)) as url, "
                        + "view_count "
                        + "FROM `bigquery-public-data.stackoverflow.posts_questions` "
                        + "WHERE tags like '%google-bigquery%' "
                        + "ORDER BY favorite_count DESC LIMIT 10");

        // Create a job ID so that we can safely retry.
        JobId jobId = JobId.of(UUID.randomUUID().toString());
        Job queryJob = bigQueryClient.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

        // Wait for the query to complete.
        queryJob = queryJob.waitFor();

        // Check for errors
        if (queryJob == null) {
            throw new RuntimeException("Job no longer exists");
        } else if (queryJob.getStatus().getError() != null) {
            // You can also look at queryJob.getStatus().getExecutionErrors() for all
            // errors, not just the latest one.
            throw new RuntimeException(queryJob.getStatus().getError().toString());
        }

        // Get the results.
        QueryResponse response = bigQueryClient.getQueryResults(jobId);

        TableResult result = queryJob.getQueryResults();

        Map<String, Long> resultMap = new HashMap<>();

        // Print all pages of the results.
        for (FieldValueList row : result.iterateAll()) {
            String url = row.get("url").getStringValue();
            long viewCount = row.get("view_count").getLongValue();
            System.out.printf("url: %s views: %d%n", url, viewCount);
            resultMap.put(url, viewCount);
        }

        return resultMap;
    }
}
