package com.movieland.service.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.movieland.service.enums.GenderCodes;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Artist.class)
public class Artist_ {
	
	public static volatile SingularAttribute<Artist, BigInteger> id;
	public static volatile SingularAttribute<Artist, String> name;
	public static volatile SingularAttribute<Artist, String> artisticName;
	public static volatile SingularAttribute<Artist, GenderCodes> gender;
	public static volatile SingularAttribute<Artist, LocalDate> birthDate;
	public static volatile SingularAttribute<Artist, Country> nationality;
	public static volatile ListAttribute<Artist, RoleByArtist> rolesByArtist;
	public static volatile ListAttribute<Artist, StarByMovie> starringsByMovie;
	public static volatile ListAttribute<Artist, ProducerByMovie> producersByMovie;
	public static volatile ListAttribute<Artist, DirectorByMovie> directorsByMovie;
	public static volatile SingularAttribute<Artist, String> status;
	public static volatile SingularAttribute<Artist, String> createdBy;
	public static volatile SingularAttribute<Artist, LocalDateTime> createdAt;
	public static volatile SingularAttribute<Artist, String> modifiedBy;
	public static volatile SingularAttribute<Artist, LocalDateTime> modifiedAt;
	
}
