package com.movieland.service.entity;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Movie.class)
public class Movie_ {
	
	public static volatile SingularAttribute<Movie, BigInteger> id;
	public static volatile SingularAttribute<Movie, String> status;
	public static volatile SingularAttribute<Movie, String> createdBy;
	public static volatile SingularAttribute<Movie, LocalDateTime> createdAt;
	public static volatile SingularAttribute<Movie, String> modifiedBy;
	public static volatile SingularAttribute<Movie, LocalDateTime> modifiedAt;

}
