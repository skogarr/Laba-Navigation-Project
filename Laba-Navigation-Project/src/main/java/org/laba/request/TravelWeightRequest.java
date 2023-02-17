package org.laba.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelWeightRequest {

    private long transitPointAId;

    private long transitPointBId;

    private double carWeight;

    private double busWeight;

    private double tramWeight;

    private double metroWeight;

}