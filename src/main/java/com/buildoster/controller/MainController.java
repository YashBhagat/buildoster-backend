package com.buildoster.controller;

import com.buildoster.jwtpayload.request.ProductDescriptionRequest;
import com.buildoster.jwtpayload.response.ErrorResponse;
import com.buildoster.jwtpayload.response.ResponseMessage;
import com.buildoster.model.*;
import com.buildoster.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class MainController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ConfigurationService configurationService;
    @Autowired
    private ConfigurationTypeService configurationTypeService;
    @Autowired
    private ProductDescriptionService productDescriptionService;
    @Autowired
    private ImageService imageService;


    @PostMapping("/category/add")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category) {
        try {
            Category category1 = categoryService.createCategory(category);
            if (category1.getId() != null)
                return new ResponseEntity<>(new ResponseMessage("Category added successfully!"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Sub Category Operation
    @PostMapping("/sub-category/add")
    public ResponseEntity<?> createSubCategory(@Validated @RequestBody SubCategory subCategory) {
        try {
            SubCategory subCategory1 = subCategoryService.createSubCategory(subCategory);
            if (subCategory1.getId() != null)
                return new ResponseEntity<>(new ResponseMessage("SubCategory added successfully!"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Product Category Operation
    @PostMapping("/product-category/add")
    public ResponseEntity<?> createProductCategory(@Validated @RequestBody ProductCategory productCategory) {
        try {
            ProductCategory productCategory1 = productCategoryService.createProductCategory(productCategory);
            if (productCategory1.getId() != null)
                return new ResponseEntity<>(new ResponseMessage("Details added successfully!"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Product Operation
    @PostMapping("/product/add")
    public ResponseEntity<?> createProduct(@Valid @RequestBody Product product) {
        try {
            ConfigurationType configurationType = configurationTypeService.findById(product.getConfiguration_type_id()).orElseThrow(() -> new RuntimeException("Fail! -> Cause: No data found."));
            Set<ConfigurationType> configurationTypes = new HashSet<>();
            configurationTypes.add(configurationType);
            product.setConfigurationTypes(configurationTypes);
            Product product1 = productService.addProductDetails(product);
            if (product1.getId() != null)
                return new ResponseEntity<>(new ResponseMessage("Details added successfully!"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Image operation
    @PostMapping("/product-img/add")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile[] file, @RequestParam(value = "product_id", required = false) Long product_id) {
        if (product_id == null)
            return new ResponseEntity<>(new ErrorResponse("Please Provide productId!"), HttpStatus.BAD_REQUEST);
        if (file.length < 0) {
            for (MultipartFile multipartFile : file) {
                Image images = new Image();
                String imagePath;
                imagePath = "/home/rajput/AllProjects/buildoster/src/main/java/com/buildoster/image/productimage/";
                imagePath = imagePath + String.valueOf(product_id + "-" + multipartFile.getOriginalFilename());
                images.setProduct_id(product_id);
                images.setImage_url(product_id + "-" + multipartFile.getOriginalFilename());
                try {
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(imagePath);
                    Files.write(path, bytes);
                    System.out.println("File Uploaded Successfully");
                    if (product_id != null) {
                        Image image = imageService.addImages(images);
                        if (image.getId() != null)
                            return new ResponseEntity<>(new ResponseMessage("Images uploaded successfully!"), HttpStatus.OK);
                        else
                            return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
                    } else
                        return new ResponseEntity<>(new ResponseMessage("Images uploaded successfully!"), HttpStatus.OK);
                } catch (Exception e) {
                    System.out.println("Exception" + e);
                    return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
                }
            }
        } else {
            System.out.println("File is Empty not Uploaded");
            return new ResponseEntity<>(new ErrorResponse("File is Empty not Uploaded!"), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    @PostMapping("/other-img/add")
    public ResponseEntity uploadOtherImageFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            String imagePath = "/home/rajput/AllProjects/buildoster/src/main/java/com/buildoster/image/otherimage/";
            imagePath = imagePath + String.valueOf(file.getOriginalFilename());
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(imagePath);
                Files.write(path, bytes);
                System.out.println("File Uploaded Successfully");
                return new ResponseEntity<>(new ResponseMessage("Images uploaded successfully!"), HttpStatus.OK);
            } catch (Exception e) {
                System.out.println("Exception" + e);
                return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
            }
        } else {
            System.out.println("File is Empty not Uploaded");
            return new ResponseEntity<>(new ErrorResponse("File is Empty not Uploaded!"), HttpStatus.BAD_REQUEST);
        }
    }

    //Product Description Operation
    @PostMapping(path = "/product-description/add",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity createProductDescription(@Valid @ModelAttribute ProductDescriptionRequest prodDescReq) {
        if (prodDescReq.getMultipartFile() == null || prodDescReq.getProduct_id() == null)
            return new ResponseEntity<>(new ErrorResponse("Bad Request!"), HttpStatus.BAD_REQUEST);
        try {
            MultipartFile file = prodDescReq.getMultipartFile();
            ProductDescription productDescription = new ProductDescription(prodDescReq.getDescription_headline(), prodDescReq.getDescription(), file.getOriginalFilename(), prodDescReq.getProduct_id());
            ProductDescription response = productDescriptionService.addProductDescription(productDescription);
            if (response.getId() != null) {
                String imagePath = "/home/rajput/AllProjects/buildoster/src/main/java/com/buildoster/image/productdescpritionimage/";
                imagePath = imagePath + String.valueOf(response.getId()+"-"+file.getOriginalFilename());
                try {
                    byte[] bytes = file.getBytes();
                    Path path = Paths.get(imagePath);
                    Files.write(path, bytes);
                    System.out.println("File Uploaded Successfully");
                    return new ResponseEntity<>(new ResponseMessage("Details added successfully!"), HttpStatus.OK);
                } catch (Exception e) {
                    System.out.println("Exception" + e);
                    return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
                }
            } else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    //Configuration operation
    @PostMapping("/configuration")
    public ResponseEntity createConfiguration(@Valid @RequestBody Configuration configuration) {
        try {
            Configuration configuration1 = configurationService.addConfigurationDetail(configuration);
            if (configuration1.getId() != null)
                return new ResponseEntity<>(new ResponseMessage("Details added successfully!"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    //Configuration Type operation
    @PostMapping("/configuration-type/add")
    public ResponseEntity addConfigurationTypeDetails(@Valid @RequestBody ConfigurationType configurationType) {
        try {
            ConfigurationType configurationType1 = configurationTypeService.addConfigurationType(configurationType);
            if (configurationType1.getId() != null)
                return new ResponseEntity<>(new ResponseMessage("Details added successfully!"), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ErrorResponse("Server Error!"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse(e.getCause().getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
