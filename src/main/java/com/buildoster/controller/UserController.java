package com.buildoster.controller;

import com.buildoster.jwtpayload.request.OrderRequest;
import com.buildoster.jwtpayload.request.SignupRequest;
import com.buildoster.jwtpayload.response.ErrorResponse;
import com.buildoster.jwtpayload.response.ResponseMessage;
import com.buildoster.model.*;
import com.buildoster.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderListService orderListService;

    /*------------------------------- User Operation -------------------------------*/

    //Get the User profile details with address and other details
    @GetMapping("/user-profile")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getUserProfileDetails(Authentication authentication) {
        try {
            Optional<User> user = userService.getUserProfile(authentication.getName().toString());
            if (user.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Update the user profile with name, phone number and email
    @PutMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity updateUserProfile(@Valid @RequestBody SignupRequest signupRequest, Authentication authentication) {
        try {
            Optional<User> user1 = userService.getUserProfile(authentication.getName().toString());
            if (user1.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                if (!signupRequest.getEmail().equals(authentication.getName().toString())) {
                    if (userService.existsByEmail(signupRequest.getEmail())) {
                        return new ResponseEntity<>(new ErrorResponse("Fail -> Email is already in use!"),
                                HttpStatus.BAD_REQUEST);
                    }
                } else {
                    User user = new User(user1.get().getId(), signupRequest.getF_name(), signupRequest.getL_name(), signupRequest.getEmail(), signupRequest.getPhone());
                    int user2 = userService.updateUser(user);
                    if (user2 != 0)
                        return new ResponseEntity<>(new ResponseMessage("Your profile updated successfully!"), HttpStatus.OK);
                    else
                        return new ResponseEntity<>(new ErrorResponse("Your profile did not update!"), HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    // Get all added user address
    @GetMapping("/user/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getAddressByUserId(Authentication authentication) {
        try {
            Optional<User> user = userService.getUserProfile(authentication.getName().toString());
            if (user.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                List<Address> addresses = addressService.getUserAddress(user.get().getId());
                if (addresses.isEmpty()) {
                    return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity(addresses, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /*-------------------------- Address Operation ---------------------------*/
    //Add user address with userName, lane1, lane2, city, state and pin code
    @PostMapping("/address/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity createUserAddress(@Valid @RequestBody Address address, Authentication authentication) {
        try {
            Optional<User> user = userService.getUserProfile(authentication.getName().toString());
            if (user.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                address.setUser_id(user.get().getId());
                addressService.addAddress(address);
                return new ResponseEntity<>(new ResponseMessage("Details added successfully!"), HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        }
    }

    // user will update added own address
    @PutMapping("/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity updateUserAddress(@Valid @RequestBody Address address) {
        try {
            addressService.updateAddress(address);
            return new ResponseEntity<>(new ResponseMessage("Details updated successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        }
    }

    //Get the user address based on the address id
    @GetMapping("/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getUserAddressById(@RequestParam(value = "address-id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
        try {
            Optional<Address> address = addressService.getAddress(id);
            if (address.isEmpty()) {
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity(address, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    //Delete the user address
    @DeleteMapping("/address")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteAddressById(@RequestParam(value = "address-id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
        try {
            addressService.deleteAddress(id);
            return new ResponseEntity<>(new ResponseMessage("Details updated successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    /*--------------------------------- Cart Operation ----------------------------------*/
//User add the product in the cart
    @PostMapping("/cart/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity createCart(@RequestParam(value = "product-id", required = false) Long id, Authentication authentication, Cart cart) {
        if (id == null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
        try {
            Optional<User> user = userService.getUserProfile(authentication.getName().toString());
            if (user.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                long millis = System.currentTimeMillis();
                cart.setUser_id(user.get().getId());
                cart.setDate(new Date(millis));
                cart.setProduct_id(id);
                cartService.addCart(cart);
                return new ResponseEntity<>(new ResponseMessage("Product added successfully!"), HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        }
    }

    //Remove product from cart
    @DeleteMapping("/cart")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteCartItems(@RequestParam(value = "cart-id", required = false) Long id) {
        if (id == null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
        try {
            cartService.deleteCartItem(id);
            return new ResponseEntity<>(new ResponseMessage("Product Removed!"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        }
    }

    //User will get all cart items those he added
    @GetMapping("/cart")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity findCartItemsByUserId(Authentication authentication) {
        try {
            Optional<User> user = userService.getUserProfile(authentication.getName().toString());
            if (user.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                List<Cart> carts = cartService.getAllCartItemsByUserId(user.get().getId());
                if (carts.isEmpty())
                    return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity(carts, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    /*--------------------------------- Order Operation ------------------------------------*/
    //User can place order to buy products
    @PostMapping("/order")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        if (orderRequest == null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
        try {
            String orderStatus = "Pending";
            Order order = new Order(orderRequest.getOrder_date(), orderRequest.getTransaction_type(), orderStatus, orderRequest.getUser_id(), orderRequest.getAddress_id());
            Order order1 = orderService.addOrder(order);
            if (order1.getId() != null) {
                for (OrderList list : orderRequest.getOrder_list()) {
                    double totalPrice = 0;
                    totalPrice = list.getQuantity() * list.getProduct_price();
                    OrderList list1 = new OrderList(list.getQuantity(), list.getProduct_price(), totalPrice, orderStatus, list.getProduct_id(), order1.getId());
                    orderListService.addOrderList(list1);
                }
                return new ResponseEntity<>(new ResponseMessage("Order placed successfully!"), HttpStatus.OK);
            } else
                return new ResponseEntity(new ErrorResponse("Sorry Order did not placed try again!"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    //User will see all placed orders in our profile
    @GetMapping("/order/user")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getUserOrders(Authentication authentication) {
        try {
            Optional<User> user = userService.getUserProfile(authentication.getName().toString());
            System.out.println(user.get().getId());
            if (user.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, Session timeout!"), HttpStatus.BAD_REQUEST);
            else {
                List<Order> orders = orderService.getAllOrderByUserId(user.get().getId());
                System.out.println(orders);
                if (orders.isEmpty())
                    return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity<>(orders, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    //User or Admin can see single order full details
    @GetMapping("/order")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getOrderByOrderId(@RequestParam(value = "order-id", required = false) Long id) {
        try {
            if (id == null)
                return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
            else {
                Optional<Order> order = orderService.getOrderById(id);
                if (order.isEmpty())
                    return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
                else
                    return new ResponseEntity<>(order, HttpStatus.OK);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    // Admin can get All order in descending order based on date
    @GetMapping("/orders")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrder();
            if (orders.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    //Update the order status like cancel, pending, completed and others
    // This operation performed by user and admin both
    @PutMapping("/order")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity updateOrder(@RequestParam(value = "order-id", required = false) Long id, @RequestParam(value = "order-status", required = false) String order_status) {
        try {
            if (id == null || order_status == null)
                return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
            else {
                int data = orderService.updateOrder(order_status, id);
                if (data != 0)
                    return new ResponseEntity<>(new ResponseMessage("Order " + order_status + " successfully!"), HttpStatus.OK);
                else
                    return new ResponseEntity(new ErrorResponse("Order did not " + order_status + "!"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }

    //Update single product in a lot of products ordered status like cancel, pending, completed and others
    // This operation performed by user and admin both
    @PutMapping("/order-list")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity updateSingleOrderedItem(@RequestParam(value = "order-list-id", required = false) Long id, @RequestParam(value = "order-status", required = false) String order_status) {
        try {
            if (id == null || order_status == null)
                return new ResponseEntity(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
            else {
                int data = orderListService.updateOrder(order_status, id);
                if (data != 0)
                    return new ResponseEntity<>(new ResponseMessage("Order " + order_status + " successfully!"), HttpStatus.OK);
                else
                    return new ResponseEntity(new ErrorResponse("Order did not " + order_status + "!"), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return new ResponseEntity(new ErrorResponse("Server error"), HttpStatus.BAD_REQUEST);
        }
    }
}