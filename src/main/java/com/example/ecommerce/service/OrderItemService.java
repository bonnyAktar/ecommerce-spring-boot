package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderItemRequest;
import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderItemService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderItemService(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

//create orderitem
    public OrderItem createorderitem(OrderItemRequest request){
        Order order=orderRepository.findById(request.getOrderid()).orElse(null);
        Product product=productRepository.findById(request.getProductid()).orElse(null);

        if (order==null || product==null){
            return null;
        }

        OrderItem orderItem=new OrderItem();
        orderItem.setOrder(order);
        orderItem.setProduct(product);
        orderItem.setQuantity(request.getQuantity());
        orderItem.setPrice(product.getPrice());

        OrderItem saveorderitem=orderItemRepository.save(orderItem);

        double totalprice=0;

        List<OrderItem> items=orderItemRepository.findAll();
        for (OrderItem item:items){
            if (item.getOrder().getId()==order.getId()){
                totalprice = totalprice +(item.getPrice() * item.getQuantity());
            }
        }
        order.setTotalPrice(totalprice);
        orderRepository.save(order);

        return saveorderitem;
    }

    //get all orderitems
    public List<OrderItem> getallorderitems(){
        return orderItemRepository.findAll();
    }

    //get orderitem by id
    public Optional<OrderItem> getorderitembyid(Integer id){
        return orderItemRepository.findById(id);
    }

    //update orderitem
    public Optional<OrderItem> updateorderitem(Integer id,OrderItemRequest request){
        return orderItemRepository.findById(id).map(orderItem->{
            Order order=orderRepository.findById(request.getOrderid()).orElse(null);
            Product product=productRepository.findById(request.getProductid()).orElse(null);
            if (order==null || product==null){
                return null;
            }
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(request.getQuantity());
            orderItem.setPrice(product.getPrice());

            OrderItem saveorderitem=orderItemRepository.saveAndFlush(orderItem);

            double totalprice=0;

            List<OrderItem> items=orderItemRepository.findAll();
            for (OrderItem item:items){
                if (item.getOrder().getId()==order.getId()){
                    totalprice = totalprice +(item.getPrice() * item.getQuantity());
                }
            }
            order.setTotalPrice(totalprice);
            orderRepository.save(order);

            return saveorderitem;

        });
    }

    //delete orderitem
    public boolean deleteorderitem(Integer id){
        if (orderItemRepository.existsById(id)){
            orderItemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
