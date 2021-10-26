package com.buildoster.controller;


import com.buildoster.jwtpayload.request.LoginRequest;
import com.buildoster.jwtpayload.request.SignupRequest;
import com.buildoster.jwtpayload.response.ErrorResponse;
import com.buildoster.jwtpayload.response.JwtResponse;
import com.buildoster.jwtpayload.response.ResponseMessage;
import com.buildoster.model.*;
import com.buildoster.repository.RoleRepository;
import com.buildoster.security.jwt.JwtProvider;
import com.buildoster.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ConfigurationTypeService configurationTypeService;
    @Autowired
    private ProductDescriptionService productDescriptionService;


    @PostMapping("/sign-in")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignupRequest signUpRequest) {
        if(userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User(signUpRequest.getF_name(),signUpRequest.getL_name(),encoder.encode(signUpRequest.getPassword()),signUpRequest.getPhone(),signUpRequest.getEmail());
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch(role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.createUser(user);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"),HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity getAllCategory(){
        try{
            List<Category>  categories =  categoryService.getAllCategoryList();
            if(categories.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( categories,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/category")
    public ResponseEntity findCategoryById(@RequestParam(value = "category-id",required = false) Long category_id){
        if (category_id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            Optional<Category> categories =  categoryService.getCategoryById(category_id);
            if(categories.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( categories,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    //Sub category Operation
    @GetMapping("/sub-categories")
    public ResponseEntity getAllSubCategory(){
        try{
            List<SubCategory>  subCategories =  subCategoryService.getAllSubCategoryList();
            if(subCategories.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( subCategories,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sub-category")
    public  ResponseEntity findSubCategoryById(@RequestParam(value = "subcategory-id",required = false) Long subcategory_id){
        if (subcategory_id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            Optional<SubCategory> subCategory =  subCategoryService.findSubCategoryById(subcategory_id);
            if(subCategory.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( subCategory,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    //Product Category Operation
    @GetMapping("/product-categories")
    public ResponseEntity getAllProductCategory(){
        try{
            List<ProductCategory>  productCategories =  productCategoryService.findAll();
            if(productCategories.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( productCategories,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product-category")
    public ResponseEntity findProductCategoryById(@RequestParam(value = "product-category-id",required = false) Long id){
        if (id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            Optional<ProductCategory> productCategory =  productCategoryService.findById(id);
            if(productCategory.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( productCategory,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product-category/sub-category")
    public ResponseEntity findAllProductBySubCategoryId(@RequestParam(value = "sub-category-id",required = false) Long id){
        if (id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            List<ProductCategory> productCategories = productCategoryService.findBySubACategoryId(id);
            if(productCategories.isEmpty()){
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"),HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity( productCategories,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }
    //Product Operation
    @GetMapping("/products")
    public ResponseEntity getAllProductList(){
        try{
            List<Product> products = productService.getAllProductList();
            if(products.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                return new ResponseEntity(products,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product")
    public ResponseEntity getProductById(@RequestParam(value = "product-id",required = false) Long id){
        if (id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            Optional<Product> products = productService.getProductById(id);
            if(products.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                return new ResponseEntity( products,HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/sub-category")
    public ResponseEntity getProductBySubCategoryId(@RequestParam(value = "id",required = false) Long id){
        if (id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            List<Product> products = productService.getProductListBySubCategoryId(id);
            if(products.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                return new ResponseEntity( products,HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    //Product Description Operation
    @GetMapping("/product-description")
    public ResponseEntity getAllDescriptionByProductId(@RequestParam(value = "product-id",required = false) Long id){
        if (id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            List<ProductDescription> productDescriptions =  productDescriptionService.findAllDescByProductId(id);
            if(productDescriptions.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                return new ResponseEntity(productDescriptions,HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }

    //Configuration operation
    @GetMapping("/configuration-type/configuration")
    public ResponseEntity getConfTypeByConfigurationId(@RequestParam(value = "configuration-id",required = false) Long id){
        if (id==null)
            return new ResponseEntity(new ErrorResponse("Bad Request!"),HttpStatus.BAD_REQUEST);
        try{
            List<ConfigurationType> configurationTypes =  configurationTypeService.findByConfigurationId(id);
            if(configurationTypes.isEmpty())
                return new ResponseEntity(new ErrorResponse("Sorry, No Record Found!"), HttpStatus.BAD_REQUEST);
            else {
                return new ResponseEntity(configurationTypes,HttpStatus.OK);
            }
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(new ErrorResponse("Server error"),HttpStatus.BAD_REQUEST);
        }
    }
}
