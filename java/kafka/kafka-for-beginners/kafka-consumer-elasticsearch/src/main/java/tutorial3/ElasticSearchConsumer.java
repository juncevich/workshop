package tutorial3;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ElasticSearchConsumer {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConsumer.class);

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = createClient();

        String jsonString = "{\"foo\":\"bar\"}";
        IndexRequest indexRequest = new IndexRequest("twitter", "tweets").source(jsonString, XContentType.JSON);

        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        String id = response.getId();
        logger.info(id);

        client.close();
    }

    public static RestHighLevelClient createClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("sr9kqxlx82", "u9d3e705o"));

        RestClientBuilder builder = RestClient.builder(
                new HttpHost("kafka-course-7274254606.eu-central-1.bonsaisearch.net", 443, "https")
        ).setHttpClientConfigCallback((httpAsyncClientBuilder) -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        RestHighLevelClient restClient = new RestHighLevelClient(builder);
        return restClient;
    }

}
