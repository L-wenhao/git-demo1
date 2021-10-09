package com.csii.meter.console.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Max;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Component
@Slf4j
public class ESUtil {

    @Autowired
    RestHighLevelClient client;
    @Autowired
    ResourceLoader resourceLoader;

    //最大百万条数据
    public static final Integer MAX_NUMBER = 10000000;

    /**
     * 删除索引
     * @param index
     */
    public void deleteIndex(String index){
        DeleteIndexRequest request = new DeleteIndexRequest(index);
        try{
            AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
            log.info("删除索引返回结果：{}", JSONObject.toJSONString(response));
        }catch (Exception e){
            log.error("es删除索引出现异常：{}", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 批量删除
     * @param index 索引
     * @param idList 集合
     * @param <T>
     */
    public <T> void deleteBatch(String index, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(index, item.toString())));
        try {
          client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

        /**
         * 插入或更新数据
         * @param index
         * @param id
         * @param map
         */
    public void insert(String index, String id, Map<String,Object> map) {
        IndexRequest request = new IndexRequest(index);
        try{
            request.id(id);
            request.source(JSONObject.toJSONString(map), XContentType.JSON);
            IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
            log.info("插入索引返回结果：{}", JSONObject.toJSONString(indexResponse));
        }catch (Exception e){
            log.error("es插入数据出现异常：{}", e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * 更新字段
     * @param index
     * @param id
     * @param map
     */
    public void update(String index, String id, Map<String,Object> map) {
        UpdateRequest updateRequest = new UpdateRequest(index, id);
        try{
            updateRequest.doc(map);
            UpdateResponse updateResponse = client.update(updateRequest,RequestOptions.DEFAULT);
            log.info("插入索引返回结果：{}", JSONObject.toJSONString(updateResponse));
        }catch (Exception e){
            log.error("es插入数据出现异常：{}", e.getMessage());
        }

    }

    /**
     * 根据条件删除
     * @param index
     * @param builder
     */
    public void deleteByQuery(String index, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(ESUtil.MAX_NUMBER);
        request.setConflicts("proceed");
        try {
            client.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    /**
     * 查询数据
     * @param index
     * @param id
     * @return
     */
    public String query(String index, String id){

        GetRequest getRequest = new GetRequest(index, id);
        String result  = null;
        try {
            GetResponse response = client.get(getRequest,RequestOptions.DEFAULT);
            result = response.getSourceAsString();
        }catch(Exception e) {
            log.error("es查询出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public <T> T query(String index, String id, Class<T> c){
        T obj = null;
        GetRequest getRequest = new GetRequest(index, id);
        try {
            GetResponse response = client.get(getRequest,RequestOptions.DEFAULT);
            obj = JSON.parseObject(response.getSourceAsString(), c);
        }catch(Exception e) {
            log.error("es查询出现异常：{}", e.getMessage());
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 查询数据
     * @param index 索引
     * @param builder 查询条件
     * @param c 返回类型
     * @param <T>
     * @return
     */
    public <T> List<T> search(String index, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
          SearchResponse response = client.search(request, RequestOptions.DEFAULT);
          log.info(JSONObject.toJSONString(response));
          SearchHit[] hits = response.getHits().getHits();
          List<T> res = new ArrayList<>(hits.length);
          for (SearchHit hit : hits) {
            res.add(JSON.parseObject(hit.getSourceAsString(), c));
          }
          return res;
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
    }

    public List<String> searchIds(String index, SearchSourceBuilder builder){
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            SearchHit[] hits = response.getHits().getHits();
            List<String> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(hit.getId());
            }
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        /**
         * 单条件检索
         * @param fieldKey
         * @param fieldValue
         * @return
         */
    public MatchPhraseQueryBuilder uniqueMatchQuery(String fieldKey, String fieldValue){
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery(fieldKey,fieldValue);
        return matchPhraseQueryBuilder;
    }

    //------- 示例 ------
    public List<String> searchData(String index, String field, String value) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(100);
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = uniqueMatchQuery(field, value);
        sourceBuilder.query(matchPhraseQueryBuilder);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        List<String> result = new ArrayList<>();
        searchResponse.getHits().forEach(message -> {
            try {
                String sourceAsString = message.getSourceAsString();
                result.add(sourceAsString);
                log.info(sourceAsString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    public List<String> groupByData(String index) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder aggregationBuilder1 = AggregationBuilders.terms("sex").field("sex");
        MaxAggregationBuilder aggregationBuilder2 = AggregationBuilders.max("maxsalary").field("salary");
        sourceBuilder.aggregation(aggregationBuilder1.subAggregation(aggregationBuilder2));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        Terms aggregation = searchResponse.getAggregations().get("sex");
        Max terms2 = null;
        List<String> result = new ArrayList<>();
        for (Terms.Bucket bucket : aggregation.getBuckets()) {
            terms2 = bucket.getAggregations().get("maxsalary");  //class org.elasticsearch.search.aggregations.metrics.max.InternalMax
            log.info("性别=" + bucket.getKeyAsString() + ";最高工资=" + terms2.getValue());
            result.add("性别=" + bucket.getKeyAsString() + ";最高工资=" + terms2.getValue());
        }

        return result;
    }

    public List<String> sumData(String index) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        TermsAggregationBuilder aggregationBuilder1 = AggregationBuilders.terms("sex").field("sex");
        AggregationBuilder aggregationBuilder2 = AggregationBuilders.sum("sumsalary").field("salary");
        sourceBuilder.aggregation(aggregationBuilder1.subAggregation(aggregationBuilder2));
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        Terms aggregation = searchResponse.getAggregations().get("sex");
        Sum terms2 = null;
        List<String> result = new ArrayList<>();
        for (Terms.Bucket bucket : aggregation.getBuckets()) {
            terms2 = bucket.getAggregations().get("sumsalary");  //class org.elasticsearch.search.aggregations.metrics.max.InternalMax
            log.info("性别=" + bucket.getKeyAsString() + ";工资合计=" + terms2.getValue());
            result.add("性别=" + bucket.getKeyAsString() + ";工资合计=" + terms2.getValue());
        }

        return result;
    }

    public List<String> rangeData(String index) throws IOException {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        AggregationBuilder aggregation = AggregationBuilders.dateHistogram("agg").field("create_time").format("yyyy-MM-dd");
        sourceBuilder.aggregation(aggregation);
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest,RequestOptions.DEFAULT);
        Histogram agg = searchResponse.getAggregations().get("agg");
        for (Histogram.Bucket bucket : agg.getBuckets()) {
            log.info("日期=" + bucket.getKeyAsString());
        }

        List<String> result = new ArrayList<>();
//        searchResponse.getHits().forEach(message -> {
//            try {
//                String sourceAsString = message.getSourceAsString();
//                result.add(sourceAsString);
//                log.info(sourceAsString);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
        return result;
    }

    /**
     * json文件转化为map
     * @param fileName
     * @return
     */
    public Map<String,Object> JSONFileToMap(String fileName){
        Map<String,Object> result = new HashMap<>();
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            Resource resource = resourceLoader.getResource("classpath:"+fileName);
            is = resource.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String data = null;
            while((data = br.readLine()) != null) {
                data = handleStr(data);
                buffer.append(data);
            }
            result = (Map<String,Object>)JSONObject.parse(buffer.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(isr != null){
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public String handleStr(String data){
        if(data.contains("//")){
            data = data.substring(0,data.indexOf("//"));
        }
        return data;
    }

}
