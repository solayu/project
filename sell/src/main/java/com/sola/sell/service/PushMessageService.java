package com.sola.sell.service;

import com.sola.sell.dto.OrderDto;

public interface PushMessageService {

    void orderStatus(OrderDto orderDto);
}
