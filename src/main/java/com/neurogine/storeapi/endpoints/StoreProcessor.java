package com.neurogine.storeapi.endpoints;

import org.bson.Document;

import com.neurogine.storeapi.entities.Store;
import com.neurogine.storeapi.utils.ConvertUtils;

public class StoreProcessor {

	public Store load(Document document) {
		return ConvertUtils.ConvertDocumentToStore(document);
    }
	
	public Store create(Store store) {
		return store;
    }

	public Store update(Store store) {
		return store;
    }

	public Store delete(Document document) {
		return ConvertUtils.ConvertDocumentToStore(document);
    }

}
