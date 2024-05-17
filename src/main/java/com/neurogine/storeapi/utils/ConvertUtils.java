package com.neurogine.storeapi.utils;

import java.util.Map;

import org.bson.Document;

import com.neurogine.storeapi.entities.Store;

public class ConvertUtils {
	
	public static Document ConvertStoreToDocument(Store store) {
		Document document = new Document();
		document.put("name", store.getName());
        document.put("location", store.getLocation());
        document.put("tags", store.getTags());
        document.put("image", store.getImage());
        document.put("promotions", store.getPromotions());
        document.put("rating", store.getRating());
		return document;
	}

	public static Store ConvertDocumentToStore(Document document) {
		Store store = new Store();
		store.setId(document.getObjectId("_id").toString());
		store.setName(document.getString("name"));
		store.setImage(document.getString("image"));
		store.setLocation((Map<String, Double>)document.get("location"));
		store.setPromotions(document.getList("promotions", String.class));
		store.setTags(document.getList("tags", String.class));
		store.setRating(document.getDouble("rating"));
		return store;
	}

}
