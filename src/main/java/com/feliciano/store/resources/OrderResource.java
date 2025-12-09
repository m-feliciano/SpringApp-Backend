package com.feliciano.store.resources;

import com.feliciano.store.dto.OrderDto;
import com.feliciano.store.resources.domain.Order;
import com.feliciano.store.resources.mappers.OrderMapper;
import com.feliciano.store.resources.openapi.OrderResourceOpenApi;
import com.feliciano.store.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/v1/orders")
public class OrderResource implements OrderResourceOpenApi {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDto> find(@PathVariable Integer id) {
        Order obj = orderService.find(id);
        OrderDto response = orderMapper.toResponse(obj);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody Order obj) {
        obj = orderService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<OrderDto>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                 @RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
                                                 @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Order> list = orderService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list.map(orderMapper::toResponse));
    }

}
