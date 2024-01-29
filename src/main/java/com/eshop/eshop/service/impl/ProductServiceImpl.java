package com.eshop.eshop.service.impl;

import com.eshop.eshop.dto.ProductDto;
import com.eshop.eshop.entity.ManufacturerEntity;
import com.eshop.eshop.entity.MerchantStoreEntity;
import com.eshop.eshop.entity.product.ProductEntity;
import com.eshop.eshop.exception.ServiceException;
import com.eshop.eshop.mapper.ManufacturerMapper;
import com.eshop.eshop.mapper.MerchantMapper;
import com.eshop.eshop.mapper.ProductMapper;
import com.eshop.eshop.repository.ManufacturerRepository;
import com.eshop.eshop.repository.MerchantRepository;
import com.eshop.eshop.repository.ProductRepository;
import com.eshop.eshop.service.AuthService;
import com.eshop.eshop.service.ManufacturerService;
import com.eshop.eshop.service.MerchantService;
import com.eshop.eshop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public ProductDto createProduct(ProductDto product) {

        ManufacturerEntity manufacturer = manufacturerRepository.findById(product.getManufacturer().getId()).orElseThrow(()-> new ServiceException(HttpStatus.NOT_FOUND,"Manufacturer was not found given id !."));

        MerchantStoreEntity merchantStore = merchantMapper.convertToEntity(merchantService.getMerchantById(product.getMerchantStore().getId()));


        product.setCreatedBy(authService.getAuthenticatedUsername());
        product.setManufacturer(manufacturerMapper.convertToDto(manufacturer));
        product.setMerchantStore(merchantMapper.convertToDto(merchantStore));

        ProductEntity newProduct = productRepository.save(productMapper.convertToEntity(product));

        List<ProductEntity> products = manufacturer.getProducts();
        products.add(newProduct);
        manufacturer.setProducts(products);

        manufacturerRepository.save(manufacturer);

        return productMapper.convertToDto(newProduct);
    }

    @Override
    public List<ProductDto> getProductList() {

        List<ProductEntity> listOfTheProducts = productRepository.findByDeletedFalse();

        return listOfTheProducts.stream().map((product) -> productMapper.convertToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(long id) {
        ProductEntity product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        return productMapper.convertToDto(product);
    }

    @Override
    public ProductDto updateProduct(long id, ProductDto productDto) {

        ProductEntity product = productRepository.findById(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));

        ManufacturerEntity manufacturer = manufacturerRepository.findById(productDto.getManufacturer().getId()).orElseThrow(()-> new ServiceException(HttpStatus.NOT_FOUND,"Manufacturer was not found given id !."));

        MerchantStoreEntity merchantStore = merchantRepository.findById(productDto.getMerchantStore().getId()).orElseThrow(()-> new ServiceException(HttpStatus.NOT_FOUND,"Merchant store was not found given id !."));

        ProductEntity updatedProduct = productMapper.convertToEntity(productDto);

        updatedProduct.setManufacturer(manufacturer);
        updatedProduct.setMerchantStore(merchantStore);
        updatedProduct.setModifiedBy(authService.getAuthenticatedUsername());

        return productMapper.convertToDto(productRepository.save(updatedProduct));
    }

    @Override
    public void deleteProduct(long id) {
        ProductEntity product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "Product was not found given id!."));
        product.setDeleted(true);
        productRepository.save(product);
    }

}
