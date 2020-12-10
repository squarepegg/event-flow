package org.randomidea.flow.transforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventFlowTransform implements Serializable {

    private Object key;
    private String eventType;
    private String correlationId;
    private String sourceTopic;
    private Long timestamp;
}
