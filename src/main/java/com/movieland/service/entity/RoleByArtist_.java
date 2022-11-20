package com.movieland.service.entity;

import java.time.LocalDate;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleByArtist.class)
public class RoleByArtist_ {
	
	public static volatile SingularAttribute<RoleByArtist, RoleByArtistPK> id;
	public static volatile SingularAttribute<RoleByArtist, LocalDate> startAt;
	public static volatile SingularAttribute<RoleByArtist, LocalDate> endAt;
	public static volatile SingularAttribute<RoleByArtist, String> status;
	public static volatile SingularAttribute<RoleByArtist, Artist> artist;
	public static volatile SingularAttribute<RoleByArtist, Role> role;

}
