package pps.android.events


import com.jakewharton.rxrelay2.PublishRelay
import com.jakewharton.rxrelay2.Relay

import io.reactivex.Observable

/***
 * A simple Event Bus powered by Jake Wharton's RxRelay and RxJava2
 */
class RxEventBus {
    private val busSubject: Relay<Any> = PublishRelay.create<Any>().toSerialized()

    /**
     * Registers for a particular event and returns an observable for subscription.
     *
     * @param eventClass the event
     * @param <T>        the class type of the event
     * @return observable that can be subscribed to.
    </T> */
    fun <T> register(eventClass: Class<T>): Observable<T> {
        return busSubject
                .filter { event -> event.javaClass == eventClass }
                .map { obj -> obj as T }
    }

    /**
     * Sends an event to all the observers who have registered to receive the event type.
     *
     * @param event an Event of any type.
     */
    fun post(event: Any) {
        busSubject.accept(event)
    }
}