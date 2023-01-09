package com.movieland.service.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RateByMovie.class)
public class RateByMovie_ {

	public static volatile SingularAttribute<RateByMovie, BigInteger> id;
	public static volatile SingularAttribute<RateByMovie, Movie> ratedMovie;
	public static volatile SingularAttribute<RateByMovie, Integer> rate;
	public static volatile SingularAttribute<RateByMovie, LocalDate> ratedAt;
	public static volatile SingularAttribute<RateByMovie, String> status;
	public static volatile SingularAttribute<RateByMovie, String> createdBy;
	public static volatile SingularAttribute<RateByMovie, LocalDateTime> createdAt;
	public static volatile SingularAttribute<RateByMovie, String> modifiedBy;
	public static volatile SingularAttribute<RateByMovie, LocalDateTime> modifiedAt;
	
}
