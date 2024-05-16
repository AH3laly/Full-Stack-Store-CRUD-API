package com.neurogine.storeapi.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mongodb.dsl.MongoDb;
import org.springframework.integration.mongodb.dsl.MongoDbOutboundGatewaySpec;

import com.mongodb.client.result.InsertOneResult;
import com.neurogine.storeapi.endpoints.StoreProcessor;
import com.neurogine.storeapi.entities.Store;
import com.neurogine.storeapi.entities.utils.ConvertUtils;

@Configuration
@IntegrationComponentScan("com.example.demo")
public class StoreMessagingConfiguration {
	@MessagingGateway
    public interface StoreService {
        @Gateway(requestChannel = "createStore.input")
        void create();
        
        @Gateway(requestChannel = "updateStore.input")
        void update();
        
        @Gateway(requestChannel = "loadStore.input")
        void load();
        
        @Gateway(requestChannel = "deleteStore.input")
        void delete();
    }
	
	@Bean
    @Autowired
    public IntegrationFlow createStore(MongoDatabaseFactory mongo) {
    	return f -> f
    			.handle(createStoreRequest(mongo))
    			.handle(new StoreProcessor(), "create");
    }

    @Bean
    public MongoDbOutboundGatewaySpec createStoreRequest(MongoDatabaseFactory mongo) {
    	return MongoDb.outboundGateway(new MongoTemplate(mongo))
    			.collectionName("store")
    			.collectionCallback((c, m) -> {
    				Store storePayload = (Store) m.getPayload();
    				InsertOneResult d = c.insertOne(ConvertUtils.ConvertStoreToDocument(storePayload));
    				storePayload.setId(d.getInsertedId().toString());
    				return storePayload;
    			})
    			.expectSingleResult(true)
    			.entityClass(Store.class);
    }
    
    @Bean
    @Autowired
    public IntegrationFlow updateStore(MongoDatabaseFactory mongo) {
    	return f -> f
    			.handle(updateStoreRequest(mongo))
    			.handle(new StoreProcessor(), "update");		
    }
    
    @Bean
    public MongoDbOutboundGatewaySpec updateStoreRequest(MongoDatabaseFactory mongo) {
    	return MongoDb.outboundGateway(new MongoTemplate(mongo))
    			.collectionName("store")
    			.collectionCallback((c, m) -> {
    				Store storePayload = (Store) m.getPayload();
    				
    				Bson filter = Filters.eq("_id", new ObjectId(storePayload.getId()));

    				Bson updates = Updates.combine(
    						Updates.set("name", storePayload.getName()),
    						Updates.set("image", storePayload.getImage()),
    						Updates.set("tags", storePayload.getTags()),
    						Updates.set("location", storePayload.getLocation()),
    						Updates.set("promotions", storePayload.getPromotions()),
    						Updates.set("rating", storePayload.getRating())
					);
    				
    				c.updateMany(filter, updates);
    				
    				return storePayload;
    			})
    			.expectSingleResult(true)
    			.entityClass(Store.class);
    }
}
