package com.example.StoreAPI.configurations;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

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
}
