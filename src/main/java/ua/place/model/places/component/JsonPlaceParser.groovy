package ua.place.model.places.component

import ua.place.model.places.entity.Location
import ua.place.model.places.entity.Place

class JsonPlaceParser {

    def parsePages(inLocation, listResults) {
       // assert listPages instanceof List
        //Парсим JSON объект
       def parsePlace = { itPlace ->

            //Расчет дистанции
            def distance = { x, y, x1, y1 ->
                Math.sqrt(

                        Math.pow(x - x1, 2) +
                                Math.pow(y - y1, 2)
                )
            }

            def place = new Place();
            def location = new Location(latitude: itPlace.geometry.location.lat, longitude: itPlace.geometry.location.lng)

            place.location = location
            place.id = itPlace.id
            place.placeId = itPlace.place_id
            place.name = itPlace.name
            place.vicinity = itPlace.vicinity
            place.distance = distance(inLocation.latitude,
                    inLocation.longitude,
                    location.latitude,
                    location.longitude
            )

            place.types = itPlace.types
            return place
        }

        List list = []
        listResults.each {
                list << parsePlace(it)
        }
        return list
    }
}
