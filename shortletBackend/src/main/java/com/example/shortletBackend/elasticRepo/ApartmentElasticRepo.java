package com.example.shortletBackend.elasticRepo;

import com.example.shortletBackend.entities.Apartments;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentElasticRepo extends ElasticsearchRepository<Apartments,Long> {

    @Query("{\n" +
            "\t\"query_string\": {\n" +
            "\t  \"query\": \"?0\"\n" +
            "\t}\n" +
            "}")
    List<Apartments> findBySearchOnAllFields(String search);
}
