package com.learning.mongoData.queries;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;

import com.learning.mongoData.model.FlightInfo;
import com.learning.mongoData.model.FlightType;

@Component
public class FlightInfoQueries{
	
	private MongoTemplate mongoTemplate;
	
	public FlightInfoQueries(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public List<FlightInfo> findAll(String field, int pageNb, int pageSize){
		Query allPageAndOrder = new Query().with(Sort.by(Sort.Direction.ASC, field)).with(PageRequest.of(pageNb, pageSize));
		return this.mongoTemplate.find(allPageAndOrder, FlightInfo.class);
	}
	
	public FlightInfo findSingleById(String id) {
		return this.mongoTemplate.findById(id, FlightInfo.class);
	}
	
	public long countInternational() {
		Query international = Query.query(Criteria.where("type").is(FlightType.International));
		return this.mongoTemplate.count(international, FlightInfo.class);
	}
	
	public List<FlightInfo> findByDeparture(String departure){
		Query byDeparture = Query.query(Criteria.where("departure").is(departure));
		return this.mongoTemplate.find(byDeparture, FlightInfo.class);
	}
	
	public List<FlightInfo> findByDurationBetween(int minMinutes, int maxMinutes){
		Query delayedAtDeparture = Query.query(Criteria.where("durationMin").gte(minMinutes).lte(maxMinutes)).with(Sort.by(Sort.Direction.DESC, "durationMin"));
		return this.mongoTemplate.find(delayedAtDeparture, FlightInfo.class);
	}
	
	public List<FlightInfo> findDelayedAtDeparture(String departure){
		Query delayedAtDeparture = Query.query(Criteria.where("isDelayed").is(true).and("departure").is(departure));
		return this.mongoTemplate.find(delayedAtDeparture, FlightInfo.class);
	}
	
	public List<FlightInfo> findRelatedToCityAndNotDelayed(String city){
		Query byCity = Query.query(new Criteria()
				.orOperator(
						Criteria.where("departure").is(city),
						Criteria.where("destination").is(city)
						)
				.andOperator(Criteria.where("isDelayed").is(false))
				);
		return this.mongoTemplate.find(byCity, FlightInfo.class);
	}
	
	public List<FlightInfo> findByAircraft(String aircraft){
//		The below commented execution was before using converters
//		Query byAircraft = Query.query(Criteria.where("aircraft.model").is(aircraft));
		Query byAircraft = Query.query(Criteria.where("aircraft").is(aircraft+"/200"));
		return this.mongoTemplate.find(byAircraft, FlightInfo.class);
	}
	
	public List<FlightInfo> findByFreeText(String text){
		TextCriteria textCriteria = TextCriteria.forDefaultLanguage().matching(text);
		Query byFreeText = TextQuery.queryText(textCriteria).sortByScore().with(PageRequest.of(0, 3));
		return this.mongoTemplate.find(byFreeText, FlightInfo.class);
	}
}