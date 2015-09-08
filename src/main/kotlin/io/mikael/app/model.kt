package io.mikael.app

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.time.LocalDate
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table

@Entity @Table(name = "restaurants")
data class Restaurant(@EmbeddedId var id: Key?,
                      var name: String?, var location: String?, var lat: Double?, var lng: Double?) {

    constructor() : this(null, null, null, null, null)

    @JsonGetter("id")
    public fun jsonGetId() : Long? {
        return id?.getId()
    }

    @JsonSetter("id")
    public fun jsonSetId(newId : Long?) {
        id = Key(newId)
    }

}

@Entity @Table(name = "menus")
data class Menu(@EmbeddedId var id: Key?,
                var restaurantId: Long?, var date: LocalDate?, var text: String?) {

    constructor() : this(null, null, null, null)

}

@RepositoryRestResource(path = "restaurants")
public interface RestaurantRepository : JpaRepository<Restaurant, Key>

@RepositoryRestResource(path = "menus")
public interface MenuRepository : JpaRepository<Menu, Key>
