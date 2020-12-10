package org.randomidea.flow.controller;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.randomidea.flow.stream.FlowStores;
import org.randomidea.flow.transforms.EventFlowTransform;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@RestController
public class EventFlowController {

    // Visible for testing
    static final String MESSAGE_TRY_AGAIN_LATER = "Unable to process request for event [%s]. "
            + "The application may not have been fully initialized. Please try again later.";

    // Visible for testing
    static final String MESSAGE_NOT_FOUND = "No events found for [%s]";

    private final FlowStores stores;

    public EventFlowController(FlowStores stores) {
        this.stores = stores;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/eventflow")
    public ResponseEntity<String> getEventFlowByKey(@RequestParam("key") String key) {
        ReadOnlyKeyValueStore<Object, Object> correlatedEventsByKeyStore = stores.getCorrelatedEventsByKeyStore();

        if (correlatedEventsByKeyStore == null) {
            String message = String.format(MESSAGE_TRY_AGAIN_LATER, key);
            log.warn(message);
            return new ResponseEntity<>(message, HttpStatus.SERVICE_UNAVAILABLE);
        }

        Object eventsByKey = correlatedEventsByKeyStore.get(key);

        if (eventsByKey == null) {
            String message = String.format(MESSAGE_NOT_FOUND, key);
            log.warn(message);
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        List<String> children = (List<String>) eventsByKey;

        List<EventFlowTransform> events = getAllEventsByCompositeKey(children);
        EventFlowResponse eventFlowResponse = new EventFlowResponse().build(events, key);
        return new ResponseEntity<>(eventFlowResponse.toString(), HttpStatus.OK);
    }

    private List<EventFlowTransform> getAllEventsByCompositeKey(List<String> children) {
        ReadOnlyKeyValueStore<Object, EventFlowTransform> allEventsByCompositeKey = stores.getAllEventsByCompositeKey();

        return children.stream().map(allEventsByCompositeKey::get).collect(Collectors.toList());
    }
}
